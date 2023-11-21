package utils;

import Pages.Loginpage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StartupPage {
    //public WebDriver driver;
    public WebDriver driver;

    public List<String> str;

    public StartupPage(WebDriver driver) {
        this.driver = driver;

    }

    public Loginpage navigatetopage(String baseUrl) {
        //System.setProperty("webDriver.chrome.driver", "C:\\Users\\l\\OneDrive\\Desktop\\selenium session\\Stackinsights.pro\\src\\main\\java\\utils\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        options.addArguments("--remote-allow-origins=*");
        driver.get(baseUrl);
        driver.manage().window().maximize();
        return PageFactory.initElements(driver,Loginpage.class);
    }

    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public void wait(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitForWebElement(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public List<String> listOfGrid() throws InterruptedException {
        Thread.sleep(2000);
        wait(By.xpath("//table[@class='el-table__header']//div[@class='cell']"));
        List<WebElement> list = driver.findElements(By.xpath("//table[@class='el-table__header']//div[@class='cell']"));
        return list.stream().map(ele -> ele.getText()).collect(Collectors.toList());
    }

    public List<String> listOfServiceName() {
        wait(By.xpath("//tr[@class='el-table__row']//span[@class='link']"));
        List<WebElement> List = driver.findElements(By.xpath("//tr[@class='el-table__row']//span[@class='link']"));
        return List.stream().map(ele -> ele.getText()).collect(Collectors.toList());
    }

    public List<String> listOfServiceNamePageSecond() {
        wait(By.xpath("//tr[@class='el-table__row']//span[@class='link']"));
        driver.findElement(By.xpath("//button[@class='btn-next is-last']")).click();
        List<WebElement> List = driver.findElements(By.xpath("//tr[@class='el-table__row']//span[@class='link']"));
        return List.stream().map(ele -> ele.getText()).collect(Collectors.toList());
    }

    public void refresh() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    public void killDriver() {
        driver.quit();
    }

    public List<String> getGraphNameServicesPage() {
        wait(By.xpath("//div[@class='tab-layout']"));
        List<WebElement> graphName = driver.findElements(By.xpath("//div[@class='widget']/div[@class='header flex-h']//span[text()]"));
        return graphName.stream().map(ele -> ele.getText()).collect(Collectors.toList());
    }

    public void clickOnAnyServiceName(String name) {
        wait(By.xpath("//tr[@class='el-table__row']//span[@class='link']"));
        List<WebElement> List = driver.findElements(By.xpath("//tr[@class='el-table__row']//span[@class='link']"));
        for (WebElement element : List) {
            if (element.getText().equals(name)) {
                element.click();
                break;
            }
        }
    }

    public List<String> getTabName() {
        wait(By.xpath("//input[@placeholder='Please input']"));
        List<String> TabName = new ArrayList<>();
        List<WebElement> names = driver.findElements(By.xpath("//input[@placeholder='Please input']"));

        for (WebElement element : names) {
            TabName.add(element.getAttribute("value"));
        }
        return TabName;
    }

    public void navigateToUrl(String url, String endPoint) {
        driver.navigate().to(url + endPoint);
    }
    public void clickOnToggleOn() {
        wait(By.xpath("//div[@class='el-switch el-switch--default']"));
        WebElement toggle = driver.findElement(By.xpath("//div[@class='el-switch el-switch--default']"));
        if (toggle.getAttribute("aria-checked").equals("false")) {
            wait(By.xpath("//div[@class='el-switch el-switch--default']/span"));
            driver.findElement(By.xpath("//div[@class='el-switch el-switch--default']/span")).click();
        }
    }

    public void clickOnToggleOff() {
        WebElement toggle = driver.findElement(By.xpath("//div[@class='el-switch el-switch--default is-checked']"));
        if (toggle.getAttribute("aria-checked").equals("true")) {
            wait(By.xpath("//div[@class='el-switch el-switch--default is-checked']/span"));
            driver.findElement(By.xpath("//div[@class='el-switch el-switch--default is-checked']/span")).click();
        }
    }
    public String getToggleTextMessage() {
        wait(By.xpath("//p[@class='el-message__content']"));
        WebElement element= driver.findElement(By.xpath("//p[@class='el-message__content']"));
        return element.getText();
    }
    public void waitForPopUpDisabled(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }
    public void PopUpDisabled(){
        WebElement element= driver.findElement(By.xpath("//p[@class='el-message__content']"));
        waitForPopUpDisabled(element);
    }

    public void clickOnKebabIcon() throws InterruptedException {
        wait(By.xpath("//span[@class='icon-operation']"));
        driver.findElement(By.xpath("//span[@class='icon-operation']")).click();
        Thread.sleep(2000);
    }
    public List<String> getListOfOptionsOnKebabMenu(){

        List<WebElement> list=driver.findElements(By.xpath("//span[@class='edit-tab']/../../li/span"));
        return list.stream().map(ele ->ele.getText()).collect(Collectors.toList());
    }
    public void clickOnTab(String Name) throws InterruptedException {

        wait(By.xpath("//input[@placeholder='Please input']"));
        List<WebElement> names = driver.findElements(By.xpath("//input[@placeholder='Please input']"));
        for (WebElement element : names) {
            if (element.getAttribute("value").equals(Name)) {
                Thread.sleep(2000);
                element.click();
                break;
            }
        }
    }
    public void clickOnAnyServiceInstance() throws InterruptedException {
        wait(By.xpath("//span[@class='link']"));
        Thread.sleep(2000);
        WebElement Inst=driver.findElement(By.xpath("//span[@class='link']"));
        Inst.click();
    }
}
