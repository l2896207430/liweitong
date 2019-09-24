package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全登录过滤
 * 
 * @author Administrator
 *
 */
@WebFilter("/a")
public class SessionFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 获取路径中?后的字符串
		String queryString = req.getServletPath();
		// 获取会话Session里是否有s对象,有则为登录进入,无则未登录
		if (req.getSession().getAttribute("s") == null) {
			// 登录界面、注册页面可以允许通过
			if (queryString != null && (queryString.contains("/login.jsp") || queryString.contains("/loginCheck.jsp")
					|| queryString.contains("/addForm.jsp")||queryString.contains("/add.jsp"))) {
				// 请求地址放行
				chain.doFilter(req, resp);
			} else {
				// 未登录，无权访问，重定向到登录页面
				resp.sendRedirect("/servlet_sm1.0/login.jsp");
			}
		} else {
			// 请求地址放行
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
