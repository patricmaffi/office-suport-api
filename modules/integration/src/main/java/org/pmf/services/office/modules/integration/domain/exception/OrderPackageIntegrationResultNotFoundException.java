package org.pmf.services.office.modules.integration.domain.exception;

import java.util.UUID;

public class OrderPackageIntegrationResultNotFoundException extends RuntimeException {

    private final UUID id;

    public OrderPackageIntegrationResultNotFoundException(UUID id) {
        super(String.format("OrderPackage %s not found", id));
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
