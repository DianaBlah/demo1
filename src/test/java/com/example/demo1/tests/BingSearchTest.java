package com.example.demo1.tests;

import com.example.demo1.pages.MainPage;
import com.example.demo1.pages.ResultPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;

public class BingSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultsTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultPage rp = new ResultPage(driver);
        rp.clickElement(0);
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Открылась другая ссылка");
    }

    @Test
    public void searchFieldTest() {
        String input = "Selenium";

        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultPage rp = new ResultPage(driver);

        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }

    //@Test
    //public void searchResultsTest1() {
       // String input = "Selenium";
       // MainPage mp = new MainPage(driver);
      //  mp.sendText(input);

       // WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
       // By cite = By.cssSelector(":not(.b_adurl) > cite");


        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        //wait.until(ExpectedConditions.and(
        //        ExpectedConditions.textToBePresentInElementLocated(cite, "selenium"),
        //        ExpectedConditions.elementToBeClickable(cite)));

       // List<WebElement> results = driver.findElements(cite);

      //  clickElement(results, 0);
      //  ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
       // driver.switchTo().window(tabs.get(0));

      //  String currentUrl = driver.getCurrentUrl();
      //  assertEquals("https://www.selenium.dev/", currentUrl, "Открылась другая ссылка");
   // }
}



