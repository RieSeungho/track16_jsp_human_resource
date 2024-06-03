package com.example.track16_jsp_human_resource.controller.grade;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/grade/delete")
public class GradeDeleteController extends HttpServlet {

    private EmployService employService;


    @Override
    public void init() throws ServletException {
        employService = new EmployService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String errorMessage = "직급의 삭제처리에서 문제가 발생했습니다. 다시 시도해주세요";

        if(req.getParameter("gradeCode").isEmpty()) {
            errorMessage = "삭제할 직급의 코드가 변경되었거나 삭제되었습니다";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        String gradeCode = req.getParameter("gradeCode");

        if(employService.departPersonnel(gradeCode) > 0) {
            errorMessage = "삭제할 직급의 인원이 변경되었거나 이미 직급가 삭제되었습니다";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        int result = employService.deleteGrade(gradeCode);

        if(result == 1) {
            resp.sendRedirect(req.getContextPath() + "/grade");
        } else  {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
        }
    }
}
