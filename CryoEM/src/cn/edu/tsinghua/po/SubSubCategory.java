package cn.edu.tsinghua.po;

public class SubSubCategory {
	
	private int ssid; // Used for achor
	private String title;
	public int getSsid() {
		return ssid;
	}
	public void setSsid(int ssid) {
		this.ssid = ssid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SubSubCatagory [ssid=" + ssid + ", title=" + title + "]";
	}
	
	
	
	

}
