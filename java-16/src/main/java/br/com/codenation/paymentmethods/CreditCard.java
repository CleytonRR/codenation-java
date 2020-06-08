package br.com.codenation.paymentmethods;

public class CreditCard implements PriceStrategy {

    @Override
    public Double calculate(Double price) {
        return price * PaymentsMethodValue.CREDIT_CARD;
    }
}
