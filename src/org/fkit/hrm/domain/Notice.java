package org.fkit.hrm.domain;

import java.io.Serializable;

public class Notice implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;		// 編號
	private String title;   // 標題
	private String content; // 內容
	private java.util.Date createDate;  // 發佈日期
	private User user;		// 發佈人
	// 無參數構造器
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	// setter和getter方法
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content
				+ ", createDate=" + createDate + ", user=" + user + "]";
	}



}