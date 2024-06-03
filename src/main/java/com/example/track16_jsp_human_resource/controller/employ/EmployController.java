package com.example.track16_jsp_human_resource.controller.employ;

import com.example.track16_jsp_human_resource.dto.EmployDto;
import com.example.track16_jsp_human_resource.service.EmployService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/employ")
public class EmployController extends HttpServlet {

    private EmployService employService;

    /**
     * 해당 url요청이 들어올 경우에 EmployService 객체를 생성
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {

        employService = new EmployService();

    }

    /**
     * GET 방식으로 사원의 정보를 제공하는 메소드
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<EmployDto> employDtos = employService.getEmployDtos();

        req.setAttribute("employList", employDtos);

        req.getRequestDispatcher("/WEB-INF/employ/employ_list.jsp").forward(req, resp);
    }

    /**
     * POST 방식으로 검색하기 위한 메소드
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ArrayList<EmployDto> employDtos = null;

        String searchKey = null;
        String searchValue = null;

        if(req.getParameter("searchKey").isEmpty() || req.getParameter("searchValue").isEmpty()) {
            employDtos = employService.getEmployDtos();
        } else {
            searchKey = req.getParameter("searchKey");
            searchValue = req.getParameter("searchValue");
            req.setAttribute("searchKey", searchKey);
            req.setAttribute("searchValue", searchValue);
            employDtos = employService.getEmployDtos(searchKey, searchValue);
        }

        String errorMessage = "조회한 결과가 존재하지 않습니다.";

        if(employDtos.size() == 0) {
            req.setAttribute("errorMessage", errorMessage);
        }

        req.setAttribute("employList", employDtos);

        req.getRequestDispatcher("/WEB-INF/employ/employ_list.jsp").forward(req, resp);
    }
}
