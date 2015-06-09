package util;

import vo.PlayerPerGameVO;
import vo.PlayerTotalVO;

public class ChineseToOther {
	public static Object ChineseToString(String Chinese,PlayerTotalVO vo){
		if(vo ==null){
			System.out.println("vo传了null:Chinesetoother");
			return null;
		}
		switch (Chinese){
		case "无":return "";
		case "球员名称": return vo.name;
		case "所属球队":return vo.team;
		case  "参赛场数": return vo.game;
		case  "先发场数": return vo.game_started;
		case  "篮板数": return vo.trb;
		case  "助攻数": return vo.ast;
		case  "在场时间": return vo.minute;
		case  "投篮命中率": return vo.fga_pct;
		case  "三分命中率": return vo.fg3_pct;
		case  "罚球命中率": return vo.ft_pct;
		case  "抢断数": return vo.stl;
		case  "盖帽数": return vo.blk;
		case  "失误数": return vo.tov;
		case  "犯规数": return vo.pf;
		case  "得分": return vo.pts;
		case  "进攻篮板数": return vo.orb;
		case  "防守篮板数": return vo.drb;
		
    	//case "point":return POINT;
    	case "篮板":return vo.trb;
    	case "助攻":return vo.ast;
    	case "盖帽":return vo.blk;
    	case "抢断":return vo.stl;
    	case "犯规": return vo.pf;
    	case "失误": return vo.tov;
    	case "分钟":return vo.minute;
    	
    	case "投篮": return vo.fga;
    	case "三分": return vo.fg3;
    	case "罚球": return vo.fta;
    	    	
		default:{
			System.out.println("中文无所指，chinesetoother46"+Chinese);
			return null;
		}
		
		
		
		
		}
	}
	
	public static Object ChineseToString(String Chinese,PlayerPerGameVO vo){
		if(vo ==null){
			System.out.println("vo传了null:Chinesetoother");
			return null;
		}
		switch (Chinese){
		case "无":return "";
		case "球员名称": return vo.name;
		case "所属球队":return vo.team;
		case  "参赛场数": return vo.game;
		case  "先发场数": return vo.game_started;
		case  "篮板数": return vo.trb;
		case  "助攻数": return vo.ast;
		case  "在场时间": return vo.minute;
		case  "投篮命中率": return vo.fga_pct;
		case  "三分命中率": return vo.fg3_pct;
		case  "罚球命中率": return vo.ft_pct;
		case  "抢断数": return vo.stl;
		case  "盖帽数": return vo.blk;
		case  "失误数": return vo.tov;
		case  "犯规数": return vo.pf;
		case  "得分": return vo.pts;
		case  "进攻篮板数": return vo.orb;
		case  "防守篮板数": return vo.drb;
		
		default:{
			System.out.println("中文无所指，chinesetoother94"+ Chinese);
			return null;
		}
		
		
		
		
		}
	}
	
}
