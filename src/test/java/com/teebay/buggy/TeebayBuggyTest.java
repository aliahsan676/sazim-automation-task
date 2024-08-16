package com.teebay.buggy;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class TeebayBuggyTest {

    public static void main(String[] args) {

        // Load properties file from resources directory
        Properties props = new Properties();
        try (InputStream input = TeebayBuggyTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            // Load the properties from the file
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Retrieve the values
        String url = props.getProperty("WEBSITE_URL");
        String username = props.getProperty("USERNAME");
        String password = props.getProperty("PASSWORD");

        // Set up the FirefoxDriver
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();


        try {
            // Navigate to the login page
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            // Perform login
            WebElement webElement1 = driver.findElement(By.cssSelector("input[name='email']"));
            webElement1.clear();
            webElement1.sendKeys(username);


            WebElement webElement2 = driver.findElement(By.cssSelector("input[name='password']"));
            webElement2.clear();
            webElement2.sendKeys(password);


            driver.findElement(By.cssSelector("button[type='submit']")).click();





        } finally {

            driver.quit();
        }
    }

}
