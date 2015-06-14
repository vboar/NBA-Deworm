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
	
	private String[] teams = {"ATL", "BOS", "NJN", "CHA", "CHI", "CLE",
			  "DAL", "DEN", "DET", "GSW", "HOU", "IND",
			  "LAC", "LAL", "MEM", "MIA", "MIL", "MIN",
			  "NOH", "NYK", "OKC", "ORL", "PHI", "PHO",
			  "POR", "SAC", "SAS", "TOR", "UTA", "WAS"};
	
	private String[] fields = {"pts", "ast", "blk", "stl", "trb", "tov", "pf"};
	
	public InferenceStatsServiceImpl(){
		mdao = DaoFactoryImpl.getDaoFactory().getMatchDao();
	}
	
	public static void main(String[] args) {
		InferenceStatsServiceImpl is = new InferenceStatsServiceImpl();
		is.getTeamStepwiseToTxt("12-13");
		is.getTeamWinsToTxt("12-13");
	}
	
	@Override
	public void getTeamStepwiseToTxt(String season){
		int[][] homes = new int[30][7];
		int[][] guests = new int[30][7];
		String[] home_str = new String[7];
		String[] guest_str = new String[7];
		for(int i=0; i<7 ; ++i){
			home_str[i] = "";
			guest_str[i] = "";
		}
		List<MatchPlayerBasic> homelist = mdao.getGuestHomeTeamTotalBySeason(season, true);
		List<MatchPlayerBasic> guestlist = mdao.getGuestHomeTeamTotalBySeason(season, false);
		for(int k=0; k<homelist.size();++k){
			MatchPlayerBasic h = homelist.get(k);
			MatchPlayerBasic g = guestlist.get(k);
			for(int i=0; i<teams.length; ++i){
				if(teams[i].equals(h.getTeam_abbr())){
					homes[i][0] += h.getPts();
					homes[i][1] += h.getAst();
					homes[i][2] += h.getBlk();
					homes[i][3] += h.getStl();
					homes[i][4] += h.getTrb();
					homes[i][5] += h.getTov();
					homes[i][6] += h.getPf();
					guests[i][0] += g.getPts();
					guests[i][1] += g.getAst();
					guests[i][2] += g.getBlk();
					guests[i][3] += g.getStl();
					guests[i][4] += g.getTrb();
					guests[i][5] += g.getTov();
					guests[i][6] += g.getPf();
				}
			}
		
		}
		for(int i=0; i<30; ++i){
			home_str[0] += homes[i][0] + ";";
			home_str[1] += homes[i][1] + ";";
			home_str[2] += homes[i][2] + ";";
			home_str[3] += homes[i][3] + ";";
			home_str[4] += homes[i][4] + ";";
			home_str[5] += homes[i][5] + ";";
			home_str[6] += homes[i][6] + ";";
			guest_str[0] += guests[i][0] + ";";
			guest_str[1] += guests[i][1] + ";";
			guest_str[2] += guests[i][2] + ";";
			guest_str[3] += guests[i][3] + ";";
			guest_str[4] += guests[i][4] + ";";
			guest_str[5] += guests[i][5] + ";";
			guest_str[6] += guests[i][6] + ";";
		}
		for(int i=0; i<7; ++i){
			List<String> strs = new ArrayList<String>();
			home_str[i] = checkString(home_str[i]);
			guest_str[i] = checkString(guest_str[i]);
			strs.add(checkString(home_str[i]));
			strs.add(checkString(guest_str[i]));
			Utility.writeMulti(strs, "stats/"+fields[i]+".txt");
		}
	}

	@Override
	public void getTeamWinsToTxt(String season){
//		String[] seasons = {"14-15", "13-14", "12-13", "10-11", "09-10", 
//							"08-09", "07-08", "06-07", "05-06", "04-05"};
		String home_Str="";
		String guest_Str="";
//		for(int k=0; k<seasons.length;++k){
			List<MatchInfo> list = mdao.getRegularMatchInfoBySeason(season);
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
			for(int i=0; i<30; ++i){
				home_Str += home_wins[i] + ";";
				guest_Str += guest_wins[i] + ";";
			}
//		}

		home_Str=home_Str.substring(0,home_Str.length()-1);
		guest_Str = guest_Str.substring(0, guest_Str.length()-1);
		List<String> s = new ArrayList<String>();
		s.add(home_Str);
		s.add(guest_Str);
		Utility.writeMulti(s, "stats/team_testing.txt");
	}

	
	private String checkString(String str){
		if(str.length()==0){
			return str;
		}
		return str.substring(0,str.length()-1);
	}
	
}
