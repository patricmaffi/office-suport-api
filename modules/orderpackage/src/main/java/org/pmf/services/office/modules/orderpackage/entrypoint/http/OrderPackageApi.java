package org.pmf.services.office.modules.orderpackage.entrypoint.http;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.pmf.messenger.core.Envelope;
import org.pmf.messenger.core.MessageBus;
import org.pmf.messenger.core.stamp.FailureStamp;
import org.pmf.messenger.core.stamp.HandledStamp;
import org.pmf.services.office.modules.orderpackage.application.command.AddOrderToOrderPackage;
import org.pmf.services.office.modules.orderpackage.application.command.CreateOrderPackage;
import org.pmf.services.office.modules.orderpackage.application.dto.OrderDto;
import org.pmf.services.office.modules.orderpackage.application.dto.OrderPackageDto;
import org.pmf.services.office.modules.orderpackage.application.presenter.OrderPackageCollectionPresenter;
import org.pmf.services.office.modules.orderpackage.application.presenter.OrderPackagePresenter;
import org.pmf.services.office.modules.orderpackage.application.presenter.OrderPackageSummaryPresenter;
import org.pmf.services.office.modules.orderpackage.application.query.*;
import org.pmf.services.office.modules.orderpackage.application.view.OrderPackageSummaryView;
import org.pmf.services.office.modules.orderpackage.application.view.OrderPackageView;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.exception.OrderPackageNotFoundException;
import org.pmf.services.office.modules.orderpackage.entrypoint.http.api.error_handler.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/orderpackage")
public class OrderPackageApi {
    OrderPackageSummaryPresenter orderPackageSummaryPresenter;
    OrderPackageCollectionPresenter orderPackageCollectionPresenter;
    OrderPackagePresenter orderPackagePresenter;
    @Qualifier("messageBus") MessageBus messageBus;

    @Autowired
    public OrderPackageApi(
            OrderPackageSummaryPresenter orderPackageSummaryPresenter,
            OrderPackagePresenter orderPackagePresenter,
            OrderPackageCollectionPresenter orderPackageCollectionPresenter,
            MessageBus messageBus
    ) {
        this.orderPackageSummaryPresenter = orderPackageSummaryPresenter;
        this.orderPackagePresenter = orderPackagePresenter;
        this.orderPackageCollectionPresenter = orderPackageCollectionPresenter;
        this.messageBus = messageBus;
    }

    @GetMapping(value = "/orderpackages")
    @Operation(summary = "Retrieve the list of OrderPackage")
    @PreAuthorize("hasAnyAuthority('SCOPE_financial:order-package','SCOPE_financial:order-package:read')")
    public ResponseEntity<List<OrderPackageView>> getOrderPackagesJson(Pageable pageable) {

        var envelope = messageBus.dispatch(new GetOrderPackageCollection(pageable));

        HandledStamp handledStamp = envelope.lastOf(HandledStamp.class);
        assert handledStamp != null;
        List<OrderPackage> orderPackages = (List<OrderPackage>) handledStamp.getResult();
        var view = orderPackageCollectionPresenter.present(orderPackages);

        return ResponseEntity.ok().body(view);
    }

    @GetMapping(value = "/orderpackages/{idOrderPackage}", produces = "application/json")
    @Operation(summary = "Retrieve a OrderPackage details by idOrderPackage")
    @PreAuthorize("hasAnyAuthority('SCOPE_financial:order-package','SCOPE_financial:order-package:read')")
    public ResponseEntity<OrderPackageView> getOrderPackage(@PathVariable("idOrderPackage") UUID idOrderPackage) {

        var envelope = messageBus.dispatch(new GetOrderPackage(idOrderPackage));
        HandledStamp handledStamp = checkFailureStamp(envelope);
        assert handledStamp != null;
        OrderPackage orderPackage = (OrderPackage) handledStamp.getResult();
        var view = orderPackagePresenter.present(Objects.requireNonNull(orderPackage));

        return ResponseEntity.ok().body(view);
    }

    @GetMapping(value = "/orderpackages/bycode/{orderPackageCode}", produces = "application/json")
    @Operation(summary = "Retrieve a OrderPackage details by OrderPackageCode: ex: OP-123456 or 123456")
    @PreAuthorize("hasAnyAuthority('SCOPE_financial:order-package','SCOPE_financial:order-package:read')")
    public ResponseEntity<OrderPackageView> getOrderPackageByCode(@PathVariable("orderPackageCode") String orderPackageCode) {

        orderPackageCode = orderPackageCode.toUpperCase().replace("OP-","");
        var envelope = messageBus.dispatch(new GetOrderPackageByCode(Integer.parseInt(orderPackageCode)));
        HandledStamp handledStamp = checkFailureStamp(envelope);
        assert handledStamp != null;
        OrderPackage orderPackage = (OrderPackage) handledStamp.getResult();
        var view = orderPackagePresenter.present(Objects.requireNonNull(orderPackage));

        return ResponseEntity.ok().body(view);
    }

