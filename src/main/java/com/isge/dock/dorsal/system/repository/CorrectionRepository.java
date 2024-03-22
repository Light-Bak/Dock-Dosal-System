package com.isge.dock.dorsal.system.repository;

import com.isge.dock.dorsal.system.domain.CorrectionEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

public interface CorrectionRepository {

    List<CorrectionEntity> find();
    List<CorrectionEntity> findAll(BigDecimal userId);
    CorrectionEntity find(BigDecimal ref);

    List<CorrectionEntity> findByCreateDateBetween(LocalDate DateDebut, LocalDate DateFin);

    CorrectionEntity findByUserIdAndCreateDate(Byte userId, LocalDate createDate);
    List<CorrectionEntity> findByUserId(Byte userId);
    CorrectionEntity save(CorrectionEntity correctionEntity);

    void delete(BigDecimal ref);
}
