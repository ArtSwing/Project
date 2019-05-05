package DAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.Employee;
import Model.Food;


public class DAO {
	private static final long serialVersionUID = 1L;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @ȣ��Ʈ IP : ��Ʈ : SID
	private Connection con = null;
	Statement st;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )
	
	public DAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void dbClose() {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (pstmt != null) pstmt.close();
        } catch (Exception e) {
            System.out.println(e + "=> dbClose fail");
        }
    }
	
	
	public ArrayList<Food> Foods_Select() { // ���̺� ���̱� ���� �˻�

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
				food.setImgUrl(rs.getString("imgUrl"));
				food.setTabid(rs.getInt("tabid"));
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

	public ArrayList<Employee> EMP_Select() {
		
		String query = "select * from EMPLOYEE Order by empid";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
			ArrayList<Employee> employees = new ArrayList<Employee>();

			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
				Employee employee = new Employee();
				employee.setEmpid(Integer.parseInt(rs.getString("empid")));
				employee.setEmpname(rs.getString("Empname"));
				employee.setSex(rs.getString("Sex"));
				employee.setRank(rs.getString("Rank"));
				employee.setPhone(rs.getString("Phone"));
				employee.setSalary(Integer.parseInt(rs.getString("salary")));
				employees.add(employee);

			}

			for (Employee employee : employees) {
				System.out.println(employee.toString());
			}
			return employees;
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
	
	 /**
     * �˻��ܾ �ش��ϴ� ���ڵ� �˻��ϱ� (like�����ڸ� ����Ͽ� _, %�� ����Ҷ��� PreparedStatemnet�ȵȴ�. �ݵ��
     * Statement��ü�� �̿���)
     * */
    public void getUserSearch(DefaultTableModel model, String fieldName,
            String word) {
        String query = "SELECT * FROM EMPLOYEE WHERE " + fieldName.trim()
                + " LIKE '%" + word.trim() + "%'";
 
        try {
        	 st = con.createStatement();
             rs = st.executeQuery(query);
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < model.getRowCount();) {
                model.removeRow(0);
            }
 
            while (rs.next()) {
                Object data[] = { rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4) };
 
                model.addRow(data);
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> getUserSearch fail");
        } finally {
        	 dbClose();
        }
 
    }
 
	}






