package org.fkit.hrm.domain;

import java.io.Serializable;

public class Job implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;			// id
	private String name;		// 職位名稱
	private String remark;		// 詳細描述
	// 無參數構造器
	public Job() {
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
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}

}