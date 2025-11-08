import pages.OrderStatusPage;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class OrderStatusTest extends BaseTest {

    @Test
    public void testNonExistentOrder() {
        OrderStatusPage orderStatusPage = new OrderStatusPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/track");
        orderStatusPage.checkNonExistentOrder("999999");
        assertTrue("Сообщение 'Такого заказа нет' не отображается",
                orderStatusPage.isNotFoundImageDisplayed());
    }
}