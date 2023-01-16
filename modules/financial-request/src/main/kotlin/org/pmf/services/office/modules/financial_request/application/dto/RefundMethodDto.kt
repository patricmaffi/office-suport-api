package org.pmf.services.office.modules.financial_request.application.dto

import org.pmf.services.office.core.value_object.Money
import java.time.ZonedDateTime
import javax.validation.constraints.NotNull

class RefundMethodDto(
    @NotNull
    val dueDate: ZonedDateTime,

    @NotNull
    val dueDateType: DueDateTypeDto,

    @NotNull
    val amount: Money,

    @NotNull
    val type: RefundMethodTypeDto
)
