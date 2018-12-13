package selenium.wordpress;

import org.junit.jupiter.api.Test;
import selenium.pageobject.BaseTest;
import selenium.wordpress.pageobject.*;

public class WordpressTests extends BaseTest {

    @Test
    public void checkAddingCommentFirstNote() {
        WordpressMainPage mainPage = new WordpressMainPage(driver);

        WordpressNotePage notePage = mainPage.openNewestNote();
        String newAddedComment = notePage.addComment();
        notePage.verifyIfNewCommentWasAdded(newAddedComment);

    }

    @Test
    public void checkAddingCommentToComment() {
        WordpressMainPage mainPage = new WordpressMainPage(driver);

        WordpressNotePage notePage = mainPage.openNewestNote();
        String newAddedComment = notePage.addComment();
        String commentToComment = notePage.addCommentToComment(newAddedComment);
        notePage.verifyIfNewCommentWasAdded(commentToComment);
    }

    @Test
    public void checkAdminLogin() {
        loginAndVerify();
    }

    @Test
    public void checkAddingNewNote() {
        WordpressAdminDashboardPage dashboard = loginAndVerify();
        WordpressAdminNotes notesView = dashboard.openNotesView();
        WordpressAdminNotesCreator noteCreator = notesView.openNotesCreator();
        String noteTitle = noteCreator.addNote();
        noteCreator.logout();

        WordpressMainPage mainPage = new WordpressMainPage(driver);
        mainPage.verifyNoteAdded(noteTitle);

//        dashboard.logout();
    }

    private WordpressAdminDashboardPage loginAndVerify() {
        WordpressAdminLoginPage adminLoginPage = new WordpressAdminLoginPage(driver);
        WordpressAdminDashboardPage dashboard = adminLoginPage.login();
        dashboard.verifyIfUserIsLogged();

        return dashboard;
    }

}
