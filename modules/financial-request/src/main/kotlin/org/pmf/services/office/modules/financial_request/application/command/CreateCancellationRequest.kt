package org.pmf.services.office.modules.financial_request.application.command

import org.pmf.services.office.core.value_object.Money
import org.pmf.services.office.modules.financial_request.domain.entity.Losses
import org.pmf.services.office.modules.financial_request.domain.entity.PassengerPerStretch
import org.pmf.services.office.modules.financial_request.domain.entity.RefundMethod
import java.time.ZonedDateTime

class CreateCancellationRequest(
    val externalId: String,
    val requesterId: String,
    val voucherValue: Money,
    val feeAirline: Money,
    val fee: Money,
    val feePaymentCreditCardId: String? = "",
    val feeCiaPaymentType: String? = "",
    val isPartial: Boolean = false,
    val refundMethods: ArrayList<RefundMethod>,
    val passengersPerStretch: ArrayList<PassengerPerStretch>,
    val payOfferer: Boolean?,
    val airline: String,
    val losses : Losses? = null,
    val outboundDate: ZonedDateTime,
    val totalClientPaidAmount: Money,
)
