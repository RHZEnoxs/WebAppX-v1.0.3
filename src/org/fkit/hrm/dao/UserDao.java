package org.fkit.hrm.dao;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.UserDynaSqlProvider;
import org.fkit.hrm.domain.User;
import static org.fkit.hrm.util.common.HrmConstants.USERTABLE;

/**
 * @Description: UserMapper接口
 */
public interface UserDao {

	// 根據登錄名和密碼查詢員工
	@Select("select * from "+USERTABLE+" where loginname = #{loginname} and password = #{password}")
	User selectByLoginnameAndPassword(
			@Param("loginname") String loginname,
			@Param("password") String password);

	// 根據id查詢用戶
	@Select("select * from "+USERTABLE+" where ID = #{id}")
	User selectById(Integer id);

	// 根據id刪除用戶
	@Delete(" delete from "+USERTABLE+" where id = #{id} ")
	void deleteById(Integer id);

	// 動態修改用戶
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);

	// 動態查詢
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWhitParam")
	List<User> selectByPage(Map<String, Object> params);

	// 根據參數查詢用戶總數
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);

	// 動態插入用戶
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);

}
