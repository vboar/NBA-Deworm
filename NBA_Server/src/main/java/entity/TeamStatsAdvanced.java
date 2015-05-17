package entity;

/**
 * 球队赛季高阶数据
 * 
 * created by JaneLDQ on 2015年5月17日 下午2:53:10
 */
public class TeamStatsAdvanced {

	/**
	 * 球队
	 */
	private String abbr;
	
	/**
	 * 赛季
	 */
	private String season;
	
	/**
	 * PW(wins_pyth)
	 * Pythagorean wins, i.e., expected wins based on points scored and allowed
	 */
	private double pw;
	
	/**
	 * PL(losses_pyth)
	 * Pythagorean losses, i.e., expected losses based on points scored and allowed
	 */
	private double pl;
	
	/**
	 * MOV =Margin of Victory
	 */
	private double mov;
	
	/**
	 * SOS = Strength of Schedule
	 * A rating of strength of schedule. The rating is denominated in points above/below average,
	 * where zero is average.
	 */
	private double sos;
	
	/**
	 * SRS = Simple Rating System
	 * A team rating that takes into account average point differential and strength of schedule. 
	 * The rating is denominated in points above/below average, where zero is average.
	 */
	private double srs;
	
	/**
	 * ORtg = Offensive Rating
	 * An estimate of points produced (players) or scored (teams) per 100 possessions
	 */
	private double off_rtg;
	
	/**
	 * DRtg = Defensive Rating
	 * An estimate of points allowed per 100 possessions
	 */
	private double def_rtg;
	
	/**
	 * Pace = Pace Factor
	 * An estimate of possessions per 48 minutes
	 */
	private double pace;
	
	/**
	 * Free Throw Attempt Rate
	 */
	private double fta_per_fga_pct;
	
	/**
	 * 3-Point Attempt Rate
	 */
	private double fg3a_per_fga_pct;
	
	/**
	 * 进攻-Effective Field Goal Percentage
	 */
	private double off_efg_pct;
	
	/**
	 * 进攻-Turnover Percentage
	 */
	private double off_tov_pct;
	
	/**
	 * 进攻-进攻篮板率
	 */
	private double orb_pct;
	
	/**
	 * 进攻-Free Throws Per Field Goal Attempt
	 */
	private double off_ft_rate;
	
	/**
	 * 防守-Effective Field Goal Percentage
	 */
	private double opp_efg_pct;
	
	/**
	 * 防守-Turnover Perce
	 */
	private double opp_tov_pct;
	
	/**
	 * 防守-防守篮板率
	 */
	private double drb_pct;
	
	/**
	 * 防守-Opponent Free Throw per Field Goal Attempt
	 */
	private double opp_ft_rate;
	
	/**
	 * Arena_name
	 */
	private String arena;
	
	/**
	 * attendance
	 */
	private int attendance;
	
	/**
	 * 无参构造函数
	 */
	public TeamStatsAdvanced(){}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public double getPw() {
		return pw;
	}

	public void setPw(double pw) {
		this.pw = pw;
	}

	public double getPl() {
		return pl;
	}

	public void setPl(double pl) {
		this.pl = pl;
	}

	public double getMov() {
		return mov;
	}

	public void setMov(double mov) {
		this.mov = mov;
	}

	public double getSos() {
		return sos;
	}

	public void setSos(double sos) {
		this.sos = sos;
	}

	public double getSrs() {
		return srs;
	}

	public void setSrs(double srs) {
		this.srs = srs;
	}

	public double getOff_rtg() {
		return off_rtg;
	}

	public void setOff_rtg(double off_rtg) {
		this.off_rtg = off_rtg;
	}

	public double getDef_rtg() {
		return def_rtg;
	}

	public void setDef_rtg(double def_rtg) {
		this.def_rtg = def_rtg;
	}

	public double getPace() {
		return pace;
	}

	public void setPace(double pace) {
		this.pace = pace;
	}

	public double getFta_per_fga_pct() {
		return fta_per_fga_pct;
	}

	public void setFta_per_fga_pct(double fta_per_fga_pct) {
		this.fta_per_fga_pct = fta_per_fga_pct;
	}

	public double getFg3a_per_fga_pct() {
		return fg3a_per_fga_pct;
	}

	public void setFg3a_per_fga_pct(double fg3a_per_fga_pct) {
		this.fg3a_per_fga_pct = fg3a_per_fga_pct;
	}

	public double getOff_efg_pct() {
		return off_efg_pct;
	}

	public void setOff_efg_pct(double off_efg_pct) {
		this.off_efg_pct = off_efg_pct;
	}

	public double getOff_tov_pct() {
		return off_tov_pct;
	}

	public void setOff_tov_pct(double off_tov_pct) {
		this.off_tov_pct = off_tov_pct;
	}

	public double getOrb_pct() {
		return orb_pct;
	}

	public void setOrb_pct(double orb_pct) {
		this.orb_pct = orb_pct;
	}

	public double getOff_ft_rate() {
		return off_ft_rate;
	}

	public void setOff_ft_rate(double off_ft_rate) {
		this.off_ft_rate = off_ft_rate;
	}

	public double getOpp_efg_pct() {
		return opp_efg_pct;
	}

	public void setOpp_efg_pct(double opp_efg_pct) {
		this.opp_efg_pct = opp_efg_pct;
	}

	public double getOpp_tov_pct() {
		return opp_tov_pct;
	}

	public void setOpp_tov_pct(double opp_tov_pct) {
		this.opp_tov_pct = opp_tov_pct;
	}

	public double getDrb_pct() {
		return drb_pct;
	}

	public void setDrb_pct(double drb_pct) {
		this.drb_pct = drb_pct;
	}

	public double getOpp_ft_rate() {
		return opp_ft_rate;
	}

	public void setOpp_ft_rate(double opp_ft_rate) {
		this.opp_ft_rate = opp_ft_rate;
	}

	public String getArena() {
		return arena;
	}

	public void setArena(String arena) {
		this.arena = arena;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	
}
