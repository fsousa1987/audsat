package br.com.francisco.test.audsat.factory;

import br.com.francisco.test.audsat.api.request.create.CarRequestCreate;
import br.com.francisco.test.audsat.api.request.create.CustomerRequestCreate;
import br.com.francisco.test.audsat.api.request.create.DriverRequestCreate;
import br.com.francisco.test.audsat.api.request.create.InsuranceRequestCreate;
import br.com.francisco.test.audsat.api.request.update.CarRequestUpdate;
import br.com.francisco.test.audsat.api.request.update.CustomerRequestUpdate;
import br.com.francisco.test.audsat.api.request.update.DriverRequestUpdate;
import br.com.francisco.test.audsat.api.request.update.InsuranceRequestUpdate;
import br.com.francisco.test.audsat.domain.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ObjectFactory {

    public static Car shouldCreateACar() {
        return Car
                .builder()
                .id(UUID.randomUUID())
                .model("Uno")
                .manufacturer("Chevrolet")
                .year("2000")
                .fipeValue(BigDecimal.valueOf(35896.30))
                .build();
    }

    public static Driver shouldCreateADriver() {
        return Driver
                .builder()
                .id(UUID.randomUUID())
                .document("229.364.128-71")
                .birthDate(LocalDate.now())
                .build();
    }

    public static Customer shouldCreateACustomer() {
        return Customer
                .builder()
                .id(UUID.randomUUID())
                .name("José Castro Mendes")
                .driver(shouldCreateADriver())
                .build();
    }

    public static Insurance shouldCreateAnInsurance() {
        return Insurance
                .builder()
                .id(UUID.randomUUID())
                .customer(shouldCreateACustomer())
                .car(shouldCreateACar())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .isActive(true)
                .build();
    }

    public static DriverRequestUpdate shouldCreateADriverRequestUpdate() {
        return DriverRequestUpdate
                .builder()
                .birthDate(LocalDate.now())
                .build();
    }

    public static CustomerRequestUpdate shouldCreateCustomerRequestUpdate() {
        return CustomerRequestUpdate
                .builder()
                .name("Carlos Aparecido dos Santos")
                .driver(shouldCreateADriverRequestUpdate())
                .build();
    }

    public static CarRequestUpdate shouldCreateCarRequestUpdate() {
        return CarRequestUpdate
                .builder()
                .model("Uno")
                .manufacturer("Chevrolet")
                .year("2000")
                .fipeValue(BigDecimal.valueOf(400.00))
                .build();
    }

    public static InsuranceRequestUpdate shouldCreateInsuranceRequestUpdate() {
        return InsuranceRequestUpdate
                .builder()
                .car(shouldCreateCarRequestUpdate())
                .customer(shouldCreateCustomerRequestUpdate())
                .isActive(true)
                .build();
    }

    public static DriverRequestCreate shouldCreateADriverRequestCreate() {
        return DriverRequestCreate
                .builder()
                .birthDate(LocalDate.now())
                .document("229.364.128-71")
                .build();
    }

    public static CustomerRequestCreate shouldCreateCustomerRequestCreate() {
        return CustomerRequestCreate
                .builder()
                .name("Carlos Aparecido dos Santos")
                .driver(shouldCreateADriverRequestCreate())
                .build();
    }

    public static CarRequestCreate shouldCreateCarRequestCreate() {
        return CarRequestCreate
                .builder()
                .model("Uno")
                .manufacturer("Chevrolet")
                .year("2000")
                .fipeValue(BigDecimal.valueOf(400.00))
                .build();
    }

    public static InsuranceRequestCreate shouldCreateInsuranceRequestCreate() {
        return InsuranceRequestCreate
                .builder()
                .car(shouldCreateCarRequestCreate())
                .customer(shouldCreateCustomerRequestCreate())
                .isActive(true)
                .build();
    }

    public static Claim shouldCreateClaim() {
        return Claim
                .builder()
                .id(UUID.randomUUID())
                .car(shouldCreateACar())
                .driver(shouldCreateADriver())
                .eventDate(LocalDate.now().minusYears(5))
                .build();
    }

}
