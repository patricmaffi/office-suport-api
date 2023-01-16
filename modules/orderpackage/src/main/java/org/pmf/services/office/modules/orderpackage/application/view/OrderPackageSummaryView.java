package org.pmf.services.office.modules.orderpackage.application.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPackageSummaryView implements View {

    private String orderPackageCode;
    private UUID id;
    private String company;
    private Map<String, Object> metadata;
    private BigDecimal totalSell;
    private BigDecimal totalPurchase;
    private BigDecimal subTotal;
    private BigDecimal total;
    private BigDecimal totalAdjustments;
    private String idTransaction;

    public OrderPackageSummaryView withId(UUID id) {
        this.id = id;
        return this;
    }

    public OrderPackageSummaryView withId(Integer code) {
        this.orderPackageCode = "OP-" + code;
        return this;
    }

    public OrderPackageSummaryView withCompany(String company) {
        this.company = company;
        return this;
    }

    public OrderPackageSummaryView withTotalSell(BigDecimal totalSell) {
        this.totalSell = totalSell;
        return this;
    }

    public OrderPackageSummaryView withTotalPurchase(BigDecimal totalPurchase) {
        this.totalPurchase = totalPurchase;
        return this;
    }

    public OrderPackageSummaryView withSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
        return this;
    }
    public OrderPackageSummaryView withTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public OrderPackageSummaryView withTotalAdjustments(BigDecimal totalAdjustments) {
        this.totalAdjustments = totalAdjustments;
        return this;
    }

    public OrderPackageSummaryView withIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
        return this;
    }

    public OrderPackageSummaryView withMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getOrderPackageCode() {
        return orderPackageCode;
    }

    public String getCompany() {
        return company;
    }

    public BigDecimal getTotalSell() {
        return totalSell;
    }

    public BigDecimal getTotalPurchase() {
        return totalPurchase;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getTotal() {return subTotal;}

    public BigDecimal getTotalAdjustments() {
        return totalAdjustments;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public String getIdTransaction() {return idTransaction;}
}
