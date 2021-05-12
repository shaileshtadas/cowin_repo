package cowin;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SlotFinder 
{

	private WebDriver driver;
	@BeforeClass
	public void driverSetup() 
	{
		
		WebDriverManager.chromedriver().setup();

	}

	//@SuppressWarnings("deprecation")
	@Test(priority = 0)
	public void openChromeDriver() {
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.cowin.gov.in/home");

	}

	@Test(priority = 1)
	public void SearchByPincode() {
		driver.findElement(By.xpath("//*[@formcontrolname='pincode']")).sendKeys("444602");
		driver.findElement(By.xpath("//*[@class='pin-search-btn']")).click();
		driver.findElement(By.xpath("//label[contains(text(),'18+')]")).click();

	}

	@Test(priority = 2)
	public void slotStatus() {
		List<WebElement> slots = driver.findElements(By.xpath("//ul[@class='slot-available-wrap']/li"));
		Iterator<WebElement> itr = slots.iterator();
		while (itr.hasNext()) {
			WebElement ele = itr.next();
			System.out.println(ele.getText());
			if (ele.getText().contains("NA") || ele.getText().contains("Booked")) {
				System.out.println("No availability of vaccine");
			} else {
				System.out.println("Hey, you are lucky,run");
				Assert.assertFalse(true, ele.getText());
			}

		}
		if(slots.size()==0)
		{
			System.out.println("No vaccination centers available");
		}
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
