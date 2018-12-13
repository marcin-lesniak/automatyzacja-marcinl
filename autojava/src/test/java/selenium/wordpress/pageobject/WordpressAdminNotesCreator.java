package selenium.wordpress.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

public class WordpressAdminNotesCreator extends WordpressAdminDashboardPage{
    private final String CSS_EDIT_NOTE_URL_BUTTON = "#edit-slug-buttons > button";
    private final String CSS_SUBMIT_BUTTON = "#publishing-action #publish";
    private final String CSS_NOTE_TITLE_FIELD = "#post-body #title";
    private final String CSS_NOTE_CONTENT_FIELD = "#wp-content-editor-container #content";

    public WordpressAdminNotesCreator(WebDriver driver) {
        super(driver);
    }

    public String addNote() {
        String noteText = UUID.randomUUID().toString();

        driver.findElement(By.cssSelector(CSS_NOTE_TITLE_FIELD)).sendKeys(noteText);
        driver.findElement(By.cssSelector(CSS_NOTE_CONTENT_FIELD)).sendKeys(noteText);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .elementToBeClickable(driver.findElement(By.cssSelector(CSS_EDIT_NOTE_URL_BUTTON))));
        WebElement submit = wait.until(ExpectedConditions
                .elementToBeClickable(driver.findElement(By.cssSelector(CSS_SUBMIT_BUTTON))));
        submit.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#message a")));

        return noteText;
    }

}
