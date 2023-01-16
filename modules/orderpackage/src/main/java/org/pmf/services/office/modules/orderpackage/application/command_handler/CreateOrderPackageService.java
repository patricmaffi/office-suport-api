package org.pmf.services.office.modules.orderpackage.application.command_handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pmf.services.office.modules.orderpackage.application.presenter.OrderPackagePresenter;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateOrderPackageService {
    private static final Logger logger = LogManager.getLogger(CreateOrderPackageService.class);
    OrderPackageRepository repository;
    OrderPackagePresenter orderPackagePresenter;

    @Autowired
    public CreateOrderPackageService(OrderPackageRepository repository,
                                     OrderPackagePresenter orderPackagePresenter) {
        this.repository = repository;
        this.orderPackagePresenter = orderPackagePresenter;
    }

    @Transactional
    public OrderPackage CreateOrderPackage(OrderPackage orderPackage) {
        OrderPackage orderPackageDB = repository.saveAndFlush(orderPackage);
        var view = orderPackagePresenter.present(orderPackageDB);
        return orderPackageDB;
    }
}
