package co.com.ucn.practicas.domain.usecase;

import co.com.ucn.practicas.domain.model.entity.Empresa;
import co.com.ucn.practicas.domain.usecase.ports.EmpresaRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class EmpresaUseCaseTest {
    private EmpresaRepositoryPort repository;
    private EmpresaUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(EmpresaRepositoryPort.class);
        useCase = new EmpresaUseCase(repository);
    }

    @Test
    void crear_empresa_valida() {
        Empresa empresa = new Empresa(1L, "UCN", "123", "ucn@ucn.cl", "312121", true);
        Mockito.when(repository.findByNit("123")).thenReturn(Optional.empty());
        Mockito.when(repository.save(any(Empresa.class))).thenReturn(empresa);
        Empresa result = useCase.crear(empresa);
        assertEquals(empresa, result);
    }

    @Test
    void crear_empresa_nit_duplicado() {
        Empresa empresa = new Empresa(1L, "UCN", "123", "ucn@ucn.cl", "312121", true);
        Mockito.when(repository.findByNit("123")).thenReturn(Optional.of(empresa));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.crear(empresa));
        assertEquals("NIT ya registrado", ex.getMessage());
    }

    @Test
    void crear_empresa_campos_nulos_o_vacios() {
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(null));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Empresa(1L, null, "123", "ucn@ucn.cl", "312121", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Empresa(1L, "", "123", "ucn@ucn.cl", "312121", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Empresa(1L, "UCN", null, "ucn@ucn.cl", "312121", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Empresa(1L, "UCN", "", "ucn@ucn.cl", "312121", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Empresa(1L, "UCN", "123", null, "312121", true)));
        assertThrows(IllegalArgumentException.class, () -> useCase.crear(new Empresa(1L, "UCN", "123", "", "312121", true)));
    }

    @Test
    void obtener_empresa_existente() {
        Empresa empresa = new Empresa(1L, "UCN", "123", "ucn@ucn.cl", "312121", true);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(empresa));
        Empresa result = useCase.obtener(1L);
        assertEquals(empresa, result);
    }

    @Test
    void obtener_empresa_id_invalido() {
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(null));
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(0L));
        assertThrows(IllegalArgumentException.class, () -> useCase.obtener(-1L));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.obtener(2L));
        assertEquals("Empresa no encontrada", ex.getMessage());
    }

    @Test
    void listar_empresas_paginacion_valida() {
        List<Empresa> empresas = List.of(new Empresa(1L, "UCN", "123", "ucn@ucn.cl", "312121", true));
        Mockito.when(repository.list(0, 10)).thenReturn(empresas);
        List<Empresa> result = useCase.listar(0, 10);
        assertEquals(empresas, result);
    }

    @Test
    void listar_empresas_paginacion_invalida() {
        assertThrows(IllegalArgumentException.class, () -> useCase.listar(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> useCase.listar(0, 0));
        assertThrows(IllegalArgumentException.class, () -> useCase.listar(0, -5));
    }

    @Test
    void desactivar_empresa_existente() {
        Empresa empresa = new Empresa(1L, "UCN", "123", "ucn@ucn.cl", "312121", true);
        Mockito.when(repository.desactivar(1L)).thenReturn(empresa);
        Empresa result = useCase.desactivar(1L);
        assertEquals(empresa, result);
    }

    @Test
    void desactivar_empresa_id_invalido() {
        assertThrows(IllegalArgumentException.class, () -> useCase.desactivar(null));
        assertThrows(IllegalArgumentException.class, () -> useCase.desactivar(0L));
        assertThrows(IllegalArgumentException.class, () -> useCase.desactivar(-1L));
    }
}

