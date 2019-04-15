package View;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.DAO;

import java.awt.Font;
import javax.swing.JTable;

public class Emp extends JFrame {
	private JTable table;
	private JButton button;
	private JTextField textField;
	DAO dao;
	private String colNames[] = {"사번","이름","성별","직급","연락처","급여"};
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	
	public Emp() {
		setTitle("\uC9C1\uC6D0\uAD00\uB9AC");
		getContentPane().setLayout(null);
		setBounds(100, 100, 762, 401);
		JLabel label = new JLabel("\uC9C1\uC6D0 \uAD00\uB9AC");
		label.setFont(new Font("굴림", Font.PLAIN, 30));
		label.setBounds(192, 30, 177, 49);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(192, 132, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uAC80\uC0C9");
		button.setBounds(334, 131, 97, 23);
		getContentPane().add(button);
		
		table = new JTable(model);
		table.setBounds(43, 164, 480, 174);
		getContentPane().add(table);
	}
}
