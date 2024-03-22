package com.isge.dock.dorsal.system.repository;

import com.isge.dock.dorsal.system.domain.CorrectionEntity;
import com.isge.dock.dorsal.system.dto.CorrectionDTO;
import com.isge.dock.dorsal.system.repository.jpa.CorrectionJpaRepository;
import com.isge.dock.dorsal.system.utils.CorrectionMapper;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.isge.dock.dorsal.system.utils.CorrectionMapper.fromEntity;

@Repository
public class CorrectionRepositoryImpl implements CorrectionRepository {

    private final CorrectionJpaRepository correctionJpaRepository;

    public CorrectionRepositoryImpl(CorrectionJpaRepository correctionJpaRepository) {
        this.correctionJpaRepository = correctionJpaRepository;
    }

    @Override
    public List<CorrectionEntity> find() {
        return correctionJpaRepository.findAll();
    }

    @Override
    public List<CorrectionEntity> findAll(BigDecimal userId) {
        return null;
    }

    @Override
    public CorrectionEntity find(BigDecimal ref) {
        Optional<CorrectionEntity> bookEntityOpt = correctionJpaRepository.findByRef(ref);
        return bookEntityOpt.orElse(null);
    }
    @Override
    public List<CorrectionEntity> findByCreateDateBetween(LocalDate DateDebut, LocalDate DateFin) {
        Optional<List<CorrectionEntity>> optionalCorrections = correctionJpaRepository.findByCreateDateBetween(DateDebut, DateFin);
        return optionalCorrections.orElse(Collections.emptyList());
    }

    @Override
    public CorrectionEntity findByUserIdAndCreateDate(Byte userId, LocalDate createDate) {
        return correctionJpaRepository.findByUserIdAndCreateDate(userId, createDate).orElse(null);
    }

    @Override
    public List<CorrectionEntity> findByUserId(Byte userId) {
        return correctionJpaRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public CorrectionEntity save(CorrectionEntity correctionEntity) {
        correctionJpaRepository.save(correctionEntity);
        return correctionEntity;
    }


    @Override
    public void delete(BigDecimal ref) {
        correctionJpaRepository.deleteByRef(ref);
    }

}
