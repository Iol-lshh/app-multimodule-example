package lshh.payment.bff.domain.exception;

public class CardClientNotOkException extends RuntimeException {
    public CardClientNotOkException(String message) {
        super(message);
    }
}
