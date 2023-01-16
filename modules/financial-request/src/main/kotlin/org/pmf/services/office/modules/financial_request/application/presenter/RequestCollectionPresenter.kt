package org.pmf.services.office.modules.financial_request.application.presenter

import org.pmf.services.office.modules.financial_request.application.view.RequestView
import org.pmf.services.office.modules.financial_request.domain.entity.Request

class RequestCollectionPresenter {
    companion object {
        fun present(requests: List<Request>): List<RequestView> {
            return requests.map { request ->
                RequestPresenter.present(request)
            }
        }
    }
}
