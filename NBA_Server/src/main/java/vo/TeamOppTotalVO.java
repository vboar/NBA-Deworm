package vo;

/**
 * TeamOpponenttotal数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:12:43
 */
public class TeamOppTotalVO {

	/**
	 * 球队
	 */
	public String abbr;
	
	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * 比赛场数
	 */
	public int num_of_game;
	
	/**
	 * 在场时间
	 */
	public int minute;
	
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
	 * 两分命中数
	 */
	public int fg2;
	
	/**
	 * 两分投篮数
	 */
	public int fg2a;
	
	/**
	 * 两分命中率
	 */
	public double fg2_pct;
	
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
	 * 得分
	 */
	public int pts;

	/**
	 * 无参构造函数
	 */
	public TeamOppTotalVO(){}
	
}