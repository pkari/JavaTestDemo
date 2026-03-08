package demo.ui.guru99.tests;

import org.junit.jupiter.api.Tag;
import demo.ui.BaseUITest;
import demo.ui.guru99.pages.Guru99HomePage;
import demo.ui.guru99.pages.Guru99SeleniumLiveProject;
import demo.ui.guru99.pages.Guru99SeleniumPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("ui")
public class Guru99Test extends BaseUITest {
    private Guru99HomePage homePage;
    private Guru99SeleniumPage seleniumPage;
    private Guru99SeleniumLiveProject seleniumLiveProject;

    @Override
    protected void initPageObjects() {
        homePage = new Guru99HomePage(driver);
        seleniumPage = new Guru99SeleniumPage(driver);
        seleniumLiveProject = new Guru99SeleniumLiveProject(driver);
    }

    //Case 4
    @Test
    public void testGuru99NavigationAndIframeInteraction() {
        logger.info("Test Starting: Guru99 Navigation and Iframe Interaction");
        // Step 1: Open the URL
        homePage.open();

        // Step 2: Click on image inside iframe and switch to new tab
        String originalWindow = homePage.clickIframeImageAndSwitchToNewTab();

        // Step 3: Verify new page title
        String actualTitle = seleniumLiveProject.getPageTitle();
        String expectedTitle = "Selenium Live Project for Practice";
        assertEquals(expectedTitle, actualTitle, "Page title should match expected title");

        // Step 4: Close current tab and switch back to main window
        seleniumLiveProject.closeCurrentTabAndSwitchBack(originalWindow);

        // Step 5: Hover on Testing menu and click Selenium link
        homePage.hoverOnTestingMenu();
        homePage.clickSeleniumLink();

        // Step 6: Verify Submit button is displayed
        assertTrue(seleniumPage.isSubmitButtonDisplayed(), "Submit button should be displayed on Selenium page");
    }
}
