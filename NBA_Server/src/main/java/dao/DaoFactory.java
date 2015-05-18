package dao;

/**
 * Dao抽象工厂
 * 
 * created by JaneLDQ on 2015年5月17日 下午10:54:45
 */
public interface DaoFactory {

	public SeasonDao getSeasonDao();

	public MatchDao getMatchDao();

	public RawMatchDao getRawMatchDao();

}
