package vo;

import java.io.Serializable;

/**
 * MatchPlayerBasic数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:02:36
 */
public class MatchPlayerBasicVO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 比赛编号
	 */
	public String game_id;
	
	/**
	 * 球员名
	 */
	public String player_name;
	
	/**
	 * 球队缩写
	 */
	public  String team_abbr;
	
	/**
	 * 首发/球队(TeamTotal球队总数据，Starter首发球员，Reserve是非首发上场，DidNotPlayer冷板凳)
	 */
	public String starter;
	
	/**
	 * 上场时间
	 */
	public Double minute;
	
	/**
	 * 投篮命中数
	 */
	public Integer fg;
	
	/**
	 * 投篮出手数
	 */
	public Integer fga;
	
	/**
	 * 投篮命中率
	 */
	public Double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	public Integer fg3;
	
	/**
	 * 三分出手数
	 */
	public Integer fg3a;
	
	/**
	 * 三分命中率
	 */
	public Double fg3_pct;
	
	/**
	 * 罚球命中数
	 */
	public Integer ft;
	
	/**
	 * 罚球出手数
	 */
	public Integer fta;
	
	/**
	 * 罚球命中率
	 */
	public Double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	public Integer orb;
	
	/**
	 * 防守篮板
	 */
	public Integer drb;
	
	/**
	 * 总篮板
	 */
	public Integer trb;
	
	/**
	 * 助攻
	 */
	public Integer ast;
	
	/**
	 * 抢断
	 */
	public Integer stl;
	
	/**
	 * 盖帽
	 */
	public Integer blk;
	
	/**
	 * 失误
	 */
	public Integer tov;
	
	/**
	 * 犯规
	 */
	public Integer pf;
	
	/**
	 * 个人得分
	 */
	public Integer pts;
	
	/**
	 * +/-
	 */
	public Double plus_minus;
	
	/**
	 * 无参构造函数
	 */
	public MatchPlayerBasicVO(){}
	
}
