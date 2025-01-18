package practice.testng;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class SampleTestForScreenshot {
    @Test
    public void amazonTest() throws IOException {
    	ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		File src=driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshot/test.png");
		Files.copy(src, dest);
}
}
