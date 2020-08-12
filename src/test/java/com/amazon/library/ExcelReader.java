package com.amazon.library;

import com.amazon.constants.ExcelParameters;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class fetches data from excel sheet for execution
 */

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class ExcelReader extends BaseTest {

    Map<String, Object> device = new HashMap<>();

    private String SAMPLE_XLSX_FILE_PATH = "";
    private Workbook workbook;
    private Sheet sheet;

    public Sheet getSheet(String fileName, String sheetName) {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        try {
            workbook = WorkbookFactory.create(new File(System.getProperty("user.dir") + File.separator + fileName));
            // Getting the Sheet at index zero
            sheet = workbook.getSheet(sheetName);
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheet;
    }

    public List<Map<ExcelParameters.shoppingDetails, String>> getShoppinDetails() {
        ExcelReader excel = new ExcelReader();
        String SAMPLE_XLSX_FILE_PATH = "src/test/java/com/amazon/data/AmazonDetails.xlsx";


        Sheet sheet = excel.getSheet(SAMPLE_XLSX_FILE_PATH, "Amazon");
        int username = 0;
        int password = 1;
        int product = 2;


        List<Map<ExcelParameters.shoppingDetails, String>> allshoppingDetials = new ArrayList<>();
        int lastRow = sheet.getLastRowNum();
        for (int rowIndex = 1; rowIndex <= lastRow; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            Map<ExcelParameters.shoppingDetails, String> shippingDetails = new HashMap<>();
            shippingDetails.put(ExcelParameters.shoppingDetails.username, row.getCell(username).getStringCellValue());
            shippingDetails.put(ExcelParameters.shoppingDetails.password, row.getCell(password).getStringCellValue());
            shippingDetails.put(ExcelParameters.shoppingDetails.product, row.getCell(product).getStringCellValue());
            allshoppingDetials.add(shippingDetails);
        }
        return allshoppingDetials;
    }
}