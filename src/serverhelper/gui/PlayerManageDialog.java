package serverhelper.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.nukkit.Player;
import cn.nukkit.Server;

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
		{
			JButton btnUnban = new JButton("������");
			btnUnban.setBounds(86, 10, 69, 23);
			contentPanel.add(btnUnban);
		}
		{
			JButton btnTp = new JButton("tp");
			btnTp.setBounds(12, 43, 69, 23);
			contentPanel.add(btnTp);
		}
		{
			JButton btnTpall = new JButton("tpall");
			btnTpall.setBounds(86, 43, 69, 23);
			contentPanel.add(btnTpall);
		}
		{
			JButton btnKick = new JButton("�߹�");
			btnKick.setBounds(12, 76, 69, 23);
			contentPanel.add(btnKick);
		}
		{
			JButton btnKill = new JButton("���̱�");
			btnKill.setBounds(86, 76, 69, 23);
			contentPanel.add(btnKill);
		}
		{
			JButton btnSendmessage = new JButton("�޼��� ������");
			btnSendmessage.setBounds(12, 109, 143, 23);
			contentPanel.add(btnSendmessage);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
