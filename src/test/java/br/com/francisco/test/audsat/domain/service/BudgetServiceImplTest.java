package br.com.francisco.test.audsat.domain.service;

import br.com.francisco.test.audsat.core.infra.repository.ClaimRepository;
import br.com.francisco.test.audsat.domain.entity.Claim;
import br.com.francisco.test.audsat.domain.entity.Insurance;
import br.com.francisco.test.audsat.domain.service.impl.BudgetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static br.com.francisco.test.audsat.factory.ObjectFactory.shouldCreateAnInsurance;
import static br.com.francisco.test.audsat.factory.ObjectFactory.shouldCreateClaim;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BudgetServiceImplTest {

    private static final double VALUE_WHEN_RISK_IS_SIX_PERCENT = 2153.78;
    private static final double VALUE_WHEN_RISK_IS_EIGHT_PERCENT = 2871.70;
    private static final double VALUE_WHEN_RISK_IS_TEN_PERCENT = 3589.63;
    private static final double VALUE_WHEN_RISK_IS_TWELVE_PERCENT = 4307.56;

    @InjectMocks
    BudgetServiceImpl service;

    @MockBean
    ClaimRepository claimRepository;

    @BeforeEach
    public void setUp() {
        this.service = new BudgetServiceImpl(claimRepository);
    }

    @Test
    public void shouldCalculateBudgetWithRiskTaxOfSixPercent() {
        Insurance insurance = shouldCreateAnInsurance();

        BigDecimal budget = service.calculateBudget(insurance);

        Assertions.assertEquals(BigDecimal.valueOf(VALUE_WHEN_RISK_IS_SIX_PERCENT), budget);
    }

    @Test
    public void shouldCalculateBudgetWithRiskTaxOfEightPercent() {
        Insurance insurance = shouldCreateAnInsurance();
        insurance.getCustomer().getDriver().setBirthDate(LocalDate.now().minusYears(20));

        BigDecimal budget = service.calculateBudget(insurance);

        Assertions.assertEquals(BigDecimal.valueOf(VALUE_WHEN_RISK_IS_EIGHT_PERCENT).setScale(2, RoundingMode.HALF_EVEN), budget);
    }

    @Test
    public void shouldCalculateBudgetWithRiskTaxOfTenPercent() {
        Insurance insurance = shouldCreateAnInsurance();
        Claim claim = shouldCreateClaim();
        insurance.getCustomer().getDriver().setBirthDate(LocalDate.now().minusYears(20));

        when(claimRepository.findByDriverId(any(UUID.class))).thenReturn(Optional.of(claim));

        BigDecimal budget = service.calculateBudget(insurance);

        Assertions.assertEquals(BigDecimal.valueOf(VALUE_WHEN_RISK_IS_TEN_PERCENT).setScale(2, RoundingMode.HALF_EVEN), budget);
    }

    @Test
    public void shouldCalculateBudgetWithRiskTaxOfTwelvePercent() {
        Insurance insurance = shouldCreateAnInsurance();
        Claim claim = shouldCreateClaim();
        insurance.getCustomer().getDriver().setBirthDate(LocalDate.now().minusYears(20));

        when(claimRepository.findByDriverId(any(UUID.class))).thenReturn(Optional.of(claim));
        when(claimRepository.findByCarId(any(UUID.class))).thenReturn(Optional.of(claim));

        BigDecimal budget = service.calculateBudget(insurance);

        Assertions.assertEquals(BigDecimal.valueOf(VALUE_WHEN_RISK_IS_TWELVE_PERCENT).setScale(2, RoundingMode.HALF_EVEN), budget);
    }

}
