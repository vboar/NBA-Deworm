package ui.player.compare;

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
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import vo.PlayerInfoVO;

public class CompareChoosePanel extends JPanel {

	private PanelConfig pcfg;
	private HomeUI frame;

	private int fill = 0;

	private MySpecialTextField[] fields = new MySpecialTextField[2];

	private MyButton btn1;
	private MyButton btn2;
	private MyButton search;

	private MyLabel positionHint;
	private MyLabel bornHint;
	private MyLabel hometownHint;
	private MyLabel heightHint;
	private MyLabel weightHint;
	private MyLabel highschoolHint;
	private MyLabel collegeHint;
	private MyLabel debutHint;
	private MyLabel expHint;
	private MyLabel numHint;

	private MyLabel img2;
	private MyLabel name2;
	private MyLabel position2;
	private MyLabel born2;
	private MyLabel hometown2;
	private MyLabel height2;
	private MyLabel weight2;
	private MyLabel highschool2;
	private MyLabel college2;
	private MyLabel debut2;
	private MyLabel exp2;
	private MyLabel num2;

	private MyLabel positionHint2;
	private MyLabel bornHint2;
	private MyLabel hometownHint2;
	private MyLabel heightHint2;
	private MyLabel weightHint2;
	private MyLabel highschoolHint2;
	private MyLabel collegeHint2;
	private MyLabel debutHint2;
	private MyLabel expHint2;
	private MyLabel numHint2;

	private MyLabel img;
	private MyLabel name;
	private MyLabel position;
	private MyLabel born;
	private MyLabel hometown;
	private MyLabel height;
	private MyLabel weight;
	private MyLabel highschool;
	private MyLabel college;
	private MyLabel debut;
	private MyLabel exp;
	private MyLabel num;

