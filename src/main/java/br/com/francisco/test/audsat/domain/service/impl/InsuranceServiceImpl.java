package br.com.francisco.test.audsat.domain.service.impl;

import br.com.francisco.test.audsat.api.exceptionhandler.exceptions.InsuranceNotFoundException;
import br.com.francisco.test.audsat.api.exceptionhandler.exceptions.InvalidCreateInsuranceException;
import br.com.francisco.test.audsat.api.request.create.InsuranceRequestCreate;
import br.com.francisco.test.audsat.api.request.update.InsuranceRequestUpdate;
import br.com.francisco.test.audsat.api.response.InsuranceResponse;
import br.com.francisco.test.audsat.core.infra.repository.DriverRepository;
import br.com.francisco.test.audsat.core.infra.repository.InsuranceRepository;
import br.com.francisco.test.audsat.domain.entity.Insurance;
import br.com.francisco.test.audsat.domain.service.BudgetService;
import br.com.francisco.test.audsat.domain.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final DriverRepository driverRepository;
    private final BudgetService budgetService;
    private final ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public InsuranceResponse findById(UUID id) {
        Insurance insurance = getInsuranceById(id);
        BigDecimal budget = initializeMountBudget(insurance);

        InsuranceResponse insuranceResponse = mapper.map(insurance, InsuranceResponse.class);
        insuranceResponse.setBudget(budget);
        return insuranceResponse;
    }

    @Override
    @Transactional
    public void updateById(UUID id, InsuranceRequestUpdate request) {
        Insurance insurance = getInsuranceById(id);
        mapper.map(request, insurance);
        insuranceRepository.save(insurance);
    }

    @Override
    @Transactional
    public InsuranceResponse create(InsuranceRequestCreate request) {
        verifyAge(request);
        verifyIfDocumentExists(request.getCustomer().getDriver().getDocument());
        Insurance insurance = mapper.map(request, Insurance.class);
        insurance = insuranceRepository.save(insurance);
        BigDecimal budget = initializeMountBudget(insurance);

        InsuranceResponse insuranceResponse = mapper.map(insurance, InsuranceResponse.class);
        insuranceResponse.setBudget(budget);
        return insuranceResponse;
    }

    private void verifyAge(InsuranceRequestCreate request) {
        Integer driverAge = budgetService.driverAge(request.getCustomer().getDriver().getBirthDate());
        if (driverAge < 18) {
            throw new InvalidCreateInsuranceException("Driver less than eighteen years old");
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        Insurance insurance = getInsuranceById(id);
        insuranceRepository.delete(insurance);
    }

    private void verifyIfDocumentExists(String document) {
        if (driverRepository.findByDocument(document).isPresent()) {
            throw new InvalidCreateInsuranceException("Insurance with same document found in database!");
        }
    }

    private Insurance getInsuranceById(UUID id) {
        return insuranceRepository
                .findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException("Insurance not found for id: ".concat(id.toString())));
    }

    private BigDecimal initializeMountBudget(Insurance insurance) {
        return budgetService.calculateBudget(insurance);
    }

}
