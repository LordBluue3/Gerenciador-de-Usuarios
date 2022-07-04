package com.mikael.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mikael.model.Dao;
import com.mikael.model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet({ "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();
	JavaBeans jao = new JavaBeans();

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recebendo uma requisição WebServlet
		String action = request.getServletPath();
		System.out.println(action);
		//Verificando qual requisição é e qual ação deve ser tomada
		if(action.equalsIgnoreCase("/main")) {
			listarUsuarios(request,response);
		}else if(action.equalsIgnoreCase("/insert")) {
			novoContato(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}
	protected void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Redirecionando para o painel
		response.sendRedirect("telas/painel.html");
	}
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Testando o recebimento dos parametros
		System.out.println(request.getParameter("usuario"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("senha"));
		//Utilizando o objeto jao para setar os parametros na class JavaBeans
		jao.setUsuario(request.getParameter("usuario"));
		jao.setEmail(request.getParameter("email"));
		jao.setSenha(request.getParameter("senha"));
		//Invocando método conexão
		dao.conexao(jao);
		//Redirecionando
		response.sendRedirect("telas/painel.html");
	}

}
