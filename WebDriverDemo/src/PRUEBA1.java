import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PRUEBA1 {

	public static void main(String[] args) {
		//dirección del geckodriver.exe
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Luis\\eclipse-workspace\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
	      //Puts an Implicit wait, Will wait for 10 seconds before throwing exception
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      
	      //Launch website
	      driver.navigate().to("https://es.wikipedia.org/wiki/Per%C3%BA");
	      
	      //Maximize the browser
	      driver.manage().window().maximize();
	      
	      // Click on Historia
	      driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/div[2]/ul/li[2]/a/span[2]")).click();
	      
	      // Click on Poblamiento de america
	      driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/p[12]/a[1]")).click();
	      
	      // Enter value 10 in the first number of the percent Calculator
	      //driver.findElement(By.id("cpar1")).sendKeys("10");
	      
	      // Enter value 50 in the second number of the percent Calculator
	      //driver.findElement(By.id("cpar2")).sendKeys("50");
	      
	      // Click Calculate Button
	      //driver.findElement(By.xpath("/html/body/div[3]/div[1]/table[1]/tbody/tr[2]/td/input[2]")).click();

	      
	      // Get the Result Text based on its xpath
	     /*String result = 
	    		  driver.findElement(By.xpath(".//*[@id = 'content']/p[2]/font/b")).getText();*/

	      
	      // Print a Log In message to the screen
	      System.out.println(" Salimos de la busqueda del Perú " );
	      
	      //Close the Browser.
	      driver.close();
	}

}

