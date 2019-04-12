package View;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;

public class Emp extends JFrame {
	private JTextField textField;
	private JTable table;
	private JButton button;
	private JTextField textField_1;
	public Emp() {
		setTitle("\uC9C1\uC6D0\uAD00\uB9AC");
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\uC9C1\uC6D0 \uAD00\uB9AC");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		label.setBounds(192, 30, 177, 49);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(192, 132, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uAC80\uC0C9");
		button.setBounds(334, 131, 97, 23);
		getContentPane().add(button);
		
		table = new JTable();
		table.setBounds(43, 164, 480, 174);
		getContentPane().add(table);
		
		textField_1 = new JTextField();
		textField_1.setBounds(192, 80, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
