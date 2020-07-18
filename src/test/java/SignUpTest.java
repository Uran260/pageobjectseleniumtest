import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SignUpTest {
    WebDriver driver;
    SignUpPage signUpPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Валентин\\IdeaProjects\\testgroup\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://github.com/join");
        signUpPage = new SignUpPage(driver);
    }
    @Test
    public void signUpWithShortPass(){
        SignUpPage sp = signUpPage.typePassword("dfg");
        String error = sp.getPasswordErrorText();
        Assert.assertEquals("Make sure it's at least 15 characters OR at least 8 characters including a number and a lowercase letter. Learn more.", error);
    }
    @Test
    public void signUpUUnavailableUsernameTest(){
        SignUpPage sp = signUpPage.typeUserName("username");
        String error = sp.getUsernameErrorText();
        Assert.assertEquals("Username 'username' is unavailable.", error);
    }
    @Test
    public void signUpIncorrectUsernameTest(){
        SignUpPage sp = signUpPage.typeUserName("Uran_260");
        String error = sp.getUsernameErrorText();
        Assert.assertEquals("Username may only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen.", error);
    }
    @Test
    public void getHeadingTest(){
        String heading = signUpPage.getHeadingText();
        Assert.assertEquals("Join GitHub", heading);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
