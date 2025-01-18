package practicedatadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadTest {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./Testdata/commonAppdata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		System.out.println(pObj.getProperty("browser"));
	}
}
