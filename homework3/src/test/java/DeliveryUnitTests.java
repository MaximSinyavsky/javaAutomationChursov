import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.company.Delivery;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

public class DeliveryUnitTests {
    private Delivery delivery;

    @BeforeSuite
    void setUp() {
        delivery = new Delivery();
    }

    @Test(description = "Проверка метода deliveryCost", testName = "Проверка расчёта стоимости доставки")
    void deliveryCostTest() {
        assertEquals(delivery.deliveryCost(28, "большие", true, "повышенная загруженность"), 840.0,
                "Несоответствие стоимости доставки");
    }

    @Test(description = "Проверка метода deliveryCost", testName = "Проверка минимальной стоимости доставки")
    void minDeliverySumTest() {
        assertEquals(delivery.deliveryCost(2, "маленькие", true, "повышенная загруженность"), 400,
                "Несоответствие минимальной стоимости доставки");
    }

    @Test(description = "Проверка исключения метода deliveryCost", testName = "Проверка исключения IllegalArgumentException")
    void deliveryCostExceptionTest() {
        Exception exception = expectThrows(IllegalArgumentException.class, () -> delivery.deliveryCost(31, "большие", true, "очень высокая загруженность"));
        assertEquals(exception.getMessage(), "Хрупкие грузы нельзя возить на расстояние более 30 км");
    }

    @Test(description = "Проверка ветвления и граничных значений метода distanceDeliveryCost",
            testName = "Проверка изменения стоимости доставки в зависимости от стоимости",
    dataProvider = "delivery-distance-data")
    void distanceDeliveryCostTest(double distance, int expectedCost) {
        assertEquals(delivery.distanceDeliveryCost(distance), expectedCost,
                "Несоответствие стоимости доставки для расстояния" + distance);
    }
}
