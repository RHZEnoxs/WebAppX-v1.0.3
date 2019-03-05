package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.JobDynaSqlProvider;
import org.fkit.hrm.domain.Job;
import static org.fkit.hrm.util.common.HrmConstants.JOBTABLE;


/**
 * @Description:
 */
public interface JobDao {

	@Select("select * from "+JOBTABLE+" where ID = #{id}")
	Job selectById(int id);

	@Select("select * from "+JOBTABLE+" ")
	List<Job> selectAllJob();

	// 動態查詢
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWhitParam")
	List<Job> selectByPage(Map<String, Object> params);

	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);

	// 根據id刪除部門
	@Delete(" delete from "+JOBTABLE+" where id = #{id} ")
	void deleteById(Integer id);

	// 動態插入部門
	@SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
	void save(Job job);

	// 動態修改用戶
	@SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
	void update(Job job);
}
