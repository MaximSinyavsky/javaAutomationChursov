import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.company.Fraction;
import testdata.MyDataProvider;

import static org.testng.Assert.*;

public class FractionUnitTests {
    private Fraction fraction;

    @BeforeMethod
    public void setUp() {
        fraction = new Fraction(1, 2);
    }

    @Test(description = "Проверить конструктор",
            testName = "Проверить конструктор")
    public void testConstructorValid() {
        assertEquals(fraction.getNum(), Integer.valueOf(1));
        assertEquals(fraction.getDen(), Integer.valueOf(2));
    }

    @Test(description = "Проверить конструктор",
            testName = "Проверить исключение при num = null",
            expectedExceptions = NullPointerException.class)
    public void nullPointerExceptionNumTest() {
        new Fraction(null, 10);
    }

    @Test(description = "Проверить конструктор",
            testName = "Проверить исключение при den = null",
            expectedExceptions = NullPointerException.class)
    public void nullPointerExceptionDenTest() {
        new Fraction(10, null);
    }

    @Test(description = "Проверить конструктор",
            testName = "Проверить исключение при den = 0",
            expectedExceptions = ArithmeticException.class)
    public void ArithmeticExceptionTest() {
        new Fraction(10, 0);
    }

    @Test(description = "Проверить метод sum",
            testName = "Проверить валидные значения",
            dataProvider = "sumData", dataProviderClass = MyDataProvider.class)
    public void sumValidTest(Fraction first, Fraction second, Fraction expectedResult) {
        Fraction actualResult = Fraction.sum(first, second);
        assertEquals(actualResult, expectedResult, "Суммы должны быть равны");
    }

    @Test(description = "Проверить метод sum",
            testName = "Проверить исключение RuntimeException",
            expectedExceptions = RuntimeException.class)
    public void runtimeExceptionTest() {
        Fraction first = new Fraction(1, Integer.MAX_VALUE);
        Fraction second = new Fraction(1, 2);
        Fraction.sum(first, second);
    }

    @Test(description = "Проверить метод toString",
            testName = "Проверить метод toString")
    public void toStringTest() {
        String expectedResult = "1/2";
        assertEquals(fraction.toString(), expectedResult, "Сумма должна быть равна " + "1/2");
    }

    @Test(description = "Проверить метод equals",
            testName = "Проверить одинаковые значения")
    public void equalsTest() {
        Fraction first = new Fraction(1, 2);
        Fraction second = new Fraction(1, 2);
        assertTrue(first.equals(second), "Должно быть True");
    }

    @Test(description = "Проверить метод equals",
            testName = "Проверить разные значения")
    public void equalsDifferentTest() {
        Fraction first = new Fraction(1, 2);
        Fraction second = new Fraction(2, 4);
        assertFalse(first.equals(second), "Должно быть False");
    }

    @Test(description = "Проверить метод equals",
            testName = "Проверить null значения")
    public void equalsNullTest() {
        assertFalse(fraction.equals(null), "Должно быть False");
    }

    @Test(description = "Проверить метод hashCode",
            testName = "Проверить метод hashCode")
    public void hashCodeTest() {
        Fraction first = new Fraction(1, 2);
        Fraction second = new Fraction(1, 2);
        assertEquals(first.hashCode(), second.hashCode());
    }
}
