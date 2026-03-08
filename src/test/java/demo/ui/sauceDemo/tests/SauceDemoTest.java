package demo.ui.sauceDemo.tests;

import demo.ui.commonUi.CredentialHelper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import demo.ui.BaseUITest;
import demo.ui.sauceDemo.enums.ProductName;
import demo.ui.sauceDemo.pages.LoginPage;
import demo.ui.sauceDemo.pages.InventoryPage;
import demo.ui.sauceDemo.pages.CartPage;
import demo.ui.sauceDemo.pages.checkout.CheckoutInformationPage;
import demo.ui.sauceDemo.pages.checkout.CheckoutOverviewPage;
import demo.ui.sauceDemo.pages.checkout.CheckoutCompletePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("ui")
public class SauceDemoTest extends BaseUITest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutInformationPage checkoutInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;

    @Override
    protected void initPageObjects() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);}

    //Case 1
    @Test
    public void testSauceDemoCheckout() {
        logger.info("Test Starting: Sauce Demo Checkout");
        logger.info("Login using performance user");
        loginPage.open();
        loginPage.login(
                CredentialHelper.getUsername("performance"),
                CredentialHelper.getPassword("performance"));

        loginPage.waitForInventoryPage();

        logger.info("Add items to the cart using the InventoryPage");
        inventoryPage.addElement(ProductName.BACKPACK);
        inventoryPage.addElement(ProductName.FLEECE_JACKET);

        assertEquals("2", inventoryPage.getCartItemCount(), "Cart should contain 2 items");

        inventoryPage.goToCart();

        cartPage.clickCheckout();

        checkoutInformationPage.fillCheckoutInformation("John", "Doe", "12345");
        checkoutInformationPage.clickContinueButton();

        checkoutOverviewPage.completeCheckout();

        String expectedMessage = "Thank you for your order";
        assertTrue(checkoutCompletePage.getConfirmationMessage().contains(expectedMessage),
                   "Confirmation message should contain '" + expectedMessage + "'");
    }

    //Case 2
    @Test
    public void testLoginWithoutUsernameShowsError() {
        logger.info("Test Starting: Login without username shows error");
        loginPage.open();

        loginPage.clickLoginButton();

        String expectedErrorMessage = "Epic sadface: Username is required";
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        loginPage.checkErrorMessage(expectedErrorMessage);
    }

    //Case2
    @Test
    public void testCheckFooter() {
        logger.info("Test Starting: Check Footer");
        logger.info("Login with standard user");
        loginPage.open();
        loginPage.login(
                CredentialHelper.getUsername("standard"),
                CredentialHelper.getPassword("standard"));

        loginPage.waitForInventoryPage();

        inventoryPage.scrollToBottom();

        logger.info("Validate a footer message containing 2026 and Terms of Service");
        String footerText = inventoryPage.getFooterText();

        assertTrue(footerText.contains("2026"), "Footer should contain '2026'");
        assertTrue(footerText.contains("Terms of Service"), "Footer should contain 'Terms of Service'");
    }
}
