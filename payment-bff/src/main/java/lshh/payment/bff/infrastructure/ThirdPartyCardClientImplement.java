package lshh.payment.bff.infrastructure;

import lshh.payment.bff.domain.component.ThirdPartyCardClient;
import lshh.payment.bff.domain.dto.PayResult;
import lshh.payment.bff.domain.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyCardClientImplement implements ThirdPartyCardClient {

    @Override
    public PayResult pay(Payment payment) {
        return null;
    }
}
