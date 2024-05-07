package br.com.francisco.test.audsat.domain.service;

import br.com.francisco.test.audsat.domain.entity.Insurance;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BudgetService {

    BigDecimal calculateBudget(Insurance insurance);

    Integer driverAge(LocalDate birthDate);

}
