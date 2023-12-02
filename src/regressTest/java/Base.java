import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class Base {

    protected static PostgreSQLContainer container = new PostgreSQLContainer<>(
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
    protected static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", container::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", container::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", container::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", container::getDriverClassName);
    }
}
