package co.com.ucn.practicas.domain.model.entity;

import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    private Long idEmpresa;
    private String nombre;
    private String nit;
    private String correo;
    private String telefono;
    private Boolean activo;
}
