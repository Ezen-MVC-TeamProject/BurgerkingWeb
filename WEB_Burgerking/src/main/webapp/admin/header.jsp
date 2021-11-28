<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Main</title>
		<link rel="stylesheet" href="admin/css/admin.css">
		<script type="text/javascript" src="admin/product/product.js"></script>
		<script type="text/javascript" src="admin/member/member.js"></script>
		<script type="text/javascript" src="admin/event/event.js"></script>
		<script type="text/javascript" src="admin/qna/qna.js"></script>
		<script type="text/javascript" src="admin/order/order.js"></script>
	</head>
	<body>
		<div id="wrap">
			<header>
				<div class="adminLogin_header">
					<span>BurgerKing Manager Page</span><br>
					<input type="button" class="btn" value="Logout" style="float: right;"
					onclick="location.href='burger.do?command=adminLogout'">
				</div>
			</header>
			<div class="clear"></div>
