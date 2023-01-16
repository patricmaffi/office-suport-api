package org.pmf.services.office.modules.integration.application.query;

import java.util.Date;

public class GetIntegrationResultCollectionByPeriod implements Query {

    private final Date start;
    private final Date end;
    private final boolean onlyErrors;

    public GetIntegrationResultCollectionByPeriod(Date start, Date end, boolean onlyErrors) {
        this.start = start;
        this.end = end;
        this.onlyErrors = onlyErrors;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public boolean isOnlyErrors() {return onlyErrors;}
}
