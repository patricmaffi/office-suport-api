package org.pmf.services.office.modules.integration.application.command_handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pmf.messenger.core.Envelope;
import org.pmf.messenger.core.MessageBus;
import org.pmf.messenger.core.annotation.Handler;
import org.pmf.services.office.modules.integration.application.command.CreateOrderPackageIntegrationFailedEvent;
import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationFailedEvent;
import org.pmf.services.office.modules.integration.domain.factory.OrderPackageIntegrationFailedFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderPackageIntegrationFailedHandler {
    private static final Logger logger = LogManager.getLogger(CreateOrderPackageIntegrationFailedHandler.class);
    MessageBus messageBus;
    OrderPackageIntegrationFailedFactory factory;


    @Autowired
    public CreateOrderPackageIntegrationFailedHandler(MessageBus messageBus, OrderPackageIntegrationFailedFactory factory) {
        this.messageBus = messageBus;
        this.factory = factory;
    }

    @Handler("messageBus")
    public OrderPackageIntegrationFailedEvent handle(Envelope<CreateOrderPackageIntegrationFailedEvent> command) {
        var integrationEvent = factory.build(command.getMessage().getIntegrationDto());
        messageBus.dispatch(integrationEvent);
        return integrationEvent;
    }
}
