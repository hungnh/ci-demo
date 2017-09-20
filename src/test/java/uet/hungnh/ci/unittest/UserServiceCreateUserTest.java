package uet.hungnh.ci.unittest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import uet.hungnh.ci.persistence.entity.AppUser;
import uet.hungnh.ci.persistence.repository.AppUserRepository;
import uet.hungnh.ci.service.IUserService;
import uet.hungnh.ci.service.impl.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceCreateUserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Bean
        public IUserService userService() {
            return new UserService();
        }
    }

    @Autowired
    protected IUserService userService;

    @MockBean
    protected AppUserRepository userRepository;

    private static final AppUser ROBB = new AppUser();

    @Before
    public void setUp() {
        ROBB.setUsername("robb");
        ROBB.setFirstName("Robb");
        ROBB.setLastName("Stark");
        ROBB.setEmail("robb@gmail.com");

        AppUser savedRobb = new AppUser();
        savedRobb.setId(1L);
        savedRobb.setUsername("robb");
        savedRobb.setFirstName("Robb");
        savedRobb.setLastName("Stark");
        savedRobb.setEmail("robb@gmail.com");

        Mockito.when(userRepository.save(ROBB)).thenReturn(savedRobb);
    }

    @Test
    public void createUserSuccessful() {
        AppUser created = userService.create(ROBB);

        assertThat(created).isNotNull();
        assertThat(created.getId()).isEqualTo(1L);
        assertThat(created.getUsername()).isEqualTo("robb");
    }
}
