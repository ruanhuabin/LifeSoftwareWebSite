package cn.edu.tsinghua.service;

import cn.edu.tsinghua.entity.CategoryPageData;
public interface CategoryPageDataDAO
{
	//pageNum is starting from 1
	public CategoryPageData getCategoryPageData(int pageNum);
}
