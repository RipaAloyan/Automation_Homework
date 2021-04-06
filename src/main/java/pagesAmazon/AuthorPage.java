package pagesAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AuthorPage {
    private final WebDriver driver;

    private final String priceClass = "a-size-base-plus a-color-price a-text-bold";
    @FindBy(className = priceClass)
    List<WebElement> prices;

    private final String booksItemsClass = "a-fixed-left-grid-inner";
    @FindBy(className = booksItemsClass)
    List<WebElement> booksItems;

    private final String priceFilterLowToHighXPath = "//*[@role='option']//child::a[contains(text(),'Low to High')]";
    @FindBy(xpath = priceFilterLowToHighXPath)
    WebElement priceFilterLowToHigh;

    private final String sortById = "sortBySelectors";
    @FindBy(id = sortById)
    WebElement sortBy;

    public AuthorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void choosePriceFilterLowToHigh() {
        sortBy.click();
        waitUntilPageLoads();
        priceFilterLowToHigh.click();
    }

    public void waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(booksItems));
    }

}