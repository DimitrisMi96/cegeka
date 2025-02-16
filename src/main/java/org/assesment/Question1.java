package org.assesment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Question1 {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private Random random;
    private ChromeOptions options;


    @Before
    public void setup() {
        // Automatically download and set up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Set up Chrome options to enable incognito mode
        options = new ChromeOptions();
        options.addArguments("--incognito");  // Launch Chrome in Incognito mode

        // Initialize ChromeDriver with the options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Initialize WebDriverWait and Random
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        random = new Random();
    }

    @Test
    public void login1() {
        driver.get("file:///C:/Users/Jim/Desktop/LoginPage.xml");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kc-form-login")));
        driver.findElement(By.id("username")).sendKeys("xxx");
        driver.findElement(By.id("password")).sendKeys("yyy");
        driver.findElement(By.id("kc-login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("")));

    }



    @After
    public void tearDown() {
        // Delete cookies and quit the driver
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
