package com.example.track16_jsp_human_resource.controller.employ;

import com.example.track16_jsp_human_resource.dao.EmployDao;
import com.example.track16_jsp_human_resource.dto.DepartDto;
import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/employ/form")
public class EmployFormController extends HttpServlet {

    EmployService employService;

    @Override
    public void init() throws ServletException {

        employService = new EmployService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("gradeList", employService.getGradeList());
        req.setAttribute("departList", employService.getDepartList());

        req.getRequestDispatcher("/WEB-INF/employ/employ_form.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String KOR_REGEXP = "^[가-힣]*$";

        req.setCharacterEncoding("UTF-8");

        String errorMessage = "등록을 처리하던 중 문제가 발생했습니다. 다시 시도해주세요.";

        if(req.getParameter("name").isEmpty() || req.getParameter("age").isEmpty()) {

            errorMessage = "사원등록에 필요한 정보를 공백없이 입력해주세요.";

            req.setAttribute("gradeList", employService.getGradeList());
            req.setAttribute("departList", employService.getDepartList());

            req.setAttribute("errorMessage", errorMessage);

            req.getRequestDispatcher("/WEB-INF/employ/employ_form.jsp").forward(req, resp);
        }

        Optional<String> name = Optional.ofNullable(req.getParameter("name"));
        Optional<String> grade = Optional.ofNullable(req.getParameter("grade"));
        Optional<String> depart = Optional.ofNullable(req.getParameter("depart"));
        Optional<String> age = Optional.ofNullable(req.getParameter("age"));

        int integerAge = 0;

        int saveResult = 0;

        if(name.isPresent() && grade.isPresent() && depart.isPresent() && age.isPresent()) {

            req.setAttribute("gradeList", employService.getGradeList());
            req.setAttribute("departList", employService.getDepartList());

            try {

                integerAge = Integer.parseInt(age.get());

                if(integerAge > 70 || integerAge < 19) {
                    errorMessage = "사원은 19세부터 70세까지 등록할 수 있습니다.";

                    req.setAttribute("name", name.get());
                    req.setAttribute("enteredGrade", grade.get());
                    req.setAttribute("enteredDepart", depart.get());

                    req.setAttribute("errorMessage", errorMessage);

                    req.getRequestDispatcher("/WEB-INF/employ/employ_form.jsp").forward(req, resp);
                }

            } catch (NumberFormatException e) {

                errorMessage = "나이는 3자 이내, 숫자로 입력해주세요.";

                req.setAttribute("name", name.get());
                req.setAttribute("enteredGrade", grade.get());
                req.setAttribute("enteredDepart", depart.get());

                req.setAttribute("errorMessage", errorMessage);

                req.getRequestDispatcher("/WEB-INF/employ/employ_form.jsp").forward(req, resp);
            }

            if(name.get().matches(KOR_REGEXP) && name.get().getBytes().length < 20) {

                saveResult = employService.saveEmployDto(name.get(), grade.get(), depart.get(), integerAge);

            } else {
                errorMessage = "성명은 6자 이내, 한글로 입력해주세요.";

                req.setAttribute("enteredGrade", grade.get());
                req.setAttribute("enteredDepart", depart.get());
                req.setAttribute("age", integerAge);

                req.setAttribute("errorMessage", errorMessage);
            }

        } else {
            errorMessage = "사원등록에 필요한 정보를 공백없이 입력해주세요.";

            req.setAttribute("gradeList", employService.getGradeList());
            req.setAttribute("departList", employService.getDepartList());

            req.setAttribute("errorMessage", errorMessage);

            req.getRequestDispatcher("/WEB-INF/employ/employ_form.jsp").forward(req, resp);
        }

        if(saveResult == 1) {
            resp.sendRedirect(req.getContextPath() + "/employ");
        } else {
            req.setAttribute("errorMessage", errorMessage);

            req.setAttribute("gradeList", employService.getGradeList());
            req.setAttribute("departList", employService.getDepartList());

            req.getRequestDispatcher("/WEB-INF/employ/employ_form.jsp").forward(req, resp);
        }
    }
    
}
