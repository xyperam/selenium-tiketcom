package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	WebDriver driver;
	//constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//locator
	By flightButton = By.xpath("//img[@alt='Tiket Pesawat']");
	
	
	//Click Flight Button method
	public void clickFlight() {
		driver.findElement(flightButton).click();
	}
}
