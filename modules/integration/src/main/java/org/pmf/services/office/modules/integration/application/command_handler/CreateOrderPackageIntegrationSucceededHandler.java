package org.pmf.services.office.modules.integration.application.command_handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pmf.messenger.core.Envelope;
import org.pmf.messenger.core.MessageBus;
import org.pmf.messenger.core.annotation.Handler;
import org.pmf.services.office.modules.integration.application.command.CreateOrderPackageIntegrationSucceededEvent;
import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationSucceededEvent;
import org.pmf.services.office.modules.integration.domain.factory.OrderPackageIntegrationSucceededFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderPackageIntegrationSucceededHandler {
    private static final Logger logger = LogManager.getLogger(CreateOrderPackageIntegrationSucceededHandler.class);
    MessageBus messageBus;
    OrderPackageIntegrationSucceededFactory factory;


    @Autowired
    public CreateOrderPackageIntegrationSucceededHandler(MessageBus messageBus, OrderPackageIntegrationSucceededFactory factory) {
        this.messageBus = messageBus;
        this.factory = factory;
    }

    @Handler("messageBus")
    public OrderPackageIntegrationSucceededEvent handle(Envelope<CreateOrderPackageIntegrationSucceededEvent> command) {
        var integrationEvent = factory.build(command.getMessage().getIntegrationDto());
        messageBus.dispatch(integrationEvent);
        return integrationEvent;
    }
}
