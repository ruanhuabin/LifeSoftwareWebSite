package cn.edu.tsinghua.service;

import cn.edu.tsinghua.entity.CategoryPageData;
import cn.edu.tsinghua.entity.SubCategoryPageData;
public interface SubCategoryPageDataDAO
{
	//pageNum is starting from 1
	public SubCategoryPageData getSubCategoryPageData(int pageNum);
}
