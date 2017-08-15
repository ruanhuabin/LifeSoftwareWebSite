package cn.edu.tsinghua.test;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.serviceimpl.PostDAOImpl;


public class TestPost {
	private static final Logger logger = Logger.getLogger("JUnitLogger");
	@Test
	public void testSchemaExport()
	{	
		//����
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);		
		SchemaExport export = new SchemaExport(config);		
		//��һ��true�����ɱ��ṹ
		//�ڶ���true������sql���
		export.create(true, true);
	}

	
	@Test
	public void testAddPost()
	{		
		Post p = new Post(1, "title1", "description1", "uri1?f=g.txt");
		PostDAO pDAO = new PostDAOImpl();
		pDAO.addPost(p);
		Post p2 = new Post();
		p2.setTitle("title2");
		p2.setDescription("description2");
		p2.setSoftwareURI("uri2?f=e.txt");
		p2.setCatagory("CryoEM");
		p2.setSubCatagory("CryoEM_s");
		
		pDAO.addPost(p2);
		
	}
	
	@Test
	public void testListPost()
	{		
		
		PostDAO pDAO = new PostDAOImpl();
		List<Post> allPost = pDAO.listAllPost();
		
		logger.info("all post :" + allPost);
		
	}
}