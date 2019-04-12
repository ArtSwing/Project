package DAO;

import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import Model.Food;
import View.IntroPage;

public class practice extends JFrame {

	// DB���� ���� ȭ������ ���̺� �� ��������(select) , �����ϱ�(insert), �����ϱ�(update), �����ϱ�(delete)
	private static final long serialVersionUID = 1L;
	private JButton jBtnAddRow = null; // ���̺� ���� �߰� ��ư
	private JButton jBtnSaveRow = null; // ���̺� ���� ���� ��ư
	private JButton jBtnEditRow = null; // ���̺� ���� ���� ��ư
	private JButton jBtnDelRow = null; // ���̺� ���� ���� ��ư
	private JTable table;
	private JScrollPane scrollPane; // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @ȣ��Ʈ IP : ��Ʈ : SID
	private String colNames[] = { "�޴���ȣ", "�޴���", "����" }; // ���̺� �÷� ����
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // ���̺� ������ �� ��ü ����

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )

	public practice() {
		getContentPane().setLayout(null); // ���̾ƿ� ��ġ������ ����
		table = new JTable(model); // ���̺� �𵨰�ü ����
		table.addMouseListener(new JTableMouseListener()); // ���̺� ���콺������ ����
		scrollPane = new JScrollPane(table); // ���̺� ��ũ�� ����� �ϱ�
		scrollPane.setSize(500, 200);
		getContentPane().add(scrollPane);
		initialize();
		ArrayList<Food> foods = select();
		for (Food food : foods) {

			model.addRow(new Object[] { food.getId(), food.getName(), food.getPrice() });
		}

	}

	private class JTableMouseListener implements MouseListener { // ���콺�� ��������Ȯ���ϱ�
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

	private ArrayList<Food> select() { // ���̺� ���̱� ���� �˻�

		String query = "select * from Food Order by foodid";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
			ArrayList<Food> foods = new ArrayList<Food>();

			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
				Food food = new Food();
				food.setId(Integer.parseInt(rs.getString("Foodid")));
				food.setName(rs.getString("Foodname"));
				food.setPrice(Integer.parseInt(rs.getString("price")));
				foods.add(food);

				// model.addRow(new
				// Object[]{rs.getString("Foodid"),rs.getString("Foodname"),rs.getString("price")});
			}

			for (Food food : foods) {
				System.out.println(food.toString());
			}
			return foods;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // ��ü ������ �ݴ� ������ ����� ��ü�� �ݾ��ش�.
			} catch (Exception e2) {
			}
		}
		return null;
	}

	private void initialize() { // �׼��̺�Ʈ�� ��ư ������Ʈ ����

		// ���̺� ���� ���� �߰��ϴ� �κ�
		jBtnAddRow = new JButton();
		jBtnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				model2.addRow(new String[] { "", "", "" }); // �����̺��� �ʱⰪ
			}
		});
		jBtnAddRow.setBounds(30, 222, 120, 25);
		jBtnAddRow.setText("�߰�");
		getContentPane().add(jBtnAddRow);

		// ���̺� ���� �����ϴ� �κ�
		jBtnSaveRow = new JButton();
		jBtnSaveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // ������ �ȵ� ���¸� -1����
				String query = "insert into Food(Foodid, Foodname,Price) VALUES(?,?,?)";

				try {

					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "london", "london"); // DB ����
					pstmt = con.prepareStatement(query);

					// ����ǥ�� 3�� �̹Ƿ� 3�� ���� �Է�������Ѵ�.
					pstmt.setInt(1, Integer.parseInt(model2.getValueAt(row, 0).toString()));
					pstmt.setString(2, (String) model2.getValueAt(row, 1).toString());
					pstmt.setInt(3, Integer.parseInt(model2.getValueAt(row, 2).toString()));

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
					}
				}
				model2.setRowCount(0); // ��ü ���̺� ȭ���� ������
				select(); // ���� �� �ٽ� ��ü ������ �޾ƿ�.
			}
		});
		jBtnSaveRow.setBounds(182, 222, 120, 25);
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

				String query = "UPDATE Food SET Foodid=?, Foodname=?, price=? " + "where id=?";

				try {
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "london", "london"); // DB ����
					pstmt = con.prepareStatement(query);

					// ����ǥ�� 3�� �̹Ƿ� 3�� ���� �Է�������Ѵ�.
					pstmt.setString(1, (String) model2.getValueAt(row, 0));
					pstmt.setString(2, (String) model2.getValueAt(row, 1));
					pstmt.setString(3, (String) model2.getValueAt(row, 2));

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
				select(); // ���� �Ĵٽ� ��ü ������ �޾ƿ�.
				
			}
		});
		jBtnEditRow.setBounds(182, 270, 120, 25);
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
				String query = "delete from Food where id= ?";

				try {
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "london", "london"); // DB ����
					pstmt = con.prepareStatement(query);

					// ����ǥ�� 1�� �̹Ƿ� 3�� ���� �Է�������Ѵ�.
					pstmt.setString(1, (String) model2.getValueAt(row, 0));
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
		jBtnDelRow.setBounds(new Rectangle(320, 222, 120, 25));
		jBtnDelRow.setText("����");
		getContentPane().add(jBtnDelRow);
		
		JButton btnIntro = new JButton("Intro");
		btnIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntroPage.frame.setVisible(true);
				setVisible(false);
			}
		});
		btnIntro.setBounds(192, 316, 97, 23);
		getContentPane().add(btnIntro);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(540, 400);
	}

}