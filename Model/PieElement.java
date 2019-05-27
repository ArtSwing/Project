package Model;

public class PieElement {
	public String piename;
	public int value;
	
	public PieElement(String piename, int value) {
		this.piename = piename;
		this.value = value;
	}
	public String getPiename() {
		return piename;
	}
	public void setPiename(String piename) {
		this.piename = piename;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
