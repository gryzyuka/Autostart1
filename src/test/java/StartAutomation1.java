import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public void search() throws InterruptedException {
        driver.get("http://magento.brainacad.com/");
        driver.findElement(By.cssSelector("#search")).sendKeys("PC games");
        driver.findElement(By.cssSelector(".button.search-button")).click();
//must add checking of search results appearance
        Thread.sleep(5000);
    }

    @Test
    public void searchInvalid() throws InterruptedException {
        driver.get("http://magento.brainacad.com/");
        driver.findElement(By.cssSelector("#search")).sendKeys("@#$%^&*(");
        driver.findElement(By.cssSelector(".button.search-button")).click();
        driver.findElement(By.cssSelector(".page-title")).getText().contains("SEARCH RESULTS FOR '@#$%^&*('");
        driver.findElement(By.cssSelector(".note-msg")).getText().contains("Your search returns no results.");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#search")).clear();
        driver.findElement(By.cssSelector("#search")).sendKeys("4356526272");
        driver.findElement(By.cssSelector(".button.search-button")).click();
        driver.findElement(By.cssSelector(".page-title")).getText().contains("SEARCH RESULTS FOR '4356526272'");
        driver.findElement(By.cssSelector(".note-msg")).getText().contains("Your search returns no results.");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#search")).clear();
        driver.findElement(By.cssSelector("#search")).sendKeys("брюки");
        driver.findElement(By.cssSelector(".button.search-button")).click();
        driver.findElement(By.cssSelector(".page-title")).getText().contains("SEARCH RESULTS FOR");
        driver.findElement(By.cssSelector(".note-msg")).getText().contains("Your search returns no results.");
        Thread.sleep(5000);
    }

    @Test
    public void logIn() throws InterruptedException {
        driver.get("http://magento.brainacad.com/english/customer/account/login/");
        driver.findElement(By.cssSelector("#email")).sendKeys("testermakv@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("TESTERPASS2017");
        driver.findElement(By.cssSelector("#send2")).click();
        Thread.sleep(5000);
//logOut for correct work of further tests
        driver.get("http://magento.brainacad.com/english/customer/account/logout/");
    }

    @AfterTest
    public void after() {
        driver.close();
    }

}

