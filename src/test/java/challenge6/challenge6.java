package challenge6;

import copartSite.copartPageObjects;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class challenge6 {
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
    public void errorHandlingModelSearch() throws Exception {
        try {
            copart.search("nissan");
            copart.modelFilteringSearch("skyline");
            System.out.println("");
            System.out.println("The Skyline model is listed.");
        } catch (NoSuchElementException e) {
            System.out.println("Model not found, taking screenshot.");
            File scr = ((TakesScreenshot) copart.getDriver()).getScreenshotAs(OutputType.FILE);
            String filename = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss '.png'").format(new Date());
            File dest = new File("./screenshots/" + filename);
            FileUtils.copyFile(scr, dest);

        }


    }
}


