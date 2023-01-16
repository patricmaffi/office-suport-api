package org.pmf.services.office.modules.integration.entrypoint.http.resource;

import java.util.UUID;

public class OrderPackageIntegrationSucceededDto extends OrderPackageIntegrationDto {
    public OrderPackageIntegrationSucceededDto(UUID orderPackageId, Long orderPackageVersion, UUID eventCorrelationId, String provider) {
        super(orderPackageId, orderPackageVersion, eventCorrelationId, provider);
    }
}
