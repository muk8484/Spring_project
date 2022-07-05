<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
	<div class="container" >
		<div class="col-md-offset-3 col-md-6">
			<h3>로그인 폼</h3>
			<form action="login.do" method="post">
				<div class="form-group">
					<label for="id">아이디</label> 
					<input name="id" id="id" type="text" class="form-control" >
				</div>
				<div class="form-group">
					<label for="pw">비밀번호</label> 
					<input name="pw" id="pw" type="password" class="form-control" >
				</div>
				<div class="form-group">
					<button class="btn btn-primary btn-md btn-block">로그인</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>