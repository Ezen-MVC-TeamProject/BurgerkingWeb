<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/Delivery/deli_header.jsp"%>
<script src="member.js" type="text/javascript"></script>



<article class="joinpageatc">
	<div class="location">
		<ul>
			<li><a href="burger.do?command=index">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="burger.do?command=loginForm&non=1">로그인</a>&nbsp;>&nbsp;</li>
			<li><a href="burger.do?command=joinForm">회원가입</a>&nbsp;>&nbsp;</li>
			<li><a href="burger.do?command=contract">약관동의 및 본인인증</a>&nbsp;>&nbsp;</li>

		</ul>
	</div>

	<script type="text/javascript">
		
	
	function idcheck(){
		if( document.joinpageForm.id.value=="" ){
			alert("아이디를 입력하세요" );
			documnet.joinpageForm.id.focus();
			return;
		}
		var url = "burger.do?command=idCheckForm&id=" + document.joinpageForm.id.value;
		var opt = "toolbar=no, menubar=no, resizable=no, width=400, height=350";
		window.open(url, "IdCheck", opt);
	}


		


	function go_save(){
		if (document.joinpageForm.id.value == "") {
			alert("아이디를 입력하여 주세요."); 	    
		    document.joinpageForm.id.focus();
		} else if(document.joinpageForm.name.value == "") {
		    alert("이름을 입력해 주세요.");	    
		    document.joinpageForm.name.focus();
		} else if(document.joinpageForm.phone.value == "") {
		    alert("휴대폰번호을 입력해 주세요.");	   
		    document.joinpageForm.phone.focus();
		} else if(document.joinpageForm.pwd.value == "") {
		    alert("비밀번호를 입력해 주세요.");	    
		    document.joinpageForm.pwd.focus();
		} else if(document.joinpageForm.pwd.value != document.joinpageForm.pwdCheck.value) {
		    alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");	    
		    document.joinpageForm.pwd.focus();
		} else{
			document.joinpageForm.action ="burger.do";
			document.joinpageForm.submit(); 
		}
	}
		
	
	
	</script>



	<form method="post" name="joinpageForm" >
<input type="hidden" name="command"  value="joinpageForm">

		<div class="contentsBox01">
			<div class="web_container">
				<div class="subtitWrap m_bg_basic">
					<h2 class="page_tit">회원가입</h2>
				</div>
				<div class="container01">
					<h3 class="tit01 tit_ico man">
						<span>기본정보</span>
					</h3>
					<div class="container02">
						<div class="titbox">
							<p>회원정보를 입력해 주세요</p>
						</div>
						<div class="dlist01">
							<dl>
								<dt class="WEB vtop77">이메일 아이디</dt>
								<dd>
									<div class="inpbox">
										<label><input type="email" placeholder="이메일 아이디"
											class="st02" name="id"><input type="hidden" name="reid" id="reid">
											<button type="button" class="btn_del01"
												style="display: none;">
											
												<span>입력 텍스트 삭제</span>
											</button></label>
										<p class="txt77"><input type="button" width="40" value="중복확인" id="reid" class="dup" onclick="idcheck()">  사용 가능한 이메일 주소를 입력해 주세요. (예:
											name@mail.com)</p>
									</div>
								</dd>
							</dl>
							<dl>
								<dt class="WEB vtop77">이름</dt>
								<dd>
									<div class="inpbox">
										<label><input type="text" placeholder="이름"
											class="st02" id="name" name="name">
											<button type="button" class="btn_del01"
												style="display: none;">
											</button></label>
										<p class="txt77">이름을 입력해 주세요.</p>
									</div>
								</dd>
							</dl>
							<dl>
								<dt class="WEB vtop77">휴대폰 번호</dt>
								<dd>
									<div class="inpbox">
										<label><input type="text" placeholder="휴대폰 번호"
											class="st02" id="phone" name="phone">
											<button type="button" class="btn_del01"
												style="display: none;">
											</button></label>
										<p class="txt77">사용중인 휴대폰번호를 입력해 주세요.</p>
									</div>
								</dd>
							</dl>
						</div>
					</div>
					<h2 class="tit01 tit_ico key">
						<span>비밀번호 입력</span>
					</h2>
					<div class="container02">
						<div class="dlist01">
							<dl>
								<dt class="WEB vtop77">비밀번호</dt>
								<dd>
									<div class="inpbox">
										<label><span class="hide">비밀번호</span><input
											placeholder="비밀번호, 영문, 숫자, 특수문자 조합(10~20자 사이)"
											type="password" class="st02" name="pwd"> </label>
										<p class="txt77">
											<span>사용할 비밀번호를 입력해 주세요 </span>
										</p>
									</div>
								</dd>
							</dl>
							<dl>
								<dt class="WEB vtop77">
									<span class="hide">비밀번호 확인</span>
								</dt>
								<dd>
									<div class="inpbox">
										<label><span class="hide">비밀번호 확인</span><input
											placeholder="비밀번호 확인" type="password" class="st02" name="pwdCheck">
											</label>
										<p class="txt77">
											<span>비밀번호를 다시한번 입력해 주세요.</span>
										</p>
									</div>
								</dd>
							</dl>
						</div>
					</div>
					<div class="c_btn">
						<button type="button" id="btnJoin" value="submit"
							class="btn77 btn01_m" onclick="go_save()">
							<span>회원가입</span>
						</button>
					</div>
				</div>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
				
			</div>
						<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			
		</div>
					<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

	</form>
</article>

<%@ include file="/footer/footer.jsp"%>