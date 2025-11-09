package co.com.ucn.practicas.domain.usecase;

import co.com.ucn.practicas.domain.model.entity.Vacante;
import co.com.ucn.practicas.domain.usecase.ports.VacanteRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class VacanteUseCaseTest {
    private VacanteRepositoryPort repository;
    private VacanteUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(VacanteRepositoryPort.class);
        useCase = new VacanteUseCase(repository);
    }

    @Test
    void crear_vacante_valida() {
        Vacante vacante = new Vacante(1L, 1L, "Desarrollador", "Desarrollar apps", "ACTIVA", LocalDate.now());
        Mockito.when(repository.save(any(Vacante.class))).thenReturn(vacante);
        Vacante result = useCase.crear(vacante);
        assertEquals(vacante, result);
    }

    @Test
    void crear_vacante_campos_nulos_o_vacios() {
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(null));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Vacante(1L, null, "Desarrollador", "Desarrollar apps", "ACTIVA", LocalDate.now())));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Vacante(1L, 1L, null, "Desarrollar apps", "ACTIVA", LocalDate.now())));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Vacante(1L, 1L, "", "Desarrollar apps", "ACTIVA", LocalDate.now())));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Vacante(1L, 1L, "Desarrollador", null, "ACTIVA", LocalDate.now())));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Vacante(1L, 1L, "Desarrollador", "", "ACTIVA", LocalDate.now())));
    }

    @Test
    void obtener_vacante_existente() {
        Vacante vacante = new Vacante(1L, 1L, "Desarrollador", "Desarrollar apps", "ACTIVA", LocalDate.now());
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(vacante));
        Vacante result = useCase.obtener(1L);
        assertEquals(vacante, result);
    }

    @Test
    void obtener_vacante_id_invalido() {
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(null));
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(0L));
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(-1L));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.obtener(2L));
        assertEquals("Vacante no encontrada", ex.getMessage());
    }

}
