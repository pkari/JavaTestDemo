package demo.ui.sauceDemo.pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import demo.ui.sauceDemo.pages.BasePage;

public class CheckoutCompletePage extends BasePage {

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        return confirmationMessage.getText();
    }

}
