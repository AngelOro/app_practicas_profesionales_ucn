package co.com.ucn.practicas.domain.usecase.ports;

import co.com.ucn.practicas.domain.model.entity.Vacante;

import java.util.List;
import java.util.Optional;

public interface VacanteRepositoryPort {
    Vacante save(Vacante vacante);
    Optional<Vacante> findById(Long id);
    List<Vacante> findOpen(String programa, String ciudad, String modalidad, int page, int size);
    void close(Long id);
}
