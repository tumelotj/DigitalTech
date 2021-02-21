package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class genericMethods {

	public String takeScreenShot(String FileName, WebDriver driver) throws Exception {
		String fileName = "Empty";
		try {
			String sDefaultPath = System.getProperty("user.dir");
			sDefaultPath = sDefaultPath.replace("batch", "");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			fileName = sDefaultPath + "/screenshots/" + FileName + timeStamp + ".png";
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		return fileName;
	}

}
