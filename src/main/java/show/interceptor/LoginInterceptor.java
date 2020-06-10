package show.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取session
		HttpSession session = request.getSession();
		//获取uid
		Object id = session.getAttribute("uid");
		if(id == null){
			response.sendRedirect("/web/login1.html");
			return false;
		}
		//放行
		return true;
	}

}
