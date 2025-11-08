package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class OrderPage {
    private WebDriver driver;

    // Локаторы для первой страницы
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroField = By.className("select-search__input");
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.className("Button_Middle__1CSJM");

    // Локаторы для второй страницы
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriod = By.className("Dropdown-root");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private By confirmButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Да']");

    // Чекбоксы цветов
    private By blackCheckbox = By.cssSelector("label[for='black'] input");
    private By greyCheckbox = By.cssSelector("label[for='grey'] input");

    public final By orderConfirm = By.xpath("//div[contains(@class,'Order_ModalHeader__3FDaJ') and contains(text(),'Заказ оформлен')]");
    //чекбокс самоката - черный жемчуг

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для первой страницы формы
    public void fillFirstPage(String name, String surname, String address, String phone) {
        // Заполняем основные поля
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(phoneField).sendKeys(phone);

        // Выбор станции метро
        selectMetroStation("Бульвар Рокоссовского");
    }

    // Метод для выбора станции метро
    public void selectMetroStation(String stationName) {
        // Кликаем на поле метро
        driver.findElement(metroField).click();

        // Вводим название станции
        driver.findElement(metroField).sendKeys(stationName);

        // Ждем появления опции и кликаем на нее
        WebElement metroOption = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath(".//div[@class='select-search__select']//div[text()='" + stationName + "']")));
        metroOption.click();
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Методы для второй страницы формы
    public void fillSecondPage(String date, String period, String color, String comment) {
        // Заполняем дату
        driver.findElement(dateField).sendKeys(date, Keys.ENTER);

        // Выбираем период аренды
        selectRentalPeriod(period);

        // Выбираем цвет
        selectColor(color);

        // Заполняем комментарий
        driver.findElement(commentField).sendKeys(comment);
    }

    // Метод для выбора периода аренды
    public void selectRentalPeriod(String period) {
        // Кликаем на выпадающий список
        driver.findElement(rentalPeriod).click();

        // Ждем появления опции и кликаем на нее
        WebElement periodOption = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath(".//div[contains(@class,'Dropdown-option') and text()='" + period + "']")));
        periodOption.click();
    }

    // Метод для выбора цвета (РАБОЧИЙ ВАРИАНТ)
    public void selectColor(String color) {
        if ("black".equals(color)) {
            driver.findElement(blackCheckbox).click();
        } else if ("grey".equals(color)) {
            driver.findElement(greyCheckbox).click();
        }
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }


    // Метод проверки заказа
    public void checkOrderConfirm() {
        assertTrue(driver.findElement(orderConfirm).isDisplayed());
    }

    // Метод ожидания загрузки страницы заказа
    public void waitForOrderPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    // Метод для заполнения формы
    public void fillOrderForm(String name, String surname, String address, String phone,
                              String date, String period, String color, String comment) {
        fillFirstPage(name, surname, address, phone);
        clickNextButton();
        fillSecondPage(date, period, color, comment);
    }
}