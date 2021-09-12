package testapplication;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageoperations.MainPageOperations;
import utils.WebdriverFactory;

public class StepDefinitions {

	MainPageOperations mainPageOprs = new MainPageOperations(WebdriverFactory.getDriver());
	
	//MainPageOperations mainPageDefault = new MainPageOperations();

	@Given("I navigate to given url {string}")
	public void navigate_to_given_url(String url) throws Exception {
		System.out.println("navigate to given url::: " + url);
		mainPageOprs.navigateToSpecifiedURL(url);
		//mainPageDefault.navigateToSpecifiedURL(url);
	}

	@When("I Click on the {string} button")
	public void click_on_btn(String btnCssSelectorPath) throws Exception {
		System.out.println("Click on the given " + btnCssSelectorPath);
		mainPageOprs.btnClick(btnCssSelectorPath);
	}

	@Then("I should enter {string} to {string}")
	public void enter_data_input_field(String value, String cssPath) throws Exception {
		mainPageOprs.input_field_data_entry(value, cssPath);
	}

}
