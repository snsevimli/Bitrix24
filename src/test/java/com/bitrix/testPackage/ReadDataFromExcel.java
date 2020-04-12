package com.bitrix.testPackage;

import com.bitrix.utilities.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

public class ReadDataFromExcel {


    @Test
    public void readExcelFileTest() throws Exception {

        // we need to get a file as an object
        File file = new File("BitrixCredentials.xlsx");

        //object that represents excel file
        Workbook workbook = WorkbookFactory.create(file);

        Sheet workSheet = workbook.getSheet("Credentials");
        //get 1st row
        Row firstRow = workSheet.getRow(0);
        //get 1st cell
        Cell firstCell = firstRow.getCell(0);
        // get string value
        String value = firstCell.getStringCellValue();

        String secondCellValue = firstRow.getCell(1).getStringCellValue();

        System.out.println(value);
        System.out.println(secondCellValue);

        System.out.println("#################################################");

        int lastCell = firstRow.getLastCellNum();

        for (int i = 0; i < lastCell; i++) {
            System.out.print(firstRow.getCell(i) + " | ");
        }

        // last row is 16th --> index is 15
        // index of last row
        int numberOfRows = workSheet.getLastRowNum();
        //returns how many rows at all
        int numberOfRows2 = workSheet.getPhysicalNumberOfRows();
        System.out.println("\nIndex of last row   : " + numberOfRows);
        System.out.println("\nNumber of rows 2 : " + numberOfRows2);

        System.out.println("###############################################");

        for (int row = 0; row < workSheet.getPhysicalNumberOfRows(); row++) {
            for (int cell = 0; cell < workSheet.getRow(row).getLastCellNum(); cell++) {
                String cellValue = workSheet.getRow(row).getCell(cell).getStringCellValue();
                System.out.print(cellValue + " | ");
            }
            System.out.println();
        }
    }

    @Test
    public void execUtilityTest() {
        String path = "BitrixCredentials.xlsx";
        String spreadSheet = "Credentials";
        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);
        // https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
        //  excelUtil.getDataList().forEach(System.out::println);

        for (Map<String, String> record : excelUtil.getDataList()) {
            System.out.println(record);
        }
    }

    @Test
    public void getColumnNamesTest() {
        String path = "BitrixCredentials.xlsx";
        String spreadSheet = "Credentials";
        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);

        System.out.println(excelUtil.getColumnsNames());
    }

    @Test
    public void getCredentials() {
        String path = "BitrixCredentials.xlsx";
        String spreadSheet = "Credentials";
        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);
        System.out.println(Arrays.deepToString(excelUtil.getDataArray()));
    }
}
