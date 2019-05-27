package Model;

public class Employee {
	int empid;
	String empname;
	String sex;
	String rank;
	String phone;
	String salary;
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", sex=" + sex + ", rank=" + rank + ", phone="
				+ phone + ", salary=" + salary + "]";
	}
	public Employee(int empid, String empname, String sex, String rank, String phone, String salary) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.sex = sex;
		this.rank = rank;
		this.phone = phone;
		this.salary = salary;
	}
	public Employee() {
		super();
	}
	
	
	

}
