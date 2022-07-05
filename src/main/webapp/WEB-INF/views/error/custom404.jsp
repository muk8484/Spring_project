<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 없음</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<h3>페이지 없음</h3>
		<div class="alert alert-danger">
			요청하신 페이지가 존재하지 않습니다.
		</div>
		<ul class="list-grouup">
			<li class="list-group-item">404 오류</li>
			<li class="list-group-item">주소를 확인후 다시 시도해 주세요</li>
			<li class="list-group-item">계속 페이지를 찾지 못하면 담당자에게 연락해 주세요</li>
			<li class="list-group-item">
				<a href="/" class="btn btn-default">메인으로 이동</a>
			</li>
		</ul>
	</div>
</body>
</html>