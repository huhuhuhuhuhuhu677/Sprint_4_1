import org.openqa.selenium.By;
import pages.MainPage;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

public class NavigationTest extends BaseTest {

    @Test
    public void testSamokatLogoNavigation() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSamokatLogo();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.className("Home_FAQ__3uVm4")));

        assertTrue("После клика на логотип Самоката не остались на главной странице",
                mainPage.isPageLoaded());
    }

    @Test
    public void testYandexLogoNavigation() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickYandexLogo();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("yandex"));

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Не открылась страница Яндекса. Текущий URL: " + currentUrl,
                currentUrl.contains("yandex") || currentUrl.contains("dzen"));

    }
}