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
import Model.Sale;


public class DAO {
	private static final long serialVersionUID = 1L;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @ȣ��Ʈ IP : ��Ʈ : SID
	private Connection con = null;
	Statement st;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )
	
	public DAO() {
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
	
	public int Foods_price(int foodid) {
		int contnum = 0;
		String query = "select price from Food where foodid=?";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, String.valueOf(foodid));
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
			String s = rs.getString(1);
			contnum = Integer.parseInt(s);
			}
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
		return contnum;
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
		ArrayList<Employee> employees = null;
		String query = "select * from EMPLOYEE Order by empid";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
			employees = new ArrayList<Employee>();

			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
				Employee employee = new Employee();
				employee.setEmpid(Integer.parseInt(rs.getString("empid")));
				employee.setEmpname(rs.getString("Empname"));
				employee.setSex(rs.getString("Sex"));
				employee.setRank(rs.getString("Rank"));
				employee.setPhone(rs.getString("Phone"));
				employee.setSalary(rs.getString("salary"));
				employees.add(employee);
			}
			
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
		return employees;
	}
	//���õ� �� �ϳ��� ��������
	public Employee EMP_SelectOne(int empid) {
		Employee employee = new Employee();
		String query = "select * from EMPLOYEE where empid = ?";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, empid);
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����			

			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
				employee.setEmpid(Integer.parseInt(rs.getString("empid")));
				employee.setEmpname(rs.getString("Empname"));
				employee.setSex(rs.getString("Sex"));
				employee.setRank(rs.getString("Rank"));
				employee.setPhone(rs.getString("Phone"));
				employee.setSalary(rs.getString("salary"));
			}
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
		return employee;
	}
	
	
	public boolean Sale_Insert(ArrayList<Sale> sales) {
		
			String query = "insert into Sale(sid, sfoodid, stime, samount) VALUES(SALE_KEY.NEXTVAL,?,TO_CHAR(SYSDATE, 'YYYYMMDD'),?)";		
			
			try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			
			for (Sale sale : sales) {
				pstmt.setInt(1, sale.getSfoodid());
				pstmt.setInt(2, sale.getSamount());
				pstmt.addBatch();
				pstmt.clearParameters();
			}
			int[] result = pstmt.executeBatch();
			
			for (int i : result) {
				if(i == 0) {
					return false;
				}
			}
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose();
		}
		return false;
	}
	 /**
     * �˻��ܾ �ش��ϴ� ���ڵ� �˻��ϱ� (like�����ڸ� ����Ͽ� _, %�� ����Ҷ��� PreparedStatemnet�ȵȴ�. �ݵ��
     * Statement��ü�� �̿���)
     * */
    public ArrayList<Employee> getUserSearch(String sql) {
        String query = sql;
    
 
        try {
        	Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
        	 st = con.createStatement();
             rs = st.executeQuery(query);
             ArrayList<Employee> employees = new ArrayList<Employee>();
            // DefaultTableModel�� �ִ� ���� ������ �����
 
            while (rs.next()) {
            	Employee employee = new Employee();
				employee.setEmpid(Integer.parseInt(rs.getString("empid")));
				employee.setEmpname(rs.getString("Empname"));
				employee.setSex(rs.getString("Sex"));
				employee.setRank(rs.getString("Rank"));
				employee.setPhone(rs.getString("Phone"));
				employee.setSalary(rs.getString("salary"));
				employees.add(employee);
            }
        	for (Employee employee : employees) {
				System.out.println(employee.toString());
			}
        	System.out.println(employees);
			return employees;
        } catch (SQLException e) {
            
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	 dbClose();
        }
		return null;
    }
 
    	
	}






