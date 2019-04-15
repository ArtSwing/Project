package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Component;
import java.beans.Beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import javax.swing.GroupLayout.Alignment;


public class Pos extends JFrame {
	static int idx = 0;
	private JPanel contentPane;
	private JScrollPane scrollPane; // 테이블 스크롤바 자동으로 생성되게 하기
	private JTable table;
	JButton button = new JButton();
	private JTextField textField;
	private String[] columnNames= new String[4];
	private String[][] rowData=new String[4][4] ;
	ArrayList<Food> foods;
	private static final long serialVersionUID = 1L;
	private String colNames[] = { "메뉴명", "가격", "상품코드","수량" }; // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // 테이블 데이터 모델 객체 생성
	DAO dao;

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
	

	
	public class myTableModel extends DefaultTableModel{
		
		String dat;
		
		JButton button=new JButton("");
		
		myTableModel(String tname){
			
			super(rowData,columnNames);
			
			dat=tname;
			
		}
		
		public boolean isCellEditable(int row,int cols){
			
			if( dat=="owntable"){
			
				if(cols==0){return false;}
			
			}
			
			return true;
			
		}

	}         
	/**
	 * Create the frame.
	 */
	
	private class BtnAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton a = (JButton)e.getSource();
			int id = Integer.parseInt(a.getName());
			Food food = null;
			for (Food temp_food : foods) {
				if (temp_food.getId() == id) {
					food = temp_food;
				}
			}
			
			model.addRow(new Object[] { food.getName(), food.getPrice(),food.getId() });
			System.out.println(food.toString());
		}
	}
	
	public Pos() {
		
		dao = new DAO();
		setTitle("London Bagutee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(4, 4, 4, 4));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		ArrayList<Model.Food> bean = new ArrayList<Model.Food>();

		
		for (Model.Food beans : bean) {
			System.out.println(beans);
		}
		
		
	
		columnNames=new String[] { "메뉴명", "가격", "상품코드","수량"  };
		
		rowData = new String[][]{
					{ "", "", "", "" }
					};
	
		
		TableModel model=new myTableModel("owntable");
		button.addActionListener(new ActionListener(){
		    
			@Override
			public void actionPerformed(ActionEvent event){
					
			}
		}
	);
		
		
		JButton btn_del = new JButton("\uC804\uCCB4\uCDE8\uC18C");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_del.setBounds(808, 373, 97, 36);
		contentPane.add(btn_del);
		
		JButton button_1 = new JButton("\uACB0\uC81C\uD558\uAE30");
		button_1.setBounds(624, 373, 97, 36);
		contentPane.add(button_1);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(662, 335, 194, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(581, 144, 351, 181);
		contentPane.add(scrollPane);
		
		table = new JTable(rowData,columnNames);
		table.addMouseListener(new JTableMouseListener()); // 테이블에 마우스리스너 연결
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
		tabbedPane.addTab("식빵", null, sikbbangpanel, null);
		sikbbangpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		tabbedPane.addTab("프랑스빵", null, francepanel, null);
		francepanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tabbedPane.addTab("도넛", null, donutpanel, null);
		donutpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tabbedPane.addTab("핫브레드", null, hotpanel, null);
		hotpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		foods = dao.Foods_Select();
		for (Food food : foods) {
			JButton btn = new JButton();
			btn.setName(String.valueOf(food.getId()));
			btn.addActionListener(new BtnAction());
			btn.setIcon(new ImageIcon(food.getImgUrl()));
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
			
			JMenu menu = new JMenu("관리자모드");
			menuBar.add(menu);
			
			JMenuItem menuItem = new JMenuItem("통계");
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
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(969,468);

		
	}
	

	private class JTableMouseListener implements MouseListener { // 마우스로 눌려진값확인하기
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) { // 선택된 위치의 값을 출력

			JTable jtable = (JTable) e.getSource();
			int row = jtable.getSelectedRow(); // 선택된 테이블의 행값
			int col = jtable.getSelectedColumn(); // 선택된 테이블의 열값

			System.out.println(model.getValueAt(row, col)); // 선택된 위치의 값을 얻어내서 출력

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
