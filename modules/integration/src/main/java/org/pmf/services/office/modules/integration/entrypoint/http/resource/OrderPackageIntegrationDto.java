package org.pmf.services.office.modules.integration.entrypoint.http.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class OrderPackageIntegrationDto {

    @Valid
    private final UUID orderPackageId;

    @Valid
    private final UUID eventCorrelationId;

    @Valid
    @NotNull
    private final String provider;

    private final Long orderPackageVersion;


    public OrderPackageIntegrationDto(
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
