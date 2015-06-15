package util;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.home.HomeUI;
import ui.team.TeamDetail;
import ui.util.MyLabel;

public class Jump {

	public static void jumpToPlayer(MyLabel lb, int num, HomeUI frame) {
		lb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lb.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				switch (num) {
				case 1: {
					frame.motherPanel.teamPanel.setVisible(false);
					frame.motherPanel.teamnav.setVisible(false);
					frame.motherPanel.playernav.setVisible(true);
					frame.motherPanel.playernav.setColor(0);
					frame.motherPanel.playerPanel.playerInfoPane.changeData(lb
							.getName());
					frame.motherPanel.playerPanel.setVisible(true);
					frame.motherPanel.playernav.hintAll();
					frame.motherPanel.playerPanel.playerInfoPane
							.setVisible(true);
					break;
				}
				case 2: {
					frame.motherPanel.matchPanel.setVisible(false);
					frame.motherPanel.matchnav.setVisible(false);
					frame.motherPanel.playernav.setVisible(true);
					frame.motherPanel.playernav.setColor(0);
					frame.motherPanel.playerPanel.playerInfoPane.changeData(lb
							.getName());
					frame.motherPanel.playerPanel.setVisible(true);
					frame.motherPanel.playernav.hintAll();
					frame.motherPanel.playerPanel.playerInfoPane
							.setVisible(true);
					break;
				}
				}

			}
		});

	}

	public static void jumpToTeam(MyLabel lb, int num, HomeUI frame) {
		lb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lb.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				switch (num) {
				case 0: {
					frame.motherPanel.playerPanel.setVisible(false);
					frame.motherPanel.teamPanel
							.add(frame.motherPanel.teamPanel.teamDetail = new TeamDetail(
									frame, lb.getName()));
					frame.motherPanel.teamPanel.teamindex.setVisible(false);
					frame.motherPanel.playernav.setVisible(false);
					frame.motherPanel.teamnav.setVisible(true);
					frame.motherPanel.teamPanel.setVisible(true);
					break;
				}
				case 2:{
					frame.motherPanel.matchPanel.setVisible(false);
					frame.motherPanel.teamPanel
							.add(frame.motherPanel.teamPanel.teamDetail = new TeamDetail(
									frame, lb.getName()));
					frame.motherPanel.teamPanel.teamindex.setVisible(false);
					frame.motherPanel.matchnav.setVisible(false);
					frame.motherPanel.teamnav.setVisible(true);
					frame.motherPanel.teamPanel.setVisible(true);
					break;
				}
				}
			}
		});

	}
}
