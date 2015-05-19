package ui.home;

import javax.swing.JFrame;

import ui.common.MotherPanel;
import ui.config.FrameConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.FrameUtil;

public class HomeUI extends JFrame {

	public HomeUI() {
		// 获得窗口配置
		FrameConfig fcfg = SystemConfig.getHOME_CONFIG();
		// 设置大小
		this.setSize(fcfg.getWidth(), fcfg.getHeight());

		this.setLayout(null);

		// 设置边框消失，先不用了吧，准备用苹果风
		 this.setUndecorated(true);
		// 设置不可更改大小
		this.setResizable(false);
		// 默认关闭退出
		this.setDefaultCloseOperation(3);
		// 居中
		FrameUtil.setFrameCenter(this, fcfg.getWindowUp());
		
		//this.getContentPane().add(new HomePanel(this));
		this.getContentPane().add(new MotherPanel(this));
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new HomeUI();
	}
	

}
