package org.pmf.services.office.modules.financial_request.application.view

import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime

class RebookingRequestView(
    val feeAirline: Money,
    val fee: Money,
    val totalClientPaidAmount: Money,
    val totalRebookingAmount: Money,
    val feePaymentCreditCardId: String? = "",
    val isFeePaymentBilled: Boolean,
    val clientPaymentMethods: List<ClientPaymentMethodView>,
    val refundMethods: List<RefundMethodView>,

    id: String,
    externalId: String,
    requesterId: String,
    voucherValue: Money,
    payOfferer: Boolean? = false,
    airline: String,
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime,
    losses: LossesView?,
) : RequestView(
    id = id,
    externalId = externalId,
    requesterId = requesterId,
    voucherValue = voucherValue,
    payOfferer = payOfferer,
    airline = airline,
    createdAt = createdAt,
    updatedAt = updatedAt,
    requestType = "Rebooking",
    losses = losses,
)
