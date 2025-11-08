package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.adapter;

import co.com.ucn.practicas.domain.model.entity.Empresa;
import co.com.ucn.practicas.domain.usecase.ports.EmpresaRepositoryPort;
import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity.EmpresaEntity;
import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.repository.EmpresaJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmpresaRepositoryAdapter implements EmpresaRepositoryPort {

    private final EmpresaJpaRepository jpa;

    public EmpresaRepositoryAdapter(EmpresaJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Empresa save(Empresa d) {
        return toDomain(jpa.save(toEntity(d)));
    }

    @Override
    public Optional<Empresa> findById(Long idEmpresa) {
        return jpa.findById(idEmpresa).map(EmpresaRepositoryAdapter::toDomain);
    }

    @Override
    public Optional<Empresa> findByNit(String nit) {
        return jpa.findByNit(nit).map(EmpresaRepositoryAdapter::toDomain);
    }

    @Override
    public List<Empresa> list(int page, int size) {
        return jpa.findAll(PageRequest.of(page, size)).stream()
                .map(EmpresaRepositoryAdapter::toDomain)
                .toList();
    }

    private static Empresa toDomain(EmpresaEntity e) {
        return Empresa.builder()
                .idEmpresa(e.getIdEmpresa())
                .nombre(e.getNombre())
                .nit(e.getNit())
                .correo(e.getCorreo())
                .telefono(e.getTelefono())
                .build();
    }

    private static EmpresaEntity toEntity(Empresa d) {
        var e = new EmpresaEntity();
        if (d.getIdEmpresa() != null) e.setIdEmpresa(d.getIdEmpresa());
        e.setNombre(d.getNombre());
        e.setNit(d.getNit());
        e.setCorreo(d.getCorreo());
        e.setTelefono(d.getTelefono());
        return e;
    }
}
