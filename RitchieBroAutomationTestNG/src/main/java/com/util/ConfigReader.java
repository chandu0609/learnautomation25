package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;

	public Properties init_Prop() {
		prop = new Properties();
		FileInputStream fip;
		try {
			fip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/Config.properties");
			prop.load(fip);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}
