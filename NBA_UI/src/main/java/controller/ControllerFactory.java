package controller;

import service.CommonService;
import service.LiveService;
import service.MatchService;
import service.PlayerService;
import service.TeamService;

public interface ControllerFactory {
	
	public CommonService getCommonController();
	
	public LiveService getLiveController();
	
	public MatchService getMatchController();
	
	public PlayerService getPlayerController();
	
	public TeamService getTeamController();
}
