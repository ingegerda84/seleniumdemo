package passwords;



import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class test6 {

	WebDriver driver;
	String pass1, pass2;

	String master = "//tr[1]/td[2]/input";
	String site = "//tr[2]/td[2]/input";
	String password = "//tr[4]/td[2]/input";
	String xpath = "//input[@value='Generate']";

	@BeforeClass
	public void start() {
		driver = new FirefoxDriver();
		driver.get("http://oxogamestudio.com/passwd.current6.htm");
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void cleanup() {
		driver.get("http://oxogamestudio.com/passwd.current6.htm");
		driver.findElement(By.xpath(master)).clear();
		driver.findElement(By.xpath(site)).clear();
		driver.findElement(By.xpath(password)).clear();
		
	}
	@Test
	public void verifyEmptyPass() {

		driver.findElement(By.xpath(site)).sendKeys("123");
		driver.findElement(By.xpath("//input[@value='Generate']")).click();
		pass1 = driver.findElement(By.xpath("//tr[4]/td[2]/input")).getAttribute("value");
		System.out.println("Password: " + pass1);

		assertNotEquals("", pass1);
		assertEquals(pass1.substring(pass1.length() - 3), "@1a");

	}
	@Test
	public void verifyEmptyFields() {
		driver.findElement(By.xpath("//input[@value='Generate']")).click();
		pass1 = driver.findElement(By.xpath(password)).getAttribute("value");
		System.out.println("Password: " + pass1);

		assertNotEquals("", pass1);
		assertTrue(pass1.endsWith("@1a"), "String is not ends with @1a");

	}

	
	@Test
	public void verifyEmptyMaster() {

		driver.findElement(By.xpath(master)).sendKeys("123");
		driver.findElement(By.xpath(xpath)).click();
		
		pass1 = driver.findElement(By.xpath("//tr[4]/td[2]/input")).getAttribute("value");

		System.out.println("Password: " + pass1);

		assertNotEquals("", pass1);
		assertEquals(pass1.substring(pass1.length() - 3), "@1a");

	}

	@Test
	public void verifyNotEmpty() {

		driver.findElement(By.xpath("//tr[1]/td[2]/input")).sendKeys("123");
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys("123");

		driver.findElement(By.xpath("//input[@value='Generate']")).click();

		pass1 = driver.findElement(By.xpath("//tr[4]/td[2]/input")).getAttribute("value");

		System.out.println("Password1: " + pass1);

		assertNotEquals("", pass1);
		assertEquals(pass1.substring(pass1.length() - 3), "@1a");
		driver.get("http://oxogamestudio.com/passwd.current6.htm");
		driver.findElement(By.xpath( master )).clear();
		driver.findElement(By.xpath(site)).clear();
		driver.findElement(By.xpath(password)).clear();

		driver.findElement(By.xpath(master)).sendKeys("123");
		driver.findElement(By.xpath(site)).sendKeys("123");

		driver.findElement(By.xpath("//input[@value='Generate']")).click();

		pass2 = driver.findElement(By.xpath(password)).getAttribute("value");

		System.out.println("Password2: " + pass2);

		assertEquals(pass1, pass2);

	}

	@AfterClass
	public void stop() {
		driver.quit();
	}
}
