import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.company.Delivery;
import testdata.MyDataProvider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

public class DeliveryUnitTests {
    private Delivery delivery;

    @BeforeMethod
    public void setUp() {
        delivery = new Delivery();
    }

    @Test(description = "Проверка метода deliveryCost",
            testName = "Проверка расчёта стоимости доставки")
    public void deliveryCostTest() {
        assertEquals(delivery.deliveryCost(28, "большие", true, "повышенная загруженность"), 840.0,
                "Несоответствие стоимости доставки");
    }

    @Test(description = "Проверка метода deliveryCost",
            testName = "Проверка минимальной стоимости доставки")
    public void minDeliverySumTest() {
        assertEquals(delivery.deliveryCost(2, "маленькие", false, "повышенная загруженность"), 400,
                "Несоответствие минимальной стоимости доставки");
    }

    @Test(description = "Проверка исключения метода deliveryCost",
            testName = "Проверка исключения IllegalArgumentException")
    public void deliveryCostExceptionTest() {
        Exception exception = expectThrows(IllegalArgumentException.class, () -> delivery.deliveryCost(31, "большие", true, "очень высокая загруженность"));
        assertEquals(exception.getMessage(), "Хрупкие грузы нельзя возить на расстояние более 30 км");
    }

    @Test(description = "Проверка ветвления и граничных значений метода distanceDeliveryCost",
            testName = "Проверка изменения стоимости доставки в зависимости от дистанции",
            dataProvider = "deliveryCostDistanceData", dataProviderClass = MyDataProvider.class)
    public void distanceDeliveryCostTest(double distance, int expectedCost) {
        assertEquals(delivery.distanceDeliveryCost(distance), expectedCost,
                "Несоответствие стоимости доставки для расстояния" + distance);
    }

    @Test(description = "Проверка ветвления метода dimensionDeliveryCost",
            testName = "Проверка изменения стоимости доставки в зависимости от габаритов",
            dataProvider = "deliveryWorkLoadCoefficient", dataProviderClass = MyDataProvider.class)
    public void dimensionDeliveryCostTest(String dimension, int expectedCost) {
        assertEquals(delivery.dimensionDeliveryCost(dimension), expectedCost,
                "Несоответствие стоимости доставки для габарита");
    }

    @Test(description = "Проверка исключения метода dimensionDeliveryCost",
            testName = "Проверка исключения IllegalArgumentException при некорректном значении")
    public void unknownDimensionDeliveryCostTest() {
        String demension = "HelloWorld";
        Exception exception = expectThrows(IllegalArgumentException.class, () -> delivery.dimensionDeliveryCost(demension));
        assertEquals(exception.getMessage(), "Неизвестные габариты: " + demension);
    }

    @Test(description = "Проверка метода dimensionDeliveryCost на null значение",
            testName = "Проверка исключения IllegalArgumentException при null значении")
    public void nullDimensionDeliveryCostTest() {
        String demension = null;
        Exception exception = expectThrows(IllegalArgumentException.class, () -> delivery.dimensionDeliveryCost(demension));
        assertEquals(exception.getMessage(), "Неизвестные габариты: " + demension);
    }

    @Test(description = "Проверка ветвления метода workLoadCoefficient",
            testName = "Проверка коэффициента в зависимости от загруженности",
            dataProvider = "deliveryCostWorkLoad", dataProviderClass = MyDataProvider.class)
    public void workLoadCoefficientTest(String workLoad, double expectedCoefficient) {
        assertEquals(delivery.workLoadCoefficient(workLoad), expectedCoefficient,
                "Несоответствие коэффициента нагрузке");
    }

    @Test(description = "Проверка метода workLoadCoefficient на null значение",
            testName = "Проверка коэффициента для null - ожидается 1.0")
    public void workLoadCoefficientNullTest() {
        assertEquals(delivery.workLoadCoefficient(null), 1.0,
                "Несоответствие коэффициента нагрузке");
    }
}