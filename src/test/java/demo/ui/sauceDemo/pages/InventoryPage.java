package demo.ui.sauceDemo.pages;

import demo.ui.sauceDemo.enums.ProductName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    // WebElement locators
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpackAddButton;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement fleeceJacketAddButton;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    private WebElement cartBadge;

    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement cartLink;

    @FindBy(css = "footer.footer")
    private WebElement footer;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addElement(ProductName productName) {
        WebElement addButton = driver.findElement(By.id("add-to-cart-" + productName.getId()));
        addButton.click();
    }

//    public void addBackpackToCart() {
//        addItemToCart(ProductName.BACKPACK);
//    }
//
//    public void addFleeceJacketToCart() {
//        addItemToCart(ProductName.FLEECE_JACKET);
//   }

    public String getCartItemCount() {
        return cartBadge.getText();
    }

    public void goToCart() {
        cartLink.click();
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public String getFooterText() {
        return footer.getText();
    }
}
