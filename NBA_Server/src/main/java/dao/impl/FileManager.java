package dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 文件数据管理类
 * Created by Vboar on 2015/5/17.
 */
public class FileManager {

    /**
     * 原始数据存储路径
     */
    public static String DATA_PATH = null;
    
    static{
    	Properties prop = new Properties();
    	try{
    		InputStream in = new BufferedInputStream(new FileInputStream("nba.properties"));
    		prop.load(in);
    		DATA_PATH = prop.getProperty("datapath");
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
    
    /**
     * 空构造
     */
    public FileManager(){}

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
