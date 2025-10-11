import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class mainPageButtonsTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @Owner("Maxim Sinyavsky")
    @Tag("Smoke")
    @CsvFileSource(resources = "/test-data/mainPageButtonsData.csv", numLinesToSkip = 1)
    void mainPageLinkTest(
            String chapterName, String buttonName, String titleName,  String linkUrl
    ) {
        String url = BASE_URL + linkUrl;
        WebElement button = driver.findElement(By.xpath("//h5[text() = '"+ chapterName+"']/../a[@href = '"+linkUrl+"' and text() = '"+titleName+"']"));
        button.click();
    }
}
