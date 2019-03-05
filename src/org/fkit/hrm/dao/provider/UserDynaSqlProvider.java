package org.fkit.hrm.dao.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.User;
import static org.fkit.hrm.util.common.HrmConstants.USERTABLE;

/**
 * @Description: 用戶動態SQL語句提供類
 */
public class UserDynaSqlProvider {
	// 分頁動態查詢
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(USERTABLE);
				if(params.get("user") != null){
					User user = (User)params.get("user");
					if(user.getUsername() != null && !user.getUsername().equals("")){
						WHERE("  username LIKE CONCAT ('%',#{user.username},'%') ");
					}
					if(user.getUserstatus() != null && !user.getUserstatus().equals("")){
						WHERE(" userstatus LIKE CONCAT ('%',#{user.userstatus},'%') ");
					}
				}
			}
		}.toString();

		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}

		return sql;
	}
	// 動態查詢總數量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(USERTABLE);
				if(params.get("user") != null){
					User user = (User)params.get("user");
					if(user.getUsername() != null && !user.getUsername().equals("")){
						WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
					}
					if(user.getUserstatus() != null && !user.getUserstatus().equals("")){
						WHERE(" userstatus LIKE CONCAT ('%',#{user.userstatus},'%') ");
					}
				}
			}
		}.toString();
	}

	// 動態插入
	public String insertUser(User user){
		return new SQL(){
			{
				INSERT_INTO(USERTABLE);
				if(user.getUsername() != null && !user.getUsername().equals("")){
					VALUES("username", "#{username}");
				}
				if(user.getUserstatus() != null && !user.getUserstatus().equals("")){
					VALUES("userstatus", "#{userstatus}");
				}
				if(user.getLoginname() != null && !user.getLoginname().equals("")){
					VALUES("loginname", "#{loginname}");
				}
				if(user.getPassword() != null && !user.getPassword().equals("")){
					VALUES("password", "#{password}");
				}
			}
		}.toString();
	}
	// 動態更新
	public String updateUser(User user){

		return new SQL(){
			{
				UPDATE(USERTABLE);
				if(user.getUsername() != null){
					SET(" username = #{username} ");
				}
				if(user.getLoginname() != null){
					SET(" loginname = #{loginname} ");
				}
				if(user.getPassword()!= null){
					SET(" password = #{password} ");
				}
				if(user.getUserstatus()!= null){
					SET(" userstatus = #{userstatus} ");
				}
				if(user.getCreateDate()!= null){
					SET(" create_date = #{createDate} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
