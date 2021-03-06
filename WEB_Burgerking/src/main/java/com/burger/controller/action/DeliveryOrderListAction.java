package com.burger.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.burger.dao.CartDao;
import com.burger.dao.MemberDao;
import com.burger.dao.MyAddressDao;
import com.burger.dao.OrderDao;
import com.burger.dao.subproductOrderDao;
import com.burger.dto.CartVO;
import com.burger.dto.MemberVO;
import com.burger.dto.MyAddressVO;
import com.burger.dto.orderVO;
import com.burger.dto.subproductOrderVO;

public class DeliveryOrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Delivery/orderList.jsp";
		HttpSession session = request.getSession();
		
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		if (mvo == null) {
		    url = "burger.do?command=loginForm&non=1";
		}else{
			MemberVO mvo1 = mdao.getMember(mvo.getId());
			
			CartDao cdao = CartDao.getInstance();
			OrderDao odao = OrderDao.getInstance();
			ArrayList<orderVO> list1 = odao.getOrderList(mvo.getId());

			int totalPrice=0;
			for(orderVO ovo : list1)  // 조회된 주문의 총 결제금액 계산
				totalPrice+=ovo.getPrice1() * ovo.getQuantity();	
			if(list1.size()!=0) {
				orderVO ovo1 = list1.get(0);
				request.setAttribute("orderVO", ovo1);
			}
			
			ArrayList<orderVO> list2 = odao.getOrderList(mvo.getId());
			ArrayList<CartVO> list3 = cdao.selectCart( mvo.getId() );
			
			subproductOrderDao spodao = subproductOrderDao.getInstance();
			ArrayList<subproductOrderVO> spovo = spodao.select2SubProductOrder(mvo.getMseq());
			request.setAttribute("spseqAm", spovo);
			for(int i = 0; i < spovo.size(); i++) {
				totalPrice += spovo.get(i).getAddprice();
			}
			
			MyAddressDao madao = MyAddressDao.getInstance();
			MyAddressVO mavo = madao.getAddress(mvo.getMseq());
			
			request.setAttribute("Myaddress", mavo);
			request.setAttribute("memberVO", mvo1);
			request.setAttribute("orderList", list1);
			request.setAttribute("ovo", list2);
			request.setAttribute("cvo", list3);
			request.setAttribute("totalPrice", totalPrice);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
