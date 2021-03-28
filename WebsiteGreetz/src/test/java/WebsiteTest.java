import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class WebsiteTest {
    private WebDriver driver;

    @BeforeMethod
    public void logIn() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
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

    @AfterMethod
    public void close(){
        driver.quit();
    }

    private boolean findFavorite(int countOfFavorites) throws InterruptedException {
        Thread.sleep(5000);
        return driver.findElements(By.className("full-size")).size() > countOfFavorites;
    }

    @Test
    public void addAndCheckFavorite() throws InterruptedException {
        driver.get("https://www.greetz.nl/mygreetz/favorieten");
        Thread.sleep(5000);
        if(driver.findElement(By.xpath("//button[@class='b-icon_medium b-icon-delete']")).isDisplayed()) {
            WebElement deleteConfirmationButton = driver.findElement(By.xpath("//button[@class='b-icon_medium b-icon-delete']"));
            deleteConfirmationButton.click();
            WebElement okDelete = driver.findElement(By.xpath("//span[contains(text(), 'Ok')]"));
            okDelete.click();
            }
        driver.get("https://www.greetz.nl/ballonnen/denken-aan");
        Thread.sleep(3000);
        WebElement targetItem = driver.findElement(By.xpath("//a[@data-id='1142804226']"));

        WebElement targetElementFavouritebutton = targetItem.findElement(By.xpath("//parent::div//child::a[@class='b-products-grid__item-action']"));
        targetElementFavouritebutton.click();

        WebElement sideMenu = driver.findElement(By.xpath("//i[@class='page-header__navigation-item-icon b-icon b-icon-hamburger']"));
        sideMenu.click();

        WebElement favourite = driver.findElement(By.xpath("//span[@class='b-list--item-subject' and contains(text(), 'Favorieten')]"));
        favourite.click();

        int countOfFavorites = 0;
        Assert.assertTrue(findFavorite(countOfFavorites));
    }

    @Test
    public void priceForTwoCards() throws InterruptedException {
        driver.get("https://www.greetz.nl/kaarten/denken-aan");
        Thread.sleep(5000);
        WebElement card = driver.findElement(By.xpath("//div[@class='b-products-grid__item'][2]"));
        card.click();


        Thread.sleep(5000);
        WebElement input = driver.findElement(By.xpath("//input[@name='amount']"));
        input.clear();
        input.sendKeys("2");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='price-total' and contains(text(), '5,90')]")).isDisplayed());
    }
}
