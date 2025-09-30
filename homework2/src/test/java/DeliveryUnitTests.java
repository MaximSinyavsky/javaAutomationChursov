import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import test.company.Delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Feature("Домашнее задание №2")
@Story("Расчёт стоимости доставки")
public class DeliveryUnitTests {
    private Delivery delivery;

    @BeforeEach
    void setUp() {
        delivery = new Delivery();
    }

    @Test
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка расчёта стоимости доставки")
    void deliveryCostTest() {
        assertEquals(840.0, delivery.deliveryCost(28, "большие", true, "повышенная загруженность"),
                "Несоответствие стоимости доставки");
    }

    @Test
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка минимальной стоимости доставки")
    void minDeliverySumTest() {
        assertEquals(400, delivery.deliveryCost(2, "маленькие", false, "повышенная загруженность"),
                "Несоответствие минимальной стоимости доставки");
    }

    @Test
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка исключения IllegalArgumentException")
    void deliveryCostExceptionTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> delivery.deliveryCost(31, "большие", true, "очень высокая загруженность"),
                "Должно быть выброшено исключение IllegalArgumentException");
        assertEquals("Хрупкие грузы нельзя возить на расстояние более 30 км", exception.getMessage());
    }

    @ParameterizedTest
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка изменения стоимости доставки в зависимости от стоимости")
    @CsvFileSource(resources = "/test-data/delivery-distance-data.csv", numLinesToSkip = 1)
    void distanceDeliveryCostTest(double distance, int expectedCost) {
        assertEquals(expectedCost, delivery.distanceDeliveryCost(distance),
                "Несоответствие стоимости доставки для расстояния" + distance);
    }

    @ParameterizedTest
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка изменения стоимости доставки в зависимости от габаритов")
    @CsvSource({"большие, 200", "маленькие, 100"})
    void dimensionDeliveryCostTest(String dimension, int expectedCost) {
        assertEquals(expectedCost, delivery.dimensionDeliveryCost(dimension),
                "Несоответствие стоимости доставки для габарита");
    }

    @Test
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка исключения IllegalArgumentException при некорректном значении")
    void unknownDimensionDeliveryCostTest() {
        String dimension = "HelloWorld";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> delivery.dimensionDeliveryCost(dimension),
                "Должно быть выброшено исключение IllegalArgumentException");
        assertEquals("Неизвестные габариты: " + dimension, exception.getMessage());
    }

    @Test
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка исключения IllegalArgumentException при null значении")
    void nullDimensionDeliveryCostTest() {
        String dimension = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> delivery.dimensionDeliveryCost(dimension),
                "Должно быть выброшено исключение IllegalArgumentException");
        assertEquals("Неизвестные габариты: " + dimension, exception.getMessage());
    }

    @ParameterizedTest
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка коэффициента в зависимости от загруженности")
    @CsvFileSource(resources = "/test-data/workload_coefficient_cases.csv", numLinesToSkip = 1)
    void workLoadCoefficientTest(String workLoad, double expectedCoefficient) {
        System.out.println("Testing workLoad: " + workLoad + ", expected: " + expectedCoefficient);
        assertEquals(expectedCoefficient, delivery.workLoadCoefficient(workLoad));
    }

    @Test
    @Owner("Maxim Sinyavsky")
    @Tag("Unit")
    @DisplayName("Проверка коэффициента для null - ожидается 1.0")
    void workLoadCoefficientNullTest() {
        assertEquals(1.0, delivery.workLoadCoefficient(null));
    }
}