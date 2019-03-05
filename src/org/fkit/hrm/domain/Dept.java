package org.fkit.hrm.domain;

import java.io.Serializable;

public class Dept implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;		// id
	private String name;	// 部門名稱
	private String remark;	// 詳細描述
	// 無參數構造器
	public Dept() {
		super();
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
		return "Dept [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}

}