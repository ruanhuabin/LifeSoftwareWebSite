package cn.edu.tsinghua.po;

import java.util.ArrayList;
import java.util.List;

public class SubCategory {
	private int sid; //Used for achor
	private String title;
	private List<SubSubCategory> subSubCategories = new ArrayList<SubSubCategory>();
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<SubSubCategory> getSubSubCategories() {
		return subSubCategories;
	}
	public void setSubSubCategories(List<SubSubCategory> subSubCategories) {
		this.subSubCategories = subSubCategories;
	}
	@Override
	public String toString() {
		return "SubCatagory [sid=" + sid + ", title=" + title
				+ ", subSubCatagories=" + subSubCategories + "]";
	}
	
	

}
