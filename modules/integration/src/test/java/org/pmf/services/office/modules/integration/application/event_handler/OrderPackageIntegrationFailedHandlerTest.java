package org.pmf.services.office.modules.integration.application.event_handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.messenger.core.Envelope;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResultRepository;
import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationFailedEvent;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderPackageIntegrationFailedHandlerTest {

    @Mock
    OrderPackageIntegrationResultRepository repository;

    @Test
    public void sendIntegrationSucceedHandler()
    {
        new OrderPackageIntegrationFailedHandler(repository).handle(createEvent());
    }

    private Envelope createEvent()
    {
        OrderPackageIntegrationFailedEvent opise = Mockito.mock(OrderPackageIntegrationFailedEvent.class);
        when(opise.getOrderPackageId()).thenReturn(UUID.randomUUID());
        when(opise.getOrderPackageVersion()).thenReturn(1l);
        when(opise.getProvider()).thenReturn("Testes Unitários");

        return new Envelope<OrderPackageIntegrationFailedEvent>(opise, Collections.EMPTY_LIST);
    }
}
