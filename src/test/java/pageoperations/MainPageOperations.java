package pageoperations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import PageElements.AllElements;

public class MainPageOperations {

	protected WebDriver driver;

	AllElements elements;

	public MainPageOperations(WebDriver driver) {
		this.driver = driver;
		elements = PageFactory.initElements(this.driver, AllElements.class);

	}

	public MainPageOperations() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Navigate the given url from feature file
	 *
	 * @param url - Given url to navigate
	 * @throws Exception
	 */
	public void navigateToSpecifiedURL(String url) throws Exception {
		driver.get(url);
	}

	/**
	 * Click on the button using CSS Selector passing
	 *
	 * @param btnCssSelectorPath - CSS Selector from feature file
	 * @throws Exception
	 */
	public void btnClick(String btnCssSelectorPath) throws Exception {
		WebElement element = driver.findElement(By.cssSelector(btnCssSelectorPath));
		element.click();
	}

	/**
	 *
	 * @param value       - data field to enter
	 * @param cssSelector - css selector for given input field
	 * @throws Exception
	 */
	public void input_field_data_entry(String value, String cssSelector) throws Exception {

		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		element.clear();
		element.sendKeys(value);

	}

}
