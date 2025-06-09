package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class EmpDetailsFromExcel {
    public static void main(String[] args) throws FileNotFoundException {
//        String excelFilePath = "C:\\Users\\Dell\\IdeaProjects\\EmployeeDetails.xlsx";
        String excelFilePath = System.getProperty("user.dir") + "\\src\\test\\Resources\\EmployeeDetails.xlsx";
        try(FileInputStream fis= new FileInputStream(new File(excelFilePath));
         Workbook workbook = new XSSFWorkbook(fis))
        {
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet){

                if(row.getRowNum() == 0) continue;

                String empNo =  row.getCell(0).getStringCellValue();
                String empName = row.getCell(1).getStringCellValue();
                String empDesig = row.getCell(2).getStringCellValue();
                String empSal = row.getCell(3).getStringCellValue();
                String empDept = row.getCell(4).getStringCellValue();

                System.out.println("EMP No: " + empNo +
                        ", Name: " + empName +
                        ", Designation: " + empDesig +
                        ", Salary: " + empSal +
                        ", Department: " + empDept );



            }

        }catch (IOException e){
        System.out.println("Error reading the Excel file: " + e.getMessage());
        }
    }


}
