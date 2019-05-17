package View;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
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
import DAO.practice.JTableMouseListener;
import Model.Employee;
public class Emp extends JFrame {
	private JTable table;
	private JTextField txtsearch;
	private JPanel contentPane;
	
	DAO dao;
	ArrayList<Employee> employees;
	private String colNames[] = {"���","�̸�","����","����","����ó","�޿�"};
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @ȣ��Ʈ IP : ��Ʈ : SID
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	public NumberFormat nf = NumberFormat.getInstance();
	private JScrollPane scrollPane;
	public final int SEARCH_empid = 0;
	public final int SEARCH_empname = 1;
	public final int SEARCH_sex = 2;
	public final int SEARCH_rank = 3;
	public final int SEARCH_phone = 4;
	public final int SEARCH_salary = 5;
	public final int SEARCH_NONE = 6;
	
	private JButton jBtnAddRow = null; // ���̺� ���� �߰� ��ư
	private JButton jBtnSaveRow = null; // ���̺� ���� ���� ��ư
	private JButton jBtnEditRow = null; // ���̺� ���� ���� ��ư
	private JButton jBtnDelRow = null; // ���̺� ���� ���� ��ư
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )

	public Emp() {
		setTitle("��������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 762, 458);
		JLabel label = new JLabel("��������");
		label.setFont(new Font("����", Font.PLAIN, 30));
		label.setBounds(192, 30, 177, 49);
		initialize();
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
         			model.addRow(new Object[] { beans.getEmpid(), beans.getEmpname(), beans.getSex(),beans.getRank(),beans.getPhone(),nf.format(beans.getSalary()) });
         			System.out.println(beans);
         		}
                 
			}
		});
		txtsearch.setBounds(177, 106, 141, 48);
		getContentPane().add(txtsearch);
		txtsearch.setColumns(10);
		JTextField jTextField = new JTextField(20);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 192, 599, 174);
		getContentPane().add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JLabel label_1 = new JLabel("<-- �̸����� �˻�");
		label_1.setFont(new Font("����", Font.PLAIN, 30));
		label_1.setBounds(320, 104, 257, 49);
		getContentPane().add(label_1);
		
		
		dao = new DAO();
		ArrayList<Model.Employee> bean = dao.EMP_Select();
		for (Model.Employee beans : bean) {
			model.addRow(new Object[] { beans.getEmpid(), beans.getEmpname(), beans.getSex(),beans.getRank(),beans.getPhone(),nf.format(beans.getSalary()) });
			System.out.println(beans);
		}
		
		
	}
	
	public class JTableMouseListener implements MouseListener { // ���콺�� ��������Ȯ���ϱ�
		public void mouseClicked(java.awt.event.MouseEvent e) { // ���õ� ��ġ�� ���� ���

			JTable jtable = (JTable) e.getSource();
			int row = jtable.getSelectedRow(); // ���õ� ���̺��� �ప
			int col = jtable.getSelectedColumn(); // ���õ� ���̺��� ����

			System.out.println(model.getValueAt(row, col)); // ���õ� ��ġ�� ���� ���� ���

		}

		public void mouseEntered(java.awt.event.MouseEvent e) {
		}

		public void mouseExited(java.awt.event.MouseEvent e) {
		}

		public void mousePressed(java.awt.event.MouseEvent e) {
		}

		public void mouseReleased(java.awt.event.MouseEvent e) {
		}
	}
	
	private void initialize() { // �׼��̺�Ʈ�� ��ư ������Ʈ ����

		// ���̺� ���� ���� �߰��ϴ� �κ�
		jBtnAddRow = new JButton();
		jBtnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				model2.addRow(new String[] { "", "", "","","","" }); // �����̺��� �ʱⰪ
			}
		});
		jBtnAddRow.setBounds(84, 395, 120, 25);
		jBtnAddRow.setText("�߰�");
		getContentPane().add(jBtnAddRow);

		// ���̺� ���� �����ϴ� �κ�
		jBtnSaveRow = new JButton();
		jBtnSaveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // ������ �ȵ� ���¸� -1����
				String query = "insert into EMPLOYEE(EMPid, EMPName,SEX,RANK,PHONE,SALARY) VALUES(?,?,?,?,?,?)";

				try {

					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "london", "london"); // DB ����
					pstmt = con.prepareStatement(query);

					// ����ǥ ������� �Է�.
					pstmt.setInt(1, Integer.parseInt(model2.getValueAt(row, 0).toString()));
					pstmt.setString(2, (String) model2.getValueAt(row, 1).toString());
					pstmt.setString(3, (String) model2.getValueAt(row, 2).toString());
					pstmt.setString(4, (String) model2.getValueAt(row, 3).toString());
					pstmt.setString(5, (String) model2.getValueAt(row, 4).toString());
					pstmt.setInt(6, Integer.parseInt(model2.getValueAt(row, 5).toString()));

					int cnt = pstmt.executeUpdate();
					System.out.println(cnt);
					// pstmt.executeUpdate(); create insert update delete
					// pstmt.executeQuery(); select
				} catch (Exception eeeeee) {
					eeeeee.printStackTrace();
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				model2.setRowCount(0); // ��ü ���̺� ȭ���� ������
				//select(); // ���� �� �ٽ� ��ü ������ �޾ƿ�.
				
				ArrayList<Model.Employee> bean = new DAO().EMP_Select();
                for (Model.Employee beans : bean) {
        			model.addRow(new Object[] { beans.getEmpid(), beans.getEmpname(), beans.getSex(),beans.getRank(),beans.getPhone(),nf.format(beans.getSalary()) });
        			System.out.println(beans);
        		}
			}
		});
		jBtnSaveRow.setBounds(398, 395, 120, 25);
		jBtnSaveRow.setText("����");
		getContentPane().add(jBtnSaveRow);
		
		
	

		// ���õ� ���̺� ���� �����ϴ� �κ�
		jBtnEditRow = new JButton();
		jBtnEditRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // ������ �ȵ� ���¸� -1����

				String query = "UPDATE EMPLOYEE SET Empid=?, Empname=?, Sex=?,Rank=?,Phone=?,Salary=? " + "where empid=?";

				try {
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "london", "london"); // DB ����
					pstmt = con.prepareStatement(query);

					// ����ǥ�� 6�� �̹Ƿ� 6�� ���� �Է�������Ѵ�.
					pstmt.setString(1, (String) model2.getValueAt(row, 0));
					pstmt.setString(2, (String) model2.getValueAt(row, 1));
					pstmt.setString(3, (String) model2.getValueAt(row, 2));
					pstmt.setString(4, (String) model2.getValueAt(row, 3));
					pstmt.setString(5, (String) model2.getValueAt(row, 4));
					pstmt.setString(6, (String) model2.getValueAt(row, 5));

					int cnt = pstmt.executeUpdate();
					// pstmt.executeUpdate(); create insert update delete
					// pstmt.executeQuery(); select
				} catch (Exception eeee) {
					System.out.println(eeee.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {
					}
				}
				model2.setRowCount(0); // ��ü ���̺� ȭ���� ������
				dao.EMP_Select(); // ���� �Ĵٽ� ��ü ������ �޾ƿ�.
				
			}
		});
		jBtnEditRow.setBounds(230, 395, 120, 25);
		jBtnEditRow.setText("����");
		getContentPane().add(jBtnEditRow);

		// ���õ� ���̺� ���� �����ϴ� �κ�
		jBtnDelRow = new JButton();
		jBtnDelRow.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // ������ �ȵ� ���¸� -1����
				String query = "delete from Employee where empid= ?";

				try {
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "london", "london"); // DB ����
					pstmt = con.prepareStatement(query);
					con.setAutoCommit(true); 
					// ����ǥ�� 1�� �̹Ƿ� 3�� ���� �Է�������Ѵ�.
//					pstmt.setString(1, (String) model2.getValueAt(row, 0));
					pstmt.setInt(1, Integer.parseInt(model2.getValueAt(row, 0).toString()));
					int cnt = pstmt.executeUpdate();
					// pstmt.executeUpdate(); create insert update delete
					// pstmt.executeQuery(); select
				} catch (Exception eeee) {
					System.out.println(eeee.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {
					}
				}
				model2.removeRow(row); // ���̺� ���� ���� ����
				 
			}
		});
		jBtnDelRow.setBounds(new Rectangle(561, 396, 97, 23));
		jBtnDelRow.setText("����");
		getContentPane().add(jBtnDelRow);
		
		JButton btnIntro = new JButton("Intro");
		btnIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntroPage.frame.setVisible(true);
				setVisible(false);
			}
		});
		btnIntro.setBounds(634, 56, 97, 23);
		getContentPane().add(btnIntro);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(774, 503);
	}
}
