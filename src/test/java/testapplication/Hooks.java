package testapplication;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.WebdriverFactory;

public class Hooks {

	@Before
	public static void setUp() throws Exception {
		System.out.println("Before Step defs");
		if (WebdriverFactory.getDriver() == null) {
			WebDriver driver = WebdriverFactory.setDriver();

			WebdriverFactory.addDriver(driver);
		}
	}

	@After
	public static void tearDown(Scenario scenario) {
		System.out.println("After Step Def");
		if (WebdriverFactory.getDriver() != null || scenario.isFailed()) {
			WebdriverFactory.closeBrowser();
			// WebDriverFactory.shuttingDownThread();
		}
	}

}
