package com.example.track16_jsp_human_resource.controller.depart;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/depart")
public class DepartController extends HttpServlet {

    EmployService employService;

    @Override
    public void init() throws ServletException {

        employService = new EmployService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("departList", employService.getDepartList());

        req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
    }
}
