package cn.edu.tsinghua.service;

import cn.edu.tsinghua.entity.Admin;


public interface AdminDAO {	
	public boolean isAdminValid(Admin admin);	
	public boolean addAdmin(Admin admin);	
	
}
