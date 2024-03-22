package com.isge.dock.dorsal.system.dto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class UtilisateursDTO {
    private BigDecimal ref; // Au lieu d'envoyer l'id on va générer un user-reference
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;
    private String role;
    private List<CorrectionDTO> correction;

}
