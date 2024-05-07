package br.com.francisco.test.audsat.api.request.update;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverRequestUpdate {

    private LocalDate birthDate;

}
