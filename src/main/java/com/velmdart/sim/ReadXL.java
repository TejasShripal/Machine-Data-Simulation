package com.velmdart.sim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
@Component
public class ReadXL {
	List<String> mac = new ArrayList<String>();
	List<Integer> cyct = new ArrayList<Integer>();
	List<Integer> injt = new ArrayList<Integer>();
	public void ReadFile() throws FileNotFoundException {
		File file = ResourceUtils.getFile("classpath:macId.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook wb;
		try {
			wb = new XSSFWorkbook(fis);
			org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
			boolean i1 = true;
			boolean i2 = true;
			boolean i3 = true;
			for(Row row1: sheet){
				Cell c = row1.getCell(0);
				if(c == null || i1 == true) {

				}
				else {
					mac.add(c.getStringCellValue());
					
				}
				i1 = false;
			}
			
			//cycletimeList
			for(Row row1: sheet){
				Cell c = row1.getCell(1);
				if(c == null || i2 == true) {

				}
				else {
					cyct.add((int) c.getNumericCellValue());
					
				}
				i2 = false;
			}
			
			//injectionTimList
			for(Row row1: sheet){
				Cell c = row1.getCell(2);
				if(c == null || i3 == true) {

				}
				else {
					injt.add((int) c.getNumericCellValue());
					
				}
				i3 = false;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			}
} 
}
