package org.pmf.services.office.modules.financial_request.application.view

import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime

class ClientPaymentMethodView(
    val id: String,
    val paidAt: ZonedDateTime?,
    val amount: Money,
    val receivingBankId: String?,
    val type: String,
)
