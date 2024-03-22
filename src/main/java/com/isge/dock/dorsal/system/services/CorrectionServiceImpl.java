package com.isge.dock.dorsal.system.services;

import com.isge.dock.dorsal.system.domain.CorrectionEntity;
import com.isge.dock.dorsal.system.dto.CorrectionDTO;
import com.isge.dock.dorsal.system.repository.CorrectionRepository;
import com.isge.dock.dorsal.system.utils.AfficherMessage;
import com.isge.dock.dorsal.system.utils.CorrectionMapper;
import com.isge.dock.dorsal.system.utils.GenerateReferenceUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorrectionServiceImpl implements CorrectionService {

    private final CorrectionRepository correctionRepository;

    public CorrectionServiceImpl(CorrectionRepository correctionRepository) {
        this.correctionRepository = correctionRepository;
    }

    @Override
    public List<CorrectionDTO> find() {
        List<CorrectionEntity> entities = correctionRepository.find();
        if (entities.isEmpty()) {
            throw new AfficherMessage("Auccune correction trouvé.");
        }
        return mapEntitiesToDTO(entities);
    }

    @Override
    public List<CorrectionDTO> findAll(Byte userId) {
        List<CorrectionEntity> entities = correctionRepository.findByUserId(userId);
        if (entities.isEmpty()) {
            throw new AfficherMessage("Auccune correction trouvé.");
        }
        return mapEntitiesToDTO(entities);
    }

    @Override
    public CorrectionDTO find(BigDecimal ref) {
        CorrectionEntity correctionEntity = correctionRepository.find(ref);
        if (correctionEntity == null) {
            System.err.println("La correction spécifier n'existe pas");
            throw new AfficherMessage("Une erreur est survenue. Veuillez réessayer plus tard.");
        }
        return CorrectionMapper.fromEntity(correctionEntity);
    }

    @Override
    public List<CorrectionDTO> find(LocalDate dateDebut, LocalDate dateFin) {
        List<CorrectionEntity> correctionEntities = correctionRepository.findByCreateDateBetween(dateDebut, dateFin);

        if (!correctionEntities.isEmpty()) {
            return correctionEntities.stream()
                    .map(CorrectionMapper::fromEntity)
                    .collect(Collectors.toList());
        }else {
            throw new AfficherMessage("Auccune correction dans cette intervalle.");
        }
    }

    @Override
    public CorrectionDTO create(CorrectionDTO correctionDTO) {
        // Vérifier si une correction avec le même userId et createDate existe déjà
        CorrectionEntity existingCorrection = correctionRepository.findByUserIdAndCreateDate(correctionDTO.getUserId(), LocalDate.now());
        if (existingCorrection != null) {
            throw new AfficherMessage("Vous n'avez pas le droit d'ajouter plus d'une correction par jour. Supprimer pour pouvoir ajouter ou bien modifier celui que vous avez ajouter.");
        } else {
            CorrectionEntity newCorrectionEntity = CorrectionMapper.fromDTO(correctionDTO);
            newCorrectionEntity.setCreateDate(LocalDate.now());
            newCorrectionEntity.setRef(GenerateReferenceUtil.generateRandomBigDecimal());
            newCorrectionEntity = correctionRepository.save(newCorrectionEntity);
            return CorrectionMapper.fromEntity(newCorrectionEntity);
        }
    }




    @Override
    public void update(CorrectionDTO correctionDTO) {
        CorrectionEntity entity = correctionRepository.find(correctionDTO.getRef());
        if (entity == null) {
            return;
        }
        entity.setValeur(correctionDTO.getValeur());
        entity.setUpdateDate(LocalDate.now());
        correctionRepository.save(entity);
    }

    public void delete(BigDecimal ref) {
        try {
            CorrectionEntity entity = correctionRepository.find(ref);
            if (entity != null) {
                correctionRepository.delete(ref);
            } else {
                System.err.println("L'utilisateur avec la référence '" + ref + "' n'existe pas.");
                throw new AfficherMessage("Une erreur est survenue lors de la suppression de la correction. Veuillez réessayer plus tard.");
            }
        } catch (Exception ex) {
            System.err.println("Erreur lors de la suppression de l'auteur : " + ex.getMessage());
            throw new AfficherMessage("Une erreur est survenue lors de la suppression de la correction. Veuillez réessayer plus tard.");
        }
    }

    private List<CorrectionDTO> mapEntitiesToDTO(List<CorrectionEntity> entities) {
        if (entities.isEmpty()) {
            return List.of();
        }
        CorrectionDTO dto;
        List<CorrectionDTO> results = new ArrayList<>();
        for (CorrectionEntity entity : entities) {
            dto = CorrectionMapper.fromEntity(entity);
            results.add(dto);
        }
        return results;
    }

}
