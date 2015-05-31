package entity;

/**
 * 球员赛季场均数据类
 * 
 * created by JaneLDQ on 2015年5月17日 下午1:36:38
 */
public class PlayerStatsPerGame {

	/**
	 * 球员姓名
	 */
	private String name;
	
	/**
	 * 赛季
	 */
	private String season;
	
	/**
	 * 常规赛/季后赛 1 = normal, 0 = playeroff
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
	 * 首发场数
	 */
	private Integer game_started;
	
	/**
	 * 在场时间
	 */
	private Double minute;
	
	/**
	 * 投篮命中数
	 */
	private Double fg;
	
	/**
	 * 投篮出手数
	 */
	private Double fga;
	
	/**
	 * 投篮命中率
	 */
	private Double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	private Double fg3;
	
	/**
	 * 三分出手数
	 */
	private Double fg3a;
	
	/**
	 * 三分命中率
	 */
	private Double fg3_pct;
	
	/**
	 * 两分命中数
	 */
	private Double fg2;
	
	/**
	 * 两分投篮数
	 */
	private Double fg2a;
	
	/**
	 * 两分命中率
	 */
	private Double fg2_pct;
	
	/**
	 * effectivce field goal percentage
	 */
	private Double efg_pct;
	
	/**
	 * 罚球命中数
	 */
	private Double ft;
	
	/**
	 * 罚球出手数
	 */
	private Double fta;
	
	/**
	 * 罚球命中率
	 */
	private Double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	private Double orb;
	
	/**
	 * 防守篮板
	 */
	private Double drb;
	
	/**
	 * 总篮板
	 */
	private Double trb;
	
	/**
	 * 助攻
	 */
	private Double ast;
	
	/**
	 * 抢断
	 */
	private Double stl;
	
	/**
	 * 盖帽
	 */
	private Double blk;
	
	/**
	 * 失误
	 */
	private Double tov;
	
	/**
	 * 犯规
	 */
	private Double pf;
	
	/**
	 * 个人得分
	 */
	private Double pts;
	
	/**
	 * 无参构造函数
	 */
	public PlayerStatsPerGame(){}

	public Integer getIs_normal() {
		return is_normal;
	}

	public void setIs_normal(Integer is_normal) {
		this.is_normal = is_normal;
	}

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

	public Integer getGame_started() {
		return game_started;
	}

	public void setGame_started(Integer game_started) {
		this.game_started = game_started;
	}

	public Double getMinute() {
		return minute;
	}

	public void setMinute(Double minute) {
		this.minute = minute;
	}

	public Double getFg() {
		return fg;
	}

	public void setFg(Double fg) {
		this.fg = fg;
	}

	public Double getFga() {
		return fga;
	}

	public void setFga(Double fga) {
		this.fga = fga;
	}

	public Double getFga_pct() {
		return fga_pct;
	}

	public void setFga_pct(Double fga_pct) {
		this.fga_pct = fga_pct;
	}

	public Double getFg3() {
		return fg3;
	}

	public void setFg3(Double fg3) {
		this.fg3 = fg3;
	}

	public Double getFg3a() {
		return fg3a;
	}

	public void setFg3a(Double fg3a) {
		this.fg3a = fg3a;
	}

	public Double getFg3_pct() {
		return fg3_pct;
	}

	public void setFg3_pct(Double fg3_pct) {
		this.fg3_pct = fg3_pct;
	}

	public Double getFg2() {
		return fg2;
	}

	public void setFg2(Double fg2) {
		this.fg2 = fg2;
	}

	public Double getFg2a() {
		return fg2a;
	}

	public void setFg2a(Double fg2a) {
		this.fg2a = fg2a;
	}

	public Double getFg2_pct() {
		return fg2_pct;
	}

	public void setFg2_pct(Double fg2_pct) {
		this.fg2_pct = fg2_pct;
	}

	public Double getEfg_pct() {
		return efg_pct;
	}

	public void setEfg_pct(Double efg_pct) {
		this.efg_pct = efg_pct;
	}

	public Double getFt() {
		return ft;
	}

	public void setFt(Double ft) {
		this.ft = ft;
	}

	public Double getFta() {
		return fta;
	}

	public void setFta(Double fta) {
		this.fta = fta;
	}

	public Double getFt_pct() {
		return ft_pct;
	}

	public void setFt_pct(Double ft_pct) {
		this.ft_pct = ft_pct;
	}

	public Double getOrb() {
		return orb;
	}

	public void setOrb(Double orb) {
		this.orb = orb;
	}

	public Double getDrb() {
		return drb;
	}

	public void setDrb(Double drb) {
		this.drb = drb;
	}

	public Double getTrb() {
		return trb;
	}

	public void setTrb(Double trb) {
		this.trb = trb;
	}

	public Double getAst() {
		return ast;
	}

	public void setAst(Double ast) {
		this.ast = ast;
	}

	public Double getStl() {
		return stl;
	}

	public void setStl(Double stl) {
		this.stl = stl;
	}

	public Double getBlk() {
		return blk;
	}

	public void setBlk(Double blk) {
		this.blk = blk;
	}

	public Double getTov() {
		return tov;
	}

	public void setTov(Double tov) {
		this.tov = tov;
	}

	public Double getPf() {
		return pf;
	}

	public void setPf(Double pf) {
		this.pf = pf;
	}

	public Double getPts() {
		return pts;
	}

	public void setPts(Double pts) {
		this.pts = pts;
	}
	
}
