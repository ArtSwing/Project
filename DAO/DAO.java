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
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @호스트 IP : 포트 : SID
	private Connection con = null;
	Statement st;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )
	
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
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
			String s = rs.getString(1);
			contnum = Integer.parseInt(s);
			}
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
		return contnum;
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
				food.setPrice(Integer.parseInt(rs.getString("Price")));
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
		ArrayList<Employee> employees = null;
		String query = "select * from EMPLOYEE Order by empid";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			employees = new ArrayList<Employee>();

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
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
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
			} catch (Exception e2) {
			}
		}
		return employees;
	}
	//선택된 행 하나만 가져오기
	public Employee EMP_SelectOne(int empid) {
		Employee employee = new Employee();
		String query = "select * from EMPLOYEE where empid = ?";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, empid);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성			

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
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
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
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
     * 검색단어에 해당하는 레코드 검색하기 (like연산자를 사용하여 _, %를 사용할때는 PreparedStatemnet안된다. 반드시
     * Statement객체를 이용함)
     * */
    public ArrayList<Employee> getUserSearch(String sql) {
        String query = sql;
    
 
        try {
        	Class.forName(driver);
			con = DriverManager.getConnection(url, "london", "london");
        	 st = con.createStatement();
             rs = st.executeQuery(query);
             ArrayList<Employee> employees = new ArrayList<Employee>();
            // DefaultTableModel에 있는 기존 데이터 지우기
 
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
  //선택된 행 하나만 가져오기
  	public Food Food_SelectOne(int foodid) {
  		Food food = new Food();
  		String query = "select * from Food where foodid = ?";
  		try {
  			Class.forName(driver);
  			con = DriverManager.getConnection(url, "london", "london");
  			pstmt = con.prepareStatement(query);
  			pstmt.setInt(1, foodid);
  			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성			

  			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				food.setId(Integer.parseInt(rs.getString("Foodid")));
				food.setName(rs.getString("Foodname"));
				food.setPrice(Integer.parseInt(rs.getString("Price")));
				food.setImgUrl(rs.getString("imgUrl"));
				food.setTabid(Integer.parseInt(rs.getString("Tabid")));
  			}
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
  		return food;
  	}
  	
  	// 카테고리별 판매 순위
  	public ArrayList<Sale> Sale_Tab() {
  		ArrayList<Sale> sale = new ArrayList<Sale>();
  		String query = "select * from ( select tabname, count(*) from sale,food,tablist "
  				+ "where sale.sfoodid = food.foodid and food.tabid = tablist.tabid group by tabname)";
  		try {
  			Class.forName(driver);
  			con = DriverManager.getConnection(url, "london", "london");
  			pstmt = con.prepareStatement(query);
  			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성			

  			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
  				
  				Sale ss = new Sale();
  				
  				ss.setStabname(rs.getString(1));
  				ss.setCounthot(rs.getInt(2));
  				sale.add(ss);
  				
  			}
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
  		return sale;
  	}
  	
	// 인기품목 파이그래프 (4위까지)
  	public ArrayList<Sale> Sale_Hot() {
  		ArrayList<Sale> sale = new ArrayList<Sale>();
  		String query = "select * from ( select foodname, count(*) from sale,food where sale.sfoodid = food.foodid group by foodname order by count(*) desc) where rownum <= 5";
  		try {
  			Class.forName(driver);
  			con = DriverManager.getConnection(url, "london", "london");
  			pstmt = con.prepareStatement(query);
  			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성			

  			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
  				
  				Sale s = new Sale();
  				
  				s.setSfoodname(rs.getString(1));
  				s.setCounthot(rs.getInt(2));
  				sale.add(s);
  			}
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
  		return sale;
  	}
	}






