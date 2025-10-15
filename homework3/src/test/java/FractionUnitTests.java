import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.company.Fraction;

import static org.testng.Assert.assertEquals;

public class FractionUnitTests {


    @BeforeMethod
    void setUp() {

    }

    @Test(description = "Проверить конструктор",
            testName = "Проверить метод getNum и getDen")
    void constructorGetTest() {
        Fraction fraction = new Fraction(3, 4);
        assertEquals(fraction.getNum(), 3);
        assertEquals(fraction.getDen(), 4);
    }

    @Test(description = "Проверить конструктор",
            testName = "Проверить исключение при num = null",
            expectedExceptions = NullPointerException.class)
    void constructorNumNullTest() {
        new Fraction(null, 10);
    }

    @Test(description = "Проверить конструктор",
            testName = "Проверить исключение при den = null",
            expectedExceptions = NullPointerException.class)
    void constructorDenNullTest() {
        new Fraction(10, null);
    }

    @Test(description = "Проверить конструктор",
            testName = "Проверить исключение при den = 0",
            expectedExceptions = ArithmeticException.class)
    void constructorDenZeroTest() {
        new Fraction(10, 0);
    }


}
