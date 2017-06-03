import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.objects.NativeMath.random;




/**
 * Created by gryzyuka on 02.06.2017.
 */
public class Dashboard {
    WebDriver driver;
    SignUp signUp;
    final String firstName = "Test" + random(1);
    final String lastName = "Test" + random(1);
    final String newEmail = random(1)+"@gmail.com";

    @BeforeTest
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gryzyuka\\.m2\\repository\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.setProperty("signUp", "F:\\My Projects\\MagentoMainAcad\\src\\test\\java\\SignUp.java");
        signUp = new SignUp();
    }

    @Test
    public void logIn() throws InterruptedException {
        driver.get("http://magento.brainacad.com/english/customer/account/login/");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#email")).sendKeys("testermakv@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("testpass");
        driver.findElement(By.cssSelector("#send2")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title")).getText().contains("MY DASHBOARD"));
    }

    @Test (dependsOnMethods = {"SignUp.signUp"})
    public void EditPersonalInfo() throws InterruptedException {
        driver.get("http://magento.brainacad.com/english/customer/account/login/");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#email")).sendKeys(SignUp.email);
        driver.findElement(By.cssSelector("#pass")).sendKeys(SignUp.password);
        driver.findElement(By.cssSelector("#send2")).click();
        driver.findElement(By.cssSelector("div.box-title>a[href=\"http://magento.brainacad.com/english/customer/account/edit/\"]")).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title")).getText().contains("ACCOUNT INFORMATION"));
        driver.findElement(By.cssSelector("#firstname")).clear();
        driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#lastname")).clear();
        driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys(newEmail);
        driver.findElement(By.cssSelector("#change_password")).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#current_password")).sendKeys(SignUp.password);
        driver.findElement(By.cssSelector("#password")).sendKeys("testpass1");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("testpass1");
        driver.findElement(By.cssSelector("div.buttons-set > button")).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector(".success-msg")).getText().contains("The account information has been saved."));
        Assert.assertTrue(driver.findElement(By.cssSelector(".hello")).getText().contains("Hello, "+firstName+" "+lastName+"!"));
    }

    @AfterMethod
    public void Method() {
        driver.get("http://magento.brainacad.com/english/customer/account/logout/");
    }

    @AfterTest
    public void after() {
        driver.close();
    }

}