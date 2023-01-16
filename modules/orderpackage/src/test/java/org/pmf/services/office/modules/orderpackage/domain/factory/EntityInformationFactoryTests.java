package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.modules.orderpackage.domain.common.BankAccountType;
import org.pmf.services.office.modules.orderpackage.domain.common.PersonType;
import org.pmf.services.office.modules.orderpackage.application.dto.BankInformationDto;
import org.pmf.services.office.modules.orderpackage.application.dto.EntityInformationDto;
import org.pmf.services.office.modules.orderpackage.application.dto.ReceiptInformationDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class EntityInformationFactoryTests {

    @Mock
    ReceiptInformationFactory receiptInformationFactory;

    @Mock
    BankInformationFactory bankInformationFactory;

    @InjectMocks
    EntityInformationFactory entityInformationFactory;

    public static EntityInformationDto modelSample() {

        var receiptInformationDto = new ReceiptInformationDto(
                "FooSsn",
                PersonType.NATURAL,
                "FooCommercialName",
                "FooAddress",
                "321",
                null,
                "FooNeighborhood",
                "FooState",
                "FooPostalCode",
                "FooMunicipalCode",
                "FooMunicipalSubscription"
        );

        var bankInformationDto = new BankInformationDto(
                "001",
                "fooBank",
                "FooAgency",
                "FooAccountDigit",
                "fooAgencyDigit",
                BankAccountType.CHECKING
        );

        return new EntityInformationDto(
                "foo@email.com",
                "John Doe",
                "FooNumber",
                "0123456789",
                PersonType.NATURAL,
                receiptInformationDto,
                bankInformationDto,
                new HashMap<>()
        );
    }

    @Test
    void testBuild() {
        var entityInformationDto= modelSample();
        var entity = entityInformationFactory.build(entityInformationDto);

        Assertions.assertEquals(entity.getName(), entityInformationDto.getName());
        Assertions.assertEquals(entity.getEmail(), entityInformationDto.getEmail());
        Assertions.assertEquals(entity.getPhone(), entityInformationDto.getPhone());
        Assertions.assertTrue(entity.getMetadata().isEmpty());
    }

    @Test
    void testBuildMany() {
        var entityInformationDto_1= modelSample();
        var entityInformationDto_2= modelSample();

        var list = new ArrayList<EntityInformationDto>();
        list.add(entityInformationDto_1);
        list.add(entityInformationDto_2);

        var entities = entityInformationFactory.buildMany(list);
        Assertions.assertEquals(2, entities.size());
    }
}
