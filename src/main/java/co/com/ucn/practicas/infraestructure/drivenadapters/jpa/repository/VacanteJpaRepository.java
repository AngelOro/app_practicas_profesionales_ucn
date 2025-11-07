package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.repository;

import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity.VacanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacanteJpaRepository extends JpaRepository<VacanteEntity, Long> {
}
