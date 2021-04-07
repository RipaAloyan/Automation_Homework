package pagesAmazon;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;

public class SearchAuthor {
    private final WebDriver driver;

    //private final String navbarID = "navbar";
    //@FindBy(id = navbarID)
    //WebElement navbar;

    private final String booksXPath = "//div[@class='s-include-content-margin s-border-bottom s-latency-cf-section']";
    @FindBy(xpath = booksXPath)
    List<WebElement> books;

    private final String authorNameClass = "//*[@class='a-row']/child::a[@class='a-size-base a-link-normal']";
    @FindBy(xpath = authorNameClass)
    List <WebElement> booksAuthors;

    private final String mainContentClass = "sg-col-inner";
    @FindBy(className = mainContentClass)
    WebElement mainContent;

    public SearchAuthor(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForPageLoads(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(mainContent));
        wait.until(ExpectedConditions.visibilityOfAllElements(books));
        }

    public int getCountOfBooks(){
        return books.size();
    }

    public List<WebElement> getBooksAuthors(){
     return booksAuthors;
    }
}
