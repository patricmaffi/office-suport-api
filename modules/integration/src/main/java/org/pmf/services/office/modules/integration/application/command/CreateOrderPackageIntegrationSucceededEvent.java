package org.pmf.services.office.modules.integration.application.command;

import org.pmf.services.office.modules.integration.entrypoint.http.resource.OrderPackageIntegrationSucceededDto;

public class CreateOrderPackageIntegrationSucceededEvent {
    private final OrderPackageIntegrationSucceededDto integrationDTO;

    public CreateOrderPackageIntegrationSucceededEvent(OrderPackageIntegrationSucceededDto integrationDTO) {
        this.integrationDTO = integrationDTO;
    }

    public OrderPackageIntegrationSucceededDto getIntegrationDto() {
        return integrationDTO;
    }
}
