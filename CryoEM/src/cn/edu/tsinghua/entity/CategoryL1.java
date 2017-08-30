package cn.edu.tsinghua.entity;

public class CategoryL1 {
	private int cid;
	private String categoryName;
	
	

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String catagoryName) {
		this.categoryName = catagoryName;
	}

	@Override
	public String toString() {
		return "CatagoryL1 [catagoryName=" + categoryName + "]";
	}
	
	

}
