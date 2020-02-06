package com.spiderframe.selenium.utility;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spiderframe.selenium.pom.Doer;

public class SeleniumLibrary {

	public static WebDriver webDriver = null;
	public String screenshotFilePath;

	public boolean perform(WebDriver driver, Doer doer) {

		boolean action = false;

		switch (doer.getKeyword().toUpperCase()) {
		case "CLICK":
			click(driver, doer);
			action = true;
			break;
		case "CLICKANDWAIT":
			clickAndWait(driver, doer);
			action = true;
			break;
		case "INPUT":
			input(driver, doer);
			action = true;
			break;
		case "CLEAR":
			clear(driver, doer);
			action = true;
			break;
		case "SUBMIT":
			submit(driver, doer);
			action = true;
			break;
		case "GOTO":
			goTo(driver, doer);
			action = true;
			break;
		case "SELECTBYINDEX":
			selectByIndex(driver, doer);
			action = true;
			break;
		case "SELECTBYVALUE":
			selectByValue(driver, doer);
			action = true;
			break;
		case "SELECTBYVISIBLETEXT":
			selectByVisibleText(driver, doer);
			action = true;
			break;
		case "NAVIGATE":
			navigateTo(driver, doer);
			action = true;
			break;
		case "NAVIGATEFORWARD":
			navigateForward(driver);
			action = true;
			break;
		case "NAVIGATEBACK":
			navigateBack(driver);
			action = true;
			break;
		case "SWITCHTOALERT":
			switchToAlert(driver);
			action = true;
			break;
		case "SWITCHTOFRAMENAME":
			switchToFrameName(driver, doer);
			action = true;
			break;
		case "SWITCHTOFRAMEINDEX":
			switchToFrameIndex(driver, doer);
			action = true;
			break;
		case "SWITCHTOWINDOW":
			switchToWindow(driver, doer);
			action = true;
			break;
		case "DELETEALLCOOKIES":
			deleteAllCookies(driver);
			action = true;
			break;
		case "IMPLICITWAIT":
			implicitlyWait(driver, doer);
			action = true;
			break;
		case "PAGELOADTIME":
			pageLoadTimeout(driver, doer);
			action = true;
			break;
		case "MAXIMIZE":
			maximize(driver);
			action = true;
			break;
		case "FULLSCREEN":
			fullscreen(driver);
			action = true;
			break;
		case "REFRESH":
			refresh(driver);
			action = true;
			break;
		case "VERIFYALERTTEXT":
			verifyAlertText(driver, doer);
			action = true;
			break;
		case "ACCEPTALERT":
			acceptAlert(driver);
			action = true;
			break;
		case "DISMISSALERT":
			dismissAlert(driver);
			action = true;
			break;
		case "CAPTURESCREEN":
			captureScreen(driver, doer);
			action = true;
			break;
		default:
			throw new InvalidArgumentException("Invalid identifier");
		}
		return action;
	}

