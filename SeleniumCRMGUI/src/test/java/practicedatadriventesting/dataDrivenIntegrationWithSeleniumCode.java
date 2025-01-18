package practicedatadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class dataDrivenIntegrationWithSeleniumCode {

public static void main(String[] args) throws IOException {
		
		//read common data from properties file
		FileInputStream fis = new FileInputStream("./Testdata/demo.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//Scanner s = new Scanner(System.in);
		//System.out.println("enter the browser");
		//String browser = s.next();
		
		//read data from properties file
		WebDriver driver = null;
		
     FileInputStream fis1 = new FileInputStream(".\\Testdata\\testScriptdata.xlsx");
	 Workbook wb	=WorkbookFactory.create(fis1);
	 Sheet sh =wb.getSheet("org");
	 Row row = sh.getRow(1);
	 String orgName = row.getCell(2).toString();
	 wb.close();
	 
	
		
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		
		}
		else {
			driver = new ChromeDriver();
		}
		
		//step1 Login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step 2 navigate thef organization module
		driver.findElement(By.partialLinkText("Organizations")).click();
		
		//step 3 click on organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
