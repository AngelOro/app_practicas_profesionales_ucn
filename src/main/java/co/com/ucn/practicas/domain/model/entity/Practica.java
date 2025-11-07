package co.com.ucn.practicas.domain.model.entity;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Practica {
    private Long idPractica;
    private Long idEstudiante;
    private Long idVacante;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
}
