package co.com.ucn.practicas.domain.usecase.ports;

import co.com.ucn.practicas.domain.model.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepositoryPort {
    Estudiante save(Estudiante estudiante);
    Optional<Estudiante> findById(Long idEstudiante);
    Optional<Estudiante> findByCodigo(String codigo);
    List<Estudiante> list(int page, int size);
}
