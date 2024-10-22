/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.filter;

import com.mycompany.yogacenterproject.dto.AdminDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
//		this.context.log("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);
        AdminDTO adminDTO = null;
        HocVienDTO hocVienDTO = null;
        TrainerDTO trainerDTO = null;
        try {
            adminDTO = (AdminDTO) session.getAttribute("adminDTO");
            hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
            trainerDTO = (TrainerDTO) session.getAttribute("trainerDTO");
            this.context.log("Run to here and hocVienDTO: " + hocVienDTO);
            this.context.log("Run to here and adminDTO: " + adminDTO);
        } catch (Exception ex) {
            hocVienDTO = null;
            adminDTO = null;
        }

        if (adminDTO == null && uri.contains("Authorization/Admin")) {
            res.sendRedirect("/YogaCenter/Public/adminLogin.jsp");
            this.context.log("Unauthorized access request");
        } else {
            if (hocVienDTO == null && uri.contains("Authentication") || hocVienDTO == null && uri.contains("Authorization/TraineePrivilege") ||  trainerDTO == null && uri.contains("Authentication")||trainerDTO == null && uri.contains("Authorization/TrainerPrivilege")) {
                this.context.log("Unauthorized access request");
                res.sendRedirect("/YogaCenter/Public/signin.jsp");
            } else {
                // pass the request along the filter chain
                chain.doFilter(request, response);
            }
        }
    }

    public void destroy() {
        //close any resources here
        this.context.log("Destroy anything!!!");

    }

}
