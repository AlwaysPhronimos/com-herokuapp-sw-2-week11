package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() throws InterruptedException {
        // Enter “tomsmith” username
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith");
        Thread.sleep(4000);
        // Enter “SuperSecretPassword!” password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        Thread.sleep(4000);
        // click on Login button
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        Thread.sleep(4000);
        // Verify the text “Secure Area”
        String expectedText = "Secure Area";
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'Secure Area')]"));
        String elementText = element.getText();
        Assert.assertEquals(elementText, expectedText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() throws InterruptedException {
        //Enter username
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        Thread.sleep(4000);
        //Enter password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        Thread.sleep(4000);
        // Click on ‘LOGIN’ button
        driver.findElement(By.name("login")).click();
        Thread.sleep(4000);

        //Verify the error message “Your username is invalid !”
        String expectedText = "Your username is invalid!";
        String actualText = driver.findElement(By.xpath("//div[@id='flash-messages']")).getText();
        Assert.assertEquals(expectedText, actualText);
        Thread.sleep(4000);
    }

    @Test
    public void verifyThePasswordErrorMessage() throws InterruptedException {
        //Enter username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        Thread.sleep(4000);
        //Enter password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        Thread.sleep(4000);
        // Click on ‘LOGIN’ button
        driver.findElement(By.name("login")).click();
        Thread.sleep(4000);

        String expectedText = "Your password is invalid!";
        WebElement element = driver.findElement(By.xpath("//div[contains(@class,'flash') and text()='Your password is invalid!']"));
        String elementText = element.getText();
        Assert.assertEquals(elementText, expectedText);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
