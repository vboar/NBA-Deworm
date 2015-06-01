package vo;

/**
 * 比赛消息VO
 * Created by Vboar on 2015/6/1.
 */
public class LiveMsgVO {

    /**
     * 消息类型：0为暂停和结束消息（加粗），1为普通消息，2为强调消息（加粗）
     */
    public int type;

    public String sid;

    public String content;

    public String residualTime;

    public String team;

    public String scores;

    public LiveMsgVO() {}

}
