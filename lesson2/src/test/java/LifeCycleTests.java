import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LifeCycleTests {
    @BeforeEach
    void setUp() {
        // Здесь размещается код подготовки перед каждым тестом
        System.out.println("Перед каждым тестом");
    }

    @AfterEach
    void tearDown() {
        //Здесь размещается код очистки после каждого теста
        System.out.println("После каждого теста");
    }

    @BeforeAll
    void setUpAll() {
        //Здесь размещается код подготовки перед всеми тестами
        System.out.println("Перед всеми тестами");
    }

    @AfterAll
    void tearDownAll() {
        //
        System.out.println("После всех тестов");
    }

    @Test
    @DisplayName("Первый тест")
    @Order(1)
    void first() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
        System.out.println("Первый тест прошел");
    }

    @Test
    @DisplayName("Третий тест")
    void third() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
        System.out.println("Третий тест прошел");
    }

    @Test
    @DisplayName("Второй тест")
    @Order(2)
    void second() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
        System.out.println("Второй тест прошел");
    }
}