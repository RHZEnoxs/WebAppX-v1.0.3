package org.fkit.hrm.service;

import java.util.List;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.util.tag.PageModel;

/**
 * @Description: 人事管理服務層接口
 * <br>網站：<a href="http://www.fkit.org">瘋狂Java</a>
 * @author 肖文吉	36750064@qq.com
 * @version V1.0
 */
public interface HrmService {


	/**
	 * 用戶登錄
	 * @param  loginname
	 * @param  password
	 * @return User對像
	 * */
	User login(String loginname,String password);

	/**
	 * 根據id查詢用戶
	 * @param id
	 * @return 用戶對像
	 * */
	User findUserById(Integer id);

	/**
	 * 獲得所有用戶
	 * @return User對象的List集合
	 * */
	List<User> findUser(User user,PageModel pageModel);

	/**
	 * 根據id刪除用戶
	 * @param id
	 * */
	void removeUserById(Integer id);

	/**
	 * 修改用戶
	 * @param User 用戶對像
	 * */
	void modifyUser(User user);

	/**
	 * 添加用戶
	 * @param User 用戶對像
	 * */
	void addUser(User user);

	/**
	 * 獲得所有員工
	 * @param employee 查詢條件
	 * @param pageModel 分頁對像
	 * @return Dept對象的List集合
	 * */
	List<Employee> findEmployee(Employee employee,PageModel pageModel);

	/**
	 * 根據id刪除員工
	 * @param id
	 * */
	void removeEmployeeById(Integer id);

	/**
	 * 根據id查詢員工
	 * @param id
	 * @return 員工對像
	 * */
	Employee findEmployeeById(Integer id);

	/**
	 * 添加員工
	 * @param employee 員工對像
	 * */
	void addEmployee(Employee employee);

	/**
	 * 修改員工
	 * @param employee 員工對像
	 * */
	void modifyEmployee(Employee employee);

	/**
	 * 獲得所有部門，分頁查詢
	 * @return Dept對象的List集合
	 * */
	List<Dept> findDept(Dept dept,PageModel pageModel);

	/**
	 * 獲得所有部門
	 * @return Dept對象的List集合
	 * */
	List<Dept> findAllDept();

	/**
	 * 根據id刪除部門
	 * @param id
	 * */
	public void removeDeptById(Integer id);

	/**
	 * 添加部門
	 * @param dept 部門對像
	 * */
	void addDept(Dept dept);

	/**
	 * 根據id查詢部門
	 * @param id
	 * @return 部門對像
	 * */
	Dept findDeptById(Integer id);

	/**
	 * 修改部門
	 * @param dept 部門對像
	 * */
	void modifyDept(Dept dept);

	/**
	 * 獲得所有職位
	 * @return Job對象的List集合
	 * */
	List<Job> findAllJob();

	List<Job> findJob(Job job,PageModel pageModel);

	/**
	 * 根據id刪除職位
	 * @param id
	 * */
	public void removeJobById(Integer id);

	/**
	 * 添加職位
	 * @param Job 部門對像
	 * */
	void addJob(Job job);

	/**
	 * 根據id查詢職位
	 * @param id
	 * @return 職位對像
	 * */
	Job findJobById(Integer id);

	/**
	 * 修改職位
	 * @param dept 部門對像
	 * */
	void modifyJob(Job job);


	/**
	 * 獲得所有公告
	 * @return Notice對象的List集合
	 * */
	List<Notice> findNotice(Notice notice,PageModel pageModel);

	/**
	 * 根據id查詢公告
	 * @param id
	 * @return 公告對像
	 * */
	Notice findNoticeById(Integer id);

	/**
	 * 根據id刪除公告
	 * @param id
	 * */
	public void removeNoticeById(Integer id);

	/**
	 * 添加公告
	 * @param Notice 公告對像
	 * */
	void addNotice(Notice notice);

	/**
	 * 修改公告
	 * @param Notice 公告對像
	 * */
	void modifyNotice(Notice notice);

	/**
	 * 獲得所有文檔
	 * @return Document對象的List集合
	 * */
	List<Document> findDocument(Document document,PageModel pageModel);

	/**
	 * 添加文檔
	 * @param Document 文件對像
	 * */
	void addDocument(Document document);

	/**
	 * 根據id查詢文檔
	 * @param id
	 * @return 文檔對像
	 * */
	Document findDocumentById(Integer id);

	/**
	 * 根據id刪除文檔
	 * @param id
	 * */
	public void removeDocumentById(Integer id);

	/**
	 * 修改文檔
	 * @param Document 公告對像
	 * */
	void modifyDocument(Document document);


}
