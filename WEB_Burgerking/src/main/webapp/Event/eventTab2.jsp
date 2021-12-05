<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<link href="css/event.css" rel="stylesheet">
<head profile="http://www.w3.org/2005/10/profile" />

<article>
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="burger.do?command=index">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="burger.do?command=eventListForm">이벤트</a>&nbsp;>&nbsp;</li>
			<li>진행중</li>
		</ul>
	</div>
	</div>
	<div class="event_web_container">
		<div class="subtit">
			<h1 class="event_tit">이벤트</h1>
			<ul class="submenu_right">
				<li><span><a href="burger.do?command=eventListForm">전체</a></span></li>
				<li><span style="border-bottom: 3px solid red;"><a
						href="burger.do?command=eventTab2" style="color: red;">진행중</a></span></li>
				<li><span><a href="burger.do?command=eventTab3">종료</a></span></li>
			</ul>
		</div>
		<form name="frm" method="post" action="burder.do">

			<div class="eventarea">
				<ul>
					<c:forEach var="EventVO" items="${eventlist}">
						<li><a href="burger.do?command=eventDetailForm&eseq=${EventVO.eseq}"> <input
								type="hidden" name="eseq" value="${EventVO.eseq}" /> <img
								class="eventImg" src="image/main/event/${EventVO.image}" />
						</a>
							<p>
								<fmt:formatDate value="${EventVO.startdate}" type="date" />
								~
								<fmt:formatDate value="${EventVO.enddate}" type="date" />
							</p></li>
					</c:forEach>
				</ul>
			</div>
		</form>
	</div>
</article>

<%@ include file="/undermenu.jsp"%>
<%@ include file="/footer/footer.jsp"%>
