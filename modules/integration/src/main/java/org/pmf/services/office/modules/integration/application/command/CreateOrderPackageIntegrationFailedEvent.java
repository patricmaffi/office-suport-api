package org.pmf.services.office.modules.integration.application.command;

import org.pmf.services.office.modules.integration.entrypoint.http.resource.OrderPackageIntegrationFailedDto;

public class CreateOrderPackageIntegrationFailedEvent {
    private final OrderPackageIntegrationFailedDto integrationDTO;

    public CreateOrderPackageIntegrationFailedEvent(OrderPackageIntegrationFailedDto integrationDTO) {
        this.integrationDTO = integrationDTO;
    }

    public OrderPackageIntegrationFailedDto getIntegrationDto() {
        return integrationDTO;
    }
}
