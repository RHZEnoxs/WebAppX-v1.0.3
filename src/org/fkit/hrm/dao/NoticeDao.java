package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.NoticeDynaSqlProvider;
import org.fkit.hrm.domain.Notice;
import static org.fkit.hrm.util.common.HrmConstants.NOTICETABLE;


/**
 * @Description: NoticeMapper接口
 */
public interface NoticeDao {

	// 動態查詢
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWhitParam")
	@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
			@Result(column="USER_ID",property="user",
					one=@One(select="org.fkit.hrm.dao.UserDao.selectById",
							fetchType=FetchType.EAGER))
	})
	List<Notice> selectByPage(Map<String, Object> params);

	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);

	@Select("select * from "+NOTICETABLE+" where ID = #{id}")
	Notice selectById(int id);

	// 根據id刪除公告
	@Delete(" delete from "+NOTICETABLE+" where id = #{id} ")
	void deleteById(Integer id);

	// 動態插入公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insertNotice")
	void save(Notice notice);

	// 動態修改公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="updateNotice")
	void update(Notice notice);

}
