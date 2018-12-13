package selenium.wordpress.pageobject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WordpressAdminDashboardPage {
    protected final WebDriver driver;
    private final String CSS_ADMIN_DISPLAY_NAME = "#wp-admin-bar-my-account .display-name";

    public WordpressAdminDashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyIfUserIsLogged() {
        List<WebElement> displayNameElements = driver.findElements(By.cssSelector(CSS_ADMIN_DISPLAY_NAME));

        Assertions.assertTrue(displayNameElements.size() > 0, "Login as Admin");
    }

    public WordpressAdminNotes openNotesView() {
        WebElement menuPostsButton = driver.findElement(By.cssSelector("#menu-posts"));
        Assertions.assertNotNull(menuPostsButton, "Posts are available");

        menuPostsButton.click();

        return new WordpressAdminNotes(driver);
    }

    public void logout() {
        Actions action = new Actions(driver);
        WebElement accountBar = driver.findElement(By.cssSelector("#wp-admin-bar-my-account  > a"));
        Assertions.assertNotNull(accountBar, "Account bar is available");
        action.moveToElement(accountBar).perform();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement logoutButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("#wp-admin-bar-logout a")));
        logoutButton.click();
    }
}
