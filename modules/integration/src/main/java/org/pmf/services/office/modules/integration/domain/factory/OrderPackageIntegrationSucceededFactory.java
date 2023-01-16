package org.pmf.services.office.modules.integration.domain.factory;

import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationSucceededEvent;
import org.pmf.services.office.modules.integration.entrypoint.http.resource.OrderPackageIntegrationSucceededDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPackageIntegrationSucceededFactory implements Factory<OrderPackageIntegrationSucceededDto, OrderPackageIntegrationSucceededEvent> {

    @Override
    public OrderPackageIntegrationSucceededEvent build(OrderPackageIntegrationSucceededDto model) {
        return new OrderPackageIntegrationSucceededEvent(
                model.getOrderPackageId(),
                model.getEventCorrelationId(),
                model.getOrderPackageVersion(),
                model.getProvider()
        );
    }

    @Override
    public List<OrderPackageIntegrationSucceededEvent> buildMany(List<OrderPackageIntegrationSucceededDto> models) {
        List<OrderPackageIntegrationSucceededEvent> list = new ArrayList<>();
        if (models == null) return list;
        models.forEach((OrderPackageIntegrationSucceededDto adjustmentModel) -> list.add(build(adjustmentModel)));
        return list;
    }
}
