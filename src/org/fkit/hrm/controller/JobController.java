package org.fkit.hrm.controller;

import org.fkit.hrm.domain.Job;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Description: 處理職位請求控制器
 */

@Controller
public class JobController {

	/**
	 * 自動注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;

	/**
	 * 處理/login請求
	 * */
	@RequestMapping(value="/job/selectJob")
	public String selectJob(Model model,Integer pageIndex,
							@ModelAttribute Job job){
		System.out.println("selectJob -->> " + job);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查詢用戶信息     */
		List<Job> jobs = hrmService.findJob(job, pageModel);
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageModel", pageModel);
		return "job/job";

	}

	/**
	 * 處理刪除職位請求
	 * @param String ids 需要刪除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/removeJob")
	public ModelAndView removeJob(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根據id刪除職位
			hrmService.removeJobById(Integer.parseInt(id));
		}
		// 設置客戶端跳轉到查詢請求
		mv.setViewName("redirect:/job/selectJob");
		// 返回ModelAndView
		return mv;
	}

	/**
	 * 處理添加請求
	 * @param String flag 標記， 1表示跳轉到添加頁面，2表示執行添加操作
	 * @param Job  job  要添加的職位對像
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/addJob")
	public ModelAndView addJob(
			String flag,
			@ModelAttribute Job job,
			ModelAndView mv){
		if(flag.equals("1")){
			// 設置跳轉到添加頁面
			mv.setViewName("job/showAddJob");
		}else{
			// 執行添加操作
			hrmService.addJob(job);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/job/selectJob");
		}
		// 返回
		return mv;
	}


	/**
	 * 處理修改職位請求
	 * @param String flag 標記， 1表示跳轉到修改頁面，2表示執行修改操作
	 * @param Job job 要修改部門的對象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/updateJob")
	public ModelAndView updateDpet(
			String flag,
			@ModelAttribute Job job,
			ModelAndView mv){
		if(flag.equals("1")){
			// 根據id查詢部門
			Job target = hrmService.findJobById(job.getId());
			// 設置Model數據
			mv.addObject("job", target);
			// 設置跳轉到修改頁面
			mv.setViewName("job/showUpdateJob");
		}else{
			// 執行修改操作
			hrmService.modifyJob(job);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/job/selectJob");
		}
		// 返回
		return mv;
	}
}
