package org.anutestframework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.anutestframework.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {


    public static void main(String[] args) throws InterruptedException {
        String productName= "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        LoginPage loginPage= new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        loginPage.loginApplication("anuTest@gmail.com", "Welcome@123");
        driver.findElement(By.id("userEmail")).sendKeys("anuTest@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Welcome@123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod= products.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

       wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
       Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']"))).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);


        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        //Thread.sleep(5000);
        String confirmMsg= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText();
        Assert.assertTrue(confirmMsg.trim().equalsIgnoreCase("Thankyou for the order."));

    }
}
