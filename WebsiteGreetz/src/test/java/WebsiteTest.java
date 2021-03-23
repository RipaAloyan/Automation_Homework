import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebsiteTest {
    private WebDriver driver;

    private void logIn(WebDriver driver) throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.greetz.nl/auth/login");
        Thread.sleep(3000);

        WebElement loginForm = driver.findElement(By.id("loginForm"));

        String email = "gggggg@ggg.com";
        String password = "ggggggg";
        loginForm.findElement(By.name("email")).sendKeys(email);
        loginForm.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("login-cta")).click();
        Thread.sleep(5000);
    }

    private boolean findFavorite(WebDriver driver, int countOfFavorites) {
        if (driver.findElements(By.className("center full-size")).size() > countOfFavorites)
            return true;

        return false;
    }

    @Test
    public void addAndCheckFavorite() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        logIn(driver);

        //ավելացնենք ֆավորիտ

        driver.get("https://www.greetz.nl/ballonnen/denken-aan");
        Thread.sleep(3000);

        WebElement item = driver.findElement(By.xpath("//a[@data-id='1142804226']/.."));
        WebElement favoriteItem = item.findElement(By.className("b-products-grid__item-action"));
        favoriteItem.click();

        //ստուգենք ավելացավ, թե չէ

        WebElement sideMenu = driver.findElement(By.xpath("//i[@class='page-header__navigation-item-icon b-icon b-icon-hamburger']"));
        sideMenu.click();

        WebElement favorite = driver.findElement(By.xpath("//span[@class='b-list--item-subject' and contains(text(), 'Favorieten')]"));
        favorite.click();

        //int countOfFavorites = 0;
        //Assert.assertTrue(findFavorite(driver, countOfFavorites));



    }

    @Test
    public void cardPriceX3() throws InterruptedException {
        //login
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        logIn(driver);

        driver.get("https://www.greetz.nl/kaarten/denken-aan");
        WebElement card = driver.findElement(By.xpath("//a[@ng-href='/kaarten/detail/greetz---zonnestralen-voor-jou---denken-aan/3000009185']"));
        card.click();
        WebElement input = driver.findElement(By.className("b-input--field ng-pristine ng-valid-min ng-valid-pattern ng-not-empty ng-valid ng-valid-required ng-touched"));

    }
}


