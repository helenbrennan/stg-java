package challenge5;

import copartSite.copartPageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.*;

public class challenge5 {
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
    public void numbersOfPorscheModels() throws Exception {
        copart.search("porsche");
        copart.show100Entries();
        Thread.sleep(1000);
        List<String> results = copart.numberOfModels();
        Set<String> models = new HashSet<>(results);
        Map<String, Integer> modelsList = new HashMap<>();
        for (String model : models) {
            modelsList.put(model, 0);
        }
        for (String modelName : results) {
            modelsList.put(modelName, modelsList.get(modelName) + 1);
        }
        for (Map.Entry<String, Integer> entry : modelsList.entrySet()) {
            System.out.println("The Porsche model " + entry.getKey() + " is listed " + entry.getValue() + " times.");
        }
    }

    @Test()
    public void damageInfo() throws Exception {
        copart.search("porsche");
        copart.show100Entries();
        Thread.sleep(1000);
        List<String> results = copart.damages();
        HashMap<String, Integer> damageType = new HashMap<>();
        damageType.put("REAR END", 0);
        damageType.put("FRONT END", 0);
        damageType.put("MINOR DENT/SCRATCHES", 0);
        damageType.put("UNDERCARRIAGE", 0);
        damageType.put("MISC", 0);
        for (String damage : results) {
            switch (damage) {
                case "REAR END":
                    damageType.put("REAR END", damageType.get("REAR END") + 1);
                    break;
                case "FRONT END":
                    damageType.put("FRONT END", damageType.get("FRONT END") + 1);
                    break;
                case "MINOR DENT/SCRATCHES":
                    damageType.put("MINOR DENT/SCRATCHES", damageType.get("MINOR DENT/SCRATCHES") + 1);
                    break;
                case "UNDERCARRIAGE":
                    damageType.put("UNDERCARRIAGE", damageType.get("UNDERCARRIAGE") + 1);
                default:
                    damageType.put("MISC", damageType.get("MISC") + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : damageType.entrySet()) {
            System.out.println("The damage type " + entry.getKey() + " is listed " + entry.getValue() + " times.");
        }
    }
}
