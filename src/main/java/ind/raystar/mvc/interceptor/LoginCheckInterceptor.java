package ind.raystar.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ind.raystar.mvc.dto.MySession;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = ((MySession) request.getSession().getAttribute("mySession")).getId();
		if (id == null) {
			request.getRequestDispatcher("/WEB-INF/views/alert/not-logged-in.jsp").forward(request, response);
			return false;
		} else {
			return true;
		}
	}
}
