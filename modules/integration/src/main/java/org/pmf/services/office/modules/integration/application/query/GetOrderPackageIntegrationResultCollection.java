package org.pmf.services.office.modules.integration.application.query;

import org.springframework.data.domain.Pageable;

public class GetOrderPackageIntegrationResultCollection implements Query {

    private final Pageable pageable;

    public GetOrderPackageIntegrationResultCollection(Pageable pageable) {this.pageable = pageable;}

    public Pageable getPageable() {
        return pageable;
    }
}
