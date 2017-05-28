import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;

import static jdk.nashorn.internal.objects.NativeMath.random;

/**
 * Created by gryzyuka on 28.05.2017.
 */
public class SignUp {
    final String email = random(10) + "@gmail.com";
    WebDriver driver;

    @BeforeTest
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gryzyuka\\.m2\\repository\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://magento.brainacad.com/english/customer/account/create/");
    }

    @Test
    public void signUp() throws InterruptedException {
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("#password")).sendKeys("password123");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("password123");
        driver.findElement(By.cssSelector("div.buttons-set > button")).click();
        System.out.println(email);
        Thread.sleep(5000);
    }

    @Test
    public void signUpEmptyFields() throws InterruptedException {
        driver.findElement(By.cssSelector("div.buttons-set > button")).click();
        driver.findElement(By.cssSelector(".input-text.validation-failed")).getCssValue("border-color").contains("red");
        driver.findElements(By.xpath("//div[@class=\"validation-advice\"]"));
        java.util.List<WebElement> validation = driver.findElements(By.xpath("//div[@class=\"validation-advice\"]"));
        Iterator<WebElement> iter = validation.iterator();
        while (iter.hasNext()) {
            WebElement item = iter.next();
            item.getText().contains("This is a required field.");
            item.getCssValue("font-color").contains("red");
            System.out.println(item.getAttribute("id"));
        }
        Thread.sleep(5000);
    }

    @Test
    public void signUpExistingEmail() throws InterruptedException {
        driver.findElement(By.cssSelector("#firstname")).sendKeys("TesterI");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("TesterI");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("testermakv@gmail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("testpass");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("testpass");
        driver.findElement(By.cssSelector("div.buttons-set > button")).click();
        driver.findElement(By.cssSelector(".error-msg")).getText().contains("There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.");
        driver.findElement(By.cssSelector("a[href=\"http://magento.brainacad.com/english/customer/account/forgotpassword/\"]")).getText().contains("click here");
        Thread.sleep(5000);
    }

    @Test
    public void signUpPasswordConfirmationWrong() throws InterruptedException {
        driver.findElement(By.cssSelector("#firstname")).sendKeys("TesterI");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("TesterI");
        driver.findElement(By.cssSelector("#email_address")).sendKeys(" testermakvI@gmail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("testpass");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("testpassI");
        driver.findElement(By.cssSelector("div.buttons-set > button")).click();
        driver.findElement(By.cssSelector("#confirmation")).getCssValue("border-color").contains("red");
        driver.findElement(By.cssSelector("#advice-validate-cpassword-confirmation")).getCssValue("font-color").contains("red");
        driver.findElement(By.cssSelector("#advice-validate-cpassword-confirmation")).getText().contains("Please make sure your passwords match.");
        Thread.sleep(5000);
    }

    @AfterTest
    public void after() {
        driver.get("http://magento.brainacad.com/english/customer/account/logout/");
        driver.close();
    }
}
