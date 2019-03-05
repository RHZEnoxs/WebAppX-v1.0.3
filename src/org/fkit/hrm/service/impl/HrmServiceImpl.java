package org.fkit.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fkit.hrm.dao.DeptDao;
import org.fkit.hrm.dao.DocumentDao;
import org.fkit.hrm.dao.EmployeeDao;
import org.fkit.hrm.dao.JobDao;
import org.fkit.hrm.dao.NoticeDao;
import org.fkit.hrm.dao.UserDao;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 人事管理系統服務層接口實現類
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{

	/**
	 * 自動注入持久層Dao對像
	 * */
	@Autowired
	private UserDao userDao;

	@Autowired
	private DeptDao deptDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private JobDao jobDao;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private DocumentDao documentDao;

	/*****************用戶服務接口實現*************************************/
	/**
	 * HrmServiceImpl接口login方法實現
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}

	/**
	 * HrmServiceImpl接口findUser方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user,PageModel pageModel) {
		/** 當前需要分頁的總數據條數  */
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
			/** 開始分頁查詢數據：查詢第幾頁的數據 */
			params.put("pageModel", pageModel);
		}
		List<User> users = userDao.selectByPage(params);

		return users;
	}

	/**
	 * HrmServiceImpl接口findUserById方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口removeUserById方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void removeUserById(Integer id) {
		userDao.deleteById(id);

	}

	/**
	 * HrmServiceImpl接口addUser方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void modifyUser(User user) {
		userDao.update(user);

	}

	/**
	 * HrmServiceImpl接口modifyUser方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void addUser(User user) {
		userDao.save(user);

	}

	/*****************部門服務接口實現*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {

		return deptDao.selectAllDept();
	}

	/**
	 * HrmServiceImpl接口findDept方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findDept(Dept dept,PageModel pageModel) {
		/** 當前需要分頁的總數據條數  */
		Map<String,Object> params = new HashMap<>();
		params.put("dept", dept);
		int recordCount = deptDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** 開始分頁查詢數據：查詢第幾頁的數據 */
			params.put("pageModel", pageModel);
		}

		List<Dept> depts = deptDao.selectByPage(params);

		return depts;
	}

	/**
	 * HrmServiceImpl接口removeUserById方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void removeDeptById(Integer id) {
		deptDao.deleteById(id);

	}

	/**
	 * HrmServiceImpl接口addDept方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void addDept(Dept dept) {
		deptDao.save(dept);

	}

	/**
	 * HrmServiceImpl接口findDeptById方法實現
	 * @see { HrmService }
	 * */
	@Override
	public Dept findDeptById(Integer id) {

		return deptDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口modifyDept方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void modifyDept(Dept dept) {
		deptDao.update(dept);

	}
	/*****************員工服務接口實現*************************************/
	/**
	 * HrmService接口findEmployee方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Employee> findEmployee(Employee employee,PageModel pageModel) {
		/** 當前需要分頁的總數據條數  */
		Map<String,Object> params = new HashMap<>();
		params.put("employee", employee);

		int recordCount = employeeDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** 開始分頁查詢數據：查詢第幾頁的數據 */
			params.put("pageModel", pageModel);
		}
		List<Employee> employees = employeeDao.selectByPage(params);
		return employees;
	}
	/**
	 * HrmService接口removeEmployeeById方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void removeEmployeeById(Integer id) {
		employeeDao.deleteById(id);

	}
	/**
	 * HrmService接口findEmployeeById方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Employee findEmployeeById(Integer id) {

		return employeeDao.selectById(id);
	}

	/**
	 * HrmService接口addEmployee方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void addEmployee(Employee employee) {
		employeeDao.save(employee);

	}

	/**
	 * HrmService接口modifyEmployee方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void modifyEmployee(Employee employee) {
		employeeDao.update(employee);
	}

	/*****************職位接口實現*************************************/

	/**
	 * HrmService接口findAllJob方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findAllJob() {

		return jobDao.selectAllJob();
	}

	/**
	 * HrmService接口findJob方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findJob(Job job, PageModel pageModel) {
		/** 當前需要分頁的總數據條數  */
		Map<String,Object> params = new HashMap<>();
		params.put("job", job);
		int recordCount = jobDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** 開始分頁查詢數據：查詢第幾頁的數據 */
			params.put("pageModel", pageModel);
		}

		List<Job> jobs = jobDao.selectByPage(params);

		return jobs;
	}

	/**
	 * HrmService接口removeJobById方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void removeJobById(Integer id) {
		jobDao.deleteById(id);

	}

	/**
	 * HrmService接口addJob方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void addJob(Job job) {
		jobDao.save(job);

	}

	/**
	 * HrmService接口findJobById方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Job findJobById(Integer id) {

		return jobDao.selectById(id);
	}

	/**
	 * HrmService接口modifyJob方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void modifyJob(Job job) {
		jobDao.update(job);

	}

	/*****************公告接口實現*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		/** 當前需要分頁的總數據條數  */
		Map<String,Object> params = new HashMap<>();
		params.put("notice", notice);
		int recordCount = noticeDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** 開始分頁查詢數據：查詢第幾頁的數據 */
			params.put("pageModel", pageModel);
		}

		List<Notice> notices = noticeDao.selectByPage(params);

		return notices;
	}

	/**
	 * HrmService接口findNoticeById方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Notice findNoticeById(Integer id) {

		return noticeDao.selectById(id);
	}

	/**
	 * HrmService接口removeNoticeById方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void removeNoticeById(Integer id) {
		noticeDao.deleteById(id);

	}

	/**
	 * HrmService接口addNotice方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void addNotice(Notice notice) {
		noticeDao.save(notice);

	}

	/**
	 * HrmService接口modifyNotice方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void modifyNotice(Notice notice) {
		noticeDao.update(notice);

	}

	/*****************文件接口實現*************************************/

	/**
	 * HrmService接口findDocument方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Document> findDocument(Document document, PageModel pageModel) {
		/** 當前需要分頁的總數據條數  */
		Map<String,Object> params = new HashMap<>();
		params.put("document", document);
		int recordCount = documentDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** 開始分頁查詢數據：查詢第幾頁的數據 */
			params.put("pageModel", pageModel);
		}

		List<Document> documents = documentDao.selectByPage(params);

		return documents;
	}

	/**
	 * HrmService接口addDocument方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void addDocument(Document document) {
		documentDao.save(document);

	}
	/**
	 * HrmService接口removeDocumentById方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void removeDocumentById(Integer id) {
		documentDao.deleteById(id);

	}
	/**
	 * HrmService接口modifyDocument方法實現
	 * @see { HrmService }
	 * */
	@Override
	public void modifyDocument(Document document) {
		documentDao.update(document);

	}
	/**
	 * HrmService接口findDocumentById方法實現
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Document findDocumentById(Integer id) {

		return documentDao.selectById(id);
	}


}
