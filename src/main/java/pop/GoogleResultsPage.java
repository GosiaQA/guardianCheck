package pop;

import org.openqa.selenium.*;

import java.util.List;

public class GoogleResultsPage extends BasePage{

    private String resultsNoXPath = "//*[@id='result-stats']";
    public GoogleResultsPage(WebDriver driver) {
        super(driver);
    }


    /***
     * Gets results count from Google results page and returns it as an int
     * @return
     */
    public int getResultsCount(){
        String count = driver.findElement(By.xpath(resultsNoXPath))
                        .getText()
                        .replace("About ","")
                        .replace(",","")
                        .replace(" results","");
        int index = count.indexOf(" ");
        count = count.substring(0,index);
        return Integer.parseInt(count);
    }

    /***
     * There are at least one span with 'days ago' or 'hours ago' or 'minutes ago' text which is not from Guardian page
     * @return
     */
    public boolean dateIsPresent(){
        List<WebElement> spanElems;
        String guardianURL = "https://www.theguardian.com";
        try{
            spanElems = driver.findElements(By.xpath("//span[contains(text(), 'days ago')]"));
            checkForURLInParent(spanElems, guardianURL);
        }catch (NoSuchElementException eDays){
            try{
                spanElems = driver.findElements(By.xpath("//span[contains(text(), 'hours ago')]"));
                checkForURLInParent(spanElems,guardianURL);
            }catch (NoSuchElementException eHours){
                try{
                    spanElems = driver.findElements(By.xpath("//span[contains(text(), 'minutes ago')]"));
                    checkForURLInParent(spanElems,guardianURL);
                }catch (NoSuchElementException eMinutes){
                    //no span with '(..) ago' and without guardian URL found
                    return false;
                }
            }
        }

        // at least one span with '(..) ago' and without guardian URL found
        return true;

    }


}
