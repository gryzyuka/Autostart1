/**
 * Created by gryzyuka on 27.05.2017.
 */
public class Ffgf
{

    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver.get("http://www.yakaboo.ua");
        System.out.println(webDriver.getTitle());
    }
}
