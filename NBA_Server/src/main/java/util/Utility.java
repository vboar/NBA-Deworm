package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用工具类
 * 
 * created by JaneLDQ on 2015年5月17日 下午5:19:50
 */
public class Utility {
	
    /**
     * 时间（分钟）转换 String 转 double
     * @param minutes 分钟 String
     * @return 分钟 double
     */
    public static Double minStringToDouble(String minutes){
        if(minutes.equals("")) {
            return null;
        }else{
            String[] time = minutes.split(":");
            int minute = Integer.parseInt(time[0]);
            double second = 0;
            if(time.length>1)
            	second = Double.parseDouble(time[1])/60;
            return (minute+second);
        }
    }

    /**
     *时间（分钟）转换 double 转 String
     * @param minutes 分钟 double
     * @return 分钟 String
     */
    public static String minDoubleToString(double minutes){
        int min = (int)minutes;
        int second = (int)((minutes - min)*60);
        return Integer.toString(min)+":"+Integer.toString(second);
    }
    
	
    /**
     * 将字符串转化为int值
     * @param str 字符串
     * @return int值
     */
    public static Integer stringToInt(String str) {
        try {
    		if(str.contains(",")){
    			str = str.replaceAll(",", "");
    		}
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 将字符串转double值
     * @param str
     * @return
     */
    public static Double stringToDouble(String str){
    	try{
    		if(str.contains(",")){
    			str = str.replaceAll(",", "");
    		}
    		return Double.parseDouble(str);
    	}catch(Exception e){
    		return null;
    	}
    }
    
    /**
     * 将对象转为Integer
     * @param o
     * @return
     */
    public static Integer objectToInt(Object o){
    	if(o==null)
    		return null;
    	return Utility.stringToInt(o.toString());
    }
    
    /**
     * 将对象转为Double
     * @param o
     * @return
     */
    public static Double objectToDouble(Object o){
    	if(o==null)
    		return null;
    	return Utility.stringToDouble(o.toString());
    }

    /**
     * 将Object转为Long
     * @param o
     * @return
     */
	public static Long salaryObjectToLong(Object o) {
		if(o==null)
			return null;
		return Utility.salaryToLong(o.toString());
	}

	/**
	 * 将
	 * @param str
	 * @return
	 */
	public static Long salaryToLong(String str) {
		if(str==null)
			return null;
		str = str.replace("$", "");
		str = str.replace(",", "");
		return Long.parseLong(str);
	}
	

	/**
	 * 写入多行数据到文本文件（覆盖）
	 * 
	 * @param path
	 *            存储路径
	 */
	public static void writeMulti(List<String> list, String path) {
		try {
			FileWriter fw = new FileWriter(path, false);
			for (String s : list) {
				fw.write(s + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeMultiAppend(List<String> list, String path){
		try {
			FileWriter fw = new FileWriter(path, true);
			for (String s : list) {
				fw.write(s + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入一行数据到文本文件（覆盖）
	 * 
	 * @param s
	 * @param path
	 */
	public static void write(String s, String path) {
		try {
			FileWriter fw = new FileWriter(path, false);
			fw.write(s + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 从文本文件中读取数据
     * @param path 文件路径
     * @return String的ArrayList
     */
    public static List<String> read(String path) {
        List<String> lists = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File(path)), "UTF-8")
            );
            String temp = null;
            while((temp = br.readLine()) != null) {
                lists.add(temp);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }
}
