package com.burger.controller.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.burger.controller.action.Action;
import com.burger.dao.ProductDao;

public class AdminProductDeleteAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pseqArr = request.getParameterValues("delete");
		
		ProductDao pdao = ProductDao.getInstance();
		for(String pseq : pseqArr) { 
			pdao.deleteProduct(pseq);
		}
		response.sendRedirect("burger.do?command=adminProductList");
	}
}
