package practicedatadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcel {

		public static void main(String[] args) throws EncryptedDocumentException, IOException {
			
			//step 1 : get the excel path location and java object of the physical excelfile
			FileInputStream fis = new FileInputStream(".\\Testdata\\testScriptdata.xlsx");
				
				//step 2 : open Workbook in read mode
			Workbook wb	=WorkbookFactory.create(fis);
			
				//step 3 : get the control of the product sheet
			Sheet sh =wb.getSheet("product");
			
			// count of last used row count
			int rowCount = sh.getLastRowNum();
			
			 for(int i=1; i<rowCount;i++) {
				 
			  Row row = sh.getRow(i);
			 
			  String colum1Data = row.getCell(0).toString();
			  String colum2Data = row.getCell(1).toString();
			  
			  System.out.println(colum1Data +"\t" + colum2Data);
			 }
			 
			  wb.close();
			
			
			
			
		}
}
