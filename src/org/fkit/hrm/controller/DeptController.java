package org.fkit.hrm.controller;

import java.util.List;

import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 處理部門請求控制器
 */

@Controller
public class DeptController {

	/**
	 * 自動注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;

	/**
	 * 處理/login請求
	 * */
	@RequestMapping(value="/dept/selectDept")
	public String selectDept(Model model,Integer pageIndex,
							 @ModelAttribute Dept dept){
		System.out.println("selectDept -->>");
		System.out.println("pageIndex = " + pageIndex);
		System.out.println("dept = " + dept);
		PageModel pageModel = new PageModel();
		System.out.println("getPageIndex = " + pageModel.getPageIndex());
		System.out.println("getPageSize = " + pageModel.getPageSize());
		System.out.println("getRecordCount = " + pageModel.getRecordCount());
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查詢用戶信息     */
		List<Dept> depts = hrmService.findDept(dept, pageModel);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		return "dept/dept";

	}

	/**
	 * 處理刪除部門請求
	 * @param String ids 需要刪除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/dept/removeDept")
	public ModelAndView removeDept(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根據id刪除部門
			hrmService.removeDeptById(Integer.parseInt(id));
		}
		// 設置客戶端跳轉到查詢請求
		mv.setViewName("redirect:/dept/selectDept");
		// 返回ModelAndView
		return mv;
	}

	/**
	 * 處理添加請求
	 * @param String flag 標記， 1表示跳轉到添加頁面，2表示執行添加操作
	 * @param Dept  dept  要添加的部門對像
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/dept/addDept")
	public ModelAndView addDept(
			String flag,
			@ModelAttribute Dept dept,
			ModelAndView mv){
		if(flag.equals("1")){
			// 設置跳轉到添加頁面
			mv.setViewName("dept/showAddDept");
		}else{
			// 執行添加操作
			hrmService.addDept(dept);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/dept/selectDept");
		}
		// 返回
		return mv;
	}


	/**
	 * 處理修改部門請求
	 * @param String flag 標記， 1表示跳轉到修改頁面，2表示執行修改操作
	 * @param Dept dept 要修改部門的對象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/dept/updateDept")
	public ModelAndView updateDpet(
			String flag,
			@ModelAttribute Dept dept,
			ModelAndView mv){
		if(flag.equals("1")){
			// 根據id查詢部門
			Dept target = hrmService.findDeptById(dept.getId());
			// 設置Model數據
			mv.addObject("dept", target);
			// 設置跳轉到修改頁面
			mv.setViewName("dept/showUpdateDept");
		}else{
			// 執行修改操作
			hrmService.modifyDept(dept);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/dept/selectDept");
		}
		// 返回
		return mv;
	}

}
