package DAO;

import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import Model.Food;
import View.IntroPage;

public class practice extends JFrame {

	// DB에서 스윙 화면으로 테이블 값 가져오기(select) , 저장하기(insert), 수정하기(update), 삭제하기(delete)
	private static final long serialVersionUID = 1L;
	private JButton jBtnAddRow = null; // 테이블 한줄 추가 버튼
	private JButton jBtnSaveRow = null; // 테이블 한줄 저장 버튼
	private JButton jBtnEditRow = null; // 테이블 한줄 저장 버튼
	private JButton jBtnDelRow = null; // 테이블 한줄 삭제 벅튼
	private JTable table;
	private JScrollPane scrollPane; // 테이블 스크롤바 자동으로 생성되게 하기
	public NumberFormat nf = NumberFormat.getInstance();
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @호스트 IP : 포트 : SID
	private String colNames[] = { "메뉴번호", "메뉴명", "가격","이미지주소","탭번호" }; // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // 테이블 데이터 모델 객체 생성

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )
	
	DAO dao;
	
	public practice() {
		setTitle("\uBA54\uB274\uAD00\uB9AC");
		dao = new DAO();
		
		getContentPane().setLayout(null); // 레이아웃 배치관리자 삭제
		table = new JTable(model); // 테이블에 모델객체 삽입
		table.addMouseListener(new JTableMouseListener()); // 테이블에 마우스리스너 연결
		scrollPane = new JScrollPane(table); // 테이블에 스크롤 생기게 하기
		scrollPane.setSize(500, 200);
		getContentPane().add(scrollPane);
		initialize();
		ArrayList<Food> foods = dao.Foods_Select();
		for (Food food : foods) {

			model.addRow(new Object[] { food.getId(), food.getName(), nf.format(food.getPrice()),food.getImgUrl(),food.getTabid() });
		}

	}

	public class JTableMouseListener implements MouseListener { // 마우스로 눌려진값확인하기
		public void mouseClicked(java.awt.event.MouseEvent e) { // 선택된 위치의 값을 출력

			JTable jtable = (JTable) e.getSource();
			int row = jtable.getSelectedRow(); // 선택된 테이블의 행값
			int col = jtable.getSelectedColumn(); // 선택된 테이블의 열값

			System.out.println(model.getValueAt(row, col)); // 선택된 위치의 값을 얻어내서 출력

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

	private void initialize() { // 액션이벤트와 버튼 컴포넌트 설정

		// 테이블 새로 한줄 추가하는 부분
		jBtnAddRow = new JButton();
		jBtnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				model2.addRow(new String[] { "", "", "","","" }); // 새테이블의 초기값
			}
		});
		jBtnAddRow.setBounds(30, 222, 120, 25);
		jBtnAddRow.setText("추가");
		getContentPane().add(jBtnAddRow);

		// 테이블 새로 저장하는 부분
		jBtnSaveRow = new JButton();
		jBtnSaveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴
				String query = "insert into Food(Foodid, Foodname,Price,Imgurl,Tabid) VALUES(?,?,?,?,?)";

				try {

					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "london", "london"); // DB 연결
					pstmt = con.prepareStatement(query);

					// 물음표가 3개 이므로 3개 각각 입력해줘야한다.
					pstmt.setInt(1, Integer.parseInt(model2.getValueAt(row, 0).toString()));
					pstmt.setString(2, (String) model2.getValueAt(row, 1).toString());
					pstmt.setInt(3, Integer.parseInt(model2.getValueAt(row, 2).toString()));
					pstmt.setString(4, (String) model2.getValueAt(row, 3).toString());
					pstmt.setInt(5, Integer.parseInt(model2.getValueAt(row, 4).toString()));

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
				model2.setRowCount(0); // 전체 테이블 화면을 지워줌
				//select(); // 저장 후 다시 전체 값들을 받아옴.
				ArrayList<Food> foods = new DAO().Foods_Select();
				for (Food food : foods) {

					model.addRow(new Object[] { food.getId(), food.getName(), food.getPrice(),food.getImgUrl(),food.getTabid() });
				}
			}
		});
		jBtnSaveRow.setBounds(182, 222, 120, 25);
		jBtnSaveRow.setText("저장");
		getContentPane().add(jBtnSaveRow);

		// 선택된 테이블 한줄 수정하는 부분
		jBtnEditRow = new JButton();
		jBtnEditRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴

				String query = "UPDATE Food SET Foodid=?, Foodname=?, price=?,Imgurl=?,Tabid=? " + "where foodid=?";

				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "london", "london"); // DB 연결
					pstmt = con.prepareStatement(query);

					// 물음표가 5개 이므로 5개 각각 입력해줘야한다.
					pstmt.setString(1, (String) model2.getValueAt(row, 0));
					pstmt.setString(2, (String) model2.getValueAt(row, 1));
					pstmt.setString(3, (String) model2.getValueAt(row, 2));
					pstmt.setString(4, (String) model2.getValueAt(row, 3));
					pstmt.setString(5, (String) model2.getValueAt(row, 4));

					int cnt = pstmt.executeUpdate();
					// pstmt.executeUpdate(); create insert update delete
					// pstmt.executeQuery(); select
				} catch (Exception eeeee) {
					System.out.println(eeeee.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {
					}
				}
				model2.setRowCount(0); // 전체 테이블 화면을 지워줌
				dao.Foods_Select(); // 수정 후다시 전체 값들을 받아옴.
				
			}
		});
		jBtnEditRow.setBounds(182, 270, 120, 25);
		jBtnEditRow.setText("수정");
		getContentPane().add(jBtnEditRow);

		// 선택된 테이블 한줄 삭제하는 부분
		jBtnDelRow = new JButton();
		jBtnDelRow.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴
				String query = "delete from Food where foodid= ?";

				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "london", "london"); // DB 연결
					pstmt = con.prepareStatement(query);
					con.setAutoCommit(true); 

					// 물음표가 1개 이므로 4개 각각 입력해줘야한다.
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
				model2.removeRow(row); // 테이블 상의 한줄 삭제
			}
		});
		jBtnDelRow.setBounds(new Rectangle(320, 222, 120, 25));
		jBtnDelRow.setText("삭제");
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