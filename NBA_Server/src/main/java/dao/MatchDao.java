package dao;

import java.util.List;

import entity.Match;

/**
 * 比赛Dao抽象接口
 * 
 * created by JaneLDQ on 2015年5月18日 下午5:06:44
 */
public interface MatchDao {
	
    /**
     * 插入比赛的完整信息和数据
     * @param list Match的List
     */
    public void insertMatch(List<Match> list);
}
