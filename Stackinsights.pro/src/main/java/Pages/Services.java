package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.StartupPage;

import java.util.List;
import java.util.stream.Collectors;

public class Services extends StartupPage{
    StartupPage startupPage=new StartupPage(driver);
    public Services(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

/*    public List<String> listOfGrid(){
        List<WebElement> list=driver.findElements(By.xpath("//table[@class='el-table__header']//div[@class='cell']"));
        return list.stream().map(ele ->ele.getText()).collect(Collectors.toList());
    }*/
}
