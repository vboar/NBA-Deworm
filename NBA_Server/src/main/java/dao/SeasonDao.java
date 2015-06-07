package dao;

import java.util.List;

/**
 * 赛季数据抽象接口
 * 
 * created by JaneLDQ on 2015年5月17日 下午10:43:46
 */
public interface SeasonDao {

    /**
     * 插入一条赛季信息
     * @param season 赛季名称
     */
    public void insert(String season);

    /**
     * 插入多条赛季信息
     * @param list Season的List
     */
    public void insert(List<String> list);

    /**
     * 获得所有赛季信息,倒序排列
     */
    public List<String> getAllSeason();
    
}
