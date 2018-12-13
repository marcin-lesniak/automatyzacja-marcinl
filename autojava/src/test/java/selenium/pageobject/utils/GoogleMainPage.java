package selenium.pageobject.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class GoogleMainPage {
    private final String GOOGLE_URL = "https://www.google.pl";

    private WebDriver driver;

    public GoogleMainPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(GOOGLE_URL);
    }

    public GoogleSearchResault searchText(String textToSearch) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(textToSearch);
        searchBox.submit();

        return new GoogleSearchResault(driver);
    }
}
