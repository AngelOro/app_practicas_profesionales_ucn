package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.adapter;

import co.com.ucn.practicas.domain.model.entity.Vacante;
import co.com.ucn.practicas.domain.usecase.ports.VacanteRepositoryPort;
import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity.VacanteEntity;
import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.repository.VacanteJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VacanteRepositoryAdapter implements VacanteRepositoryPort {
    private final VacanteJpaRepository jpa;

    public VacanteRepositoryAdapter(VacanteJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Vacante save(Vacante d) {
        return toDomain(jpa.save(toEntity(d)));
    }

    @Override
    public Optional<Vacante> findById(Long id) {
        return jpa.findById(id).map(VacanteRepositoryAdapter::toDomain);
    }

    @Override
    public List<Vacante> findOpen(String programa, String ciudad, String modalidad, int page, int size) {
        // MVP: devolver todas; luego añadir Specification/Query con filtros y paginación
        return jpa.findAll().stream().map(VacanteRepositoryAdapter::toDomain).toList();
    }

    @Override
    public void close(Long id) {
        jpa.findById(id).ifPresent(e -> { e.setEstado("CERRADA"); jpa.save(e); });
    }

    // ------- mapping -------
    private static Vacante toDomain(VacanteEntity e) {
        return Vacante.builder()
                .idVacante(e.getIdVacante())
                .idEmpresa(e.getIdEmpresa())
                .titulo(e.getTitulo())
                .descripcion(e.getDescripcion())
                .estado(e.getEstado())
                .fechaPublicacion(e.getFechaPublicacion())
                .build();
    }

    private static VacanteEntity toEntity(Vacante d) {
        var e = new VacanteEntity();
        e.setIdVacante(d.getIdVacante());
        e.setIdEmpresa(d.getIdEmpresa());
        e.setTitulo(d.getTitulo());
        e.setDescripcion(d.getDescripcion());
        e.setEstado(d.getEstado());
        e.setFechaPublicacion(d.getFechaPublicacion());
        return e;
    }
}
