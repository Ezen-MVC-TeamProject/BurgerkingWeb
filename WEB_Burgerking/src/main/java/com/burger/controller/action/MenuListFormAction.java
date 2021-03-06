package com.burger.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.burger.dao.ProductDao;
import com.burger.dto.ProductVO;

public class MenuListFormAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "Product/menuList.jsp";
		String kind1 = request.getParameter("kind1");
		
		ProductDao pdao = ProductDao.getInstance();

		ArrayList<ProductVO> list = pdao.getProductList(kind1);

		request.setAttribute("productList", list);
		request.setAttribute("kind1", kind1);

		request.getRequestDispatcher(url).forward(request, response);
	}
}
