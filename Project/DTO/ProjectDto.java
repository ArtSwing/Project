package DTO;

import java.io.Serializable;

//DTO : DB�� �� Row�� Ŭ����ȭ ��Ų��. 
//�������� ��� �� Ŭ������ ��ü�� �ϳ��� ���Ϳ� ���� �ִ´�.
public class ProjectDto implements Serializable {//������ ���� �ø���������� �������̽�
	//DB���� ���� ĭ�𳯸�Ƽ
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

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getTabid() {
		return tabid;
	}

	public void setTabid(String tabid) {
		this.tabid = tabid;
	}


	@Override
	public String toString() {
		return "ProjectDto [Foodid=" + Foodid + ", Foodname=" + Foodname + ", price=" + price + ", imgurl=" + imgurl
				+ ", tabid=" + tabid + "]";
	}
	private String Foodid;
	private String Foodname;
	private String price;
	private String imgurl;
	private String tabid;
	
	public ProjectDto() {	
	}

	public ProjectDto(String Foodid, String Foodname, String price,String imgurl, String tabid) { //DTO�� �����.
		super();
		this.Foodid = Foodid;
		this.Foodname = Foodname;
		this.price = price;
		this.imgurl = imgurl;
		this.tabid = tabid;
	}
	
	//getter / setter+
	
	
	

}