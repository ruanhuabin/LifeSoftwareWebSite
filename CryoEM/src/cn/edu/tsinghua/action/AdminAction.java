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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import cn.edu.tsinghua.entity.Admin;
import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.service.AdminDAO;
import cn.edu.tsinghua.service.CategoryL1DAO;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.serviceimpl.AdminDAOImpl;
import cn.edu.tsinghua.serviceimpl.CategoryL1DAOImpl;
import cn.edu.tsinghua.serviceimpl.PostDAOImpl;


public class AdminAction extends SuperAction {
	
	private static final Logger logger = Logger.getLogger("AdminLogger");
	
	private String yourFileFileName;
	private String yourFileContentType;
	private File yourFile;
	private String uploadDir;	
	
	
	private String userFileFileName;
	private String userFileContentType;
	private File userFile;

	

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
	public String upload() {

		logger.info("filename = " + this.yourFileFileName);
		logger.info("filetype = " + this.yourFileContentType);

		String path = ServletActionContext.getServletContext().getRealPath(
				uploadDir);

		logger.info("path = " + path);

		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();

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
	
	
	public String postSoftware()
	{
		logger.info("upload filen name : " + this.yourFileFileName);
		logger.info("upload file type = " + this.yourFileContentType);

		String path = ServletActionContext.getServletContext().getRealPath(uploadDir);

		logger.info("file will be saved to path: " + path);

		File dir = new File(path);
		if (!dir.exists())
		{
			dir.mkdir();
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String newFileName;
		long now = new Date().getTime();

		newFileName = Long.toString(now) + "_" + yourFileFileName;
		logger.info("rename uploaded file to: " + newFileName);
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String softwareURI = "/CryoEM/post/download.action?f=" + newFileName;
		String category = request.getParameter("category");
		
		logger.info("post title: " + title);
		logger.info("post description: " + description);
		logger.info("post category:" + category);
		
		
		
		

		try {
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
			return "software_post_error";
					
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
		
		Post p = new Post();
		p.setTitle(title);
		p.setDescription(description);
		p.setSoftwareURI(softwareURI);
		p.setCatagory(category);
		PostDAO pDAO = new PostDAOImpl();
		pDAO.addPost(p);
		
		return "software_post_success";
	}
	
	
	public String toAdmin()
	{			
		Admin admin = (Admin)session.getAttribute("adminInfo");
		
		/*Load all categories so that admin page can list all category name in select widget*/
		CategoryL1DAO cl1DAO = new CategoryL1DAOImpl();
		List<CategoryL1> allCategory = cl1DAO.listAllL1Category();
		logger.info("all category is: " + allCategory);
		
		request.setAttribute("allL1Category", allCategory);
		if(admin != null)
			return "load_admin_page";
		else
			return "load_admin_login_page";
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
			
			/*Load all categories so that admin page can list all category name in select widget*/
			CategoryL1DAO cl1DAO = new CategoryL1DAOImpl();
			List<CategoryL1> allCategory = cl1DAO.listAllL1Category();
			logger.info("all category is: " + allCategory);
			
			request.setAttribute("allL1Category", allCategory);
			
			
			return "admin_login_success";
		}
		else
		{
			request.setAttribute("adminLoginResult", "Username or Password is not valid!");
			return "admin_login_failed";
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

		String path = ServletActionContext.getServletContext().getRealPath(
				uploadDir);

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

	
}