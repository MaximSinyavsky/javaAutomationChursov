import io.qameta.allure.Owner;
import jdk.jfr.Name;
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
    @Name("Check ")

    void mainPageLinkTest(
            String chapterName, String buttonName, String titleName, String linkUrl, Boolean isFrame
    ) {
        String expectedUrl = BASE_URL + linkUrl;
        WebElement chapterSection = driver.findElement(By.xpath("//div[h5[text() = '" + chapterName + "']]"));
        assertEquals(chapterName, chapterSection.findElement(By.tagName("h5")).getText(), "The chapter name must be: " + chapterName);

        WebElement chapterButton = chapterSection.findElement(By.linkText(buttonName));
        chapterButton.click();
        assertEquals(expectedUrl, driver.getCurrentUrl(), "The URL must be: " + expectedUrl);

        if (isFrame) {
            driver.switchTo().frame("frame-header");
        }

        WebElement actualTitle = driver.findElement(By.className("display-6"));
        assertEquals(titleName, actualTitle.getText(), "The title must be " + titleName);
    }
}