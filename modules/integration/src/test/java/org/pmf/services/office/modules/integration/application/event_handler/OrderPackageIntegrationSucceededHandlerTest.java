package org.pmf.services.office.modules.integration.application.event_handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.messenger.core.Envelope;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResultRepository;
import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationSucceededEvent;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderPackageIntegrationSucceededHandlerTest {

    @Mock
    OrderPackageIntegrationResultRepository repository;

    @Test
    public void sendIntegrationSucceedHandler()
    {
        new OrderPackageIntegrationSucceededHandler(repository).handle(createEvent());
    }

    private Envelope<OrderPackageIntegrationSucceededEvent> createEvent()
    {

        OrderPackageIntegrationSucceededEvent opise = Mockito.mock(OrderPackageIntegrationSucceededEvent.class);
        when(opise.getOrderPackageId()).thenReturn(UUID.randomUUID());
        when(opise.getOrderPackageVersion()).thenReturn(1l);
        when(opise.getProvider()).thenReturn("Testes Unitários");

        return new Envelope<OrderPackageIntegrationSucceededEvent>(opise, Collections.EMPTY_LIST);
    }
}
