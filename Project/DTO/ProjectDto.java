package DTO;

import java.io.Serializable;

//DTO : DB의 한 Row를 클래스화 시킨다. 
//여러개일 경우 이 클래스의 객체를 하나씩 벡터에 집어 넣는다.
public class ProjectDto implements Serializable {//순서를 위한 시리얼라이져블 인터페이스
	
	private String Foodid; //DB에서 사용된 칸디날리티
	private String Foodname;
	private String price;
	
	public ProjectDto() {	
	}

	public ProjectDto(String Foodid, String Foodname, String price) { //DTO를 만든다.
		super();
		this.Foodid = Foodid;
		this.Foodname = Foodname;
		this.price = price;
	}
	
	//getter / setter+
	
	
	
	public String getFoodid() {
		return Foodid;
	}

	public void setFoodid(String foodid) {
		Foodid = foodid;
	}

	public String getFoodname() {
		return Foodname;
	}

	public void setFoodname(String foodname) {
		Foodname = foodname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String toString() {
		return "[" + this.Foodid + "/" + this.Foodname + "/" + this.price + "]";
	}
}