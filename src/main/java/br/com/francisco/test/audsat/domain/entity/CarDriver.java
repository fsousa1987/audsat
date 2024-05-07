package br.com.francisco.test.audsat.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "car_drivers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Car car;

    private Boolean isMainDriver;

}
