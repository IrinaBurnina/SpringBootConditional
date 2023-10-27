package ir.bu.Conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ConditionalApplicationTests {
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void devProfileTest() {
        //arrange
        String expected = "Current profile is dev";
        //act
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080), String.class);
        //assert
        Assertions.assertEquals(expected, forEntity.getBody());
    }

    @Test
    void prodProfileTest() {
        //arrange
        String expected = "Current profile is production";
        //act
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081), String.class);
        //assert
        Assertions.assertEquals(expected, forEntity.getBody());
    }
}