package demo.ui.onlineHtmlEditor.pages;

import demo.ui.onlineHtmlEditor.helper.ToolbarHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HTMLEditorPage extends BasePage {

    @FindBy(css = "button[data-cke-tooltip-text='Bold (Ctrl+B)']")
    private WebElement boldButton;

    @FindBy(css = "button[data-cke-tooltip-text='Underline (Ctrl+U)']")
    private WebElement underlineButton;

//    @FindBy(css = "iframe.cke_wysiwyg_frame")
//    private WebElement editorIframe;

    @FindBy(css = "div[aria-label='Rich Text Editor. Editing area: main. Press Alt+0 for help.']")
    private WebElement editorBody;

    @FindBy(css = "div[class='ck ck-toolbar__items']")
    private WebElement toolbar;

    @FindBy(css = "button.ch2-btn.ch2-allow-all-btn.ch2-btn-primary")
    private WebElement acceptCookieButton;

    private ToolbarHelper toolbarHelper;

    public HTMLEditorPage(WebDriver driver) {
        super(driver);
        this.toolbarHelper = new ToolbarHelper(toolbar, wait);
    }

    public void open() {
        driver.get("https://onlinehtmleditor.dev");
    }

    public void closeCookieModalIfPresent() {
        try {
//            WebElement acceptButton = driver.findElement(By.cssSelector("button.ch2-btn.ch2-allow-all-btn.ch2-btn-primary"));
            if (acceptCookieButton.isDisplayed()) {
                acceptCookieButton.click();
            }
        } catch (NoSuchElementException e) {
            // Cookie modal not present, continue
        }
    }

    public void typeTextInEditor(String text) {
        wait.until(ExpectedConditions.visibilityOf(editorBody));

        // Save which buttons were pressed before typing
        List<String> pressedButtons = toolbarHelper.getPressedButtonTooltips();

        // Turn off all formatting buttons
        toolbarHelper.ensureAllFormattingButtonsOff();

        // Type the text
        editorBody.sendKeys(text);

        // Restore previously pressed buttons
        toolbarHelper.restoreButtonStates(pressedButtons);
    }

    public void typeBoldText(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(boldButton));

        // Save which buttons were pressed before typing
        List<String> pressedButtons = toolbarHelper.getPressedButtonTooltips();

        // Turn off all formatting buttons
        toolbarHelper.ensureAllFormattingButtonsOff();

        boldButton.click();
        editorBody.sendKeys(text);
        boldButton.click();

        // Restore previously pressed buttons
        toolbarHelper.restoreButtonStates(pressedButtons);

    }

    public void typeUnderlineText(String text) {
        //driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(underlineButton));
        // Save which buttons were pressed before typing
        List<String> pressedButtons = toolbarHelper.getPressedButtonTooltips();

        // Turn off all formatting buttons
        toolbarHelper.ensureAllFormattingButtonsOff();

        underlineButton.click();
        editorBody.sendKeys(text);
        underlineButton.click();

        // Restore previously pressed buttons
        toolbarHelper.restoreButtonStates(pressedButtons);
    }

    public String getEditorText() {
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(editorIframe));
        String text = editorBody.getText();
        //driver.switchTo().defaultContent();
        return text;
    }

    public String getEditorHtml() {
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(editorIframe));
        String html = editorBody.getAttribute("innerHTML");
        //driver.switchTo().defaultContent();
        return html;
    }
}
