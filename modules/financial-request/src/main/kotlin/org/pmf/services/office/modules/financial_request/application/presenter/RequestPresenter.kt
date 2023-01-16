package org.pmf.services.office.modules.financial_request.application.presenter

import org.pmf.services.office.modules.financial_request.application.view.*
import org.pmf.services.office.modules.financial_request.domain.entity.CancellationRequest
import org.pmf.services.office.modules.financial_request.domain.entity.RebookingRequest
import org.pmf.services.office.modules.financial_request.domain.entity.RefundRequest
import org.pmf.services.office.modules.financial_request.domain.entity.Request

class RequestPresenter {
    companion object {
        fun present(request: Request): RequestView {
            when (request) {
                is CancellationRequest -> {
                    val passengersPerStretch = request.passengersPerStretch.map { passengerPerStretch ->
                        PassengerPerStretchView(
                            id = passengerPerStretch.id.toString(),
                            stretch = passengerPerStretch.stretch.name,
                            passengerId = passengerPerStretch.passengerId
                        )
                    }

                    val refundMethods = request.refundMethods.map { refundMethod ->
                        RefundMethodView(
                            id = refundMethod.id.toString(),
                            type = refundMethod.type.name,
                            amount = refundMethod.amount,
                            dueDate = refundMethod.dueDate
                        )
                    }
                    var losses : LossesView? = null
                    request.losses?.let {
                        losses = LossesView(
                            id = it.id.toString(),
                            amount = it.amount,
                            approver = it.approver,
                            details = it.details,
                            payer = it.payer,
                            reason = it.reason
                        )
                    }

                    return CancellationRequestView(
                        externalId = request.externalId,
                        createdAt = request.createdAt!!,
                        voucherValue = request.voucherValue,
                        feePaymentCreditCardId = request.feePaymentCreditCardId,
                        feeCiaPaymentType = request.feeCiaPaymentType,
                        requesterId = request.requesterId,
                        isPartial = request.isPartial!!,
                        fee = request.fee,
                        passengersPerStretch = passengersPerStretch,
                        refundMethods = refundMethods,
                        airline = request.airline,
                        feeAirline = request.feeAirline,
                        id = request.id.toString(),
                        payOfferer = request.payOfferer,
                        updatedAt = request.updatedAt!!,
                        losses = losses
                    )
                }
                is RebookingRequest -> {
                    val clientPaymentMethods = request.clientPaymentMethods.map { paymentMethod ->
                        ClientPaymentMethodView(
                            id = paymentMethod.id.toString(),
                            type = paymentMethod.type.name,
                            amount = paymentMethod.amount,
                            paidAt = paymentMethod.paidAt,
                            receivingBankId = paymentMethod.receivingBankId
                        )
                    }

                    val refundMethods = request.refundMethods.map { refundMethod ->
                        RefundMethodView(
                            id = refundMethod.id.toString(),
                            type = refundMethod.type.name,
                            amount = refundMethod.amount,
                            dueDate = refundMethod.dueDate
                        )
                    }

                    var losses : LossesView? = null
                    request.losses?.let {
                        losses = LossesView(
                            id = it.id.toString(),
                            amount = it.amount,
                            approver = it.approver,
                            details = it.details,
                            payer = it.payer,
                            reason = it.reason
                        )
                    }

                    return RebookingRequestView(
                        externalId = request.externalId,
                        voucherValue = request.voucherValue,
                        feePaymentCreditCardId = request.feePaymentCreditCardId,
                        isFeePaymentBilled = request.isFeePaymentBilled,
                        requesterId = request.requesterId,
                        fee = request.fee,
                        clientPaymentMethods = clientPaymentMethods,
                        refundMethods = refundMethods,
                        totalClientPaidAmount = request.totalClientPaidAmount,
                        createdAt = request.createdAt!!,
                        airline = request.airline,
                        feeAirline = request.feeAirline,
                        id = request.id.toString(),
                        payOfferer = request.payOfferer,
                        updatedAt = request.updatedAt!!,
                        losses = losses,
                        totalRebookingAmount = request.totalRebookingAmount,
                    )
                }
                is RefundRequest -> {
                    return RefundRequestView(
                        refundAmount = request.refundAmount,
                        refundReason = request.refundReason,
                        refundAccountType = request.refundAccountType.name,
                        externalId = request.externalId,
                        voucherValue = request.voucherValue,
                        requesterId = request.requesterId,
                        createdAt = request.createdAt!!,
                        airline = request.airline,
                        id = request.id.toString(),
                        payOfferer = request.payOfferer,
                        updatedAt = request.updatedAt!!
                    )
                }
                else -> throw Exception("Unrecognized request type")
            }
        }
    }
}
