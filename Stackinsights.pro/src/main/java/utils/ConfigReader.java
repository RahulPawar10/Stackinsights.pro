package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
public static String properties(String Key) throws IOException
{
	
	Properties prop =new Properties();
	FileInputStream fis=new FileInputStream("C:\\New Working Project\\New folder (4)\\Stackinsights.pro\\Stackinsights.pro\\src\\main\\java\\config\\config.properties");
	
	prop.load(fis);
	
	return prop.getProperty(Key);
	
}
}
