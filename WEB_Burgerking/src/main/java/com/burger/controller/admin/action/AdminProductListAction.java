package com.burger.controller.admin.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.burger.controller.action.Action;
import com.burger.dao.AdminDao;
import com.burger.dto.AdminVO;
import com.burger.dto.ProductVO;
import com.burger.util.Paging;

public class AdminProductListAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/product/productList.jsp";
		HttpSession session = request.getSession();
		AdminVO id = (AdminVO) session.getAttribute("loginAdmin");
		 
		int page = 1;
		
		if(id == null) { url = "burger.do?command=admin"; }
		else {
			String key = ""; 
			if(request.getParameter("key") != null) { 
				key = request.getParameter("key"); session.setAttribute("key", key); 
			}else if(session.getAttribute("key") != null) {
				key = (String)session.getAttribute("key");
			}else { 
				session.removeAttribute("key");
				key = "";
			}
		
		// 현재 표시할 페이지에 대한 준비
		if(request.getParameter("page") != null){ 
			page = Integer.parseInt(request.getParameter("page")); 
			session.setAttribute("page", page); 
		}else if(session.getAttribute("page") != null) { 
			page = (int)session.getAttribute("page"); 
		}else { 
			page = 1;
			session.removeAttribute("page"); 
		}
		
		Paging paging = new Paging(); paging.setPage(page);
		
		AdminDao adao = AdminDao.getInstance(); 
		int count = adao.getAllCount("product", "pname", key); 
		count = count - adao.getshortPCount("product", "pname", key);
		paging.setTotalCount(count);
		ArrayList<ProductVO> productList = adao.listProduct(paging, key);
		
		request.setAttribute("productList", productList);
		request.setAttribute("paging", paging);
		request.setAttribute("key", key); }
		request.getRequestDispatcher(url).forward(request,response);
	}
}
