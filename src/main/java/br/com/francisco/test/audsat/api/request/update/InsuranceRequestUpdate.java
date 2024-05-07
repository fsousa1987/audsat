package br.com.francisco.test.audsat.api.request.update;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsuranceRequestUpdate {

    private CustomerRequestUpdate customer;
    private CarRequestUpdate car;
    private Boolean isActive;

}
