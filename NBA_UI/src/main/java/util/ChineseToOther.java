package util;

import vo.PlayerAdvancedVO;
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
	
	public static Object ChineseToString(String Chinese,PlayerAdvancedVO vo){
		if(vo ==null){
			System.out.println("vo传了null:Chinesetoother");
			return null;
		}
		switch (Chinese){
		case "无":return "";
		case "球员名称": return vo.name;
		case "所属球队":return vo.team;
		case  "true shooting percentage": return vo.ts_pct;
		case  "player efficiency rating": return vo.per;
		case  "3-point attempt rate": return vo.fa3a_per_fga_pct;
		case  "free throw attempt rate": return vo.fta_per_fga_pct;
		case  "进攻篮板率": return vo.orb_pct;
		case  "防守篮板率": return vo.drb_pct;
		case  "总篮板率": return vo.trb_pct;
		case  "助攻率": return vo.ast_pct;
		case  "抢断率": return vo.stl_pct;
		case  "盖帽率": return vo.blk_pct;
		case  "失误率": return vo.tov_pct;
		case  "使用率": return vo.usg_pct;
		case  "offensive win shares": return vo.ows;
		case  "defensive win shares": return vo.dws;
		case  "win shares": return vo.ws;		
		case  "win shares per 48 mins": return vo.ws_48;
		case  "offensive box plus/minus": return vo.obpm;
		case  "defensive box plus/minus": return vo.dbpm;
		case  "box plus/minus": return vo.bpm;
		case  "value over replacement player": return vo.vorp;
		
		
		
		
		
		default:{
			System.out.println("中文无所指，chinesetoother94"+ Chinese);
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
