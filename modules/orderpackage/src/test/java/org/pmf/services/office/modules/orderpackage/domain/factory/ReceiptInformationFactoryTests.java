package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.domain.common.PersonType;
import org.pmf.services.office.modules.orderpackage.application.dto.ReceiptInformationDto;

import java.util.ArrayList;

class ReceiptInformationFactoryTests {

    ReceiptInformationFactory receiptInformationFactory;

    public ReceiptInformationFactoryTests() {
        this.receiptInformationFactory = new ReceiptInformationFactory();
    }

    static ReceiptInformationDto modelSample() {
        return new ReceiptInformationDto(
                "FooSsn",
                PersonType.NATURAL,
                "FooFantasyName",
                "FooAddress",
                "FooNumber",
                "FooComplement",
                "FooNeighborhood",
                "FooState",
                "FooPostalCode",
                "FooMunicipalCode",
                "FooMunicipalSubscription"
        );
    }

    @Test
    void testBuild() {
        var receiptInformationDto = modelSample();
        var receiptInformation = receiptInformationFactory.build(receiptInformationDto);

        Assertions.assertEquals(receiptInformation.getAddress().toString(), receiptInformationDto.getAddress().toString());
        Assertions.assertEquals(receiptInformation.getSsn(), receiptInformationDto.getSsn());
        Assertions.assertEquals(receiptInformation.getNeighborhood(), receiptInformationDto.getNeighborhood());
        Assertions.assertEquals(receiptInformation.getFantasyName(), receiptInformationDto.getFantasyName());
        Assertions.assertEquals(receiptInformation.getState(), receiptInformationDto.getState());
        Assertions.assertEquals(receiptInformation.getPersonType(), receiptInformationDto.getPersonType());
        Assertions.assertEquals(receiptInformation.getMunicipalCode(), receiptInformationDto.getMunicipalCode());
        Assertions.assertEquals(receiptInformation.getMunicipalSubscription(), receiptInformationDto.getMunicipalSubscription());
        Assertions.assertEquals(receiptInformation.getPostalCode(), receiptInformationDto.getPostalCode());
    }

    @Test
    void testBuildMany() {
        var receiptInformationDto_1 = modelSample();
        var receiptInformationDto_2 = modelSample();

        var list = new ArrayList<ReceiptInformationDto>();
        list.add(receiptInformationDto_1);
        list.add(receiptInformationDto_2);

        var receipts = receiptInformationFactory.buildMany(list);

        Assertions.assertFalse(receipts.isEmpty());
    }
}
