package dao.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库管理类
 * 
 * Created by Vboar on 2015/5/17.
 */
public class SqlManager {

    // 单例
    private static SqlManager sqlManager;
    
    // 数据库信息
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = null;
    private static String IP = null;
    private static String USER = null;
    private static String PASSWORD = null;
    private static String DB = null;
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    
    static{
    	Properties prop = new Properties();
    	try{
    		InputStream in = new BufferedInputStream(new FileInputStream("nba.properties"));
    		prop.load(in);
    		IP = prop.getProperty("db_ip");
    		USER = prop.getProperty("db_user");
    		PASSWORD = prop.getProperty("db_password");
    		DB = prop.getProperty("db");
    		URL = "jdbc:mysql://" + IP +  "/" + DB;
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
    /**
     * 构造函数 初始化
     */
    private SqlManager() {

        // 加载驱动
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }
    }
    
    /**
     * 单例模式
     * @return
     */
    public static SqlManager getSqlManager() {
        if (sqlManager != null) {
            return sqlManager;
        }
        return new SqlManager();
    }
    
    /**
     * 获得数据库连接
     * @return Connection
     */
    public synchronized Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 提交更改
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放Connection资源
     */
    public void releaseConnection() {
        if (connection != null) {
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放PreparedStatement资源
     */
    public void releasePreparedStatement() {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放statement资源
     */
    public void releaseStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放ResultSet资源
     */
    public void releaseResultSet() {

        if(resultSet != null){
            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放所有资源
     */
    public void releaseAll() {
        releasePreparedStatement();
        releaseResultSet();
        releaseConnection();
    }

    /**
     * 更新数据库的SQL语句的统一方法
     * @param sql SQL语句
     * @return 结果
     */
    public boolean executeUpdate(String sql) {
        // 受影响的行数
        int affectedLine = 0;
        try {
            statement = connection.createStatement();
            affectedLine = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseStatement();
        }
        return affectedLine > 0;
    }

    /**
     * 更新数据库的SQL语句的统一方法
     * @param sql SQL语句
     * @param params 参数数组
     * @return 结果
     */
    public boolean executeUpdate(String sql,Object[] params) {

        // 受影响的行数
        int affectedLine = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            affectedLine = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releasePreparedStatement();
        }

        return affectedLine > 0;
    }

    /**
     * 更新数据库的SQL语句的统一方法（参数是ArrayList）
     * @param sql SQL语句
     * @param params 参数list
     * @return 结果
     */
    public boolean executeUpdateByList(String sql,List<Object> params) {

        boolean result = false;

        // 受影响的行数
        int affectedLine = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                	
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            affectedLine = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releasePreparedStatement();
        }

        result = affectedLine > 0;
        return result;
    }

    /**
     * 查询单条信息
     * @param sql SQL语句
     * @param params 参数数组
     * @return 单条信息HashMap
     */
    public Map<String, Object> querySimple(String sql, Object[] params) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int col_length = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 0; i < col_length; i++) {
                    String key = resultSetMetaData.getColumnName(i+1);
                    Object value = resultSet.getObject(key);
                    if (value == null) {
                        value = "";
                    }
                    map.put(key, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询多条信息
     * @param sql SQL语句
     * @param params 参数数组
     * @return 多条信息 HashMap的ArrayList
     */
    public ArrayList<Map<String, Object>> queryMulti(String sql, Object[] params) {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int col_length = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < col_length; i++) {
                    String key = resultSetMetaData.getColumnName(i+1);
                    Object value = resultSet.getObject(key);
                    if (value == null) {
                        value = "";
                    }
                    map.put(key, value);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询多条信息
     * @param sql SQL语句
     * @param params 参数数组
     * @return 多条信息 HashMap的ArrayList
     */
    public ArrayList<Map<String, Object>> queryMultiByList(String sql, List<Object> params) {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(params != null && params.size() != 0) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int col_length = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < col_length; i++) {
                    String key = resultSetMetaData.getColumnName(i+1);
                    Object value = resultSet.getObject(key);
                    if (value == null) {
                        value = "";
                    }
                    map.put(key, value);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询单条信息
     * @param sql SQL语句
     * @param params 参数数组
     * @return 单条信息ObjectList
     */
    public ArrayList<Object> querySimpleForSimple(String sql, Object[] params) {
        ArrayList<Object> list = new ArrayList<Object>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int col_length = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 0; i < col_length; i++) {
                    String key = resultSetMetaData.getColumnName(i+1);
                    Object value = resultSet.getObject(key);
                    if (value == null) {
                        value = "";
                    }
                    list.add(value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询多条信息
     * @param sql SQL语句
     * @param params 参数数组
     * @return 多条信息 ObjectList的ArrayList
     */
    public ArrayList<ArrayList<Object>> queryMultiForSimple(String sql, Object[] params) {
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int col_length = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                ArrayList<Object> objects = new ArrayList<Object>();
                for (int i = 0; i < col_length; i++) {
                    String key = resultSetMetaData.getColumnName(i+1);
                    Object value = resultSet.getObject(key);
                    if (value == null) {
                        value = "";
                    }
                    objects.add(value);
                }
                list.add(objects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 补全SQL参数
     * @param sql SQL语句
     * @param num 参数数量
     * @return SQL
     */
    public String appendSQL(String sql, int num) {
        sql += "(";
        for (int i = 0; i < num; i++) {
            if (i == num-1) {
                sql += "?),";
                break;
            }
            sql += "?,";
        }
        return sql;
    }

    /**
     * 去除SQL多余逗号
     * @param sql SQL语句
     * @return SQL
     */
    public String fillSQL(String sql) {
        return sql.substring(0, sql.length()-1);
    }

    /**
     * 补全SQL参数和去除SQL多余逗号
     * @param sql SQL语句
     * @param num 参数数量
     * @return SQL
     */
    public String appendAndFillSQL(String sql, int num) {
        sql += "(";
        for (int i = 0; i < num; i++) {
            if (i == num-1) {
                sql += "?)";
                break;
            }
            sql += "?,";
        }
        return sql;
    }

    public static void main(String[] args) {
        SqlManager.getSqlManager().getConnection();

    }
}
