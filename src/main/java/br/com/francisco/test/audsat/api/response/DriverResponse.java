package br.com.francisco.test.audsat.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse {

    private UUID id;
    private String document;
    private LocalDate birthDate;

}
