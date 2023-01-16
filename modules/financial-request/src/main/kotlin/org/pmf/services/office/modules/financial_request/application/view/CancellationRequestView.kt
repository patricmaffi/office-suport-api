package org.pmf.services.office.modules.financial_request.application.view

import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime

class CancellationRequestView(
    val feeAirline: Money,
    val fee: Money,
    val feePaymentCreditCardId: String? = null,
    val feeCiaPaymentType: String? = "",
    val passengersPerStretch: List<PassengerPerStretchView>,
    val refundMethods: List<RefundMethodView>,
    val isPartial: Boolean,

    id: String,
    externalId: String,
    requesterId: String,
    voucherValue: Money,
    payOfferer: Boolean?,
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
    requestType = "Cancellation",
    losses = losses,
)
