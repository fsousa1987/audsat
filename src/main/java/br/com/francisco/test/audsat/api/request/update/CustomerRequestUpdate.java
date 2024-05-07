package br.com.francisco.test.audsat.api.request.update;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestUpdate {

    private String name;
    private DriverRequestUpdate driver;

}
