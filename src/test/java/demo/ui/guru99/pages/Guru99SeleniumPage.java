package demo.ui.guru99.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Guru99SeleniumPage extends BasePage {

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public Guru99SeleniumPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSubmitButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        return submitButton.isDisplayed();
    }
}
