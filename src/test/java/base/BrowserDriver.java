package base;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class BrowserDriver implements WebDriver {
	private WebDriver driver;
	private final String browserName;
	private final int timeout = 30;
	private final String chromeDriverPath = "./src/test/resources/chromedriver";

	public BrowserDriver(String browserName) {
		this.browserName = browserName;
		this.driver = createDriver(browserName);
	}

	private WebDriver createDriver(String browserName) {
		if (browserName.toUpperCase().equals("FIREFOX"))
			return firefoxDriver();
		if (browserName.toUpperCase().equals("CHROME"))
			return chromeDriver();

		throw new RuntimeException("invalid browser name");
	}

	private WebDriver chromeDriver() {
		if (!new File(chromeDriverPath).exists())
			throw new RuntimeException("chromedriver.exe does not exist!");

		try {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			return new ChromeDriver();
		}

		catch (Exception ex) {
			throw new RuntimeException("couldnt create chrome driver");
		}
	}

	private WebDriver firefoxDriver() {
		try {
			return new FirefoxDriver();
		} catch (Exception ex) {
			throw new RuntimeException("could not create the firefox driver");
		}
	}

	public String toString() {
		return this.browserName;
	}

	public WebDriver driver() {
		return this.driver;
	}

	public void close() {
		driver().close();
	}

	public void get(String arg0) {
		driver().get(arg0);
	}

	public String getCurrentUrl() {
		return driver().getCurrentUrl();
	}

	public String getPageSource() {
		return driver().getPageSource();
	}

	public String getTitle() {
		return driver().getTitle();
	}

	public String getWindowHandle() {
		return driver().getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return driver().getWindowHandles();
	}

	public Options manage() {
		return driver().manage();
	}

	public Navigation navigate() {
		return driver().navigate();
	}

	public void quit() {
		driver().quit();
	}

	public TargetLocator switchTo() {
		return driver().switchTo();
	}

	public WebElement findElement(By locator) {
		WebElement element = new WebDriverWait(driver(), timeout)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public List<WebElement> findElements(By arg0) {
		return driver().findElements(arg0);
	}

	public WebElement findClickableElement(By locator) {
		WebElement element = new WebDriverWait(driver(), timeout)
				.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public WebElement findHiddenElement(By locator) {
		WebElement element = new WebDriverWait(driver(), timeout)
				.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}
}
