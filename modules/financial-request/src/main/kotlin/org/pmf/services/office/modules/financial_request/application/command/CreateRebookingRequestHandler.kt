package org.pmf.services.office.modules.financial_request.application.command

import org.pmf.services.office.modules.financial_request.domain.entity.RebookingRequest
import org.pmf.services.office.modules.financial_request.domain.repository.RebookingRequestRepository
import org.pmf.services.office.modules.financial_request.domain.entity.Transaction
import org.pmf.services.office.modules.financial_request.domain.repository.TransactionRepository
import org.pmf.services.office.modules.financial_request.domain.exception.InvalidTransactionException
import org.springframework.stereotype.Component


@Component

class CreateRebookingRequestHandler(
    val rebookingRequestRepository: RebookingRequestRepository,
    val transactionRepository: TransactionRepository
) {


    fun handle(command: org.pmf.services.office.modules.financial_request.application.command.CreateRebookingRequest) {

        val relatedTransaction: Transaction = transactionRepository.findTransactionById(command.externalId)
            ?: throw InvalidTransactionException()

        val rebookingRequest = RebookingRequest(
            externalId = command.externalId,
            airline = command.airline,
            fee = command.fee,
            feeProvider = command.feeProvider,
            feeAirline = command.feeAirline,
            voucherValue = command.voucherValue,
            requesterId = command.requesterId,
            feePaymentCreditCardId = command.feePaymentCreditCardId,
            totalClientPaidAmount = command.totalClientPaidAmount,
            payOfferer = command.payOfferer,
            isFeePaymentBilled = command.isFeePaymentBilled,
            clientPaymentMethods = command.clientPaymentMethods,
            refundMethods = command.refundMethods,
            totalTransactionAmount = relatedTransaction.totalAmount,
            totalRebookingAmount = command.totalRebookingAmount,
            losses = command.losses,
        )

        this.rebookingRequestRepository.save(rebookingRequest)

    }


}
