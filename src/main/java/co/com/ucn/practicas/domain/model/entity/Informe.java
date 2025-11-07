package co.com.ucn.practicas.domain.model.entity;

import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Informe {
    private Long idInforme;
    private Long idPractica;
    private String periodo;
    private LocalDate fechaEntrega;
    private String urlDocumento;
    private BigDecimal calificacion;
}
