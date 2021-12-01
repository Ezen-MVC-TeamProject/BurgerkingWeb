package com.burger.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.burger.dao.CartDao;
import com.burger.dao.ProductDao;
import com.burger.dto.CartVO;
import com.burger.dto.MemberVO;
import com.burger.dto.ProductVO;

public class DeliveryCartFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Delivery/cart.jsp"; // 최종 목적지

		
		HttpSession session = request.getSession(); 
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if (mvo == null) {
		    url = "burger.do?command=loginForm";
		}else {
			// 로그인 유저의 아이디로 카트물건들을 검색해서 리턴받습니다 
			CartDao cdao = CartDao.getInstance();
			ArrayList<CartVO> list = cdao.selectCart( mvo.getId() );		
			
			request.setAttribute("cartList", list); // 리퀘스트에 리턴받은 리스트 저장 
			// 리턴받은 카트 리스트에 있는 상품들의 총구매금액을 계산합니다 
			int totalPrice = 0; 
			for(CartVO cvo : list) totalPrice += cvo.getPrice1() * cvo.getQuantity(); // 금액*수량이 누적 -> 총금액을 추가로 계산
			request.setAttribute("totalPrice", totalPrice); // 리퀘스트에 저장
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}