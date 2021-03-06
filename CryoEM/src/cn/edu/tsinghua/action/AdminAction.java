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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
*/
import org.apache.struts2.ServletActionContext;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.edu.tsinghua.entity.Admin;
import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.CategoryL2;
import cn.edu.tsinghua.entity.CategoryPageData;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.entity.PostPageData;
import cn.edu.tsinghua.entity.SubCategoryPageData;
import cn.edu.tsinghua.po.SubCategory;
import cn.edu.tsinghua.service.AdminDAO;
import cn.edu.tsinghua.service.CategoryL1DAO;
import cn.edu.tsinghua.service.CategoryL2DAO;
import cn.edu.tsinghua.service.CategoryPageDataDAO;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.service.PostPageDataDAO;
import cn.edu.tsinghua.service.SubCategoryPageDataDAO;
import cn.edu.tsinghua.serviceimpl.AdminDAOImpl;
import cn.edu.tsinghua.serviceimpl.CategoryL1DAOImpl;
import cn.edu.tsinghua.serviceimpl.CategoryL2DAOImpl;
import cn.edu.tsinghua.serviceimpl.CategoryPageDataDAOImpl;
import cn.edu.tsinghua.serviceimpl.PostDAOImpl;
import cn.edu.tsinghua.serviceimpl.PostPageDataDAOImpl;
import cn.edu.tsinghua.serviceimpl.SubCategoryPageDataDAOImpl;


public class AdminAction extends SuperAction {
	
	private static final Logger logger = Logger.getLogger("AdminLogger");
	//private static final Logger logger = LogManager.getLogger(AdminAction.class);
	
	private String yourFileFileName;
	private String yourFileContentType;
	private File yourFile;
	private String uploadDir;	
	
	
	private String userFileFileName;
	private String userFileContentType;
	private File userFile;
	
	
	private ArrayList<String> list;
	
	private int pageNumForPost;
	
	private PostPageData postPageData ;
	private CategoryPageData categoryPageData;

	

