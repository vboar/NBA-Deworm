package vo;

import java.io.Serializable;

/**
 * TeamAdvanced数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:09:15
 */
public class TeamAdvancedVO implements Serializable{

	private static final long serialVersionUID = 1L;

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
	public Double pw;
	
	/**
	 * PL(losses_pyth)
	 * Pythagorean losses, i.e., expected losses based on points scored and allowed
	 */
	public Double pl;
	
	/**
	 * MOV =Margin of Victory
	 */
	public Double mov;
	
	/**
	 * SOS = Strength of Schedule
	 * A rating of strength of schedule. The rating is denominated in points above/below average,
	 * where zero is average.
	 */
	public Double sos;
	
	/**
	 * SRS = Simple Rating System
	 * A team rating that takes into account average point differential and strength of schedule. 
	 * The rating is denominated in points above/below average, where zero is average.
	 */
	public Double srs;
	
	/**
	 * ORtg = Offensive Rating
	 * An estimate of points produced (players) or scored (teams) per 100 possessions
	 */
	public Double off_rtg;
	
	/**
	 * DRtg = Defensive Rating
	 * An estimate of points allowed per 100 possessions
	 */
	public Double def_rtg;
	
	/**
	 * Pace = Pace Factor
	 * An estimate of possessions per 48 minutes
	 */
	public Double pace;
	
	/**
	 * Free Throw Attempt Rate
	 */
	public Double fta_per_fga_pct;
	
	/**
	 * 3-Point Attempt Rate
	 */
	public Double fg3a_per_fga_pct;
	
	/**
	 * 进攻-Effective Field Goal Percentage
	 */
	public Double off_efg_pct;
	
	/**
	 * 进攻-Turnover Percentage
	 */
	public Double off_tov_pct;
	
	/**
	 * 进攻-进攻篮板率
	 */
	public Double orb_pct;
	
	/**
	 * 进攻-Free Throws Per Field Goal Attempt
	 */
	public Double off_ft_rate;
	
	/**
	 * 防守-Effective Field Goal Percentage
	 */
	public Double opp_efg_pct;
	
	/**
	 * 防守-Turnover Perce
	 */
	public Double opp_tov_pct;
	
	/**
	 * 防守-防守篮板率
	 */
	public Double drb_pct;
	
	/**
	 * 防守-Opponent Free Throw per Field Goal Attempt
	 */
	public Double opp_ft_rate;
	
	/**
	 * Arena_name
	 */
	public String arena;
	
	/**
	 * attendance
	 */
	public Integer attendance;
	
	/**
	 * 无参构造函数
	 */
	public TeamAdvancedVO(){}
	
}
