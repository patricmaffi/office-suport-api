package org.pmf.services.office.modules.financial_request.application.command

import org.pmf.services.office.modules.financial_request.domain.entity.Cancellation
import org.pmf.services.office.modules.financial_request.domain.entity.CancellationRequest
import org.pmf.services.office.modules.financial_request.domain.entity.Transaction
import org.pmf.services.office.modules.financial_request.domain.entity.TransactionType
import org.pmf.services.office.modules.financial_request.domain.exception.InvalidTransactionException
import org.pmf.services.office.modules.financial_request.domain.repository.CancellationRepository
import org.pmf.services.office.modules.financial_request.domain.repository.CancellationRequestRepository
import org.pmf.services.office.modules.financial_request.domain.repository.TransactionRepository
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class CreateCancellationRequestHandler(
    val cancellationRequestRepository: CancellationRequestRepository,
    val transactionRepository: TransactionRepository,
    val cancellationRepository: CancellationRepository,
    val deleteCancellationRequestHandler : org.pmf.services.office.modules.financial_request.application.command.DeleteCancellationRequestHandler
) {

    fun handle(command: org.pmf.services.office.modules.financial_request.application.command.CreateCancellationRequest) {

        val relatedTransaction: Transaction = transactionRepository.findTransactionById(command.externalId)
            ?: throw InvalidTransactionException()

        var relatedCreatedCancellation =  try {
            cancellationRepository.findByTransactionId(command.externalId)
        } catch (e : Exception ) {Cancellation(command.outboundDate, ZonedDateTime.now())}

        val cancellationRequest = if (relatedTransaction.type == TransactionType.OTA) {
            CancellationRequest.createFromOTA(
                externalId = command.externalId,
                airline = command.airline,
                fee = command.fee,
                feePaymentCreditCardId = command.feePaymentCreditCardId,
                feeAirline = command.feeAirline,
                voucherValue = command.voucherValue,
                requesterId = command.requesterId,
                feeCiaPaymentType = command.feeCiaPaymentType!!,
                isPartial = command.isPartial,
                passengersPerStretch = command.passengersPerStretch,
                refundMethods = command.refundMethods,
                relatedTransaction = relatedTransaction,
                outboundDate = relatedCreatedCancellation.outboundDate,
                cancellationCreationDate = relatedCreatedCancellation.createdAt,
                losses = command.losses,
                payOfferer = command.payOfferer,
                totalClientPaidAmount = command.totalClientPaidAmount
            )
        } else {
            CancellationRequest.createFromMiles(
                externalId = command.externalId,
                airline = command.airline,
                fee = command.fee,
                feeAirline = command.feeAirline,
                voucherValue = command.voucherValue,
                requesterId = command.requesterId,
                isPartial = command.isPartial,
                passengersPerStretch = command.passengersPerStretch,
                refundMethods = command.refundMethods,
                feePaymentCreditCardId = command.feePaymentCreditCardId,
                relatedTransaction = relatedTransaction,
                outboundDate = relatedCreatedCancellation.outboundDate,
                cancellationCreationDate = relatedCreatedCancellation.createdAt,
                losses = command.losses,
                payOfferer = command.payOfferer,
                totalClientPaidAmount = command.totalClientPaidAmount
            )
        }
        //REMOVE REQUEST ANTERIOR PARA SALVAR NOVO
        this.deleteCancellationRequestHandler.handle(command.externalId)
        this.cancellationRequestRepository.save(cancellationRequest)
    }

}
