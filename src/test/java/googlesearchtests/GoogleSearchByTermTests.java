package googlesearchtests;

import base.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class GoogleSearchByTermTests extends BaseTest {
    @BeforeAll
    public static void beforeAllTests(){

        driver = startBrowser(BrowserTypes.CHROME);

        // Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Navigate to Google.com
        driver.get("https://google.com");

        // Accept Terms
        WebElement acceptButton = driver.findElement(By.id("L2AGLb"));
        acceptButton.click();
    }

    @Test
    public void resultsFound_when_searchForTerm_TelerikAcademy() {
        String searchTerm = "Telerik Academy Alpha";

        // Type Term to search in input
        WebElement input = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.visibilityOf(input));
        input.sendKeys(searchTerm);

        // Submit Search form
        input.sendKeys(Keys.ENTER);

        String expectedResult = "IT Career Start in 6 Months - Telerik Academy Alpha";

        // Assert Result found
        WebElement resultAnchor = driver.findElement(By.xpath("//a/h3"));
        wait.until(ExpectedConditions.visibilityOf(resultAnchor));
        Assertions.assertTrue(resultAnchor.getText().contains(expectedResult), "expected result '" + expectedResult + "' not found. Actual text: " + resultAnchor.getText());
    }
}