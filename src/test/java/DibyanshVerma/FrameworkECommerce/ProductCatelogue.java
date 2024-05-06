package DibyanshVerma.FrameworkECommerce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ReusableMethod.reusable;

public class ProductCatelogue extends reusable {

	WebDriver driver;
	public ProductCatelogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

//	List<WebElement> ele = driver.findElements(By.cssSelector(".mb-3"));
	
	//Page Factory
	@FindBy(css=".mb-3")
	List<WebElement> product;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy= By.cssSelector(".mb-3");
	By addtoCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		elementtoAppear(productBy);
		return product;
	}
	
	public WebElement getProductbyName(String Productname)
	{
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst()
				.orElse(null);
		
		return prod;
	}
	
	public void addtoCart(String Productname ) throws InterruptedException
	{
		WebElement prod=getProductbyName(Productname);
		prod.findElement(addtoCart).click();
		elementtoAppear(toastMessage);
		waitElementtoDisappear(spinner);
	}
	
	
	
	
	
	

}
