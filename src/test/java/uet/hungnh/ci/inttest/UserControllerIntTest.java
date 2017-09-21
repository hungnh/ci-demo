package uet.hungnh.ci.inttest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uet.hungnh.ci.CIDemoApplication;
import uet.hungnh.ci.persistence.entity.AppUser;
import uet.hungnh.ci.persistence.repository.AppUserRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CIDemoApplication.class)
@AutoConfigureWebMvc
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@AutoConfigureJsonTesters
public class UserControllerIntTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppUser> json;

    @Autowired
    private AppUserRepository userRepository;

    @After
    public void clearTestData() {
        userRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateUserSuccessful() throws Exception {
        AppUser user = createTestUserWithoutId();

        mvc.perform(post("/users")
                .content(json.write(user).getJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.username", is("hungnh")));
    }

    @Test
    public void whenValidId_thenUserShouldBeFound() throws Exception {
        AppUser user = createTestUserWithoutId();
        user.setId(1L);
        userRepository.save(user);

        mvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("hungnh")));
    }

    private AppUser createTestUserWithoutId() {
        AppUser user = new AppUser();
        user.setUsername("hungnh");
        user.setFirstName("Jon");
        user.setLastName("Snow");
        user.setEmail("hungnh@gmail.com");
        return user;
    }
}
