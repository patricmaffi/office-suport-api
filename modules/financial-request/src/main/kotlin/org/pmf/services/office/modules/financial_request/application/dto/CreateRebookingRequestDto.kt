package org.pmf.services.office.modules.financial_request.application.dto

import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.domain.entity.Losses
import javax.validation.Valid
import javax.validation.constraints.NotNull

class CreateRebookingRequestDto(
    @NotNull
    var fee: Money,

    @NotNull
    var feeProvider: Money,

    @NotNull
    var feeAirline: Money,

    @NotNull
    var totalRebookingAmount: Money,

    @NotNull
    var externalId: String,

    @NotNull
    var requesterId: String,

    @NotNull
    var voucherValue: Money,

    @NotNull
    var totalClientPaidAmount: Money,

    @NotNull
    var feePaymentCreditCardId: String? = "",
    var isFeePaymentBilled: Boolean = false,

    @NotNull
    var refundMethods: ArrayList<@Valid RefundMethodDto>,

    @NotNull
    var clientPaymentMethods: ArrayList<@Valid ClientPaymentMethodDto>,
    var payOfferer: Boolean = false,

    var losses: Losses? = null,

    @NotNull
    var airline: String
)
