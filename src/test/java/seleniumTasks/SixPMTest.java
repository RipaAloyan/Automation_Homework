
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

public class SixPMTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    @BeforeMethod
    public void setUps() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.6pm.com");
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 15);
    }

    @Test
    public void addToBag(){
        By accessoriesInMenuBarLoc = By.xpath("//a[@href='/c/accessories']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(accessoriesInMenuBarLoc));
        WebElement accessoriesInMenuBar = driver.findElement(accessoriesInMenuBarLoc);
        action.moveToElement(accessoriesInMenuBar).perform();

        By aviators = By.xpath("//a[contains(text(), 'Aviators')]");
        wait.until(ExpectedConditions.elementToBeClickable(aviators));

            WebElement aviatorsSunglassesButton = accessoriesInMenuBar.findElement(aviators);
            aviatorsSunglassesButton.click();

        By allSunglassesLoc = By.xpath("//*[@class='searchPage']//child::article");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(allSunglassesLoc, 20));

        List<WebElement> allSunglasses = driver.findElements(allSunglassesLoc);
        WebElement thisSunglasses = GreetzLnTest.randomElement(allSunglasses);
        thisSunglasses.click();

        By addToShoppingBagButtonLoc = By.xpath("//button[@data-track-value='Add-To-Cart']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(addToShoppingBagButtonLoc));

        String expectedAddress = driver.getCurrentUrl();
        WebElement addToShoppingBagButton = driver.findElement(addToShoppingBagButtonLoc);
        wait.until(ExpectedConditions.elementToBeClickable(addToShoppingBagButtonLoc));
        addToShoppingBagButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@action='/checkout/initiate']")));

        driver.get("https://www.6pm.com/cart");
        By itemLoc = By.xpath("//a[@data-te='TE_CART_PRODUCTCLICKED']");
        wait.until(ExpectedConditions.elementToBeClickable(itemLoc));
        driver.findElements(itemLoc).get(1).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
        String actualAddress = driver.getCurrentUrl();

        Assert.assertEquals(actualAddress, expectedAddress, "Sorry, addresses  don't match.");
    }

    @AfterMethod
    public void dltFromBag(){
        driver.get("https://www.6pm.com/cart");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Quantity')]")));
        WebElement quantity = driver.findElement(By.xpath("//select[@name='quantity']"));
        quantity.click();
        driver.findElement(By.xpath("//option['remove']")).click();
        driver.quit();
    }
}
