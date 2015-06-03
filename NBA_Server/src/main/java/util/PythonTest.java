package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PythonTest {
	public static void main(String[] args) throws IOException {
		
		Runtime.getRuntime().exec("python C:\\__init__.py");
		//PythonInterpreter interpreter = new PythonInterpreter();  
		 //InputStream filepy = new FileInputStream("C:\\__init__.py"); 
		 //interpreter.execfile(filepy); 
		 //filepy.close();
	}
	
}
