package util;

public class SystemPosition {
	
	public static String getSystemPosition(){
		return  System.getProperty("user.dir"); 

	}
public static void main(String[] args) {
	System.out.println(getSystemPosition());
}
}
