package com.isge.dock.dorsal.system.services;

import com.isge.dock.dorsal.system.dto.UtilisateursDTO;

import java.math.BigDecimal;
import java.util.List;

public interface UtilisateursService {

    List<UtilisateursDTO> find() throws Exception;

    UtilisateursDTO find(BigDecimal ref);
    UtilisateursDTO findByUsernameAndPassword(String Username, String Password);

    UtilisateursDTO save(UtilisateursDTO utilisateursDTO);

    void delete(BigDecimal ref);

    void update(UtilisateursDTO utilisateursDTO);
}
