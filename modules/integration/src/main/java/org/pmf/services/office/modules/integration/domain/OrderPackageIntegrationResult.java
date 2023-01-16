package org.pmf.services.office.modules.integration.domain;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Table(name = "\"integration_result\"", schema = "office_service")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Entity
public class OrderPackageIntegrationResult {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "order_package_id", columnDefinition = "BINARY(16)")
    private UUID orderPackageId;

    @Column(name = "order_package_version")
    private Long orderPackageVersion;

    @Column(name = "event_correlation_id", columnDefinition = "BINARY(16)")
    private UUID eventCorrelationId;

    private String provider;

    private Long attempts;

    private Result result;

    @Column(name = "fail_code")
    private String failCode;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private Map<String, String> details;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public OrderPackageIntegrationResult() {
    }

    public OrderPackageIntegrationResult(
            UUID orderPackageId,
            Long orderPackageVersion,
            UUID eventCorrelationId,
            String provider,
            Long attempts,
            Result result,
            Map<String, String> details
    ) {
        this.id = UUID.randomUUID();
        this.orderPackageId = orderPackageId;
        this.orderPackageVersion = orderPackageVersion;
        this.eventCorrelationId = eventCorrelationId;
        this.provider = provider;
        this.attempts = attempts;
        this.result = result;
        this.details = details;
        this.createdAt = LocalDateTime.now();
    }

    public OrderPackageIntegrationResult(
            UUID orderPackageId,
            Long orderPackageVersion,
            UUID eventCorrelationId,
            String provider,
            Long attempts,
            Result result,
            Map<String, String> details,
            String failCode) {
        this(orderPackageId, orderPackageVersion, eventCorrelationId, provider, attempts, result, details);
        this.failCode = failCode;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderPackageId() {
        return orderPackageId;
    }

    public Long getOrderPackageVersion() {
        return orderPackageVersion;
    }

    public UUID getEventCorrelationId() {
        return eventCorrelationId;
    }

    public String getProvider() {
        return provider;
    }

    public Result getResult() {
        return result;
    }

    public String getFailCode() {
        return failCode;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getAttempts() {
        return attempts;
    }
}