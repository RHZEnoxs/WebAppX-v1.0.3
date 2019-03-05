package org.fkit.hrm.controller;


import java.util.List;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Employee;
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

/**
 * @Description: 處理員工請求控制器
 */
@Controller
public class EmployeeController {
	/**
	 * 自動注入hrmService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;

	/**
	 * 處理查詢請求
	 * @param pageIndex 請求的是第幾頁
	 * @param String job_id 職位編號
	 * @param String dept_id 部門編號
	 * @param employee 模糊查詢參數
	 * @param Model model
	 * */
	@RequestMapping(value="/employee/selectEmployee")
	public String selectEmployee(Integer pageIndex,
								 Integer job_id,Integer dept_id,
								 @ModelAttribute Employee employee,
								 Model model){
		// 模糊查詢時判斷是否有關聯對像傳遞，如果有，創建並封裝關聯對像
		this.genericAssociation(job_id, dept_id, employee);
		// 創建分頁對像
		PageModel pageModel = new PageModel();
		// 如果參數pageIndex不為null，設置pageIndex，即顯示第幾頁
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		// 查詢職位信息，用於模糊查詢
		List<Job> jobs = hrmService.findAllJob();
		// 查詢部門信息 ，用於模糊查詢
		List<Dept> depts = hrmService.findAllDept();
		// 查詢員工信息
		List<Employee> employees = hrmService.findEmployee(employee,pageModel);
		// 設置Model數據
		model.addAttribute("employees", employees);
		model.addAttribute("jobs", jobs);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		// 返回員工頁面
		return "employee/employee";

	}

	/**
	 * 處理添加員工請求
	 * @param String flag 標記， 1表示跳轉到添加頁面，2表示執行添加操作
	 * @param String job_id 職位編號
	 * @param String dept_id 部門編號
	 * @param Employee employee 接收添加參數
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/employee/addEmployee")
	public ModelAndView addEmployee(
			String flag,
			Integer job_id,Integer dept_id,
			@ModelAttribute Employee employee,
			ModelAndView mv){
		if(flag.equals("1")){
			// 查詢職位信息
			List<Job> jobs = hrmService.findAllJob();
			// 查詢部門信息
			List<Dept> depts = hrmService.findAllDept();
			// 設置Model數據
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			// 返回添加員工頁面
			mv.setViewName("employee/showAddEmployee");
		}else{
			// 判斷是否有關聯對像傳遞，如果有，創建關聯對像
			this.genericAssociation(job_id, dept_id, employee);
			// 添加操作
			hrmService.addEmployee(employee);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		// 返回
		return mv;

	}

	/**
	 * 處理刪除員工請求
	 * @param String ids 需要刪除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/employee/removeEmployee")
	public ModelAndView removeEmployee(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根據id刪除員工
			hrmService.removeEmployeeById(Integer.parseInt(id));
		}
		// 設置客戶端跳轉到查詢請求
//		mv.setView(new RedirectView("/hrmapp/employee/selectEmployee"));
//		mv.setViewName("forward:/employee/selectEmployee");
		mv.setViewName("redirect:/employee/selectEmployee");
		// 返回ModelAndView
		return mv;
	}

	/**
	 * 處理修改員工請求
	 * @param String flag 標記，1表示跳轉到修改頁面，2表示執行修改操作
	 * @param String job_id 職位編號
	 * @param String dept_id 部門編號
	 * @param Employee employee  要修改員工的對象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/employee/updateEmployee")
	public ModelAndView updateEmployee(
			String flag,
			Integer job_id,Integer dept_id,
			@ModelAttribute Employee employee,
			ModelAndView mv){
		if(flag.equals("1")){
			// 根據id查詢員工
			Employee target = hrmService.findEmployeeById(employee.getId());
			// 需要查詢職位信息
			List<Job> jobs = hrmService.findAllJob();
			// 需要查詢部門信息
			List<Dept> depts = hrmService.findAllDept();
			// 設置Model數據
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			mv.addObject("employee", target);
			// 返回修改員工頁面
			mv.setViewName("employee/showUpdateEmployee");
		}else{
			// 創建並封裝關聯對像
			this.genericAssociation(job_id, dept_id, employee);
			System.out.println("updateEmployee -->> " + employee);
			// 執行修改操作
			hrmService.modifyEmployee(employee);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		// 返回
		return mv;
	}

	/**
	 * 由於部門和職位在Employee中是對像關聯映射，
	 * 所以不能直接接收參數，需要創建Job對像和Dept對像
	 * */
	private void genericAssociation(Integer job_id,
									Integer dept_id,Employee employee){
		if(job_id != null){
			Job job = new Job();
			job.setId(job_id);
			employee.setJob(job);
		}
		if(dept_id != null){
			Dept dept = new Dept();
			dept.setId(dept_id);
			employee.setDept(dept);
		}
	}

}
