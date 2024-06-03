package com.example.track16_jsp_human_resource.controller.depart;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/depart/delete")
public class DepartDeleteController extends HttpServlet {

    private EmployService employService;


    @Override
    public void init() throws ServletException {
        employService = new EmployService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String errorMessage = "부서의 삭제처리에서 문제가 발생했습니다. 다시 시도해주세요";

        if(req.getParameter("departCode").isEmpty()) {
            errorMessage = "삭제할 부서의 코드가 변경되었거나 삭제되었습니다";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        String departCode = req.getParameter("departCode");

        if(employService.departPersonnel(departCode) > 0) {
            errorMessage = "삭제할 부서의 인원이 변경되었거나 이미 부서가 삭제되었습니다";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        int result = employService.deleteDepart(departCode);

        if(result == 1) {
            resp.sendRedirect(req.getContextPath() + "/depart");
        } else  {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
        }



    }
}
