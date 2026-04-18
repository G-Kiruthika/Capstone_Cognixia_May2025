package config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
	public static String loginTitle;
	public static String validEmail;
    public static String validPassword;
    public static String invalidEmail;
    public static String invalidPassword;
    public static String continueBtnText;
    public static String signInBtnText;
    public static String assertPassMessage;
    public static String assertFailMessage;
    
	static Properties prop = new Properties();
	 
    public static void loadConfig() {
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            prop.load(fis);
            
            loginTitle = prop.getProperty("loginTitle");
             validEmail = prop.getProperty("validEmail");
             validPassword = prop.getProperty("validPassword");
             invalidEmail = prop.getProperty("invalidEmail");
             invalidPassword = prop.getProperty("invalidPassword");
             continueBtnText = prop.getProperty("continueBtnText");
             signInBtnText = prop.getProperty("signInBtnText");
             
             assertFailMessage = prop.getProperty("assertFailMessage");
             assertPassMessage = prop.getProperty("assertPassMessage");
             
             
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static String get(String key) {
        return prop.getProperty(key);
    }
}
