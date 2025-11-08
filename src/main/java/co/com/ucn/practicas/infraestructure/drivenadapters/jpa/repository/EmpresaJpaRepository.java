package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.repository;

import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity.EmpresaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaJpaRepository extends JpaRepository<EmpresaEntity, Long> {
    Optional<EmpresaEntity> findByNit(String nit);
    Page<EmpresaEntity> findAll(Pageable pageable);
}
