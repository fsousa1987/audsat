package br.com.francisco.test.audsat.core.infra.repository;

import br.com.francisco.test.audsat.domain.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClaimRepository extends JpaRepository<Claim, UUID> {

    Optional<Claim> findByDriverId(UUID id);

    Optional<Claim> findByCarId(UUID id);
}
