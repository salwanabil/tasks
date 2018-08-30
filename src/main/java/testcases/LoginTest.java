package testcases;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pages.LoginPage;


/*
 * Author : Salwa Nabil
 * email : salwa_ms1985@live.com 
 */


public class LoginTest extends Base{

	LoginPage login;

	///this method used for running test that takes data from excel file"Data Driven Framework"
	@Test(dataProvider="Static")
	public void tc_001_login_functionality(String uname, String pass) throws Exception {
		login = new LoginPage(driver);
		//User fill login page with user name and password then click login button
		login.enterUsername(uname);		
		login.enterPassword(pass);
		login.clickSignin();
		//after user has logged in , will click logout
		login.Logout();

	}

	///Usage: gets data from excel sheet and loop it, for multiple execution of same test case
	@DataProvider(name="Static")
	public Object[][] testtDataGenerator() throws Exception
	{
		//get path and name of excel sheet
		FileInputStream file = new FileInputStream("./TestData/FacebookTestData.xlsx");
		//creating Workbook of excel .xslx workbook file
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		//get sheet name of workbook that contain login data
		XSSFSheet loginSheet = workbook.getSheet("Login");
		//get count of number of rows that already contain data
		int numberOfData = loginSheet.getPhysicalNumberOfRows();	
		//get data from excel file and put it in 2d array represents [rows][columns] 
		Object [][] testData = new Object [numberOfData][2];

		//looping throw rows and columns
		for (int i = 0; i < numberOfData; i++) {
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell username =  row.getCell(0);
			XSSFCell password =  row.getCell(1);
			testData[i][0] = username.getStringCellValue();
			testData[i][1] = password.getStringCellValue();			
			System.out.println("User :" + username + "has successfully logged in");
		}
		return testData;
	}

}
