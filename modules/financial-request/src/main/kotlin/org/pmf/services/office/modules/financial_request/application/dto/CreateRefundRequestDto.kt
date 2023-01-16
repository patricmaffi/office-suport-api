package org.pmf.services.office.modules.financial_request.application.dto

import org.pmf.services.office.core.value_object.Money
import javax.validation.constraints.NotNull

class CreateRefundRequestDto(

    @NotNull
    var externalId: String,

    @NotNull
    var requesterId: String,

    @NotNull
    var voucherValue: Money,

    @NotNull
    var amount: Money,

    @NotNull
    var reason: String,

    @NotNull
    var accountType: RefundAccountTypeDto,
    var payOfferer: Boolean = false,

    @NotNull
    var airline: String
)
