package co.com.ucn.practicas.infraestructure.entrypoints;

import co.com.ucn.practicas.domain.model.entity.Empresa;
import co.com.ucn.practicas.domain.usecase.EmpresaUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresasController {

    private final EmpresaUseCase useCase;

    public EmpresasController(EmpresaUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Empresa> crear(@Valid @RequestBody CrearEmpresaRequest req) {
        var empresa = Empresa.builder()
                .nombre(req.nombre())
                .nit(req.nit())
                .correo(req.correo())
                .telefono(req.telefono())
                .build();
        return ResponseEntity.ok(useCase.crear(empresa));
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(useCase.listar(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.obtener(id));
    }

    public record CrearEmpresaRequest(
            @NotBlank String nombre,
            @NotBlank String nit,
            @NotBlank String correo,
            @NotBlank String telefono
    ) {}
}
