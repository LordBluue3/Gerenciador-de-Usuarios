package com.mikael.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mikael.model.Dao;

/**
 * Servlet implementation class Controller
 */
@WebServlet({ "/Controller", "/main" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equalsIgnoreCase("/main")) {
			listarUsuarios(request,response);
		}
	}
	protected void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("telas/painel.html");
	}

}
