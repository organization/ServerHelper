package serverhelper.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.function.BiConsumer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.TranslationContainer;
import cn.nukkit.utils.TextFormat;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SettingFrame extends JFrame {
	private JTextField spawnProtectionField;
	private JTextField maxPlayerField;
	private JTextField whitelistPlayerField;

	public SettingFrame() {
		setTitle("Server Helper");
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane(panel);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("settings.png"));

		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(300, 800));
		
		Border whitelistBorder = BorderFactory.createEtchedBorder();
		whitelistBorder = BorderFactory.createTitledBorder(whitelistBorder, "ȭ��Ʈ����Ʈ");
		JPanel wlBorderPanel = new JPanel();
		wlBorderPanel.setLayout(new GridLayout(0, 1));
		wlBorderPanel.setBorder(whitelistBorder);
		wlBorderPanel.setBounds(12, 10, 94, 61);
		
		JRadioButton whitelistOn = new JRadioButton("�ѱ�");
		wlBorderPanel.add(whitelistOn);
		
		JRadioButton whitelistOff = new JRadioButton("����");
		wlBorderPanel.add(whitelistOff);
		
		panel.add(wlBorderPanel);
		
		ButtonGroup whitelistGroup = new ButtonGroup();
		whitelistGroup.add(whitelistOn);
		whitelistGroup.add(whitelistOff);
		
		if (Server.getInstance().hasWhitelist()) {
			whitelistOn.setSelected(true);
		} else {
			whitelistOff.setSelected(false);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 138, 276, 2);
		panel.add(separator);
		
		Border achieveBorder = BorderFactory.createEtchedBorder();
		achieveBorder = BorderFactory.createTitledBorder(achieveBorder, "��������");
		JPanel achievePanel = new JPanel();
		achievePanel.setLayout(new GridLayout(0, 1));
		achievePanel.setBorder(achieveBorder);
		achievePanel.setBounds(12, 150, 105, 69);
		
		JRadioButton achievementOnBtn = new JRadioButton("�ѱ�");
		achievementOnBtn.setBounds(12, 244, 49, 23);
		achievePanel.add(achievementOnBtn);
		
		JRadioButton achievementOffBtn = new JRadioButton("����");
		achievementOffBtn.setBounds(65, 244, 121, 23);
		achievePanel.add(achievementOffBtn);
		
		achievePanel.add(achievementOnBtn);
		achievePanel.add(achievementOffBtn);
		panel.add(achievePanel);
		
		ButtonGroup acheiveBtnGroup = new ButtonGroup();
		acheiveBtnGroup.add(achievementOnBtn);
		acheiveBtnGroup.add(achievementOffBtn);
		
		if (Server.getInstance().getPropertyBoolean("announce-player-achievements")) {
			achievementOnBtn.setSelected(true);
		} else {
			achievementOffBtn.setSelected(false);
		} 
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 229, 276, 2);
		panel.add(separator_1);
		
		JLabel lblSpawn = new JLabel("������ȣ ����");
		lblSpawn.setBounds(12, 241, 83, 15);
		panel.add(lblSpawn);
		
		spawnProtectionField = new JTextField();
		spawnProtectionField.setBounds(12, 266, 76, 21);
		panel.add(spawnProtectionField);
		spawnProtectionField.setColumns(10);
		spawnProtectionField.setText(Server.getInstance().getPropertyString("spawn-protection"));
		
		JButton btnProtectionApply = new JButton("����");
		btnProtectionApply.setBounds(100, 265, 63, 23);
		panel.add(btnProtectionApply);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 297, 276, 2);
		panel.add(separator_2);
		
		JLabel lblPlayer = new JLabel("�ִ� �÷��̾� ��");
		lblPlayer.setBounds(12, 309, 105, 15);
		panel.add(lblPlayer);
		
		maxPlayerField = new JTextField();
		maxPlayerField.setBounds(12, 334, 76, 21);
		panel.add(maxPlayerField);
		maxPlayerField.setColumns(10);
		maxPlayerField.setText(String.valueOf(Server.getInstance().getMaxPlayers()));
		
		JButton maxPlayerApplybutton = new JButton("����");
		maxPlayerApplybutton.setBounds(100, 334, 63, 23);
		panel.add(maxPlayerApplybutton);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 365, 276, 2);
		panel.add(separator_3);
		
		Border flyBorder = BorderFactory.createEtchedBorder();
		flyBorder = BorderFactory.createTitledBorder(flyBorder, "����");
		JPanel flyPanel = new JPanel();
		flyPanel.setLayout(new GridLayout(0, 1));
		flyPanel.setBorder(flyBorder);
		flyPanel.setBounds(12, 377, 105, 69);
		
		JRadioButton flyAllowBtn = new JRadioButton("���");
		JRadioButton flyDisallowBtn = new JRadioButton("�����");
		
		flyPanel.add(flyAllowBtn);
		flyPanel.add(flyDisallowBtn);
		panel.add(flyPanel);
		
		if (Server.getInstance().getAllowFlight()) {
			flyAllowBtn.setSelected(true);
		} else {
			flyDisallowBtn.setSelected(true);
		}
		
		ButtonGroup flyBtnGroup = new ButtonGroup();
		flyBtnGroup.add(flyAllowBtn);
		flyBtnGroup.add(flyDisallowBtn);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(12, 456, 276, 2);
		panel.add(separator_4);
		
		whitelistPlayerField = new JTextField();
		whitelistPlayerField.setBounds(92, 78, 116, 21);
		panel.add(whitelistPlayerField);
		whitelistPlayerField.setColumns(10);
		
		JLabel lblPlayername = new JLabel("�÷��̾� �� :");
		lblPlayername.setBounds(12, 81, 76, 15);
		panel.add(lblPlayername);
		
		JButton whitelistAddBtn = new JButton("�߰�");
		whitelistAddBtn.setBounds(12, 105, 63, 23);
		panel.add(whitelistAddBtn);
		
		JButton whitelistRmBtn = new JButton("����");
		whitelistRmBtn.setBounds(80, 105, 63, 23);
		panel.add(whitelistRmBtn);
		
		JButton whitelistResetBtn = new JButton("�ʱ�ȭ");
		whitelistResetBtn.setBounds(147, 105, 76, 23);
		panel.add(whitelistResetBtn);
		
		JButton whitelistBtn = new JButton("���");
		whitelistBtn.setBounds(225, 105, 63, 23);
		panel.add(whitelistBtn);
		
		Border gmBorder = BorderFactory.createEtchedBorder();
		gmBorder = BorderFactory.createTitledBorder(gmBorder, "�⺻ ���Ӹ��");
		JPanel gmPanel = new JPanel();
		gmPanel.setLayout(new GridLayout(0, 1));
		gmPanel.setBorder(gmBorder);
		gmPanel.setBounds(12, 468, 116, 115);
		
		JRadioButton survivalBtn = new JRadioButton("�����̹�");
		JRadioButton creativeBtn = new JRadioButton("ũ������Ƽ��");
		JRadioButton adventureBtn = new JRadioButton("����");
		JRadioButton spectatorBtn = new JRadioButton("������");
		
		ButtonGroup gmBtnGroup = new ButtonGroup();
		gmBtnGroup.add(survivalBtn);
		gmBtnGroup.add(creativeBtn);
		gmBtnGroup.add(adventureBtn);
		gmBtnGroup.add(spectatorBtn);
		
		int defaultGamemode = Server.getInstance().getDefaultGamemode();
		if (defaultGamemode == Player.SURVIVAL) {
			survivalBtn.setSelected(true);
		} else if (defaultGamemode == Player.CREATIVE) {
			creativeBtn.setSelected(true);
		} else if (defaultGamemode == Player.SPECTATOR) {
			spectatorBtn.setSelected(true);
		} else if (defaultGamemode == Player.ADVENTURE) {
			adventureBtn.setSelected(true);
		}
		
		gmPanel.add(survivalBtn);
		gmPanel.add(creativeBtn);
		gmPanel.add(adventureBtn);
		gmPanel.add(spectatorBtn);
		
		panel.add(gmPanel);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(12, 593, 276, 2);
		panel.add(separator_5);
		
		Border pvpBorder = BorderFactory.createEtchedBorder();
		pvpBorder = BorderFactory.createTitledBorder(pvpBorder, "pvp");
		JPanel pvpPanel = new JPanel();
		pvpPanel.setLayout(new GridLayout(0, 1));
		pvpPanel.setBorder(pvpBorder);
		pvpPanel.setBounds(12, 605, 76, 69);
		
		JRadioButton pvpOnBtn = new JRadioButton("���");
		JRadioButton pvpOffBtn = new JRadioButton("�����");
		
		ButtonGroup pvpBtnGroup = new ButtonGroup();
		pvpBtnGroup.add(pvpOnBtn);
		pvpBtnGroup.add(pvpOffBtn);
		
		if (Server.getInstance().getPropertyBoolean("pvp") == true) {
			pvpOnBtn.setSelected(true);
		} else {
			pvpOffBtn.setSelected(true);
		}
		
		pvpPanel.add(pvpOnBtn);
		pvpPanel.add(pvpOffBtn);
		
		panel.add(pvpPanel);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(12, 684, 276, 2);
		panel.add(separator_6);
		
		JButton btnPlayerlist = new JButton("�÷��̾� ���");
		btnPlayerlist.setBounds(12, 696, 131, 23);
		panel.add(btnPlayerlist);
		
		JButton btnPlayerManage = new JButton("�÷��̾� ����");
		btnPlayerManage.setBounds(12, 727, 131, 23);
		panel.add(btnPlayerManage);
		
		JButton btnSendmessage = new JButton("�޼��� ������");
		btnSendmessage.setBounds(12, 760, 131, 23);
		panel.add(btnSendmessage);
		
		getContentPane().add(scroll);
		
		
		whitelistOn.addActionListener(e -> Server.getInstance().setPropertyBoolean("white-list", true));
		whitelistOff.addActionListener(e -> Server.getInstance().setPropertyBoolean("white-list", false));
		whitelistAddBtn.addActionListener(e -> {
			Server.getInstance().addWhitelist(whitelistPlayerField.getText());
			JOptionPane.showMessageDialog(null, whitelistPlayerField.getText() + "�� ȭ��Ʈ����Ʈ�� �߰��߽��ϴ�.");
		});
		whitelistRmBtn.addActionListener(e -> {
			Server.getInstance().removeWhitelist(whitelistPlayerField.getText());
			JOptionPane.showMessageDialog(null, whitelistPlayerField.getText() + "�� ȭ��Ʈ����Ʈ���� �����߽��ϴ�.");
		});
		whitelistResetBtn.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(null, "������ ȭ��Ʈ����Ʈ�� �ʱ�ȭ�Ͻðڽ��ϱ�?");
			if (result == JOptionPane.OK_OPTION) {
				for (String player : Server.getInstance().getWhitelist().getAll().keySet()) {
					Server.getInstance().removeWhitelist(player);
				}
			}
			JOptionPane.showMessageDialog(null, "ȭ��Ʈ����Ʈ�� �ʱ�ȭ �߽��ϴ�.");
		});
		whitelistBtn.addActionListener(e -> {
			StringBuilder msg = new StringBuilder("");
			Server.getInstance().getWhitelist().getAll().keySet().parallelStream().forEach(value -> msg.append(value + "\n"));
			JOptionPane.showMessageDialog(null, msg);
		});
		achievementOnBtn.addActionListener(e -> Server.getInstance().setPropertyBoolean("announce-player-achievements", true));
		achievementOffBtn.addActionListener(e -> Server.getInstance().setPropertyBoolean("announce-player-achievements", false));
		btnProtectionApply.addActionListener(e -> {
			try {
				int protection = Integer.parseInt(spawnProtectionField.getText());
				Server.getInstance().setPropertyInt("spawn-protection", protection);
				JOptionPane.showMessageDialog(null, "���� ��ȣ ������ " + protection + "���� �����߽��ϴ�.");
			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, "������ ������ �Է� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			}
		});
		maxPlayerApplybutton.addActionListener(e -> {
			try {
				int maxPlayers = Integer.parseInt(maxPlayerField.getText());
				Server.getInstance().setPropertyInt("max-players", maxPlayers);
				JOptionPane.showMessageDialog(null, "�ִ� �÷��̾ "+ maxPlayers + "�� �����߽��ϴ�.");
			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, "������ �Է� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			}
		});
		flyAllowBtn.addActionListener(e -> Server.getInstance().setPropertyBoolean("allow-flight", true));
		flyDisallowBtn.addActionListener(e -> Server.getInstance().setPropertyBoolean("allow-flight", false));
		survivalBtn.addActionListener(e -> Server.getInstance().setPropertyInt("gamemode", Player.SURVIVAL));
		creativeBtn.addActionListener(e -> Server.getInstance().setPropertyInt("gamemode", Player.CREATIVE));
		adventureBtn.addActionListener(e -> Server.getInstance().setPropertyInt("gamemode", Player.ADVENTURE));
		spectatorBtn.addActionListener(e -> Server.getInstance().setPropertyInt("gamemode", Player.SPECTATOR));
		pvpOnBtn.addActionListener(e -> Server.getInstance().setPropertyBoolean("pvp", true));
		pvpOffBtn.addActionListener(e -> Server.getInstance().setPropertyBoolean("gamemode", false));
		btnPlayerlist.addActionListener(event -> {
			StringBuffer playerlist = new StringBuffer("");
			Server.getInstance().getOnlinePlayers().forEach((uuid, player) -> playerlist.append(player.getName() + "\n"));
			JOptionPane.showMessageDialog(null, playerlist);
		});
		btnPlayerManage.addActionListener(event -> {
			String name = JOptionPane.showInputDialog(null, "������ �÷��̾�� �Է�");
			if (name == null) {
				return;
			}
			Player player = Server.getInstance().getPlayer(name);
			if (player == null) {
				JOptionPane.showMessageDialog(null, "�� �÷��̾�� ������ �������� �ƴմϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			PlayerManageDialog dialog = new PlayerManageDialog(player);
			dialog.setVisible(true);
		});
		btnSendmessage.addActionListener(event -> {
			String msg = JOptionPane.showInputDialog("�������鿡�� �� ���� �Է��ϼ���.");
			if (msg == null) msg = "";
			Server.getInstance().broadcastMessage(new TranslationContainer(TextFormat.LIGHT_PURPLE + "%chat.type.announcement", new String[]{"����", msg}));
		});
		

		setVisible(true);
		pack();
	}
}
