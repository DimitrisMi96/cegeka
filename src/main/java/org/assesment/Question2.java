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

    Random random = new Random();

    ChromeOptions options = new ChromeOptions();

    @Before
    public void setup() {
        // Automatically download and set up ChromeDriver
        WebDriverManager.chromedriver().setup();
        // Set up Chrome options to enable incognito mode
        options.addArguments("--incognito");  // Launch Chrome in Incognito mode
        // Initialize ChromeDriver with the options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testAssessment() {
        driver.get("file:///C:/Users/Jim/AppData/Local/Temp/%7B3ED5BD84-4ACD-44FD-A750-4DBCFA8E0D60%7D/%7BC3250C1F-B661-4528-8E92-7975B136173E%7D/QuestionnariePage.xml");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_idt39:j_idt43")));
        Assert.assertEquals("Under Appraisal / Operation Active", driver.findElement(By.id("j_idt39:j_idt43")).getText());
        //Create a list that contains all radio buttons
        List<WebElement> radioButtons = driver.findElements(By.xpath("//*[local-name()='input' and @type='radio' and (@value='Y' or @value='N')]"));

        for (WebElement radioButton : radioButtons) {
            if (random.nextBoolean()) {
                if (radioButton.getAttribute("value").equals("Y") && !radioButton.isSelected()) {
                    radioButton.click();
                }
            } else {
                if (radioButton.getAttribute("value").equals("N") && !radioButton.isSelected()) {
                    radioButton.click();
                }
            }
        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
