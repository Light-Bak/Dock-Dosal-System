package com.isge.dock.dorsal.system.utils;
import com.isge.dock.dorsal.system.domain.CorrectionEntity;
import com.isge.dock.dorsal.system.domain.UtilisateursEntity;
import com.isge.dock.dorsal.system.dto.CorrectionDTO;
import com.isge.dock.dorsal.system.dto.UtilisateursDTO;
import java.util.ArrayList;
import java.util.List;

public class UtilisateursMapper {

    private UtilisateursMapper() {
    }

    public static UtilisateursDTO fromEntity(UtilisateursEntity utilisateursEntity) {
        if (null == utilisateursEntity) {
            return null;
        }
        UtilisateursDTO utilisateursDTO = new UtilisateursDTO();
        utilisateursDTO.setRef(utilisateursEntity.getRef());
        utilisateursDTO.setId(utilisateursEntity.getId());
        utilisateursDTO.setNom(utilisateursEntity.getNom());
        utilisateursDTO.setPrenom(utilisateursEntity.getPrenom());
        utilisateursDTO.setEmail(utilisateursEntity.getEmail());
        utilisateursDTO.setUsername(utilisateursEntity.getUsername());
        utilisateursDTO.setPassword(utilisateursEntity.getPassword());
        utilisateursDTO.setRole(utilisateursEntity.getRole());
        utilisateursDTO.setCorrection(fromCorrectionEntities(utilisateursEntity.getCorrection()));
        return utilisateursDTO;
    }

    public static UtilisateursEntity fromDTO(UtilisateursDTO utilisateursDTO) {
        if (null == utilisateursDTO) {
            return null;
        }
        UtilisateursEntity utilisateursEntity = new UtilisateursEntity();
        utilisateursEntity.setRef(utilisateursDTO.getRef());
        utilisateursEntity.setNom(utilisateursDTO.getNom());
        utilisateursEntity.setPrenom(utilisateursDTO.getPrenom());
        utilisateursEntity.setEmail(utilisateursDTO.getEmail());
        utilisateursEntity.setUsername(utilisateursDTO.getUsername());
        utilisateursEntity.setPassword(utilisateursDTO.getPassword());
        utilisateursEntity.setRole(utilisateursDTO.getRole());
        utilisateursEntity.setCorrection(fromCorrectionDTOs(utilisateursDTO.getCorrection(), utilisateursEntity));
        return utilisateursEntity;
    }

    public static List<UtilisateursDTO> fromEntities(List<UtilisateursEntity> entities) {
        if (null == entities || entities.isEmpty()) {
            return List.of();
        }
        List<UtilisateursDTO> utilisateursDTOS = new ArrayList<>();
        for (UtilisateursEntity entity : entities) {
            utilisateursDTOS.add(fromEntity(entity));
        }
        return utilisateursDTOS;
    }


    private static List<CorrectionDTO> fromCorrectionEntities(List<CorrectionEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        CorrectionDTO correctionDTO;
        List<CorrectionDTO> authorDTOS = new ArrayList<>();
        for (CorrectionEntity entity : entities) {
            correctionDTO = new CorrectionDTO();
            correctionDTO.setValeur(entity.getValeur());
            correctionDTO.setRef(entity.getRef());
            correctionDTO.setCreateDate(entity.getCreateDate());
            correctionDTO.setUpdateDate(entity.getUpdateDate());
            authorDTOS.add(correctionDTO);
        }
        return authorDTOS;
    }

    private static List<CorrectionEntity> fromCorrectionDTOs(List<CorrectionDTO> correctionDTOS, UtilisateursEntity utilisateursEntity) {
        if (null == correctionDTOS || correctionDTOS.isEmpty()) {
            return List.of();
        }
        List<CorrectionEntity> entities = new ArrayList<>();
        CorrectionEntity entity;
        for (CorrectionDTO dto : correctionDTOS) {
            entity = new CorrectionEntity();
            entity.setValeur(dto.getValeur());
            entity.setCreateDate(dto.getCreateDate());
            entity.setUpdateDate(dto.getUpdateDate());
            entity.setRef(GenerateReferenceUtil.generateRandomBigDecimal());
            entities.add(entity);
        }
        return entities;
    }

}
