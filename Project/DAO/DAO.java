package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Employee;
import Model.Food;

public class DAO {
	private static final long serialVersionUID = 1L;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @호스트 IP : 포트 : SID
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )
	
	public DAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Food> Foods_Select() { // 테이블에 보이기 위해 검색

		String query = "select * from Food Order by foodid";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			ArrayList<Food> foods = new ArrayList<Food>();

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
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
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
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
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			ArrayList<Employee> employees = new ArrayList<Employee>();

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
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
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
			} catch (Exception e2) {
			}
		}
		return null;
	}

		
	}



