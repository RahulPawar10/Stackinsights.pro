package test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//import Pages.Homepage;
import Pages.Loginpage;
import Pages.Services;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;
import utils.StartupPage;


public class Stackinsights_test {
    public WebDriver driver;
    public StartupPage startupPage;
    public ConfigReader configReader;
    public Loginpage loginpage;
    public SoftAssert softAssert;

    public Services services;

    @BeforeClass
    public void setupmethod() throws IOException, InterruptedException {
        softAssert = new SoftAssert();
        loginpage = new Loginpage(driver);
        startupPage = new StartupPage(driver);
        services=new Services(driver);
        configReader = new ConfigReader();
        loginpage = startupPage.navigatetopage(configReader.properties("baseUrl"));
        loginpage.navigatetologin(configReader.properties("username"), configReader.properties("password"));
    }

    @Test(description = "User should navigate to services page inside GS module and verify the table grid name and services names"
            , testName = "TC-01")
    public void VerifyTheGridNameAndServiceNameOnGeneralServicePage() throws Exception {
        try {
            startupPage.refresh();
            startupPage.navigateToUrl(configReader.properties("baseUrl"), "General-Service/Services");


            //verify the Grid Name
            softAssert.assertEquals(startupPage.listOfGrid(), Arrays.asList("Service Groups", "Service Names", "Load (calls / min)", "Success Rate (%)", "Latency (ms)", "Apdex"),"Grid Name Not matched");

            //Verify the service Name second page
            List<String>serviceName=startupPage.listOfServiceName();
            softAssert.assertEquals(serviceName, Arrays.asList("agent::python-app","apache::tomcat-server", "demo::java-app", "humancloud::resume-maker", "piggymetrics::account-service", "piggymetrics::turbine-stream", "piggymetrics::monitoring","piggymetrics::notification-service","piggymetrics::gateway","piggymetrics::config"),"Services name not Matched");

            List<String>serviceNamePageTwo=startupPage.listOfServiceNamePageSecond();
            softAssert.assertEquals(serviceNamePageTwo, Arrays.asList("piggymetrics::auth-service","piggymetrics::registry","piggymetrics::statistics-service"),"Services name not Matched");

            //Reload the page
            startupPage.refresh();

            //click on Any services name
            startupPage.clickOnAnyServiceName("agent::python-app");

            //verify the General-Service page graphs
            softAssert.assertEquals(startupPage.getGraphNameServicesPage(),Arrays.asList("Service Apdex", "Success Rate", "Service Load", "Service Avg Response Time (ms)","Service Apdex", "Service Response Time Percentile (ms)", "Success Rate (%)", "Service Load (calls / min)", "Message Queue Consuming Count", "Message Queue Avg Consuming Latency (ms)", "Service Instances Load (calls / min)", "Slow Service Instance (ms)", "Service Instance Success Rate (%)", "Endpoint Success Rate in Current Service (%)", "Slow Endpoints in Current Service (ms)", "Endpoint Load in Current Service (calls / min)"),"graphs Name Not Matched");

            //Verify the Tab Name on General-Service page
            softAssert.assertEquals(startupPage.getTabName(),Arrays.asList("Overview", "Instance", "Endpoint", "Topology", "Trace", "Trace Profiling", "eBPF Profiling", "Log"),"Tab Name not matched");
            softAssert.assertAll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test(description = "Verify the dropdown value should be present inside the service , names inside kebab menu options inside the service tab , Toggle Btn and after clicking on Toggle Btn and get massage and add tab btn is displayed and verify the menu option is displayed"
            , testName = "TC-02")
    public void VerifyTheServiceNameInsideTheDropdownAndVerifyKebabMenuOptionsAndMenuOptions() throws Exception {
        try {
            Thread.sleep(2000);
            startupPage.refresh();

            startupPage.navigateToUrl(configReader.properties("baseUrl"), "dashboard/GENERAL/Service/YWdlbnQ6OnB5dGhvbi1hcHA=.1/General-Service");

            startupPage.clickOnToggleOn();
            System.out.println(startupPage.getToggleTextMessage());
            softAssert.assertEquals(startupPage.getToggleTextMessage(), "You are entering edit mode","text Message not matched");
            startupPage.PopUpDisabled();

            startupPage.clickOnToggleOff();
            System.out.println(startupPage.getToggleTextMessage());
            softAssert.assertEquals(startupPage.getToggleTextMessage(), "You are entering view mode","text Message not matched");

            startupPage.PopUpDisabled();
            startupPage.clickOnToggleOn();

            startupPage.PopUpDisabled();
            startupPage.clickOnKebabIcon();

            List<String> list=startupPage.getListOfOptionsOnKebabMenu();
            softAssert.assertEquals(list,Arrays.asList("Enable editing tab names","Delete"),"Kebab menu option are not matched");

            softAssert.assertAll();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Verify the service Instance on instance tab,Navigate to service Instance details page  and Verify the service Instance details , Verify the Toggle Btn and after cliking on Toggle Btn and get massage,Verify the Tab should be displayed inside the services instance details page,Verify the graph should be displayed inside the services instance details page inside the overview Tab"
            , testName = "TC-03")
    public void VerifyTheDetailsOfInstanceTabAndVerifyTheToggleBtnAndVerifyTheGraphs() throws Exception {
        try {
            Thread.sleep(2000);
            startupPage.refresh();

            startupPage.navigateToUrl(configReader.properties("baseUrl"), "dashboard/GENERAL/Service/YWdlbnQ6OnB5dGhvbi1hcHA=.1/General-Service");

            startupPage.clickOnTab("Instance");
            startupPage.clickOnAnyServiceInstance();
            //Verify the Tabs
            softAssert.assertEquals(startupPage.getTabName(),Arrays.asList("Overview", "Trace", "Log", "JVM",".NET", "Spring Sleuth", "Golang","PVM"),"Tab Name not matched");

            System.out.println(startupPage.getGraphNameServicesPage());
            //verify the General-Service page graphs
            softAssert.assertEquals(startupPage.getGraphNameServicesPage(),Arrays.asList("Service Instance Latency (ms)","Service Instance Success Rate (%)", "Service Instance Load (calls / min)", "Database Connection Pool", "Thread Pool"),"graphs Name Not Matched");


            startupPage.clickOnToggleOn();
            System.out.println(startupPage.getToggleTextMessage());
            softAssert.assertEquals(startupPage.getToggleTextMessage(), "You are entering edit mode","text Message not matched");
            startupPage.PopUpDisabled();

            startupPage.clickOnToggleOff();
            System.out.println(startupPage.getToggleTextMessage());
            softAssert.assertEquals(startupPage.getToggleTextMessage(), "You are entering view mode","text Message not matched");

            startupPage.PopUpDisabled();
            startupPage.clickOnToggleOn();

            softAssert.assertAll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Verify the service Endpoint on Endpoint tab ,Navigate to service Endpoint details page  and Verify the service Endpoint details,Verify the graph should displayed and graph value should be visible ,Verify the Toggle Btn and after clicking on Toggle Btn and get massage and Verify the Tab should be displayed inside the services Endpoint details page"
            , testName = "TC-04")
    public void VerifyTheGraphShouldBeDisplayedAndVerifyTheTabs() throws Exception {
        try {
            Thread.sleep(2000);
            startupPage.refresh();

            startupPage.navigateToUrl(configReader.properties("baseUrl"), "dashboard/GENERAL/Service/YWdlbnQ6OnB5dGhvbi1hcHA=.1/General-Service");
            startupPage.clickOnTab("Endpoint");
            startupPage.clickOnAnyServiceInstance();

            //Verify the Tabs
            softAssert.assertEquals(startupPage.getTabName(),Arrays.asList("Overview", "Topology", "Trace", "Log"),"Tab Name not matched");

            //verify the General-Service page graphs
            System.out.println(startupPage.getGraphNameServicesPage());
            softAssert.assertEquals(startupPage.getGraphNameServicesPage(),Arrays.asList("Endpoint Response Time Percentile (ms)", "Endpoint Avg Response Time (ms)", "Message Queue Avg Consuming Latency (ms)", "Endpoint Success Rate (%)", "Endpoint Load (calls / min)"),"graphs Name Not Matched");

            startupPage.clickOnToggleOn();
            System.out.println(startupPage.getToggleTextMessage());
            softAssert.assertEquals(startupPage.getToggleTextMessage(), "You are entering edit mode","text Message not matched");
            startupPage.PopUpDisabled();

            startupPage.clickOnToggleOff();
            System.out.println(startupPage.getToggleTextMessage());
            softAssert.assertEquals(startupPage.getToggleTextMessage(), "You are entering view mode","text Message not matched");

            startupPage.PopUpDisabled();
            startupPage.clickOnToggleOn();


            softAssert.assertAll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @AfterClass()
    public void teardown() {
        // startupPage.killDriver();
    }

}
