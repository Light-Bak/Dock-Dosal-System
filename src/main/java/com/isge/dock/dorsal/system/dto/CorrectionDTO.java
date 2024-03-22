package com.isge.dock.dorsal.system.dto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CorrectionDTO {
    private Byte valeur;
    private BigDecimal ref;
    private Byte userId;
    private LocalDate createDate;
    private LocalDate updateDate;
}
