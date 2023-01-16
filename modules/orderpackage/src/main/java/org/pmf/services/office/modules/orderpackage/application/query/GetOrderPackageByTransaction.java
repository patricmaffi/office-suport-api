package org.pmf.services.office.modules.orderpackage.application.query;

public class GetOrderPackageByTransaction implements Query {

    private final String idTransaction;

    public GetOrderPackageByTransaction(String code) {
        this.idTransaction = code;
    }

    public String getIdTransaction() {
        return idTransaction;
    }
}
