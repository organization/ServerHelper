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
		whitelistBorder = BorderFactory.createTitledBorder(whitelistBorder, "화이트리스트");
		JPanel wlBorderPanel = new JPanel();
		wlBorderPanel.setLayout(new GridLayout(0, 1));
		wlBorderPanel.setBorder(whitelistBorder);
		wlBorderPanel.setBounds(12, 10, 94, 61);
		
		JRadioButton whitelistOn = new JRadioButton("켜기");
		wlBorderPanel.add(whitelistOn);
		
		JRadioButton whitelistOff = new JRadioButton("끄기");
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
		achieveBorder = BorderFactory.createTitledBorder(achieveBorder, "도전과제");
		JPanel achievePanel = new JPanel();
		achievePanel.setLayout(new GridLayout(0, 1));
		achievePanel.setBorder(achieveBorder);
		achievePanel.setBounds(12, 150, 105, 69);
		
		JRadioButton achievementOnBtn = new JRadioButton("켜기");
		achievementOnBtn.setBounds(12, 244, 49, 23);
		achievePanel.add(achievementOnBtn);
		
		JRadioButton achievementOffBtn = new JRadioButton("끄기");
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
		
		JLabel lblSpawn = new JLabel("스폰보호 범위");
		lblSpawn.setBounds(12, 241, 83, 15);
		panel.add(lblSpawn);
		
		spawnProtectionField = new JTextField();
		spawnProtectionField.setBounds(12, 266, 76, 21);
		panel.add(spawnProtectionField);
		spawnProtectionField.setColumns(10);
		spawnProtectionField.setText(Server.getInstance().getPropertyString("spawn-protection"));
		
		JButton btnProtectionApply = new JButton("적용");
		btnProtectionApply.setBounds(100, 265, 63, 23);
		panel.add(btnProtectionApply);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 297, 276, 2);
		panel.add(separator_2);
		
		JLabel lblPlayer = new JLabel("최대 플레이어 수");
		lblPlayer.setBounds(12, 309, 105, 15);
		panel.add(lblPlayer);
		
		maxPlayerField = new JTextField();
		maxPlayerField.setBounds(12, 334, 76, 21);
		panel.add(maxPlayerField);
		maxPlayerField.setColumns(10);
		maxPlayerField.setText(String.valueOf(Server.getInstance().getMaxPlayers()));
		
		JButton maxPlayerApplybutton = new JButton("적용");
		maxPlayerApplybutton.setBounds(100, 334, 63, 23);
		panel.add(maxPlayerApplybutton);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 365, 276, 2);
		panel.add(separator_3);
		
		Border flyBorder = BorderFactory.createEtchedBorder();
		flyBorder = BorderFactory.createTitledBorder(flyBorder, "날기");
		JPanel flyPanel = new JPanel();
		flyPanel.setLayout(new GridLayout(0, 1));
		flyPanel.setBorder(flyBorder);
		flyPanel.setBounds(12, 377, 105, 69);
		
		JRadioButton flyAllowBtn = new JRadioButton("허용");
		JRadioButton flyDisallowBtn = new JRadioButton("비허용");
		
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
		
		JLabel lblPlayername = new JLabel("플레이어 명 :");
		lblPlayername.setBounds(12, 81, 76, 15);
		panel.add(lblPlayername);
		
		JButton whitelistAddBtn = new JButton("추가");
		whitelistAddBtn.setBounds(12, 105, 63, 23);
		panel.add(whitelistAddBtn);
		
		JButton whitelistRmBtn = new JButton("제거");
		whitelistRmBtn.setBounds(80, 105, 63, 23);
		panel.add(whitelistRmBtn);
		
		JButton whitelistResetBtn = new JButton("초기화");
		whitelistResetBtn.setBounds(147, 105, 76, 23);
		panel.add(whitelistResetBtn);
		
		JButton whitelistBtn = new JButton("목록");
		whitelistBtn.setBounds(225, 105, 63, 23);
		panel.add(whitelistBtn);
		
		Border gmBorder = BorderFactory.createEtchedBorder();
		gmBorder = BorderFactory.createTitledBorder(gmBorder, "기본 게임모드");
		JPanel gmPanel = new JPanel();
		gmPanel.setLayout(new GridLayout(0, 1));
		gmPanel.setBorder(gmBorder);
		gmPanel.setBounds(12, 468, 116, 115);
		
		JRadioButton survivalBtn = new JRadioButton("서바이벌");
		JRadioButton creativeBtn = new JRadioButton("크리에이티브");
		JRadioButton adventureBtn = new JRadioButton("모험");
		JRadioButton spectatorBtn = new JRadioButton("관전자");
		
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
		
		JRadioButton pvpOnBtn = new JRadioButton("허용");
		JRadioButton pvpOffBtn = new JRadioButton("비허용");
		
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
		
		JButton btnPlayerlist = new JButton("플레이어 목록");
		btnPlayerlist.setBounds(12, 696, 131, 23);
		panel.add(btnPlayerlist);
		
		JButton btnPlayerManage = new JButton("플레이어 관리");
		btnPlayerManage.setBounds(12, 727, 131, 23);
		panel.add(btnPlayerManage);
		
		JButton btnSendmessage = new JButton("메세지 보내기");
		btnSendmessage.setBounds(12, 760, 131, 23);
		panel.add(btnSendmessage);
		
		getContentPane().add(scroll);
		
		
		whitelistOn.addActionListener(e -> Server.getInstance().setPropertyBoolean("white-list", true));
		whitelistOff.addActionListener(e -> Server.getInstance().setPropertyBoolean("white-list", false));
		whitelistAddBtn.addActionListener(e -> {
			Server.getInstance().addWhitelist(whitelistPlayerField.getText());
			JOptionPane.showMessageDialog(null, whitelistPlayerField.getText() + "를 화이트리스트에 추가했습니다.");
		});
		whitelistRmBtn.addActionListener(e -> {
			Server.getInstance().removeWhitelist(whitelistPlayerField.getText());
			JOptionPane.showMessageDialog(null, whitelistPlayerField.getText() + "를 화이트리스트에서 제거했습니다.");
		});
		whitelistResetBtn.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(null, "정말로 화이트리스트를 초기화하시겠습니까?");
			if (result == JOptionPane.OK_OPTION) {
				for (String player : Server.getInstance().getWhitelist().getAll().keySet()) {
					Server.getInstance().removeWhitelist(player);
				}
			}
			JOptionPane.showMessageDialog(null, "화이트리스트를 초기화 했습니다.");
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
				JOptionPane.showMessageDialog(null, "스폰 보호 범위를 " + protection + "으로 설정했습니다.");
			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, "범위는 정수만 입력 가능합니다.", "에러", JOptionPane.ERROR_MESSAGE);
			}
		});
		maxPlayerApplybutton.addActionListener(e -> {
			try {
				int maxPlayers = Integer.parseInt(maxPlayerField.getText());
				Server.getInstance().setPropertyInt("max-players", maxPlayers);
				JOptionPane.showMessageDialog(null, "최대 플레이어를 "+ maxPlayers + "로 설정했습니다.");
			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, "정수만 입력 가능합니다.", "에러", JOptionPane.ERROR_MESSAGE);
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
			String name = JOptionPane.showInputDialog(null, "관리할 플레이어명 입력");
			if (name == null) {
				return;
			}
			Player player = Server.getInstance().getPlayer(name);
			if (player == null) {
				JOptionPane.showMessageDialog(null, "이 플레이어는 서버에 접속중이 아닙니다.", "에러", JOptionPane.ERROR_MESSAGE);
				return;
			}
			PlayerManageDialog dialog = new PlayerManageDialog(player);
			dialog.setVisible(true);
		});
		btnSendmessage.addActionListener(event -> {
			String msg = JOptionPane.showInputDialog("서버원들에게 할 말을 입력하세요.");
			if (msg == null) msg = "";
			Server.getInstance().broadcastMessage(new TranslationContainer(TextFormat.LIGHT_PURPLE + "%chat.type.announcement", new String[]{"서버", msg}));
		});
		

		setVisible(true);
		pack();
	}
}
