package cn.edu.tsinghua.test;
import java.util.ArrayList;
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
import cn.edu.tsinghua.po.Category;
import cn.edu.tsinghua.po.SubCategory;
import cn.edu.tsinghua.po.SubSubCategory;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.serviceimpl.PostDAOImpl;


public class TestCatogory {
	private static final Logger logger = Logger.getLogger("JUnitLogger");
	
	
	@Test
	public void testConstructCatagory()
	{		
		
		Category c1 = new Category();
		c1.setTitle("CryoEM");
		
		List<SubCategory> c1SubCatagories = new ArrayList<SubCategory>();
		
		SubCategory sc1 = new SubCategory();
		sc1.setSid(0);
		sc1.setTitle("Thunder");
		
		SubCategory sc2 = new SubCategory();
		sc2.setSid(1);
		sc2.setTitle("Thunder2");
		
		List<SubSubCategory> sc2SSCatatory = new ArrayList<SubSubCategory>();
		SubSubCategory ssc1 = new SubSubCategory();
		ssc1.setSsid(11);
		ssc1.setTitle("Thunder2_1");
		
		sc2SSCatatory.add(ssc1);
		
		SubSubCategory ssc2 = new SubSubCategory();
		ssc2.setSsid(22);
		ssc2.setTitle("Thunder2_2");
		sc2SSCatatory.add(ssc2);
		
		sc2.setSubSubCategories(sc2SSCatatory);
		
		
		SubCategory sc3 = new SubCategory();
		sc3.setSid(2);
		sc3.setTitle("Thunder3");
		
		c1SubCatagories.add(sc1);
		c1SubCatagories.add(sc2);
		c1SubCatagories.add(sc3);
		
		c1.setSubCategories(c1SubCatagories);
		
		
		
		Category c2 = new Category();
		c2.setTitle("protein");
		
		List<SubCategory> c2SubCatagories = new ArrayList<SubCategory>();
		
		SubCategory c2s1 = new SubCategory();
		c2s1.setSid(0);
		c2s1.setTitle("Quan");
		
		SubCategory c2s2 = new SubCategory();
		c2s2.setSid(1);
		c2s2.setTitle("Lipid");
		
		c2SubCatagories.add(c2s1);
		c2SubCatagories.add(c2s2);
		
		c2.setSubCategories(c2SubCatagories);
		
		List<Category> catagories = new ArrayList<Category>();
		catagories.add(c1);
		catagories.add(c2);
		
		logger.info("catagories =" + catagories);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