	public CompareChoosePanel(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		// 初始化组件
		this.initComponent();
		this.repaint();

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
				List<String> list = null;
				try {
					list = ServiceFactoryImpl.getInstance().getPlayerService()
							.getNameList(" ");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return (ArrayList<String>) list;
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
		btn1 = new MyButton(pcfg.getButtons().element("btn1"));
		btn2 = new MyButton(pcfg.getButtons().element("btn2"));
		search = new MyButton(pcfg.getButtons().element("search"));
		add(btn1);
		add(btn2);
		add(search);
		addAction(btn1, 0);
		addAction(btn2, 1);
		addSerachAction(search);
		

	}

	private void initLabels() {
		img = new MyLabel(pcfg.getLabels().element("img"));
		add(img);

		positionHint = new MyLabel(pcfg.getLabels().element("positionhint"));
		add(positionHint);

		bornHint = new MyLabel(pcfg.getLabels().element("bornhint"));
		add(bornHint);

		hometownHint = new MyLabel(pcfg.getLabels().element("hometownhint"));
		add(hometownHint);

		heightHint = new MyLabel(pcfg.getLabels().element("heighthint"));
		add(heightHint);

		weightHint = new MyLabel(pcfg.getLabels().element("weighthint"));
		add(weightHint);

		highschoolHint = new MyLabel(pcfg.getLabels().element("highschoolhint"));
		add(highschoolHint);

		collegeHint = new MyLabel(pcfg.getLabels().element("collegehint"));
		add(collegeHint);

		debutHint = new MyLabel(pcfg.getLabels().element("debuthint"));
		add(debutHint);

		expHint = new MyLabel(pcfg.getLabels().element("exphint"));
		add(expHint);

		numHint = new MyLabel(pcfg.getLabels().element("numhint"));
		add(numHint);

		//

		name = new MyLabel(pcfg.getLabels().element("name"));
		add(name);

		position = new MyLabel(pcfg.getLabels().element("position"));
		add(position);

		born = new MyLabel(pcfg.getLabels().element("born"));
		add(born);

		hometown = new MyLabel(pcfg.getLabels().element("hometown"));
		add(hometown);

		height = new MyLabel(pcfg.getLabels().element("height"));
		add(height);

		weight = new MyLabel(pcfg.getLabels().element("weight"));
		add(weight);

		highschool = new MyLabel(pcfg.getLabels().element("highschool"));
		add(highschool);

		college = new MyLabel(pcfg.getLabels().element("college"));
		add(college);

		debut = new MyLabel(pcfg.getLabels().element("debut"));
		add(debut);

		exp = new MyLabel(pcfg.getLabels().element("exp"));
		add(exp);

		num = new MyLabel(pcfg.getLabels().element("num"));
		add(num);

		img2 = new MyLabel(pcfg.getLabels().element("img2"));
		add(img2);

		positionHint2 = new MyLabel(pcfg.getLabels().element("positionhint2"));
		add(positionHint2);

		bornHint2 = new MyLabel(pcfg.getLabels().element("bornhint2"));
		add(bornHint2);

		hometownHint2 = new MyLabel(pcfg.getLabels().element("2"));
		add(hometownHint2);

		heightHint2 = new MyLabel(pcfg.getLabels().element("heighthint2"));
		add(heightHint2);

		weightHint2 = new MyLabel(pcfg.getLabels().element("weighthint2"));
		add(weightHint2);

		highschoolHint2 = new MyLabel(pcfg.getLabels().element(
				"highschoolhint2"));
		add(highschoolHint);

		collegeHint2 = new MyLabel(pcfg.getLabels().element("collegehint2"));
		add(collegeHint2);

		debutHint2 = new MyLabel(pcfg.getLabels().element("debuthint2"));
		add(debutHint2);

		expHint2 = new MyLabel(pcfg.getLabels().element("exphint2"));
		add(expHint2);

		numHint2 = new MyLabel(pcfg.getLabels().element("numhint2"));
		add(numHint2);

		//

		name2 = new MyLabel(pcfg.getLabels().element("name2"));
		add(name2);

		position2 = new MyLabel(pcfg.getLabels().element("position2"));
		add(position2);

		born2 = new MyLabel(pcfg.getLabels().element("born2"));
		add(born2);

		hometown2 = new MyLabel(pcfg.getLabels().element("hometown2"));
		add(hometown2);

		height2 = new MyLabel(pcfg.getLabels().element("height2"));
		add(height2);

		weight2 = new MyLabel(pcfg.getLabels().element("weight2"));
		add(weight2);

		highschool2 = new MyLabel(pcfg.getLabels().element("highschool2"));
		add(highschool2);

		college2 = new MyLabel(pcfg.getLabels().element("college2"));
		add(college2);

		debut2 = new MyLabel(pcfg.getLabels().element("debut2"));
		add(debut2);

		exp2 = new MyLabel(pcfg.getLabels().element("exp2"));
		add(exp2);

		num2 = new MyLabel(pcfg.getLabels().element("num2"));
		add(num2);
	}

	private void addAction(MyButton btn, int num) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = fields[num].getText();
				try {
					PlayerInfoVO vo = ServiceFactoryImpl.getInstance()
							.getPlayerService().getPlayerInfoByName(name);
					changeData(num, vo);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	private void changeData(int number, PlayerInfoVO vo) {
		switch (number) {
		case 0: {
			name.setText(vo.name);

			position.setText(vo.position);

			born.setText(vo.born);

			hometown.setText(vo.hometown);
			height.setText(vo.height);

			weight.setText(vo.weight.toString());

			highschool.setText(vo.high_school);

			college.setText(vo.college);

			debut.setText(vo.debut);

			exp.setText(vo.exp.toString());

			num.setText(vo.number.toString());

			try {
				ImageIcon icon = ServiceFactoryImpl.getInstance()
						.getPlayerService().getPlayerPortraitByName(vo.name);
				img.setImage(icon);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fill++;
			break;
		}
		case 1: {
			name2.setText(vo.name);

			position2.setText(vo.position);

			born2.setText(vo.born);

			hometown2.setText(vo.hometown);
			height2.setText(vo.height);

			weight2.setText(vo.weight.toString());

			highschool2.setText(vo.high_school);

			college2.setText(vo.college);

			debut2.setText(vo.debut);

			exp2.setText(vo.exp.toString());

			num2.setText(vo.number.toString());

			try {
				ImageIcon icon = ServiceFactoryImpl.getInstance()
						.getPlayerService().getPlayerPortraitByName(vo.name);
				img2.setImage(icon);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fill++;
			break;
		}
		}
	}
	
	private void addSerachAction(MyButton btn){
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fill>=2){
					//TODO
				}
			}
		});
	}

}
