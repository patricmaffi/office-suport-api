package org.pmf.services.office.modules.orderpackage.application.query_handler;

import org.pmf.services.office.modules.orderpackage.application.query.GetOrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackageRepository;
import org.pmf.services.office.modules.orderpackage.domain.exception.OrderPackageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetOrderPackageHandler {

    OrderPackageRepository repository;

    @Autowired
    public GetOrderPackageHandler(OrderPackageRepository repository) {
        this.repository = repository;
    }

    public OrderPackage handle(GetOrderPackage envelope) {
        return repository.findByGuid(envelope.getGuid()).orElseThrow(() -> new OrderPackageNotFoundException(envelope.getGuid()));
    }
}
