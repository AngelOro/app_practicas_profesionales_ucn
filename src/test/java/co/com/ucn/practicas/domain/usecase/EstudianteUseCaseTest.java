package co.com.ucn.practicas.domain.usecase;

import co.com.ucn.practicas.domain.model.entity.Estudiante;
import co.com.ucn.practicas.domain.usecase.ports.EstudianteRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class EstudianteUseCaseTest {
    private EstudianteRepositoryPort repository;
    private EstudianteUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(EstudianteRepositoryPort.class);
        useCase = new EstudianteUseCase(repository);
    }

    @Test
    void crear_estudiante_valido() {
        Estudiante estudiante = new Estudiante(1L, "20210001", "Juan Perez", "juan@ucn.cl", "Ingeniería", true);
        Mockito.when(repository.findByCodigo("20210001")).thenReturn(Optional.empty());
        Mockito.when(repository.save(any(Estudiante.class))).thenReturn(estudiante);
        Estudiante result = useCase.crear(estudiante);
        assertEquals(estudiante, result);
    }

    @Test
    void crear_estudiante_codigo_duplicado() {
        Estudiante estudiante = new Estudiante(1L, "20210001", "Juan Perez", "juan@ucn.cl", "Ingeniería", true);
        Mockito.when(repository.findByCodigo("20210001")).thenReturn(Optional.of(estudiante));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.crear(estudiante));
        assertEquals("Código de estudiante ya registrado", ex.getMessage());
    }

    @Test
    void crear_estudiante_campos_nulos_o_vacios() {
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(null));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Estudiante(1L, null, "Juan Perez", "juan@ucn.cl", "Ingeniería", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Estudiante(1L, "", "Juan Perez", "juan@ucn.cl", "Ingeniería", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Estudiante(1L, "20210001", null, "juan@ucn.cl", "Ingeniería", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Estudiante(1L, "20210001", "", "juan@ucn.cl", "Ingeniería", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Estudiante(1L, "20210001", "Juan Perez", null, "Ingeniería", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Estudiante(1L, "20210001", "Juan Perez", "", "Ingeniería", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Estudiante(1L, "20210001", "Juan Perez", "juan@ucn.cl", null, true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Estudiante(1L, "20210001", "Juan Perez", "juan@ucn.cl", "", true)));
    }

    @Test
    void obtener_estudiante_existente() {
        Estudiante estudiante = new Estudiante(1L, "20210001", "Juan Perez", "juan@ucn.cl", "Ingeniería", true);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(estudiante));
        Estudiante result = useCase.obtener(1L);
        assertEquals(estudiante, result);
    }

    @Test
    void obtener_estudiante_id_invalido() {
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(null));
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(0L));
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(-1L));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.obtener(2L));
        assertEquals("Estudiante no encontrado", ex.getMessage());
    }

    @Test
    void listar_estudiantes_paginacion_valida() {
        List<Estudiante> estudiantes = List.of(new Estudiante(1L, "20210001", "Juan Perez", "juan@ucn.cl", "Ingeniería", true));
        Mockito.when(repository.list(0, 10)).thenReturn(estudiantes);
        List<Estudiante> result = useCase.listar(0, 10);
        assertEquals(estudiantes, result);
    }

    @Test
    void listar_estudiantes_paginacion_invalida() {
        assertThrows(IllegalArgumentException.class, () -> useCase.listar(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> useCase.listar(0, 0));
        assertThrows(IllegalArgumentException.class, () -> useCase.listar(0, -5));
    }
}
