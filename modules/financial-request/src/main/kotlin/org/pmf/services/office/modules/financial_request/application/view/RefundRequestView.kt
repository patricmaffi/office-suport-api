package org.pmf.services.office.modules.financial_request.application.view

import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime

class RefundRequestView(
    val refundAmount: Money,
    val refundReason: String,
    val refundAccountType: String,

    id: String,
    externalId: String,
    requesterId: String,
    voucherValue: Money,
    payOfferer: Boolean? = false,
    airline: String,
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime,
) : RequestView(
    id = id,
    externalId = externalId,
    requesterId = requesterId,
    voucherValue = voucherValue,
    payOfferer = payOfferer,
    airline = airline,
    createdAt = createdAt,
    updatedAt = updatedAt,
    requestType = "Refund"
)
