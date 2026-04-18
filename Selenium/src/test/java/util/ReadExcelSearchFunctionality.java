package util;

import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ReadExcelSearchFunctionality {
    private static final String EXCEL_PATH = "C:\\Users\\kiruthika.ganesan\\eclipse-workspace\\May_CapstoneProject\\May_capstone_dataDriven.xlsx";
    private static final String SHEET_NAME = "Sheet1"; 

    // ✅ Get the number of rows (excluding header if any)
    public static int getRowCount() {
        int rowCount = 0;
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(SHEET_NAME);

            if (sheet == null) {
                throw new RuntimeException("Sheet \"" + SHEET_NAME + "\" not found in file: " + EXCEL_PATH);
            }

            rowCount = sheet.getLastRowNum(); // Row index starts at 0
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rowCount;
    }

    //  Read a single search term from the given row index
    public static String getSearchTerm(int rowIndex) {
        String value = null;
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(SHEET_NAME);

            if (sheet == null) {
                throw new RuntimeException("Sheet \"" + SHEET_NAME + "\" not found in file: " + EXCEL_PATH);
            }

            XSSFRow row = sheet.getRow(rowIndex);
            if (row != null && row.getCell(0) != null) {
                value = row.getCell(0).getStringCellValue();
            } else {
                System.err.println("Row or Cell is empty at index: " + rowIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
