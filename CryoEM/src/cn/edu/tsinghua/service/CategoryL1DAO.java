package cn.edu.tsinghua.service;

import java.util.List;

import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.Post;

public interface CategoryL1DAO {	
	public boolean addL1Category(CategoryL1 cl1);	
	public boolean isCategoryExist(CategoryL1 cl1);
	public List<CategoryL1> listAllL1Category();
}