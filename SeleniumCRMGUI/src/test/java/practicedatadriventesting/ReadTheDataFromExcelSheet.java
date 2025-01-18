package practicedatadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadTheDataFromExcelSheet {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//step 1 : get the excel path location and java object of the physical excelfile
	FileInputStream fis = new FileInputStream(".\\Testdata\\testScriptdata.xlsx");
		
		//step 2 : open Workbook in read mode
	Workbook wb	=WorkbookFactory.create(fis);
	
		//step 3 : get the control of the org sheet
	Sheet sh =wb.getSheet("org");
	
		//step 4 : get the control of the 1st row
	Row row = sh.getRow(1);
	
		//step 5 : get the control of 2nd cell and the the string data
	 Cell cel = row.getCell(2);
	 String data = cel.getStringCellValue();
	 
	 System.out.println(data);
	 
	 //step 6 :  close the workbook
	 wb.close();
	}
}
