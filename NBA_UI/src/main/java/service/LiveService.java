package service;

import vo.LiveMatchInfoVO;
import vo.LiveMatchVO;
import vo.LiveMsgVO;

import java.util.List;

/**
 * Created by Vboar on 2015/6/1.
 */
public interface LiveService {

    /**
     * 开启直播Python服务
     */
    public void startLiveService();

    /**
     * 停止直播Python服务
     */
    public void stopLiveService();

    /**
     * 获得直播比赛列表
     * @return 直播比赛列表
     */
    public List<LiveMatchInfoVO> getAllLiveList();

    /**
     * 检查比赛是否开始
     * @return 如有开始的比赛则返回比赛VO，如果没有则返回null
     */
    public LiveMatchInfoVO checkMatchStart();

    /**
     * 根据MatchId获得所有比赛消息
     * @param matchId
     * @return
     */
    public List<LiveMsgVO> getMsg(String matchId);

    /**
     * 获得历史比赛列表
     * @return
     */
    public List<LiveMatchInfoVO> getHistoryList();

    /**
     * 获得动态比赛信息VO类
     * @param matchId
     * @return
     */
    public LiveMatchVO getMatchVO(String matchId);

    /**
     * 将虎扑直播ID转化为数据库GameId
     * @param hupuId
     * @return
     */
    public String hupuIdToGameId(String hupuId);

}
