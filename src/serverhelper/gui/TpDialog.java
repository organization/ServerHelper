package serverhelper.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TpDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField xField;
	private JTextField yField;
	private JTextField zField;
	private JTextField playerField;
	private JTextField worldNameField;

	public TpDialog(Player player) {
		setBounds(100, 100, 302, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setTitle(player.getName() + "'s tp");
		
		JLabel lblX = new JLabel("x:");
		lblX.setBounds(12, 10, 16, 15);
		contentPanel.add(lblX);
		
		xField = new JTextField();
		xField.setBounds(29, 7, 81, 21);
		contentPanel.add(xField);
		xField.setColumns(10);
		
		JLabel lblY = new JLabel("y:");
		lblY.setBounds(12, 34, 16, 15);
		contentPanel.add(lblY);
		
		yField = new JTextField();
		yField.setColumns(10);
		yField.setBounds(29, 31, 81, 21);
		contentPanel.add(yField);
		
		JLabel lblZ = new JLabel("z:");
		lblZ.setBounds(12, 59, 16, 15);
		contentPanel.add(lblZ);
		
		zField = new JTextField();
		zField.setColumns(10);
		zField.setBounds(29, 56, 81, 21);
		contentPanel.add(zField);
		
		JLabel lblPlayername = new JLabel("�÷��̾��:");
		lblPlayername.setBounds(12, 122, 69, 15);
		contentPanel.add(lblPlayername);
		
		playerField = new JTextField();
		playerField.setColumns(10);
		playerField.setBounds(82, 119, 81, 21);
		contentPanel.add(playerField);
		
		JLabel lblWorldname = new JLabel("�����:");
		lblWorldname.setBounds(12, 84, 40, 15);
		contentPanel.add(lblWorldname);
		
		worldNameField = new JTextField();
		worldNameField.setColumns(10);
		worldNameField.setBounds(53, 81, 81, 21);
		contentPanel.add(worldNameField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(event -> {
					if (xField.getText().isEmpty() || yField.getText().isEmpty() || zField.getText().isEmpty()) {
						if (playerField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "��ǥ�� �÷��̾���� �Է��� �ּ���.", "����", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							Player target = Server.getInstance().getPlayer(playerField.getText());
							if (target == null) {
								JOptionPane.showMessageDialog(null, "�ش� �÷��̾�� ������ �������� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
								return;
							} else {
								player.teleport(target);
								JOptionPane.showMessageDialog(null, player.getName() + "�� " + target.getName() + "���� �̵����׽��ϴ�.");
							}
						}
					} else {
						Position pos;
						try {
							if (worldNameField.getText().isEmpty()) {
								int x, y, z;
								x = Integer.parseInt(xField.getText());
								y = Integer.parseInt(yField.getText());
								z = Integer.parseInt(zField.getText());
								pos = new Position(x, y, z, player.level);
							} else {
								Level targetLevel = Server.getInstance().getLevelByName(worldNameField.getText());
								if (targetLevel == null) {
									JOptionPane.showMessageDialog(null, "�ش� ����� �������� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
									return;
								}
								int x, y, z;
								x = Integer.parseInt(xField.getText());
								y = Integer.parseInt(yField.getText());
								z = Integer.parseInt(zField.getText());
								pos = new Position(x, y, z, targetLevel);
							}
							player.teleport(pos);
							JOptionPane.showMessageDialog(null, "�÷��̾ �ش���ǥ�� �̵����׽��ϴ�.");
						} catch (NumberFormatException exception) {
							JOptionPane.showMessageDialog(null, "��ǥ�� ���ڸ� �Է� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					this.setVisible(false);
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(e -> this.setVisible(false));
			}
		}
	}
}
