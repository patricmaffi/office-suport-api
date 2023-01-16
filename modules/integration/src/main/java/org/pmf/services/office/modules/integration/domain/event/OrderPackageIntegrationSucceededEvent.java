package org.pmf.services.office.modules.integration.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrderPackageIntegrationSucceededEvent extends OrderPackageIntegrationEvent{
    public OrderPackageIntegrationSucceededEvent(
            @JsonProperty("orderPackageId") UUID orderPackageId,
            @JsonProperty("eventCorrelationId") UUID eventCorrelationId,
            @JsonProperty("orderPackageVersion") Long orderPackageVersion,
            @JsonProperty("provider") String provider) {
        super(orderPackageId, orderPackageVersion, eventCorrelationId, provider);
    }
}
