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

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Question1 {

    private static WebDriver driver;
    private static WebDriverWait wait;
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
    }

    @Test
    public void login1() {
        String filePath = Paths.get("src", "main", "resources", "LoginPage (2).xml").toAbsolutePath().toString();
        driver.get("file:///" + filePath.replace("\\", "/"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kc-page-title")));
        driver.findElement(By.id("email")).sendKeys("test@example.com");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("kc-login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("invalid-email-message")));
        Assert.assertEquals("Invalid email: Please provide a valid email address.",driver.findElement(By.id("invalid-email-message")).getText());
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("test1@example.com");
        driver.findElement(By.id("kc-login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("invalid-email-message")));
        Assert.assertEquals("Enter correct email",driver.findElement(By.id("invalid-email-message")).getText());
    }

    @Test
    public void login2() {
        String filePath = Paths.get("src", "main", "resources", "LoginPage (2).xml").toAbsolutePath().toString();
        driver.get("file:///" + filePath.replace("\\", "/"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kc-page-title")));
        driver.findElement(By.id("email")).sendKeys("user@example.com");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("kc-login")).click();
        Assert.assertEquals("Success!",driver.findElement(By.id("success-message")).getText());
    }


    @After
    public void tearDown() {
        // Delete cookies and quit the driver
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
