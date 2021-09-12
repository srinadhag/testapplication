package utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/** This class is used to initialising the browser */
public class WebdriverFactory {

	protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static final String CHROME_DRIVER_WIN = BasePage.getProperty("chromedriverpath");
	// To quit the drivers and browsers at the end only.
	private static List<WebDriver> storedDrivers = new ArrayList<>();

	private WebdriverFactory() {
	}

	public static WebDriver setDriver() {
		String browser = BasePage.getProperty("browser");
		WebDriver setdriver = null;
		switch (browser) {
		case "CHROME":
			setdriver = setChromeDriver();
			break;
		case "BS":
			setdriver = bssetDriver();
			break;
		default:
			System.out.println("Unable to determine browser type to create browser instance.");
		}
		return setdriver;
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void addDriver(WebDriver webdriver) {
		storedDrivers.add(webdriver);
		driver.set(webdriver);
	}

	public static void closeBrowser() {
		driver.get().manage().deleteAllCookies();
		driver.get().close();
		driver.get().quit();
		storedDrivers.remove(driver.get());
		driver.remove();
	}

	public static WebDriver bssetDriver() {
		try {
			System.out.println("Getting Browserstack");
			System.setProperty("http.proxyHost", "cloudproxy-r.nat.bt.com");
			System.setProperty("http.proxyPort", "8080");
			System.setProperty("https.proxyHost", "cloudproxy-r.nat.bt.com");
			System.setProperty("https.proxyPort", "8080");

			String username = System.getenv("BROWSERSTACK_USERNAME");
			String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
			String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
			String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
			String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "8");
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "64.0");
			caps.setCapability("project", "AEM_PORTAL");
			caps.setCapability("name", "Regression");
			// caps.setCapability("browserstack.local", "true");
			caps.setCapability("browserstack.debug", "true");
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("browserstack.timezone", "GB");
			caps.setCapability("browserstack.selenium_version", "3.141.59");
			caps.setCapability("resolution", "1440x900");
			caps.setCapability("browserstack.idleTimeout", 1000);
			caps.setCapability("build", buildName);
			caps.setCapability("browserstack.local", browserstackLocal);
			caps.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);

			// caps.setCapability("build", "08April"); // Switch to window
			// caps.setCapability("browserstack.use_w3c", "true");
			String stringURL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
			System.out.println("BrowserStack Driver initiated successfully"); // URL serverUrl;
			URL serverUrl = new URL(stringURL);
			return new RemoteWebDriver(serverUrl, caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	private static WebDriver setChromeDriver() {

		System.setProperty("webdriver.chrome.driver", (new File(CHROME_DRIVER_WIN)).getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(ChromeOptions.CAPABILITY, options);

		return new ChromeDriver(caps);
	}

	public static void shuttingDownThread() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {

				storedDrivers.stream().forEach(WebDriver::quit);
			}
		});
	}
}
