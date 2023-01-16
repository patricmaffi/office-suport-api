package org.pmf.services.office.modules.integration.entrypoint.http;

import io.swagger.v3.oas.annotations.Operation;
import org.pmf.messenger.core.Envelope;
import org.pmf.messenger.core.MessageBus;
import org.pmf.messenger.core.stamp.FailureStamp;
import org.pmf.messenger.core.stamp.HandledStamp;
import org.pmf.services.office.modules.integration.application.command.CreateOrderPackageIntegrationFailedEvent;
import org.pmf.services.office.modules.integration.application.command.CreateOrderPackageIntegrationSucceededEvent;
import org.pmf.services.office.modules.integration.application.query.GetIntegrationResultCollectionByOrderPackageId;
import org.pmf.services.office.modules.integration.application.query.GetIntegrationResultCollectionByPeriod;
import org.pmf.services.office.modules.integration.application.query.GetOrderPackageIntegrationResultCollection;
import org.pmf.services.office.modules.integration.domain.OrderPackageIntegrationResult;
import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationFailedEvent;
import org.pmf.services.office.modules.integration.domain.event.OrderPackageIntegrationSucceededEvent;
import org.pmf.services.office.modules.integration.entrypoint.http.resource.OrderPackageIntegrationFailedDto;
import org.pmf.services.office.modules.integration.entrypoint.http.resource.OrderPackageIntegrationSucceededDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/integration")
public class IntegrationApi {
    @Qualifier("messageBus") MessageBus messageBus;

    @Autowired
    public IntegrationApi(
            MessageBus messageBus
    ) {
        this.messageBus = messageBus;
    }

    @GetMapping("")
    public String index() {
        return "Integration module";
    }

    @GetMapping(value = "/failedTest")
    public ResponseEntity<OrderPackageIntegrationResult> getIntegrationResult() {

        var orderPackageIntegrationFailedEvent = new OrderPackageIntegrationFailedEvent(
                UUID.randomUUID(),
                1l,
                UUID.randomUUID(),
                "netsuite_adapter",
                "CustomerNotFound",
                new HashMap<>());

        var envelope = messageBus.dispatch(orderPackageIntegrationFailedEvent);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/succeedTest")
    public ResponseEntity<OrderPackageIntegrationResult> createSucceedIntegrationResult() {

        var orderPackageIntegrationSuccedEvent = new OrderPackageIntegrationSucceededEvent(
                 UUID.randomUUID(),
                 UUID.randomUUID(),
                1l,
                "netsuite_adapter"
                );
        var envelope = messageBus.dispatch(orderPackageIntegrationSuccedEvent);
        envelope.getMessage();
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/succeeded")
    @Operation(summary = "Create a new Integration Succeded Event")
    @Transactional
    public ResponseEntity<OrderPackageIntegrationSucceededEvent> createSucceededEvent(@Valid @RequestBody OrderPackageIntegrationSucceededDto event) {

        var envelope = messageBus.dispatch(new CreateOrderPackageIntegrationSucceededEvent(event));
        HandledStamp handledStamp = envelope.lastOf(HandledStamp.class);

        assert handledStamp != null;
        var result = (OrderPackageIntegrationSucceededEvent) handledStamp.getResult();

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping(value = "/failed")
    @Operation(summary = "Create a new Integration Failed Event")
    @Transactional
    public ResponseEntity<OrderPackageIntegrationFailedEvent> createFailedEvent(@Valid @RequestBody OrderPackageIntegrationFailedDto event) {

        var envelope = messageBus.dispatch(new CreateOrderPackageIntegrationFailedEvent(event));
        HandledStamp handledStamp = envelope.lastOf(HandledStamp.class);

        assert handledStamp != null;
        var result = (OrderPackageIntegrationFailedEvent) handledStamp.getResult();

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(value = "/results")
    @Operation(summary = "Retrieve a list with details about the integrations attempts")
    public ResponseEntity<List<OrderPackageIntegrationResult>> getOrderPackageIntegrationResultJson(Pageable pageable) {

        var envelope = messageBus.dispatch(new GetOrderPackageIntegrationResultCollection(pageable));

        HandledStamp handledStamp = envelope.lastOf(HandledStamp.class);
        assert handledStamp != null;
        List<OrderPackageIntegrationResult> results = (List<OrderPackageIntegrationResult>) handledStamp.getResult();

        return ResponseEntity.ok().body(results);
    }

    @GetMapping(value = "/results/{idOrderPackage}", produces = "application/json")
    @Operation(summary = "Retrieve a list OrderPackage details by idOrderPackage")
    public ResponseEntity<List<OrderPackageIntegrationResult>> getOrderPackage(@PathVariable("idOrderPackage") UUID idOrderPackage) {

        var envelope = messageBus.dispatch(new GetIntegrationResultCollectionByOrderPackageId(idOrderPackage));
        HandledStamp handledStamp = envelope.lastOf(HandledStamp.class);
        assert handledStamp != null;
        List<OrderPackageIntegrationResult> results = (List<OrderPackageIntegrationResult>) handledStamp.getResult();

        return ResponseEntity.ok().body(Objects.requireNonNull(results));
    }

    @GetMapping(value = "/errorsByPeriod/{start}/{end}", produces = "application/json")
    @Operation(summary = "Retrieve a list Integration Result Errors details by Period")
    public ResponseEntity<List<OrderPackageIntegrationResult>> getIntegrationResultByPeriod(
            @PathVariable(value = "start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
            @PathVariable(value = "end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {

        Envelope envelope = messageBus.dispatch(new GetIntegrationResultCollectionByPeriod(start, end, true));

        HandledStamp handledStamp = checkFailureStamp(envelope);
        List<OrderPackageIntegrationResult> results = (List<OrderPackageIntegrationResult>) handledStamp.getResult();

        return ResponseEntity.ok().body(Objects.requireNonNull(results));
    }

    @GetMapping(value = "/resultsByPeriod/{start}/{end}", produces = "application/json")
    @Operation(summary = "Retrieve a list Integration Result details by Period With Success")
    public ResponseEntity<List<OrderPackageIntegrationResult>> getIntegrationResultByPeriodWithSuccess(

            @PathVariable(value = "start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
            @PathVariable(value = "end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {

        Envelope envelope = messageBus.dispatch(new GetIntegrationResultCollectionByPeriod(start, end, false));
        HandledStamp handledStamp = checkFailureStamp(envelope);
        List<OrderPackageIntegrationResult> results = (List<OrderPackageIntegrationResult>) handledStamp.getResult();

        return ResponseEntity.ok().body(Objects.requireNonNull(results));
    }

    private HandledStamp checkFailureStamp(Envelope env)
    {
        HandledStamp handledStamp = (HandledStamp) env.lastOf(HandledStamp.class);
        if(handledStamp == null)
        {
            FailureStamp failureStamp = (FailureStamp) env.lastOf(FailureStamp.class);
            RuntimeException e = (RuntimeException) failureStamp.getThrowable();
            throw e;
        }
        return handledStamp;
    }
}
