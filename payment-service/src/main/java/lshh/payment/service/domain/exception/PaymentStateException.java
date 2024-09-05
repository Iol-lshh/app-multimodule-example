package lshh.payment.service.domain.exception;

public class PaymentStateException extends RuntimeException{
    public PaymentStateException(String message) {
        super(message);
    }
}
