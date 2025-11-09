package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.adapter;

import co.com.ucn.practicas.domain.model.entity.Estudiante;
import co.com.ucn.practicas.domain.usecase.ports.EstudianteRepositoryPort;
import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity.EstudianteEntity;
import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.repository.EstudianteJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EstudianteRepositoryAdapter implements EstudianteRepositoryPort {

    private final EstudianteJpaRepository jpa;

    public EstudianteRepositoryAdapter(EstudianteJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Estudiante save(Estudiante d) {
        return toDomain(jpa.save(toEntity(d)));
    }

    @Override
    public Optional<Estudiante> findById(Long idEstudiante) {
        return jpa.findById(idEstudiante).map(EstudianteRepositoryAdapter::toDomain);
    }

    @Override
    public Optional<Estudiante> findByCodigo(String codigo) {
        return jpa.findByCodigo(codigo).map(EstudianteRepositoryAdapter::toDomain);
    }

    @Override
    public List<Estudiante> list(int page, int size) {
        return jpa.findAll(PageRequest.of(page, size)).stream()
                .map(EstudianteRepositoryAdapter::toDomain)
                .toList();
    }

    private static Estudiante toDomain(EstudianteEntity e) {
        return Estudiante.builder()
                .idEstudiante(e.getIdEstudiante())
                .codigo(e.getCodigo())
                .nombre(e.getNombre())
                .correo(e.getCorreo())
                .programa(e.getPrograma())
                .build();
    }

    private static EstudianteEntity toEntity(Estudiante d) {
        var e = new EstudianteEntity();
        if (d.getIdEstudiante() != null) e.setIdEstudiante(d.getIdEstudiante());
        e.setCodigo(d.getCodigo());
        e.setNombre(d.getNombre());
        e.setCorreo(d.getCorreo());
        e.setPrograma(d.getPrograma());
        return e;
    }
}
