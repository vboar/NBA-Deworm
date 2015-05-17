package entity;

/**
 * 球员赛季高阶数据
 * 
 * created by JaneLDQ on 2015年5月17日 下午1:39:15
 */
public class PlayerStatsAdvanced {
	/**
	 * 球员姓名
	 */
	private String name;
	
	/**
	 * 赛季
	 */
	private String season;
	
	/**
	 * 常规赛/季后赛
	 */
	private int is_normal;
	
	/**
	 * 所属球队（缩写）
	 */
	private String team;
	
	/**
	 * 位置
	 */
	private String position;
	
	/**
	 * 比赛场数
	 */
	private int game;
	
	/**
	 * 在场时间
	 */
	private int minute;
	
	/**
	 * player efficiency rating
	 */
	private double per;
	/**
	 * true shooting percentage
	 */
	private double ts_pct;
	
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
	 * 盖帽率
	 */
	private double blk_pct;
	
	/**
	 * 失误率
	 */
	private double tov_pct;
	
	/**
	 * 使用率
	 */
	private double usg_pct;
	
	/**
	 * offensive win shares
	 */
	private double ows;
	
	/**
	 * defensive win shares
	 */
	private double dws;
	
	/**
	 * win shares
	 */
	private double ws;
	
	/**
	 * win shares per 48 mins;
	 */
	private double ws_48;
	
	/**
	 * offensive box plus/minus
	 */
	private double obpm;
	
	/**
	 * defensive box plus/minus
	 */
	private double dbpm;
	
	/**
	 * box plus/minus
	 */
	private double bpm;
	
	/**
	 * value over replacement player
	 */
	private double vorp;
	
	/**
	 * 无参构造函数
	 */
	public PlayerStatsAdvanced() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getGame() {
		return game;
	}

	public void setGame(int game) {
		this.game = game;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public double getPer() {
		return per;
	}

	public void setPer(double per) {
		this.per = per;
	}

	public double getTs_pct() {
		return ts_pct;
	}

	public void setTs_pct(double ts_pct) {
		this.ts_pct = ts_pct;
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

	public double getBlk_pct() {
		return blk_pct;
	}

	public void setBlk_pct(double blk_pct) {
		this.blk_pct = blk_pct;
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

	public double getOws() {
		return ows;
	}

	public void setOws(double ows) {
		this.ows = ows;
	}

	public double getDws() {
		return dws;
	}

	public void setDws(double dws) {
		this.dws = dws;
	}

	public double getWs() {
		return ws;
	}

	public void setWs(double ws) {
		this.ws = ws;
	}

	public double getWs_48() {
		return ws_48;
	}

	public void setWs_48(double ws_48) {
		this.ws_48 = ws_48;
	}

	public double getObpm() {
		return obpm;
	}

	public void setObpm(double obpm) {
		this.obpm = obpm;
	}

	public double getDbpm() {
		return dbpm;
	}

	public void setDbpm(double dbpm) {
		this.dbpm = dbpm;
	}

	public double getBpm() {
		return bpm;
	}

	public void setBpm(double bpm) {
		this.bpm = bpm;
	}

	public double getVorp() {
		return vorp;
	}

	public void setVorp(double vorp) {
		this.vorp = vorp;
	}

	public int getIs_normal() {
		return is_normal;
	}

	public void setIs_normal(int is_normal) {
		this.is_normal = is_normal;
	}
	
}
