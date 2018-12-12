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
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordpressTests {

    private WebDriver driver;
    private String commentAuthor;
    private String commentEmail;
    private String comment;

    @BeforeEach
    public void setUpTest() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        commentAuthor = "MarcinMarcin" + Math.random()%10;
        commentEmail = "marcin.marcin@gmail.com";
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void checkAddingCommentToComment() {
        driver.get("https://automatyzacja.benedykt.net/");
        WebElement firstNote = driver.findElement(By.cssSelector("article .entry-title"));

        Assertions.assertNotNull(firstNote, "First note exists");

        firstNote.findElement(By.tagName("a")).click();
        comment = UUID.randomUUID().toString();
        addNewComment(comment);
        WebElement myComments = getMyAddedComment(comment);
        Assertions.assertNotNull(myComments, "My comment exists");

        myComments.findElement(By.cssSelector(".reply a")).click();
        comment = UUID.randomUUID().toString();
        addNewComment(comment);
        myComments = getMyAddedComment(comment);
        Assertions.assertNotNull(myComments, "My comment to comment exists");
    }

    private WebElement getMyAddedComment(String comment) {
        WebElement myComments = driver.findElements(By.cssSelector("article.comment-body"))
                .stream()
                .filter(c -> c.findElement(By.cssSelector("div.comment-author b")).getText()
                        .equals(commentAuthor))
                .filter(c -> c.findElement(By.cssSelector("div.comment-content p")).getText()
                        .equals(comment))
                .findFirst().orElse(null);

        return myComments;
    }

    private void addNewComment(String comment) {

        driver.findElement(By.cssSelector("textarea#comment")).sendKeys(comment);

        driver.findElement(By.cssSelector("input#author")).sendKeys(commentAuthor);

        driver.findElement(By.cssSelector("input#email")).sendKeys(commentEmail);

        driver.findElement(By.cssSelector("input#submit")).submit();
    }

    @Test
    public void checkAddingCommentToFirstComment() {
        driver.get("https://automatyzacja.benedykt.net/");
        WebElement firstNoteWithComment = driver.findElements(By.cssSelector(".comments-link a"))
                .stream()
                .filter(n -> {
                    String text = n.getText();
                    Integer textAsInt = Integer.parseInt(text);
                    return textAsInt != null;
                })
                .findFirst().orElse(null);

        Assertions.assertNotNull(firstNoteWithComment, "Note with comments exists");
    }
}
