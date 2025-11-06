package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderStatusPage {
    private WebDriver driver;
    // Локаторы
    private By orderNumberField = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private By searchButton = By.xpath(".//button[text()='Go!']");
    private By notFoundImage = By.xpath(".//img[@alt='Not found']");

    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }
    // Номер заказа
    public void checkNonExistentOrder(String orderNumber) {
        driver.findElement(orderNumberField).sendKeys(orderNumber);
        driver.findElement(searchButton).click();
    }
    // Изображение "Такого заказа нет"
    public boolean isNotFoundImageDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));
        return driver.findElement(notFoundImage).isDisplayed();
    }
}