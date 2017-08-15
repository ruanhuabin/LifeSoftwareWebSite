package cn.edu.tsinghua.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.po.Category;
import cn.edu.tsinghua.po.SubCategory;
import cn.edu.tsinghua.po.SubSubCategory;
import cn.edu.tsinghua.service.CategoryL1DAO;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.serviceimpl.CategoryL1DAOImpl;
import cn.edu.tsinghua.serviceimpl.PostDAOImpl;


public class UserAction extends SuperAction {
	
	private static final Logger logger = Logger.getLogger("UserLogger");
	
	public String listSoftware()
	{
		PostDAO pDAO = new PostDAOImpl();
		List<Post> allPost = pDAO.listAllPost();
		
		request.setAttribute("all_post", allPost);
		
		logger.info("all post info: " + allPost);
		
		
		List<Category> catagories2 = getCatetoryResult(allPost);
		
		logger.info("catagories2 = " + catagories2);
		
		request.setAttribute("catagories", catagories2);
		
		
		
		
		
		
		return "list_software_success";
	}
	
	private class SubCatagoryWrapper
	{
		public SubCategory subCatagory ;
		public boolean isAddedToCatagory = false;
		
	}
	private SubCatagoryWrapper getSubCatagory(List<Category> catagories, String catagoryName, String subCatagoryName)	
	{
		SubCatagoryWrapper subCatagoryWrapper = new SubCatagoryWrapper();
		for(Category c: catagories)
		{
			List<SubCategory> subCatagories = c.getSubCategories();
			if(!catagoryName.equals(c.getTitle()))
			{
				continue;
			}
			for(SubCategory s: subCatagories)
			{
				if(subCatagoryName.equals(s.getTitle()))
				{
					subCatagoryWrapper.subCatagory = s;
					subCatagoryWrapper.isAddedToCatagory = true;
					return subCatagoryWrapper;
				}
			}
			
		}
		
		//if return from here, means that current subCatatory is not found ,so we create a new one for it
		subCatagoryWrapper.subCatagory = new SubCategory();
		subCatagoryWrapper.isAddedToCatagory = false;
		
		return subCatagoryWrapper;
		
	}
	
	private List<Category> getCatetoryResult(List<Post> allPost)
	{
//		String strCatagories[] = {"CryoEM", "Protein", "General"};
//		List<Category> catagories = new ArrayList<Category>();
//		for(String str: strCatagories)
//		{
//			Category c = new Category();
//			c.setTitle(str);
//			
//			catagories.add(c);
//		}
		
		//Firstly, get all categories from database
		CategoryL1DAO cl1DAO = new CategoryL1DAOImpl();
		List<CategoryL1> cl1s = cl1DAO.listAllL1Category();
		
		List<Category> catagories = new ArrayList<Category>();
		
		for(CategoryL1 cl1: cl1s)
		{
			String categoryName = cl1.getCategoryName();			
			Category c = new Category();
			c.setTitle(categoryName);			
			catagories.add(c);			
		}
		
		for(Post p: allPost)
		{
			String cName = p.getCatagory();
			String scName = p.getSubCatagory();
			
			Category currCatagory = null;
			//Check which catagory this post belongs to
			for(Category c: catagories)
			{
				if(cName.equals(c.getTitle()))
				{
					logger.info("Current post: " + p);
					currCatagory = c;
					break;
				}
			}
			
			//current catagory has no subcatagory
			
			if(scName == null || scName.equals(""))
			{				
				SubCategory sc = new SubCategory();
				sc.setTitle(p.getTitle());
				sc.setSid(p.getPid());
				currCatagory.getSubCategories().add(sc);			
			}
			else // This post has subcatagory
			{
				//SubCatagory sc = new SubCatagory();
				SubCatagoryWrapper scw = getSubCatagory(catagories, cName, scName);
				SubCategory sc = scw.subCatagory;
				sc.setTitle(p.getSubCatagory());
				if(scw.isAddedToCatagory == false)
				{
					logger.info("=============>sc.sid = " + p.getPid());
					sc.setSid(p.getPid());
				}
				
				SubSubCategory ssc = new SubSubCategory();
				ssc.setTitle(p.getTitle());
				ssc.setSsid(p.getPid());				
				sc.getSubSubCategories().add(ssc);
				
				logger.info("post = " + p);
				logger.info("currCatagory = " + currCatagory);
				logger.info("currCatagory.getSubCatogories = " + currCatagory.getSubCategories());
				
				if(scw.isAddedToCatagory == false)
				{
					currCatagory.getSubCategories().add(sc);
				}
			}			
		}
		
		//set the catagory's cid to the first subcatagory's sid
		
		for(Category c: catagories)
		{
			for(SubCategory s: c.getSubCategories())
			{
				c.setCid(s.getSid());
				break;
			}
		}
		return catagories;
	}
	

	
}