import autum.com.users.UsersApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UsersApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmokeTest {

    @Autowired
    private TestRestTemplate rest;
    @LocalServerPort
    private int port;

    public static PostgreSQLContainer container = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:16.1")
    )
            .withDatabaseName("db")
            .withUsername("postgres")
            .withPassword("123");

    @BeforeAll
    public static void setUp() {
        try {
            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", container::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", container::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", container::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", container::getDriverClassName);
    }


    @Test
    public void checkAvailabilityService() {
        var identifier = "VFTURE1234VCD453IUUR675USFF439FG";
        var url = "http://localhost:" + port + "/api/v1/users/" + identifier;
        var response = rest.getForEntity(url, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
}