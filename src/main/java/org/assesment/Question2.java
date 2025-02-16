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
    public void testAssessment() {
        driver.get("file:///C:/Users/Jim/AppData/Local/Temp/%7B3ED5BD84-4ACD-44FD-A750-4DBCFA8E0D60%7D/%7BC3250C1F-B661-4528-8E92-7975B136173E%7D/QuestionnariePage.xml");

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
