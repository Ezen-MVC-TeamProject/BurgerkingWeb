package com.burger.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.burger.dao.QnaDao;
import com.burger.dto.MemberVO;
import com.burger.dto.QnaVO;

public class QnaViewAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "ServiceCenter/qnaView.jsp";
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if(mvo == null) {
			url = "burger.do?command=index";
		}else {
			int qseq = Integer.parseInt(request.getParameter("qseq"));
			QnaDao qdao = QnaDao.getInstance();
			QnaVO qvo = qdao.getQna2(qseq);
			request.setAttribute("qvoVO", qvo);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
