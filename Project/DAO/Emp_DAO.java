package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Emp_DAO {
	private static final long serialVersionUID = 1L;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // @호스트 IP : 포트 : SID
	private Connection con = null;
	Statement st;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )



public Emp_DAO() {
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
}

//public int