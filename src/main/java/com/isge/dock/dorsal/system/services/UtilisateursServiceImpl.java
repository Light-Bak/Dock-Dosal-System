package com.isge.dock.dorsal.system.services;

import com.isge.dock.dorsal.system.domain.UtilisateursEntity;
import com.isge.dock.dorsal.system.dto.UtilisateursDTO;
import com.isge.dock.dorsal.system.repository.UtilisateursRepository;
import com.isge.dock.dorsal.system.repository.jpa.UtilisateursJpaRepository;
import com.isge.dock.dorsal.system.utils.UtilisateursMapper;
import com.isge.dock.dorsal.system.utils.GenerateReferenceUtil;
import com.isge.dock.dorsal.system.utils.AfficherMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UtilisateursServiceImpl implements UtilisateursService {


    private final BCryptPasswordEncoder passwordEncoder;
    private final UtilisateursRepository utilisateursRepository;
    private final UtilisateursJpaRepository utilisateursJpaRepository;

    public UtilisateursServiceImpl( BCryptPasswordEncoder PasswordEncoder,
                                   UtilisateursRepository UtilisateursRepository,
                                   UtilisateursJpaRepository UtilisateursJpaRepository) {
        this.utilisateursRepository = UtilisateursRepository;
        this.utilisateursJpaRepository = UtilisateursJpaRepository;
        this.passwordEncoder = PasswordEncoder;
    }

    @Override
    public List<UtilisateursDTO> find() throws Exception {
            List<UtilisateursEntity> utilisateursEntities = utilisateursRepository.find();
            if (utilisateursEntities.isEmpty()) {
                System.err.println("Aucun utilisateur dans la BD");
                throw new AfficherMessage("Une erreur est survenue. Veuillez réessayer plus tard.");
            }
            return UtilisateursMapper.fromEntities(utilisateursEntities);
    }

    @Override
    public UtilisateursDTO find(BigDecimal ref) {
            UtilisateursEntity utilisateursEntity = utilisateursRepository.find(ref);
            if (utilisateursEntity == null) {
                System.err.println("L'utilisateur spécifié est n'exite pas");
                throw new AfficherMessage("Une erreur est survenue. Veuillez réessayer plus tard.");
            }
            return UtilisateursMapper.fromEntity(utilisateursEntity);
    }

    @Override
    public UtilisateursDTO findByUsernameAndPassword(String username, String password) {
        UtilisateursEntity utilisateur = utilisateursJpaRepository.findByUsernameAndPassword(username, password);
        if (utilisateur == null) {
            System.err.println("Utilisateur non trouvé pour les informations d'identification fournies");
            throw new AfficherMessage("Identifiants invalides. Veuillez réessayer.");
        }
        return UtilisateursMapper.fromEntity(utilisateur);
    }


    @Override
    public UtilisateursDTO save(UtilisateursDTO utilisateursDTO) throws RuntimeException {
        UtilisateursEntity utilisateursEntity = UtilisateursMapper.fromDTO(utilisateursDTO);
        utilisateursEntity.setRole("USER"); // Toute inscription prend le rôle USER
        utilisateursEntity.setRef(GenerateReferenceUtil.generateRandomBigDecimal());
        String password = utilisateursDTO.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        utilisateursEntity.setPassword(hashedPassword);

        try {
            utilisateursJpaRepository.save(utilisateursEntity);
            return UtilisateursMapper.fromEntity(utilisateursEntity);
        } catch (Exception ex) {
            System.err.println("Erreur lors de l'enregistrement de l'utilisateur : " + ex.getMessage());
            throw new AfficherMessage("Une erreur est survenue lors de l'enregistrement de l'utilisateur. Veuillez réessayer plus tard.");
        }
    }


    @Override
    public void delete(BigDecimal ref) {
        try {
            UtilisateursEntity entity = utilisateursRepository.find(ref);
            if (entity != null) {
                utilisateursRepository.delete(ref);
            } else {
                System.err.println("L'utilisateur avec la référence '" + ref + "' n'existe pas.");
                throw new AfficherMessage("Une erreur est survenue lors de la suppression de l'Utilisateur. Veuillez réessayer plus tard.");
            }
        } catch (Exception ex) {
            System.err.println("Erreur lors de la suppression de l'auteur : " + ex.getMessage());
            throw new AfficherMessage("Une erreur est survenue lors de la suppression de l'Utilisateur. Veuillez réessayer plus tard.");
        }
    }

    @Override
    public void update(UtilisateursDTO utilisateursDTO) {
        try {
            UtilisateursEntity utilisateursFromDB = utilisateursRepository.find(utilisateursDTO.getRef());
            if (null == utilisateursFromDB) {
                return;
            }
            utilisateursFromDB.setNom(utilisateursDTO.getNom());
            utilisateursFromDB.setPrenom(utilisateursDTO.getPrenom());
            utilisateursFromDB.setEmail(utilisateursDTO.getEmail());
            utilisateursFromDB.setUsername(utilisateursDTO.getUsername());
            utilisateursFromDB.setPassword(utilisateursDTO.getPassword());
            utilisateursRepository.save(utilisateursFromDB);
        } catch (Exception ex) {
            System.err.println("Erreur lors de la mise à jour de l'utilisateur : " + ex.getMessage());
            throw new AfficherMessage("Une erreur est survenue lors de la mise à jour de l'utilisateur. Veuillez réessayer plus tard.");
        }
    }
}
