package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    // Локаторы
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By metroStationOption = By.xpath(".//div[@class='select-search__select']//button");
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath(".//button[text()='Далее']");
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriod = By.className("Dropdown-placeholder");
    private By rentalPeriodOption = By.xpath(".//div[contains(@class, 'Dropdown-option')]");
    private By colorBlack = By.id("black");
    private By colorGrey = By.id("grey");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath(".//button[text()='Заказать']");
    private By confirmButton = By.xpath(".//div[contains(@class, 'Order_Modal')]//button[text()='Да']");
    private By successMessage = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    // Заполнение анкеты
    public void fillFirstPage(String name, String surname, String address, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);

        driver.findElement(metroField).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(metroStationOption));
        driver.findElements(metroStationOption).get(0).click();

        driver.findElement(phoneField).sendKeys(phone);
    }
    // Переход на след. стр
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillSecondPage(String date, String period, String color, String comment) {
        driver.findElement(dateField).sendKeys(date);

        // Выбор периода аренды
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodOption));
        driver.findElements(rentalPeriodOption).get(0).click();

        // Выбор цвета
        if ("black".equals(color)) {
            driver.findElement(colorBlack).click();
        } else if ("grey".equals(color)) {
            driver.findElement(colorGrey).click();
        }
        // Комментарий курьеру
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickConfirmButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(confirmButton));
        driver.findElement(confirmButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).isDisplayed();
    }

    public void waitForOrderPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }
}