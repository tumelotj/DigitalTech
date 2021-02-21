package utilities;

import pageObjects.pageObjects;

import java.io.File;
import java.util.ArrayList;

public class FileUtils {
	public static File loadObjectsRepository(String fileName) {
		try {
			//Get file from resources folder
			ClassLoader classLoader = pageObjects.class.getClassLoader();
			return new File(classLoader.getResource(fileName).getFile());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-9);

		}
		return null;


	}


}



