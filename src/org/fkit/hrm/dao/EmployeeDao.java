package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.EmployeeDynaSqlProvider;
import org.fkit.hrm.domain.Employee;
import static org.fkit.hrm.util.common.HrmConstants.EMPLOYEETABLE;

/**
 * @Description: EmployeeMapper接口
 */
public interface EmployeeDao {

	// 根據參數查詢員工總數
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);

	// 根據參數動態查詢員工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWhitParam")
	@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="CARD_ID",property="cardId"),
			@Result(column="POST_CODE",property="postCode"),
			@Result(column="QQ_NUM",property="qqNum"),
			@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
			@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
			@Result(column="DEPT_ID",property="dept",
					one=@One(select="org.fkit.hrm.dao.DeptDao.selectById",
							fetchType=FetchType.EAGER)),
			@Result(column="JOB_ID",property="job",
					one=@One(select="org.fkit.hrm.dao.JobDao.selectById",
							fetchType=FetchType.EAGER))
	})
	List<Employee> selectByPage(Map<String, Object> params);

	// 動態插入員工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
	void save(Employee employee);

	// 根據id刪除員工
	@Delete(" delete from "+EMPLOYEETABLE+" where id = #{id} ")
	void deleteById(Integer id);

	// 根據id查詢員工
	@Select("select * from "+EMPLOYEETABLE+" where ID = #{id}")
	@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="CARD_ID",property="cardId"),
			@Result(column="POST_CODE",property="postCode"),
			@Result(column="QQ_NUM",property="qqNum"),
			@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
			@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
			@Result(column="DEPT_ID",property="dept",
					one=@One(select="org.fkit.hrm.dao.DeptDao.selectById",
							fetchType=FetchType.EAGER)),
			@Result(column="JOB_ID",property="job",
					one=@One(select="org.fkit.hrm.dao.JobDao.selectById",
							fetchType=FetchType.EAGER))
	})
	Employee selectById(Integer id);

	// 動態修改員工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
	void update(Employee employee);

}
