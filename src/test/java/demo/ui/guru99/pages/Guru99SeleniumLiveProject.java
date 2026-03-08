package demo.ui.guru99.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Guru99SeleniumLiveProject extends BasePage{

    @FindBy(xpath = "//header/h1")
    private WebElement title;

    public Guru99SeleniumLiveProject(WebDriver driver) {
        super(driver);}

    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOf(title));
        return driver.getTitle();
    }

    public void closeCurrentTabAndSwitchBack(String originalWindow) {
        closeCurrentWindow();
        switchToWindow(originalWindow);
    }
}
