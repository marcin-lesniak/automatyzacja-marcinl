package selenium.pageobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.pageobject.utils.GoogleMainPage;
import selenium.pageobject.utils.GoogleSearchResault;

public class GoogleTests extends BaseTest {

    @Test
    public void canFindScrumOrgOnGoogle() {
        String textToSearch = "scrum org";
        String requiredUrl = "https://www.scrum.org/";

        canFindPage(textToSearch, requiredUrl);
    }

    @Test
    public void canFindOnetPlOnGoogle() {
        String textToSearch = "onet pl";
        String requiredUrl = "https://www.onet.pl/";

        canFindPage(textToSearch, requiredUrl);
    }

    private void canFindPage(String textToSearch, String requiredUrl) {
        GoogleMainPage mainPage = new GoogleMainPage(driver);

        GoogleSearchResault resaults = mainPage.searchText(textToSearch);

        Assertions.assertTrue(resaults.contains(requiredUrl), "Find" + requiredUrl);
    }
}
