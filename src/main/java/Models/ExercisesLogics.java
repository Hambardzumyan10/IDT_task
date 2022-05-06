package Models;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExercisesLogics extends UtilsAuthPractic {

    public ExercisesLogics() {
        PageFactory.initElements(getDriver(), this);
    }

    private final By Sales = By.cssSelector("#homefeatured>li>div>.right-block>.content_price>.price-percent-reduction");
    private final By ProductsCount = By.cssSelector("#homefeatured>li");
    private final By ContinueShopping = By.cssSelector(".button-container>span");
    private final By Shopping_cart = By.cssSelector(".shopping_cart>a");


    public void GetAllSaleProducts() throws InterruptedException {
        System.setProperty(LoginPass.Chrome, LoginPass.ChromePath);
        setDriver();
        //getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().get("http://automationpractice.com/index.php");
        getDriver().manage().window().maximize();
        Actions action = new Actions(getDriver());
        Clickable(Sales);
        if (getSaleIndexAndAddList().size()>0){
            for (int i = 0; i < getSaleIndexAndAddList().size(); i++) {   //getSaleNumbersAndAddList().size()  --  talisa qani hat sale ka ejum
                action.moveToElement(getDriver().findElements(Sales).get(i)).perform(); //hover sale product
                Thread.sleep(400); //sorry :)
                JavascriptExecutor executor = (JavascriptExecutor) getDriver();
                WebElement SaleAddToCart = getDriver().findElements(By.cssSelector("#homefeatured>li>div>.right-block"))
                        .get(getSaleIndexAndAddList().get(i)).findElement(By.cssSelector(".button-container>a"));
                executor.executeScript("arguments[0].click();", SaleAddToCart);
                Click(ContinueShopping);
            }
            Click(Shopping_cart);
            System.out.println("All sale products add in Shopping List");
        }else {
            System.out.println("No products are discounted");
        }
    }
    public List<Integer> getSaleIndexAndAddList() {  //List-i mech Adda anum , te vor indexi taka sale-shor@
        List<Integer> numList
                = new ArrayList<Integer>();
        int ProductCount = getDriver().findElements(ProductsCount).size();
        for (int u = 0; u < ProductCount; u++) {
            try {
                getDriver().findElements(By.cssSelector("#homefeatured>li")).get(u)
                        .findElement(By.cssSelector("div>.right-block>.content_price>.price-percent-reduction"));  //ete ka Sale ira index@ adda anum numList-i mech
                numList.add(u);
            } catch (Exception NotVisibleSaleProduct) {

            }
        }
        return numList;
    }
}