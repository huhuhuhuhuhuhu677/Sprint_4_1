package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;

public class MainPage {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;

    // Локаторы для кнопок заказа
    private By orderButtonHeader = By.className("Button_Button__ra12g");
    private By orderButtonFooter = By.className("Button_Middle__1CSJM");

    // Локаторы для FAQ
    private By faqSection = By.className("Home_FAQ__3uVm4");
    private By faqTable = By.xpath(".//div[@class='accordion']");

    // Локаторы для логотипов
    private By samokatLogo = By.className("Header_LogoScooter__3lsAR");
    private By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    // Локатор для куки
    private By closeButtonCookie = By.cssSelector("button.App_CookieButton__3cvqF");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // === ОСНОВНЫЕ МЕТОДЫ ===

    public void openPage() {
        driver.get(URL);
    }

    public void acceptCookies() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(closeButtonCookie));
            driver.findElement(closeButtonCookie).click();
            System.out.println("Куки приняты");
        } catch (Exception e) {
            System.out.println("Кнопка куки не найдена или уже закрыта");
        }
    }

    // === МЕТОДЫ ДЛЯ ORDER TEST ===

    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    public void clickOrderButtonFooter() {
        // Скроллим до футера перед кликом
        scrollToFooter();
        driver.findElement(orderButtonFooter).click();
    }

    // Метод для скролла до футера (ДОБАВЛЕНО)
    public void scrollToFooter() {
        try {
            WebElement footerButton = driver.findElement(orderButtonFooter);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", footerButton);

            Thread.sleep(1000);

            System.out.println("Проскроллили до нижней кнопки заказа");
        } catch (Exception e) {
            System.out.println("Ошибка при скролле до футера: " + e.getMessage());
        }
    }

    // === МЕТОДЫ ДЛЯ FAQ TEST ===

    public void clickQuestion(int questionNumber) {
        By questionLocator = By.id("accordion__heading-" + (questionNumber - 1));
        driver.findElement(questionLocator).click();
    }

    public String getFaqAnswerText() {
        By faqAnswer = By.cssSelector("[data-accordion-component='AccordionItemPanel']:not([hidden])");
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(faqAnswer));
        WebElement answerElement = driver.findElement(faqAnswer);
        return answerElement.getText().trim();
    }

    public void scrollToFAQTable() {
        WebElement tableFAQ = driver.findElement(faqTable);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", tableFAQ);
    }

    public void closeCookieButton() {
        driver.findElement(closeButtonCookie).click();
    }

    // === МЕТОДЫ ДЛЯ NAVIGATION TEST ===

    public void clickSamokatLogo() {
        driver.findElement(samokatLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void waitForMainPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(faqSection));
    }

    // Методы для работы с окнами
    public void switchToNewWindow() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public int getWindowsCount() {
        return driver.getWindowHandles().size();
    }

    public void waitForNewWindow(int originalWindowCount) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.getWindowHandles().size() > originalWindowCount);
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public boolean isYandexPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("yandex") || currentUrl.contains("dzen");
    }

    public boolean isPageLoaded() {
        return driver.findElement(faqSection).isDisplayed();
    }
}
