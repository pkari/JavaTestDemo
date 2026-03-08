package demo.ui.onlineHtmlEditor.tests;

import org.junit.jupiter.api.Tag;
import demo.ui.BaseUITest;
import demo.ui.onlineHtmlEditor.pages.HTMLEditorPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("ui")
public class HTMLEditorTest extends BaseUITest {

    private HTMLEditorPage editorPage;

    @Override
    protected void initPageObjects() {
        editorPage = new HTMLEditorPage(driver);
    }

    //Case 3
    @Test
    public void testRichTextEditorFormatting() {
        logger.info("Test Starting: Rich Text Editor Formatting");
        logger.info("Step 1: Open the URL");
        editorPage.open();
        editorPage.closeCookieModalIfPresent();

        logger.info("Type text with formatting");
        editorPage.typeBoldText("Automation");
        editorPage.typeTextInEditor(" ");

        editorPage.typeUnderlineText("Test");
        editorPage.typeTextInEditor(" ");

        editorPage.typeTextInEditor("Example");

        logger.info("Validate the text is appearing in the rich text editor");
        String editorText = editorPage.getEditorText();
        assertTrue(editorText.contains("Automation"), "Editor should contain 'Automation'");
        assertTrue(editorText.contains("Test"), "Editor should contain 'Test'");
        assertTrue(editorText.contains("Example"), "Editor should contain 'Example'");

        logger.info("Validate formatting by checking HTML");
        String editorHtml = editorPage.getEditorHtml();
        assertTrue(editorHtml.contains("<strong>Automation</strong>"), "Automation should be bold");
        assertTrue(editorHtml.contains("<u>Test</u>"), "Test should be underlined");
    }
}
