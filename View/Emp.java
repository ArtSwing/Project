package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DAO;
import Model.Employee;
public class Emp extends JFrame {
	private JTable table;
	private JTextField txtsearch;
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
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < model.getRowCount();) {
					model.removeRow(0);
	            }
				 String s = txtsearch.getText();
				 System.out.println(s);
                 String sql = "select * from employee where empname Like '%"+s+"%'";
                 ArrayList<Model.Employee> bean = dao.getUserSearch(sql);
                 for (Model.Employee beans : bean) {
         			model.addRow(new Object[] { beans.getEmpid(), beans.getEmpname(), beans.getSex(),beans.getRank(),beans.getPhone(),beans.getSalary() });
         			System.out.println(beans);
         		}
                 
			}
		});
		txtsearch.setBounds(177, 106, 141, 48);
		getContentPane().add(txtsearch);
		txtsearch.setColumns(10);
		JTextField jTextField = new JTextField(20);
	
		
		
		
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
		
		JLabel label_1 = new JLabel("<-- \uC774\uB984\uC73C\uB85C \uAC80\uC0C9");
		label_1.setFont(new Font("굴림", Font.PLAIN, 30));
		label_1.setBounds(320, 104, 257, 49);
		getContentPane().add(label_1);
		
		
		dao = new DAO();
		ArrayList<Model.Employee> bean = dao.EMP_Select();

		
		for (Model.Employee beans : bean) {
			model.addRow(new Object[] { beans.getEmpid(), beans.getEmpname(), beans.getSex(),beans.getRank(),beans.getPhone(),beans.getSalary() });
			System.out.println(beans);
		}
		
		
	}
}
