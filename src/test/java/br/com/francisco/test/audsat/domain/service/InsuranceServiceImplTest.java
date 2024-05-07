package br.com.francisco.test.audsat.domain.service;

import br.com.francisco.test.audsat.api.exceptionhandler.exceptions.InsuranceNotFoundException;
import br.com.francisco.test.audsat.api.exceptionhandler.exceptions.InvalidCreateInsuranceException;
import br.com.francisco.test.audsat.api.request.create.InsuranceRequestCreate;
import br.com.francisco.test.audsat.api.request.update.InsuranceRequestUpdate;
import br.com.francisco.test.audsat.api.response.InsuranceResponse;
import br.com.francisco.test.audsat.core.infra.repository.DriverRepository;
import br.com.francisco.test.audsat.core.infra.repository.InsuranceRepository;
import br.com.francisco.test.audsat.domain.entity.Driver;
import br.com.francisco.test.audsat.domain.entity.Insurance;
import br.com.francisco.test.audsat.domain.service.impl.InsuranceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static br.com.francisco.test.audsat.factory.ObjectFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class InsuranceServiceImplTest {

    public static final double BUDGET_VALUE = 573.45;

    @InjectMocks
    InsuranceServiceImpl service;

    @MockBean
    InsuranceRepository insuranceRepository;

    @MockBean
    DriverRepository driverRepository;

    @MockBean
    BudgetService budgetService;

    @InjectMocks
    ModelMapper mapper;

    @BeforeEach
    public void setUp() {
        this.service = new InsuranceServiceImpl(insuranceRepository, driverRepository, budgetService, mapper);
    }

    @Test
    public void shouldReturnInsuranceById() {
        Insurance insurance = shouldCreateAnInsurance();

        when(insuranceRepository.findById(any(UUID.class))).thenReturn(Optional.of(insurance));

        InsuranceResponse insuranceResponse = service.findById(insurance.getId());

        assertThat(insuranceResponse.getInsuranceId()).isNotNull();
        assertThat(insuranceResponse.getCustomer()).isNotNull();
        assertThat(insuranceResponse.getCar()).isNotNull();
        assertThat(insuranceResponse.getIsActive()).isTrue();
    }

    @Test
    public void shouldThrownAnExceptionWhenNotFoundInsuranceById() {
        when(insuranceRepository.findById(any(UUID.class))).thenThrow(InsuranceNotFoundException.class);

        assertThrows(InsuranceNotFoundException.class,
                () -> service.findById(UUID.randomUUID()));
    }

    @Test
    public void shouldUpdateInsuranceById() {
        InsuranceRequestUpdate insuranceRequestUpdate = shouldCreateInsuranceRequestUpdate();
        Insurance insurance = shouldCreateAnInsurance();

        when(insuranceRepository.findById(any(UUID.class))).thenReturn(Optional.of(insurance));

        service.updateById(UUID.randomUUID(), insuranceRequestUpdate);

        verify(insuranceRepository, atLeastOnce()).save(insurance);
    }

    @Test
    public void shouldCreateInsurance() {
        InsuranceRequestCreate insuranceRequestCreate = shouldCreateInsuranceRequestCreate();
        insuranceRequestCreate.getCustomer().getDriver().setBirthDate(LocalDate.now().minusYears(30));
        Insurance insurance = shouldCreateAnInsurance();

        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);
        when(driverRepository.findByDocument(anyString())).thenReturn(Optional.empty());
        when(budgetService.driverAge(any(LocalDate.class))).thenReturn(30);
        when(budgetService.calculateBudget(any(Insurance.class))).thenReturn(BigDecimal.valueOf(BUDGET_VALUE));

        InsuranceResponse insuranceResponse = service.create(insuranceRequestCreate);

        assertThat(insuranceResponse.getInsuranceId()).isNotNull();
        assertThat(insuranceResponse.getCustomer()).isNotNull();
        assertThat(insuranceResponse.getCar()).isNotNull();
        assertThat(insuranceResponse.getIsActive()).isTrue();
    }

    @Test
    public void shouldThrownExceptionWhenTryToCreateInsuranceWithInvalidDriverAge() {
        InsuranceRequestCreate insuranceRequestCreate = shouldCreateInsuranceRequestCreate();
        Driver driver = shouldCreateADriver();

        when(driverRepository.findByDocument(anyString())).thenReturn(Optional.ofNullable(driver));

        var invalidCreateInsuranceException = assertThrows(InvalidCreateInsuranceException.class,
                () -> service.create(insuranceRequestCreate));

        assertThat(invalidCreateInsuranceException.getMessage())
                .isEqualTo("Driver less than eighteen years old");
    }

    @Test
    public void shouldThrownExceptionWhenTryToCreateInsuranceWithDocumentAlreadyInDatabase() {
        InsuranceRequestCreate insuranceRequestCreate = shouldCreateInsuranceRequestCreate();
        Driver driver = shouldCreateADriver();

        when(budgetService.driverAge(any(LocalDate.class))).thenReturn(30);
        when(driverRepository.findByDocument(anyString())).thenReturn(Optional.ofNullable(driver));

        var invalidCreateInsuranceException = assertThrows(InvalidCreateInsuranceException.class,
                () -> service.create(insuranceRequestCreate));

        assertThat(invalidCreateInsuranceException.getMessage())
                .isEqualTo("Insurance with same document found in database!");
    }

    @Test
    public void shouldDeleteInsuranceById() {
        Insurance insurance = shouldCreateAnInsurance();

        when(insuranceRepository.findById(any(UUID.class))).thenReturn(Optional.of(insurance));

        service.deleteById(UUID.randomUUID());

        verify(insuranceRepository, atLeastOnce()).delete(insurance);
    }

}
