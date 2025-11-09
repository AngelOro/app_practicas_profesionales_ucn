package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.mapper;

import co.com.ucn.practicas.domain.model.entity.Empresa;
import co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity.EmpresaEntity;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {
    public Empresa toModel(EmpresaEntity entity) {
        if (entity == null) return null;
        return Empresa.builder()
                .idEmpresa(entity.getIdEmpresa())
                .nombre(entity.getNombre())
                .nit(entity.getNit())
                .correo(entity.getCorreo())
                .telefono(entity.getTelefono())
                .activo(entity.getActivo())
                .build();
    }

    public EmpresaEntity toEntity(Empresa model) {
        if (model == null) return null;
        var entity = new EmpresaEntity();
        entity.setIdEmpresa(model.getIdEmpresa());
        entity.setNombre(model.getNombre());
        entity.setNit(model.getNit());
        entity.setCorreo(model.getCorreo());
        entity.setTelefono(model.getTelefono());
        entity.setActivo(model.getActivo());
        return entity;
    }
}
