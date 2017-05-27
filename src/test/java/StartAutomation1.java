import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static jdk.nashorn.internal.objects.NativeMath.random;

/**
 * Created by gryzyuka on 21.05.2017.
 */
public class StartAutomation1 {
    WebDriver driver;

    @BeforeTest
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gryzyuka\\.m2\\repository\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void firstTest() throws InterruptedException {
        driver.get("http://magento.brainacad.com/");
        driver.findElement(By.cssSelector("#search")).sendKeys("PC games");
        Thread.sleep(5000);
    }

    @Test
    public void secondTest() throws InterruptedException {
        driver.get("http://magento.brainacad.com/english/customer/account/create/");
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Tester");
        final String email = random(10) + "@gmail.com";
        driver.findElement(By.cssSelector("#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("#password")).sendKeys("password123");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("password123");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("div.buttons-set > button")).click();
        System.out.println(email);
    }

    @Test
    public void thirdTest() throws InterruptedException {
        driver.get("http://magento.brainacad.com/english/customer/account/login/");
        driver.findElement(By.cssSelector("#email")).sendKeys("0.9704557751281363@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("password123");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#send2")).click();
    }

    @AfterTest
    public void after() {
        driver.close();
    }

}

