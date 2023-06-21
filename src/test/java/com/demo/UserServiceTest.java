package com.demo;

import com.demo.domain.Role;
import com.demo.domain.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeAll
    public void setUp() throws Exception {
        Optional<User> kyawswar = Optional.of(User.builder()
                        .email("kyawswaraung@gmail.com")
                        .id(1L)
                        .userName("kyawswar")
                        .firstName("kyaw")
                        .lastName("swar")
                        .imageUrl("http://localhost/image")
                        .role(Role.ROLE_ADMIN)
                        .password("12345")
                        .build());

        Optional<User> mgmg = Optional.of(User.builder()
                .email("mgmg@gmail.com")
                .id(2L)
                .userName("mgmg")
                .firstName("mg")
                .lastName("mg")
                .imageUrl("http://localhost/image")
                .role(Role.ROLE_USER)
                .password("123456")
                .build());

        MockitoAnnotations.openMocks(this);
        when(userRepository.findByUserName("kyawswar"))
                .thenReturn(kyawswar);
        when(userRepository.findByUserName("mgmg"))
                .thenReturn(mgmg);
    }

    @Test
    public void findOneByUserNameTest() {
        Optional<User> user = userService.findOneByUserName("kyawswar");
        Assertions.assertTrue(
                user.isPresent()
                        && user.get().getEmail().equals("kyawswaraung@gmail.com")
        );

        Optional<User> user2 = userService.findOneByUserName("mgmg");
        Assertions.assertTrue(
                user2.isPresent()
                        && user2.get().getEmail().equals("mgmg@gmail.com")
        );
    }
}
