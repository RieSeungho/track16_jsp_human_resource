package com.example.track16_jsp_human_resource.controller.grade;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/grade/insert")
public class GradeInsertController extends HttpServlet {

    private EmployService employService;

    @Override
    public void init() throws ServletException {
        employService = new EmployService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String errorMessage = "직급의 등록처리에서 문제가 발생했습니다. 다시 시도해주세요";

        if(req.getParameter("gradeCode").isEmpty() || req.getParameter("gradeName").isEmpty()) {
            errorMessage = "등록할 직급의 코드와 명칭을 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        String gradeCode = req.getParameter("gradeCode");
        String gradeName = req.getParameter("gradeName");

        if(gradeCode.length() > 3){
            errorMessage = "등록할 직급의 코드는 영문과 숫자로 3자리 이하로 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        if(gradeName.getBytes().length > 20) {
            errorMessage = "등록할 직급의 명칭은 한글 6자, 영문으로 20이하로 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        if(employService.gradeDuplicationCheck(gradeCode, gradeName)) {
            errorMessage = "직급의 코드 또는 명칭이 중복되었습니다. 다시 시도해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
            return;
        }

        int result = employService.saveGrade(gradeCode, gradeName);

        if(result == 1){
            resp.sendRedirect(req.getContextPath() + "/depart");
        } else {

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("gradeList", employService.getGradeList());
            req.getRequestDispatcher("/WEB-INF/grade/grade_manage.jsp").forward(req, resp);
        }
    }
}
