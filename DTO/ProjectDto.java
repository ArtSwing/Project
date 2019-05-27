package DTO;

import java.io.Serializable;

//DTO : DB�� �� Row�� Ŭ����ȭ ��Ų��. 
//�������� ��� �� Ŭ������ ��ü�� �ϳ��� ���Ϳ� ���� �ִ´�.
public class ProjectDto implements Serializable {//������ ���� �ø���������� �������̽�
	
	private String Foodid; //DB���� ���� ĭ�𳯸�Ƽ
	private String Foodname;
	private String price;
	
	public ProjectDto() {	
	}

	public ProjectDto(String Foodid, String Foodname, String price) { //DTO�� �����.
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