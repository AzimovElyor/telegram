package com.example.springmvc.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;
@WebFilter(filterName = "filterProject", urlPatterns = {"/*"})
public class FilterProject implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        UUID userId = (UUID)session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        String url = req.getServletPath();
        if(!url.equals("/sign-in") && !url.equals("/sign-up") && !url.equals("/")){
            if(userId != null || username != null){
                resp.sendRedirect("/");
            }
        }else{
            filterChain.doFilter(req,resp);
        }

    }
}
