package org.pmf.services.office.modules.orderpackage.application.query_handler;

import org.pmf.services.office.modules.orderpackage.application.query.GetOrderPackageByTransaction;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrderPackageByTransactionHandler {

    OrderPackageRepository repository;

    @Autowired
    public GetOrderPackageByTransactionHandler(OrderPackageRepository repository) {
        this.repository = repository;
    }

    public List<OrderPackage> handle(GetOrderPackageByTransaction envelope) {
        return repository.findByIdTransaction(envelope.getIdTransaction());
    }
}