	public String getYourFileFileName() {
		return yourFileFileName;
	}
	public void setYourFileFileName(String yourFileFileName) {
		this.yourFileFileName = yourFileFileName;
	}
	public String getYourFileContentType() {
		return yourFileContentType;
	}
	public void setYourFileContentType(String yourFileContentType) {
		this.yourFileContentType = yourFileContentType;
	}
	public File getYourFile() {
		return yourFile;
	}
	public void setYourFile(File yourFile) {
		this.yourFile = yourFile;
	}
	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
	
	
	public String getUserFileFileName() {
		return userFileFileName;
	}
	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
	}
	public String getUserFileContentType() {
		return userFileContentType;
	}
	public void setUserFileContentType(String userFileContentType) {
		this.userFileContentType = userFileContentType;
	}
	public File getUserFile() {
		return userFile;
	}
	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}
	
	
	public int getPageNumForPost() {
		return pageNumForPost;
	}
	public void setPageNumForPost(int pageNum) {
		this.pageNumForPost = pageNum;
	}
	
	
	public PostPageData getPostPageData() {
		return postPageData;
	}
	public void setPostPageData(PostPageData postPageData) {
		this.postPageData = postPageData;
	}
	
	
	public CategoryPageData getCategoryPageData() {
		return categoryPageData;
	}
	public void setCategoryPageData(CategoryPageData categoryPageData) {
		this.categoryPageData = categoryPageData;
	}
	public String upload() {

		logger.info("filename = " + this.yourFileFileName);
		logger.info("filetype = " + this.yourFileContentType);

//		String path = ServletActionContext.getServletContext().getRealPath(
//				uploadDir);

		String path = uploadDir;
		logger.info("path = " + path);				
		File dir = new File(uploadDir);
		if(!dir.exists())
		{
			if(dir.mkdir())
			{
				logger.info("Create upload dir [ " + uploadDir + " ] success");
			}
			else
			{
				logger.warning("Create upload dir [ " + uploadDir + " ] failed");
			}
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String newFileName;
		long now = new Date().getTime();

		newFileName = Long.toString(now) + "_" + yourFileFileName;
		logger.info("new File Name = " + newFileName);

		try {
			FileInputStream fis = new FileInputStream(yourFile);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,
					newFileName));

			bos = new BufferedOutputStream(fos);

			byte[] buf = new byte[4096];

			int len = -1;

			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != bis)
					bis.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {

				if (null != bos)
					bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return "software_upload_success";
	}
	
	private void unMarkPreviousWelcomePost()
	{
		PostDAO pDAO = new PostDAOImpl();
		String hql = "from Post as p where p.isWelcomePost = '1'"; 
		List<Post> posts = pDAO.getPostByHQL(hql);
		if(posts.size() != 0)
		{
			Post p = posts.get(0);
			p.setIsWelcomePost("0");
			pDAO.updatePost(p);
		}
		
	}
	public String postSoftware()
	{
		logger.info("upload filen name : " + this.yourFileFileName);
		logger.info("upload file type = " + this.yourFileContentType);

		String newFileName;
		long now = new Date().getTime();

		newFileName = Long.toString(now) + "_" + yourFileFileName;
		logger.info("rename uploaded file to: " + newFileName);
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String softwareURI = "/CryoEM/post/download.action?f=" + newFileName;
		String category = request.getParameter("category");
		String subCategory = request.getParameter("subCategory");
		
		
		
		if(subCategory.equals("----Please Select Sub Category----"))
		{
			subCategory = null;
		}
		
		String isMarkedWelcome = request.getParameter("isMarkedWelcome");
		String author = request.getParameter("author");
		String authorHomePageURL = request.getParameter("authorHomePage");
		String forumURL = request.getParameter("forumPage");
		
		logger.info("post title: " + title);
		logger.info("post description: " + description);
		logger.info("post category:" + category);
		logger.info("post sub category:" + subCategory);
		logger.info("isMarkedWelcome :" + isMarkedWelcome);
		logger.info("author:" + author);
		logger.info("author home page url:" + authorHomePageURL);
		logger.info("forum page url: " + forumURL);
		
		if(authorHomePageURL == null)
		{
			authorHomePageURL = "";
		}
		if(forumURL == null)
		{
			forumURL = "";
		}
		
		if(isMarkedWelcome == null)
		{
			isMarkedWelcome = "0";
		}
		
		writeUploadFile(newFileName);
		
		authorHomePageURL = authorHomePageURL.trim();
		if(authorHomePageURL.startsWith("http://") == false)
			authorHomePageURL = "http://" + authorHomePageURL;		
		
		forumURL = forumURL.trim();
		if(forumURL.startsWith("http://") == false)
		{
			forumURL = "http://" + forumURL;
		}
		Post p = new Post();
		p.setTitle(title);
		p.setDescription(description);
		p.setSoftwareURI(softwareURI);
		p.setCatagory(category);
		p.setSubCatagory(subCategory);
		p.setSoftwareFileName(this.yourFileFileName);
		p.setIsWelcomePost(isMarkedWelcome);
		p.setAuthor(author);
		p.setAuthorHomePageURL(authorHomePageURL);
		p.setForumURL(forumURL);
		PostDAO pDAO = new PostDAOImpl();
		
		/*
		 * 在添加新的post之前，如果当前提交的post被标记为欢迎软件的话，则需要将之前标记为欢迎软件的标记给去掉
		 */
		
		if(isMarkedWelcome.equals("1"))
		{
			unMarkPreviousWelcomePost();
		}
		pDAO.addPost(p);
		
		return "software_post_success";
	}
	private void writeUploadFile(String newFileName) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			//String path = ServletActionContext.getServletContext().getRealPath(uploadDir);
			String path = uploadDir;
			logger.info("file will be saved to path: " + path);			
			File dir = new File(path);
			if (!dir.exists())
			{
				dir.mkdir();
			}
			FileInputStream fis = new FileInputStream(yourFile);
			bis = new BufferedInputStream(fis);
			FileOutputStream fos = new FileOutputStream(new File(dir, newFileName));

			bos = new BufferedOutputStream(fos);

			byte[] buf = new byte[4096];

			int len = -1;

			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
					
		} finally
		{
			try {

				if (null != bis)
					bis.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {

				if (null != bos)
					bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	
	public String toAdmin()
	{			
		Admin admin = (Admin)session.getAttribute("adminInfo");
		
		loadCategoryAndSubCategory();
		
//		List<Vector<String>> subCategories = new ArrayList<Vector<String>>();
//		Vector<String> s1 = new Vector<String>();
//		s1.add("11");
//		s1.add("12");
//		s1.add("13");
//		
//		Vector<String> s2 = new Vector<String>();
//		s2.add("21");
//		s2.add("22");
//		s2.add("23");
//		
//		Vector<String> s3 = new Vector<String>();
//		s3.add("31");
//		s3.add("32");
//		s3.add("33");
//		
//		subCategories.add(s1);
//		subCategories.add(s2);
//		subCategories.add(s3);
//		request.setAttribute("allL2Category", subCategories);
//		
//		logger.info("AllL2Category = " + subCategories);
		
		List<String> subCategories2 = new ArrayList<String>();
		subCategories2.add("abc");
		subCategories2.add("def");
		subCategories2.add("hij");
		logger.info("AllL3Category = " + subCategories2);
		request.setAttribute("allL3Category", subCategories2);
		
		
		if(admin != null)
			return "load_admin_page";
		else
			return "load_admin_login_page";
	}
	private void loadCategoryAndSubCategory() {
		/*Load all categories so that admin page can list all category name in select widget*/
		CategoryL1DAO cl1DAO = new CategoryL1DAOImpl();
		List<CategoryL1> allCategory = cl1DAO.listAllL1Category();
		logger.info("all category is: " + allCategory);
		
		request.setAttribute("allL1Category", allCategory);
		
		CategoryL2DAO allSubCategoryDAO = new CategoryL2DAOImpl();
		List<CategoryL2> allSubCategory = allSubCategoryDAO.listAllL2Category();
		logger.info("all sub category is: " + allSubCategory);
		
		List<Vector<String>> newSubCategories = new ArrayList<Vector<String>>();
		
		for(CategoryL1 cl1:allCategory)
		{
			Vector<String> currSubCategory = new Vector<String>();
			for(CategoryL2 cl2: allSubCategory)
			{
				String parent = cl2.getParentCategoryName();
				String child = cl2.getCategoryName();
				
				if(parent.equals(cl1.getCategoryName()))
				{
					currSubCategory.add(child);
				}
			}
			
			newSubCategories.add(currSubCategory);
		}
		
		request.setAttribute("allL2Category", newSubCategories);
		logger.info("AllL2Category = " + newSubCategories);
	}
	
	public ArrayList<String> getList() {
		return list;
	}
	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	public String adminLogin()
	{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Admin admin = new Admin(username, password);
		AdminDAO adminDAO = new AdminDAOImpl();
		
		boolean result = adminDAO.isAdminValid(admin);
		
		if(result)
		{
			session.setAttribute("adminInfo", admin);
			
			loadCategoryAndSubCategory();
			
			
			return "admin_login_success";
		}
		else
		{
			request.setAttribute("adminLoginResult", "Username or Password is not valid!");
			return "admin_login_failed";
		}
		
	}
	
	
	public String addAdmin()
	{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Admin admin = new Admin(username, password);
		AdminDAO adminDAO = new AdminDAOImpl();
		
		boolean result = adminDAO.isAdminExist(admin);
		
		if(result)
		{
			request.setAttribute("addAdminResult", "Failed: Name  \"" + username + "\" is already exist");
			
			return "add_admin_failed";
		}
		else
		{
			adminDAO.addAdmin(admin);
			request.setAttribute("addAdminResult", "Add Admin  \"" + username + "\" success");
			return "add_admin_success";
		}
		
	}
	
	/**
	 * Process file that uploading for user to download by the link within description text
	 * @return
	 */
	public String uploadUserFile()
	{
		
		logger.info("filename = " + this.userFileFileName);
		logger.info("filetype = " + this.userFileContentType);

//		String path = ServletActionContext.getServletContext().getRealPath(
//				uploadDir);

		String path = uploadDir;
		logger.info("path = " + path);

		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String newFileName;
		long now = new Date().getTime();

		newFileName = Long.toString(now) + "_" + userFileFileName;
		logger.info("new File Name = " + newFileName);

		try {
			FileInputStream fis = new FileInputStream(userFile);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,
					newFileName));

			bos = new BufferedOutputStream(fos);

			byte[] buf = new byte[4096];

			int len = -1;

			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}

		} catch (Exception e) {			
			e.printStackTrace();
			request.setAttribute("user_file_URL", "Generate file URL failed, please retry again!!!");
			return "upload_user_file_failed";
		} finally {
			try {

				if (null != bis)
					bis.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {

				if (null != bos)
					bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		String userFileURL = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort() + "/CryoEM/post/download.action?f=" + newFileName;
		
		userFileURL = "<a href=\"" + userFileURL + "\">"+ userFileFileName + "</a>";
		
		logger.info("userFileURL = " + userFileURL);
		request.setAttribute("user_file_URL", userFileURL);
		
		return "upload_user_file_success";
	}
	
	
	
	public String addCategory()
	{
		String categoryName = request.getParameter("category");
		CategoryL1 c1 = new CategoryL1();
		c1.setCategoryName(categoryName);
		
		
		CategoryL1DAO categoryL1DAO = new CategoryL1DAOImpl();
		
		if(categoryL1DAO.isCategoryExist(c1))
		{
			request.setAttribute("addCategoryResult", "Failed: Category \"" + categoryName + "\" is already exist");
			return "add_category_failed";
		}
		
		boolean result = categoryL1DAO.addL1Category(c1);

		if(result == true)
		{
			request.setAttribute("addCategoryResult", "Add category \"" + categoryName + "\" success");
			return "add_category_success";
		}
		else
		{
			request.setAttribute("addCategoryResult", "Create Category \"" + categoryName + "\" failed, ask adminstrator for help");
			return "add_category_failed";
			
		}	
		
	}
	
	public String toSubCategory()
	{
		CategoryL1DAO cl1DAO = new CategoryL1DAOImpl();
		
		List<CategoryL1> categories = cl1DAO.listAllL1Category();
		logger.info("L1 Categories: " + categories);
		
		request.setAttribute("parentCategories", categories);
		return "to_subcategory_success";
	}

	
	public String addSubCategory()
	{
		String parentCategoryName = request.getParameter("parentCategory");
		String subCategoryName = request.getParameter("subCategory");
		CategoryL2 c2 = new CategoryL2();
		c2.setParentCategoryName(parentCategoryName);
		c2.setCategoryName(subCategoryName);
		
		logger.info("parent category name: " + parentCategoryName);
		logger.info("sub category name: " + subCategoryName);
		
		
		CategoryL1DAO cl1DAO = new CategoryL1DAOImpl();		
		List<CategoryL1> categories = cl1DAO.listAllL1Category();		
		request.setAttribute("parentCategories", categories);
		
		
		
		CategoryL2DAO categoryL2DAO = new CategoryL2DAOImpl();
		
		if(categoryL2DAO.isSubCategoryExist(c2))
		{
			request.setAttribute("addSubCategoryResult", "Failed: Category [ " + parentCategoryName + " , " + subCategoryName + " ] is already exist");
			return "add_sub_category_failed";
		}
		
		boolean result = categoryL2DAO.addL2Category(c2);

		if(result == true)
		{
			request.setAttribute("addSubCategoryResult", "Add category [" + parentCategoryName + " , " + subCategoryName + " ] success");
			return "add_sub_category_success";
		}
		else
		{
			request.setAttribute("addSubCategoryResult", "Create Category [ " + parentCategoryName + " , " +  subCategoryName + " ] failed, ask adminstrator for help");
			return "add_sub_category_failed";
			
		}	
		
	}
	
	public String toPostManage()
	{
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null)
		{
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		
		PostPageDataDAO ppdDAO = new PostPageDataDAOImpl();	
		
		PostPageData postPageData = ppdDAO.getPostPageData(pageNum);
		this.postPageData = postPageData;
		logger.info("pageNum: " + pageNum);
		logger.info("postPageData = " + postPageData);
		logger.info("postPageDataSize = " + postPageData.getList().size());
		request.setAttribute("postPageData", postPageData);
		return "to_post_manage_success";
	}
	
	public String editPost()
	{
		String pidStr = request.getParameter("pid");
		logger.info("view pidStr = " + pidStr);
		
		int pid = 0;
		if(pidStr != null)
		{
			pid = Integer.parseInt(pidStr);
		}
		logger.info("pid = " + pid);
		
		loadCategoryAndSubCategory();
		
		PostDAO pDAO = new PostDAOImpl();
		Post p = pDAO.getPost(pid);
		
		request.setAttribute("postToEdit", p);
		return "view_post_success";
	}
	
	
	public String editCategory()
	{
		
		String cid = request.getParameter("cid");
		String pageNum = request.getParameter("pageNum");
		String categoryName = request.getParameter("categoryName");
		logger.info("cid to view: " + cid);
		logger.info("pageNum to view: " + pageNum);
		logger.info("category name to view: " + categoryName);
		request.setAttribute("cid", cid);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("categoryName", categoryName);
		return "view_category_success";
	}
	
	public String editSubCategory()
	{
		
		String scid = request.getParameter("scid");
		String pageNum = request.getParameter("pageNum");
		String parentCategoryName = request.getParameter("parentCategoryName");
		String categoryName = request.getParameter("categoryName");
		logger.info("scid to view: " + scid);
		logger.info("pageNum to view: " + pageNum);
		logger.info("parent category name to view: " + parentCategoryName);
		logger.info("category name to view: " + categoryName);
		request.setAttribute("scid", scid);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("parentCategoryName", parentCategoryName);
		request.setAttribute("categoryName", categoryName);
		
		CategoryL1DAO dao = new CategoryL1DAOImpl();
		List<CategoryL1> categories = dao.listAllL1Category();
		request.setAttribute("parentCategories", categories);
		logger.info("parent categories: " + categories);
		return "view_sub_category_success";
	}
	
	public String deletePost()
	{
		String pidStr = request.getParameter("pid");
		logger.info("delete pid = " + pidStr);
		int pid = Integer.parseInt(pidStr);		
		PostDAO postDAO = new PostDAOImpl();
		postDAO.deletePost(pid);
		
		String pageNumStr = request.getParameter("pageNum");
		logger.info("pageNumStr = " + pageNumStr);
		if(pageNumStr == null)
			pageNumStr = "1";
		
		this.setPageNumForPost(Integer.parseInt(pageNumStr));
		
		return "delete_post_success";
	}
	
	public String getPostPreNextPageData()
	{
		
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null)
		{
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		
		PostPageDataDAO ppdDAO = new PostPageDataDAOImpl();	
		
		
		PostPageData postPageData = ppdDAO.getPostPageData(pageNum);
		this.postPageData = postPageData;
		logger.info("pageNum: " + pageNum);
		logger.info("postPageData = " + postPageData);
		logger.info("postPageDataSize = " + postPageData.getList().size());
		request.setAttribute("postPageData", postPageData);
		return "get_post_page_data_success";
		
		
	}
	
	public String getCategoryPreNextPageData()
	{
		
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null)
		{
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		
		CategoryPageDataDAO ppdDAO = new CategoryPageDataDAOImpl();	
		
		
		CategoryPageData categoryPageData = ppdDAO.getCategoryPageData(pageNum);
		this.categoryPageData = categoryPageData;
		logger.info("pageNum: " + pageNum);
		logger.info("categoryPageData = " + categoryPageData);
		logger.info("categoryPageDataSize = " + categoryPageData.getList().size());
		logger.info("categoryPageData = " + categoryPageData.getList());
		request.setAttribute("categoryPageData", categoryPageData);
		request.setAttribute("allL1Categories", categoryPageData.getList());
		return "get_category_page_data_success";
		
		
	}
	
	public String getSubCategoryPreNextPageData()
	{
		
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null)
		{
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		
		SubCategoryPageDataDAO ppdDAO = new SubCategoryPageDataDAOImpl();	
		
		
		SubCategoryPageData subCategoryPageData = ppdDAO.getSubCategoryPageData(pageNum);
		this.subCategoryPageData = subCategoryPageData;
		logger.info("pageNum: " + pageNum);
		logger.info("subCategoryPageData = " + subCategoryPageData);
		logger.info("subCategoryPageDataSize = " + subCategoryPageData.getList().size());
		logger.info("subCategoryPageData = " + subCategoryPageData.getList());
		request.setAttribute("subCategoryPageData", subCategoryPageData);
		request.setAttribute("allL2Categories", subCategoryPageData.getList());
		return "get_sub_category_page_data_success";
		
		
	}
	
	public String updatePost()
	{
		String pidStr = request.getParameter("pid");
		int pid = Integer.parseInt(pidStr);
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String subCategory = request.getParameter("subCategory");
		
		
		
		if(subCategory.equals("----Please Select Sub Category----"))
		{
			subCategory = null;
		}
		
		String isMarkedWelcome = request.getParameter("isMarkedWelcome");
		String author = request.getParameter("author");
		String authorHomePageURL = request.getParameter("authorHomePage");
		String forumURL = request.getParameter("forumPage");
		if(authorHomePageURL == null)
		{
			authorHomePageURL = "";
		}
		if(forumURL == null)
		{
			forumURL = "";
		}
		if(isMarkedWelcome == null)
		{
			isMarkedWelcome = "0";
		}
		
		authorHomePageURL = authorHomePageURL.trim();
		if(authorHomePageURL.startsWith("http://") == false)
			authorHomePageURL = "http://" + authorHomePageURL;		
		
		forumURL = forumURL.trim();
		if(forumURL.startsWith("http://") == false)
		{
			forumURL = "http://" + forumURL;
		}
		
		PostDAO pDAO = new PostDAOImpl();
		Post p = pDAO.getPost(pid);
		p.setTitle(title);
		p.setDescription(description);
		p.setCatagory(category);
		p.setSubCatagory(subCategory);
		p.setAuthor(author);
		p.setAuthorHomePageURL(authorHomePageURL);
		p.setIsWelcomePost(isMarkedWelcome);
		p.setForumURL(forumURL);
		
		
		if(this.yourFile != null)
		{
			String newFileName;
			long now = new Date().getTime();

			newFileName = Long.toString(now) + "_" + yourFileFileName;
			logger.info("rename uploaded file to: " + newFileName);	
			String softwareURI = "/CryoEM/post/download.action?f=" + newFileName;
			p.setSoftwareURI(softwareURI);
			p.setSoftwareFileName(this.yourFileFileName);
			writeUploadFile(newFileName);
		}
		
		/*
		 * 在添加新的post之前，如果当前提交的post被标记为欢迎软件的话，则需要将之前标记为欢迎软件的标记给去掉
		 */
		
		if(isMarkedWelcome.equals("1"))
		{
			unMarkPreviousWelcomePost();
		}
		
		boolean result = pDAO.updatePost(p);
		request.setAttribute("postToEdit", p);
		loadCategoryAndSubCategory();
		
		if(result)
		{
			request.setAttribute("updatePostResult", "Update Successed");
			return "update_post_success";
		}
		else
		{
			request.setAttribute("updatePostResult", "Update Failed");
			return "update_post_failed";
		}
	}
	
	public String toCategoryManage()
	{
		
		
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null)
		{
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		
		CategoryPageDataDAO ppdDAO = new CategoryPageDataDAOImpl();	
		
		CategoryPageData categoryPageData = ppdDAO.getCategoryPageData(pageNum);
		this.categoryPageData = categoryPageData;
		logger.info("pageNum: " + pageNum);
		logger.info("categoryPageData = " + categoryPageData);
		logger.info("categoryPageDataSize = " + categoryPageData.getList().size());
		request.setAttribute("allL1Categories", categoryPageData.getList());
		logger.info("allL1Categories: " + categoryPageData.getList());
		
		request.setAttribute("categoryPageData", categoryPageData);
		
		
		
		//Used for deciding whether to output update result info in web page
		String updateCategoryInfoSource = request.getParameter("updateCategoryInfoSource");
		String updateCategoryResult = request.getParameter("updateCategoryResult");
		
		logger.info("updateCategoryInfoSource = " + updateCategoryInfoSource);
		logger.info("updateCategoryResult = " + updateCategoryResult);
		
		request.setAttribute("updateCategoryInfoSource", updateCategoryInfoSource);
		request.setAttribute("updateCatetoryResult", updateCategoryResult);
		
		
		return "to_category_manage_success";
	}
	
	private int pageNumForCategory;	
	public int getPageNumForCategory() {
		return pageNumForCategory;
	}
	public void setPageNumForCategory(int pageNumForCategory) {
		this.pageNumForCategory = pageNumForCategory;
	}
	public String deleteCategory()
	{
		int cid = Integer.parseInt(request.getParameter("cid"));
		
				
		
		CategoryL1DAO dao = new CategoryL1DAOImpl();
		dao.deleteCategory(cid);
		
		String pageNumStr = request.getParameter("pageNum");
		logger.info("pageNumStr = " + pageNumStr);
		if(pageNumStr == null)
			pageNumStr = "1";
		
		this.setPageNumForCategory(Integer.parseInt(pageNumStr));
		
		return "delete_category_success";
	}
	
	
	public String deleteSubCategory()
	{
		int cid = Integer.parseInt(request.getParameter("scid"));
		
				
		
		CategoryL2DAO dao = new CategoryL2DAOImpl();
		dao.deleteSubCategory(cid);
		
		String pageNumStr = request.getParameter("pageNum");
		logger.info("pageNumStr = " + pageNumStr);
		if(pageNumStr == null)
			pageNumStr = "1";
		
		this.setPageNumForSubCategory(Integer.parseInt(pageNumStr));
		
		return "delete_sub_category_success";
	}
	
	
	private String updateCategoryInfoSource;
	private String updateCategoryResult;
	
	public String getUpdateCategoryInfoSource() {
		return updateCategoryInfoSource;
	}
	public void setUpdateCategoryInfoSource(String updateCatetoryInfoSource) {
		this.updateCategoryInfoSource = updateCatetoryInfoSource;
	}
	public String getUpdateCategoryResult() {
		return updateCategoryResult;
	}
	public void setUpdateCategoryResult(String updateCategoryResult) {
		this.updateCategoryResult = updateCategoryResult;
	}
	public String updateCategory()
	{
		int cid = Integer.parseInt(request.getParameter("cid"));
		String categoryName = request.getParameter("categoryName");
		String pageNumStr = request.getParameter("pageNum");
		logger.info("pageNumStr = " + pageNumStr);
		if(pageNumStr == null)
			pageNumStr = "1";
		
		logger.info("cid to update: " + cid);
		logger.info("categoryName to update:" + categoryName);
		logger.info("pageNumstr = " + pageNumStr);
		CategoryL1 c = new CategoryL1();
		c.setCid(cid);
		c.setCategoryName(categoryName);
		CategoryL1DAO dao = new CategoryL1DAOImpl();
		
		this.setPageNumForCategory(Integer.parseInt(pageNumStr));
		
		
		boolean isCategoryExist = dao.isCategoryExist(c);
		
		
		this.setUpdateCategoryInfoSource("from_update");
		if(isCategoryExist)
		{			
			this.setUpdateCategoryResult("Update Failed: \"" + categoryName + "\" is already existed");
			return "update_category_failed";
		}
		else
		{
			dao.updateCategory(c);		
			this.setUpdateCategoryResult("Update To Category Name \"" + categoryName + "\" Success");
			return "update_category_success";
			
		}
		
		
		
	}
	
	
	
	private int pageNumForSubCategory;	
	private SubCategoryPageData subCategoryPageData = null;
	
	
	public int getPageNumForSubCategory() {
		return pageNumForSubCategory;
	}
	public void setPageNumForSubCategory(int pageNumForSubCategory) {
		this.pageNumForSubCategory = pageNumForSubCategory;
	}
	
	public SubCategoryPageData getSubCategoryPageData() {
		return subCategoryPageData;
	}
	public void setSubCategoryPageData(SubCategoryPageData subCategoryPageData) {
		this.subCategoryPageData = subCategoryPageData;
	}
	public String toSubCategoryManage()
	{
		
		
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null)
		{
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		
		SubCategoryPageDataDAO ppdDAO = new SubCategoryPageDataDAOImpl();	
		
		SubCategoryPageData subCategoryPageData = ppdDAO.getSubCategoryPageData(pageNum);
		this.subCategoryPageData = subCategoryPageData;
		logger.info("pageNum: " + pageNum);
		logger.info("subCategoryPageData = " + subCategoryPageData);
		logger.info("subCategoryPageDataSize = " + subCategoryPageData.getList().size());
		request.setAttribute("allL2Categories", subCategoryPageData.getList());
		logger.info("allL2Categories: " + subCategoryPageData.getList());
		
		request.setAttribute("subCategoryPageData", subCategoryPageData);
		
		//Used for deciding whether to output update result info in web page
		String updateSubCategoryInfoSource = request.getParameter("updateSubCategoryInfoSource");
		String updateSubCategoryResult = request.getParameter("updateSubCategoryResult");
		
		logger.info("updateSubCategoryInfoSource = " + updateSubCategoryInfoSource);
		logger.info("updateSubCategoryResult = " + updateSubCategoryResult);
		
		request.setAttribute("updateSubCategoryInfoSource", updateSubCategoryInfoSource);
		request.setAttribute("updateSubCatetoryResult", updateSubCategoryResult);

		return "to_sub_category_manage_success";
	}
	
	private String updateSubCategoryInfoSource;
	private String updateSubCategoryResult;
	
	public String getUpdateSubCategoryInfoSource() {
		return updateSubCategoryInfoSource;
	}
	public void setUpdateSubCategoryInfoSource(String updateSubCategoryInfoSource) {
		this.updateSubCategoryInfoSource = updateSubCategoryInfoSource;
	}
	public String getUpdateSubCategoryResult() {
		return updateSubCategoryResult;
	}
	public void setUpdateSubCategoryResult(String updateSubCategoryResult) {
		this.updateSubCategoryResult = updateSubCategoryResult;
	}
	public String updateSubCategory()
	{
		int scid = Integer.parseInt(request.getParameter("scid"));
		String parentCategoryName = request.getParameter("parentCategoryName");
		String categoryName = request.getParameter("categoryName");
		String pageNumStr = request.getParameter("pageNum");
		logger.info("pageNumStr = " + pageNumStr);
		if(pageNumStr == null)
			pageNumStr = "1";
		
		logger.info("scid to update: " + scid);
		logger.info("parentCategoryName to update:" + parentCategoryName);
		logger.info("categoryName to update:" + categoryName);
		logger.info("pageNumstr = " + pageNumStr);
		CategoryL2 c = new CategoryL2();
		c.setScid(scid);
		c.setParentCategoryName(parentCategoryName);
		c.setCategoryName(categoryName);
		CategoryL2DAO dao = new CategoryL2DAOImpl();
		//dao.updateSubCategory(c);
		this.setPageNumForSubCategory(Integer.parseInt(pageNumStr));
		
		boolean isCategoryExist = dao.isSubCategoryExist(c);
		
		
		this.setUpdateSubCategoryInfoSource("from_update");
		if(isCategoryExist)
		{			
			this.setUpdateSubCategoryResult("Update Failed: \"" + parentCategoryName + "," + categoryName + "\" is already existed");
			return "update_sub_category_failed";
		}
		else
		{
			dao.updateSubCategory(c);		
			this.setUpdateSubCategoryResult("Update To Category Name \"" + parentCategoryName + "," + categoryName + "\" Success");
			return "update_sub_category_success";
			
		}
		
	}
	
	public String toGenDownloadURL()
	{
		return "to_gen_download_url_success";
	}
	
	public String toAddCategory()
	{
		return "to_add_category_success";
	}
	
}
