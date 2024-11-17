import base.BaseUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.Assert.assertTrue;

public class MyStepdefs extends BaseUtil {
    private BaseUtil baseUtil;

    public void Steps(BaseUtil util){
        this.baseUtil = util;
    }

    private WebDriver driver;

    @Before()
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Given("I am in the login page of the Para Bank Application")
    public void i_am_in_the_login_page_of_the_Para_Bank_Application() {

        driver.get("http://parabank.parasoft.com/parabank/index.htm");
    }

    @When("I enter valid {string} and {string} with {string}")
    public void i_enter_valid_credentials(String username, String password, String userFullName1) {
        baseUtil.userFullName = userFullName1;

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("username")).submit();
    }

    @Then("I should be taken to the Overview page")
    public void i_should_be_taken_to_the_Overview_page() throws Exception {
        Thread.sleep(3000);

        String actualFullName = driver.findElement(By.className("smallText")).getText().toString();
        System.out.println(baseUtil.userFullName.toString());
        assertTrue(actualFullName, actualFullName.contains(baseUtil.userFullName));
        driver.findElement(By.linkText("Log Out")).click();

        driver.quit();
    }

    @After()
    public void quitBrowser() {
        driver.quit();
    }
}
