package linkedin.practiceSelenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class form {

	public static WebDriver driver;
	
	
	@FindBy(id="first-name")
	private WebElement FName;
	
	@FindBy(id="last-name")
	private WebElement LName;
	
	@FindBy(id="job-title")
	private WebElement jTitle;
	
	@FindBy(css=".input-group input[id=\"radio-button-1\"]")
	private WebElement edu;
	
	@FindBy(css="#checkbox-2")
	private WebElement sex;
	
	@FindBy(id="select-menu")
	private WebElement exp;
	
	@FindBy(id="datepicker")
	private WebElement date;
	
	@FindBy(css="div a[class='btn btn-lg btn-primary']")
	private WebElement submit;

	@FindBy(css=".alert.alert-success")
	private WebElement message;
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://formy-project.herokuapp.com/form");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@Test
	public void fillform() {
		FName.sendKeys("Mathu");
		LName.sendKeys("Bala");
		jTitle.sendKeys("QA");
		edu.click();
		sex.click();
		Select s = new Select(exp);
		s.selectByValue("3");
		date.sendKeys("04/12/2023");
		submit.click();
		String expected = "The form was successfully submitted!";
		String actual = message.getText();
		Assert.assertEquals(actual, expected);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
}
