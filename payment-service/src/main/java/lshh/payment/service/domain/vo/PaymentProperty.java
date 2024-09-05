package lshh.payment.service.domain.vo;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lshh.modules.money.MoneyConverter;
import org.javamoney.moneta.Money;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PaymentProperty {
    @Convert(converter = MoneyConverter.class)
    private Money amount;
}
