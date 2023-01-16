package org.pmf.services.office.modules.orderpackage.application.query;

import org.springframework.data.domain.Pageable;

public class GetOrderPackageCollection implements Query {

    private final Pageable pageable;

    public GetOrderPackageCollection(Pageable pageable) {this.pageable = pageable;}

    public Pageable getPageable() {
        return pageable;
    }
}
