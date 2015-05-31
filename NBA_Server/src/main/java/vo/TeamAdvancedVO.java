package vo;

/**
 * TeamAdvanced数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:09:15
 */
public class TeamAdvancedVO {


	/**
	 * 球队
	 */
	public String abbr;
	
	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * PW(wins_pyth)
	 * Pythagorean wins, i.e., expected wins based on points scored and allowed
	 */
	public double pw;
	
	/**
	 * PL(losses_pyth)
	 * Pythagorean losses, i.e., expected losses based on points scored and allowed
	 */
	public double pl;
	
	/**
	 * MOV =Margin of Victory
	 */
	public double mov;
	
	/**
	 * SOS = Strength of Schedule
	 * A rating of strength of schedule. The rating is denominated in points above/below average,
	 * where zero is average.
	 */
	public double sos;
	
	/**
	 * SRS = Simple Rating System
	 * A team rating that takes into account average point differential and strength of schedule. 
	 * The rating is denominated in points above/below average, where zero is average.
	 */
	public double srs;
	
	/**
	 * ORtg = Offensive Rating
	 * An estimate of points produced (players) or scored (teams) per 100 possessions
	 */
	public double off_rtg;
	
	/**
	 * DRtg = Defensive Rating
	 * An estimate of points allowed per 100 possessions
	 */
	public double def_rtg;
	
	/**
	 * Pace = Pace Factor
	 * An estimate of possessions per 48 minutes
	 */
	public double pace;
	
	/**
	 * Free Throw Attempt Rate
	 */
	public double fta_per_fga_pct;
	
	/**
	 * 3-Point Attempt Rate
	 */
	public double fg3a_per_fga_pct;
	
	/**
	 * 进攻-Effective Field Goal Percentage
	 */
	public double off_efg_pct;
	
	/**
	 * 进攻-Turnover Percentage
	 */
	public double off_tov_pct;
	
	/**
	 * 进攻-进攻篮板率
	 */
	public double orb_pct;
	
	/**
	 * 进攻-Free Throws Per Field Goal Attempt
	 */
	public double off_ft_rate;
	
	/**
	 * 防守-Effective Field Goal Percentage
	 */
	public double opp_efg_pct;
	
	/**
	 * 防守-Turnover Perce
	 */
	public double opp_tov_pct;
	
	/**
	 * 防守-防守篮板率
	 */
	public double drb_pct;
	
	/**
	 * 防守-Opponent Free Throw per Field Goal Attempt
	 */
	public double opp_ft_rate;
	
	/**
	 * Arena_name
	 */
	public String arena;
	
	/**
	 * attendance
	 */
	public int attendance;
	
	/**
	 * 无参构造函数
	 */
	public TeamAdvancedVO(){}
	
}
