package fun.justdevelops.web;

import fun.justdevelops.driver.TestDriverFactory;
import fun.justdevelops.pages.WikipediaWebPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WikipediaWebTest {

    private WebDriver driver;
    private WikipediaWebPage wikipediaPage;
    private static final String BASE_URL = "https://ru.wikipedia.org/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = TestDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        wikipediaPage = new WikipediaWebPage(driver);
    }

    @Test
    public void testMainPageLoadAndElementsDisplay() {
        Assert.assertTrue(wikipediaPage.isMainPageContentDisplayed(), "Содержимое главной страницы не отображается");
    }

    @Test
    public void testSearchFunctionality() {
        String searchQuery = "Россия";
        String expectedArticleTitle = "Россия";

        wikipediaPage.searchFor(searchQuery);

        String heading = wikipediaPage.getFirstHeadingText();

        Assert.assertEquals(heading, expectedArticleTitle, "Поиск провален. Ожидаемый заголовок: '" + expectedArticleTitle + "', полученный заголовок: " + heading);
    }

    @Test
    public void testRandomPageNavigation() {
        wikipediaPage.isMainPageContentDisplayed();
        String originalUrl = driver.getCurrentUrl();

        wikipediaPage.clickRandomPageLink();

        Assert.assertNotEquals(driver.getCurrentUrl(), originalUrl, "Переход к случайной странице не произошёл, как ожидалось");
    }

    @Test
    public void testSearchInputInteractivity() {
        wikipediaPage.isMainPageContentDisplayed();

        Assert.assertTrue(wikipediaPage.isSearchInputDisplayedAndEnabled(), "Поле поиска не отображается или не активно");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}