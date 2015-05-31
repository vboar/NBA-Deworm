package entity;

/**
 * 球队赛季场均数据
 * 
 * created by JaneLDQ on 2015年5月17日 下午2:24:55
 */
public class TeamStatsPerGame {

	/**
	 * 球队（缩写）
	 */
	private String abbr;
	
	/**
	 * 赛季
	 */
	private String season;
	
	/**
	 * 时间
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
	 * 得分
	 */
	private Double pts;

	/**
	 * 无参构造函数
	 */
	public TeamStatsPerGame() {}

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
