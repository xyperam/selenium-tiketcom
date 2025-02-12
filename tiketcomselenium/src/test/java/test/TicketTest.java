package test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.PesawatPage;

public class TicketTest {
	WebDriver driver;
	HomePage homePage;
	PesawatPage pesawatPage;
@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.tiket.com/");		
		homePage = new HomePage(driver);
		pesawatPage = new PesawatPage(driver);
	}
@DataProvider(name = "airportNames")
public Object[][] provideAirportNames() {
    return new Object[][] {
        {"Halim","Ngurah"},
    };
}
@Test(dataProvider = "airportNames")
public void testSearchFlight(String derparture,String destination) throws InterruptedException {
	homePage.clickFlight();
	Thread.sleep(100);
	pesawatPage.inputDerparture(derparture);
	Thread.sleep(1000);
	pesawatPage.inputDestination(destination);
	Thread.sleep(1000);
	pesawatPage.inputDate();
	Thread.sleep(1000);
	pesawatPage.pickPassanger();
}

@AfterClass
public void tearDown() {
	driver.quit();
}
}
