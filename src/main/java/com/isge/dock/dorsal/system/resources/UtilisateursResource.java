package com.isge.dock.dorsal.system.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isge.dock.dorsal.system.dto.UtilisateursDTO;
import com.isge.dock.dorsal.system.services.UtilisateursService;
import com.isge.dock.dorsal.system.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/utilisateurs")
public class UtilisateursResource {
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;
    private final UtilisateursService utilisateursService;
    public UtilisateursResource(UtilisateursService utilisateursService) {
        this.utilisateursService = utilisateursService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UtilisateursDTO>> find() throws Exception {
        return ResponseEntity.ok(utilisateursService.find());
    }

    @GetMapping("/findOne/{ref}")
    public ResponseEntity<UtilisateursDTO> find(@PathVariable BigDecimal ref) {
        return ResponseEntity.ok(utilisateursService.find(ref));
    }

    @PostMapping("/inscription")
    public ResponseEntity<UtilisateursDTO> create(@RequestBody UtilisateursDTO utilisateursDTO) {
        return new ResponseEntity<>(utilisateursService.save(utilisateursDTO), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UtilisateursDTO utilisateursDTO) {
        UtilisateursDTO utilisateur = utilisateursService.findByUsernameAndPassword(utilisateursDTO.getUsername(), utilisateursDTO.getPassword());

        if (utilisateur != null) {
            String token = jwtToken.generateJwtToken(utilisateur.getUsername()); // Utiliser le nom d'utilisateur comme sujet du token JWT
            Map<String, Object> response = new HashMap<>();
            response.put("username", utilisateur.getUsername());
            response.put("userid", utilisateur.getId());
            response.put("ref", utilisateur.getRef());
            response.put("nom", utilisateur.getNom());
            response.put("prenom", utilisateur.getPrenom());
            response.put("enmail", utilisateur.getEmail());
            response.put("role", utilisateur.getRole());
            response.put("token", token);

            // Convertir l'objet en chaîne JSON
            String jsonResponse = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                jsonResponse = mapper.writeValueAsString(response);
            } catch (Exception e) {
                e.printStackTrace(); // Gérer l'exception si nécessaire
            }
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides.");
        }
    }


    @DeleteMapping("/supprimer/{ref}")
    public ResponseEntity<HttpStatus> delete(@PathVariable BigDecimal ref) {
        utilisateursService.delete(ref);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/modifier/{ref}")
    public ResponseEntity<HttpStatus> update(@RequestBody UtilisateursDTO utilisateursDTO, @PathVariable BigDecimal ref) {
        if (!(ref.equals(utilisateursDTO.getRef()))) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        utilisateursService.update(utilisateursDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
