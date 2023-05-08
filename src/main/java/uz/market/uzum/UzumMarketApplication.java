package uz.market.uzum;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition
@EnableJpaRepositories
public class UzumMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(UzumMarketApplication.class, args);
    }


    /*
    todo 1 Elbek order, payment,
    todo 2 Elshod Category product crud test
    todo 3 Abdulloh AKA - work with Redis Cache integrate with Click API for payment, stress, load and performance test
    todo 4 Javohir   user registr qiganda usha userga basket ochilishi kerak
    todo 5 Fayzulloh MR.Xolms Basket bilan ishlash
    todo 6 Fayzullo BROTHER  comment  bilan ishlash
    todo 7 Muhammad  Destionion point  bilan ishlash
    todo 8 Uktam  va Mirsaid  bulib tulashni tushunib qilib kelish sorry guys  databaseda ham saqlab ketishi  kerak
    todo 9 Asrorkhuja rasmlarni  cloudga saqlashni Document CRUD  Event Listenerni ishalatib yozing
* */

}
