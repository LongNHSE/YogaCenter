/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.filter;

import com.mycompany.yogacenterproject.dto.HocVienDTO;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebFilter("/Authentication")
public class Authentication implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        String uri = req.getRequestURI();
////		this.context.log("Requested Resource::"+uri);
//
//        HttpSession session = req.getSession(false);
//
//        HocVienDTO hocVienDTO = null;
//        try {
//            hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
//            this.context.log("Run to here and hocVienDTO: " + hocVienDTO);
//        } catch (Exception ex) {
//            hocVienDTO = null;
//        }
//
//        if (hocVienDTO == null && uri.contains("Admin")) {
//            this.context.log("Unauthorized access request");
//            res.sendRedirect("/YogaCenter/Authentication/signin.jsp");
//        } else {
//            // pass the request along the filter chain
//            chain.doFilter(request, response);
//        }
    }

    public void destroy() {
        //close any resources here
        this.context.log("Destroy anything!!!");

    }

}
