package com.isge.dock.dorsal.system.utils;
import com.isge.dock.dorsal.system.domain.CorrectionEntity;
import com.isge.dock.dorsal.system.dto.CorrectionDTO;


public class CorrectionMapper {
    private CorrectionMapper() {
    }

    /**
     *
     * @param entity objet de type CorrectionEntity
     * @return CorrectionDTO
     */
    public static CorrectionDTO fromEntity(CorrectionEntity entity) {
        if (null == entity) {
            return null;
        }

        CorrectionDTO correctionDTO = new CorrectionDTO();
        correctionDTO.setValeur(entity.getValeur());
        correctionDTO.setRef(entity.getRef());
        correctionDTO.setUserId(entity.getUserId());
        correctionDTO.setCreateDate(entity.getCreateDate());
        correctionDTO.setUpdateDate(entity.getUpdateDate());
        return correctionDTO;
    }

    /**
     *
     * @param correctionDTO objet de type CorrectionDTO
     * @return CorrectionEntity
     */
    public static CorrectionEntity fromDTO(CorrectionDTO correctionDTO) {
        if (null == correctionDTO) {
            return null;
        }

        CorrectionEntity correctionEntity = new CorrectionEntity();
        correctionEntity.setValeur(correctionDTO.getValeur());
        correctionEntity.setRef(correctionDTO.getRef());
        correctionEntity.setUserId(correctionDTO.getUserId());
        return correctionEntity;
    }
}
