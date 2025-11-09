package co.com.ucn.practicas.infraestructure.drivenadapters.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 30, nullable = false, unique = true)
    private String nit;

    @Column(length = 120, nullable = false)
    private String correo;

    @Column(length = 30, nullable = false)
    private String telefono;

    @Column(nullable = false)
    private Boolean activo = true;
}
