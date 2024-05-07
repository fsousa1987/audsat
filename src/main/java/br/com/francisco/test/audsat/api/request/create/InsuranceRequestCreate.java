package br.com.francisco.test.audsat.api.request.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsuranceRequestCreate {

    @NotNull(message = "Field customer can not be empty")
    @Valid
    private CustomerRequestCreate customer;

    @NotNull(message = "Field car can not be empty")
    @Valid
    private CarRequestCreate car;

    @NotNull(message = "Field isActive can not be empty")
    private Boolean isActive;

}
