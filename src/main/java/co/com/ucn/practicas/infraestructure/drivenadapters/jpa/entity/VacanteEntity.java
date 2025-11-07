package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "vacante")
public class VacanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacante")
    private Long idVacante;

    @Column(name = "id_empresa", nullable = false)
    private Long idEmpresa;

    @Column(length = 150, nullable = false)
    private String titulo;

    @Column(columnDefinition = "text")
    private String descripcion;

    @Column(length = 15, nullable = false)
    private String estado; // ABIERTA/CERRADA

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;
}
