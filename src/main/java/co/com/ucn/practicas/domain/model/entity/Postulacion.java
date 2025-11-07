package co.com.ucn.practicas.domain.model.entity;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Postulacion {
    private Long idPostulacion;
    private Long idEstudiante;
    private Long idVacante;
    private LocalDate fecha;
    private String estado;
}
