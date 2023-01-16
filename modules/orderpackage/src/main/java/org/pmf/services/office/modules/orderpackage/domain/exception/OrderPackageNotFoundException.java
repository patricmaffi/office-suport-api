package org.pmf.services.office.modules.orderpackage.domain.exception;

import java.util.UUID;

public class OrderPackageNotFoundException extends RuntimeException {

    private final UUID id;

    public OrderPackageNotFoundException(UUID id) {
        super(String.format("OrderPackage %s not found", id));
        this.id = id;
    }
    public OrderPackageNotFoundException(String id) {
        super(String.format("OrderPackage %s not found", id));
        this.id = null;
    }

    public UUID getId() {
        return id;
    }
}
