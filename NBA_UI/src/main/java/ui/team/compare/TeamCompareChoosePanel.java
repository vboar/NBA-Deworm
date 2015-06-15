package ui.team.compare;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.FuzzySearch;
import ui.util.LoadFont;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import vo.TeamInfoVO;

public class TeamCompareChoosePanel extends JPanel{
	private PanelConfig pcfg;
	private HomeUI frame;

	private int fill = 0;
	Image bg;

	private MySpecialTextField[] fields = new MySpecialTextField[2];

	private MyButton btn1;
	private MyButton btn2;
	private MyButton search;

	
	private MyLabel img;
	private MyLabel name;
	
	private MyLabel abbrHint;
	private MyLabel timeHint;
	private MyLabel divisionHint;
	private MyLabel leagueHint;
	private MyLabel recordHint;
	private MyLabel playoffHint;
	private MyLabel winHint;
	
	private MyLabel abbr;
	private MyLabel time;
	private MyLabel division;
	private MyLabel league;
	private MyLabel record;
	private MyLabel playoff;
	private MyLabel win;

	
	private MyLabel img2;
	private MyLabel name2;
	
	private MyLabel abbr2;
	private MyLabel time2;
	private MyLabel division2;
	private MyLabel league2;
	private MyLabel record2;
	private MyLabel playoff2;
	private MyLabel win2;


	private MyLabel abbrHint2;
	private MyLabel timeHint2;
	private MyLabel divisionHint2;
	private MyLabel leagueHint2;
	private MyLabel recordHint2;
	private MyLabel playoffHint2;
	private MyLabel winHint2;

	public TeamCompareChoosePanel(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		this.bg = pcfg.getBg();
		// 设置布局管理器为自由布局
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		// 初始化组件
		this.initComponent();
		this.repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}

	private void initComponent() {
		inittextFields();
		initButtons();
		initLabels();

	}

