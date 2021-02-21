
import com.aventstack.extentreports.ExtentTest;
        import com.aventstack.extentreports.Status;
        import org.apache.log4j.xml.DOMConfigurator;
        import org.openqa.selenium.WebDriver;
        import org.testng.annotations.AfterMethod;
        import org.testng.annotations.BeforeTest;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;

        import utilities.*;
        import pageObjects.*;

public class AddUser extends GlobalVariables {
    driverSetup newDriverClass;
    pageObjects pageMethodsClass;
    ExtentReports extentReports;
    private WebDriver localDriver;
    private static ExtentTest testReport;
    @BeforeTest
    public void setUp() throws Exception {
        DOMConfigurator.configure("log4j2.xml");
        System.out.println("Wait for tests to initialize");
        System.out.println(this.getClass().getSimpleName());
        extentReports = new ExtentReports();

        newDriverClass = new driverSetup();

    }

    @Test(dataProvider="getData")
    public void unitTest(String...data){


        try{
            String scenarioName = data[0];
            String executionMode = data[1];
            String fName = data[2];
            String secName = data[3];
            String userN = data[4];
            String passW = data[5];
            String custom = data[6];
            String role = data[7];
            String eMail = data[8];
            String cell = data[9];

            if(executionMode.equalsIgnoreCase("yes")){
                testReport = extent.createTest(scenarioName+"_"+this.getClass().getSimpleName()," Testing the Add User process");
                extentReports.reportLogger(testReport,"Starting execution for scenario ''"+scenarioName+"_"+this.getClass().getSimpleName(),localDriver,false, Status.INFO);
                localDriver = newDriverClass.openBrowser("chrome");
                pageMethodsClass = new pageObjects(localDriver);

                pageMethodsClass.navigate(absaUrl);
                pageMethodsClass.waitForElementToAppear(addUser);
                extentReports.reportLogger(testReport,"Navigate to User List Table is successful",localDriver,true, Status.PASS);
                pageMethodsClass.click(addUser);
                extentReports.reportLogger(testReport,"Clicked Add User button successfully",localDriver,true, Status.PASS);
                pageMethodsClass.waitForElementToAppear(firstname);
                pageMethodsClass.input(firstname, fName);
                extentReports.reportLogger(testReport,"Entered First Name successfully",localDriver,true, Status.PASS);
                pageMethodsClass.input(lastname,secName);
                extentReports.reportLogger(testReport,"Entered The Last Name successfully",localDriver,true, Status.PASS);
                pageMethodsClass.input(username,userN);
                extentReports.reportLogger(testReport,"Entered Username successfully",localDriver,true, Status.PASS);
                pageMethodsClass.input(pass,passW);
                extentReports.reportLogger(testReport,"Entered Password successfully",localDriver,true, Status.PASS);

                switch(custom)
                { 
                    case "Customer AAA" :
                        pageMethodsClass.click(custA);
                        extentReports.reportLogger(testReport,"Radio button clicked successfully",localDriver,true, Status.PASS);
                        break;
                    case  "Customer BBB" :
                        pageMethodsClass.click(custB);
                        extentReports.reportLogger(testReport,"Radio button clicked successfully",localDriver,true, Status.PASS);
                        break;
                }
                pageMethodsClass.select(roles, role);
                extentReports.reportLogger(testReport,"Role Selected successfully",localDriver,true, Status.PASS);
                pageMethodsClass.input(mail,eMail);
                extentReports.reportLogger(testReport,"Entered mail successfully",localDriver,true, Status.PASS);
                pageMethodsClass.input(cellN, cell);
                extentReports.reportLogger(testReport,"Entered cellphone number successfully",localDriver,true, Status.PASS);
                pageMethodsClass.click(save);
                extentReports.reportLogger(testReport,"Clicked the Save button successfully",localDriver,true, Status.PASS);

                extent.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @DataProvider
    public Object[][] getData() {
        Object [][] dataObject = null;
        try {
            dataObject = ExcelUtils.readExcel();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataObject;
    }

    @AfterMethod
    public void killDriver(){
        localDriver.quit();
        extent.flush();
    }
}
