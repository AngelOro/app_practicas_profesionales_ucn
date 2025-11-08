package co.com.ucn.practicas.domain.model.entity;

import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private Long idEstudiante;
    private String codigo;
    private String nombre;
    private String correo;
    private String programa;
    private Boolean activo;
}

