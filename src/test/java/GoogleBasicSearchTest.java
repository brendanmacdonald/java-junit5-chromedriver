import Utils.Config;
import Model.GoogleSearchPage;
import Utils.ConfigHelper;
import Model.PageUtils;
import Utils.WebdriverHelper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@DisplayName("Google Tests")
public class GoogleBasicSearchTest {

    private static WebDriver driver;
    private Config config;
    private static GoogleSearchPage gsp;
    private static
    PageUtils pu;

    @BeforeAll
    public static void setUpConfig() {
        gsp = new GoogleSearchPage();
        pu = new PageUtils();
    }

    @BeforeEach
    public void setUpDriver() {
        config = ConfigHelper.getConfig();
        driver = WebdriverHelper.createDriver(config.getBrowserType());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("First Google Search test")
    public void search() {
        driver.get(gsp.getUrl());
        By searchBox = gsp.getSearchBox();
        driver.findElement(searchBox).sendKeys("ChromeDriver");
        driver.findElement(searchBox).submit();
        pu.waitForWebElementToBeClickable(driver, gsp.getLogo());
        assertEquals("ChromeDriver - Google Search", driver.getTitle());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
