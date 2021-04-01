package seleniumTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;

public class SixPMTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    @BeforeSuite
    public void setUps() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.6pm.com");
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addToBag() {
        By accessoriesInMenuBarLoc = By.xpath("//a[@href='/c/accessories']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(accessoriesInMenuBarLoc));
        WebElement accessoriesInMenuBar = driver.findElement(accessoriesInMenuBarLoc);
        action.moveToElement(accessoriesInMenuBar).perform();

        By aviators = By.xpath("//a[contains(text(), 'Aviators')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(aviators));
        try {
            WebElement aviatorsSunglassesButton = accessoriesInMenuBar.findElement(aviators);
            aviatorsSunglassesButton.click();
        } catch (NoSuchElementException e) {
            return;
        }
        By allSunglassesLoc = By.xpath("//a[@class='YU-z']");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(allSunglassesLoc, 20));

        List<WebElement> allSunglasses = driver.findElements(allSunglassesLoc);
        WebElement thisSunglasses = GreetzLnTest.randomElement(allSunglasses);
        thisSunglasses.click();

        String expectedAddress = driver.getPageSource();
        By addToShoppingBagButtonLoc = By.xpath("//button[@data-track-value='Add-To-Cart']");
        //String expectedName = driver.findElement(By.xpath("//div[@itemprop='name']")).getText();
        //String expectedPrice = driver.findElement(By.xpath("//span[@class='Ko-z']")).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToShoppingBagButtonLoc));
        WebElement addToShoppingBagButton = driver.findElement(addToShoppingBagButtonLoc);
        addToShoppingBagButton.click();

        driver.get("https://www.6pm.com/cart");
        By itemLoc = By.xpath("//div[@class='rt-z zd-z Yq-z']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemLoc));
        //List <WebElement> name = driver.findElements(By.xpath("//a[@data-te='TE_CART_PRODUCTCLICKED']"));
        //String actualName = name.get(1).getText();
        driver.findElement(itemLoc).click();

        String actualAddress = driver.getPageSource();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualAddress, expectedAddress, "Sorry, addresses  don't match.");
    }
    @AfterMethod
    public void dltFromBag(){
        driver.get("https://www.6pm.com/cart");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Quantity')]")));
        WebElement quantity = driver.findElement(By.xpath("//select[@name='quantity']"));
        quantity.click();
        driver.findElement(By.xpath("//option['remove']")).click();
        driver.quit();
    }
}
