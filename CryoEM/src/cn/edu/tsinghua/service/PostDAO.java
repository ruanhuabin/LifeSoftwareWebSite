package cn.edu.tsinghua.service;

import java.util.List;

import cn.edu.tsinghua.entity.Post;

public interface PostDAO {	
	public boolean addPost(Post p);	
	public List<Post> listAllPost();
}
