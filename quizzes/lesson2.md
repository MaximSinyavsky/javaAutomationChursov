# Quiz lesson2
## 1. Аннотации
Аннотации в JUnit 5 — это метаданные, которые определяют поведение тестов, их жизненный цикл и конфигурацию. Они используются для пометки тестовых методов, классов, setup/teardown действий и других настроек. Основные аннотации находятся в пакете org.junit.jupiter.api.
### 1. @Test
Описание: Помечает метод как тестовый. JUnit 5 запускает его как отдельный тест. Используй assertions (например, assertEquals, assertTrue) для проверки результатов.

        import static org.junit.jupiter.api.Assertions.assertEquals;
        
        public class CalculatorTest {
            @Test
            void testAdd() {
                Calculator calc = new Calculator();
                assertEquals(5, calc.add(2, 3));
            }
        }
### 2. @BeforeEach
Описание: Выполняется перед каждым @Test. Полезно для подготовки данных или объектов.
Пример (включён в общий пример ниже).
### 3. @AfterEach
Описание: Выполняется после каждого @Test. Для очистки ресурсов.
### 4. @BeforeAll
Описание: Выполняется один раз перед всеми тестами в классе. Метод должен быть static.
### 5. @AfterAll
Описание: Выполняется один раз после всех тестов. Тоже static.
### 6. @DisplayName
Описание: Задаёт читаемое имя теста или класса для отчётов.
### 7. @Disabled
Описание: Отключает тест или класс, чтобы он не запускался.
### 8. @TestFactory
Описание: Помечает метод, который создаёт динамические тесты (не путать с @Test). Возвращает коллекцию тестов (например, DynamicTest). Используется для сложных сценариев, где тесты генерируются во время выполнения.

    import org.junit.jupiter.api.TestFactory;
    import java.util.Arrays;
    import java.util.Collection;
    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.DynamicTest.dynamicTest;
    
    public class DynamicTestExample {
        @TestFactory
        Collection<DynamicTest> dynamicTests() {
            Calculator calc = new Calculator();
            return Arrays.asList(
                dynamicTest("Test add 2 + 3", () -> assertEquals(5, calc.add(2, 3))),
                dynamicTest("Test add -1 + 1", () -> assertEquals(0, calc.add(-1, 1)))
            );
        }
    }
Здесь метод возвращает список тестов, которые JUnit выполнит. Это полезно, если тесты зависят от внешних данных (например, из файла).
### 9. @Nested
Описание: Позволяет создавать вложенные тестовые классы внутри основного. Удобно для группировки связанных тестов, чтобы код был структурирован.

      import org.junit.jupiter.api.Test;
      import static org.junit.jupiter.api.Assertions.assertEquals;
      
      public class NestedTestExample {
          @Nested
          class AdditionTests {
              Calculator calc = new Calculator();
          @Test
          void testAddPositive() {
              assertEquals(5, calc.add(2, 3));
          }
  
          @Test
          void testAddNegative() {
              assertEquals(-1, calc.add(2, -3));
          }
      }
    }
В отчёте тесты будут сгруппированы под AdditionTests, что улучшает читаемость.
### 10. @Tag
Описание: Позволяет пометить тесты тегами для выборочного запуска. Например, можно запускать только тесты с тегом "smoke" или "integration".

    import org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertEquals;
    
    public class TaggedTestExample {
        @Test
        @Tag("smoke")
        void testAdd() {
            Calculator calc = new Calculator();
            assertEquals(5, calc.add(2, 3));
        }
    }
Запустить тесты с тегом "smoke" можно через Maven: mvn test -Dtest.groups=smoke.
### 11. @Timeout
Описание: Устанавливает максимальное время выполнения теста. Если тест превышает время, он падает с ошибкой.

    import org.junit.jupiter.api.Timeout;
    import java.util.concurrent.TimeUnit;
    
    public class TimeoutTestExample {
        @Test
        @Timeout(value = 1, unit = TimeUnit.SECONDS)
        void testWithTimeout() throws InterruptedException {
            Thread.sleep(2000); // Тест упадёт, так как спит 2 секунды
        }
    }
Здесь тест завершится с ошибкой, если выполняется дольше 1 секунды.

