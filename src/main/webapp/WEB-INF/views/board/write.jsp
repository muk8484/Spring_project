<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
	
	<script src="/ckeditor/ckeditor.js"></script>
	<script>
	    window.onload = function(){
	       ck = CKEDITOR.replace("editor");
	    };
    </script>
	
</head>
<body>
	<div class="container">
		<h3>게시판 리스트</h3>
		<form action="write.do" method="post">
		<input type="hidden" name="perPageNum" value="${param.perPageNum }">
			<div class="form-group">
				<label for="title">제목</label> 
				<input type="text" name="title" id="title" class="form-control" >
			</div>
			<div class="form-group">
				<label for="content">내용</label> 
				<textarea rows="5" name="content" id="editor" class="form-control"></textarea>
			</div>
			<div class="form-group">
				<label for="writer">작성자</label> 
				<input type="text" name="writer" id="writer" class="form-control" >
			</div>
			<button type="submit" class="btn btn-default">등록</button>
			<button type="reset" class="btn btn-default">새로입력</button>
			<button type="button" class="btn btn-default" class="canceBtn">취소</button>
		</form>
	</div>
</body>
</html>