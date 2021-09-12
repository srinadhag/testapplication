package PageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllElements {

	

	@FindBy(css = ".class")
	public WebElement classname;
	
	@FindBy(xpath = "//*[text()='data']")
	public WebElement datafield;

}
