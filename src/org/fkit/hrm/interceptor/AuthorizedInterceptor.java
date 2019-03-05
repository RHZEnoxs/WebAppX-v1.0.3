package org.fkit.hrm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.util.common.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 判斷用戶權限的Spring MVC的攔截器
 */
public class AuthorizedInterceptor  implements HandlerInterceptor {

	/** 定義不需要攔截的請求 */
	private static final String[] IGNORE_URI = {"/loginForm", "/login","/404.html"};

	/**
	 * 該方法需要preHandle方法的返回值為true時才會執行。
	 * 該方法將在整個請求完成之後執行，主要作用是用於清理資源。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception exception)
			throws Exception {

	}

	/**
	 * 這個方法在preHandle方法返回值為true的時候才會執行。
	 * 執行時間是在處理器進行處理之 後，也就是在Controller的方法調用之後執行。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
						   Object handler, ModelAndView mv) throws Exception {

	}

	/**
	 * preHandle方法是進行處理器攔截用的，該方法將在Controller處理之前進行調用，
	 * 當preHandle的返回值為false的時候整個請求就結束了。
	 * 如果preHandle的返回值為true，則會繼續執行postHandle和afterCompletion。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
							 Object handler) throws Exception {
		/** 默認用戶沒有登錄 */
		boolean flag = false;
		/** 獲得請求的ServletPath */
		String servletPath = request.getServletPath();
		/**  判斷請求是否需要攔截 */
		for (String s : IGNORE_URI) {
			if (servletPath.contains(s)) {
				flag = true;
				break;
			}
		}
		/** 攔截請求 */
		if (!flag){
			/** 1.獲取session中的用戶  */
			User user = (User) request.getSession().getAttribute(HrmConstants.USER_SESSION);
			/** 2.判斷用戶是否已經登錄 */
			if(user == null){
				/** 如果用戶沒有登錄，跳轉到登錄頁面 */
				request.setAttribute("message", "請先登錄再訪問網站!");
				request.getRequestDispatcher(HrmConstants.LOGIN).forward(request, response);
				return flag;
			}else{
				flag = true;
			}
		}
		return flag;

	}

}
