package ir.bu.Conditional.config;

import ir.bu.Conditional.profile.DevProfile;
import ir.bu.Conditional.profile.ProductionProfile;
import ir.bu.Conditional.profile.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false ")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
