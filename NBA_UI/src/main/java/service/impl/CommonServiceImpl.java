package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import service.CommonService;


public class CommonServiceImpl extends UnicastRemoteObject implements CommonService {

	private static final long serialVersionUID = 1L;
	
	public CommonServiceImpl() throws RemoteException{
		super();
	}

	public List<String> getAllSeason() throws RemoteException {
		return null;
	}
	
}
