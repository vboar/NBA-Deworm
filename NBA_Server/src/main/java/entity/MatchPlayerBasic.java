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
	 * 首发/球队
	 */
	private String starter;
	
	/**
	 * 上场时间
	 */
	private double minute;
	
	/**
	 * 投篮命中数
	 */
	private int fg;
	
	/**
	 * 投篮出手数
	 */
	private int fga;
	
	/**
	 * 投篮命中率
	 */
	private double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	private int fg3;
	
	/**
	 * 三分出手数
	 */
	private int fg3a;
	
	/**
	 * 三分命中率
	 */
	private double fg3_pct;
	
	/**
	 * 罚球命中数
	 */
	private int ft;
	
	/**
	 * 罚球出手数
	 */
	private int fta;
	
	/**
	 * 罚球命中率
	 */
	private double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	private int orb;
	
	/**
	 * 防守篮板
	 */
	private int drb;
	
	/**
	 * 总篮板
	 */
	private int trb;
	
	/**
	 * 助攻
	 */
	private int ast;
	
	/**
	 * 抢断
	 */
	private int stl;
	
	/**
	 * 盖帽
	 */
	private int blk;
	
	/**
	 * 失误
	 */
	private int tov;
	
	/**
	 * 犯规
	 */
	private int pf;
	
	/**
	 * 个人得分
	 */
	private int pts;
	
	/**
	 * +/-
	 */
	private double plus_minus;
	
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

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}

	public int getFg() {
		return fg;
	}

	public void setFg(int fg) {
		this.fg = fg;
	}

	public int getFga() {
		return fga;
	}

	public void setFga(int fga) {
		this.fga = fga;
	}

	public double getFga_pct() {
		return fga_pct;
	}

	public void setFga_pct(double fga_pct) {
		this.fga_pct = fga_pct;
	}

	public int getFg3() {
		return fg3;
	}

	public void setFg3(int fg3) {
		this.fg3 = fg3;
	}

	public int getFg3a() {
		return fg3a;
	}

	public void setFg3a(int fg3a) {
		this.fg3a = fg3a;
	}

	public double getFg3_pct() {
		return fg3_pct;
	}

	public void setFg3_pct(double fg3_pct) {
		this.fg3_pct = fg3_pct;
	}

	public int getFt() {
		return ft;
	}

	public void setFt(int ft) {
		this.ft = ft;
	}

	public int getFta() {
		return fta;
	}

	public void setFta(int fta) {
		this.fta = fta;
	}

	public double getFt_pct() {
		return ft_pct;
	}

	public void setFt_pct(double ft_pct) {
		this.ft_pct = ft_pct;
	}

	public int getOrb() {
		return orb;
	}

	public void setOrb(int orb) {
		this.orb = orb;
	}

	public int getDrb() {
		return drb;
	}

	public void setDrb(int drb) {
		this.drb = drb;
	}

	public int getTrb() {
		return trb;
	}

	public void setTrb(int trb) {
		this.trb = trb;
	}

	public int getAst() {
		return ast;
	}

	public void setAst(int ast) {
		this.ast = ast;
	}

	public int getStl() {
		return stl;
	}

	public void setStl(int stl) {
		this.stl = stl;
	}

	public int getBlk() {
		return blk;
	}

	public void setBlk(int blk) {
		this.blk = blk;
	}

	public int getTov() {
		return tov;
	}

	public void setTov(int tov) {
		this.tov = tov;
	}

	public int getPf() {
		return pf;
	}

	public void setPf(int pf) {
		this.pf = pf;
	}

	public int getPts() {
		return pts;
	}

	public void setPts(int pts) {
		this.pts = pts;
	}

	public double getPlus_minus() {
		return plus_minus;
	}

	public void setPlus_minus(double plus_minus) {
		this.plus_minus = plus_minus;
	}
	
}

