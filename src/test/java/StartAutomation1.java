import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by gryzyuka on 21.05.2017.
 */
public class StartAutomation1 {
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver.get("http://www.yakaboo.ua");
        System.out.println(webDriver.getTitle());
    }
}
