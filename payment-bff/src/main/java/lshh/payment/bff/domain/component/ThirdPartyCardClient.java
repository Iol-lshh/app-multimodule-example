package lshh.payment.bff.domain.component;

import lshh.modules.task.Result;
import lshh.payment.bff.domain.entity.Payment;

public interface ThirdPartyCardClient {
    Result pay(Payment payment);
}
