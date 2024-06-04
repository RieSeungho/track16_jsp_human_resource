package com.example.track16_jsp_human_resource.controller.depart;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/depart/insert")
public class DepartInsertController extends HttpServlet {

    private EmployService employService;

    @Override
    public void init() throws ServletException {
        employService = new EmployService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String errorMessage = "부서기능의 처리에서 오류가 발생했습니다.";

        if(req.getParameter("departCode").isEmpty() || req.getParameter("departName").isEmpty()){
            errorMessage = "등록할 부서의 코드와 명칭을 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        String departCode = req.getParameter("departCode");
        String departName = req.getParameter("departName");

        if(departCode.getBytes().length > 3){
            errorMessage = "등록할 부서의 코드는 영문과 숫자로 3자리 이하로 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        if(departName.getBytes().length > 20) {
            errorMessage = "등록할 부서의 명칭은 한글 6자, 영문으로 20이하로 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("de    partList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        if(!employService.departDuplicationCheck(departCode, departName)) {
            errorMessage = "부서의 코드 또는 명칭이 중복되었습니다. 다시 시도해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        int result = employService.saveDepart(departCode, departName);

        if(result == 1){
            resp.sendRedirect(req.getContextPath() + "/depart");
        } else {
            errorMessage = "부서의 등록처리에서 문제가 발생했습니다. 다시 시도해주세요.";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
        }
    }
}