    @GetMapping(value = "/orderpackages/bytransaction/{idTransaction}", produces = "application/json")
    @Operation(summary = "Retrieve a OrderPackage's list by idTransaction")
    @PreAuthorize("hasAnyAuthority('SCOPE_financial:order-package','SCOPE_financial:order-package:read')")
    public ResponseEntity<List<OrderPackageView>> getOrderPackageByTransaction(@PathVariable("idTransaction") String idTransaction) {

        var envelope = messageBus.dispatch(new GetOrderPackageByTransaction(idTransaction));
        HandledStamp handledStamp = checkFailureStamp(envelope);
        assert handledStamp != null;
        List<OrderPackage> orderPackage = (List<OrderPackage>) handledStamp.getResult();
        var view = orderPackagePresenter.presentMany(Objects.requireNonNull(orderPackage));

        return ResponseEntity.ok().body(view);
    }

    @GetMapping(value = "/orderpackages/{idOrderPackage}/summary")
    @Operation(summary = "Retrieves a summary from order")
    @PreAuthorize("hasAnyAuthority('SCOPE_financial:order-package','SCOPE_financial:order-package:read')")
    public ResponseEntity<OrderPackageSummaryView> getOrderPackageSummary(@PathVariable("idOrderPackage") UUID idOrderPackage) {

        var envelope = messageBus.dispatch(new GetOrderPackage(idOrderPackage));

        HandledStamp handledStamp = checkFailureStamp(envelope);
        assert handledStamp != null;
        OrderPackage orderPackage = (OrderPackage) handledStamp.getResult();
        var view = orderPackageSummaryPresenter.present(orderPackage);

        return ResponseEntity.ok().body(view);
    }

    @GetMapping(value = "/orderpackages/summary/{month}")
    @Operation(summary = "Retrieves a summary from order")
    @PreAuthorize("hasAnyAuthority('SCOPE_financial:order-package','SCOPE_financial:order-package:read')")
    public ResponseEntity<List<OrderPackageSummaryView>> getOrderPackageSummary(
            @PathVariable("month") @DateTimeFormat(pattern="yyyy-MM-dd") Date monthRef,
            int size, int page) {
        var envelope = messageBus.dispatch(new GetSumaryByMonth(monthRef, PageRequest.of(page, size)));

        HandledStamp handledStamp = checkFailureStamp(envelope);
        assert handledStamp != null;
        List<OrderPackage> orderPackage = (List<OrderPackage>) handledStamp.getResult();
        var view = orderPackageSummaryPresenter.presentMany(orderPackage);

        return ResponseEntity.ok().body(view);
    }

    @GetMapping(value = "/orderpackages/version")
    @Operation(summary = "Retrieves a manual project's version ")
    @PreAuthorize("hasAnyAuthority('SCOPE_financial:order-package','SCOPE_financial:order-package:read')")
    public ResponseEntity version() {return ResponseEntity.ok().body("1.1.10");}

    @PostMapping(value = "/orderpackages")
    @Operation(summary = "Create a new OrderPackage")
    @PreAuthorize("hasAnyAuthority('SCOPE_financial:order-package','SCOPE_financial:order-package:read')")
    @Transactional
    @ApiResponses(
            value = {
                @ApiResponse(code = 404, message = "Resource not found", response = ErrorResponse.class),
                @ApiResponse(code = 201, message = "Created")
            }
            )
    public ResponseEntity<OrderPackageView> createOrderPackage(@Valid @RequestBody OrderPackageDto orderPackageModel) {
        var envelope = messageBus.dispatch(new CreateOrderPackage(orderPackageModel));
        HandledStamp handledStamp = checkFailureStamp(envelope);

        assert handledStamp != null;
        OrderPackage orderPackage = (OrderPackage) handledStamp.getResult();

        assert orderPackage != null;
        var orderPackageView = orderPackagePresenter.present(orderPackage);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderPackageView);
    }

    @PostMapping(value = "/orderpackages/{idOrderPackage}/orders", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Add an Order to an exists OrderPackage")
    @PreAuthorize("hasAuthority('SCOPE_financial:order-package')")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Resource not found", response = ErrorResponse.class),
                    @ApiResponse(code = 201, message = "Created")
            }
    )
    public ResponseEntity addOrderToOrderPackage(@PathVariable("idOrderPackage") UUID idOrderPackage, @RequestBody List<@Valid OrderDto> orderDtos) {
        if(orderDtos.size() > 0){
            throw new OrderPackageNotFoundException(idOrderPackage);
        }
        var envelope = messageBus.dispatch(new AddOrderToOrderPackage(idOrderPackage, orderDtos));

        var query = new GetOrderPackage(idOrderPackage);
        var envelopeBusca = messageBus.dispatch(query);

        HandledStamp handledStamp = checkFailureStamp(envelopeBusca);

        assert handledStamp != null;
        OrderPackage orderPackage = (OrderPackage) handledStamp.getResult();

        var orderPackageView = orderPackagePresenter.present(orderPackage);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderPackageView);
    }

    // Verifica se existe um Selo de retorno Válido
    // Se não houver verifica se tem um selo de falha 
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
