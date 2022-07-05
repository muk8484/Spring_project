<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 이미지 폼</title>
</head>
<body>
	<div class="container">
		<h3>게시판 이미지 폼</h3>
		<form action="write.do" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="title">제목</label> 
				<input name="title" type="text" class="form-control" id="title">
			</div>
			<div class="form-group">
				<label for="imageFile">이미지 파일</label> 
				<input name="imageFile" id="imageFile" type="file" class="form-control" >
			</div>
			<div class="form-group">
			  <label for="content">내용</label>
			  <textarea name="content" class="form-control" rows="5" id="content"></textarea>
			</div>
			<button type="submit" class="btn btn-default">등록</button>
		</form>
	</div>
</body>
</html>