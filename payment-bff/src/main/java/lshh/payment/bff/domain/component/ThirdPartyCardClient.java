package lshh.payment.bff.domain.component;

import lshh.payment.bff.domain.dto.PayResult;
import lshh.payment.bff.domain.entity.Payment;

public interface ThirdPartyCardClient {
    PayResult pay(Payment payment);
}
