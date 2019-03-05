package org.fkit.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;





/**
 * 動態頁面跳轉控制器
 * */
@Controller
public class FormController{

	@RequestMapping(value="/{formName}")
	public String loginForm(@PathVariable String formName){
		// 動態跳轉頁面
		return formName;
	}

}

