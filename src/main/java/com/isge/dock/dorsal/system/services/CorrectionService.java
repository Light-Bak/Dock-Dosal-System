package com.isge.dock.dorsal.system.services;

import com.isge.dock.dorsal.system.dto.CorrectionDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CorrectionService {

    List<CorrectionDTO> find();

    CorrectionDTO find(BigDecimal ref);
    List<CorrectionDTO> findAll(Byte userId);

    List<CorrectionDTO> find(LocalDate dateDebut, LocalDate dateFin);

    CorrectionDTO create(CorrectionDTO correctionEntity);

    void update(CorrectionDTO correctionDTO);

    void delete(BigDecimal ref);
}
