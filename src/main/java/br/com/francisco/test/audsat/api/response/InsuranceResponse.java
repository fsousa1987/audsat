package br.com.francisco.test.audsat.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponse {

    private UUID insuranceId;
    private CustomerResponse customer;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private CarResponse car;
    private Boolean isActive;
    private BigDecimal budget;

}
