package com.restaurant.factory;

import com.restaurant.payment.CardPayment;
import com.restaurant.payment.CashPayment;
import com.restaurant.payment.PaymentMethod;
import com.restaurant.payment.WalletPayment;

public class PaymentFactory {

    public PaymentMethod createPaymentMethod(String type) {

        if (type == null) {
            throw new IllegalArgumentException("Payment type cannot be null");
        }

        switch (type.toLowerCase()) {
            case "cash":
                return new CashPayment();

            case "card":
                return new CardPayment("1234567812345678");

            case "wallet":
                return new WalletPayment("Vodafone Cash");

            default:
                throw new IllegalArgumentException("Unknown payment method: " + type);
        }
    }
}