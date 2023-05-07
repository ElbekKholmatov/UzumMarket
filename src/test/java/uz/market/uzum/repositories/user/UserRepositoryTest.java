//package uz.market.uzum.repositories.user;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import uz.market.uzum.domains.user.User;
//
//import java.util.Collection;
//import java.util.Optional;
//
//@DataJpaTest
//@ActiveProfiles("test")
//public class UserRepositoryTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Test
//    public void testFindByEmail() {
//        // given
//        User user = new User();
//        user.setEmail("test@test.com");
//        userRepository.save(user);
//
//        // when
//        User foundUser = userRepository.findByEmail("test@test.com");
//
//        // then
//        Assertions.assertEquals(user.getEmail(), foundUser.getEmail());
//    }
//
//    @Test
//    public void testCheckUniqueFields() {
//        // given
//        User user = new User();
//        user.setEmail("test@test.com");
//        userRepository.save(user);
//
//        // when
//        Optional<User> foundUser = userRepository.checkUniqueFields("test@test.com");
//
//        // then
//        Assertions.assertTrue(foundUser.isPresent());
//        Assertions.assertEquals(user.getEmail(), foundUser.get().getEmail());
//    }
//
//    @Test
//    public void testFindByEmailLike() {
//        // given
//        User user = new User();
//        user.setEmail("test@test.com");
//        userRepository.save(user);
//
//        // when
//        Optional<User> foundUser = userRepository.findByEmailLike("%test%");
//
//        // then
//        Assertions.assertTrue(foundUser.isPresent());
//        Assertions.assertEquals(user.getEmail(), foundUser.get().getEmail());
//    }
//
//    @Test
//    public void testFindAllUserDetails() {
//        // given
//        User user1 = new User();
//        user1.setEmail("test1@test.com");
//        userRepository.save(user1);
//
//        User user2 = new User();
//        user2.setEmail("test2@test.com");
//        userRepository.save(user2);
//
//        // when
//        Optional<Collection<User>> users = userRepository.findAllUserDetails();
//
//        // then
//        Assertions.assertTrue(users.isPresent());
//        Assertions.assertEquals(2, users.get().size());
//    }
//}
