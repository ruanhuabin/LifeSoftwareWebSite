package cn.edu.tsinghua.entity;

public class Post {
	private int pid;  //Post ID
	private String title; //Posted software title
	private String description; //Posted software detail description
	private String softwareURI; //Posted software URI
	
	private String catagory;
	private String subCatagory;
	
	private String isWelcomePost; // Used for determined whether this software is shown in welcome page
	private String author;
	private String authorHomePageURL;
	private String authorHeadIconURI; 
	private String forumURL;
	
	private String softwareFileName;//This attribute is only used for display in web page

	
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
	
	
	
	public String getSoftwareFileName() {
		return softwareFileName;
	}
	public void setSoftwareFileName(String softwareFileName) {
		this.softwareFileName = softwareFileName;
	}
	
	
	public String getIsWelcomePost() {
		return isWelcomePost;
	}
	public void setIsWelcomePost(String isWelcomePost) {
		this.isWelcomePost = isWelcomePost;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorHomePageURL() {
		return authorHomePageURL;
	}
	public void setAuthorHomePageURL(String authorHomePageURL) {
		this.authorHomePageURL = authorHomePageURL;
	}
	public String getAuthorHeadIconURI() {
		return authorHeadIconURI;
	}
	public void setAuthorHeadIconURI(String authorHeadIconURI) {
		this.authorHeadIconURI = authorHeadIconURI;
	}
	
	public String getForumURL() {
		return forumURL;
	}
	public void setForumURL(String forumURL) {
		this.forumURL = forumURL;
	}
	
	@Override
	public String toString() {
		return "Post [pid=" + pid + ", title=" + title + ", description="
				+ description + ", softwareURI=" + softwareURI + ", catagory="
				+ catagory + ", subCatagory=" + subCatagory
				+ ", isWelcomePost=" + isWelcomePost + ", author=" + author
				+ ", authorHomePageURL=" + authorHomePageURL
				+ ", authorHeadIconURI=" + authorHeadIconURI + ", forumURL="
				+ forumURL + ", softwareFileName=" + softwareFileName + "]";
	}
	public Post(int pid, String title, String description, String softwareURI) {
		super();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.softwareURI = softwareURI;
	}
	
	
	
	public Post(int pid, String title, String description, String softwareURI,
			String catagory, String subCatagory, String isWelcomePost,
			String author, String authorHomePageURL, String authorHeadIconURI,
			String softwareFileName) {
		super();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.softwareURI = softwareURI;
		this.catagory = catagory;
		this.subCatagory = subCatagory;
		this.isWelcomePost = isWelcomePost;
		this.author = author;
		this.authorHomePageURL = authorHomePageURL;
		this.authorHeadIconURI = authorHeadIconURI;
		this.softwareFileName = softwareFileName;
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
	public Post(int pid, String title, String description, String softwareURI,
			String catagory, String subCatagory, String isWelcomePost,
			String author, String authorHomePageURL, String authorHeadIconURI,
			String forumURL, String softwareFileName) {
		super();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.softwareURI = softwareURI;
		this.catagory = catagory;
		this.subCatagory = subCatagory;
		this.isWelcomePost = isWelcomePost;
		this.author = author;
		this.authorHomePageURL = authorHomePageURL;
		this.authorHeadIconURI = authorHeadIconURI;
		this.forumURL = forumURL;
		this.softwareFileName = softwareFileName;
	}
	
	
	
	
	
	
	

}
