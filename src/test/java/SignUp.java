import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.objects.NativeMath.random;

/**
 * Created by gryzyuka on 02.06.2017.
 */
public class SignUp {
    WebDriver driver;
    static final String email = random(1) + "@gmail.com";
    static String password = "password123";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gryzyuka\\.m2\\repository\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("http://magento.brainacad.com/english/customer/account/create/");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void signUp() throws InterruptedException {
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("div.buttons-set > button")).click();
        System.out.println(email);
        Assert.assertEquals("my dashboard", driver.findElement(By.cssSelector(".page-title")).getText().toLowerCase());
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
            Assert.assertEquals("This is a required field.", item.getText());
            Assert.assertEquals("rgba(255, 0, 0, 1)", item.getCssValue("color"));
            ;
            System.out.println(item.getAttribute("id"));
        }
    }

    @Test
    public void signUpExistingEmail() throws InterruptedException {
        driver.findElement(By.cssSelector("#firstname")).sendKeys("TesterI");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("TesterI");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("testermakv@gmail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("testpass");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("testpass");
        driver.findElement(By.cssSelector("div.buttons-set > button")).click();
        Assert.assertEquals("There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.", driver.findElement(By.cssSelector(".error-msg")).getText());
        Assert.assertEquals("click here", driver.findElement(By.cssSelector("a[href=\"http://magento.brainacad.com/english/customer/account/forgotpassword/\"]")).getText());
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
        Assert.assertEquals("rgba(255, 0, 0, 1)", driver.findElement(By.cssSelector("#advice-validate-cpassword-confirmation")).getCssValue("color"));
        Assert.assertTrue(driver.findElement(By.cssSelector("#advice-validate-cpassword-confirmation")).getText().contains("Please make sure your passwords match."));
    }

    @AfterMethod
    public void Method() {
        driver.get("http://magento.brainacad.com/english/customer/account/logout/");
    }

    @AfterClass
    public void after() {
        driver.close();

    }
}
