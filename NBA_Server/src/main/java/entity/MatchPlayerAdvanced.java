package entity;

/**
 * 单场比赛高阶球员数据
 * 
 * created by JaneLDQ on 2015年5月17日 下午1:00:08
 */
public class MatchPlayerAdvanced {

	/**
	 * 比赛编号
	 */
	private String game_id;
	
	/**
	 * 球员名
	 */
	private String player_name;
	
	/**
	 * 球队缩写
	 */
	private  String team_abbr;
	
	/**
	 * 首发
	 */
	private boolean starter;
	
	/**
	 * 上场时间
	 */
	private double minute;
	
	/**
	 * true shooting percentage
	 */
	private double ts_pct;
	
	/**
	 * effective field goal percentage
	 */
	private double efg_pct;
	
	/**
	 * 3-point attempt rate
	 */
	private double fa3a_per_fga_pct;
	
	/**
	 * free throw attempt rate
	 */
	private double fta_per_fga_pct;
	
	/**
	 * 进攻篮板率
	 */
	private double orb_pct;
	
	/**
	 * 防守篮板率
	 */
	private double drb_pct;
	
	/**
	 * 总篮板率
	 */
	private double trb_pct;
	
	/**
	 * 助攻率
	 */
	private double ast_pct;
	
	/**
	 * 抢断率
	 */
	private double stl_pct;
	
	/**
	 * 失误率
	 */
	private double tov_pct;
	
	/**
	 * 盖帽率
	 */
	private double blk_pct;
	
	/**
	 * 使用率
	 */
	private double usg_pct;
	
	/**
	 * 进攻效率
	 */
	private double off_rtg;
	
	/**
	 * 防守效率
	 */
	private double def_rtg;
	
	/**
	 * 无参构造函数
	 */
	public MatchPlayerAdvanced(){}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public String getTeam_abbr() {
		return team_abbr;
	}

	public void setTeam_abbr(String team_abbr) {
		this.team_abbr = team_abbr;
	}

	public boolean isStarter() {
		return starter;
	}

	public void setStarter(boolean starter) {
		this.starter = starter;
	}

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}

	public double getTs_pct() {
		return ts_pct;
	}

	public void setTs_pct(double ts_pct) {
		this.ts_pct = ts_pct;
	}

	public double getEfg_pct() {
		return efg_pct;
	}

	public void setEfg_pct(double efg_pct) {
		this.efg_pct = efg_pct;
	}

	public double getFa3a_per_fga_pct() {
		return fa3a_per_fga_pct;
	}

	public void setFa3a_per_fga_pct(double fa3a_per_fga_pct) {
		this.fa3a_per_fga_pct = fa3a_per_fga_pct;
	}

	public double getFta_per_fga_pct() {
		return fta_per_fga_pct;
	}

	public void setFta_per_fga_pct(double fta_per_fga_pct) {
		this.fta_per_fga_pct = fta_per_fga_pct;
	}

	public double getOrb_pct() {
		return orb_pct;
	}

	public void setOrb_pct(double orb_pct) {
		this.orb_pct = orb_pct;
	}

	public double getDrb_pct() {
		return drb_pct;
	}

	public void setDrb_pct(double drb_pct) {
		this.drb_pct = drb_pct;
	}

	public double getTrb_pct() {
		return trb_pct;
	}

	public void setTrb_pct(double trb_pct) {
		this.trb_pct = trb_pct;
	}

	public double getAst_pct() {
		return ast_pct;
	}

	public void setAst_pct(double ast_pct) {
		this.ast_pct = ast_pct;
	}

	public double getStl_pct() {
		return stl_pct;
	}

	public void setStl_pct(double stl_pct) {
		this.stl_pct = stl_pct;
	}

	public double getTov_pct() {
		return tov_pct;
	}

	public void setTov_pct(double tov_pct) {
		this.tov_pct = tov_pct;
	}

	public double getUsg_pct() {
		return usg_pct;
	}

	public void setUsg_pct(double usg_pct) {
		this.usg_pct = usg_pct;
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

	public double getBlk_pct() {
		return blk_pct;
	}

	public void setBlk_pct(double blk_pct) {
		this.blk_pct = blk_pct;
	}
	
}
