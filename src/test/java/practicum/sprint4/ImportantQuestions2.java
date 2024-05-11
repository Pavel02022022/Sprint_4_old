package practicum.sprint4;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.sprint4.pom.MainPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class ImportantQuestions2 {

    private WebDriver driver;
    private  String question ;
    private  String answer ;
    private int index;
    //
    By accordionButton = By.className("accordion__button");
    //

    private String path = "https://qa-scooter.praktikum-services.ru/";

    public ImportantQuestions2(String question, String answer, int index) {
        this.question = question;
        this.answer = answer;
        this.index = index;
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
    }


    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        //Вопросы и ответы на них
        return new Object[][] {
                { "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                { "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов " +
                        "— один за другим.", 1},
                {"Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы " +
                        "оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7 }

        };
    }



    @Test
    public void test2(){

        driver.get(path);

        // Количество кнопок
        int size = driver.findElements(accordionButton).size();

 for (int i =0; i < size; i++){
     if (i == index) {
         String ariaControls = driver.findElements(accordionButton).get(i).getAttribute("aria-controls");

         System.out.println(driver.findElements(accordionButton).get(i).getText());

         Assert.assertEquals(driver.findElements(accordionButton).get(i).getText(), question);

         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                 driver.findElement(By.xpath(".//div[@aria-controls='" + ariaControls + "']")));

         driver.findElements(accordionButton).get(i).click();
         // Выпадающий список открылся
         Assert.assertEquals("true", driver.findElements(accordionButton).get(i).getAttribute("aria-expanded"));

         // Текст больше не скрыт
         Assert.assertNull(driver.findElements(accordionButton).get(i).getAttribute("hidden"));

         // Проверка, что открылся соответствующий текст
         String text = new WebDriverWait(driver, Duration.ofSeconds(3))
                 .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//div[@id ='" + ariaControls + "']")))).getText();

         System.out.println(text);

         Assert.assertEquals(text, answer);
     }
 }
 }
 @Description("sdf")
 @Test
 public void test3(){
     driver.get(path);

     MainPage mainPage = new MainPage(driver);
     mainPage.goToAccordionButton(index);
     Assert.assertEquals(question, mainPage.getTextFromQuestion(index));
     mainPage.clickOnQuestion(index);
     Assert.assertEquals("true", mainPage.checkTextExpandsOnclick(index));
     Assert.assertNull(mainPage.textIsNotHidden(index));
     Assert.assertEquals(answer, mainPage.getExpandedTextFromAnswer(index));

 }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();

    }


}
