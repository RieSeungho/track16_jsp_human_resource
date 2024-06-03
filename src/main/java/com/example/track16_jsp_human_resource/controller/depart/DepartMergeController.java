package com.example.track16_jsp_human_resource.controller.depart;

import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/depart/merge")
public class DepartMergeController extends HttpServlet {

    private EmployService employService;

    @Override
    public void init() throws ServletException {
        employService = new EmployService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String errorMessage = "부서기능의 처리에서 오류가 발생했습니다.";

        if(req.getParameter("mergeFrom").isEmpty() || req.getParameter("mergeTo").isEmpty()) {
            errorMessage = "병합할 대상부서와 병합할 목표의 부서를 선택해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        String mergeFrom = req.getParameter("mergeFrom");
        String mergeTo = req.getParameter("mergeTo");

        if(mergeFrom.equals(mergeTo)) {
            errorMessage = "병합 대상부서와 목표부서를 서로 다르게 입력해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        if(employService.departPersonnel(mergeFrom) != 1) {
            errorMessage = "인원이 없는 대상부서와 병합할 수 없습니다.";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
            return;
        }

        int result = employService.mergeDepart(mergeFrom, mergeTo);

        if(result > 0) {
            resp.sendRedirect(req.getContextPath() + "/depart");
        } else {
            errorMessage = "부서 병합처리의 과정에서 문제가 발생했습니다. 다시 시도해주세요";

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("departList", employService.getDepartList());
            req.getRequestDispatcher("/WEB-INF/depart/depart_manage.jsp").forward(req, resp);
        }
    }
}
