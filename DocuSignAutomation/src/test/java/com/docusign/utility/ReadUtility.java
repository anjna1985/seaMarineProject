package com.docusign.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import io.restassured.path.json.JsonPath;

public class ReadUtility {
//5---> CSV, JSON, Property, Excel, Database
	private ReadUtility() {
		throw new IllegalStateException("Utility class");
	}

	public static List<String[]> readCSV(File file) throws IOException, CsvException {

		FileReader myFile = new FileReader(file);
		CSVReader myCSV = new CSVReader(myFile);
		List<String[]> myData = myCSV.readAll();
		return myData;
	}

	public static String[][] readExcel(File file, String sheetName) throws InvalidFormatException, IOException {
		XSSFWorkbook myWorkBook = new XSSFWorkbook(file);
		XSSFSheet myWorkSheet = myWorkBook.getSheet(sheetName);
		int totalRows = myWorkSheet.getLastRowNum();
		System.out.println("totalRows =" + totalRows);
		XSSFRow rowHeader = myWorkSheet.getRow(0);
		int totalCols = rowHeader.getLastCellNum();
		System.out.println("totalCols =" + totalCols);

		String data[][] = new String[totalRows][totalCols];
		XSSFRow xssfRow;
		XSSFCell xssfCell;
		for (int rows = 1; rows <= totalRows; rows++) {// Because we need to skip the header
			xssfRow = myWorkSheet.getRow(rows);
			for (int cols = 0; cols < totalCols; cols++) {
				xssfCell = xssfRow.getCell(cols);
				data[rows - 1][cols] = xssfCell.toString(); // So that we can store from 0
			}
			System.out.println("");
		}
		return data;
	}

	public static String readPropertyFile(String fileName, String keyName) throws IOException {

		Properties properties = new Properties();
		File f = new File(System.getProperty("user.dir") + "/testData/" + fileName);
		FileReader reader = new FileReader(f);
		properties.load(reader);
		String keyValue = properties.getProperty(keyName);
		return keyValue;

	}

	public static String readJson(String file, String key) {
		JsonPath jsonPath = new JsonPath(new File(System.getProperty("user.dir") + "/testData/" + file));
		String value = jsonPath.get(key) + "";
		return value;
	}

	public static Iterator<String[]> readDbTable(String fileName, String tableName, String query) throws IOException {
		Connection conn = null;
		Iterator<String[]> tableIterator = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://"+readPropertyFile(fileName, "DBHOSTURL")+":"+readPropertyFile(fileName, "PORT")+"/"+readPropertyFile(fileName, "DATABASE")+"?user="+readPropertyFile(fileName, "USERNAME")+"&password="+readPropertyFile(fileName, "PASSWORD"));
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int totalColumnsInTable = rs.getMetaData().getColumnCount();
			List<String[]> tableList = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[totalColumnsInTable];
				for (int iCol = 1; iCol <= totalColumnsInTable; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? null : obj.toString(); // Handling NULL Data
				}
				tableList.add(row);
			}

			 tableIterator = tableList.iterator();
				
			}

		 catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return tableIterator;
	}

}
