package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.repository;

import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity.EstudianteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteJpaRepository extends JpaRepository<EstudianteEntity, Long> {
    Optional<EstudianteEntity> findByCodigo(String codigo);
    Page<EstudianteEntity> findAll(Pageable pageable);
}
