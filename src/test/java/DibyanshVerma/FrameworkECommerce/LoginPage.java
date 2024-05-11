package DibyanshVerma.FrameworkECommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ReusableMethod.reusable;

public class LoginPage extends reusable {

	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

//	WebElement user name=driver.findElement(By.id("userEmail"));
	
	//Page Factory
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	public void loginApplication(String username, String Password)
	{
		email.sendKeys(username);
		password.sendKeys(Password);
		submit.click();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
