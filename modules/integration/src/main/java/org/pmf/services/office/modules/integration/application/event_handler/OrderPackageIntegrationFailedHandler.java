package org.pmf.services.office.modules.integration.application.event_handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pmf.messenger.core.Envelope;
import org.pmf.messenger.core.annotation.Handler;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResult;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResultRepository;
import org.pmf.services.office.modules.integration.domain.Result;
import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationFailedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderPackageIntegrationFailedHandler {

    private static final Logger logger = LogManager.getLogger(OrderPackageIntegrationFailedHandler.class);
    OrderPackageIntegrationResultRepository repository;

    @Autowired
    public OrderPackageIntegrationFailedHandler(OrderPackageIntegrationResultRepository repository) {
        this.repository = repository;
    }

    @Handler("messageBus")
    public OrderPackageIntegrationResult handle(Envelope<OrderPackageIntegrationFailedEvent> event) {
        var message = event.getMessage();
        long attempts = repository.countByOrderPackage(message.getOrderPackageId()) +1;
        var integrationResult = new OrderPackageIntegrationResult(
                message.getOrderPackageId(),
                message.getOrderPackageVersion(),
                message.getEventCorrelationId(),
                message.getProvider(),
                attempts,
                Result.FAIL,
                message.getDetails(),
                message.getFailCode()
        );
        repository.saveAndFlush(integrationResult);
        logger.info("Receiving OrderPackageIntegrationFailedEvent and persisting");
        return integrationResult;
    }
}
