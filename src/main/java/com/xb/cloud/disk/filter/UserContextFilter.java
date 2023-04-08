package com.xb.cloud.disk.filter;

import com.xb.cloud.disk.constant.SysConstants;
import com.xb.cloud.disk.entity.User;
import com.xb.cloud.disk.support.UserContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author jack
 */
@WebFilter(urlPatterns = "/**")
@Component
public class UserContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        User user = (User) req.getSession().getAttribute(SysConstants.USER_CONTEXT_ATTR_NAME);
        UserContext.setUser(user);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            // clear
            UserContext.remove();
        }
    }
}
