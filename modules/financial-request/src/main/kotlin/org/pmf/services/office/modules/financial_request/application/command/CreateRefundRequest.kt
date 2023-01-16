package org.pmf.services.office.modules.financial_request.application.command

import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.domain.entity.RefundAccountType

class CreateRefundRequest(
    val externalId: String,
    val requesterId: String,
    val voucherValue: Money,
    val amount: Money,
    val reason: String,
    val accountType: RefundAccountType,
    val payOfferer: Boolean = false,
    val airline: String,
)
