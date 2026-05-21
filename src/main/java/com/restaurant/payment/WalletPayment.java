package com.restaurant.payment;

public class WalletPayment implements PaymentMethod {
    private String walletId;

    public WalletPayment(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid by wallet: " + amount);
        System.out.println("Wallet Provider: " + walletId);
        // Here you would add logic to handle wallet payment, such as validating the wallet and processing the transaction
    }
}
