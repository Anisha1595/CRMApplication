package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		// read data from property file

		// read common data from properties file
		FileInputStream fis = new FileInputStream(".//Testdata/demo.properties");
		Properties pObj = new Properties();
		pObj.load(fis);

		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

		// generate random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read the data from excel file

		FileInputStream fis1 = new FileInputStream(".//Testdata//testdataorg.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(4);
		String  lastname= row.getCell(2).toString() + randomInt;
		wb.close();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();

		} else {
			driver = new ChromeDriver();
		}

		// step1 Login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
        //navigate to contact module
		driver.findElement(By.partialLinkText("Contacts")).click();
		
       //click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
       //Enter all the details to create a contact
		
		Date dateObj= new Date();
		//capture system current date
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		String startDate =sim.format(dateObj);
		
		 Calendar cal = sim.getCalendar();
		 cal.add(Calendar.DAY_OF_MONTH, 30);
		 String endDate = sim.format(cal.getTime());
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify the lastDate

		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(lastname)) {
			System.out.println(lastname+ "Information is created==PASS");
		} else {
			System.out.println(lastname + "Information is not created==FAIL");
		}

		// Verify the startDate

				String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if (actStartDate.equals(startDate)) {
					System.out.println(startDate+ "Information is created==PASS");
				} else {
					System.out.println(startDate + "Information is not created==FAIL");
				}	
				
				// Verify the endDate

				String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
				if (actEndDate.equals(endDate)) {
					System.out.println(endDate+ "Information is created==PASS");
				} else {
					System.out.println(endDate + "Information is not created==FAIL");
				}					
		
		// logout
		Thread.sleep(3000);

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();

		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
