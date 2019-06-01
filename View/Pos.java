package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Component;
import java.beans.Beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import DAO.DAO;
import DAO.practice;
import DAO.practice.JTableMouseListener;
import Model.Change;
import Model.Food;
import Model.Sale;

import javax.swing.GroupLayout.Alignment;

public class Pos extends JFrame {
	static int idx = 0;
	private JPanel contentPane;
	private JScrollPane scrollPane; // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�
	private JTable table;
	JButton button = new JButton();
	private String[] columnNames = new String[4];
	private String[][] rowData = new String[4][4];
	ArrayList<Food> foods;
	private static final long serialVersionUID = 1L;
	private String colNames[] = { "�޴���", "����", "��ǰ�ڵ�", "����" }; // ���̺� �÷� ����
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // ���̺� ������ �� ��ü ����
	DAO dao;
	public NumberFormat nf = NumberFormat.getInstance();
	public static int sumprice = 0;
	public static JLabel pricelbl;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @ȣ��Ʈ IP : ��Ʈ : SID
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pos frame = new Pos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class myTableModel extends DefaultTableModel {

		String dat;

		JButton button = new JButton("");

		myTableModel(String tname) {

			super(rowData, columnNames);

			dat = tname;

		}

		public boolean isCellEditable(int row, int cols) {

			if (dat == "owntable") {

				if (cols == 0) {
					return false;
				}

			}

			return true;

		}

	}

	/**
	 * Create the frame.
	 */

