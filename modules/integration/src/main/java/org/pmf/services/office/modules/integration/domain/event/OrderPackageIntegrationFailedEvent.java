package org.pmf.services.office.modules.integration.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.UUID;

public class OrderPackageIntegrationFailedEvent extends OrderPackageIntegrationEvent {


    private final String failCode;

    private final Map<String, String> details;

    public OrderPackageIntegrationFailedEvent(
            @JsonProperty("orderPackageId") UUID orderPackageId,
            @JsonProperty("orderPackageVersion") Long orderPackageVersion,
            @JsonProperty("eventCorrelationId") UUID eventCorrelationId,
            @JsonProperty("provider") String provider,
            @JsonProperty("failCode") String failCode,
            @JsonProperty("details") Map<String, String> details
    ) {
        super(orderPackageId, orderPackageVersion, eventCorrelationId, provider);
        this.failCode = failCode;
        this.details = details;
    }

    public String getFailCode() {
        return failCode;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
