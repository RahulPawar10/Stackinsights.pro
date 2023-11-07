package utils;

import Pages.Loginpage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        return PageFactory.initElements(driver, Loginpage.class);
    }

    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public void wait(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<String> listOfGrid() {
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
        Thread.sleep(2000);
        driver.navigate().refresh();
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
        List<String> ABC = new ArrayList<>();
        List<WebElement> names = driver.findElements(By.xpath("//input[@placeholder='Please input']"));

        for (WebElement element : names) {
            ABC.add(element.getAttribute("value"));
        }
        return ABC;
    }

    public void navigateToUrl(String url, String endPoint) {
        driver.navigate().to(url + endPoint);
    }
}
