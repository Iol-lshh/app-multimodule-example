package lshh.payment.service.domain.dto;

import java.util.List;

public record PaymentListView (
        List<PaymentStateView> payments
){

}
