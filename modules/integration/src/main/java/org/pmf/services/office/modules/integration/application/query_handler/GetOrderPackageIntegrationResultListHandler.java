package org.pmf.services.office.modules.integration.application.query_handler;

import org.pmf.messenger.core.Envelope;
import org.pmf.messenger.core.annotation.Handler;
import org.pmf.services.office.modules.integration.application.query.GetOrderPackageIntegrationResultCollection;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResult;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrderPackageIntegrationResultListHandler {

    OrderPackageIntegrationResultRepository repository;

    @Autowired
    public GetOrderPackageIntegrationResultListHandler(OrderPackageIntegrationResultRepository repository) {
        this.repository = repository;
    }

    @Handler("messageBus")
    public List<OrderPackageIntegrationResult> handle(Envelope<GetOrderPackageIntegrationResultCollection> envelope) {
        var pageable = envelope.getMessage().getPageable();
        return repository.findAll(pageable).getContent();
    }
}
