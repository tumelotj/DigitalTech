package utilities;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {

	public static Object[][] readExcel() throws Exception {
		//Access spreadsheet
		System.out.println("Accessing spreadsheet and setting up Array");
		File myFile = FileUtils.loadObjectsRepository("testData.xlsx");
		FileInputStream myStream = new FileInputStream(myFile);
		XSSFWorkbook myWorkbook = new XSSFWorkbook(myStream);


		XSSFSheet dataSheet = myWorkbook.getSheet("TestScenarios");
		int numRows = dataSheet.getLastRowNum()+1;
		int numCols = dataSheet.getRow(0).getLastCellNum();
		String[][] excelData = new String[numRows-1][numCols];

		System.out.println("Populating Array to the array");
		for (int i=1; i<numRows; i++) {
			XSSFRow row = dataSheet.getRow(i);
			for (int j=0; j<numCols; j++) {
				XSSFCell cell = row.getCell(j);
				String value = cellToString(cell);
				excelData[i-1][j] = value;
			}
		}
		System.out.println("Array population complete");
		return excelData;
	}

	public static String cellToString(XSSFCell cell) {
		int type = cell.getCellType();
		String result;

		if (type == XSSFCell.CELL_TYPE_FORMULA) {
			throw new RuntimeException("Cannot process a formula. Please change field to result of formula.");
		}
		//If blanks are ever able to be evaluated by Apache POI, set them to empty string
		else if (type ==XSSFCell.CELL_TYPE_BLANK) {
			result = " ";
		}
		//Convert cell contents to String
		else {
			result = String.valueOf(cell);
		}
		return result;
	}

	public static String getCellData(int row, int col) {
		try {
			File myFile = FileUtils.loadObjectsRepository("testData.xlsx");
			FileInputStream myStream = new FileInputStream(myFile);
			XSSFWorkbook myWorkbook = new XSSFWorkbook(myStream);

			XSSFSheet dataSheet = myWorkbook.getSheet("TestScenarios");
			int rowNum = dataSheet.getLastRowNum() + 1;
			int colNum = dataSheet.getRow(0).getLastCellNum();
			XSSFCell Cell = dataSheet.getRow(row).getCell(col);
			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	return"";
	}
}