## 2. Параметризированные тесты в JUnit 5
Параметризированные тесты позволяют запускать один и тот же тест с разными входными данными. Это удобно, чтобы не писать отдельные методы для каждого набора данных. Для этого нужны специальные аннотации из пакета org.junit.jupiter.params.
Чтобы использовать параметризированные тесты, добавь зависимость в pom.xml:

    xml<dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-params</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
    Создадим класс StringUtils для демонстрации:textpublic class StringUtils {
        public boolean isPalindrome(String str) {
            if (str == null) return false;
            String clean = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            return clean.equals(new StringBuilder(clean).reverse().toString());
        }
    }
Теперь разберём аннотации для параметризированных тестов.
### 1. @ParameterizedTest
Описание: Помечает метод как параметризированный тест. Вместо @Test используется @ParameterizedTest, и метод должен принимать параметры. Источник данных указывается отдельной аннотацией (например, @ValueSource).

    import org.junit.jupiter.params.provider.ValueSource;
    import static org.junit.jupiter.api.Assertions.assertTrue;
    
    public class ParameterizedTestExample {
        private StringUtils utils = new StringUtils();

    @ParameterizedTest
    @ValueSource(strings = {"radar", "level", "A man a plan a canal Panama"})
    void testIsPalindrome(String input) {
        assertTrue(utils.isPalindrome(input));
        }
    }
Здесь тест testIsPalindrome запустится трижды с разными строками: "radar", "level", "A man a plan a canal Panama".
### 2. @ValueSource
Описание: Простейший источник данных для @ParameterizedTest. Поддерживает примитивы (int, double), строки и классы. Передаёт один параметр за раз.
Пример (уже показан выше). Можно также использовать для чисел:

    import org.junit.jupiter.params.provider.ValueSource;
    import static org.junit.jupiter.api.Assertions.assertEquals;
    
    public class ParameterizedNumberTest {
        private Calculator calc = new Calculator();

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3})
        void testAddWithOne(int number) {
            assertEquals(number + 1, calc.add(number, 1));
        }
    }
### 3. @CsvSource
Описание: Позволяет передать несколько параметров в тест, используя CSV-формат. Каждая строка — это набор входных данных.

    import org.junit.jupiter.params.provider.CsvSource;
    import static org.junit.jupiter.api.Assertions.assertEquals;
    
    public class CsvSourceTestExample {
        private Calculator calc = new Calculator();

        @ParameterizedTest
        @CsvSource({
            "1, 2, 3",
            "0, 5, 5",
            "-1, 1, 0"
        })
        void testAdd(int a, int b, int expected) {
            assertEquals(expected, calc.add(a, b));
        }
    }
Каждая строка в @CsvSource задаёт три значения: два входных и ожидаемый результат.
### 4. @MethodSource
Описание: Позволяет использовать метод в том же классе как источник данных. Метод должен возвращать Stream, Iterable или массив.

    import org.junit.jupiter.params.provider.MethodSource;
    import static org.junit.jupiter.api.Assertions.assertTrue;
    import java.util.stream.Stream;
    
    public class MethodSourceTestExample {
        private StringUtils utils = new StringUtils();
    
        @ParameterizedTest
        @MethodSource("palindromeProvider")
        void testIsPalindrome(String input) {
            assertTrue(utils.isPalindrome(input));
        }
    
        static Stream<String> palindromeProvider() {
            return Stream.of("radar", "level", "deified");
        }
    }
Метод palindromeProvider возвращает поток строк, которые используются в тесте.
### 5. @CsvFileSource
Описание: Читает данные для теста из CSV-файла. Полезно, если данных много, и их удобнее хранить отдельно.

      Пример (нужен файл palindromes.csv в src/test/resources):textinput
      radar
      level
      deifiedtextimport org.junit.jupiter.params.ParameterizedTest;
      import org.junit.jupiter.params.provider.CsvFileSource;
      import static org.junit.jupiter.api.Assertions.assertTrue;
      
      public class CsvFileSourceTestExample {
          private StringUtils utils = new StringUtils();

    @ParameterizedTest
    @CsvFileSource(resources = "/palindromes.csv", numLinesToSkip = 1)
    void testIsPalindrome(String input) {
        assertTrue(utils.isPalindrome(input));
    }
    }
numLinesToSkip = 1 пропускает заголовок в CSV-файле.

