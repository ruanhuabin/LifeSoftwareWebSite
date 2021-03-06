package cn.edu.tsinghua.service;

import java.util.List;

import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.CategoryL2;
import cn.edu.tsinghua.entity.Post;

public interface CategoryL2DAO {
	boolean addL2Category(CategoryL2 cl2);	
	public List<CategoryL2> listAllL2Category();
	boolean isSubCategoryExist(CategoryL2 c2);
	
		
	
	
	public int getTotalRowNum();
	public List<CategoryL2> getSubCategoryDataByPageIndex(int offset, int pageSize);
	public boolean deleteSubCategory(int scid);
	boolean updateSubCategory(CategoryL2 c);
	
}
