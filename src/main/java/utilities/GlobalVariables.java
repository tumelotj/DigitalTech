package utilities;

import com.aventstack.extentreports.ExtentReports;

public class GlobalVariables {
	static final String projectPath = System.getProperty("user.dir");
	protected final  ExtentReports extent = utilities.ExtentReports.getInstance();
	protected final ObjectRepository objectRepository = new ObjectRepository();
	protected final String absaUrl = objectRepository.absa;
	protected final String addUser = objectRepository.user;
	protected final String firstname = objectRepository.firstName;
	protected final String lastname = objectRepository.lastName;
	protected final String username = objectRepository.userName;
	protected final String pass = objectRepository.password;
	protected final String custA = objectRepository.customerA;
	protected final String custB = objectRepository.customerB;
	protected final String roles = objectRepository.role;
	protected final String mail = objectRepository.email;
	protected final String cellN = objectRepository.cellNumber;
	protected final String cancel = objectRepository.cancel1;
	protected final String save = objectRepository.save1;





}
