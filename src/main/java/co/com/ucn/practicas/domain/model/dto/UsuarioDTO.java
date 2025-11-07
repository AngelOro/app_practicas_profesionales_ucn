package co.com.ucn.practicas.domain.model.dto;


import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String email;
    private String passwordHash;
    private String rol;
}
