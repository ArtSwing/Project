package Model;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

	public class Food {
	
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getTabid() {
			return tabid;
		}
		public void setTabid(int tabid) {
			this.tabid = tabid;
		}
		int id;
		@Override
		public String toString() {
			return "Food [id=" + id + ", name=" + name + ", imgUrl=" + imgUrl + ", price=" + price + ", tabid=" + tabid
					+ "]";
		}
		String name;
		String imgUrl;
		int price;
		int tabid;
		
	
		
		
}
