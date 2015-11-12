package passwords;



import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class test5 {

	WebDriver driver;
	String pass1, pass2;

	String master = "master";
	String site = "site";
	String password = "password";
	String xpath = "//input[@value='Generate']";

	@BeforeClass
	public void start() {
		driver = new FirefoxDriver();
		driver.get("http://oxogamestudio.com/passwd.current5.htm");
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void cleanup() {
		driver.get("http://oxogamestudio.com/passwd.current5.htm");
		driver.findElement(By.name(master)).clear();
		driver.findElement(By.name(site)).clear();
		driver.findElement(By.name(password)).clear();
		
	}
	@Test
	public void verifyEmptyPass() {

		driver.findElement(By.name(site)).sendKeys("123");
		driver.findElement(By.xpath("//input[@value='Generate']")).click();
		pass1 = driver.findElement(By.name("password")).getAttribute("value");
		System.out.println("Password: " + pass1);

		assertNotEquals("", pass1);
		assertEquals(pass1.substring(pass1.length() - 3), "@1a");

	}
	@Test
	public void verifyEmptyFields() {
		driver.findElement(By.xpath("//input[@value='Generate']")).click();
		pass1 = driver.findElement(By.name(password)).getAttribute("value");
		System.out.println("Password: " + pass1);

		assertNotEquals("", pass1);
		assertTrue(pass1.endsWith("@1a"), "String is not ends with @1a");

	}

	
	@Test
	public void verifyEmptyMaster() {

		driver.findElement(By.name(master)).sendKeys("123");
		driver.findElement(By.xpath(xpath)).click();
		
		pass1 = driver.findElement(By.name("password")).getAttribute("value");

		System.out.println("Password: " + pass1);

		assertNotEquals("", pass1);
		assertEquals(pass1.substring(pass1.length() - 3), "@1a");

	}

	@Test
	public void verifyNotEmpty() {

		driver.findElement(By.name("master")).sendKeys("123");
		driver.findElement(By.name("site")).sendKeys("123");

		driver.findElement(By.xpath("//input[@value='Generate']")).click();

		pass1 = driver.findElement(By.name("password")).getAttribute("value");

		System.out.println("Password1: " + pass1);

		assertNotEquals("", pass1);
		assertEquals(pass1.substring(pass1.length() - 3), "@1a");
		driver.get("http://oxogamestudio.com/passwd.current5.htm");
		driver.findElement(By.name("master")).clear();
		driver.findElement(By.name("site")).clear();
		driver.findElement(By.name("password")).clear();

		driver.findElement(By.name("master")).sendKeys("123");
		driver.findElement(By.name("site")).sendKeys("123");

		driver.findElement(By.xpath("//input[@value='Generate']")).click();

		pass2 = driver.findElement(By.name("password")).getAttribute("value");

		System.out.println("Password2: " + pass2);

		assertEquals(pass1, pass2);

	}

	@AfterClass
	public void stop() {
		driver.quit();
	}
}
