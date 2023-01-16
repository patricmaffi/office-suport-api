package org.pmf.services.office.modules.integration.application.query_handler;

import org.pmf.messenger.core.Envelope;
import org.pmf.messenger.core.annotation.Handler;
import org.pmf.services.office.modules.integration.application.query.GetOrderPackageIntegrationResult;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResult;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResultRepository;
import org.pmf.services.office.modules.integration.domain.exception.OrderPackageIntegrationResultNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetOrderPackageIntegrationResultHandler {

    OrderPackageIntegrationResultRepository repository;

    @Autowired
    public GetOrderPackageIntegrationResultHandler(OrderPackageIntegrationResultRepository repository) {
        this.repository = repository;
    }

    @Handler("messageBus")
    public OrderPackageIntegrationResult handle(Envelope<GetOrderPackageIntegrationResult> envelope) {
        var query = envelope.getMessage();
        return repository.findById(query.getGuid()).orElseThrow(() -> new OrderPackageIntegrationResultNotFoundException(query.getGuid()));
    }
}
