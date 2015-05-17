package dao.impl;

import dao.DaoFactory;
import dao.SeasonDao;

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

}