	public Pos() {

		dao = new DAO();
		setTitle("London Bagutee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(4, 4, 4, 4));
		setContentPane(contentPane);
		pricelbl = new JLabel("0\uC6D0");
		pricelbl.setBounds(731, 346, 57, 15);
		contentPane.add(pricelbl);
		contentPane.setLayout(null);

		ArrayList<Model.Food> bean = new ArrayList<Model.Food>();

		for (Model.Food beans : bean) {
			System.out.println(beans);
		}

		columnNames = new String[] { "�޴���", "����", "��ǰ�ڵ�", "����" };

		rowData = new String[][] { { "", "", "", "" } };

		TableModel model = new myTableModel("owntable");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

			}
		});

		JButton btn_del = new JButton("\uC804\uCCB4\uCDE8\uC18C");
		btn_del.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				pricelbl.setText("0��");
				sumprice = 0;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);

			}
		});
		btn_del.setBounds(808, 373, 97, 36);
		contentPane.add(btn_del);

		JButton btn_gyul = new JButton("\uACB0\uC81C\uD558\uAE30");
		JPanel p = new JPanel();
		JTextField tf = new JTextField(10);
		add(tf);
		btn_gyul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����â", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					tf.setText("Just Closed without Selection");
				} else if (result == JOptionPane.YES_OPTION) {
					tf.setText("Yes");
					System.out.println("test");
					
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				    int nRow = dtm.getRowCount();

				    ArrayList<Sale> sales = new ArrayList<Sale>();
				    for (int i = 0 ; i < nRow ; i++) {
				    	for (Food food : foods) {
							if ((int) dtm.getValueAt(i, 2) == food.getId()) {
								System.out.println(food);
								Sale sale = new Sale();
								sale.setSfoodid(food.getId());
								sale.setSfoodname(food.getName());
								sale.setSprice(food.getPrice());
								sale.setStabid(food.getTabid());
								sale.setSamount(Integer.parseInt((String) dtm.getValueAt(i, 3)));
								sales.add(sale);
							}
						}
				    }
				    boolean d = dao.Sale_Insert(sales);
				    System.out.println(d);
					pricelbl.setText("0��");
					sumprice = 0;
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setNumRows(0);
				    
				    
				} else {
					tf.setText("No");
				}
			}
		});

		btn_gyul.setBounds(624, 373, 97, 36);
		contentPane.add(btn_gyul);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(581, 144, 351, 181);
		contentPane.add(scrollPane);

		table = new JTable(rowData, columnNames);
		table.addMouseListener(new JTableMouseListener()); // ���̺� ���콺������ ����
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setToolTipText("");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(83, 79, 446, 265);
		contentPane.add(tabbedPane);

		JPanel sikbbangpanel = new JPanel();
		JPanel francepanel = new JPanel();
		JPanel donutpanel = new JPanel();
		JPanel hotpanel = new JPanel();

		sikbbangpanel.setToolTipText("");
		tabbedPane.addTab("�Ļ�", null, sikbbangpanel, null);
		sikbbangpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tabbedPane.addTab("��������", null, francepanel, null);
		francepanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tabbedPane.addTab("����", null, donutpanel, null);
		donutpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tabbedPane.addTab("�ֺ극��", null, hotpanel, null);
		hotpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		foods = dao.Foods_Select();
		for (Food food : foods) {
			JButton btn = new JButton();
			btn.setName(String.valueOf(food.getId()));
			btn.addActionListener(new BtnAction());

			ImageIcon img = new ImageIcon(food.getImgUrl());
			Image i = img.getImage().getScaledInstance(70, 70, 0);
			ImageIcon i2 = new ImageIcon(i);
			btn.setIcon(i2);
			switch (food.getTabid()) {
			case 1:
				sikbbangpanel.add(btn);
				break;
			case 2:
				francepanel.add(btn);
				break;
			case 3:
				donutpanel.add(btn);
				break;
			case 4:
				hotpanel.add(btn);
				break;
			default:
				break;
			}
		}

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 993, 36);
		contentPane.add(menuBar);

		JMenu menu = new JMenu("�����ڸ��");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("���");
		menu.add(menuItem);

		JButton btnIntro = new JButton("intro");
		btnIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntroPage.frame.setVisible(true);
				setVisible(false);
			}
		});
		btnIntro.setBounds(835, 46, 97, 23);
		contentPane.add(btnIntro);

		JLabel label = new JLabel("\uCD1D \uACB0\uC81C\uAE08\uC561 : ");
		label.setToolTipText("");
		label.setBounds(624, 346, 97, 15);
		contentPane.add(label);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(969, 468);

	}

	private int cnt = 0;
	private int rowcnt = 0;

	private class BtnAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int flag = 0;

			JButton a = (JButton) e.getSource();
			int id = Integer.parseInt(a.getName());
			int i;
			Food food = null;
			for (Food temp_food : foods) {
				if (temp_food.getId() == id) {
					food = temp_food;
					sumprice += dao.Foods_price(temp_food.getId());
					pricelbl.setText(String.valueOf(nf.format(sumprice)) + "��");
				}
			}
			table.setModel(model);
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			int nRow = m.getRowCount(), nCol = m.getColumnCount();
			System.out.println("n" + nRow + "n" + nCol);
			Object[][] tableData = new Object[nRow][nCol];
			int temp, num = 0;
			String p;
			for (i = 0; i < nRow; i++) {

				if (m.getValueAt(i, 2).equals(food.getId())) {
					num = Integer.parseInt((String) m.getValueAt(i, 3));
					System.out.println(num);
					p = Integer.toString(num + 1);
					// m.setValueAt(p, i, 3);
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				m.insertRow(0, new Object[] { food.getName(), nf.format(food.getPrice()), food.getId(), "1" });
				table.updateUI();
			} else {
				// int num = Integer.parseInt((String) m.getValueAt(i, 3));
				System.out.println(num);
				p = Integer.toString(num + 1);
				m.setValueAt(p, i, 3);
			}
			flag = 1;
		}
	}

	private class JTableMouseListener implements MouseListener { // ���콺�� ��������Ȯ���ϱ�
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) { // ���õ� ��ġ�� ���� ���

			JTable jtable = (JTable) e.getSource();
			int row = jtable.getSelectedRow(); // ���õ� ���̺��� �ప
			int col = jtable.getSelectedColumn(); // ���õ� ���̺��� ����

			System.out.println(model.getValueAt(row, col)); // ���õ� ��ġ�� ���� ���� ���

		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
