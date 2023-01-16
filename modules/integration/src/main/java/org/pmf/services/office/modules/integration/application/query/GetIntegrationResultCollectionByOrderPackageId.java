package org.pmf.services.office.modules.integration.application.query;

import java.util.UUID;

public class GetIntegrationResultCollectionByOrderPackageId implements Query {

    private final UUID guid;

    public GetIntegrationResultCollectionByOrderPackageId(UUID guid) {
        this.guid = guid;
    }

    public UUID getGuid() {
        return guid;
    }
}
