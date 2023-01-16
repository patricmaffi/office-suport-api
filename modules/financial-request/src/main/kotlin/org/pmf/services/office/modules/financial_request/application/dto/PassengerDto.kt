package org.pmf.services.office.modules.financial_request.application.dto

import javax.validation.constraints.NotNull

class PassengerDto(
    @NotNull
    val id: String? = null,

    @NotNull
    val name: String? = null
)
