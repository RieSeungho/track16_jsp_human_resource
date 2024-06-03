package com.example.track16_jsp_human_resource.controller.grade;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/grade/update")
public class GradeNameUpdateController extends HttpServlet {

    private EmployService employService;

    @Override
    public void init() throws ServletException {
        employService = new EmployService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String errorMessage = "직급의 수정처리에서 문제가 발생했습니다. 다시 시도해주세요";

        if(req.getParameter("existCode").isEmpty() || req.getParameter("updateName").isEmpty()) {

            errorMessage = "수정할 직급의 코드와 명칭을 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        String existCode = req.getParameter("existCode");
        String updateName = req.getParameter("updateName");

        if(updateName.getBytes().length > 20) {
            errorMessage = "수정할 직급의 명칭은 한글 6자, 영문으로 20이하로 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        if(employService.gradeNameDuplication(updateName) != 0) {
            errorMessage = "기존 직급의 명칭과 중복됩니다. 다시 시도해주세요.";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        int result = 0;

        result = employService.updateGradeName(existCode, updateName);

        if(result == 1) {
            resp.sendRedirect(req.getContextPath() + "/grade");

        } else {
            errorMessage = "직급명칭의 수정처리에서 문제가 발생했습니다. 다시 시도해주세요.";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
        }

    }
}
