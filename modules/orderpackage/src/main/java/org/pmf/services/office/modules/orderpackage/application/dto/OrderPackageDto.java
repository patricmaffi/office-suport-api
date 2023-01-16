package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pmf.services.office.modules.orderpackage.domain.ProviderRelation;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderPackageDto {

    @NotEmpty
    private final String company;

    private final UUID correlationId;

    @NotNull
    private final ProviderRelation providerRelation;

    @NotEmpty
    private final List<OrderDto> orders;
    private final Map<String, Object> metadata;

    @JsonCreator
    public OrderPackageDto(
            @JsonProperty("company") String company,
            @JsonProperty("correlationId") UUID correlationId,
            @JsonProperty("providerRelation") ProviderRelation providerRelation,
            @JsonProperty("orders") List<OrderDto> orders,
            @JsonProperty("metadata") Map<String, Object> metadata
    ) {
        this.company = company;
        this.correlationId = correlationId;
        this.providerRelation = providerRelation;
        this.orders = orders;
        this.metadata = metadata;
    }

    public String getCompany() {
        return company;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public ProviderRelation getProviderRelation() {
        return providerRelation;
    }

    public List<@Valid OrderDto> getOrders() {
        return orders;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
