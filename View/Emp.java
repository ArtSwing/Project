package View;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.DAO;
import Model.Employee;
import Model.Food;
import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
public class Emp extends JFrame {
	private JTable table;
	private JButton btn_search;
	private JTextField textField;
	private JPanel contentPane;
	DAO dao;
	ArrayList<Employee> employees;
	private String colNames[] = {"사번","이름","성별","직급","연락처","급여"};
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private JScrollPane scrollPane;
	public final int SEARCH_empid = 0;
	public final int SEARCH_empname = 1;
	public final int SEARCH_sex = 2;
	public final int SEARCH_rank = 3;
	public final int SEARCH_phone = 4;
	public final int SEARCH_salary = 5;
	public final int SEARCH_NONE = 6;
	

	public Emp() {
		setTitle("직원관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 762, 401);
		JLabel label = new JLabel("직원관리");
		label.setFont(new Font("굴림", Font.PLAIN, 30));
		label.setBounds(192, 30, 177, 49);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 System.out.println(e.getKeyChar()+" keyReleased key");
			}
		});
		textField.setBounds(192, 132, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		JTextField jTextField = new JTextField(20);
		btn_search = new JButton("검색");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_search.setBounds(334, 131, 97, 23);
		getContentPane().add(btn_search);
	
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 164, 599, 174);
		getContentPane().add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnIntro = new JButton("Intro");
		btnIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntroPage.frame.setVisible(true);
				setVisible(false);
			}
		});
		btnIntro.setBounds(545, 49, 97, 23);
		getContentPane().add(btnIntro);
		
		
		dao = new DAO();
		ArrayList<Model.Employee> bean = dao.EMP_Select();

		
		for (Model.Employee beans : bean) {
			model.addRow(new Object[] { beans.getEmpid(), beans.getEmpname(), beans.getSex(),beans.getRank(),beans.getPhone(),beans.getSalary() });
			System.out.println(beans);
		}
		
		
	}
}
