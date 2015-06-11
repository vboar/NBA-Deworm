package util;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PythonTest {
	public static void main(String[] args) throws IOException {


        JFrame f = new JFrame();
        f.setVisible(true);
        f.setBounds(100, 100, 500, 500);
		
		Process pi = Runtime.getRuntime().exec("python " +
                "D:\\Vboar\\Documents\\workspace\\my_workspace\\stats\\Demo.py");
        try {
            pi.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JPanel p = new JPanel();
        JLabel j = new JLabel();
        j.setIcon(new ImageIcon("a.png"));
        p.add(j);

        f.setContentPane(p);
        f.revalidate();
        f.repaint();

        //PythonInterpreter interpreter = new PythonInterpreter();
		 //InputStream filepy = new FileInputStream("C:\\__init__.py"); 
		 //interpreter.execfile(filepy); 
		 //filepy.close();
	}
	
}
