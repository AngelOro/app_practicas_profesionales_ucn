package co.com.ucn.practicas.infraestructure.entrypoints;

import co.com.ucn.practicas.domain.model.entity.Estudiante;
import co.com.ucn.practicas.domain.usecase.EstudianteUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudiantesController {
    private final EstudianteUseCase useCase;

    public EstudiantesController(EstudianteUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Estudiante> crear(@Valid @RequestBody CrearEstudianteRequest req) {
        var est = Estudiante.builder()
                .codigo(req.codigo())
                .nombre(req.nombre())
                .correo(req.correo())
                .programa(req.programa())
                .build();
        return ResponseEntity.ok(useCase.crear(est));
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(useCase.listar(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.obtener(id));
    }

    public record CrearEstudianteRequest(
            @NotBlank String codigo,
            @NotBlank String nombre,
            @NotBlank String correo,
            @NotBlank String programa
    ) {}
}
