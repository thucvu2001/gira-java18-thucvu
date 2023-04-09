package cybersoft.javabackend.java18.gira.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware") // kich hoat chuc nang Auditing trong BaseEntity
public class JpaConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    static class AuditorAwareImpl implements AuditorAware<String> {

//        @Override
//        public Optional<String> getCurrentAuditor() { // tra ve user dang login, cau hinh lai sau khi co security
//            return Optional.of("Anonymous");
//        }

        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getName().equals("anonymousUser")) {
                return Optional.of("SYSTEM");
            } else {
                return Optional.of(authentication.getName());
            }
        }
    }
}
