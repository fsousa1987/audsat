package br.com.francisco.test.audsat.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private UUID id;
    private String model;
    private String manufacturer;
    private String year;
    private BigDecimal fipeValue;

}
