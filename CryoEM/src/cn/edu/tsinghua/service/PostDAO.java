package cn.edu.tsinghua.service;

import java.util.List;

import cn.edu.tsinghua.entity.Post;

public interface PostDAO {	
	public boolean addPost(Post p);	
	public List<Post> listAllPost();
	public int getTotalRowNum();
	public List<Post> getPostDataByPageIndex(int offset, int pageSize);
	public boolean deletePost(int pid);
	public Post getPost(int pid);
	public boolean updatePost(Post p);
	public List<Post> getPostByHQL(String hql);
}
