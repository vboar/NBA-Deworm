package service;

import java.util.ArrayList;
import java.util.List;

import util.Utility;
import dao.MatchDao;
import dao.impl.DaoFactoryImpl;
import entity.MatchInfo;
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
		//is.getTeamStepwise("14-15");
		is.getTeamWins();
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

	public void getTeamWins(){
		String[] seasons = {"14-15", "13-14", "12-13", "10-11", "09-10", 
							"08-09", "07-08", "06-07", "05-06", "04-05"};
		String[] teams = {"ATL", "BOS", "NJN", "CHA", "CHI", "CLE",
				  "DAL", "DEN", "DET", "GSW", "HOU", "IND",
				  "LAC", "LAL", "MEM", "MIA", "MIL", "MIN",
				  "NOH", "NYK", "OKC", "ORL", "PHI", "PHO",
				  "POR", "SAC", "SAS", "TOR", "UTA", "WAS"};
		String home_Str="";
		String guest_Str="";
		for(int k=0; k<seasons.length;++k){
			List<MatchInfo> list = mdao.getRegularMatchInfoBySeason(seasons[k]);
			int[] home_wins = new int[30];
			int[] guest_wins = new int[30];
			for(MatchInfo m: list){
				String home = m.getHome_team();
				String guest = m.getGuest_team();
				if(m.getHome_point()>m.getGuest_point()){
					for(int i=0; i<teams.length; ++i){
						if(teams[i].equals(home)){
							home_wins[i]++;
						}
					}
				}else{
					for(int j=0; j<teams.length;++j){
						if(teams[j].equals(guest)){
							guest_wins[j]++;
						}
					}
				}
			}
			int hsum=0;
			int gsum=0;
			for(int i=0; i<30; ++i){
				home_Str += home_wins[i] + ";";
				guest_Str += guest_wins[i] + ";";
				hsum += home_wins[i];
				gsum += guest_wins[i];
			}
			System.out.println(hsum + " " + gsum);
		}

		home_Str=home_Str.substring(0,home_Str.length()-1);
		guest_Str = guest_Str.substring(0, guest_Str.length()-1);
		List<String> s = new ArrayList<String>();
		s.add(home_Str);
		s.add(guest_Str);
		Utility.writeMulti(s, "stats/team_testing.txt");
	}
	
}
