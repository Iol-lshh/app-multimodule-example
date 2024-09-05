package lshh.payment.service.domain.component;


import lshh.payment.service.domain.dto.PaymentListView;
import lshh.payment.service.domain.dto.PaymentStateView;

import java.util.UUID;

public interface PaymentViewer {
    PaymentStateView readPayment(UUID paymentId);

    PaymentListView readBySellerId(String sellerId);
}
