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
	 * 常规赛/季后赛  1 = normal, 0 = playeroff
	 */
	private Integer is_normal;
	
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
	private Integer game;
	
	/**
	 * 在场时间
	 */
	private Integer minute;
	
	/**
	 * player efficiency rating
	 */
	private Double per;
	/**
	 * true shooting percentage
	 */
	private Double ts_pct;
	
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
	 * 盖帽率
	 */
	private Double blk_pct;
	
	/**
	 * 失误率
	 */
	private Double tov_pct;
	
	/**
	 * 使用率
	 */
	private Double usg_pct;
	
	/**
	 * offensive win shares
	 */
	private Double ows;
	
	/**
	 * defensive win shares
	 */
	private Double dws;
	
	/**
	 * win shares
	 */
	private Double ws;
	
	/**
	 * win shares per 48 mins;
	 */
	private Double ws_48;
	
	/**
	 * offensive box plus/minus
	 */
	private Double obpm;
	
	/**
	 * defensive box plus/minus
	 */
	private Double dbpm;
	
	/**
	 * box plus/minus
	 */
	private Double bpm;
	
	/**
	 * value over replacement player
	 */
	private Double vorp;
	
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

	public Integer getGame() {
		return game;
	}

	public void setGame(Integer game) {
		this.game = game;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Double getPer() {
		return per;
	}

	public void setPer(Double per) {
		this.per = per;
	}

	public Double getTs_pct() {
		return ts_pct;
	}

	public void setTs_pct(Double ts_pct) {
		this.ts_pct = ts_pct;
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

	public Double getBlk_pct() {
		return blk_pct;
	}

	public void setBlk_pct(Double blk_pct) {
		this.blk_pct = blk_pct;
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

	public Double getOws() {
		return ows;
	}

	public void setOws(Double ows) {
		this.ows = ows;
	}

	public Double getDws() {
		return dws;
	}

	public void setDws(Double dws) {
		this.dws = dws;
	}

	public Double getWs() {
		return ws;
	}

	public void setWs(Double ws) {
		this.ws = ws;
	}

	public Double getWs_48() {
		return ws_48;
	}

	public void setWs_48(Double ws_48) {
		this.ws_48 = ws_48;
	}

	public Double getObpm() {
		return obpm;
	}

	public void setObpm(Double obpm) {
		this.obpm = obpm;
	}

	public Double getDbpm() {
		return dbpm;
	}

	public void setDbpm(Double dbpm) {
		this.dbpm = dbpm;
	}

	public Double getBpm() {
		return bpm;
	}

	public void setBpm(Double bpm) {
		this.bpm = bpm;
	}

	public Double getVorp() {
		return vorp;
	}

	public void setVorp(Double vorp) {
		this.vorp = vorp;
	}

	public Integer getIs_normal() {
		return is_normal;
	}

	public void setIs_normal(Integer is_normal) {
		this.is_normal = is_normal;
	}
	
}
