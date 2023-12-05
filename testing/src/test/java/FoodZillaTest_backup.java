import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import junit.framework.Assert;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FoodZillaTest_backup {
	AndroidDriver<AndroidElement> driver;

	@Before
	public void setUp() throws MalformedURLException {

		System.out.println("Running Automated Test cases on FOODZILLA AI Mobile Application");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability("platformName", "Android");
		dc.setCapability("udid", "R5CT61W9L0R");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "SM A536U1");
		dc.setCapability("appPackage", "io.foodzilla.app");
		dc.setCapability("appActivity", "io.foodzilla.app.MainActivity");
		dc.setCapability("noReset", "true");
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<AndroidElement>(url, dc);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	boolean askLogin = true;

	@Test
	public void Login() {

		try {
			driver.findElementByXPath("//android.widget.TextView[@text='Log in (for invited users)']").isDisplayed();
		} catch (Exception e) {
			askLogin = false;
		}

		if (askLogin) {
			System.out.println("Login Required");
			System.out.println("About to click on loggin button");
			String loginInvitedUser = "//android.widget.TextView[@text='Log in (for invited users)']";
			// driver.findElementByXPath("//android.widget.TextView[@text='Log in (for
			// invited users)']").click();
			clickElement(loginInvitedUser);
			System.out.println("clicked on loggin button");

			String enterEmailLocator = "//android.widget.EditText[@text='Enter email']";
			// driver.findElementByXPath("//android.widget.EditText[@text='Enter
			// email']").sendKeys("naga26.nagubandi@gmail.com");
			enterText(enterEmailLocator, "naga25.nagubandi@gmail.com");

			String enterPasswordLocator = "//android.widget.EditText[@text='Enter password']";
			// driver.findElementByXPath("//android.widget.EditText[@text='Enter
			// password']").sendKeys("AP24m2349@");
			enterText(enterPasswordLocator, "naga25");

			String continueButton = "//android.widget.TextView[@text='Continue']";
			// driver.findElementByXPath("//android.widget.TextView[@text='Continue']").click();
			clickElement(continueButton);

			System.out.println("clicked on continue button");

		} else {
			System.out.println("Didnt ask for login");
		}

	}
	
	
	public void clickElement(String xpathString) {
		driver.findElementByXPath(xpathString).click();
		// System.out.println("The element got clicked");
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new    UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().xpath(xpathString)").click(); //scroll down to the element and click
		//driver.findElementByXPath(xpathString).
	//	driver.findElementByAndroidUIAutomator("new UiScrollable(new    UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"WebView\").instance(0))").click(); //scroll down to the element and click
	//	String value = "(//androidx.cardview.widget.CardView//android.widget.FrameLayout//android.widget.ImageView)[17]";
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new    UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().xpath(value)").click(); //scroll down to the element and click

	}

	public void enterText(String xpathString, String value) {
		driver.findElementByXPath(xpathString).sendKeys(value);
		;
	}

	public String getText(String xpathString) {
		String textValue = driver.findElementByXPath(xpathString).getText();
		return textValue;

	}

	public List<String> getActualItems() {
		String items = "//android.widget.TextView[contains(@text,'(')]";
		List<AndroidElement> elements = driver.findElements(By.xpath(items));
		List<String> itemsNamesActual = new ArrayList<String>();
		for (AndroidElement androidElement : elements) {
			String text = androidElement.getText();
			itemsNamesActual.add(text);
		}

		// System.out.println("itemsNamesActual " + itemsNamesActual);
		return itemsNamesActual;

	}

	public List<String> getExpectedItems(String[] expectedItems) {
		List<String> itemsNamesExpected = new ArrayList<String>();
		for (String item : expectedItems) {
			itemsNamesExpected.add(item);
		}
		// System.out.println("itemsNamesExpected " + itemsNamesExpected);

		// itemsNamesExpected.add("Rice (0.1cup)");
		// itemsNamesExpected.add("Chicken (200grams)");
		return itemsNamesExpected;

	}

	public String getGivenAlbum(String albumName) {
		String albumNameDynamic = "//android.widget.TextView[@text='" + albumName + "']";

		// //android.widget.TextView[@text='Downloads']
		// System.out.println("printing album with passed name" + albumNameDynamic);
		// "Hello All this is" +firstName + " and Surna me is "+ lastName
		return albumNameDynamic;

	}

	public void openGalleryAndSelectAlbum(String albumName, String imageIndex) throws InterruptedException {
		// System.out.println("Importing from Gallery");

		Thread.sleep(2000);
		String openGalleryLocator = "//android.widget.TextView[@text='From Gallery']";
		clickElement(openGalleryLocator);

		String selectAlbumLocator = "//android.widget.LinearLayout[@content-desc=\"Albums\"]/android.widget.TextView";
		clickElement(selectAlbumLocator);

		// System.out.println("opening food album");
		String openFoodAlbum = getGivenAlbum(albumName);
		clickElement(openFoodAlbum);
		Thread.sleep(2000);

		// (//android.widget.FrameLayout[@content-desc='Button']/android.widget.FrameLayout[2])[2]
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new    UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().xpath(value)").click(); //scroll down to the element and click
		String selectFoodPicture = "(//androidx.cardview.widget.CardView//android.widget.FrameLayout//android.widget.ImageView)["
				+ imageIndex + "]";
		clickElement(selectFoodPicture);
		Thread.sleep(150);
	}

	public void verifyExpectedAndActual(String[] expectedItems) {
		boolean matches = false;
		List<String> itemsNamesActual = getActualItems();
		// System.out.println("itemsNamesActual " + itemsNamesActual);

		List<String> itemsNamesExpected = getExpectedItems(expectedItems);
	//	int iterationCount = (itemsNamesExpected.size() > itemsNamesActual.size()) ? itemsNamesExpected.size(): itemsNamesActual.size();
		
		if(itemsNamesExpected.size() > itemsNamesActual.size()) {
			for(String value: itemsNamesExpected) {
				//System.out.println("Expected > actual");
				System.out.println(itemsNamesActual.size());
				if (itemsNamesActual.contains(value)) {
					matches = true;
					break;
				}
			}
		}else {
			//System.out.println("Expected < actual");
			for(String value: itemsNamesActual) {
				if (itemsNamesExpected.contains(value)) {
					matches = true;
					break;
				}
			}
		}
		
		if (itemsNamesExpected.size() == itemsNamesActual.size()) {
			//System.out.println("Expected = actual");
			matches = itemsNamesActual.equals(itemsNamesExpected);
		}

		// System.out.println("The two ist comparision " + matches);
		// String message = "";

		if (itemsNamesActual.size() <= 0) {

			// Sorry, we could not detect foods in this picture, click on "Add Foods" to
			// analyse nutrition automatically.

			String noItemsSorry = "//android.view.ViewGroup//android.view.ViewGroup//android.widget.TextView[contains(@text,'Sorry')]";
			String message = getText(noItemsSorry);
			// String finalMessage = "The expected Items are " + itemsNamesExpected + "
			// however we got this **** " + message +" **** ";
			System.out.println("The expected Items are " + itemsNamesExpected + " however we got this **** " + message
					+ "  ****   ");
			assertTrue(false);
			// Assert.assertTrue(false, finalMessage);
		} else if (!matches) {
			System.out.println(
					"The items doesnt match, Expected " + itemsNamesExpected + " however got this " + itemsNamesActual);

			assertTrue(false);

		} else {
			System.out.println(
					"The items matched, Expected " + itemsNamesExpected + " and actual items " + itemsNamesActual);
			assertTrue(true);
		}

	}

	public void addToDairy() throws InterruptedException {
		Thread.sleep(7000);
		System.out.println("Add to Dairy");
		String addToDairy = "//android.widget.TextView[@text='Add to Diary']";
		clickElement(addToDairy);
		Thread.sleep(3000);

	}

	@Test
	public void TestCaseA1() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "1");

		String[] expectedItems = { "Bread (50grams)" };
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseA2() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "2");

		String[] expectedItems = { "Berry (50grams)", "Strawberry (20grams)", "Fruit salad (50grams)", "Blueberry (20grams)", "Blackberry (20grams)" };
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseA3() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "11");

		String[] expectedItems = { "Ice Cream (200grams)"  };
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseA4() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "4");

		String[] expectedItems = { "Chicken (200grams)" };
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseA5() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "15");

		String[] expectedItems = { "Rice (01.cup)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseA6() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "12");

		String[] expectedItems = { "Chocolate (50grams)", "Chocolate (50grams)", "Wafer (50grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseA7() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "14");

		String[] expectedItems = { "Rice (0.1cup)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseA8() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "8");

		String[] expectedItems = { "Coffee Beans (200grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseA9() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "9");

		String[] expectedItems = { "Sushi (200grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseB1() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "10");

		String[] expectedItems = { "Kidney bean (50grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	
	@Test
	public void TestCaseB2() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "11");

		String[] expectedItems = { "Ice cream (200grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();
		

	}
	@Test
	public void TestCaseB3() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "12");

		String[] expectedItems = { "Chocolate (50grams)", "Chocolate (50grams)", "Wafer (50grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseB4() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "14");

		String[] expectedItems = { "Cake (1000grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseB5() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "14");

		String[] expectedItems = { "Rice (0.1cup)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCaseB6() throws InterruptedException {
		openGalleryAndSelectAlbum("Screenshots", "15");

		String[] expectedItems = { "Rice (0.1cup)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	
	@Test
	public void TestCase16() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "1");

		String[] expectedItems = { "Sorry, we could not detect foods in this picture, tap on \\\"Add Foods\\\" to add foods and ingredients."};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase17() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "2");

		String[] expectedItems = { "Sorry, we could not detect foods in this picture, tap on \\\"Add Foods\\\" to add foods and ingredients."};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase18() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "3");

		String[] expectedItems = { "Bread (50grams", "Lettuce (50grams)", "Tomato (50grams)", "Cheese (1 slice)", "Toast (50grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase19() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "4");

		String[] expectedItems = { "Burrito (50grams)", "Chicken (50grams)", "Cheese (1 slice)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase20() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "5");

		String[] expectedItems = { "Apple (50grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase21() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "6");

		String[] expectedItems = { "Apple (50grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase22() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "7");

		String[] expectedItems = { "Apple (50grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase23() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "8");

		String[] expectedItems = { "Chili (10ml)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase24() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "9");

		String[] expectedItems = { "Chili (10ml)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase25() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "10");

		String[] expectedItems = { "Milk (10ml)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase26() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "11");

		String[] expectedItems = { "Milk (10ml)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase27() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "12");

		String[] expectedItems = { "Strawberry (20grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase28() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "13");

		String[] expectedItems = { "Fish (200grams)" };
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase29() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "14");

		String[] expectedItems = { "Sorry, we could not detect foods in this picture, tap on \\\"Add Foods\\\" to add foods and ingredients."};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}
	@Test
	public void TestCase30() throws InterruptedException {
		openGalleryAndSelectAlbum("Camera", "15");

		String[] expectedItems = { "Egg (50grams)"};
		verifyExpectedAndActual(expectedItems);

		addToDairy();
		driver.closeApp();

	}

}
