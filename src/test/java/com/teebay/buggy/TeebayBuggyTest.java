package com.teebay.buggy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TeebayBuggyTest {

    public static void main(String[] args) throws IOException {

        Properties props = new Properties();
        FileInputStream file = new FileInputStream("config.properties");
        props.load(file);


        String url = props.getProperty("WEBSITE_URL");
        String username = props.getProperty("USERNAME");
        String password = props.getProperty("PASSWORD");


        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        try {

            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


            WebElement webElement1 = driver.findElement(By.cssSelector("input[name='email']"));
            webElement1.clear();
            webElement1.sendKeys(username);

            WebElement webElement2 = driver.findElement(By.cssSelector("input[name='password']"));
            webElement2.clear();
            webElement2.sendKeys(password);

            driver.findElement(By.cssSelector("button[type='submit']")).click();


        }finally {
            driver.quit();
        }

    }

}
