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
	private double minute;
	
	/**
	 * 投篮命中数
	 */
	private double fg;
	
	/**
	 * 投篮出手数
	 */
	private double fga;
	
	/**
	 * 投篮命中率
	 */
	private double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	private double fg3;
	
	/**
	 * 三分出手数
	 */
	private double fg3a;
	
	/**
	 * 三分命中率
	 */
	private double fg3_pct;
	
	/**
	 * 两分命中数
	 */
	private double fg2;
	
	/**
	 * 两分投篮数
	 */
	private double fg2a;
	
	/**
	 * 两分命中率
	 */
	private double fg2_pct;
	
	/**
	 * 罚球命中数
	 */
	private double ft;
	
	/**
	 * 罚球出手数
	 */
	private double fta;
	
	/**
	 * 罚球命中率
	 */
	private double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	private double orb;
	
	/**
	 * 防守篮板
	 */
	private double drb;
	
	/**
	 * 总篮板
	 */
	private double trb;
	
	/**
	 * 助攻
	 */
	private double ast;
	
	/**
	 * 抢断
	 */
	private double stl;
	
	/**
	 * 盖帽
	 */
	private double blk;
	
	/**
	 * 失误
	 */
	private double tov;
	
	/**
	 * 犯规
	 */
	private double pf;
	
	/**
	 * 得分
	 */
	private double pts;

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

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}

	public double getFg() {
		return fg;
	}

	public void setFg(double fg) {
		this.fg = fg;
	}

	public double getFga() {
		return fga;
	}

	public void setFga(double fga) {
		this.fga = fga;
	}

	public double getFga_pct() {
		return fga_pct;
	}

	public void setFga_pct(double fga_pct) {
		this.fga_pct = fga_pct;
	}

	public double getFg3() {
		return fg3;
	}

	public void setFg3(double fg3) {
		this.fg3 = fg3;
	}

	public double getFg3a() {
		return fg3a;
	}

	public void setFg3a(double fg3a) {
		this.fg3a = fg3a;
	}

	public double getFg3_pct() {
		return fg3_pct;
	}

	public void setFg3_pct(double fg3_pct) {
		this.fg3_pct = fg3_pct;
	}

	public double getFg2() {
		return fg2;
	}

	public void setFg2(double fg2) {
		this.fg2 = fg2;
	}

	public double getFg2a() {
		return fg2a;
	}

	public void setFg2a(double fg2a) {
		this.fg2a = fg2a;
	}

	public double getFg2_pct() {
		return fg2_pct;
	}

	public void setFg2_pct(double fg2_pct) {
		this.fg2_pct = fg2_pct;
	}

	public double getFt() {
		return ft;
	}

	public void setFt(double ft) {
		this.ft = ft;
	}

	public double getFta() {
		return fta;
	}

	public void setFta(double fta) {
		this.fta = fta;
	}

	public double getFt_pct() {
		return ft_pct;
	}

	public void setFt_pct(double ft_pct) {
		this.ft_pct = ft_pct;
	}

	public double getOrb() {
		return orb;
	}

	public void setOrb(double orb) {
		this.orb = orb;
	}

	public double getDrb() {
		return drb;
	}

	public void setDrb(double drb) {
		this.drb = drb;
	}

	public double getTrb() {
		return trb;
	}

	public void setTrb(double trb) {
		this.trb = trb;
	}

	public double getAst() {
		return ast;
	}

	public void setAst(double ast) {
		this.ast = ast;
	}

	public double getStl() {
		return stl;
	}

	public void setStl(double stl) {
		this.stl = stl;
	}

	public double getBlk() {
		return blk;
	}

	public void setBlk(double blk) {
		this.blk = blk;
	}

	public double getTov() {
		return tov;
	}

	public void setTov(double tov) {
		this.tov = tov;
	}

	public double getPf() {
		return pf;
	}

	public void setPf(double pf) {
		this.pf = pf;
	}

	public double getPts() {
		return pts;
	}

	public void setPts(double pts) {
		this.pts = pts;
	}
	
}
