package ui.controller;

import service.LiveService;
import service.impl.ServiceFactoryImpl;



public class LiveController {

	LiveService ls = ServiceFactoryImpl.getInstance().getLiveService();
	

}
