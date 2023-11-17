import autum.com.users.UsersApplication;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UsersApplication.class)
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    public static PostgreSQLContainer container = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:16.1")
    )
            .withDatabaseName("db")
            .withUsername("postgres")
            .withPassword("123");

    @BeforeAll
    public static void setUp() {
        container.start();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", container::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", container::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", container::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", container::getDriverClassName);
    }

    @Test
    public void findCity() throws Exception {
        var result = mockMvc.perform(get("/api/v1/city/London"))
                .andExpect(status().isOk())
                .andReturn();
        var content = result.getResponse().getContentAsString();
        assertEquals("[]", content);
    }
}