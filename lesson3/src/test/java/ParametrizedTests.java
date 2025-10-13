import dto.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testdata.MyDataProvider;

import java.util.List;

public class ParametrizedTests {
    @Test(dataProvider = "dataMethod")
    public void dataProviderTest(int value) {
        System.out.println("Тест с параметром: " + value);
    }

    @Test(dataProvider = "customName", dataProviderClass = MyDataProvider.class)
    public void dataProviderComplexTest(List<Integer> list, String test, Double number, Boolean value, User user) {
        System.out.printf("Тест с параметрами: %s, %s, %s, %s, %s", list, test, number, value, user);
    }

    @DataProvider
    public Object[][] dataMethod() {
        return new Object[][]{{1}, {2}, {3}};
    }
}