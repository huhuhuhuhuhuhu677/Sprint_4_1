import pages.MainPage;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class FaqTest extends BaseTest {

    @Test
    public void testAllFaqQuestionsExpand() {
        MainPage mainPage = new MainPage(driver);

        assertTrue("Главная страница не загрузилась", mainPage.isPageLoaded());

        int questionsCount = mainPage.getFaqQuestionsCount();
        for (int i = 0; i < questionsCount; i++) {
            mainPage.clickFaqQuestion(i);
            assertTrue("Ответ на вопрос " + (i + 1) + " не отображается",
                    mainPage.isFaqAnswerDisplayed(i));
        }
    }
}