<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록 폼</title>
  <script type="text/javascript">
  	$(function(){
  		$("#cancelBtn").click(function () {
			history.back();
		});
  	});
  </script>
</head>
<body>
	<div class="container">
	<h3>공지사항 등록 폼</h3>
	<form action="write.do" method="post">
		<input name="perPageNum" value="${param.perPageNum }" type="hidden">
		<div class="form-group">
			<label for="title">제목</label> 
			<input name="title" id="title" class="form-control" >
		</div>
		<div class="form-group">
			<label for="content">내용</label> 
			<textarea rows="5" name="content" id="content" class="form-control"></textarea>
		</div>
		<div class="form-group">
			<label for="startDate">공지시작일</label> 
			<input  name="startDate" id="startDate" class="form-control">
		</div>
		<div class="form-group">
			<label for="endDate">공지종료일</label> 
			<input name="endDate" id="endDate" class="form-control">
		</div>
		<button type="submit" class="btn btn-default">등록</button>
		<button type="reset" class="btn btn-default">새로입력</button>
		<button type="button" class="btn btn-default" id="cancelBtn">취소</button>
	</form>
	</div>
</body>
</html>