package br.com.francisco.test.audsat.api.request.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestCreate {

    @NotBlank(message = "Field name can not be empty")
    private String name;

    @NotNull(message = "Field driver can not be empty")
    @Valid
    private DriverRequestCreate driver;

}
