package selenium.wordpress.pageobject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Stream;

public class WordpressMainPage {
    private final WebDriver driver;

    private final String CSS_FIRST_NOTE = "article .entry-title";
    private final String WORDPRESS_URL = "https://automatyzacja.benedykt.net/";

    public WordpressMainPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(WORDPRESS_URL);
    }

    public WordpressNotePage openNewestNote() {
        WebElement firstNote = driver.findElement(By.cssSelector(CSS_FIRST_NOTE));
        Assertions.assertNotNull(firstNote, "First note exists");

        firstNote.findElement(By.tagName("a")).click();

        return new WordpressNotePage(driver);
    }

    public void verifyNoteAdded(String noteTitle) {
        List<WebElement> allNotesOnFirstPage = driver.findElements(By.cssSelector("#main article"));
        Stream<WebElement> newNotes = allNotesOnFirstPage.stream()
                .filter(n -> n.findElement(By.cssSelector(".entry-title a")).getText().equals(noteTitle));

        Assertions.assertTrue(newNotes.count() > 0);
    }
}
