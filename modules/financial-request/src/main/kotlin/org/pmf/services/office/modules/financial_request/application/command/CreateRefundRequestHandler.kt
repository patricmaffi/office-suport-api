package org.pmf.services.office.modules.financial_request.application.command

import org.pmf.services.office.modules.financial_request.domain.entity.RefundRequest
import org.pmf.services.office.modules.financial_request.domain.repository.RefundRequestRepository
import org.pmf.services.office.modules.financial_request.domain.repository.TransactionRepository
import org.pmf.services.office.modules.financial_request.domain.exception.InvalidTransactionException

import org.springframework.stereotype.Component

@Component
class CreateRefundRequestHandler(
    val rebookingRequestRepository: RefundRequestRepository,
    val transactionRepository: TransactionRepository
) {

    fun handle(command: org.pmf.services.office.modules.financial_request.application.command.CreateRefundRequest) {

        transactionRepository.findTransactionById(command.externalId)
            ?: throw InvalidTransactionException()

        val rebookingRequest = RefundRequest(
            externalId = command.externalId,
            airline = command.airline,
            voucherValue = command.voucherValue,
            requesterId = command.requesterId,
            payOfferer = command.payOfferer,
            refundAccountType = command.accountType,
            refundAmount = command.amount,
            refundReason = command.reason,
        )

        this.rebookingRequestRepository.save(rebookingRequest)
    }
}
