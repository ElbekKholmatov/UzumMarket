package uz.market.uzum;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition
@EnableJpaRepositories
@EnableCaching
public class UzumMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(UzumMarketApplication.class, args);
    }

}
