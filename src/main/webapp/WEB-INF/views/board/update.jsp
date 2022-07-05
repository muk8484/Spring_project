<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>
  
  <script src="/ckeditor/ckeditor.js"></script>
 	<script>
	    window.onload = function(){
	       ck = CKEDITOR.replace("editor");
	    };
    </script> 
  
</head>
<body>
	<div class="container">
	<h3>게시판 글수정</h3>
	<form action="update.do" method="post">
		<input name="page" value="${pageObject.page }" type="hidden">
		<input name="perPageNum" value="${pageObject.perPageNum }" type="hidden">
		<div class="form-group">
			<label for="bo">글번호</label> 
			<input type="text" name="no" id="no" class="form-control" readonly="readonly" value="${vo.no }">
		</div>
		<div class="form-group">
			<label for="title">제목</label> 
			<input type="text" name="title" id="title" class="form-control" value="${vo.title }" >
		</div>
		<div class="form-group">
			<label for="content">내용</label> 
			<textarea rows="5" name="content" id="editor" class="form-control">${vo.content }</textarea>
		</div>
		<div class="form-group">
			<label for="writer">작성자</label> 
			<input type="text" name="writer" id="writer" class="form-control" value="${vo.writer }">
		</div>
		<button type="submit" class="btn btn-default">수정</button>
		<button type="reset" class="btn btn-default">새로입력</button>
		<button type="button" class="btn btn-default" class="canceBtn">등록</button>
	</form>
	</div>
</body>
</html>