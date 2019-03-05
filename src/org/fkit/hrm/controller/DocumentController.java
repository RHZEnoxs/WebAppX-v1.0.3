package org.fkit.hrm.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 處理上傳下載文件請求控制器
 */

@Controller
public class DocumentController {

	/**
	 * 自動注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;


	/**
	 * 處理/login請求
	 * */
	@RequestMapping(value="/document/selectDocument")
	public String selectDocument(
			Model model,Integer pageIndex,
			@ModelAttribute  Document document){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查詢用戶信息     */
		List<Document> documents = hrmService.findDocument(document, pageModel);
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel", pageModel);
		return "document/document";

	}

	/**
	 * 處理添加請求
	 * @param String flag 標記， 1表示跳轉到上傳頁面，2表示執行上傳操作
	 * @param Notice notice  要添加的公告對像
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/document/addDocument")
	public ModelAndView addDocument(
			String flag,
			@ModelAttribute Document document,
			ModelAndView mv,
			HttpSession session)throws Exception{
		if(flag.equals("1")){
			mv.setViewName("document/showAddDocument");
		}else{
			// 上傳文件路徑
			String path = session.getServletContext().getRealPath(
					"/upload/");
			System.out.println(path);
			// 上傳文件名
			String fileName = document.getFile().getOriginalFilename();
			// 將上傳文件保存到一個目標文件當中
			document.getFile().transferTo(new File(path+File.separator+ fileName));

			// 插入數據庫
			// 設置fileName
			document.setFileName(fileName);
			// 設置關聯的User對像
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			document.setUser(user);
			// 插入數據庫
			hrmService.addDocument(document);
			// 返回
			mv.setViewName("redirect:/document/selectDocument");
		}
		// 返回
		return mv;
	}

	/**
	 * 處理刪除文檔請求
	 * @param String ids 需要刪除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/document/removeDocument")
	public ModelAndView removeDocument(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根據id刪除文檔
			hrmService.removeDocumentById(Integer.parseInt(id));
		}
		// 設置客戶端跳轉到查詢請求
		mv.setViewName("redirect:/document/selectDocument");
		// 返回ModelAndView
		return mv;
	}

	/**
	 * 處理修改文檔請求
	 * @param String flag 標記， 1表示跳轉到修改頁面，2表示執行修改操作
	 * @param Document document 要修改文檔的對象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/document/updateDocument")
	public ModelAndView updateDocument(
			String flag,
			@ModelAttribute Document document,
			ModelAndView mv){
		if(flag.equals("1")){
			// 根據id查詢文檔
			Document target = hrmService.findDocumentById(document.getId());
			// 設置Model數據
			mv.addObject("document", target);
			// 設置跳轉到修改頁面
			mv.setViewName("document/showUpdateDocument");
		}else{
			// 執行修改操作
			hrmService.modifyDocument(document);
			// 設置客戶端跳轉到查詢請求
			mv.setViewName("redirect:/document/selectDocument");
		}
		// 返回
		return mv;
	}

	/**
	 * 處理文檔下載請求
	 * @param String flag 標記， 1表示跳轉到修改頁面，2表示執行修改操作
	 * @param Document document 要修改文檔的對象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/document/downLoad")
	public ResponseEntity<byte[]>  downLoad(
			Integer id,
			HttpSession session) throws Exception{
		// 根據id查詢文檔
		Document target = hrmService.findDocumentById(id);
		String fileName = target.getFileName();
		// 上傳文件路徑
		String path = session.getServletContext().getRealPath(
				"/upload/");
		// 獲得要下載文件的File對像
		File file = new File(path+File.separator+ fileName);
		// 創建springframework的HttpHeaders對像
		HttpHeaders headers = new HttpHeaders();
		// 下載顯示的文件名，解決中文名稱亂碼問題
		String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		// 通知瀏覽器以attachment（下載方式）打開圖片
		headers.setContentDispositionFormData("attachment", downloadFielName);
		// application/octet-stream ： 二進制流數據（最常見的文件下載）。
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 201 HttpStatus.CREATED
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}

}
