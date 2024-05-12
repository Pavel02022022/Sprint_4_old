package practicum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    // Кнопка "Заказать"
    private By orderButton = By.className("Button_Button__ra12g");
    // "Вопрос о важном"
    private By accordionButton = By.className("accordion__button");

    //private String attributeAriaControls = driver.findElements(accordionButton).get(i).getAttribute("aria-controls");;

    //private By ariaControls = By.xpath(".//div[@aria-controls='" + attributeAriaControls + "']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Нажать кнопку "Заказать"
    public MainPage pressOrderButton(){
        driver.findElement(orderButton).click();
        return this;
    }

    public MainPage clickOnQuestion(int i){
        driver.findElements(accordionButton).get(i).click();
        return this;
    }

    public MainPage goToAccordionButton(int i){
        String ariaControls = driver.findElements(accordionButton).get(i).getAttribute("aria-controls");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath(".//div[@aria-controls='" + ariaControls + "']")));
        return this;
    }

    public String checkTextExpandsOnclick(int i){
        return driver.findElements(accordionButton).get(i).getAttribute("aria-expanded");
    }

    public String textIsNotHidden(int i){
        return driver.findElements(accordionButton).get(i).getAttribute("hidden");
    }

    public String getExpandedTextFromAnswer(int i){
        String ariaControls = driver.findElements(accordionButton).get(i).getAttribute("aria-controls");
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id(ariaControls)))).getText();
    }

    public String getTextFromQuestion(int i){
        return driver.findElements(accordionButton).get(i).getText();
    }

    public boolean questionTextIsDisplayed(int i){
        return driver.findElements(accordionButton).get(i).isDisplayed();

    }







}
