package org.pmf.services.office.modules.financial_request.application.dto

import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.domain.entity.Losses
import org.pmf.services.office.modules.financial_request.domain.entity.PassengerPerStretch
import java.time.ZonedDateTime
import javax.validation.Valid
import javax.validation.constraints.NotNull

class CreateCancellationRequestDto(
    @NotNull
    var fee: Money,

    @NotNull
    var feeAirline: Money,

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
    var feeCiaPaymentType: String? = "",
    var isPartial: Boolean = false,

    @NotNull
    var passengersPerStretch: ArrayList<@Valid PassengerPerStretch>,

    @NotNull
    var refundMethods: ArrayList<@Valid RefundMethodDto>,

    var payOfferer: Boolean? = false,

    var losses: Losses? = null,

    @NotNull
    var airline: String,

    val outboundDate: ZonedDateTime,
)
