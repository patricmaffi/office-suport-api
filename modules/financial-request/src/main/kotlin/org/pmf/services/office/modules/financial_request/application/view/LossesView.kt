package org.pmf.services.office.modules.financial_request.application.view

import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.domain.entity.LossPayerType
import org.pmf.services.office.modules.financial_request.domain.entity.LossReasonType

open class LossesView(
    open val id: String,
    open val amount: Money,
    open val details: String,
    open val payer: LossPayerType,
    open val reason: LossReasonType,
    open val approver: String,
)
