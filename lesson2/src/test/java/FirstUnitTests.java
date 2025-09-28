import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import test.company.User;

import static org.junit.jupiter.api.Assertions.*;

public class FirstUnitTests {
    @Test
    @DisplayName("Проверка суммы")
    @Tag("UnitTest")
    void sumNumbersTest(){
        //  AAA -> Arrange -> Act -> Assert (Разделяются пустой строкой)
        // Arrange
        int a = 3;
        int b = 2;
        // Act
        int actualSum = a+b;
        int expactedSum = 5;
        //Assert
        assertEquals(expactedSum, a+b);
    }
    @Test
    @DisplayName("Проверка true / false")
    void assertTrueFalseTest(){
        int a = 2+2;
        int b = 4;
        assertTrue(a == b);
        assertFalse(a !=b);
    }
    @Test
    void expectionTest(){
        String test = null;
        Exception thrown = assertThrows(NullPointerException.class, () -> test.length());
        Assertions.assertEquals("Cannot invoke \"String.length()\" because \"test\" is null", thrown.getMessage());
    }

    @Test
    void assertsAllTest(){
        User user = new User ("John", "Doe", 30);
        assertAll(
                ()-> assertEquals("John", user.getFirstName(), "Неправильное имя"),
                ()->assertEquals("Doe", user.getSecondName(), "Неправильная фамилия"),
                ()->assertEquals(30, user.getAge(), "Неправильный возраст")

        );
    }

    @Test
    void assertsAllSeparated(){
        User user = new User ("John", "Doe", 30);
        assertAll(
                ()-> assertEquals("John1", user.getFirstName(), "Неправильное имя"),
                ()->assertEquals("Doe2", user.getSecondName(), "Неправильная фамилия"),
                ()->assertEquals(31, user.getAge(), "Неправильный возраст")

        );
    }
}
