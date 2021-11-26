<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp" %>
<%@ include file="/admin/sub_menu.jsp"%>

<article>
	<h1>상품 상세 보기</h1>
	<table id="list" border="1">
		<tr>
			<th>상품분류</th>
			<td colspan="3">${kind1}</td>
			<th>분류번호</th><td colspan="7">${productVO.kind2}</td>
		</tr>
		<tr>
			<th align="center">상품명</th>
			<td colspan="3">${productVO.pname}</td>
			<th align="center">사용유무</th>
			<td colspan="3">${useyn}</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td colspan="7" align="center">
				<img src="image/menu/${productVO.kind1}/${productVO.image}" width="200px">
			</td>
		</tr>
	</table>
	<input type="button" class="btn" value="수정" onclick="go_mod('${productVO.spseq}')">
	<input type="button" class="btn" value="목록" onclick="go_mov()">
</article>

<%@ include file="/admin/footer.jsp"%>