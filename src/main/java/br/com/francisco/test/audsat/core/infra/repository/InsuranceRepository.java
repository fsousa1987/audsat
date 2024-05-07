package br.com.francisco.test.audsat.core.infra.repository;

import br.com.francisco.test.audsat.domain.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceRepository extends JpaRepository<Insurance, UUID> {
}
