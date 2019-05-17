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
	private String colNames[] = {"사번","이름","성별","직급","연락처","급여"};
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @호스트 IP : 포트 : SID
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
	
	private JButton jBtnAddRow = null; // 테이블 한줄 추가 버튼
	private JButton jBtnSaveRow = null; // 테이블 한줄 저장 버튼
	private JButton jBtnEditRow = null; // 테이블 한줄 저장 버튼
	private JButton jBtnDelRow = null; // 테이블 한줄 삭제 버튼
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )

	public Emp() {
		setTitle("직원관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 762, 458);
		JLabel label = new JLabel("직원관리");
		label.setFont(new Font("굴림", Font.PLAIN, 30));
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
		
		JLabel label_1 = new JLabel("<-- 이름으로 검색");
		label_1.setFont(new Font("굴림", Font.PLAIN, 30));
		label_1.setBounds(320, 104, 257, 49);
		getContentPane().add(label_1);
		
		
		dao = new DAO();
		ArrayList<Model.Employee> bean = dao.EMP_Select();
		for (Model.Employee beans : bean) {
			model.addRow(new Object[] { beans.getEmpid(), beans.getEmpname(), beans.getSex(),beans.getRank(),beans.getPhone(),nf.format(beans.getSalary()) });
			System.out.println(beans);
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
				model2.addRow(new String[] { "", "", "","","","" }); // 새테이블의 초기값
			}
		});
		jBtnAddRow.setBounds(84, 395, 120, 25);
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
				String query = "insert into EMPLOYEE(EMPid, EMPName,SEX,RANK,PHONE,SALARY) VALUES(?,?,?,?,?,?)";

				try {

					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "london", "london"); // DB 연결
					pstmt = con.prepareStatement(query);

					// 물음표 갯수대로 입력.
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
				model2.setRowCount(0); // 전체 테이블 화면을 지워줌
				//select(); // 저장 후 다시 전체 값들을 받아옴.
				
				ArrayList<Model.Employee> bean = new DAO().EMP_Select();
                for (Model.Employee beans : bean) {
        			model.addRow(new Object[] { beans.getEmpid(), beans.getEmpname(), beans.getSex(),beans.getRank(),beans.getPhone(),nf.format(beans.getSalary()) });
        			System.out.println(beans);
        		}
			}
		});
		jBtnSaveRow.setBounds(398, 395, 120, 25);
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

				String query = "UPDATE EMPLOYEE SET Empid=?, Empname=?, Sex=?,Rank=?,Phone=?,Salary=? " + "where empid=?";

				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "london", "london"); // DB 연결
					pstmt = con.prepareStatement(query);

					// 물음표가 6개 이므로 6개 각각 입력해줘야한다.
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
				model2.setRowCount(0); // 전체 테이블 화면을 지워줌
				dao.EMP_Select(); // 수정 후다시 전체 값들을 받아옴.
				
			}
		});
		jBtnEditRow.setBounds(230, 395, 120, 25);
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
				String query = "delete from Employee where empid= ?";

				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "london", "london"); // DB 연결
					pstmt = con.prepareStatement(query);
					con.setAutoCommit(true); 
					// 물음표가 1개 이므로 3개 각각 입력해줘야한다.
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
		jBtnDelRow.setBounds(new Rectangle(561, 396, 97, 23));
		jBtnDelRow.setText("삭제");
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
