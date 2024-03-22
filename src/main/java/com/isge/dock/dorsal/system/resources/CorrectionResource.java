package com.isge.dock.dorsal.system.resources;

import com.isge.dock.dorsal.system.dto.CorrectionDTO;
import com.isge.dock.dorsal.system.services.CorrectionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = "/correction")
public class CorrectionResource {

    private final CorrectionService correctionService;

    public CorrectionResource(CorrectionService correctionService) {

        this.correctionService = correctionService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<CorrectionDTO> create(@RequestBody CorrectionDTO correctionDTO) {
        CorrectionDTO result = correctionService.create(correctionDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/findOne/{ref}")
    public ResponseEntity<CorrectionDTO> find(@PathVariable(name = "ref") BigDecimal ref) {
        return new ResponseEntity<>(correctionService.find(ref), HttpStatus.OK);
    }

    @GetMapping("/findAll/{userId}")
    public ResponseEntity<List<CorrectionDTO>> findAll(@PathVariable(name = "userId") Byte userId) {
        return new ResponseEntity<>(correctionService.findAll(userId), HttpStatus.OK);
    }

     @GetMapping("/searchIntervalDate/{dateDebut},{dateFin}")
        public ResponseEntity<List<CorrectionDTO>> find(
                @PathVariable("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
                @PathVariable("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
            List<CorrectionDTO> corrections = correctionService.find(dateDebut, dateFin);
            return new ResponseEntity<>(corrections, HttpStatus.OK);
        }


    @PutMapping("/modifier/{ref}")
    public ResponseEntity<HttpStatus> update(@RequestBody CorrectionDTO correctionDTO, @PathVariable BigDecimal ref) {
        if (!(ref.equals(correctionDTO.getRef()))) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        correctionService.update(correctionDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/supprimer/{ref}")
    public ResponseEntity<HttpStatus> delete(@PathVariable BigDecimal ref) {
        correctionService.delete(ref);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
