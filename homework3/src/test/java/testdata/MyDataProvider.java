package testdata;

import org.testng.annotations.DataProvider;
import test.company.Fraction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyDataProvider {
    @DataProvider(name = "deliveryCostDistanceData")
    public Object[][] deliveryCostDistanceData() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/test-data/delivery-distance-data.csv"));

        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] values = line.split(",");
            if (values.length == 2) {
                double distance = Double.parseDouble(values[0].trim());
                int expectedCost = Integer.parseInt(values[1].trim());
                testData.add(new Object[]{distance, expectedCost});
            }
        }
        reader.close();

        return testData.toArray(new Object[0][]);
    }

    @DataProvider(name = "deliveryCostWorkLoad")
    public Object[][] deliveryCostWorkLoad() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/test-data/workload_coefficient_cases.csv"));

        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] values = line.split(",");
            if (values.length == 2) {
                String workload = values[0].trim();
                double expectedCoefficient = Double.parseDouble(values[1].trim());
                testData.add(new Object[]{workload, expectedCoefficient});
            }
        }
        reader.close();

        return testData.toArray(new Object[0][]);
    }

    @DataProvider(name = "deliveryWorkLoadCoefficient")
    public Object[][] deliveryWorkLoadCoefficient() {
        return new Object[][]{
                {"большие", 200},
                {"маленькие", 100}
        };
    }

    @DataProvider(name = "sumData")
    public Object[][] sumData() {
        return new Object[][] {
                {new Fraction(1, 2), new Fraction(1, 3), new Fraction(5, 6)},
                {new Fraction(2, 5), new Fraction(3, 5), new Fraction(25, 25)},
                {new Fraction(-1, 2), new Fraction(1, 2), new Fraction(0, 4)}
        };
    }
}
