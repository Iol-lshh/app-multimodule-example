package lshh.payment.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "lshh.modules.client")
@SpringBootApplication
public class PaymentBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentBffApplication.class, args);
	}
}
