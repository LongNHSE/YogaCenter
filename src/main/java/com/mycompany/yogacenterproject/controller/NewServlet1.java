/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mycompany.yogacenterproject.dto.Student;



public class NewServlet1 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public NewServlet1() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        List<Student> listOfStudent = getStudentData();

        Gson gson = new Gson();

        String jsonString = gson.toJson(listOfStudent);

        response.setContentType("application/json");

        response.getWriter().write(jsonString);

    }

    private List<Student> getStudentData() {

        List<Student> listOfStudent = new ArrayList<Student>();
        Student s1 = new Student();
        s1.setName("Sandeep");
        s1.setComputerMark(75);
        s1.setMathematicsMark(26);
        s1.setGeographyMark(91);
        s1.setHistoryMark(55);
        s1.setLitratureMark(36);
        listOfStudent.add(s1);

        return listOfStudent;
    }
}
