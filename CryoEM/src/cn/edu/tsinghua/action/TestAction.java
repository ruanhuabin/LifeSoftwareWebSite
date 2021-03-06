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
import cn.edu.tsinghua.po.CategoryL2;
import cn.edu.tsinghua.po.SubCategory;
import cn.edu.tsinghua.po.SubSubCategory;
import cn.edu.tsinghua.service.CategoryL1DAO;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.serviceimpl.CategoryL1DAOImpl;
import cn.edu.tsinghua.serviceimpl.PostDAOImpl;


public class TestAction extends SuperAction {
	
	private static final Logger logger = Logger.getLogger("UserLogger");
	private String username = "hello world";
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public static Logger getLogger() {
		return logger;
	}



	public String login()
	{
		username = request.getParameter("username");
		logger.info("username is: " + username);
		return "test_login_success";
	}
	
	
	

	
}
