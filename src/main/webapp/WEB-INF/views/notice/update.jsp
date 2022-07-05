<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글수정</title>
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
	<h3>공지사항 글수정</h3>
	<form action="update.do" method="post">
		<input name="page" value="${pageObject.page }" type="hidden">
		<input name="perPageNum" value="${pageObject.perPageNum }" type="hidden">
		<input name="key" value="${pageObject.key }" type="hidden">
		<input name="word" value="${pageObject.word }" type="hidden">
		<div class="form-group">
			<label for="no">글번호</label> 
			<input type="text" name="no" id="no" class="form-control" readonly="readonly" value="${vo.no }">
		</div>
		<div class="form-group">
			<label for="title">제목</label> 
			<input type="text" name="title" id="title" class="form-control" value="${vo.title }" >
		</div>
		<div class="form-group">
			<label for="content">내용</label> 
			<textarea rows="5" name="content" id="content" class="form-control">${vo.content }</textarea>
		</div>
		<div class="form-group">
			<label for="startDate">공지시작일</label> 
			<input type="text" name="startDate" id="startDate" class="form-control" 
			value="<fmt:formatDate value="${vo.startDate }" pattern="yyyy-MM-dd"/>">
		</div>
		<div class="form-group">
			<label for="endDate">공지종료일</label> 
			<input type="text" name="endDate" id="endDate" class="form-control" 
			value="<fmt:formatDate value="${vo.endDate }" pattern="yyyy-MM-dd"/>">
		</div>
		<button type="submit" class="btn btn-default">수정</button>
		<button type="reset" class="btn btn-default">새로입력</button>
		<button type="button" class="btn btn-default" id="cancelBtn">취소</button>
	</form>
	</div>
</body>
</html>