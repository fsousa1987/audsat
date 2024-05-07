package br.com.francisco.test.audsat.core.infra.repository;

import br.com.francisco.test.audsat.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
