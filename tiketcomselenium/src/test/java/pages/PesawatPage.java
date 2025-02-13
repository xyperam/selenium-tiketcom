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
	WebDriverWait wait;
	public PesawatPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
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
	By dateDerparture = By.xpath("//button[contains(@class,'Day_day')]//span[@aria-label='24 Februari 2025 Senin']");
	By passengersPicker = By.xpath("//div[contains(@class,'SearchForm_passenger_picker')]");
	By addButtonDewasa = By.xpath("(//button[contains(@class, 'QuantityEditor_operation_button__')])[2]");
	By minButtonDewasa = By.xpath("(//button[contains(@class, 'QuantityEditor_operation_button__')])[1]");
	By quantitypassengers = By.xpath("//input[@class='QuantityEditor_quantity_input__bAtrd']");
	By buttonSubmitPassengers = By.xpath("//button[contains(@class,'PassengerForm_btn_save')]");
	By buttonSearch = By.xpath("//button[contains(@class,'SearchForm_btn_submit')]");
	public void inputDerparture(String airportName) {
		
		driver.findElement(derpartureInput).click();
		WebElement searchBoxInputDerp = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxInputDerparture));
		driver.findElement(searchBoxInputDerparture).sendKeys(airportName);
		WebElement searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(getDropdownSearchResult(airportName)));
		searchResult.click();
		
	}
	public void inputDestination(String airportName) {
	
		driver.findElement(destinationInput).click();
		WebElement searchBoxInputDest = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxInputDestination));
		driver.findElement(searchBoxInputDestination).sendKeys(airportName);
		WebElement searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(getDropdownSearchResult(airportName)));
		searchResult.click();
		
	}
	public void inputDate() {
		driver.findElement(elementClickDate).click();
	
		WebElement datepick = wait.until(ExpectedConditions.elementToBeClickable(dateDerparture));
		datepick.click();
	}
	
	public void pickPassenger() {
	    // Klik elemen untuk membuka pop-up pemilihan penumpang
	    driver.findElement(passengersPicker).click();


	    for (int i = 0; i < 10; i++) {            
	        // Tunggu hingga qtyField tersedia dan memiliki atribut 'value'
	        WebElement qtyField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantitypassengers));

	        // Pastikan atribut 'value' tidak null sebelum parsing
	        String valueAttr = qtyField.getAttribute("value");
	        int currentValue = (valueAttr != null && !valueAttr.isEmpty()) ? Integer.parseInt(valueAttr) : 0;

	        // Cek apakah tombol "Tambah Penumpang" sudah disabled
	        WebElement addButton = driver.findElement(addButtonDewasa);
	        boolean isDisabled = addButton.getAttribute("disabled") != null;

	        if (currentValue >= 7 || isDisabled) {
	            System.out.println("Jumlah penumpang telah mencapai batas: " + currentValue);
	            break;
	        }

	        // Klik tombol tambah jika belum disabled
	       if(!isDisabled) {
	    	   
	    	   WebElement addButtonClickable = wait.until(ExpectedConditions.elementToBeClickable(addButtonDewasa));
	    	   addButtonClickable.click();
	       }

	        // Tunggu qtyField diperbarui sebelum membaca nilai baru
	        WebElement qtyFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(quantitypassengers));
	        String finalValueAttr = qtyFinal.getAttribute("value");
	        int finalValue = (finalValueAttr != null && !finalValueAttr.isEmpty()) ? Integer.parseInt(finalValueAttr) : 0;

	        // Validasi batas maksimum
	        if (finalValue > 7) {
	            System.out.println("Error: Jumlah penumpang melebihi batas maksimum!");
	        } else {
	            System.out.println("Jumlah akhir penumpang: " + finalValue);
	        }
	    }
	}
			public void clickSubmitPassenger() {
				driver.findElement(buttonSubmitPassengers).click();
			}
			public void clickButtonSearch() {
				
				driver.findElement(buttonSearch).click();
				 wait.until(ExpectedConditions.urlContains("pesawat/search"));
				 System.out.println("Navigated to: " + driver.getCurrentUrl());
				
			}


}
