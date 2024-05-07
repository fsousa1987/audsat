package br.com.francisco.test.audsat.core.infra.repository;

import br.com.francisco.test.audsat.domain.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {

    Optional<Driver> findByDocument(String document);

}
