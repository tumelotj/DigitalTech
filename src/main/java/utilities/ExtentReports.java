package utilities;
import com.*;

//import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewConfigurer;

//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class ExtentReports extends GlobalVariables{

   private static com.aventstack.extentreports.ExtentReports extent;

    private static String resultsPath;
    private static String resultsFolder;

    public static com.aventstack.extentreports.ExtentReports getInstance() {

        if (extent == null ){
            create_ResultsFolder();
            resultsPath = resultsFolder + "/extent.html";
            createInstance(resultsPath);
        }

        return extent;
    }

    public static com.aventstack.extentreports.ExtentReports createInstance(String fileName) {

        //ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

        ExtentSparkReporter html = new ExtentSparkReporter(fileName);

        html.config().setReportName("HL Scoring Engine Tests");
        html.config().setTheme(Theme.STANDARD);
        html.config().setDocumentTitle("HL Scoring Engine Tests");
        html.config().setEncoding("utf-8");
        //        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
  //      htmlReporter.config().setChartVisibilityOnOpen(true);
        // htmlReporter.loadConfig(System.getProperty("user.home")+"/IdeaProjects/new_application_service/src/main/java/config/ExtentConfig.xml");

        extent = new com.aventstack.extentreports.ExtentReports();
        extent.attachReporter(html);

        return extent;
    }

    public static void create_ResultsFolder() {
        String RESULTSFOLDER = getResultsLocation();
        LocalDate currentDate = LocalDate.now();
        Month m = currentDate.getMonth();
        String displayName = m.getDisplayName ( TextStyle.SHORT , Locale.ENGLISH );
        Path p1 = Paths.get(RESULTSFOLDER,new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(
                Calendar.getInstance().getTime()));
        resultsFolder = p1.toString();
        System.out.println("Results Location : "+ resultsFolder);
        new File(resultsFolder).mkdir();

    }

    public  static String getResultsLocation(){

        return String.valueOf(Paths.get(projectPath,"/reports"));
    }

    public void reportLogger(ExtentTest testReport,String details,
                             WebDriver driver, Boolean takeScreenshot, Status logStatus) throws Exception {
        String fileName = "";

        testReport.log(logStatus,details);

        if (takeScreenshot) {
            fileName = new genericMethods().takeScreenShot("ExtentScreenshot",driver);
            testReport.info("Screen Captured", MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());

        }



    }

}
