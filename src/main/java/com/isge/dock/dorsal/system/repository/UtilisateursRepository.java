package com.isge.dock.dorsal.system.repository;

import com.isge.dock.dorsal.system.domain.UtilisateursEntity;
import com.isge.dock.dorsal.system.dto.UtilisateursDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UtilisateursRepository {
    List<UtilisateursEntity> find();

    UtilisateursEntity find(BigDecimal ref);
    UtilisateursEntity findByUsernameAndPassword(String username, String password);
    UtilisateursEntity save(UtilisateursEntity utilisateursEntity);

    void delete(BigDecimal ref);

    void update(UtilisateursEntity utilisateursEntity);

}
