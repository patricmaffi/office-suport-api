package org.pmf.services.office.modules.orderpackage.application.query_handler;

import org.pmf.services.office.modules.orderpackage.application.query.GetOrderPackageByCode;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackageRepository;
import org.pmf.services.office.modules.orderpackage.domain.exception.OrderPackageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetOrderPackageByCodeHandler {

    OrderPackageRepository repository;

    @Autowired
    public GetOrderPackageByCodeHandler(OrderPackageRepository repository) {
        this.repository = repository;
    }

    public OrderPackage handle(GetOrderPackageByCode envelope) {
        return repository.findByOrderPackageCode(envelope.getCode()).orElseThrow(() -> new OrderPackageNotFoundException(envelope.getCode().toString()));
    }
}
