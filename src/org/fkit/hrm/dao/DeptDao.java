package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.DeptDynaSqlProvider;
import org.fkit.hrm.domain.Dept;
import static org.fkit.hrm.util.common.HrmConstants.DEPTTABLE;

/**
 * @Description: DeptMapper接口
 */
public interface DeptDao {

	// 動態查詢
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWhitParam")
	List<Dept> selectByPage(Map<String, Object> params);

	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);

	@Select("select * from "+DEPTTABLE+" ")
	List<Dept> selectAllDept();

	@Select("select * from "+DEPTTABLE+" where ID = #{id}")
	Dept selectById(int id);

	// 根據id刪除部門
	@Delete(" delete from "+DEPTTABLE+" where id = #{id} ")
	void deleteById(Integer id);

	// 動態插入部門
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);

	// 動態修改用戶
	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update(Dept dept);
}
