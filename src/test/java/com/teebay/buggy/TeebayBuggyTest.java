package com.teebay.buggy;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
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


            // Create product
            driver.findElement(By.cssSelector("button[class^='ui blue button']")).click();

            WebElement webElement3 = driver.findElement(By.cssSelector("input[name='title']"));
            webElement3.clear();
            webElement3.sendKeys("Football");



            WebElement dropdownElement = driver.findElement(By.cssSelector("div[name='categories']"));
            dropdownElement.click();

            WebElement optionElement1 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/form/div[2]/div/div/div[1]/span"));
            optionElement1.click();


            WebElement webElement4 = driver.findElement(By.cssSelector("textarea[name='description']"));
            webElement4.clear();
            webElement4.sendKeys("This is a large size Football.");


            WebElement webElement5 = driver.findElement(By.cssSelector("input[name='purchase_price']"));
            webElement5.clear();
            webElement5.sendKeys("500");


            WebElement webElement6 = driver.findElement(By.cssSelector("input[name='rent_price']"));
            webElement6.clear();
            webElement6.sendKeys("200");


            WebElement optionElement2 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/form/div[4]/div[2]/div[2]/div/div[2]/div[4]/span"));
            optionElement2.click();


            driver.findElement(By.cssSelector("button[class='ui blue button']")).click();


            // Update Product

            driver.findElement(By.cssSelector("div[class=\"sc-fqkvVR jQvrZo\"]")).click();
            WebElement webElement7 = driver.findElement(By.cssSelector("input[name='purchase_price']"));
            webElement7.clear();
            webElement7.sendKeys("900");


            driver.findElement(By.cssSelector("button[class='ui blue button']")).click();


            // Delete Product
            driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div[1]/div/div[1]/div[1]/button/i")).click();

            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/button[2]")).click();

            driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[1]/div/div[1]/div[1]/button")).click();

            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/button[2]")).click();

            // Validate deleting all products from “My Products”
            List<WebElement> productsAfterDeletion = driver.findElements(By.cssSelector(".product-item-class"));
            if (productsAfterDeletion.isEmpty()) {
                System.out.println("All products have been successfully deleted.");
            } else {
                System.out.println("Some products are still present on the product page.");
            }

            //Validate functionality of the Search filter on “Browse Products”

            driver.findElement(By.xpath("/html/body/div/div[1]/a[2]")).click();

            WebElement webElement8 = driver.findElement(By.cssSelector("input[name='title']"));
            webElement8.clear();
            webElement8.sendKeys("Lawn Mower");



            WebElement dropdownElement2 = driver.findElement(By.cssSelector("div[name='categories']"));
            dropdownElement2.click();

            WebElement optionElement3 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/div/form/div[2]/div/div[2]/div[5]/span"));
            optionElement3.click();


            driver.findElement(By.cssSelector("button[class='ui blue button']")).click();


            // Validate view count change on visit for products in “Browse Products"


            driver.findElement(By.xpath("/html/body/div/div[1]/a[2]")).click();
            WebElement selectedProduct = products.get(productIndex);
            WebElement viewCountElement = selectedProduct.findElement(By.cssSelector(".view-count-class"));
            int initialViewCount = Integer.parseInt(viewCountElement.getText());

            driver.findElement(By.cssSelector("div[class=\"sc-fqkvVR jQvrZo\"]")).click();
            driver.navigate().back();
            driver.navigate().refresh();

            WebElement updatedViewCountElement = updatedProduct.findElement(By.cssSelector(".view-count-class"));
            int updatedViewCount = Integer.parseInt(updatedViewCountElement.getText());

            if (updatedViewCount == initialViewCount + 1) {
                System.out.println("View count has successfully incremented.");
            } else {
                System.out.println("View count did not increment as expected.");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {

            driver.quit();
        }
    }

}
