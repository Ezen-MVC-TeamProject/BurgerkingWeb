package com.burger.controller.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.burger.controller.action.Action;
import com.burger.dao.AdminDao;
import com.burger.dto.AdminVO;

public class AdminOrderSaveAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "burger.do?command=adminOrderList";
		
		HttpSession session = request.getSession(); 
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		
		if(avo == null) { 
			url = "burger.do?command=admin";
		}else { 
			// 전달된 주문상세번호들의 주문을 미처리 ->처리완료 변경해주세요.
			String[] result = request.getParameterValues("result");
			AdminDao adao = AdminDao.getInstance(); 
			for(String odseq : result) {
				adao.updateOrderResult(odseq); 
			} 
		}
		request.getRequestDispatcher(url).forward(request, response);		
	}
}
