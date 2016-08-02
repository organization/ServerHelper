package serverhelper.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.TranslationContainer;
import cn.nukkit.utils.TextFormat;

public class PlayerManageDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public PlayerManageDialog(Player player) {
		setBounds(100, 100, 187, 219);
		setTitle(player.getName() + "����");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnBan = new JButton("��");
		btnBan.setBounds(12, 10, 69, 23);
		contentPanel.add(btnBan);
		btnBan.addActionListener(event -> {
			int kind = JOptionPane.showOptionDialog(null, "�� ���� ����", "�˸�", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"�г��ӹ�", "�����ǹ�"}, "�г��ӹ�");
			if (kind == JOptionPane.YES_OPTION) {
				player.setBanned(true);
				JOptionPane.showMessageDialog(null, player.getName() + "�� �г��ӹ� �߽��ϴ�.");
			} else if (kind == JOptionPane.NO_OPTION) {
				Server.getInstance().getIPBans().addBan(player.getName());
				JOptionPane.showMessageDialog(null, player.getName() + "�� �����ǹ� �߽��ϴ�.");
			}
		});
		{
			JButton btnUnban = new JButton("������");
			btnUnban.setBounds(86, 10, 69, 23);
			contentPanel.add(btnUnban);
			btnUnban.addActionListener(event -> {
				player.setBanned(false);
				Server.getInstance().getIPBans().remove(player.getName());
				JOptionPane.showMessageDialog(null, player.getName() + "�� ���� �����߽��ϴ�.");
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
				if (JOptionPane.showConfirmDialog(null, "������ ��� �÷��̾ �� �÷��̾�� �̵���Ű�ڽ��ϱ�?", "�ȳ�", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					for (Player target : Server.getInstance().getOnlinePlayers().values()) {
						target.teleport(player);
					}
					JOptionPane.showMessageDialog(null, "��� �÷��̾ " + player.getName() + "���� �̵����׽��ϴ�.");
				}
			});
		}
		{
			JButton btnKick = new JButton("�߹�");
			btnKick.setBounds(12, 76, 69, 23);
			contentPanel.add(btnKick);
			btnKick.addActionListener(event -> {
				String reason = JOptionPane.showInputDialog("����");
				player.kick(reason);
			});
		}
		{
			JButton btnKill = new JButton("���̱�");
			btnKill.setBounds(86, 76, 69, 23);
			contentPanel.add(btnKill);
			btnKill.addActionListener(event -> {
				player.kill();
				JOptionPane.showMessageDialog(null, player.getName() + "�� �׿����ϴ�.");
			});
		}
		{
			JButton btnSendmessage = new JButton("�޼��� ������");
			btnSendmessage.setBounds(12, 109, 143, 23);
			contentPanel.add(btnSendmessage);
			btnSendmessage.addActionListener(event -> {
				String msg = JOptionPane.showInputDialog("���� �޼��� �Է�");
				player.sendMessage(new TranslationContainer(TextFormat.LIGHT_PURPLE + "%chat.type.announcement", new String[]{"����", msg}));
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
