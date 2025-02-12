package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PesawatPage {
	WebDriver driver;
	
	public PesawatPage(WebDriver driver) {
		this.driver = driver;
	}
	//locators
	By derpartureInput = By.xpath("//div[@data-testid='clickable-departure-input']//div");
	By destinationInput = By.xpath("//div[@data-testid='clickable-arrival-input']//div");
	By searchBoxInputDerparture = By.xpath("//div[@class='SearchBox_placeholder__7OmSD']//input");
	By searchBoxInputDestination = By.xpath("//div[@class='SearchBox_placeholder__7OmSD']//label//input");
	public By getDropdownSearchResult(String airportName) {
		
		return By.xpath("//div[@class='List_center_side__hMEYn']//span[contains(text(),'"+airportName+"')]");
	}
	By elementClickDate = By.xpath("//div[contains(@class,'SearchForm_date')]//div//p[contains(@class,'SearchForm_departure_return')]");
	By dateDerparture = By.xpath("//span[@aria-label='24 Februari 2025 Senin']");
	
	public void inputDerparture(String airportName) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.findElement(derpartureInput).click();
		WebElement searchBoxInputDerp = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxInputDerparture));
		driver.findElement(searchBoxInputDerparture).sendKeys(airportName);
		WebElement searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(getDropdownSearchResult(airportName)));
		searchResult.click();
		
	}
	public void inputDestination(String airportName) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.findElement(destinationInput).click();
		WebElement searchBoxInputDest = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxInputDestination));
		driver.findElement(searchBoxInputDestination).sendKeys(airportName);
		WebElement searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(getDropdownSearchResult(airportName)));
		searchResult.click();
		
	}
	public void inputDate() {
		driver.findElement(elementClickDate).click();
		driver.findElement(dateDerparture).click();
	}
}
