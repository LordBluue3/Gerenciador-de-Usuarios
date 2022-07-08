package com.mikael.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mikael.model.Dao;
import com.mikael.model.JavaBeans;


@WebServlet({ "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();
	JavaBeans javaBeans = new JavaBeans();

    public Controller() {
        super();
    }

	/*
	* Método que recebe uma requisição do Servlet de um @WebServlet,
	* ele verifica qual requição @WebServlet foi chamado e executa um método dessa requisição
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestServlet = request.getServletPath();
		System.out.println(requestServlet);
		if(requestServlet.equalsIgnoreCase("/main")) {
			listUser(request,response);
		}else if(requestServlet.equalsIgnoreCase("/insert")) {
			newUser(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}
	/*
	* Método que redireciona o usuário para o painel
	*/
	protected void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("telas/painel.html");
	}
	/*
	 *Método que pega os parametros dos inputs do localizado na View telas/adicionar.html identificados pelo name=""
	 * seta esses parametros na class JavaBeans, invoca o método da class Dao conexão
	 * e redireciona para o painel.
	 */
	protected void newUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		javaBeans.setUser(request.getParameter("user"));
		javaBeans.setEmail(request.getParameter("email"));
		javaBeans.setPassword(request.getParameter("password"));
		dao.connection(javaBeans);
		response.sendRedirect("telas/painel.html");
	}
}