	private void inittextFields() {
		FuzzySearch fz = new FuzzySearch() {
			@Override
			public ArrayList<String> getFuzzyResult(String keyword) {
				List<TeamInfoVO> list = null;
				try {
					list = ServiceFactoryImpl.getInstance().getTeamService()
							.getAllTeamInfo();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ArrayList<String> list2 = new ArrayList<String>();
				for (TeamInfoVO s : list) {
					if (s.name.contains(keyword))
						list2.add(s.name);
				}
				return list2;
			}
		};

		fields[0] = new MySpecialTextField(pcfg.getTextFields().element(
				"field1"), fz);
		fields[1] = new MySpecialTextField(pcfg.getTextFields().element(
				"field2"), fz);
		add(fields[0]);
		add(fields[1]);
	}

	private void initButtons() {
		btn1 = new MyButton(pcfg.getButtons().element("btn1"), true);
		btn2 = new MyButton(pcfg.getButtons().element("btn2"), true);
		search = new MyButton(pcfg.getButtons().element("search"), true);
		add(btn1);
		add(btn2);
		add(search);
		addAction(btn1, 0);
		addAction(btn2, 1);
		addSerachAction(search);

	}

	private void initLabels() {
		img = new MyLabel(pcfg.getLabels().element("img"));
		img.setFont(new Font("华文细黑", 0, 14));
		add(img);

		abbrHint = new MyLabel(pcfg.getLabels().element("abbrhint"));
		abbrHint.setFont(new Font("华文细黑", 0, 14));
		add(abbrHint);

		timeHint = new MyLabel(pcfg.getLabels().element("timehint"));
		timeHint.setFont(new Font("华文细黑", 0, 14));
		add(timeHint);

		divisionHint = new MyLabel(pcfg.getLabels().element("divisionhint"));
		divisionHint.setFont(new Font("华文细黑", 0, 14));
		add(divisionHint);

		leagueHint = new MyLabel(pcfg.getLabels().element("leaguehint"));
		leagueHint.setFont(new Font("华文细黑", 0, 14));
		add(leagueHint);

		recordHint = new MyLabel(pcfg.getLabels().element("recordhint"));
		recordHint.setFont(new Font("华文细黑", 0, 14));
		add(recordHint);

		playoffHint = new MyLabel(pcfg.getLabels().element("playoffhint"));
		playoffHint.setFont(new Font("华文细黑", 0, 14));
		add(playoffHint);

		winHint = new MyLabel(pcfg.getLabels().element("winhint"));
		winHint.setFont(new Font("华文细黑", 0, 14));
		add(winHint);

		//

		name = new MyLabel(pcfg.getLabels().element("name"));
		name.setForeground(Color.white);
		add(name);

		abbr = new MyLabel(pcfg.getLabels().element("abbr"));
		abbr.setFont(new Font("华文细黑", 0, 14));
		add(abbr);

		time = new MyLabel(pcfg.getLabels().element("time"));
		time.setFont(new Font("华文细黑", 0, 14));
		add(time);

		division = new MyLabel(pcfg.getLabels().element("division"));
		division.setFont(new Font("华文细黑", 0, 14));
		add(division);

		league = new MyLabel(pcfg.getLabels().element("league"));
		league.setFont(new Font("华文细黑", 0, 14));
		add(league);

		record = new MyLabel(pcfg.getLabels().element("record"));
		record.setFont(new Font("华文细黑", 0, 14));
		add(record);

		playoff = new MyLabel(pcfg.getLabels().element("playoff"));
		playoff.setFont(new Font("华文细黑", 0, 14));
		add(playoff);

		win = new MyLabel(pcfg.getLabels().element("win"));
		win.setFont(new Font("华文细黑", 0, 14));
		add(win);

		img2 = new MyLabel(pcfg.getLabels().element("img2"));
		img2.setFont(new Font("华文细黑", 0, 14));
		add(img2);

		abbrHint2 = new MyLabel(pcfg.getLabels().element("abbrhint2"));
		abbrHint2.setFont(new Font("华文细黑", 0, 14));
		add(abbrHint2);

		timeHint2 = new MyLabel(pcfg.getLabels().element("timehint2"));
		timeHint2.setFont(new Font("华文细黑", 0, 14));
		add(timeHint2);

		divisionHint2 = new MyLabel(pcfg.getLabels().element("divisionhint2"));
		divisionHint2.setFont(new Font("华文细黑", 0, 14));
		add(divisionHint2);

		leagueHint2 = new MyLabel(pcfg.getLabels().element("leaguehint2"));
		leagueHint2.setFont(new Font("华文细黑", 0, 14));
		add(leagueHint2);

		recordHint2 = new MyLabel(pcfg.getLabels().element("recordhint2"));
		recordHint2.setFont(new Font("华文细黑", 0, 14));
		add(recordHint2);

		playoffHint2 = new MyLabel(pcfg.getLabels().element("playoffhint2"));
		playoffHint2.setFont(new Font("华文细黑", 0, 14));
		add(playoffHint2);

		winHint2 = new MyLabel(pcfg.getLabels().element("winhint2"));
		winHint2.setFont(new Font("华文细黑", 0, 14));
		add(winHint2);

		//

		name2 = new MyLabel(pcfg.getLabels().element("name2"));
		name2.setForeground(Color.white);
		add(name2);

		abbr2 = new MyLabel(pcfg.getLabels().element("abbr2"));
		abbr2.setFont(new Font("华文细黑", 0, 14));
		add(abbr2);

		time2 = new MyLabel(pcfg.getLabels().element("time2"));
		time2.setFont(new Font("华文细黑", 0, 14));
		add(time2);

		division2 = new MyLabel(pcfg.getLabels().element("division2"));
		division2.setFont(new Font("华文细黑", 0, 14));
		add(division2);

		league2 = new MyLabel(pcfg.getLabels().element("league2"));
		league2.setFont(new Font("华文细黑", 0, 14));
		add(league2);

		record2 = new MyLabel(pcfg.getLabels().element("record2"));
		record2.setFont(new Font("华文细黑", 0, 14));
		add(record2);

		playoff2 = new MyLabel(pcfg.getLabels().element("playoff2"));
		playoff2.setFont(new Font("华文细黑", 0, 14));
		add(playoff2);

		win2 = new MyLabel(pcfg.getLabels().element("win2"));
		win2.setFont(new Font("华文细黑", 0, 14));
		add(win2);

	}

	private void addAction(MyButton btn, int num) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = fields[num].getText();
				try {
					List<TeamInfoVO> list = ServiceFactoryImpl.getInstance()
							.getTeamService().getAllTeamInfo();
					TeamInfoVO vo = null;
					for(TeamInfoVO temp: list){
						if(name.equals(temp.name))
							vo = temp;
					}
					
					changeData(vo, num);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	private void changeData(TeamInfoVO vo, int number) {
		switch (number) {
		case 0: {
			name.setText(vo.name);

			abbr.setText(vo.abbr);			
			time.setText(vo.buildup_time);
			division.setText(vo.division);
			league.setText(vo.league);
			record.setText(vo.record);
			playoff.setText(vo.playeroff_appearance.toString());
			win.setText(vo.championships.toString());

			ImageIcon icon = null;
			try {
				icon = ServiceFactoryImpl.getInstance().getTeamService()
						.getTeamLogoByAbbr(vo.abbr);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (icon != null) {
				icon.setImage(icon.getImage().getScaledInstance(200, 150,
						Image.SCALE_DEFAULT));
				img.setIcon(icon);
			} else {
				icon = new ImageIcon("img/player/unknown.png");
				icon.setImage(icon.getImage().getScaledInstance(169, 130,
						Image.SCALE_DEFAULT));
				img.setIcon(icon);
			}
			fill++;
			break;
		}
		case 1: {
			name2.setText(vo.name);

			abbr2.setText(vo.abbr);			
			time2.setText(vo.buildup_time);
			division2.setText(vo.division);
			league2.setText(vo.league);
			record2.setText(vo.record);
			playoff2.setText(vo.playeroff_appearance.toString());
			win2.setText(vo.championships.toString());

			ImageIcon icon = null;
			try {
				icon = ServiceFactoryImpl.getInstance().getTeamService()
						.getTeamLogoByAbbr(vo.abbr);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (icon != null) {
				icon.setImage(icon.getImage().getScaledInstance(200, 150,
						Image.SCALE_DEFAULT));
				img2.setIcon(icon);
			} else {
				icon = new ImageIcon("img/player/unknown.png");
				icon.setImage(icon.getImage().getScaledInstance(169, 130,
						Image.SCALE_DEFAULT));
				img2.setIcon(icon);
			}
			fill++;
			break;
		}
		}
	}

	private void addSerachAction(MyButton btn) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fill >= 2) {
					frame.motherPanel.playerPanel.compareChoosePanel
							.setVisible(false);
					frame.motherPanel.playerPanel.comparePanel.changeData(
							fields[0].getText(), fields[1].getText());
					frame.motherPanel.playerPanel.comparePanel.setVisible(true);
				}
			}
		});
	}
}
