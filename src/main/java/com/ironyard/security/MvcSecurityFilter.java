package com.ironyard.security;

import com.ironyard.data.IronUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jasonskipper on 10/31/16.
 */

// used for controllers in the mvc folder
public class MvcSecurityFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse resp = ((HttpServletResponse) response);

        // check session

        IronUser usr = (IronUser) req.getSession().getAttribute("user");
        boolean authorized = (usr != null);

        // check destination
        if (!authorized){
            authorized = req.getRequestURI().contains("wideopen");
        }

        if(!authorized){
            authorized =  req.getRequestURI().contains("login");
        }

        if(authorized) {
            chain.doFilter(request, response);
        }else{
            resp.sendRedirect("/mvc/login.jsp");
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}