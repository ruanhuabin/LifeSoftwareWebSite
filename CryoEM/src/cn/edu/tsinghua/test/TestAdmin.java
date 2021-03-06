package cn.edu.tsinghua.test;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import cn.edu.tsinghua.entity.Admin;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.service.AdminDAO;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.serviceimpl.AdminDAOImpl;
import cn.edu.tsinghua.serviceimpl.PostDAOImpl;


public class TestAdmin {
	private static final Logger logger = Logger.getLogger("JUnitLogger");
	

	
	@Test
	public void testAddAdmin()
	{	
		Admin admin = new Admin("admin", "123456");
		AdminDAO adminDAO = new AdminDAOImpl();
		adminDAO.addAdmin(admin);
	}
	
	@Test
	public void testAdminIsValid()
	{		
		
		Admin admin = new Admin("admin", "123456");
		AdminDAO adminDAO = new AdminDAOImpl();
		
		
		Assert.assertTrue(adminDAO.isAdminValid(admin));
		
		
	}
}
