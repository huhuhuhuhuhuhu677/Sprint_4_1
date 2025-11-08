//Исправлена проверка - была последовательно
//Добавлена параметизация
//Проверяется ответ на вопрос, а не только отображение

import pages.MainPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {

    private final int questionNumber;
    private final String questionDescription;
    private final String expectedAnswer;

    public FaqTest(int questionNumber, String questionDescription, String expectedAnswer) {
        this.questionNumber = questionNumber;
        this.questionDescription = questionDescription;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters(name = "Тест вопроса FAQ: {1}")
    public static Object[][] getQuestionData() {
        return new Object[][]{
                {1, "Стоимость аренды", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Несколько самокатов", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Время аренды", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Заказ на сегодня", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Продление и возврат", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Зарядка самоката", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Отмена заказа", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Зона оплата", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void testFaqQuestionAnswer() {
        MainPage mainPage = new MainPage(driver);

        // Открыть домашнюю страницу Яндекс самокат
        mainPage.openPage();

        // Проскролить страницу до появления таблицы FAQ
        mainPage.scrollToFAQTable();

        // Закрыть куки, если мешают
        mainPage.closeCookieButton();

        // Нажать на вопрос (используем метод из рабочего кода)
        mainPage.clickQuestion(questionNumber);

        // Проверить что ответ отображается и содержит правильный текст
        String actualAnswer = mainPage.getFaqAnswerText();
        assertEquals("Текст ответа должен соответствовать ожидаемому", expectedAnswer, actualAnswer);
    }
}