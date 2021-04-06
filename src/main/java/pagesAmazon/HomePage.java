package pagesAmazon;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
private final WebDriver driver;

private final String navbarID = "navbar";
@FindBy(id = navbarID)
    WebElement navbar;

private final String deliverLocationID = "glow-ingress-block";
@FindBy(id = deliverLocationID)
    WebElement deliverLocation;

private final String categoriesOfProductClass = "nav-search-facade";
@FindBy(className = categoriesOfProductClass)
    WebElement categoriesOfProduct;

private final String categoryBooksXPath =  "//*[@value='search-alias=stripbooks-intl-ship']";
@FindBy(xpath =categoryBooksXPath)
    WebElement categoryBooks;

private final String searchFieldXPath = "//input[@type='text' and @aria-label='Search']";
@FindBy(xpath = searchFieldXPath )
    WebElement searchField;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    PageFactory.initElements(driver, this);
    }

    public String getDeliverLocation(){
        return deliverLocation.getText().replace('\n', ' ');
    }

    public void chooseBooksFromCategories(){
        categoryBooks.click();
    }

    public void searchAuthor(String authorFullName){
        searchField.sendKeys(authorFullName);
        searchField.sendKeys(Keys.ENTER);
    }

    public void waitUntilPageLoads(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(navbarID)));
        }

}
