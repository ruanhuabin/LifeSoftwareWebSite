package cn.edu.tsinghua.entity;

public class Post {
	private int pid;  //Post ID
	private String title; //Posted software title
	private String description; //Posted software detail description
	private String softwareURI; //Posted software URI
	
	private String catagory;
	private String subCatagory;

	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSoftwareURI() {
		return softwareURI;
	}
	public void setSoftwareURI(String softwareURI) {
		this.softwareURI = softwareURI;
	}
	
	
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public String getSubCatagory() {
		return subCatagory;
	}
	public void setSubCatagory(String subCatagory) {
		this.subCatagory = subCatagory;
	}
	
	@Override
	public String toString() {
		return "Post [pid=" + pid + ", title=" + title + ", description="
				+ description + ", softwareURI=" + softwareURI + ", catagory="
				+ catagory + ", subCatagory=" + subCatagory
				 + "]";
	}
	public Post(int pid, String title, String description, String softwareURI) {
		super();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.softwareURI = softwareURI;
	}
	
	public Post(int pid, String title, String description, String softwareURI,
			String catagory, String subCatagory, String subSubCatagory) {
		super();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.softwareURI = softwareURI;
		this.catagory = catagory;
		this.subCatagory = subCatagory;
		
	}
	public Post() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}