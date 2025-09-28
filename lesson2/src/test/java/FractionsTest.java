import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import test.company.Fraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FractionsTest {
    @Test
    @Tag("Smoke")
    @DisplayName("Сложение дувх положительных чисел")
    void simpleFractionTest() {
        Fraction first = new Fraction(1, 2);
        Fraction second = new Fraction(2, 3);
        Fraction expectedSum = new Fraction(7, 6);
        assertEquals(expectedSum, Fraction.sum(first, second), "Суммы должны быть одинаковыми");
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Сложение отрицательных чисел")
    void negativeFractionTest() {
        Fraction first = new Fraction(-1, 2);
        Fraction second = new Fraction(2, 3);
        Fraction expectedSum = new Fraction(1, 6);
        assertEquals(expectedSum, Fraction.sum(first, second), "Суммы должны быть одинаковыми");
    }

    @Test
    @Tag("Negative")
    @DisplayName("Нулевой числитель")
    void zeroNumFractionTest() {
        Fraction first = new Fraction(1, 2);
        Fraction second = new Fraction(-2, 4);
        Fraction expectedSum = new Fraction(0, 8);
        assertEquals(expectedSum, Fraction.sum(first, second), "Суммы должны быть одинаковыми");
    }

    @Test
    @Tag("Negatrive")
    @DisplayName("Не заполнены числитель и знаменатель")
    void nullFractionTest() {
        assertThrows(NullPointerException.class, () -> new Fraction(null, null),
                "Должно быть выброшено исключение NullPointerExceptions");
    }

    @Test
    @Tag("Negative")
    @DisplayName("Переполнение")
    void longFractionTest() {
        Fraction first = new Fraction(1, 2);
        Fraction second = new Fraction(1, Integer.MAX_VALUE);
        assertThrows(RuntimeException.class, () -> Fraction.sum(first, second),
                "Должно быть выброшено исключение RuntimeExceptions");
    }
}
