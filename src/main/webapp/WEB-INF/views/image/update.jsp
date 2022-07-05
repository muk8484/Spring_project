<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 이미지 정보수정 폼</title>
</head>
<body>
	<div class="container">
		<h3>게시판 이미지 정보수정 폼</h3>
		<div>
			사진변경은 이미지 보기의 바꾸기 버튼을 이용하세요.
		</div>
		<form action="update.do" method="post" >
		<!-- 숨겨서 넘겨 줘야할 데이터 - 페이지, 한 페이지당 갯수 -->
		<input type="hidden" name="page" value="${pageObject.page }">
		<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
			<div class="form-group">
				<label for="no">번호</label> 
				<input name="no" type="text" class="form-control" id="no" value="${vo.no }" 
					onclick="alert('번호는 수정할 수 없습니다.')" readonly>
			</div>
			<div class="form-group">
				<label for="title">제목</label> 
				<input name="title" type="text" class="form-control" id="title" value="${vo.title }">
			</div>
			<div class="form-group">
			  <label for="content">내용</label>
			  <textarea name="content" class="form-control" rows="5" id="content">${vo.content }</textarea>
			</div>
			<button type="submit" class="btn btn-default">바꾸기</button>
		</form>
	</div>
</body>
</html>