package co.com.ucn.practicas.domain.usecase.ports;

import co.com.ucn.practicas.domain.model.entity.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepositoryPort {
    Empresa save(Empresa empresa);
    Optional<Empresa> findById(Long id);
    Optional<Empresa> findByNit(String nit);
    List<Empresa> list(int page, int size);
}
