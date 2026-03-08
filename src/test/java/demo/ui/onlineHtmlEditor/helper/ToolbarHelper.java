package demo.ui.onlineHtmlEditor.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ToolbarHelper {
    private final WebElement toolbar;
    private final WebDriverWait wait;

    public ToolbarHelper(WebElement toolbar, WebDriverWait wait) {
        this.toolbar = toolbar;
        this.wait = wait;
    }

    public boolean isButtonPressed(WebElement button) {
        String ariaPressed = button.getAttribute("aria-pressed");
        return "true".equals(ariaPressed);
    }

    public void ensureAllFormattingButtonsOff() {
        // Find all buttons with aria-pressed attribute in the container
        List<WebElement> buttons = toolbar.findElements(By.cssSelector("button[aria-pressed]"));

        for (WebElement button : buttons) {
            String ariaPressed = button.getAttribute("aria-pressed");

            // If button is pressed (true), click it to turn it off
            if ("true".equals(ariaPressed)) {
                String tooltipText = button.getAttribute("data-cke-tooltip-text");

                // Click the button using the tooltip text as selector
                WebElement buttonToClick = toolbar.findElement(
                        By.cssSelector("button[data-cke-tooltip-text='" + tooltipText + "']")
                );
                buttonToClick.click();
            }
        }
    }

    public List<String> getPressedButtonTooltips() {
        List<String> pressedTooltips = new ArrayList<>();
        List<WebElement> buttons = toolbar.findElements(By.cssSelector("button[aria-pressed]"));

        for (WebElement button : buttons) {
            if (isButtonPressed(button)) {
                String tooltipText = button.getAttribute("data-cke-tooltip-text");
                pressedTooltips.add(tooltipText);
            }
        }

        return pressedTooltips;
    }

    public void restoreButtonStates(List<String> tooltipsToPress) {
        for (String tooltip : tooltipsToPress) {
            WebElement button = toolbar.findElement(
                By.cssSelector("button[data-cke-tooltip-text='" + tooltip + "']")
            );

            // Only click if it's not already pressed
            if (!isButtonPressed(button)) {
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
            }
        }
    }
}
