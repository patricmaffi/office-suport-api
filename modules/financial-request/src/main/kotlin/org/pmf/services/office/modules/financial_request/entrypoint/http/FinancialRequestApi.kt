package org.pmf.services.office.modules.financial_request.entrypoint.http

import org.pmf.services.office.modules.financial_request.application.command.*
import org.pmf.services.office.modules.financial_request.application.dto.CreateCancellationRequestDto
import org.pmf.services.office.modules.financial_request.application.dto.CreateRebookingRequestDto
import org.pmf.services.office.modules.financial_request.application.dto.CreateRefundRequestDto
import org.pmf.services.office.modules.financial_request.application.presenter.RequestCollectionPresenter
import org.pmf.services.office.modules.financial_request.application.query.FindManyRequestsQuery
import org.pmf.services.office.modules.financial_request.application.query.FindManyRequestsQueryHandler
import org.pmf.services.office.modules.financial_request.application.view.RequestView
import org.pmf.services.office.modules.financial_request.domain.entity.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/financial-request")
class FinancialRequestApi(
    private val createCancellationRequestHandler: org.pmf.services.office.modules.financial_request.application.command.CreateCancellationRequestHandler,
    private val createRebookingRequestHandler: org.pmf.services.office.modules.financial_request.application.command.CreateRebookingRequestHandler,
    private val createRefundRequestHandler: org.pmf.services.office.modules.financial_request.application.command.CreateRefundRequestHandler,
    private val findManyRequestsQueryHandler: FindManyRequestsQueryHandler,
    private val deleteCancellationRequestHandler: org.pmf.services.office.modules.financial_request.application.command.DeleteCancellationRequestHandler
) {

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{externalId}")
    fun deleteCancellationRequest(@PathVariable("externalId") externalId: String) {
        println("CALL DELETE REQUEST $externalId")
        deleteCancellationRequestHandler.handle(externalId)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("cancellation")
    fun createCancellationRequest(@RequestBody dto: CreateCancellationRequestDto) {
        val refundMethods = ArrayList<RefundMethod>()
        val passengersPerStretch = ArrayList<PassengerPerStretch>()

        dto.refundMethods.forEach { refundMethod ->
            refundMethods.add(
                RefundMethod(
                    type = RefundMethodType.valueOf(refundMethod.type.name),
                    amount = refundMethod.amount,
                    dueDate = refundMethod.dueDate,
                    dueDateType = DueDateType.valueOf(refundMethod.dueDateType.name),
                )
            )
        }

        dto.passengersPerStretch.forEach { passengerPerStretch ->
            passengersPerStretch.add(
                PassengerPerStretch(
                    stretch = Stretch.valueOf(passengerPerStretch.stretch.name),
                    passengerId = passengerPerStretch.passengerId
                )
            )
        }

        this.createCancellationRequestHandler.handle(
            command =
            org.pmf.services.office.modules.financial_request.application.command.CreateCancellationRequest(
                feePaymentCreditCardId = dto.feePaymentCreditCardId,
                feeAirline = dto.feeAirline,
                requesterId = dto.requesterId,
                voucherValue = dto.voucherValue,
                fee = dto.fee,
                airline = dto.airline,
                externalId = dto.externalId,
                feeCiaPaymentType = dto.feeCiaPaymentType,
                isPartial = dto.isPartial,
                payOfferer = dto.payOfferer,
                refundMethods = refundMethods,
                passengersPerStretch = passengersPerStretch,
                losses = dto.losses,
                outboundDate = dto.outboundDate,
                totalClientPaidAmount = dto.totalClientPaidAmount
            )
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rebooking")
    fun createRebookingRequest(@RequestBody @Valid dto: CreateRebookingRequestDto) {
        val refundMethods = ArrayList<RefundMethod>()
        val clientPaymentMethods = ArrayList<ClientPaymentMethod>()

        dto.refundMethods.forEach { refundMethod ->
            refundMethods.add(
                RefundMethod(
                    type = RefundMethodType.valueOf(refundMethod.type.name),
                    amount = refundMethod.amount,
                    dueDate = refundMethod.dueDate,
                    dueDateType = DueDateType.valueOf(refundMethod.dueDateType.name)
                )
            )
        }


        dto.clientPaymentMethods.forEach { paymentMethod ->
            clientPaymentMethods.add(
                ClientPaymentMethod(
                    amount = paymentMethod.amount,
                    receivingBankId = paymentMethod.receivingBankId,
                    type = ClientPaymentMethodType.valueOf(paymentMethod.type.name),
                    paidAt = paymentMethod.paidAt
                )
            )
        }


        this.createRebookingRequestHandler.handle(
            command =
            org.pmf.services.office.modules.financial_request.application.command.CreateRebookingRequest(
                feePaymentCreditCardId = dto.feePaymentCreditCardId,
                feeProvider = dto.feeProvider,
                feeAirline = dto.feeAirline,
                requesterId = dto.requesterId,
                voucherValue = dto.voucherValue,
                fee = dto.fee,
                airline = dto.airline,
                externalId = dto.externalId,
                isFeePaymentBilled = dto.isFeePaymentBilled,
                payOfferer = dto.payOfferer,
                totalClientPaidAmount = dto.totalClientPaidAmount,
                refundMethods = refundMethods,
                clientPaymentMethods = clientPaymentMethods,
                totalRebookingAmount = dto.totalRebookingAmount,
                losses = dto.losses,
            )
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("refund")
    fun createRefundRequest(@RequestBody @Valid dto: CreateRefundRequestDto) {

        this.createRefundRequestHandler.handle(
            command =
            org.pmf.services.office.modules.financial_request.application.command.CreateRefundRequest(
                requesterId = dto.requesterId,
                voucherValue = dto.voucherValue,
                airline = dto.airline,
                externalId = dto.externalId,
                payOfferer = dto.payOfferer,
                amount = dto.amount,
                reason = dto.reason,
                accountType = RefundAccountType.valueOf(dto.accountType.name)
            )
        )
    }

    @GetMapping
    fun findManyRequests(
        @RequestParam("externalId") externalId: String? = null,
        @RequestParam("startDate") startDate: String? = null,
        @RequestParam("endDate") endDate: String? = null,
        @RequestParam("requestType") requestType: String? = null,
    ): List<RequestView> {
        val requests = this.findManyRequestsQueryHandler.handle(
            query = FindManyRequestsQuery(
                externalId = externalId,
                startDate = if (startDate != null) ZonedDateTime.of(
                    LocalDateTime.of(
                        LocalDate.parse(startDate),
                        LocalDateTime.MIN.toLocalTime()
                    ), ZoneId.of("America/Sao_Paulo")
                ) else null,
                endDate = if (endDate != null) ZonedDateTime.of(
                    LocalDateTime.of(
                        LocalDate.parse(endDate),
                        LocalDateTime.MAX.toLocalTime()
                    ), ZoneId.of("America/Sao_Paulo")
                ) else null,
                requestType = if (requestType != null) RequestType.values()
                    .first { it.conventional == requestType } else null
            )
        )

        return RequestCollectionPresenter.present(requests)
    }
}
