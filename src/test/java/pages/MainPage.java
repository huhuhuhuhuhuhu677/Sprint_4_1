package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Локаторы
    private By faqSection = By.className("Home_FAQ__3uVm4");
    private By faqButton = By.className("Accordion__Button");
    private By faqAnswer = By.className("Accordion__Panel");
    private By orderButtonHeader = By.className("Button_Button__ra12g");
    private By orderButtonFooter = By.className("Button_Middle__1CSJM");
    private By samokatLogo = By.className("Header_LogoScooter__3lsAR");
    private By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFaqQuestion(int index) {
        driver.findElements(faqButton).get(index).click();
    }

    public boolean isFaqAnswerDisplayed(int index) {
        return driver.findElements(faqAnswer).get(index).isDisplayed();
    }

    public int getFaqQuestionsCount() {
        return driver.findElements(faqButton).size();
    }

    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    public void clickOrderButtonFooter() {
        driver.findElement(orderButtonFooter).click();
    }

    public void clickSamokatLogo() {
        driver.findElement(samokatLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public boolean isPageLoaded() {
        return driver.findElement(faqSection).isDisplayed();
    }
}