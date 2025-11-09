package co.com.ucn.practicas.domain.usecase;

import co.com.ucn.practicas.domain.model.entity.Estudiante;
import co.com.ucn.practicas.domain.usecase.ports.EstudianteRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EstudianteUseCase {

    private final EstudianteRepositoryPort estudianteRepositoryPort;

    public Estudiante crear(Estudiante estudiante) {
        validarCrear(estudiante);
        estudianteRepositoryPort.findByCodigo(estudiante.getCodigo()).ifPresent(e -> {
            throw new IllegalArgumentException("Código de estudiante ya registrado");
        });
        return estudianteRepositoryPort.save(estudiante);
    }

    public Estudiante obtener(Long idEstudiante) {
        validarId(idEstudiante);
        return estudianteRepositoryPort.findById(idEstudiante)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
    }

    public List<Estudiante> listar(int page, int size) {
        validarPaginacion(page, size);
        return estudianteRepositoryPort.list(page, size);
    }

    private void validarCrear(Estudiante e) {
        if (e == null) throw new IllegalArgumentException("Estudiante requerido");
        if (isBlank(e.getCodigo())) throw new IllegalArgumentException("codigo requerido");
        if (isBlank(e.getNombre())) throw new IllegalArgumentException("nombre requerido");
        if (isBlank(e.getCorreo())) throw new IllegalArgumentException("correo requerido");
        if (isBlank(e.getPrograma())) throw new IllegalArgumentException("programa requerido");
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("id inválido");
    }

    private void validarPaginacion(int page, int size) {
        if (page < 0) throw new IllegalArgumentException("page no puede ser negativo");
        if (size <= 0) throw new IllegalArgumentException("size debe ser > 0");
    }

    private boolean isBlank(String s){ return s == null || s.trim().isEmpty(); }

}
