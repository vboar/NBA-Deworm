package service;

import vo.TeamWinAnalysisVO;

/**
 * 推断统计调用接口
 * 
 * created by JaneLDQ on 2015年6月14日 下午3:38:47
 */
public interface InferenceStatsService {

	/**
	 * 获得30支球队某赛季的pts,ast,blk,stl,trb,tov,pf的主客场分别累计值
	 * 存储路径为stats/*.txt
	 * 格式 : 主场一行/客场一行
	 * @param season
	 */
	public void getTeamStepwiseToTxt(String season);
	
	/**
	 * 获得30支球队10个赛季的pts,ast,blk,stl,trb,tov,pf的主客场分别累计值
	 * 存储路径为stats/*.txt
	 * 格式 : 主场一行/客场一行
	 * @param season
	 */
	public void getTeamStepwiseToTxt_10();
	
	/**
	 * 获得30支球队某赛季的主客场胜场数
	 * 存储路径为 stats/team_testing.txt
	 * 格式：主场一行/客场一行
	 * @param season
	 */
	public void getTeamWinsToTxt(String season);
	
	/**
	 * 获得30支球队10个赛季的主客场胜场数
	 * 存储路径为 stats/team_testing.txt
	 * 格式：主场一行/客场一行
	 */
	public void getTeamWinsToTxt_10();
	
	/**
	 * 获得单个赛季——球队主客场胜场影响差异分析结果
	 * @param season 单个赛季
	 * @return
	 */
	public TeamWinAnalysisVO getTeamTestingResultBySeason(String season);
	
	/**
	 * 获得10个赛季——球队主客场胜场影响差异统计分析结果
	 * @return
	 */
	public TeamWinAnalysisVO getTeamTestingResult_10();
	
}
