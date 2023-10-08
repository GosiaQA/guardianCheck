package pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//page url = https://www.theguardian.com/tone/news

public class GuardianLandingPage extends BasePage{

    private String titleXPath = "//*[@data-link-name='article']";
    private String dateXPath = "//time[@class='fc-date-headline']";
    private String popupAcceptButtonXPath = "//*[text()='Yes, I’m happy']";

    public GuardianLandingPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://www.theguardian.com/tone/news");
        //closePopup();
    }

    /***
     * Close cookies agreement popup if opened
     */
    private void closePopup(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(popupAcceptButtonXPath)));
        driver.findElement(By.xpath(popupAcceptButtonXPath)).click();
    }

    /***
     * Gets first found article element title (trims 'Live' prefix)
     * @return
     */
    public String getFirstNewsTitle(){
        List<WebElement> articles = driver.findElements(By.xpath(titleXPath));
        return articles.get(0).getText().replaceFirst("Live\n","");
    }

    /***
     * Returns date for news in format: YYYY-MM-DD
     * @return
     */
    public String getFirstNewsDate(){
        return driver.findElement(By.xpath(dateXPath)).getAttribute("datetime");
    }


}
