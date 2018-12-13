package selenium.wordpress.pageobject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.UUID;

public class WordpressNotePage {
    private final WebDriver driver;

    private String commentAuthor;
    private String commentEmail;

    public WordpressNotePage(WebDriver driver) {
        this.driver = driver;

        commentAuthor = "MarcinMarcin" + Math.random()%10;
        commentEmail = "marcin.marcin@gmail.com";
    }

    public String addComment() {
        String newComment = UUID.randomUUID().toString();

        driver.findElement(By.cssSelector("textarea#comment")).sendKeys(newComment);
        driver.findElement(By.cssSelector("input#author")).sendKeys(commentAuthor);
        driver.findElement(By.cssSelector("input#email")).sendKeys(commentEmail);
        driver.findElement(By.cssSelector("input#submit")).submit();

        return newComment;
    }

    public void verifyIfNewCommentWasAdded(String newAddedComment) {
        WebElement myComments = getMyComment(newAddedComment);

        Assertions.assertNotNull(myComments, "My comment to comment exists");
    }

    private WebElement getMyComment(String newAddedComment) {
        WebElement myComment = driver.findElements(By.cssSelector("article.comment-body"))
                .stream()
                .filter(c -> c.findElement(By.cssSelector("div.comment-author b")).getText()
                        .equals(commentAuthor))
                .filter(c -> c.findElement(By.cssSelector("div.comment-content p")).getText()
                        .equals(newAddedComment))
                .findFirst().orElse(null);

        return myComment;
    }

    public String addCommentToComment(String newAddedComment) {
        WebElement myComments = getMyComment(newAddedComment);
        myComments.findElement(By.cssSelector(".reply a")).click();

        String commentToComment = addComment();

        return commentToComment;
    }
}
