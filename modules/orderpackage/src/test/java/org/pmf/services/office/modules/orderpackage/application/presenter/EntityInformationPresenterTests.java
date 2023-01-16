package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.domain.common.EntityInformation;
import org.mockito.Mockito;

class EntityInformationPresenterTests {

    EntityInformationPresenter entityInformationPresenter;

    public EntityInformationPresenterTests() {
        this.entityInformationPresenter = new EntityInformationPresenter();
    }

    @Test
    void testPresent() {
        var entityInformationPresenter = new EntityInformationPresenter();
        var entityInformationMock = Mockito.mock(EntityInformation.class);

        var entityInformationView = entityInformationPresenter.present(entityInformationMock);

        Assertions.assertNotNull(entityInformationView);
        Assertions.assertNull(entityInformationView.getName());
        Assertions.assertNull(entityInformationView.getEmail());
        Assertions.assertNull(entityInformationView.getPhone());
        Assertions.assertNull(entityInformationView.getReceiptInformation());
        Assertions.assertNull(entityInformationView.getBankInformation());
        Assertions.assertEquals(0, entityInformationView.getMetadata().size());
    }
}
