package entity;

/**
 * 单场比赛基本球员数据类
 * 
 * created by JaneLDQ on 2015年5月17日 下午12:53:34
 */
public class MatchPlayerBasic {

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
	 * 首发/球队(TeamTotal球队总数据，Starter首发球员，Reserve是非首发上场，DidNotPlayer冷板凳)
	 */
	private String starter;
	
	/**
	 * 上场时间
	 */
	private Double minute;
	
	/**
	 * 投篮命中数
	 */
	private Integer fg;
	
	/**
	 * 投篮出手数
	 */
	private Integer fga;
	
	/**
	 * 投篮命中率
	 */
	private Double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	private Integer fg3;
	
	/**
	 * 三分出手数
	 */
	private Integer fg3a;
	
	/**
	 * 三分命中率
	 */
	private Double fg3_pct;
	
	/**
	 * 罚球命中数
	 */
	private Integer ft;
	
	/**
	 * 罚球出手数
	 */
	private Integer fta;
	
	/**
	 * 罚球命中率
	 */
	private Double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	private Integer orb;
	
	/**
	 * 防守篮板
	 */
	private Integer drb;
	
	/**
	 * 总篮板
	 */
	private Integer trb;
	
	/**
	 * 助攻
	 */
	private Integer ast;
	
	/**
	 * 抢断
	 */
	private Integer stl;
	
	/**
	 * 盖帽
	 */
	private Integer blk;
	
	/**
	 * 失误
	 */
	private Integer tov;
	
	/**
	 * 犯规
	 */
	private Integer pf;
	
	/**
	 * 个人得分
	 */
	private Integer pts;
	
	/**
	 * +/-
	 */
	private Double plus_minus;
	
	/**
	 * 无参构造函数
	 */
	public MatchPlayerBasic(){}

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

	public Integer getFg() {
		return fg;
	}

	public void setFg(Integer fg) {
		this.fg = fg;
	}

	public Integer getFga() {
		return fga;
	}

	public void setFga(Integer fga) {
		this.fga = fga;
	}

	public Double getFga_pct() {
		return fga_pct;
	}

	public void setFga_pct(Double fga_pct) {
		this.fga_pct = fga_pct;
	}

	public Integer getFg3() {
		return fg3;
	}

	public void setFg3(Integer fg3) {
		this.fg3 = fg3;
	}

	public Integer getFg3a() {
		return fg3a;
	}

	public void setFg3a(Integer fg3a) {
		this.fg3a = fg3a;
	}

	public Double getFg3_pct() {
		return fg3_pct;
	}

	public void setFg3_pct(Double fg3_pct) {
		this.fg3_pct = fg3_pct;
	}

	public Integer getFt() {
		return ft;
	}

	public void setFt(Integer ft) {
		this.ft = ft;
	}

	public Integer getFta() {
		return fta;
	}

	public void setFta(Integer fta) {
		this.fta = fta;
	}

	public Double getFt_pct() {
		return ft_pct;
	}

	public void setFt_pct(Double ft_pct) {
		this.ft_pct = ft_pct;
	}

	public Integer getOrb() {
		return orb;
	}

	public void setOrb(Integer orb) {
		this.orb = orb;
	}

	public Integer getDrb() {
		return drb;
	}

	public void setDrb(Integer drb) {
		this.drb = drb;
	}

	public Integer getTrb() {
		return trb;
	}

	public void setTrb(Integer trb) {
		this.trb = trb;
	}

	public Integer getAst() {
		return ast;
	}

	public void setAst(Integer ast) {
		this.ast = ast;
	}

	public Integer getStl() {
		return stl;
	}

	public void setStl(Integer stl) {
		this.stl = stl;
	}

	public Integer getBlk() {
		return blk;
	}

	public void setBlk(Integer blk) {
		this.blk = blk;
	}

	public Integer getTov() {
		return tov;
	}

	public void setTov(Integer tov) {
		this.tov = tov;
	}

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public Integer getPts() {
		return pts;
	}

	public void setPts(Integer pts) {
		this.pts = pts;
	}

	public Double getPlus_minus() {
		return plus_minus;
	}

	public void setPlus_minus(Double plus_minus) {
		this.plus_minus = plus_minus;
	}
	
}

