package vo;

/**
 * TeamPerGameVO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:13:55
 */
public class TeamPerGameVO {
	
	/**
	 * 球队（缩写）
	 */
	public String abbr;
	
	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * 时间
	 */
	public double minute;
	
	/**
	 * 投篮命中数
	 */
	public double fg;
	
	/**
	 * 投篮出手数
	 */
	public double fga;
	
	/**
	 * 投篮命中率
	 */
	public double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	public double fg3;
	
	/**
	 * 三分出手数
	 */
	public double fg3a;
	
	/**
	 * 三分命中率
	 */
	public double fg3_pct;
	
	/**
	 * 两分命中数
	 */
	public double fg2;
	
	/**
	 * 两分投篮数
	 */
	public double fg2a;
	
	/**
	 * 两分命中率
	 */
	public double fg2_pct;
	
	/**
	 * 罚球命中数
	 */
	public double ft;
	
	/**
	 * 罚球出手数
	 */
	public double fta;
	
	/**
	 * 罚球命中率
	 */
	public double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	public double orb;
	
	/**
	 * 防守篮板
	 */
	public double drb;
	
	/**
	 * 总篮板
	 */
	public double trb;
	
	/**
	 * 助攻
	 */
	public double ast;
	
	/**
	 * 抢断
	 */
	public double stl;
	
	/**
	 * 盖帽
	 */
	public double blk;
	
	/**
	 * 失误
	 */
	public double tov;
	
	/**
	 * 犯规
	 */
	public double pf;
	
	/**
	 * 得分
	 */
	public double pts;

	/**
	 * 无参构造函数
	 */
	public TeamPerGameVO() {}

}
