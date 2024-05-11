package DibyanshVerma.FrameworkECommerce;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import JsonData.DataReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalonetest {

static WebDriver driver= null;

//	static String productName = "ZARA COAT 3";

//	public static void main(String[] args) throws InterruptedException {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitorder(HashMap<String, String> input) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		LoginPage lp = new LoginPage(driver);
		lp.goTo();
		lp.loginApplication(input.get("email"), input.get("password"));
		ProductCatelogue pc = new ProductCatelogue(driver);
		List<WebElement> products = pc.getProductList();
		pc.addtoCart(input.get("productName"));
		pc.clickonAddtoCart();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(carProd -> carProd.getText().equalsIgnoreCase(input.get("productName")));
		Assert.assertTrue(match);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,400)");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,400)");
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[placeholder*='Country']")).sendKeys("INDIA");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions
				.visibilityOfAllElements(driver.findElements(By.cssSelector("section[class*='ta-results']"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]"))).click();
//		driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")).click();
		driver.findElement(By.cssSelector("a[class*='btnn action']")).click();

		String orderConfirm = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderConfirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "postmanLupin@gmail.com");
//		map.put("password", "Test@1234");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "Ishu.verma@gmail.com");
//		map1.put("password", "Test@1234");
//		map1.put("productName", "ADIDAS ORIGINAL");
		
		DataReader data= new DataReader();
		List<HashMap<String,String>> data1= data.getJsonDataToMap();

		return new Object[][] { { data1.get(0) }, { data1.get(1) } };
	}
	
	public  String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File Source= ts.getScreenshotAs(OutputType.FILE);
		File DestinationFile= new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
		FileUtils.copyFile(Source, DestinationFile);
		return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
	}

}
