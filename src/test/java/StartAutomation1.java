import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

    @AfterTest
    public void after() {
        driver.close();
    }

}

