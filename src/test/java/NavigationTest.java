//Локаторы и код работы с WebDriver перемещены в pages

import pages.MainPage;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class NavigationTest extends BaseTest {

    @Test
    public void testSamokatLogoNavigation() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSamokatLogo();
        mainPage.waitForMainPageLoad();
        assertTrue("После клика на логотип Самоката не остались на главной странице",
                mainPage.isPageLoaded());
    }

    @Test
    public void testYandexLogoNavigation() {
        MainPage mainPage = new MainPage(driver);

        String originalWindow = mainPage.getCurrentWindowHandle();

        int originalWindowCount = mainPage.getWindowsCount();

        mainPage.clickYandexLogo();
        mainPage.waitForNewWindow(originalWindowCount);

        mainPage.switchToNewWindow();

        assertTrue("Не открылась страница Яндекса",
                mainPage.isYandexPage());

        mainPage.closeCurrentWindow();

        mainPage.switchToWindow(originalWindow);
    }
}
