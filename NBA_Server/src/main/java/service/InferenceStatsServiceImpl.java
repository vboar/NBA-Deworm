package service;

import java.util.List;

import util.Utility;
import dao.MatchDao;
import dao.impl.DaoFactoryImpl;
import entity.MatchPlayerBasic;

/**
 * 推断统计
 * 
 * created by JaneLDQ on 2015年6月14日 下午3:42:30
 */
public class InferenceStatsServiceImpl implements InferenceStatsService {
	
	private MatchDao mdao;
	
	public InferenceStatsServiceImpl(){
		mdao = DaoFactoryImpl.getDaoFactory().getMatchDao();
	}
	
	public static void main(String[] args) {
		InferenceStatsServiceImpl is = new InferenceStatsServiceImpl();
		is.getTeamStepwise("14-15");
	}
	
	public void getTeamStepwise(String season){
		List<MatchPlayerBasic> list = mdao.getMatchPlayerBasicByPlayerName("Team Totals", season, null, -1);
		String pts="";
		String ast="";
		String blk="";
		String stl="";
		String trb="";
		String tov="";
		String pf="";
		for(MatchPlayerBasic mpb: list){
			pts += mpb.getPts() + ";";
			ast += mpb.getAst() + ";";
			blk += mpb.getBlk() + ";";
			stl += mpb.getStl() + ";";
			trb += mpb.getTrb() + ";";
			tov += mpb.getTov() + ";";
			pf += mpb.getPf() + ";";
		}
		pts = checkString(pts);
		ast = checkString(ast);
		blk = checkString(blk);
		stl = checkString(stl);
		trb = checkString(trb);
		tov = checkString(tov);
		pf = checkString(pf);
		Utility.write(pts, "stats/pts.txt");
		Utility.write(ast, "stats/ast.txt");
		Utility.write(blk, "stats/blk.txt");
		Utility.write(stl, "stats/stl.txt");
		Utility.write(trb, "stats/trb.txt");
		Utility.write(tov, "stats/tov.txt");
		Utility.write(pf, "stats/pf.txt");
	}
	
	private String checkString(String str){
		if(str.length()==0){
			return str;
		}
		return str.substring(0,str.length()-1);
	}

}
