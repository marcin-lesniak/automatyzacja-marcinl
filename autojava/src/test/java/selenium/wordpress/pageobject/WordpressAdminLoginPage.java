package selenium.wordpress.pageobject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WordpressAdminLoginPage {

    private final WebDriver driver;
    private final String LOGIN = "automatyzacja";
    private final String PASSWORD = "jesien2018";

    private final String WORDPRESS_ADMIN_URL = "https://automatyzacja.benedykt.net/wp-admin";

    public WordpressAdminLoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(WORDPRESS_ADMIN_URL);
    }

    public WordpressAdminDashboardPage login() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#user_login")));

        usernameField.click();
        usernameField.sendKeys(LOGIN);

        driver.findElement(By.cssSelector("#user_pass")).sendKeys(PASSWORD);

        driver.findElement(By.cssSelector("#wp-submit")).submit();

        return new WordpressAdminDashboardPage(driver);
    }
}
