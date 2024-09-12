package lshh.payment.bff.common.config;

import lshh.modules.apikey.ApiKeyFilter;
import lshh.modules.apikey.ApiKeySellerProvider;
import lshh.modules.clock.ClockManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public ApiKeyFilter apiKeyFilter(ClockManager clockManager, ApiKeySellerProvider apiKeySellerProvider) {
        return new ApiKeyFilter(clockManager, apiKeySellerProvider);
    }
}
