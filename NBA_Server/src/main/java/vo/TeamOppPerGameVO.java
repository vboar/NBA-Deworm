package vo;

/**
 * TeamOppPerGameVO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:11:41
 */
public class TeamOppPerGameVO {

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
	public Double minute;
	
	/**
	 * 投篮命中数
	 */
	public Double fg;
	
	/**
	 * 投篮出手数
	 */
	public Double fga;
	
	/**
	 * 投篮命中率
	 */
	public Double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	public Double fg3;
	
	/**
	 * 三分出手数
	 */
	public Double fg3a;
	
	/**
	 * 三分命中率
	 */
	public Double fg3_pct;
	
	/**
	 * 两分命中数
	 */
	public Double fg2;
	
	/**
	 * 两分投篮数
	 */
	public Double fg2a;
	
	/**
	 * 两分命中率
	 */
	public Double fg2_pct;
	
	/**
	 * 罚球命中数
	 */
	public Double ft;
	
	/**
	 * 罚球出手数
	 */
	public Double fta;
	
	/**
	 * 罚球命中率
	 */
	public Double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	public Double orb;
	
	/**
	 * 防守篮板
	 */
	public Double drb;
	
	/**
	 * 总篮板
	 */
	public Double trb;
	
	/**
	 * 助攻
	 */
	public Double ast;
	
	/**
	 * 抢断
	 */
	public Double stl;
	
	/**
	 * 盖帽
	 */
	public Double blk;
	
	/**
	 * 失误
	 */
	public Double tov;
	
	/**
	 * 犯规
	 */
	public Double pf;
	
	/**
	 * 得分
	 */
	public Double pts;
	
	/**
	 * 无参构造函数
	 */
	public TeamOppPerGameVO(){}
}
