package lshh.payment.bff.common.config;

import lshh.modules.apikey.ApiKeySellerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApikeyConfig {
    @Bean
    ApiKeySellerProvider mockApiKeySellerProvider() {
        return new ApiKeySellerProvider() {
            @Override
            public String getSellerId(String apiKeyValue) {
                return "good-seller";
            }
        };
    }
}
