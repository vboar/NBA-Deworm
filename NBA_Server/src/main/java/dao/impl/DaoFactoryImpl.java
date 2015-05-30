package dao.impl;

import dao.DaoFactory;
import dao.MatchDao;
import dao.PlayerDao;
import dao.RawMatchDao;
import dao.RawPlayerDao;
import dao.RawTeamDao;
import dao.SeasonDao;
import dao.TeamDao;

/**
 * Dao抽象工厂具体实现
 * 
 * created by JaneLDQ on 2015年5月17日 下午10:55:29
 */
public class DaoFactoryImpl implements DaoFactory{

    /**
     * 单例模式
     */
    private static DaoFactoryImpl daoFactory;

    private DaoFactoryImpl() {}

    public static DaoFactory getDaoFactory() {
        if (daoFactory != null) {
            return daoFactory;
        }
        return new DaoFactoryImpl();
    }
	
	@Override
	public SeasonDao getSeasonDao() {
		return new SeasonDaoImpl();
	}

	@Override
	public MatchDao getMatchDao() {
		return new MatchDaoImpl();
	}

	@Override
	public RawMatchDao getRawMatchDao() {
		return new RawMatchDaoImpl();
	}

	@Override
	public PlayerDao getPlayerDao() {
		return new PlayerDaoImpl();
	}

	@Override
	public RawPlayerDao getRawPlayerDao() {
		return new RawPlayerDaoImpl();
	}

	@Override
	public TeamDao getTeamDao() {
		return new TeamDaoImpl();
	}

	@Override
	public RawTeamDao getRawTeamDao() {
		return new RawTeamDaoImpl();
	}

}