	public static WebDriver getDriver(String browserName) {

		browserName = browserName.toLowerCase();

		Properties prop = new Properties();
		prop.setProperty("CHROME_DRIVER_LOCATION", "driver\\chromedriver.exe");
		prop.setProperty("GECKO_DRIVER_LOCATION", "driver\\geckodriver.exe");
		prop.setProperty("IE_DRIVER_LOCATION", "driver\\IEDriverServer.exe");
		prop.setProperty("EDGE_DRIVER_LOCATION", "driver\\MicrosoftWebDriver.exe");

		try {
			if (browserName.equals("chrome") || browserName.equals("google")) {
				System.setProperty("webdriver.chrome.driver", prop.getProperty("CHROME_DRIVER_LOCATION"));
				webDriver = new ChromeDriver();
			} else if (browserName.equals("firefox") || browserName.equals("ff")) {
				System.setProperty("webdriver.gecko.driver", prop.getProperty("GECKO_DRIVER_LOCATION"));
				webDriver = new FirefoxDriver(setCapabilities(browserName));
			} else if (browserName.equals("ie") || browserName.equals("internetexplorer")) {
				System.setProperty("webdriver.ie.driver", prop.getProperty("IE_DRIVER_LOCATION"));
				webDriver = new InternetExplorerDriver(setCapabilities(browserName));
			} else if (browserName.equals("edge") || browserName.equals("microsoftedge")) {
				File file = new File("C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe");
				System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
				webDriver = new EdgeDriver(setCapabilities(browserName));
			} else if (browserName.equals("safari") || browserName.equals("apple")) {

			} else {
				throw new InvalidArgumentException("Invalid browser name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return webDriver;
	}

	public static DesiredCapabilities setCapabilities(String browser) {

		DesiredCapabilities capabilities = null;

		if (browser.equalsIgnoreCase("ff") || browser.equalsIgnoreCase("firefox")) {
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			capabilities.setCapability(FirefoxDriver.BINARY, "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			capabilities.setBrowserName("firefox");
			capabilities.setVersion("54.0");
			capabilities.setPlatform(Platform.WIN10);
		} else if (browser.equalsIgnoreCase("edge") || browser.equalsIgnoreCase("microsoftedge")) {
			capabilities = DesiredCapabilities.edge();
			capabilities.setBrowserName(BrowserType.EDGE);
			capabilities.setPlatform(Platform.WIN10);
		} else if (browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("internetexplorer")) {
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		}

		return capabilities;
	}

	public void click(WebDriver driver, Doer doer) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		By by = by(doer.getLocator(), doer.getTarget());
		wait.until(ExpectedConditions.elementToBeClickable(by));
		element(driver, by).click();
	}

	public void clickAndWait(WebDriver driver, Doer doer) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		By by = by(doer.getLocator(), doer.getTarget());
		wait.until(ExpectedConditions.elementToBeClickable(by));
		element(driver, by).click();
		threadWait(Integer.parseInt(doer.getData()));
	}

	public void input(WebDriver driver, Doer doer) {
		element(driver, by(doer.getLocator(), doer.getTarget())).sendKeys(doer.getData());
	}

	public void clear(WebDriver driver, Doer doer) {
		element(driver, by(doer.getLocator(), doer.getTarget())).clear();
	}

	public void submit(WebDriver driver, Doer doer) {
		element(driver, by(doer.getLocator(), doer.getTarget())).submit();
	}

	public void goTo(WebDriver driver, Doer doer) {
		driver.get(doer.getData());
	}

	public void navigateTo(WebDriver driver, Doer doer) {
		driver.navigate().to(doer.getData());
	}

	public void navigateForward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}

	public void switchToFrameName(WebDriver driver, Doer doer) {
		driver.switchTo().frame(doer.getData());
	}

	public void switchToFrameIndex(WebDriver driver, Doer doer) {
		driver.switchTo().frame(Integer.parseInt(doer.getData()));
	}

	public void switchToWindow(WebDriver driver, Doer doer) {
		driver.switchTo().window(doer.getData());
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void switchToAlert(WebDriver driver) {
		driver.switchTo().alert();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void verifyAlertText(WebDriver driver, Doer doer) {
		String actual = driver.switchTo().alert().getText();
		assertEquals(actual, doer.getData(), "Alert Text is correct");
	}

	public void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	public void selectByVisibleText(WebDriver driver, Doer doer) {
		new Select(element(driver, by(doer.getLocator(), doer.getTarget()))).selectByVisibleText(doer.getData());
	}

	public void selectByIndex(WebDriver driver, Doer doer) {
		new Select(element(driver, by(doer.getLocator(), doer.getTarget())))
				.selectByIndex(Integer.parseInt(doer.getData()));
	}

	public void selectByValue(WebDriver driver, Doer doer) {
		new Select(element(driver, by(doer.getLocator(), doer.getTarget()))).selectByValue(doer.getData());
	}

	public void fullscreen(WebDriver driver) {
		driver.manage().window().fullscreen();
	}

	public void maximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void implicitlyWait(WebDriver driver, Doer doer) {
		driver.manage().timeouts().implicitlyWait(Long.parseLong(doer.getData()), TimeUnit.SECONDS);
	}

	public void pageLoadTimeout(WebDriver driver, Doer doer) {
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(doer.getData()), TimeUnit.SECONDS);
	}

	public WebElement element(WebDriver driver, By by) {
		return driver.findElement(by);
	}

	public By by(String locator, String target) {

		By by = null;
		locator = locator.toLowerCase();

		switch (locator) {
		case "id":
			by = By.id(target);
			break;
		case "xpath":
			by = By.xpath(target);
			break;
		case "classname":
			by = By.className(target);
			break;
		case "name":
			by = By.name(target);
			break;
		case "css":
			by = By.cssSelector(target);
			break;
		case "linktext":
			by = By.linkText(target);
			break;
		case "partiallinktext":
			by = By.partialLinkText(target);
			break;
		default:
			throw new InvalidArgumentException("Invalid locator type");
		}

		return by;
	}

	public void captureScreen(WebDriver driver, Doer doer) {
		String time;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");

		time = date.format(cal.getTime());
		time = time.replaceAll(":", "-");
		time = time.replaceAll("/", "-");

		String filePath = System.getProperty("user.dir") + File.separator + "images" + File.separator + doer.getData()
				+ "-" + time + ".png";
		screenshotFilePath = filePath;
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean findLink(WebDriver driver, String link) {
		boolean found = false;
		List<WebElement> links = driver.findElements(By.tagName("a"));

		int count = 1;
		for (WebElement element : links) {
			String hrefLink = element.getAttribute("href"); // Get link URL
			// String linkText = element.getText(); // Get text of the link
			if (hrefLink != null && hrefLink.contains(link)) {
				System.out.println("Verify Link: " + hrefLink);
				System.out.println("Input Value: " + link);
				System.out.println("Link Found! @Count" + count);
				// driver.findElement(By.linkText(linkText)).click();
				found = true;
				break;
			}
			count++;
		}
		return found;
	}

	public void threadWait(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitTillElementIsVisible(WebDriver driver, WebElement element, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void dragAndDropActions(WebDriver driver, String sourceXpath, String targetXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		WebElement target = driver.findElement(By.xpath(targetXpath));

		waitTillElementIsVisible(driver, source, 30);

		Actions builder = new Actions(driver);
		builder.clickAndHold(source).perform();
		builder.moveToElement(target, target.getLocation().x, target.getLocation().y).perform();
		builder.release(target).perform();
	}

	public void dragAndDropRobot(WebDriver driver, String sourceXpath, String targetXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		WebElement target = driver.findElement(By.xpath(targetXpath));

		waitTillElementIsVisible(driver, source, 30);

		try {
			Point c1 = source.getLocation();
			Point c2 = target.getLocation();
			System.out.println("SOURCE = X:" + c1.getX() + "--> Y:" + c1.getY());
			System.out.println("TARGET = X:" + c2.getX() + "--> Y:" + c2.getY());
			Robot robot = new Robot();
			robot.mouseMove(c1.getX(), c1.getY());
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseMove(c2.getX(), c2.getY());
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void dragAndDropJavaScript(WebDriver driver, String sourceId, String targetId) {
		try {
			String line = null;

			BufferedReader br = new BufferedReader(new FileReader("javascript\\drag.js"));
			StringBuffer buffer = new StringBuffer();

			while ((line = br.readLine()) != null)
				buffer.append(line);

			String javaScript = buffer.toString();

			String java_Script = javaScript + "$('#" + sourceId + "').simulateDragDrop({ dropTarget: '#" + targetId
					+ "'});";

			((JavascriptExecutor) driver).executeScript(java_Script);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void focusJavaScript(WebDriver driver, String sourceXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", source);
	}

	public void focusAndClick(WebDriver driver, String sourceXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		Locatable hoverItem = (Locatable) source;
		Mouse mouse = ((HasInputDevices) driver).getMouse();
		mouse.mouseMove(hoverItem.getCoordinates());
		mouse.click(hoverItem.getCoordinates());
	}

	/**
	 * Used to open new URL in new tab
	 * 
	 * @param driver
	 * @param url
	 */
	public void openUrlNewWindow(WebDriver driver, String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()"); // Open new tab

		int size = driver.getWindowHandles().size(); // Get number of tabs
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[size - 1]);
		Doer doer = new Doer();
		doer.setKeyword("navigate");
		doer.setData(url);
		perform(driver, doer); // Navigate to URL
		threadWait(5);
	}

	/**
	 * Used to get window handle when link opens new tab/window
	 * 
	 * @param driver
	 */
	public void getPresentWorkingWindow(WebDriver driver) {
		int size = driver.getWindowHandles().size(); // Get number of tabs
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[size - 1]);
		threadWait(5);
	}

	public void openNewTab(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()"); // Open new tab
		int size = driver.getWindowHandles().size(); // Get number of tabs
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[size - 1]);
	}

	public String[] getWindowTitles(WebDriver driver) {
		int size = driver.getWindowHandles().size();
		String[] titles = new String[size];
		for (int i = 0; i < size; i++) {
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[i]);
			titles[i] = driver.getTitle().trim();
		}
		return titles;
	}

	public void switchToWindowIndex(WebDriver driver, int i) {
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[i]);
	}

	public void focusWindow(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.focus()");
	}

	public void switchWindow(WebDriver driver, String[] titles, String currentTab, String switchToTab) {
		pressAltTabRobot();
		int switchToTabIndex = 0;
		int currentTabIndex = 0;
		for (int i = 0; i < titles.length; i++) {
			if (titles[i].contains(switchToTab)) {
				switchToTabIndex = i;
			}
			if (titles[i].contains(currentTab)) {
				currentTabIndex = i;
			}
		}
		System.out.println("Focus Tab Index for \"" + switchToTab + "\": " + switchToTabIndex);
		System.out.println("Current Tab Index for \"" + currentTab + "\": " + currentTabIndex);
		int press = 0;
		if (switchToTabIndex == currentTabIndex) {
			press = currentTabIndex;
			System.out.println("No focus");
		} else if (currentTabIndex > switchToTabIndex) {
			press = press + (titles.length - 1) - currentTabIndex;
			press = press + switchToTabIndex + 1;
			for (int j = 0; j < press; j++)
				pressCtrlTabRobot();
			System.out.println("Pressed Ctrl+TAB: " + press + " times");
		} else {
			press = switchToTabIndex - currentTabIndex;
			for (int k = 0; k < press; k++)
				pressCtrlTabRobot();
			System.out.println("Pressed Ctrl+TAB: " + press + " times");
		}
		System.out.println(driver.getTitle());
	}

	public void pressCtrlTabAction(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();
	}

	public void pressCtrlTabRobot() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_TAB);

			r.keyRelease(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void pressAltTabRobot() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ALT);
			r.keyPress(KeyEvent.VK_TAB);

			r.keyRelease(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_ALT);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
