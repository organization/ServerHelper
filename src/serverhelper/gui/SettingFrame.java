package serverhelper.gui;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class SettingFrame extends JFrame {

	/**
	 * Create the frame.
	 */
	public SettingFrame() {
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane(panel);// 胶农费 积己

		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 500));
		
		JLabel lblWhitelist = new JLabel("whitelist");
		lblWhitelist.setBounds(12, 10, 57, 15);
		panel.add(lblWhitelist);
		
		JRadioButton rdbtnOn = new JRadioButton("on");
		rdbtnOn.setBounds(12, 31, 39, 23);
		panel.add(rdbtnOn);
		
		JRadioButton rdbtnOff = new JRadioButton("off");
		rdbtnOff.setBounds(55, 31, 39, 23);
		panel.add(rdbtnOff);
		
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(rdbtnOn);
		bGroup.add(rdbtnOff);

		getContentPane().add(scroll);

		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
