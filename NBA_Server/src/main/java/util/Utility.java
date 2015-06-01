package util;

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
}
