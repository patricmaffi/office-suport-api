package org.pmf.services.office.modules.orderpackage.domain.exception;

public class UnsupportedPaymentMethodException extends RuntimeException {

    public UnsupportedPaymentMethodException(String type) {
        super(String.format("PaymentMethod \"%s\" is not supported", type));
    }
}
