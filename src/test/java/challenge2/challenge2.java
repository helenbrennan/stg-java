package challenge2;


import copartSite.copartPageObjects;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class challenge2 {
    private copartPageObjects copart;
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


    @Test()
    public void searchForExotics() throws Exception {
        copart.search("exotics");
        List<String> allMakes = copart.findMakes();
//        System.out.println(allMakes.toString());
        Assert.assertTrue(allMakes.contains("PORSCHE"));
    }
}

