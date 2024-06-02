package com.example.track16_jsp_human_resource.controller.employ;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet("/employ/update/form")
public class EmployUpdateFormController extends HttpServlet {

    EmployService employService;

    @Override
    public void init() throws ServletException {

        employService = new EmployService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Optional<String> no = Optional.ofNullable(req.getParameter("no"));

        String errorMessage = "회원의 정보를 불러오던 중에 오류가 발생했습니다. 다시 시도해주세요.";

        String employIdentifier = null;

        try {
            if(no.isPresent()) {
                employIdentifier = no.get();
            } else {
                req.setAttribute("employList", employService.getEmployDtos());
                req.setAttribute("errorMessage", errorMessage);

                req.getRequestDispatcher("/WEB-INF/employ/employ_list.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/employ");

            e.printStackTrace();
        }

        Map<String, Object> employMap = employService.getEmployMap(employIdentifier);

        if(employMap.equals(null)) {
            errorMessage = "조회한 회원의 정보가 변경 또는 삭제되었습니다. 다시 시도해주세요.";

            req.setAttribute("employList", employService.getEmployDtos());
            req.setAttribute("errorMessage", errorMessage);

            req.getRequestDispatcher("/WEB-INF/employ/employ_list.jsp").forward(req, resp);
        }

        req.setAttribute("gradeList", employService.getGradeList());
        req.setAttribute("departList", employService.getDepartList());
        req.setAttribute("employMap", employMap);
        req.getRequestDispatcher("/WEB-INF/employ/employ_update.jsp").forward(req, resp);

    }
}
