package org.pmf.services.office.modules.financial_request.application.view

import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime

open class RequestView(
    open val id: String,
    open val externalId: String,
    open val requesterId: String,
    open val airline: String,
    open val voucherValue: Money,
    open val payOfferer: Boolean? = false,
    open val createdAt: ZonedDateTime,
    open val updatedAt: ZonedDateTime,
    open val requestType: String,
    open val losses : LossesView? = null,
)
