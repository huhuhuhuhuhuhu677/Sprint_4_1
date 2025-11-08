//Добавлена аннотация для параметризованных тестов
//Тест больше н падает


import pages.MainPage;
import pages.OrderPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    private final String color;
    private final String comment;
    private final String buttonLocation;

    public OrderTest(String name, String surname, String address, String phone,
                     String date, String color, String comment, String buttonLocation) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.color = color;
        this.comment = comment;
        this.buttonLocation = buttonLocation;
    }

    @Parameterized.Parameters(name = "Заказ через {7} кнопку: {0} {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Ибрагим", "Ибрагимов", "ул. Пионеров, 1", "+79109111213",
                        "15.11.2025", "black", "Не звонить", "header"},
                {"Лидия", "Мяукова", "пр. Мира, 1", "+79989111219",
                        "16.11.2025", "grey", "Код от домофона 123", "footer"}
        });
    }

    @Test
    public void testSuccessfulOrder() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // Принимаем куки перед началом теста
        mainPage.acceptCookies();

        // Выбор кнопки заказа в зависимости от параметра
        if ("header".equals(buttonLocation)) {
            System.out.println("Кликаем на верхнюю кнопку заказа");
            mainPage.clickOrderButtonHeader();
        } else {
            System.out.println("Кликаем на нижнюю кнопку заказа");
            mainPage.clickOrderButtonFooter();
        }

        // Заполнение формы
        orderPage.waitForOrderPageLoad();
        orderPage.fillOrderForm(name, surname, address, phone, date, "сутки", color, comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmButton();

        // Проверка подтверждения заказа
        orderPage.checkOrderConfirm();
    }
}