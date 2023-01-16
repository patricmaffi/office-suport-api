package org.pmf.services.office.modules.integration.domain.factory;

import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationFailedEvent;
import org.pmf.services.office.modules.integration.entrypoint.http.resource.OrderPackageIntegrationFailedDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPackageIntegrationFailedFactory implements Factory<OrderPackageIntegrationFailedDto, OrderPackageIntegrationFailedEvent> {

    @Override
    public OrderPackageIntegrationFailedEvent build(OrderPackageIntegrationFailedDto model) {
        return new OrderPackageIntegrationFailedEvent(
                model.getOrderPackageId(),
                model.getOrderPackageVersion(),
                model.getEventCorrelationId(),
                model.getProvider(),
                model.getFailCode(),
                model.getDetails()
        );
    }

    @Override
    public List<OrderPackageIntegrationFailedEvent> buildMany(List<OrderPackageIntegrationFailedDto> models) {
        List<OrderPackageIntegrationFailedEvent> list = new ArrayList<>();
        if (models == null) return list;
        models.forEach((OrderPackageIntegrationFailedDto adjustmentModel) -> list.add(build(adjustmentModel)));
        return list;
    }
}
