package cn.edu.tsinghua.po;

import java.util.ArrayList;
import java.util.List;

public class CategoryL2 {
	
	private int cid;
	private String title;
	private List<SubCategory> subCategories = new ArrayList<SubCategory>();
	
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<SubCategory> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<SubCategory> subCatogories) {
		this.subCategories = subCatogories;
	}
	@Override
	public String toString() {
		return "Catagory [cid=" + cid + ", title=" + title + ", subCatogories="
				+ subCategories + "]";
	}
	
	
	
	
	

}
