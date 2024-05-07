package br.com.francisco.test.audsat.api.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequestCreate {

    @NotBlank(message = "Field model can not be empty")
    private String model;

    @NotBlank(message = "Field manufacturer can not be empty")
    private String manufacturer;

    @NotBlank(message = "Field year can not be empty")
    private String year;

    @NotNull(message = "Field fipeValue can not be empty")
    private BigDecimal fipeValue;

}
