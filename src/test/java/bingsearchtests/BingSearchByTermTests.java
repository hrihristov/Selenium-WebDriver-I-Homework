package bingsearchtests;

import base.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BingSearchByTermTests extends BaseTest {

    @BeforeAll
    public static void beforeAllTests(){
        driver = startBrowser(BrowserTypes.CHROME);

        // Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Navigate to Google.com
        driver.get("https://bing.com");

        // Accept Terms
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnp_btn_accept")));
        WebElement acceptButton = driver.findElement(By.id("bnp_btn_accept"));
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
        WebElement submitButton = driver.findElement(By.xpath("//label[@id='search_icon']"));
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();

        String expectedResult = "Telerik Academy Alpha - IT Career Start in 6 Months";
        String expectedResultPrephrased = "IT Career Start in 6 Months - Telerik Academy Alpha";

        // Assert Result found
        WebElement resultAnchor = driver.findElement(By.xpath("(//div[@class='b_title'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(resultAnchor));
        Assertions.assertTrue(resultAnchor.getText().contains(expectedResult) || resultAnchor.getText().contains(expectedResultPrephrased), "expected result not found. Actual text: " + resultAnchor.getText());
    }
}