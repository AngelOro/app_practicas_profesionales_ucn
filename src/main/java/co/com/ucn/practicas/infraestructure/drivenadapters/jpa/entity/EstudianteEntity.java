package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estudiante")
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @Column(length = 50, nullable = false, unique = true)
    private String codigo;

    @Column(length = 120, nullable = false)
    private String nombre;

    @Column(length = 120, nullable = false)
    private String correo;

    @Column(length = 120, nullable = false)
    private String programa;

    @Column(nullable = false)
    private Boolean activo = true;
}
