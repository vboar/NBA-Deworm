package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.SeasonDao;

/**
 *　TODO season的dao具体实现
 * 
 * created by JaneLDQ on 2015年5月17日 下午10:45:30
 */
public class SeasonDaoImpl implements SeasonDao {

	SqlManager sqlManager = SqlManager.getSqlManager();
	
	@Override
	public void insert(String season) {

	}

	@Override
	public void insert(List<String> list) {
        sqlManager.getConnection();

        List<Object> objects = new ArrayList<Object>();
        String sql = "INSERT INTO season (season) VALUES ";
        for (String s: list) {
            objects.add(s);
            sql += "(?),";
        }
        sql = sql.substring(0, sql.length()-1);
        sqlManager.executeUpdateByList(sql, objects);

        sqlManager.releaseConnection();
	}

	@Override
	public List<String> getAllSeason() {
		return null;
	}

}
