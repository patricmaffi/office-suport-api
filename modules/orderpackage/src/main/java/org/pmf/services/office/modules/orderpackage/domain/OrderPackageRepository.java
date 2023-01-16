package org.pmf.services.office.modules.orderpackage.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPackageRepository extends
        JpaRepository<OrderPackage, UUID>,
        RevisionRepository<OrderPackage, UUID, Long> {

    Optional<OrderPackage> findByGuid(UUID guid);

    Optional<OrderPackage> findByOrderPackageCode(Integer orderPackageCode);

    @Query("SELECT op FROM OrderPackage op JOIN op.orders ord JOIN ord.transactions tr WHERE tr.metadata LIKE %:idTransaction%")
    List<OrderPackage> findByIdTransaction(@Param("idTransaction") String idTransaction);

    @Query("SELECT DISTINCT op FROM OrderPackage op JOIN  op.orders ord JOIN ord.transactions tr JOIN ord.items it WHERE tr.createdAt between :startDate and :endDate")
    List<OrderPackage> findByMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate , Pageable pageable);

}
