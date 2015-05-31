package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import service.CommonService;
import dao.SeasonDao;
import dao.impl.DaoFactoryImpl;

public class CommonServiceImpl extends UnicastRemoteObject implements CommonService {

	private static final long serialVersionUID = 1L;
	
	private SeasonDao sdao;
	
	public CommonServiceImpl() throws RemoteException{
		super();
		sdao = DaoFactoryImpl.getDaoFactory().getSeasonDao();
	}

	@Override
	public List<String> getAllSeason() throws RemoteException {
		return sdao.getAllSeason();
	}
	
}
