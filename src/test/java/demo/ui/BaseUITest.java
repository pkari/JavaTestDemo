package demo.ui;

import demo.ui.commonUi.CredentialHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import demo.ui.commonUi.WebDriverFactory;
import demo.BaseTest;

import java.time.Duration;

public abstract class BaseUITest extends BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = WebDriverFactory.getChromeOptions();

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        initPageObjects();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Hook method for subclasses to initialize their page objects
    protected abstract void initPageObjects();
}