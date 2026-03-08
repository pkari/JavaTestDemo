package demo.ui.guru99.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class Guru99HomePage extends BasePage {

    private static final String URL = "http://demo.guru99.com/test/guru99home";

    @FindBy(css = "a[href='http://www.guru99.com/live-selenium-project.html'] img")
    private WebElement iframeImage;

    @FindBy(css = "iframe[id*='a077aa5e']")
    private WebElement bottomIframe;

    @FindBy(xpath = "//a[contains(text(), 'Testing')]")
    private WebElement testingMenu;

    @FindBy(xpath = "//li/a[@href=\"https://www.guru99.com/selenium-tutorial.html\" and normalize-space()=\"Selenium\"]")
    private WebElement seleniumLink;

    public Guru99HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public String clickIframeImageAndSwitchToNewTab() {
        String originalWindow = getCurrentWindowHandle();

        // Switch to iframe
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(bottomIframe));

        // Click the image
        wait.until(ExpectedConditions.elementToBeClickable(iframeImage));
        iframeImage.click();

        // Switch to new tab
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                switchToWindow(handle);
                break;
            }
        }

        return originalWindow;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void closeCurrentTabAndSwitchBack(String originalWindow) {
        closeCurrentWindow();
        switchToWindow(originalWindow);
    }

    public void hoverOnTestingMenu() {
        // Switch back to default content if we were in iframe
        driver.switchTo().defaultContent();

        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(testingMenu));
        actions.moveToElement(testingMenu).perform();
    }

    public void clickSeleniumLink() {
        wait.until(ExpectedConditions.elementToBeClickable(seleniumLink));
        seleniumLink.click();
    }
}
