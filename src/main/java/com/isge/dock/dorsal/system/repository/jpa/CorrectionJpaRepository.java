package com.isge.dock.dorsal.system.repository.jpa;

import com.isge.dock.dorsal.system.domain.CorrectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CorrectionJpaRepository extends JpaRepository<CorrectionEntity, Long> {

    @Transactional(readOnly = true)
    Optional<CorrectionEntity> findByRef(BigDecimal ref);

    @Transactional(readOnly = true)
    Optional<CorrectionEntity> findByUserIdAndCreateDate(Byte userId, LocalDate createDate);

    @Transactional(readOnly = true)
    Optional<List<CorrectionEntity>> findByUserId(Byte userId);

    Optional<List<CorrectionEntity>> findByCreateDateBetween(LocalDate DateDebut, LocalDate DateFin);

    /*@Modifying
    @Query(value = "update BookEntity bookentity set bookentity.author = :author, bookentity.updateAt = :updatedAt, bookentity.name = :name" +
            " where bookentity.isbns = :isbns")
    @Transactional
    void updateByQuery(@Param("author") String author, @Param("name") String name, @Param("isbns") String isbns, @Param("updatedAt") LocalDateTime updatedAt);
*/
    @Modifying
    @Transactional
    void deleteByRef(BigDecimal ref);

}
