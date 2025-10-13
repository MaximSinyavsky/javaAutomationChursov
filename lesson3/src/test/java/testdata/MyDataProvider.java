package testdata;

import dto.User;
import org.testng.annotations.DataProvider;

import java.util.List;

public class MyDataProvider {
    @DataProvider(name = "customName")
    public Object[][] complexDataMethod() {
        return new Object[][]{
                {List.of(1), "Test", 1.0, true, new User("name", "lastname", 10)},
                {List.of(2), "Test3", 2.0, false, new User("name2", "lastname2", 20)}
        };
    }
}