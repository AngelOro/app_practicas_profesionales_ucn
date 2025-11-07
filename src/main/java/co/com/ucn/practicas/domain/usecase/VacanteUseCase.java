package co.com.ucn.practicas.domain.usecase;


import co.com.ucn.practicas.domain.model.entity.Vacante;
import co.com.ucn.practicas.domain.usecase.ports.VacanteRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class VacanteUseCase {

    private final VacanteRepositoryPort repo;


    public Vacante crear(Vacante v) {
        validarCrear(v);
        if (v.getEstado() == null) v.setEstado("ABIERTA");
        if (v.getFechaPublicacion() == null) v.setFechaPublicacion(LocalDate.now());
        return repo.save(v);
    }

    public List<Vacante> listarAbiertas(String programa, String ciudad, String modalidad, int page, int size) {
        validarPaginacion(page, size);
        return repo.findOpen(trim(programa), trim(ciudad), trim(modalidad), page, size);
    }

    public Vacante obtener(Long idVacante) {
        validarId(idVacante);
        return repo.findById(idVacante)
                .orElseThrow(() -> new IllegalArgumentException("Vacante no encontrada"));
    }

    public Vacante editar(Long idVacante, String nuevoTitulo, String nuevaDescripcion) {
        validarId(idVacante);
        Vacante actual = obtener(idVacante);
        if (!isBlank(nuevoTitulo)) actual.setTitulo(nuevoTitulo.trim());
        if (!isBlank(nuevaDescripcion)) actual.setDescripcion(nuevaDescripcion.trim());
        return repo.save(actual);
    }

    public void cerrar(Long idVacante) {
        validarId(idVacante);
        repo.close(idVacante);
    }

    private void validarCrear(Vacante v) {
        if (v == null) throw new IllegalArgumentException("Vacante requerida");
        if (v.getIdEmpresa() == null || v.getIdEmpresa() <= 0)
            throw new IllegalArgumentException("idEmpresa requerido y > 0");
        if (isBlank(v.getTitulo())) throw new IllegalArgumentException("titulo requerido");
        if (isBlank(v.getDescripcion())) throw new IllegalArgumentException("descripcion requerida");
    }

    private void validarPaginacion(int page, int size) {
        if (page < 0) throw new IllegalArgumentException("page no puede ser negativo");
        if (size <= 0) throw new IllegalArgumentException("size debe ser > 0");
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("id inválido");
    }

    private boolean isBlank(String s){ return s == null || s.trim().isEmpty(); }
    private String trim(String s){ return s == null ? null : s.trim(); }
}
