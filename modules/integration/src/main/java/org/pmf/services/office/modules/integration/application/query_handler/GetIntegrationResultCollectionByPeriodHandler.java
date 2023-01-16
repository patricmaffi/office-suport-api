package org.pmf.services.office.modules.integration.application.query_handler;

import org.pmf.messenger.core.Envelope;
import org.pmf.messenger.core.annotation.Handler;
import org.pmf.services.office.modules.integration.application.query.GetIntegrationResultCollectionByPeriod;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResult;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;

@Component
public class GetIntegrationResultCollectionByPeriodHandler {

    OrderPackageIntegrationResultRepository repository;

    @Autowired
    public GetIntegrationResultCollectionByPeriodHandler(OrderPackageIntegrationResultRepository repository) {
        this.repository = repository;
    }

    @Handler("messageBus")
    public List<OrderPackageIntegrationResult> handle(Envelope<GetIntegrationResultCollectionByPeriod> envelope) {
        if(envelope.getMessage().isOnlyErrors())
            return repository.findByErrorsByPeriod(
                    envelope.getMessage().getStart().toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime(),
                    envelope.getMessage().getEnd().toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime());
        else
            return repository.findByPeriod(
                    envelope.getMessage().getStart().toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime(),
                    envelope.getMessage().getEnd().toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime());
    }
}
