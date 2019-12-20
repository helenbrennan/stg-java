package challenge7;

import copartSite.copartPageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

public class challenge7 {
    private copartPageObjects copart;

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
    public void checkPopularMakesModels() throws Exception {
        List<List<String>> popularResults = copart.popularItems();
        for (List<String> result : popularResults) {
            String modelsForSearchResults = result.get(0).replace(" ", "-");
            copart.getDriver().get(result.get(1));
            WebDriverWait wait = new WebDriverWait(copart.getDriver(), 10);
            copart.waitForSearchResults();
            Assert.assertTrue(copart.getSearchText().toLowerCase().contains(modelsForSearchResults.toLowerCase()), String.format("Comparing search result string '%s' and popular item '%s'", copart.getSearchText(), modelsForSearchResults));
            Assert.assertTrue(copart.getPageURL().contains(modelsForSearchResults.toLowerCase()), String.format("Comparing url '%s' and popular item '%s'", copart.getPageURL(), modelsForSearchResults));
            Assert.assertTrue(copart.getPageURL().substring(result.get(1).length()).contains(modelsForSearchResults.toLowerCase()), String.format("Comparing URL parameters '%s' and popular items '%s'", copart.getPageURL().substring(result.get(1).length()), modelsForSearchResults));
        }
    }
}
