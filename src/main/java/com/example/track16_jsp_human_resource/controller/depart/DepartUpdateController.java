package com.example.track16_jsp_human_resource.controller.depart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet("/depart/update")
public class DepartUpdateController extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String raw = null;
        String candidate = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));

        while ((raw = reader.readLine()) != null) {
            candidate += raw;
        }

        System.out.println("데이터" + candidate);

        reader.close();
    }
}
