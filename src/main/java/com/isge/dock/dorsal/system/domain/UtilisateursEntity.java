package com.isge.dock.dorsal.system.domain;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "utilisateurs")
public class UtilisateursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private BigDecimal ref; // Au lieu d'envoyer l'id on va générer un user-reference
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "userId", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    List<CorrectionEntity> correction;

}
