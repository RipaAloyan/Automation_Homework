package seleniumTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class GreetzLnTest {
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

    @Test
    public void addAndCheckFavoriteTest() throws InterruptedException {
        driver.get("https://www.greetz.nl/ballonnen/denken-aan");
        Thread.sleep(5000);
        List<WebElement> targetItems = driver.findElements(By.xpath("//*[@class='b-products-grid__item']"));

        WebElement targetItem = randomElement(targetItems);
        Thread.sleep(3000);
        String expectedName = targetItem.findElement(By.className("b-products-grid__item-title")).getText();
        String expectedPrice = targetItem.findElement(By.xpath("//*[@data-qa-ref='normal-price']")).getText();
        Thread.sleep(3000);
        WebElement targetElementFavouriteButton = targetItem.findElement(By.xpath("//descendant::div[@class='b-favourite']"));
        targetElementFavouriteButton.click();
        Thread.sleep(2000);

        driver.get("https://www.greetz.nl/mygreetz/favorieten");
        Thread.sleep(5000);
        driver.findElement(By.className("full-size")).click();
        Thread.sleep(3000);
        String actualName = driver.findElement(By.xpath("//*[@ng-bind='gift.title']")).getText();
        String actualPrice = driver.findElement(By.className("price-normal")).getText();

        String msg1 = "Names don't match.";
        String msg2 = "Prices don't match";
        resultAnalise(actualName,expectedName,actualPrice, expectedPrice, msg1, msg2);

        dltFavorite();
    }

    @Test(priority = 1)
    public void priceForTwoCardsTest () throws InterruptedException, ParseException {
        driver.get("https://www.greetz.nl/kaarten/denken-aan");
        Thread.sleep(5000);
        List <WebElement> cards = driver.findElements(By.xpath("//div[@class='b-products-grid__item']"));
        WebElement theCard = randomElement(cards);
        Thread.sleep(2000);
        theCard.click();
        Thread.sleep(4000);

        WebElement input = driver.findElement(By.xpath("//input[@name='amount']"));
        input.clear();
        input.sendKeys("2");
        Thread.sleep(4000);

        String priceOfCard = driver.findElement(By.xpath("//*[@class='price-card price-block']/child::span/child::span[@data-qa-ref='normal-price']")).getText();
        double expectedResult = parse(priceOfCard, 1)*2;
        Thread.sleep(3000);

        String bill = driver.findElement(By.xpath("//*[@class='price-total']")).getText();
        double actualResult = parse(bill, 2);
        Thread.sleep(2000);

        Assert.assertEquals(expectedResult, actualResult, "Prices don't match");
    }

    public static int randomNumber ( int max){
        Random random = new Random();
        return random.nextInt(max);
    }
    public static WebElement randomElement (List < WebElement > list) {
        return list.get(randomNumber(list.size()));
    }
    private void resultAnalise(String actualName, String expectedName,String actualPrice, String expectedPrice,String  msg1,String  msg2){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualName, expectedName,msg1);
        softAssert.assertEquals(actualPrice, expectedPrice,msg2);
    }
    private void dltFavorite()throws InterruptedException{
        driver.get("https://www.greetz.nl/mygreetz/favorieten");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@class='b-icon_medium b-icon-delete']"));
        WebElement deleteConfirmationButton = driver.findElement(By.xpath("//button[@class='b-icon_medium b-icon-delete']"));
        deleteConfirmationButton.click();
        WebElement okDelete = driver.findElement(By.xpath("//span[contains(text(), 'Ok')]"));
        okDelete.click();
    }
    public double parse(String s, int ind) {
        String subSt = s.split(" ")[ind];
        String result = subSt.replace(',', '.');
        return Double.parseDouble(result);
    }
}