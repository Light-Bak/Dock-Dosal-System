package com.isge.dock.dorsal.system.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "correction")
public class CorrectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte valeur;
    @Column(updatable = false)
    private BigDecimal ref;
    private Byte userId;
    @Column(name = "createDate", updatable = false)
    private LocalDate createDate;
    @Column(name = "updateDate")
    private LocalDate updateDate;
}
