package serverhelper.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

public class PlayerManageDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public PlayerManageDialog(Player player) {
		setBounds(100, 100, 187, 219);
		setTitle(player.getName() + "관리");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnBan = new JButton("밴");
		btnBan.setBounds(12, 10, 69, 23);
		contentPanel.add(btnBan);
		btnBan.addActionListener(event -> {
			int kind = JOptionPane.showOptionDialog(null, "밴 종류 선택", "알림", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"닉네임밴", "아이피밴"}, "닉네임밴");
			if (kind == JOptionPane.YES_OPTION) {
				player.setBanned(true);
				JOptionPane.showMessageDialog(null, player.getName() + "을 닉네임밴 했습니다.");
			} else if (kind == JOptionPane.NO_OPTION) {
				Server.getInstance().getIPBans().addBan(player.getName());
				JOptionPane.showMessageDialog(null, player.getName() + "을 아이피밴 했습니다.");
			}
		});
		{
			JButton btnUnban = new JButton("밴해제");
			btnUnban.setBounds(86, 10, 69, 23);
			contentPanel.add(btnUnban);
			btnUnban.addActionListener(event -> {
				player.setBanned(false);
				Server.getInstance().getIPBans().remove(player.getName());
				JOptionPane.showMessageDialog(null, player.getName() + "의 밴을 해제했습니다.");
			});
		}
		{
			JButton btnTp = new JButton("tp");
			btnTp.setBounds(12, 43, 69, 23);
			contentPanel.add(btnTp);
			btnTp.addActionListener(event -> {
				TpDialog dialog = new TpDialog(player);
				dialog.setVisible(true);
			});
		}
		{
			JButton btnTpall = new JButton("tpall");
			btnTpall.setBounds(86, 43, 69, 23);
			contentPanel.add(btnTpall);
			btnTpall.addActionListener(event -> {
				if (JOptionPane.showConfirmDialog(null, "정말로 모든 플레이어를 이 플레이어에게 이동시키겠습니까?", "안내", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					for (Player target : Server.getInstance().getOnlinePlayers().values()) {
						target.teleport(player);
					}
					JOptionPane.showMessageDialog(null, "모든 플레이어를 " + player.getName() + "에게 이동시켰습니다.");
				}
			});
		}
		{
			JButton btnKick = new JButton("추방");
			btnKick.setBounds(12, 76, 69, 23);
			contentPanel.add(btnKick);
			btnKick.addActionListener(event -> {
				String reason = JOptionPane.showInputDialog("이유");
				player.kick(reason);
			});
		}
		{
			JButton btnKill = new JButton("죽이기");
			btnKill.setBounds(86, 76, 69, 23);
			contentPanel.add(btnKill);
			btnKill.addActionListener(event -> {
				player.kill();
				JOptionPane.showMessageDialog(null, player.getName() + "을 죽였습니다.");
			});
		}
		{
			JButton btnSendmessage = new JButton("메세지 보내기");
			btnSendmessage.setBounds(12, 109, 143, 23);
			contentPanel.add(btnSendmessage);
			btnSendmessage.addActionListener(event -> {
				String msg = JOptionPane.showInputDialog("보낼 메세지 입력");
				player.sendMessage(new TranslationContainer(TextFormat.LIGHT_PURPLE + "%chat.type.announcement", new String[]{"서버", msg}));
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(e -> this.setVisible(false));
			}
		}
	}
}