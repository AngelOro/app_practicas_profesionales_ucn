package co.com.ucn.practicas.domain.usecase;

import co.com.ucn.practicas.domain.model.entity.Empresa;
import co.com.ucn.practicas.domain.usecase.ports.EmpresaRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EmpresaUseCase {

    private final EmpresaRepositoryPort empresaRepositoryPort;

    public Empresa crear(Empresa empresa) {
        validarCrear(empresa);
        empresaRepositoryPort.findByNit(empresa.getNit()).ifPresent(e -> {
            throw new IllegalArgumentException("NIT ya registrado");
        });
        return empresaRepositoryPort.save(empresa);
    }

    public Empresa obtener(Long idEmpresa) {
        validarId(idEmpresa);
        return empresaRepositoryPort.findById(idEmpresa)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));
    }

    public List<Empresa> listar(int page, int size) {
        validarPaginacion(page, size);
        return empresaRepositoryPort.list(page, size);
    }

    private void validarCrear(Empresa e) {
        if (e == null) throw new IllegalArgumentException("Empresa requerida");
        if (isBlank(e.getNombre())) throw new IllegalArgumentException("nombre requerido");
        if (isBlank(e.getNit())) throw new IllegalArgumentException("nit requerido");
        if (isBlank(e.getCorreo())) throw new IllegalArgumentException("correo requerido");
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
