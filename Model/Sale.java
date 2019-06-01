package Model;

import java.sql.Date;

public class Sale {

	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getSfoodid() {
		return sfoodid;
	}
	public void setSfoodid(int sfoodid) {
		this.sfoodid = sfoodid;
	}
	public String getSfoodname() {
		return sfoodname;
	}
	public void setSfoodname(String sfoodname) {
		this.sfoodname = sfoodname;
	}
	public int getSprice() {
		return sprice;
	}
	public void setSprice(int sprice) {
		this.sprice = sprice;
	}
	public int getStabid() {
		return stabid;
	}
	public void setStabid(int stabid) {
		this.stabid = stabid;
	}
	public int getSamount() {
		return samount;
	}
	public void setSamount(int samount) {
		this.samount = samount;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}



	@Override
	public String toString() {
		return "Sale [sid=" + sid + ", sfoodid=" + sfoodid + ", sfoodname=" + sfoodname + ", sprice=" + sprice
				+ ", stabid=" + stabid + ", samount=" + samount + ", stime=" + stime + ", counthot=" + counthot
				+ ", stabname=" + stabname + "]";
	}



	int sid;
	int sfoodid;
	String sfoodname;
	int sprice;
	int stabid;
	int samount;
	String stime;
	int counthot;
	String stabname;
	
	public String getStabname() {
		return stabname;
	}
	public void setStabname(String stabname) {
		this.stabname = stabname;
	}
	public int getCounthot() {
		return counthot;
	}
	public void setCounthot(int counthot) {
		this.counthot = counthot;
	}


}
