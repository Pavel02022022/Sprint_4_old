package practicum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;

    // Кнопка "Заказать"
    private By orderButton = By.xpath(".//button[contains(text(), 'Заказать')]");
    // "Вопрос о важном"
    private By accordionButton = By.className("accordion__button");

    private By topOrderButton = By.className("Button_Button__ra12g");
    private By botttomOrderButton =By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    private By cookiePanel = By.className("App_CookieConsent__1yUIN");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открыть главную страницу сайта
    public MainPage open() {
        driver.get(URL);
        return this;
    }
    // Клик на вопросе
    public MainPage clickOnQuestion(int i){
        driver.findElements(accordionButton).get(i).click();
        return this;
    }

    // Переход к n-ой accordionButton
    public MainPage goToAccordionButton(int i){
        String ariaControls = driver.findElements(accordionButton).get(i).getAttribute("aria-controls");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath(".//div[@aria-controls='" + ariaControls + "']")));
        return this;
    }

    // Получение атрибута aria-expanded, который понадобится для проверки, что произошло развертывание
    public String checkTextExpandsOnclick(int i){
        return driver.findElements(accordionButton).get(i).getAttribute("aria-expanded");
    }

    // Получение атрибута hidden, который понадобится для проверки, что текст ответа больше не скрыт
    public String textIsNotHidden(int i){
        return driver.findElements(accordionButton).get(i).getAttribute("hidden");
    }

    // Получение текста открывшегося текста ответа
    public String getExpandedTextFromAnswer(int i){
        String ariaControls = driver.findElements(accordionButton).get(i).getAttribute("aria-controls");
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id(ariaControls)))).getText();
    }

    // Получение текста вопроса
    public String getTextFromQuestion(int i){
        return driver.findElements(accordionButton).get(i).getText();
    }

    // Текст больше не скрыт
    public boolean questionTextIsDisplayed(int i){
        return driver.findElements(accordionButton).get(i).isDisplayed();
    }

    // Ождидание элемента
    public MainPage waitForElement(By element){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(element)));
        return this;
    }

    // Нажать на кнопку "Заказать"
    public MainPage clickOnOrderButton(String orderButtonPosition) {
        if (orderButtonPosition.equals("top")){
            waitForElement(topOrderButton);
            removeElement(cookiePanel);
            driver.findElement(topOrderButton).click();
        }

        if (orderButtonPosition.equals("bottom")){
            waitForElement(botttomOrderButton);
            removeElement(cookiePanel);
            driver.findElement(botttomOrderButton).click();
        }
        return this;
    }

    // Удаление элемента понадобилось, потому что элемент перекрывал нажатие на кнопку заказа
    public MainPage removeElement(By element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].remove();",
                driver.findElement(element));
        return this;
    }
}
