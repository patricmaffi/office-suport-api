package org.pmf.services.office.modules.integration.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderPackageIntegrationResultRepository
        extends JpaRepository<OrderPackageIntegrationResult, UUID> {

    Optional<OrderPackageIntegrationResult> findById(UUID id);

    @Query("SELECT COUNT(u) FROM OrderPackageIntegrationResult u WHERE u.orderPackageId=?1")
    long countByOrderPackage(UUID orderPackageId);

    @Query("SELECT u FROM OrderPackageIntegrationResult u WHERE u.orderPackageId = ?1 or u.eventCorrelationId = ?1")
    List<OrderPackageIntegrationResult> findByOrderPackageId(UUID id);

    @Query("SELECT DISTINCT u FROM OrderPackageIntegrationResult u WHERE u.createdAt between :startDate and :endDate")
    List<OrderPackageIntegrationResult> findByPeriod(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT DISTINCT u FROM OrderPackageIntegrationResult u WHERE u.result = 0 AND u.createdAt between :startDate and :endDate")
    List<OrderPackageIntegrationResult> findByErrorsByPeriod(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
