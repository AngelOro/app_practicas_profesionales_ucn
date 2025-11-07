
package co.com.ucn.practicas.domain.model.entity;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Vacante {
    private Long idVacante;
    private Long idEmpresa;
    private String titulo;
    private String descripcion;
    private String estado;
    private LocalDate fechaPublicacion;
}
