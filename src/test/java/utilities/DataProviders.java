package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "LoginData") // name to give to know from which dataprovider the test method gets data.
	public String[][] getData() throws IOException {
		ExcelUtility ex = new ExcelUtility(".\\testData\\opencartlogindata.xlsx");
		int totalrows = ex.getRowcount("Sheet1");
		int totalcols = ex.getCellCount("Sheet1", 1);
		String loginData[][] = new String[totalrows][totalcols];
		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				loginData[i - 1][j] = ex.getCellData("Sheet1", i, j);
			}
		}

		return loginData;

	}
}
