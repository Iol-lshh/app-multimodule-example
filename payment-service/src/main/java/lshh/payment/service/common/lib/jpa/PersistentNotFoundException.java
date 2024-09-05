package lshh.payment.service.common.lib.jpa;

public class PersistentNotFoundException extends RuntimeException {
    public PersistentNotFoundException(String message) {
        super(message);
    }
}
