package org.pmf.services.office.modules.financial_request.application.view

import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime

class RefundMethodView(
    val id: String,
    val dueDate: ZonedDateTime,
    val amount: Money,
    val type: String,
)
