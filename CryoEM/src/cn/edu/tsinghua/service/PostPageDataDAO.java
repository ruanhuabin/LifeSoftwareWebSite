package cn.edu.tsinghua.service;

import cn.edu.tsinghua.entity.PostPageData;
public interface PostPageDataDAO
{
	//pageNum is starting from 1
	public PostPageData getPostPageData(int pageNum);
}
