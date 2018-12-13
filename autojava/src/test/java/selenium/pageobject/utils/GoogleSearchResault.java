package selenium.pageobject.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class GoogleSearchResault {
    private static final By CSS_DIV_RESULTS = By.cssSelector("div.rc");
    private static final By CSS_DRV_RESULT_ANCHOR = By.cssSelector("div.r > a");

    private final WebDriver driver;

    public GoogleSearchResault(WebDriver driver) {
        this.driver = driver;
    }

    public boolean contains(String requiredUrl) {
        Stream<WebElement> searchResults = driver.findElements(CSS_DIV_RESULTS).stream();
        WebElement result = searchResults.filter(r -> r.findElement(CSS_DRV_RESULT_ANCHOR)
                .getAttribute("href")
                .equals(requiredUrl))
                .findFirst().orElse(null);

        return result != null;
    }
}
