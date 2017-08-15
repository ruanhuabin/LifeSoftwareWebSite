package cn.edu.tsinghua.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;



import com.opensymphony.xwork2.ActionSupport;



public class DownloadAction extends SuperAction {
	
	private String inputPath;
	private String uploadDir;	
	private String outputFilename;
	private String downloadDir;
	
	private static final Logger logger = Logger.getLogger("MyLogger");
	
	
	
	
	public String getDownloadDir() {
		return downloadDir;
	}

	public void setDownloadDir(String downloadDir) {
		this.downloadDir = downloadDir;
	}

	public String getOutputFilename() {
		return outputFilename;
	}

	public void setOutputFilename(String outputFilename) {
		this.outputFilename = outputFilename;
	}	

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
	public InputStream getSoftwareTargetFile() throws Exception
	{
		String fileName = request.getParameter("f");		
		this.setOutputFilename(fileName);
		logger.info("fileName = " + fileName);		
		String uploadDir = ServletActionContext.getServletContext().getRealPath(this.downloadDir);		
		String filePath = uploadDir + "/" + fileName;		
		logger.info("filePath = " + filePath);
		File file = new File(filePath);
		return new FileInputStream(file);
		
	}
	
	


}