Полный пример с несколькими аннотациями
Вот пример, объединяющий несколько аннотаций, чтобы показать их взаимодействие:textimport org.junit.jupiter.api.*;

    import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.CsvSource;
    import static org.junit.jupiter.api.Assertions.*;
    
    @DisplayName("Тесты для калькулятора")
    public class ComprehensiveTestExample {
        private static Calculator calc;

    @BeforeAll
    static void globalSetUp() {
        calc = new Calculator();
        System.out.println("Global setup completed");
    }

    @AfterAll
    static void globalTearDown() {
        calc = null;
        System.out.println("Global cleanup completed");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Preparing test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning up after test...");
    }

    @Test
    @DisplayName("Проверка сложения положительных чисел")
    void testAddPositive() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    @Disabled("Временно отключено")
    void testDisabled() {
        fail("This test should not run");
    }

    @ParameterizedTest
    @DisplayName("Параметризированный тест сложения")
    @CsvSource({
        "1, 2, 3",
        "0, 5, 5",
        "-1, 1, 0"
    })
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }

    @Nested
    @DisplayName("Тесты для отрицательных чисел")
    class NegativeNumberTests {
        @Test
        void testAddNegative() {
            assertEquals(-5, calc.add(-2, -3));
        }
    }
    }

## 3. Основные методы Assertions в JUnit 5
Класс org.junit.jupiter.api.Assertions содержит статические методы для проверки условий. Если проверка не проходит, выбрасывается исключение AssertionFailedError, и тест считается проваленным. Вот список самых популярных assertions с описанием и примерами.
### 1. assertEquals(expected, actual)
Описание: Проверяет, что фактическое значение (actual) равно ожидаемому (expected). Работает с примитивами, объектами, массивами и т.д. Если объекты сравниваются, вызывается метод equals().
Параметры:

expected — ожидаемое значение.
actual — фактическое значение.
message (опционально) — сообщение, которое выводится при провале теста.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertEquals;
    
    public class AssertEqualsTest {
        private Calculator calc = new Calculator();
    
        @Test
        void testAdd() {
            int result = calc.add(2, 3);
            assertEquals(5, result, "2 + 3 должно быть равно 5");
        }
    }
Если result не равен 5, тест упадёт с сообщением "2 + 3 должно быть равно 5".
### 2. assertNotEquals(unexpected, actual)
Описание: Проверяет, что фактическое значение не равно неожиданному. Противоположность assertEquals.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertNotEquals;
    
    public class AssertNotEqualsTest {
        private Calculator calc = new Calculator();
    
        @Test
        void testAddNotEqual() {
            int result = calc.add(2, 3);
            assertNotEquals(6, result, "2 + 3 не должно быть равно 6");
        }
    }
### 3. assertTrue(condition)
Описание: Проверяет, что условие возвращает true.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertTrue;
    
    public class AssertTrueTest {
        private StringUtils utils = new StringUtils();
    
        @Test
        void testIsPalindrome() {
            assertTrue(utils.isPalindrome("radar"), "Строка 'radar' должна быть палиндромом");
        }
    }
Класс StringUtils (как в прошлом ответе) проверяет, является ли строка палиндромом.
### 4. assertFalse(condition)
Описание: Проверяет, что условие возвращает false.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertFalse;
    
    public class AssertFalseTest {
        private StringUtils utils = new StringUtils();
    
        @Test
        void testNotPalindrome() {
            assertFalse(utils.isPalindrome("hello"), "Строка 'hello' не должна быть палиндромом");
        }
    }
### 5. assertNull(actual)
Описание: Проверяет, что значение равно null.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertNull;
    
    public class AssertNullTest {
        private StringUtils utils = new StringUtils();
    
        @Test
        void testNullInput() {
            assertNull(utils.isPalindrome(null), "Палиндром для null должен вернуть null");
        }
    }
Примечание: В нашем StringUtils метод isPalindrome возвращает false для null, так что тест здесь для примера. В реальном коде нужно адаптировать под поведение метода.
### 6. assertNotNull(actual)
Описание: Проверяет, что значение не равно null.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertNotNull;
    
    public class AssertNotNullTest {
        @Test
        void testNotNull() {
            Calculator calc = new Calculator();
            assertNotNull(calc, "Объект калькулятора не должен быть null");
        }
    }
### 7. assertThrows(expectedType, executable)
Описание: Проверяет, что код выбрасывает ожидаемое исключение. executable — это лямбда-выражение, содержащее код, который должен выбросить исключение.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertThrows;
    
    public class AssertThrowsTest {
        private Calculator calc = new Calculator();
    
        @Test
        void testDivideByZero() {
            assertThrows(ArithmeticException.class, () -> calc.divide(10, 0), "Деление на ноль должно выбросить ArithmeticException");
        }
    }
    Добавим метод divide в Calculator:textpublic class Calculator {
        public int add(int a, int b) {
            return a + b;
        }
    
        public int divide(int a, int b) {
            return a / b; // Выбрасывает ArithmeticException при b = 0
        }
    }
