<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/Delivery/deli_header.jsp"%>

<article>
	<div class="location">
		<ul>
			<li><a href="burger.do?command=index">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="burger.do?command=loginForm&non=1">로그인</a>&nbsp;>&nbsp;</li>
			<li><a href="burger.do?command=join">회원가입</a></li>
		</ul>
	</div>





	<div class="contentsBox01 join_welcome">
		<div class="web_container1">
			<h2 class="logo"></h2>
			<div class="cont">
				<p class="tit">
					<strong>버거킹 가입이 완료되었습니다</strong>
				</p>
				<p>
					회원가입을 환영합니다.<br>버거킹의 다양한 혜택과 서비스를 이용하실 수 있어요.
				</p>
			</div>
			<div class="c_btn m_item2">
				<a href="burger.do?command=index" class="btn01"><span>홈으로</span></a><a
					href="burger.do?command=index" class="btn01"><span>주문하기</span></a>
			</div>
		</div>
	</div>

</article>

<%@ include file="/footer/footer.jsp"%>