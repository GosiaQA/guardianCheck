package pop;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


// page_url = https://www.google.com/

public class GoogleSearchPage extends BasePage{

    @FindBy(xpath = "//*[@title='Search']")
    private WebElement searchText;
    @FindBy(xpath = "//div[text()='Accept all']")
    private WebElement acceptAllButton;
    private String acceptAllButtonText = "//div[text()='Accept all']";

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://www.google.com");
        closePopup();
    }

    /***
     * Closes privacy popup if opened
     */
    private void closePopup(){
        acceptAllButton = driver.findElement(By.xpath(acceptAllButtonText));
        if (acceptAllButton != null) {
            scrollTo(acceptAllButton);
            acceptAllButton.click();
        }
    }

    public void search(String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea")));
        searchText = driver.findElement(By.xpath("//textarea"));
        searchText.sendKeys(text+ Keys.ENTER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
