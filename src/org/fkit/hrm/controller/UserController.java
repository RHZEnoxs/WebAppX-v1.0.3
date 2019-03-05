package org.fkit.hrm.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 處理用戶請求控制器
 * */
@Controller
public class UserController {

	/**
	 * 自動注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;

	/**
	 * 處理登錄請求
	 * @param String loginname  登錄名
	 * @param String password 密碼
	 * @return 跳轉的視圖
	 * */
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam("loginname") String loginname,
							  @RequestParam("password") String password,
							  HttpSession session,
							  ModelAndView mv){
		// 調用業務邏輯組件判斷用戶是否可以登錄
		User user = hrmService.login(loginname, password);
		if(user != null){
			// 將用戶保存到HttpSession當中
			session.setAttribute(HrmConstants.USER_SESSION, user);
			// 客戶端跳轉到main頁面
			mv.setViewName("redirect:/main");
		}else{
			// 設置登錄失敗提示信息
			mv.addObject("message", "登錄名或密碼錯誤!請重新輸入");
			// 服務器內部跳轉到登錄頁面
			mv.setViewName("forward:/loginForm");
		}
		return mv;

	}

	/**
	 * 處理查詢請求
	 * @param pageIndex 請求的是第幾頁
	 * @param employee 模糊查詢參數
	 * @param Model model
	 * */
	@RequestMapping(value="/user/selectUser")
	public String selectUser(Integer pageIndex,
							 @ModelAttribute User user,
							 Model model){
		System.out.println("user = " + user);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查詢用戶信息     */
		List<User> users = hrmService.findUser(user, pageModel);
		model.addAttribute("users", users);
		model.addAttribute("pageModel", pageModel);
		return "user/user";

	}

	/**
	 * 處理刪除用戶請求
	 * @param String ids 需要刪除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/user/removeUser")
	public ModelAndView removeUser(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根據id刪除員工
			hrmService.removeUserById(Integer.parseInt(id));
		}
		// 設置客戶端跳轉到查詢請求
		mv.setViewName("redirect:/user/selectUser");
		// 返回ModelAndView
		return mv;
	}


	/**
	 * 處理修改用戶請求
	 * @param String flag 標記， 1表示跳轉到修改頁面，2表示執行修改操作
	 * @param User user  要修改用戶的對象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/user/updateUser")
	public ModelAndView updateUser(
			String flag,
			@ModelAttribute User user,
			ModelAndView mv){
		if(flag.equals("1")){
			// 根據id查詢用戶
			User target = hrmService.findUserById(user.getId());
			// 設置Model數據
			mv.addObject("user", target);
			// 返回修改員工頁面
			mv.setViewName("user/showUpdateUser");
		}else{
			// 執行修改操作
			hrmService.modifyUser(user);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/user/selectUser");
		}
		// 返回
		return mv;
	}


	/**
	 * 處理添加請求
	 * @param String flag 標記， 1表示跳轉到添加頁面，2表示執行添加操作
	 * @param User user  要添加用戶的對象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/user/addUser")
	public ModelAndView addUser(
			String flag,
			@ModelAttribute User user,
			ModelAndView mv){
		if(flag.equals("1")){
			// 設置跳轉到添加頁面
			mv.setViewName("user/showAddUser");
		}else{
			// 執行添加操作
			hrmService.addUser(user);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/user/selectUser");
		}
		// 返回
		return mv;
	}

	/**
	 * 處理註銷退出請求
	 * @param String flag 標記， 1表示跳轉到添加頁面，2表示執行添加操作
	 * @param User user  要添加用戶的對象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/logout")
	public ModelAndView logout(
			ModelAndView mv,
			HttpSession session) {
		// 註銷session
		session.invalidate();
		// 跳轉到登錄頁面
		mv.setViewName("redirect:/loginForm");
		return mv;
	}

}
