import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class SeleniumDevTest {

    @Test
    public void lastVersion_3_141_59() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/");
        Thread.sleep(1000);

        WebElement download = driver.findElement(By.xpath("//*[@id=\"navbar\"]/a[1]"));
        download.click();


        WebElement latestStableVersion = driver.findElement(By.xpath("//p[contains(text(), 'Latest stable version')]/child::a[contains(text(), '3.141.59')]"));
        //եթե գտավ՝ փասա, եթե չգտավ ՝ ֆեյլա
        Thread.sleep(3000);

        driver.quit();
    }


    /*@Test
    public void searchSeleniumWebdriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/");

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys("selenium webdriver" + Keys.ENTER);

        ArrayList <WebElement> resultsOfSearch = new ArrayList<>();
        resultsOfSearch.add(driver.findElement(By.xpath("[class=\"ec_ styleable-rootcontainer c_\"]")));
        //չեմ կարում էլեմենտների վրով ֆռամ որ համեմատեմ սպասածիս հետ

         }*/

}
