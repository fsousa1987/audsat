package br.com.francisco.test.audsat.api.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverRequestCreate {

    @NotBlank(message = "Field document can not be empty")
    private String document;

    @NotNull(message = "Field birthDate can not be empty")
    private LocalDate birthDate;

}
