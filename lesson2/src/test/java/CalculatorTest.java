import org.junit.jupiter.api.Test;
import test.company.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void testAdd(){
        Calculator calculator = new Calculator();
                assertEquals(5, calculator.add(3,2));
    }
}
