package test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.PesawatPage;

public class TicketTest {
	WebDriver driver;
	HomePage homePage;
	PesawatPage pesawatPage;
@BeforeClass(alwaysRun = true)
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.get("https://www.tiket.com/");		
		homePage = new HomePage(driver);
		pesawatPage = new PesawatPage(driver);
	}

@BeforeMethod(alwaysRun = true)
public void navigateToHomePage() {
    driver.get("https://www.tiket.com/");
}
@DataProvider(name = "airportNames")
public Object[][] provideAirportNames() {
    return new Object[][] {
        {"Halim","Ngurah"},
        {"Halim","Changi"}
    };
}
@Test(priority= 1, groups = "endtoend",dataProvider = "airportNames")
public void testSearchFlight(String derparture,String destination) throws InterruptedException {
	try {		
		Reporter.log("Test dimulai: Mencari penerbangan dari " + derparture + " ke " + destination, true);
		homePage.clickFlight();
		Thread.sleep(100);
		pesawatPage.inputDerparture(derparture);
		Thread.sleep(1000);
		pesawatPage.inputDestination(destination);
		Thread.sleep(1000);
		pesawatPage.inputDate();
		Thread.sleep(1000);
		pesawatPage.pickPassenger();
		pesawatPage.clickSubmitPassenger();
		pesawatPage.clickButtonSearch();
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + actualUrl);
		Assert.assertTrue(actualUrl.startsWith("https://www.tiket.com/pesawat/search"),"Failed: Halaman pencarian tidak terbuka, URL: " + actualUrl);
		Reporter.log("Test berhasil: Pencarian penerbangan berhasil dilakukan", true);		
	} catch(Exception e) {
		captureScreenshot("testSearchFlight");
		Assert.fail("Test gagal karena:"+ e.getMessage());
	}
}

@Test (priority = 2,groups = "component")
public void maxPassenger() {
	try {		
		homePage.clickFlight(); 
		pesawatPage.pickPassenger();
	}catch(Exception e) {
		captureScreenshot("maxPassenger");
		Assert.fail("Tes gagal karena: "+ e.getMessage());
	}
}

@AfterMethod
public void takeScreenshotOnFailure(ITestResult result) {
    if (ITestResult.FAILURE == result.getStatus()) {
        captureScreenshot(result.getName());
    }
}

@AfterClass
public void tearDown() {
	driver.quit();
}

public void captureScreenshot(String methodName) {
	try {
		  if (driver == null) {
		        Reporter.log("Gagal mengambil screenshot: driver tidak tersedia", true);
		        return;
		    }
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String filepath = "test-output/screenshots/"+ methodName +"_"+timestamp+".png";
		
		FileUtils.copyFile(source,new File(filepath));
		Reporter.log("Screenshoot disimpan: "+filepath,true);
		
	}catch(IOException e){
		Reporter.log("Gagal mengambil screenshot: "+ e.getMessage(),true);
	}
}
}
