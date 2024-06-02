package com.example.track16_jsp_human_resource.controller.employ;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employ/delete")
public class EmployDeleteController extends HttpServlet {

    private EmployService employService;

    @Override
    public void init() throws ServletException {

        employService = new EmployService();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String errorMessage = "요청 이전에 변경 또는 삭제가 발생하여 삭제에 실패했습니다";

        if(req.getParameter("no").isEmpty()) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("employList", employService.getEmployDtos());
            req.setAttribute("/WEB-INF/employ/employ_list.jsp", errorMessage);
        }

        String no = req.getParameter("no");

        int deleteResult = employService.deleteEmploy(no);

        if(deleteResult == 1) {
            resp.sendRedirect(req.getContextPath() + "/employ");
        } else {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("employList", employService.getEmployDtos());
            req.setAttribute("/WEB-INF/employ/employ_list.jsp", errorMessage);
        }
    }
}
