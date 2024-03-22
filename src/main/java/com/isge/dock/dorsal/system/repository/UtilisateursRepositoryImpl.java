package com.isge.dock.dorsal.system.repository;

import com.isge.dock.dorsal.system.domain.UtilisateursEntity;
import com.isge.dock.dorsal.system.repository.jpa.UtilisateursJpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class UtilisateursRepositoryImpl implements UtilisateursRepository {
    private final UtilisateursJpaRepository UtilisateursJpaRepository;
    private final UtilisateursJpaRepository utilisateursJpaRepository;

    public UtilisateursRepositoryImpl( UtilisateursJpaRepository utilisateursJpaRepository,UtilisateursJpaRepository UtilisateursJpaRepository) {
        this.UtilisateursJpaRepository = UtilisateursJpaRepository;
        this.utilisateursJpaRepository = utilisateursJpaRepository;
    }

    @Override
    public List<UtilisateursEntity> find() {
        return UtilisateursJpaRepository.findAll();
    }

    @Override
    public UtilisateursEntity find(BigDecimal ref) {
        return UtilisateursJpaRepository.findByref(ref).orElse(null);
    }

    @Override
    public UtilisateursEntity findByUsernameAndPassword(String username, String password) {
        UtilisateursEntity utilisateur = utilisateursJpaRepository.findByUsernameAndPassword(username, password);
        return utilisateur;
    }


    @Override
    public UtilisateursEntity save(UtilisateursEntity utilisateurEntity) {
        UtilisateursJpaRepository.save(utilisateurEntity);
        return utilisateurEntity;
    }

    @Override
    public void delete(BigDecimal ref) {
        UtilisateursJpaRepository.deleteByref(ref);
    }

    @Override
    public void update(UtilisateursEntity utilisateursEntity) {
        UtilisateursJpaRepository.updateUtilisateurs(
                utilisateursEntity.getRef(),
                utilisateursEntity.getNom(),
                utilisateursEntity.getEmail(),
                utilisateursEntity.getPrenom(),
                utilisateursEntity.getUsername(),
                utilisateursEntity.getPassword()
        );
    }
}
