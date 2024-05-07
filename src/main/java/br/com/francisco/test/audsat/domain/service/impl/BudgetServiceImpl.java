package br.com.francisco.test.audsat.domain.service.impl;

import br.com.francisco.test.audsat.core.infra.repository.ClaimRepository;
import br.com.francisco.test.audsat.domain.entity.Claim;
import br.com.francisco.test.audsat.domain.entity.Insurance;
import br.com.francisco.test.audsat.domain.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private static final double PERCENT_TAX_RISK = 0.02;

    private BigDecimal initialBudget = BigDecimal.valueOf(0.06);

    private final ClaimRepository claimRepository;

    public BigDecimal calculateBudget(Insurance insurance) {
        initialBudget = calculateInitialBudget(insurance);
        return calculateBaseBudget(insurance.getCar().getFipeValue(), initialBudget);
    }

    private BigDecimal calculateInitialBudget(Insurance insurance) {
        int driverAge = driverAge(insurance.getCustomer().getDriver().getBirthDate());

        if (driverAge >= 18 && driverAge <= 25) {
            initialBudget = initialBudget.add(BigDecimal.valueOf(PERCENT_TAX_RISK));
        }

        Optional<Claim> driverClaim = claimRepository.findByDriverId(insurance.getCustomer().getDriver().getId());

        if (driverClaim.isPresent()) {
            initialBudget = initialBudget.add(BigDecimal.valueOf(PERCENT_TAX_RISK));
        }

        Optional<Claim> carClaim = claimRepository.findByCarId(insurance.getCar().getId());

        if (carClaim.isPresent()) {
            initialBudget = initialBudget.add(BigDecimal.valueOf(PERCENT_TAX_RISK));
        }

        return initialBudget;
    }

    private BigDecimal calculateBaseBudget(BigDecimal fipeValue, BigDecimal budget) {
        return budget.multiply(fipeValue).setScale(2, RoundingMode.HALF_EVEN);
    }

    public Integer driverAge(LocalDate birthDate) {
        LocalDate actualDay = LocalDate.now();
        Period period = Period.between(birthDate, actualDay);
        return period.getYears();
    }

}
