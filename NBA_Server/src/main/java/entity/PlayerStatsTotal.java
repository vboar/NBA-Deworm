package entity;

/**
 * 球员赛季总数据类
 * 
 * created by JaneLDQ on 2015年5月17日 下午1:22:33
 */
public class PlayerStatsTotal {

	/**
	 * 球员姓名
	 */
	private String name;
	
	/**
	 * 赛季
	 */
	private String season;
	
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
	 * 首发场数
	 */
	private int game_started;
	
	/**
	 * 在场时间
	 */
	private int minute;
	
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
	 * 两分命中数
	 */
	private int fg2;
	
	/**
	 * 两分投篮数
	 */
	private int fg2a;
	
	/**
	 * 两分命中率
	 */
	private double fg2_pct;
	
	/**
	 * effectivce field goal percentage
	 */
	private double efg_pct;
	
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
	 * 无参构造函数
	 */
	public PlayerStatsTotal(){}

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

	public int getGame_started() {
		return game_started;
	}

	public void setGame_started(int game_started) {
		this.game_started = game_started;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
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

	public int getFg2() {
		return fg2;
	}

	public void setFg2(int fg2) {
		this.fg2 = fg2;
	}

	public int getFg2a() {
		return fg2a;
	}

	public void setFg2a(int fg2a) {
		this.fg2a = fg2a;
	}

	public double getFg2_pct() {
		return fg2_pct;
	}

	public void setFg2_pct(double fg2_pct) {
		this.fg2_pct = fg2_pct;
	}

	public double getEfg_pct() {
		return efg_pct;
	}

	public void setEfg_pct(double efg_pct) {
		this.efg_pct = efg_pct;
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
	
	
}
