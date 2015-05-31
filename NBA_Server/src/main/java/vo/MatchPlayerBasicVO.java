package vo;

/**
 * MatchPlayerBasic数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:02:36
 */
public class MatchPlayerBasicVO {

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
	public double minute;
	
	/**
	 * 投篮命中数
	 */
	public int fg;
	
	/**
	 * 投篮出手数
	 */
	public int fga;
	
	/**
	 * 投篮命中率
	 */
	public double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	public int fg3;
	
	/**
	 * 三分出手数
	 */
	public int fg3a;
	
	/**
	 * 三分命中率
	 */
	public double fg3_pct;
	
	/**
	 * 罚球命中数
	 */
	public int ft;
	
	/**
	 * 罚球出手数
	 */
	public int fta;
	
	/**
	 * 罚球命中率
	 */
	public double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	public int orb;
	
	/**
	 * 防守篮板
	 */
	public int drb;
	
	/**
	 * 总篮板
	 */
	public int trb;
	
	/**
	 * 助攻
	 */
	public int ast;
	
	/**
	 * 抢断
	 */
	public int stl;
	
	/**
	 * 盖帽
	 */
	public int blk;
	
	/**
	 * 失误
	 */
	public int tov;
	
	/**
	 * 犯规
	 */
	public int pf;
	
	/**
	 * 个人得分
	 */
	public int pts;
	
	/**
	 * +/-
	 */
	public double plus_minus;
	
	/**
	 * 无参构造函数
	 */
	public MatchPlayerBasicVO(){}
	
}
