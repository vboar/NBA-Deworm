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
	 * 赛季
	 */
	private String season;

	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 球员名
	 */
	private String player_name;
	
	/**
	 * 球队缩写
	 */
	private  String team_abbr;
	
	/**
	 * 首发(Team Total球队总数据，Starter首发球员，Reserve是非首发上场，DidNotPlayer冷板凳)
	 */
	private String starter;
	
	/**
	 * 上场时间
	 */
	private Double minute;
	
	/**
	 * true shooting percentage
	 */
	private Double ts_pct;
	
	/**
	 * effective field goal percentage
	 */
	private Double efg_pct;
	
	/**
	 * 3-point attempt rate
	 */
	private Double fa3a_per_fga_pct;
	
	/**
	 * free throw attempt rate
	 */
	private Double fta_per_fga_pct;
	
	/**
	 * 进攻篮板率
	 */
	private Double orb_pct;
	
	/**
	 * 防守篮板率
	 */
	private Double drb_pct;
	
	/**
	 * 总篮板率
	 */
	private Double trb_pct;
	
	/**
	 * 助攻率
	 */
	private Double ast_pct;
	
	/**
	 * 抢断率
	 */
	private Double stl_pct;
	
	/**
	 * 失误率
	 */
	private Double tov_pct;
	
	/**
	 * 盖帽率
	 */
	private Double blk_pct;
	
	/**
	 * 使用率
	 */
	private Double usg_pct;
	
	/**
	 * 进攻效率
	 */
	private Double off_rtg;
	
	/**
	 * 防守效率
	 */
	private Double def_rtg;
	
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

	public String getStarter() {
		return starter;
	}

	public void setStarter(String starter) {
		this.starter = starter;
	}

	public Double getMinute() {
		return minute;
	}

	public void setMinute(Double minute) {
		this.minute = minute;
	}

	public Double getTs_pct() {
		return ts_pct;
	}

	public void setTs_pct(Double ts_pct) {
		this.ts_pct = ts_pct;
	}

	public Double getEfg_pct() {
		return efg_pct;
	}

	public void setEfg_pct(Double efg_pct) {
		this.efg_pct = efg_pct;
	}

	public Double getFa3a_per_fga_pct() {
		return fa3a_per_fga_pct;
	}

	public void setFa3a_per_fga_pct(Double fa3a_per_fga_pct) {
		this.fa3a_per_fga_pct = fa3a_per_fga_pct;
	}

	public Double getFta_per_fga_pct() {
		return fta_per_fga_pct;
	}

	public void setFta_per_fga_pct(Double fta_per_fga_pct) {
		this.fta_per_fga_pct = fta_per_fga_pct;
	}

	public Double getOrb_pct() {
		return orb_pct;
	}

	public void setOrb_pct(Double orb_pct) {
		this.orb_pct = orb_pct;
	}

	public Double getDrb_pct() {
		return drb_pct;
	}

	public void setDrb_pct(Double drb_pct) {
		this.drb_pct = drb_pct;
	}

	public Double getTrb_pct() {
		return trb_pct;
	}

	public void setTrb_pct(Double trb_pct) {
		this.trb_pct = trb_pct;
	}

	public Double getAst_pct() {
		return ast_pct;
	}

	public void setAst_pct(Double ast_pct) {
		this.ast_pct = ast_pct;
	}

	public Double getStl_pct() {
		return stl_pct;
	}

	public void setStl_pct(Double stl_pct) {
		this.stl_pct = stl_pct;
	}

	public Double getTov_pct() {
		return tov_pct;
	}

	public void setTov_pct(Double tov_pct) {
		this.tov_pct = tov_pct;
	}

	public Double getUsg_pct() {
		return usg_pct;
	}

	public void setUsg_pct(Double usg_pct) {
		this.usg_pct = usg_pct;
	}

	public Double getOff_rtg() {
		return off_rtg;
	}

	public void setOff_rtg(Double off_rtg) {
		this.off_rtg = off_rtg;
	}

	public Double getDef_rtg() {
		return def_rtg;
	}

	public void setDef_rtg(Double def_rtg) {
		this.def_rtg = def_rtg;
	}

	public Double getBlk_pct() {
		return blk_pct;
	}

	public void setBlk_pct(Double blk_pct) {
		this.blk_pct = blk_pct;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
