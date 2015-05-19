package ui.util;

import org.dom4j.Element;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class MySpecialTextField extends MyTextField {

	private MyComboBox box;
	
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel model;

	private FuzzySearch listener;

	public MySpecialTextField(Element ele,FuzzySearch listener) {
		super(ele);
		this.listener = listener;
		this.initBox();
		this.initTextField();
		this.setLayout(new BorderLayout());
		this.add(box, BorderLayout.SOUTH);
	}

	@SuppressWarnings("rawtypes")
	private void initBox() {
		this.model = new DefaultComboBoxModel();
		this.box = new MyComboBox(model) {
			public Dimension getPreferredSize() {
				return new Dimension(super.getPreferredSize().width, 0);
			}
		};
		this.setPreferredSize(getPreferredSize());
		this.box.setSelectedItem(null);
		this.box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isAdjusting()) {
					if (box.getSelectedItem() != null) {
						MySpecialTextField.this.setText(box.getSelectedItem().toString());
					}
				}
			}
		});
	}

	public void initTextField() {
		// 添加键盘监听器
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				setAdjusting(true);
				if (e.getKeyCode() == KeyEvent.VK_ENTER|| e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					e.setSource(box);
					box.dispatchEvent(e);
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if(box.getSelectedItem()!=null){
							setText(box.getSelectedItem().toString());
							box.setPopupVisible(false);
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					box.setPopupVisible(true);
				}
				setAdjusting(false);
			}
		});
		
		// 添加文本输入监听器
		this.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateList();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateList();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateList();
			}
			
			// 刷新列表
			private void updateList() {
				setAdjusting(true);
				String input = MySpecialTextField.this.getText();
				addItems(listener.getFuzzyResult(input));
				box.setPopupVisible(model.getSize()!=0);
				setAdjusting(false);
			}
		});
	}

	private boolean isAdjusting() {
		if (this.box.getClientProperty("is_adjusting") instanceof Boolean) {
			return (Boolean) this.box.getClientProperty("is_adjusting");
		}
		return false;
	}

	private void setAdjusting(boolean adjusting) {
		this.box.putClientProperty("is_adjusting", adjusting);
	}

	@SuppressWarnings("unchecked")
	public void addItems(ArrayList<String> strs) {
		if(strs!=null){
			this.box.removeAllItems();
			for (int i = 0; i < strs.size(); ++i) {
				this.box.addItem(strs.get(i));
			}
		}
		this.box.updateUI();
	}
}
