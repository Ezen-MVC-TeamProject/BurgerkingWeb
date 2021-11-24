<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Delivery/deli_header.jsp"%>

<article style="background-color: #f2ebe6;">
	<div class="location">
		<ul>
			<li><a href="burger.do?command=index">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="burger.do?command=loginForm">로그인</a>&nbsp;>&nbsp;</li>
			<li><a href="burger.do?command=findIdForm">아이디/비밀번호 찾기</a></li>
		</ul>
	</div>

	<div class="web_container02">
		<form name="frm" action="burger.do" method="post">
			<input type="hidden" name="command" value="findId">
			<h1 class="big_h1" style="margin-bottom: 30px;">아이디/비밀번호 찾기</h1>
			<ul id="terms_wrap_ul" style="left: 0px; position:relative;">
				<li>
					<span>
						<a href="burger.do?command=findIdForm" style="color: red; text-decoration: underline; font-size: 1.45em;">아이디 찾기</a>
					</span>
				</li>
				<li><span><a href="burger.do?command=findPwdForm" style="font-size: 1.45em;">비밀번호 찾기</a></span></li>
			</ul>
			<div class="btnarea" style="text-align: center; margin-top: 80px;">
				<span style="font-family: 'Noto Sans KR', sans-serif;">가입시 회원정보로 등록한 이름과 휴대폰 번호를 입력해 주세요.</span>
			</div>
			<div class="textarea" style="height: 520px;">
				<div class="inpbox" style="margin-bottom: 30px;">
					<div class="vtop">이름</div>
					<label>
						<span class="hide">ID</span>
						<input type="text" placeholder="이름" id="name" name="name" onblur="check_input1();">
						<span id="name_coment" class="coment_text">이름을 입력해 주세요.</span>
					</label>
				</div>
				<div class="inpbox">
				<div class="vtop">휴대폰 번호</div>
					<label>
						<span class="hide">Phone</span>
						<input placeholder="휴대폰 번호" type="text" name="phone" id="phone" onblur="check_input2();">
						<span id="phone_coment" class="coment_text">휴대폰 번호를 입력해 주세요.</span>
					</label>
				</div>
				<ul class="find_id_text">
					<li>ㆍ가입 시 입력한 이름과 휴대폰번호로 아이디 정보를 찾을 수 있습니다.</li>
					<li>ㆍ네이버, 카카오톡, 삼성 앱카드, 애플아이디로 가입하신 회원님께서는 해당 서비스에서 아이디/비밀번호 찾기를
					진행해 주세요.</li>
				</ul>
			</div>
			<div class="c_btn item2 findidalign">
				<input type="submit" class="btn01 m" value="아이디 찾기" onclick="return inputidChk()"/>
			</div>
		</form>
	</div>
</article>

<%@ include file="/footer/footer.jsp" %>