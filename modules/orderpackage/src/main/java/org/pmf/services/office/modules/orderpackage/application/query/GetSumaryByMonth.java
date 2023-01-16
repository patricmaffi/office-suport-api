package org.pmf.services.office.modules.orderpackage.application.query;

import org.springframework.data.domain.Pageable;

import java.util.Date;

public class GetSumaryByMonth implements Query {

    private final Date month;
    private final Pageable pageable;

    public GetSumaryByMonth(Date month, Pageable pageable)
    {
        this.month = month;
        this.pageable = pageable;
    }

    public Date getMonth() {
        return month;
    }

    public Pageable getPageable() { return this.pageable; }
}
