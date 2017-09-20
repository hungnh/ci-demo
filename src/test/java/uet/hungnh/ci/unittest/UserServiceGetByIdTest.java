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
import uet.hungnh.ci.common.exception.ExceptionMessage;
import uet.hungnh.ci.common.exception.ServiceException;
import uet.hungnh.ci.persistence.entity.AppUser;
import uet.hungnh.ci.persistence.repository.AppUserRepository;
import uet.hungnh.ci.service.IUserService;
import uet.hungnh.ci.service.impl.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceGetByIdTest {
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

    private static final long USER_ID = 1L;
    private static final String USERNAME = "hungnh";

    private static final long INVALID_USER_ID = 2L;

    @Before
    public void setUp() {
        AppUser jon = new AppUser();
        jon.setId(USER_ID);
        jon.setUsername(USERNAME);
        jon.setFirstName("Jon");
        jon.setLastName("Snow");
        jon.setEmail("hungnh.uet@gmail.com");

        Mockito.when(userRepository.findOne(USER_ID)).thenReturn(jon);
        Mockito.when(userRepository.findOne(INVALID_USER_ID)).thenReturn(null);
    }

    @Test
    public void whenValidID_thenUserShouldBeFound() throws ServiceException {
        AppUser found = userService.getById(USER_ID);

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(USER_ID);
        assertThat(found.getUsername()).isEqualTo(USERNAME);
    }

    @Test
    public void whenInvalidID_thenExceptionShouldBeThrown() throws ServiceException {
        thrown.expect(ServiceException.class);
        thrown.expectMessage(ExceptionMessage.USER_NOT_FOUND.getMessage());

        userService.getById(INVALID_USER_ID);
    }
}
