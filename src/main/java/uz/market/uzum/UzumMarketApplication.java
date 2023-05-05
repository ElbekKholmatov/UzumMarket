package uz.market.uzum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.market.uzum.repositories.user.UserRepository;
import uz.market.uzum.repositories.user.UserRolesRepository;

@SpringBootApplication
public class UzumMarketApplication {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public UzumMarketApplication(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UzumMarketApplication.class, args);
    }

    /*
    todo 1  Elbek order, payment,
    todo 2 Elshod Category product crud test
    todo 3 Abdulloh AKA -  Javohir   user registr qiganda usha userga basket ochilishi kerak
    todo 4 Fayzulloh MR.Xolms Basket bilan ishlash
    todo 5 Fayzullo BROTHER  comment  bilan ishlash
    todo 6 Muhammad  Destionion point  bilan ishlash
    todo 7 Uktam  va Mirsaid  bulib tulashni tushunib qilib kelish sorry guys  databaseda ham saqlab ketishi  kerak
    todo 9 Asrorkhuja rasmlarni  cloudga saqlashni Document CRUD  Event Listenerni ishalatib yozing

* */

}
