package com.docusign.dataprovider;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import com.docusign.utility.ReadUtility;
import com.opencsv.exceptions.CsvException;

public class TestDataProvider {
	/*
	 * Important Points to Remember:
	 * 
	 * 1) Data Provider can ONLY return Data in Following Format: Object[][] or
	 * Object[] OR Iterator<E>.
	 * 
	 * 2)Object Class in JAVA: The Object class is the parent class of all the
	 * classes in java by default. In other words, it is the topmost class of java.
	 */
	@DataProvider(name = "loginTestDataProvider")
	public Iterator<String[]> userNameTestData() throws IOException, CsvException {
		// CSV Data
		File f = new File(System.getProperty("user.dir") + "/testData/userData.csv");
		List<String[]> testDataList = ReadUtility.readCSV(f);// PS ---Convert List<String[]>
		Iterator<String[]> testDataListIterator = testDataList.iterator();
		testDataListIterator.next(); // TO SKIP the HEADERS
		return testDataListIterator;
	}

	@DataProvider(name = "ProductTestDataProvider")
	public String[][] prodTestData() throws IOException, InvalidFormatException {
		// Reading EXCEL FILE
		File f = new File(System.getProperty("user.dir") + "/testData/TestData.xlsx");
		String[][] testDataArray = ReadUtility.readExcel(f, "ProductName");// PS ---Convert List<String[]>
		return testDataArray;
	}

	@DataProvider(name = "ProductTestDataProviderDB")
	public  Iterator<String[]> prodTestDataDB() throws IOException, InvalidFormatException {
		// Reading EXCEL FILE
		
		Iterator<String[]> testDataIterator= ReadUtility.readDbTable("database.properties", "ProductTestData", "select * from ProductTestData");
		return testDataIterator;
	}
}
