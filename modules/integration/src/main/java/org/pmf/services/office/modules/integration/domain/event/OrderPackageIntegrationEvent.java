package org.pmf.services.office.modules.integration.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrderPackageIntegrationEvent {

    private final UUID orderPackageId;

    private final UUID eventCorrelationId;

    private final String provider;

    private final Long orderPackageVersion;


    public OrderPackageIntegrationEvent(
            @JsonProperty("orderPackageId") UUID orderPackageId,
            @JsonProperty("orderPackageVersion") Long orderPackageVersion,
            @JsonProperty("eventCorrelationId") UUID eventCorrelationId,
            @JsonProperty("provider") String provider
    ) {
        this.orderPackageId = orderPackageId;
        this.orderPackageVersion = orderPackageVersion;
        this.eventCorrelationId = eventCorrelationId;
        this.provider = provider;
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

}
