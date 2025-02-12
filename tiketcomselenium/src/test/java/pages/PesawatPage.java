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
	By dateDerparture = By.xpath("//button[contains(@class,'Day_day')]//span[@aria-label='24 Februari 2025 Senin']");
	By passangersPicker = By.xpath("//div[contains(@class,'SearchForm_passenger_picker')]");
	By addButtonDewasa = By.xpath("(//button[contains(@class, 'QuantityEditor_operation_button__')])[2]");
	By minButtonDewasa = By.xpath("(//button[contains(@class, 'QuantityEditor_operation_button__')])[1]");
	By quantityPassangers = By.xpath("//input[@class='QuantityEditor_quantity_input__bAtrd']");
	
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
		WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(20));
		WebElement datepick = wait.until(ExpectedConditions.elementToBeClickable(dateDerparture));
		datepick.click();
	}
	
	public void pickPassanger() {
	    // Klik elemen untuk membuka pop-up pemilihan penumpang
	    driver.findElement(passangersPicker).click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    for (int i = 0; i < 10; i++) {            
	        // Tunggu hingga qtyField tersedia dan memiliki atribut 'value'
	        WebElement qtyField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityPassangers));

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
	        WebElement addButtonClickable = wait.until(ExpectedConditions.elementToBeClickable(addButtonDewasa));
	        addButtonClickable.click();

	        // Tunggu qtyField diperbarui sebelum membaca nilai baru
	        WebElement qtyFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityPassangers));
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


}
