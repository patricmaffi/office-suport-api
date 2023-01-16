package org.pmf.services.office.modules.integration.application.query;

import java.util.UUID;

public class GetOrderPackageIntegrationResult implements Query {

    private final UUID guid;

    public GetOrderPackageIntegrationResult(UUID guid) {
        this.guid = guid;
    }

    public UUID getGuid() {
        return guid;
    }
}
