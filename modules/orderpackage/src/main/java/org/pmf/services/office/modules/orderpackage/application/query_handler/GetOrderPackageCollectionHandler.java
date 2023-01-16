package org.pmf.services.office.modules.orderpackage.application.query_handler;

import org.pmf.services.office.modules.orderpackage.application.query.GetOrderPackageCollection;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrderPackageCollectionHandler {

    OrderPackageRepository repository;

    @Autowired
    public GetOrderPackageCollectionHandler(OrderPackageRepository repository) {
        this.repository = repository;
    }

    public List<OrderPackage> handle(GetOrderPackageCollection envelope) {
        var pageable = envelope.getPageable();
        return repository.findAll(pageable).getContent();
    }
}
