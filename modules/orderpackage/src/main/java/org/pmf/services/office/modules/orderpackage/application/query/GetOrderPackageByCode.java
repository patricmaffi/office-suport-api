package org.pmf.services.office.modules.orderpackage.application.query;

public class GetOrderPackageByCode implements Query {

    private final Integer code;

    public GetOrderPackageByCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
