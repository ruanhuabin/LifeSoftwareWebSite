package cn.edu.tsinghua.entity;

import java.io.Serializable;

public class CategoryL2 implements Serializable{
	private int scid;
	private String parentCategoryName;
	private String categoryName;
	
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "CategoryL2 [parentCategoryName=" + parentCategoryName
				+ ", categoryName=" + categoryName + "]";
	}
	
	public int getScid() {
		return scid;
	}
	public void setScid(int scid) {
		this.scid = scid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime
				* result
				+ ((parentCategoryName == null) ? 0 : parentCategoryName
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryL2 other = (CategoryL2) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (parentCategoryName == null) {
			if (other.parentCategoryName != null)
				return false;
		} else if (!parentCategoryName.equals(other.parentCategoryName))
			return false;
		return true;
	}
	
	
	
	

}
