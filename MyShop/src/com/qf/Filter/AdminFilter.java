package com.qf.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/admin.jsp")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String loginURI = httpRequest.getContextPath() + "/admin/login.jsp"; // Assuming login page is mapped to /login

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean resourceRequest = httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/admin/login.jsp"); // Assuming login action is mapped to /admin/login

        if (loggedIn || loginRequest || resourceRequest) {
            chain.doFilter(request, response); // Allow the request to pass through the filter
        } else {
            httpResponse.sendRedirect(loginURI); // Redirect to the login page
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if any
    }

    @Override
    public void destroy() {
        // Cleanup code, if any
    }
}
