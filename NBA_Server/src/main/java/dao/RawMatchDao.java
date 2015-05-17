package dao;

import java.util.List;

import entity.Match;

/**
 * 原始Match数据解析接口
 * 
 * created by JaneLDQ on 2015年5月17日 下午3:51:58
 */
public interface RawMatchDao {

	/**
	 * 解析Match文件
	 */
	public List<Match> getAllMatch();
	
}
