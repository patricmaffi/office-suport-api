package org.pmf.services.office.modules.financial_request.application.dto

import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime
import javax.validation.constraints.NotNull

class ClientPaymentMethodDto(
    @NotNull
    val amount: Money,

    @NotNull
    val type: ClientPaymentMethodTypeDto,

    val paidAt: ZonedDateTime?,

    val receivingBankId: String?,
)
