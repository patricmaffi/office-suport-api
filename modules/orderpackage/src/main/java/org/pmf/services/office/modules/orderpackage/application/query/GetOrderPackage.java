package org.pmf.services.office.modules.orderpackage.application.query;

import java.util.UUID;

public class GetOrderPackage implements Query {

    private final UUID guid;

    public GetOrderPackage(UUID guid) {
        this.guid = guid;
    }

    public UUID getGuid() {
        return guid;
    }
}
