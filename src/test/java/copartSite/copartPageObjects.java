package copartSite;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class copartPageObjects {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait wait1;
    private By searchBox = By.id("input-search");
    private By searchButton = By.xpath("//*[@id='search-form']/div/div[2]/button");
    private By makeColumn = By.xpath("//*[@id='serverSideDataTable']//tr/td[5]");
    private By modelColumn = By.xpath("//*[@id='serverSideDataTable']//tr/td[6]");
    private By damageColumn = By.xpath("//*[@id='serverSideDataTable']//tr/td[12]");
    private By twentiethItem = By.cssSelector("#serverSideDataTable > tbody > tr:nth-child(20)");
    private By popularMakes = By.xpath("//*[@id='tabTrending']/div[1]//a");
    private By listOfResults = By.id("serverSideDataTable");
    private By dropDown = By.xpath("//*[@id=\"serverSideDataTable_length\"]/label/select");
    private By maxItems = By.cssSelector("#serverSideDataTable > tbody > tr:nth-child(100)");
    private By modelFilter = By.xpath("//*[@id=\"filters-collapse-1\"]/div[1]/ul/li[4]/h4/a[1]");
    private By modelSearchBox = By.xpath("//*[@id=\"collapseinside4\"]/form/div/input");
    private By skylineClickBox = By.cssSelector("abbr[value='Skyline']");
    private By searchResults = By.xpath("//*[@id='mainBody']/div[1]/div/div[1]/div[1]/div/div[2]/div[3]/h1/span[2]");

    public copartPageObjects(String browser) {
        driver = buildDriver(browser);
        wait = new WebDriverWait(driver, 10);
        wait1 = new WebDriverWait(driver, 1);
    }

    private WebDriver buildDriver(String browser) {
        WebDriver newDriver = null;
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
                newDriver = new ChromeDriver();
                newDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                newDriver.manage().window().maximize();
                break;
            case "firefox":
                System.setProperty("webdriver.geckodriver.driver", "./bin/geckodriver.exe");
                newDriver = new FirefoxDriver();
                newDriver.manage().window().maximize();
            default:
                System.out.println("Please use either chrome or Firefox.");
        }
        return newDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quit() {
        driver.quit();
    }

    public void waitForSearchResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
    }

    public String getSearchText() {
        return driver.findElement(searchResults).getText();
    }
    public String getPageURL(){
    return driver.getCurrentUrl();
    }

    public void navigate() {
        driver.get("https://www.copart.com");
    }

    public void search(String whatToSearchFor) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(whatToSearchFor);
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(searchResults), whatToSearchFor));

    }

    public List<String> findMakes() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(twentiethItem));
        List<WebElement> makes = driver.findElements(makeColumn);
        List<String> allMakes = new ArrayList<>();
        for (WebElement make : makes) {
            allMakes.add(make.getText());
        }
        return allMakes;
    }

    public List<List<String>> popularItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(popularMakes));
        List<WebElement> popular = driver.findElements(popularMakes);
        List<List<String>> results = new ArrayList<>();
        for (int i = 0; i < popular.size(); i++) {
            List<String> result = new ArrayList<>();
            result.add(popular.get(i).getText());
            result.add(popular.get(i).getAttribute("href"));
            results.add(result);
        }
        return results;
    }

    public void show100Entries() {
        Select dropDown = new Select(driver.findElement(By.xpath("//*[@id=\"serverSideDataTable_length\"]/label/select")));
        dropDown.selectByValue("100");
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.listOfResults));
        WebElement maxItems = driver.findElement(By.cssSelector("#serverSideDataTable > tbody > tr:nth-child(100)"));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(maxItems)));
    }

    public List<String> numberOfModels() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(twentiethItem));
        List<WebElement> allModels = driver.findElements(modelColumn);
        List<String> modelsResults = new ArrayList<>();
        for (WebElement model : allModels) {
            modelsResults.add(model.getText());
        }
        return modelsResults;
    }

    public List<String> damages() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(twentiethItem));
        List<WebElement> damaged = driver.findElements(damageColumn);
        List<String> damagedList = new ArrayList<>();
        for (WebElement damage : damaged) {
            damagedList.add(damage.getText());
        }
        return damagedList;
    }

    public void modelFilteringSearch(String modelToSearch) {
        wait.until(ExpectedConditions.elementToBeClickable(modelFilter));
        driver.findElement(modelFilter).click();
        driver.findElement(modelSearchBox).sendKeys(modelToSearch);
        driver.findElement(skylineClickBox).click();
    }


}


