package org.pmf.services.office.modules.financial_request.domain.exception

class RefundAmountExceededTransactionTotalValueException :
    RuntimeException("Total refund amount exceeds total transaction amount.")
