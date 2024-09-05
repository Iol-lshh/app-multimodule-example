package lshh.payment.service.common.config;

import lshh.modules.clock.ClockManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {
    @Bean
    public ClockManager clockManager(){
        return ClockManager.of();
    }
}
