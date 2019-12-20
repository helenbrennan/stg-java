package challenge3;

import copartSite.copartPageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class challenge3 {
    private copartPageObjects copart;
//    private By popularMakes = By.xpath("//*[@id='tabTrending']/div[1]//a");
//    public WebDriver driver;

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() throws Exception {
        copart = new copartPageObjects("chrome");
        copart.navigate();

    }


    @AfterClass
    public void stopClass() {
        copart.quit();

    }

    @AfterMethod()
    public void afterMethod() {
    }


    @Test()
    public void popularItemsMakesModels() throws Exception {
        List<List<String>> popularResults = copart.popularItems();
        for (List<String> result : popularResults)
            System.out.println(result.get(0) + " - " + result.get(1));
    }
}



