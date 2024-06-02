package com.example.track16_jsp_human_resource.controller.grade;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/grade")
public class GradeController extends HttpServlet {

    EmployService employService;

    @Override
    public void init() throws ServletException {

        employService = new EmployService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("gradeList", employService.getGradeList());

        req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);

    }
}
