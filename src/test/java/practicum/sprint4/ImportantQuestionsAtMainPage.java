package practicum.sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import practicum.sprint4.pom.MainPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class ImportantQuestionsAtMainPage {
    private WebDriver driver;
    private int accordionButtonNumber;
    private  String question ;
    private  String answer ;

    public ImportantQuestionsAtMainPage(int accordionButtonNumber, String question, String answer) {
        this.accordionButtonNumber = accordionButtonNumber;
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void setup() {
        // Настройки Хрома
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        // Драйвер для браузера Chrome
        driver = new ChromeDriver(options);
        // Ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Адрес страницы
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }


    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        // Номер accordion__button, вопросы и ответы на них
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов " +
                        "— один за другим."},
                {2, "Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы " +
                        "оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}

        };
    }

    // Проверяем соответствие текста вопроса
    @Test
    public void checkQuestionText(){
        MainPage mainPage = new MainPage(driver);
        mainPage.goToAccordionButton(accordionButtonNumber);
        Assert.assertEquals(question, mainPage.getTextFromQuestion(accordionButtonNumber));
    }

    // Проверяем, что при нажатии на вопрос открывается текст ответа
    @Test
    public void checkExpandingAnswer(){
        MainPage mainPage = new MainPage(driver);
        mainPage.goToAccordionButton(accordionButtonNumber)
                .clickOnQuestion(accordionButtonNumber);
        // После нажатия на вопрос открыватся ответ
        Assert.assertEquals("true", mainPage.checkTextExpandsOnclick(accordionButtonNumber));
        // Текст ответа больше не скрыт
        Assert.assertTrue(mainPage.questionTextIsDisplayed(accordionButtonNumber));
        Assert.assertNull(mainPage.textIsNotHidden(accordionButtonNumber));
        // Текст ответа не Null
        Assert.assertNotNull(mainPage.getTextFromQuestion(accordionButtonNumber));
    }

    // Проверяем соответствие текста ответа
    @Test
    public void checkExpandingAnswerText(){
        MainPage mainPage = new MainPage(driver);
        mainPage.goToAccordionButton(accordionButtonNumber)
                .clickOnQuestion(accordionButtonNumber);
        Assert.assertEquals(answer, mainPage.getExpandedTextFromAnswer(accordionButtonNumber));
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }


}