### 8. assertDoesNotThrow(executable)
Описание: Проверяет, что код не выбрасывает исключений.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
    
    public class AssertDoesNotThrowTest {
        private Calculator calc = new Calculator();
    
        @Test
        void testValidDivision() {
            assertDoesNotThrow(() -> calc.divide(10, 2), "Деление 10 на 2 не должно выбросить исключение");
        }
    }
### 9. assertArrayEquals(expected, actual)
Описание: Проверяет, что два массива равны (сравнивает элементы и их порядок).

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertArrayEquals;
    
    public class AssertArrayEqualsTest {
        @Test
        void testArrayEquality() {
            int[] expected = {1, 2, 3};
            int[] actual = {1, 2, 3};
            assertArrayEquals(expected, actual, "Массивы должны быть равны");
        }
    }
### 10. assertIterableEquals(expected, actual)
Описание: Проверяет, что два объекта Iterable (например, списки) содержат одинаковые элементы в том же порядке.

    Пример:textimport org.junit.jupiter.api.Test;
    import java.util.Arrays;
    import static org.junit.jupiter.api.Assertions.assertIterableEquals;
    
    public class AssertIterableEqualsTest {
        @Test
        void testListEquality() {
            Iterable<Integer> expected = Arrays.asList(1, 2, 3);
            Iterable<Integer> actual = Arrays.asList(1, 2, 3);
            assertIterableEquals(expected, actual, "Списки должны быть равны");
        }
    }
### 11. assertAll(executables)
Описание: Группирует несколько assertions. Все проверки выполняются, даже если некоторые падают, и в отчёте показываются все ошибки.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertAll;
    import static org.junit.jupiter.api.Assertions.assertEquals;
    
    public class AssertAllTest {
        private Calculator calc = new Calculator();
    
        @Test
        void testMultipleAssertions() {
            assertAll("Проверка нескольких условий",
                () -> assertEquals(5, calc.add(2, 3), "2 + 3 должно быть 5"),
                () -> assertEquals(0, calc.add(-1, 1), "-1 + 1 должно быть 0")
            );
        }
    }
Если оба assertions упадут, ты увидишь обе ошибки в отчёте.
### 12. assertTimeout(duration, executable)
Описание: Проверяет, что код выполняется в пределах указанного времени. Если время превышено, тест падает.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertTimeout;
    import java.time.Duration;
    
    public class AssertTimeoutTest {
        @Test
        void testTimeout() {
            assertTimeout(Duration.ofSeconds(1), () -> {
                Thread.sleep(500); // Выполняется за 0.5 секунды
            });
        }
    }
Если код выполняется дольше 1 секунды, тест упадёт.
### 13. assertTimeoutPreemptively(duration, executable)
Описание: Похоже на assertTimeout, но прерывает выполнение кода, если время превышено. Используется для жёсткого контроля времени.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
    import java.time.Duration;
    
    public class AssertTimeoutPreemptivelyTest {
        @Test
        void testTimeoutPreemptively() {
            assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
                Thread.sleep(2000); // Прервётся после 1 секунды
            });
        }
    }
### 14. fail(message)
Описание: Принудительно завершает тест с ошибкой. Полезно для тестирования незавершённых методов или временного провала теста.

    Пример:textimport org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.fail;
    
    public class FailTest {
        @Test
        void testFail() {
            fail("Тест не реализован");
        }
    }































## 1. Какая аннотация используется для того, чтобы пропустить тест в JUnit 5?
## 2. Какая аннотация позволяет задать читаемое имя для теста в JUnit 5?
## 3. Какой метод используется для проверки того, что два значения равны в JUnit 5?
## 4. Какой метод используется для проверки, что выбрасывается исключение в JUnit 5?
## 5. Какую аннотацию следует использовать для выполнения действия перед каждым тестом?
## 6. Какая аннотация используется для указания, что тест должен быть выполнен несколько раз?
## 7. Какой файл нужно создать для настройки параллельного запуска тестов в JUnit 5?
## 8. Какая аннотация используется для добавления расширений к тестам в JUnit 5?
## 9. 10. Какую команду следует использовать для запуска тестов определенного класса с помощью Gradle?
