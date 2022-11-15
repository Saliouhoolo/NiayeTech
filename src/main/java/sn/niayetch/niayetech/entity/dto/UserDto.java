package sn.niayetch.niayetech.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private String role;

    public UserDto(Long id, String prenom, String nom, String email, String role) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.role = role;
    }
}
