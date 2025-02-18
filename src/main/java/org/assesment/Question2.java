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

public class Question2 {

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
    public void randomRadioButtonPicker() {

        //Path logic since xml file is placed in the project resources.
        String filePath = Paths.get("src", "main", "resources", "QuestionnariePage.xml").toAbsolutePath().toString();
        driver.get("file:///" + filePath.replace("\\", "/"));

        // Wait for element visibility
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_idt39:j_idt43")));
        Assert.assertEquals("Under Appraisal / Operation Active", driver.findElement(By.id("j_idt39:j_idt43")).getText());

        // Create a list that contains all radio buttons
        List<WebElement> radioButtons = driver.findElements(By.xpath("//*[local-name()='input' and @type='radio' and (@value='Y' or @value='N')]"));

        // Randomly select either "Yes" or "No" for each question
        for (WebElement radioButton : radioButtons) {
            // Check if the radio button is not selected
            if (!radioButton.isSelected()) {
                // Randomly click either Yes or No
                if (random.nextBoolean() && radioButton.getAttribute("value").equals("Y")) {
                    radioButton.click();
                } else if (!random.nextBoolean() && radioButton.getAttribute("value").equals("N")) {
                    radioButton.click();
                }
            }
        }
        System.out.println("All radio buttons have been assigned option");
    }

    @After
    public void tearDown() {
        // Delete cookies and quit the driver
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
