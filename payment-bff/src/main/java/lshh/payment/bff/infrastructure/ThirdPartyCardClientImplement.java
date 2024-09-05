package lshh.payment.bff.infrastructure;

import lshh.modules.task.Result;
import lshh.payment.bff.domain.component.ThirdPartyCardClient;
import lshh.payment.bff.domain.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyCardClientImplement implements ThirdPartyCardClient {

    @Override
    public Result pay(Payment payment) {
        return null;
    }
}
