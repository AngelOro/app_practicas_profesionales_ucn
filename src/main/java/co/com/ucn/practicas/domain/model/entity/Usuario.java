package co.com.ucn.practicas.domain.model.entity;

import lombok.*;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long idUsuario;
    private String email;
    private String passwordHash;
    private String rol;
    private Long idEstudiante;
    private Long idEmpresa;
    private Boolean activo;
}
