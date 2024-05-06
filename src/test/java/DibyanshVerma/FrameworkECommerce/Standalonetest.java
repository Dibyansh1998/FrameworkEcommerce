package DibyanshVerma.FrameworkECommerce;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalonetest {


	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LoginPage lp= new LoginPage(driver);
		lp.goTo();
		lp.loginApplication("postmanLupin@gmail.com", "Test@1234");
		ProductCatelogue pc= new ProductCatelogue(driver);
		List<WebElement> products=pc.getProductList();
		pc.addtoCart(productName);
		pc.clickonAddtoCart();
		

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(carProd -> carProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,400)");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,400)");
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[placeholder*='Country']")).sendKeys("INDIA");

		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions
				.visibilityOfAllElements(driver.findElements(By.cssSelector("section[class*='ta-results']"))));
		wait.until(ExpectedConditions.
				visibilityOfElementLocated(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]"))).click();
//		driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")).click();
		driver.findElement(By.cssSelector("a[class*='btnn action']")).click();

		String orderConfirm = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderConfirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

//		driver.close();

	}

}
