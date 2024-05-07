package br.com.francisco.test.audsat.core.infra.repository;

import br.com.francisco.test.audsat.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}
