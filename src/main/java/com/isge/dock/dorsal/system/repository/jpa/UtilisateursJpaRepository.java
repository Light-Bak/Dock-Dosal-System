package com.isge.dock.dorsal.system.repository.jpa;

import com.isge.dock.dorsal.system.domain.UtilisateursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface UtilisateursJpaRepository extends JpaRepository<UtilisateursEntity, Long> {

    @Transactional(readOnly = true)
    Optional<UtilisateursEntity> findByref(BigDecimal ref);

    UtilisateursEntity findByUsernameAndPassword(String username, String password);

    @Transactional()
    @Modifying
    @Query(value = "update UtilisateursEntity utilisateurs set utilisateurs.ref = :ref, utilisateurs.nom = :nom, utilisateurs.prenom = :prenom , utilisateurs.username = :username , utilisateurs.password = :password, utilisateurs.email = :email where utilisateurs.ref = :ref")
    void updateUtilisateurs(BigDecimal ref, String nom, String prenom, String email, String username , String password);

    @Transactional()
    @Modifying
    void deleteByref(BigDecimal ref);
}
