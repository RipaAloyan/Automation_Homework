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

    private boolean findFavorite(int countOfFavorites) throws InterruptedException {
        Thread.sleep(5000);
        return driver.findElements(By.className("full-size")).size() > countOfFavorites;
    }


    @Test
    public void addAndCheckFavorite() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        logIn(driver);

        //եթե կա, ջնջենք
        driver.get("https://www.greetz.nl/mygreetz/favorieten");
        Thread.sleep(5000);
        if(driver.findElement(By.xpath("//button[@class='b-icon_medium b-icon-delete']")).isDisplayed()) {
            WebElement deleteFavorite = driver.findElement(By.xpath("//button[@class='b-icon_medium b-icon-delete']"));
            deleteFavorite.click();
            WebElement okDelete = driver.findElement(By.xpath("//span[contains(text(), 'Ok')]"));
            okDelete.click();
            }



        driver.get("https://www.greetz.nl/ballonnen/denken-aan");
        Thread.sleep(3000);
        WebElement targetItem = driver.findElement(By.xpath("//a[@data-id='1142804226']"));

        WebElement targetElementFavoriteItem = targetItem.findElement(By.xpath("//parent::div//child::a[@class='b-products-grid__item-action']"));
        targetElementFavoriteItem.click();

        WebElement sideMenu = driver.findElement(By.xpath("//i[@class='page-header__navigation-item-icon b-icon b-icon-hamburger']"));
        sideMenu.click();

        WebElement favorite = driver.findElement(By.xpath("//span[@class='b-list--item-subject' and contains(text(), 'Favorieten')]"));
        favorite.click();

        int countOfFavorites = 0;
        Assert.assertTrue(findFavorite(countOfFavorites));
        driver.quit();
    }

    @Test
    public void priceForTwoCards() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        logIn(driver);

        driver.get("https://www.greetz.nl/kaarten/denken-aan");
        Thread.sleep(5000);
        WebElement card = driver.findElement(By.xpath("//div[@class='b-products-grid__item'][2]"));
        card.click();


        Thread.sleep(5000);
        WebElement input = driver.findElement(By.xpath("//input[@name='amount']"));
        input.clear();
        input.sendKeys(Keys.NUMPAD2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='price-total' and contains(text(), '5,90')]")).isDisplayed());
        driver.quit();
    }
}
