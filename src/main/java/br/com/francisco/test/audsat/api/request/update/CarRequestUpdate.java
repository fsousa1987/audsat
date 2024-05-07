package br.com.francisco.test.audsat.api.request.update;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequestUpdate {

    private String model;
    private String manufacturer;
    private String year;
    private BigDecimal fipeValue;

}
