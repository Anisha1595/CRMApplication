package practicedatadriventesting;

import org.testng.annotations.Test;

public class ReadRunTimeMavenParameterTest {
@Test
public void runtimeParameterTest() {
	String URL =System.getProperty("url");
	String BROWSER =System.getProperty("browser");
	String USERNAME =System.getProperty("username");
	String PASSWORD=System.getProperty("password");
	
	System.out.println("Env Data==>URL====>"+URL);
	System.out.println("Browser Data==>====>"+BROWSER);
	System.out.println("username Data==>====>"+USERNAME);
	System.out.println("password Data==>====>"+PASSWORD);
}
}
