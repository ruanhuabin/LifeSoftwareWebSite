package cn.edu.tsinghua.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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

	public String getOutputFilename() throws UnsupportedEncodingException {
		//因为文件名中可能还有中文，为了能够在下载对话框中显示中文文件名，需要对含有中文的文件名进行重新解码，否则，在下载对话框中显示中文文件名的时候，会出现乱码
		return new String(outputFilename.getBytes(), "ISO-8859-1");
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
		logger.info("File name before decoding:" + fileName);
		//先将表单中的中文文件名进行解码成utf-8以后才能够在后台找到正确的文件名。
		fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		this.setOutputFilename(fileName);
		logger.info("fileName = " + fileName);		
//		String uploadDir = ServletActionContext.getServletContext().getRealPath(this.downloadDir);	
//		
//		String filePath = uploadDir + "/" + fileName;
		String filePath = this.downloadDir + "/" + fileName;
		logger.info("filePath = " + filePath);
		File file = new File(filePath);
		return new FileInputStream(file);
		
	}
	
	


}
