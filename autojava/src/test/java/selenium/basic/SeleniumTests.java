package selenium.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeleniumTests {
    private WebDriver driver;

    @BeforeEach
    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void canFindCodeSprintersOnGoogle() {
        driver.get("https://www.google.com/");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("code sprinters");
        searchBox.submit();

        Stream<WebElement> searchResults = driver.findElements(By.cssSelector("div.rc")).stream();
        WebElement result = searchResults.filter(r -> r.findElement(By.cssSelector("div.r > a"))
                .getAttribute("href")
                .equals("http://agileszkolenia.pl/"))
                .findFirst().orElse(null);

        Assertions.assertNotNull(result);
    }

    @Test
    public void verifiyAuthorAboutTransformationPost() {
        driver.get("http://markowicz.pro/");
        Stream<WebElement> listOfNotes = driver.findElements(By.className("entry-title"))
                .stream()
                .filter(n -> n.findElement(By.tagName("a")).getText().equals("O transformacjach"));

        List<WebElement> filteredNotes = listOfNotes.collect(Collectors.toList());
        Assertions.assertEquals(1, filteredNotes.size(), "Only one matching note is found on page");

        driver.findElement(By.cssSelector("#eu-cookie-law input")).submit();

        filteredNotes.get(0).click();
        WebElement author = driver.findElement(By.cssSelector(".author > a"));

        Assertions.assertEquals("Rafał", author.getText(), "Rafał is the author of the nore");
        Assertions.assertEquals("http://markowicz.pro/author/rafal-markowicz/", author.getAttribute("href"),
                "Verify URL");
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
