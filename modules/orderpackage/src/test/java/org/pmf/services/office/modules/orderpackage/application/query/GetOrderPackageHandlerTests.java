package org.pmf.services.office.modules.orderpackage.application.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pmf.services.office.modules.orderpackage.application.query_handler.GetOrderPackageHandler;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackageRepository;
import org.pmf.services.office.modules.orderpackage.domain.exception.OrderPackageNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderPackageHandlerTests {

    @Mock
    OrderPackageRepository repository;

    @InjectMocks
    GetOrderPackageHandler handler;

    @Test
    void testHandleWithResult() {
        var uuid = UUID.randomUUID();
        var result = Mockito.mock(OrderPackage.class);
        GetOrderPackage query = new GetOrderPackage(uuid);
        when(repository.findByGuid(uuid)).thenReturn(Optional.of(result));
        Assertions.assertEquals(result, handler.handle(query));
    }

    @Test
    void testHandleWithoutResult() {
        var uuid = UUID.randomUUID();
        GetOrderPackage query = new GetOrderPackage(uuid);
        when(repository.findByGuid(uuid)).thenReturn(Optional.empty());
        var exception = assertThrows(OrderPackageNotFoundException.class, () -> handler.handle(query));
        Assertions.assertEquals("OrderPackage " + uuid.toString() +  " not found", exception.getMessage());
        Assertions.assertEquals(uuid, exception.getId());
    }
}
