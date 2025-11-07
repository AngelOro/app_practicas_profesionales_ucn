package co.com.ucn.practicas.infraestructure.entrypoints.vacante;

import co.com.ucn.practicas.domain.model.entity.Vacante;
import co.com.ucn.practicas.domain.usecase.VacanteUseCase;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vacantes")
public class VacanteController {

    private final VacanteUseCase useCase;

    public VacanteController(VacanteUseCase useCase) {
        this.useCase = useCase;
    }

    /** POST /api/vacantes  - crear */
    @PostMapping
    public ResponseEntity<Vacante> crear(@RequestBody CrearVacanteRequest req) {
        Vacante v = Vacante.builder()
                .idEmpresa(req.idEmpresa())
                .titulo(req.titulo())
                .descripcion(req.descripcion())
                .estado("ABIERTA")
                .fechaPublicacion(LocalDate.now())
                .build();
        return ResponseEntity.ok(useCase.crear(v));
    }

    /** GET /api/vacantes  - listar abiertas con filtros básicos y paginación */
    @GetMapping
    public ResponseEntity<List<Vacante>> listar(
            @RequestParam(required = false) String programa,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String modalidad,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(useCase.listarAbiertas(programa, ciudad, modalidad, page, size));
    }

    /** GET /api/vacantes/{id} - detalle */
    @GetMapping("/{id}")
    public ResponseEntity<Vacante> obtener(@PathVariable("id") Long id) {
        return ResponseEntity.ok(useCase.obtener(id));
    }

    /** PATCH /api/vacantes/{id} - editar título/descripcion */
    @PatchMapping("/{id}")
    public ResponseEntity<Vacante> editar(@PathVariable("id") Long id, @RequestBody EditarVacanteRequest req) {
        return ResponseEntity.ok(useCase.editar(id, req.titulo(), req.descripcion()));
    }

    /** PATCH /api/vacantes/{id}/cerrar - cerrar vacante */
    @PatchMapping("/{id}/cerrar")
    public ResponseEntity<Void> cerrar(@PathVariable("id") Long id) {
        useCase.cerrar(id);
        return ResponseEntity.noContent().build();
    }

    // ---- DTOs mínimos ----
    public record CrearVacanteRequest(
            Long idEmpresa,
            @NotBlank String titulo,
            @NotBlank String descripcion
    ) {}

    public record EditarVacanteRequest(
            String titulo,
            String descripcion
    ) {}
}
