package pop;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /***
     * Refreshes the current page
     */
    public void refresh(){
        driver.navigate().refresh();
    }

    /***
     * Scrolls down to given element
     * @param element
     */
    public void scrollTo(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0]. scrollIntoView(true);", element);
    }

    /***
     * Returns the ancestor for given child element
     * @param child
     * @param level - if <=0 null will be returned
     * @return
     */
    public WebElement getAncestor(WebElement child, int level){
        WebElement parentElement = null;

        for (int i = 0; i < level; i++){
            parentElement = child.findElement(By.xpath("./.."));
        }

        return parentElement;
    }

    /***
     * Checks if url existis in any of items from the given list
     * @param list
     * @param url
     */
    public void checkForURLInParent(List<WebElement> list, CharSequence url){
        WebElement parentElement;
        String xpath = "//*[not(contains(@href,'" + url + "'))]";
        for(WebElement child:list){
            //try to find Guardian page in parent element
            parentElement = getAncestor(child, 4);
            parentElement.findElement(By.xpath(xpath));
        }
    }
}
