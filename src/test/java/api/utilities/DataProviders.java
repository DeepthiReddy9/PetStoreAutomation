package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx";
		XLUtility xl=new XLUtility(path);
		int rowCount=xl.getRowCount("Sheet1");
		int colCount=xl.getCellCount("Sheet1",1);
		
		String apidata[][]=new String[rowCount][colCount];
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx";
		XLUtility xl=new XLUtility(path);
		int rowCount=xl.getRowCount("Sheet1");
		String apidata[]=new String[rowCount];
		for(int i=1;i<=rowCount;i++) {
			apidata[i-1]=xl.getCellData("Sheet1", i, 1);

		}
		return apidata;


		
	}
}
