package selenium.wordpress.pageobject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WordpressAdminNotes extends WordpressAdminDashboardPage {

    public WordpressAdminNotes(WebDriver driver) {
        super(driver);
    }

    public WordpressAdminNotesCreator openNotesCreator() {
        WebElement menuPostsButton = driver.findElement(By.cssSelector("#wpbody .wrap a.page-title-action"));
        Assertions.assertNotNull(menuPostsButton, "New post button is available");

        menuPostsButton.click();

        return new WordpressAdminNotesCreator(driver);
    }
}
