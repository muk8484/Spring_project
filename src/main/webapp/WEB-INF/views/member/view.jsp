<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title }</title>
</head>
<body>
	<div class="container">
		<h2>${title }</h2>
		<table class="table">
			<tr>
				<th>아이디</th>
				<td>${vo.id }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${vo.name }</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>${vo.gender }</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><fmt:formatDate value="${vo.birth }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>${vo.tel }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${vo.email }</td>
			</tr>
			<tr>
				<th>가입일</th>
				<td><fmt:formatDate value="${vo.regDate }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<th>사진</th>
				<td><img alt="${vo.photo }" src="${vo.photo }"></td>
			</tr>
			<tr>
				<th>등급</th>
				<td>${vo.gradeName }</td>
			</tr>
			<tr>
				<td colspan="2">
				<!-- 개인 메뉴 -->
					<c:if test="${param.id eq null }">
						<a href="update.do" class="btn btn-default" >정보수정</a>
						<a href="delete.do" class="btn btn-default" >회원탈퇴</a>
						<a href="changePassword.do" class="btn btn-default" >비밀번호변경</a>
					</c:if>
				<!-- 관리자 메뉴 -->
					<c:if test="${param.id ne null && login.gradeNo eq 9 && login.id ne vo.id}">
						<form action="changeStatus.do" method="post">
							<input type="hidden" name="id" value="${vo.id }">
							<input type="hidden" name="page" value="${param.page }">
							<input type="hidden" name="perPageNum" value="${param.perPageNum }">
							<!-- 상태변경 -->
							<div class="input-group col-md-5">
								<select name="status" class="form-control">
									<option ${(vo.status == "정상")?"selected":"" } >정상</option>
									<option ${(vo.status == "탈퇴")?"selected":"" } >탈퇴</option>
									<option ${(vo.status == "강퇴")?"selected":"" } >강퇴</option>
									<option ${(vo.status == "휴면")?"selected":"" } >휴면</option>
								</select>
								<div class="input-group-btn">
									<button class="btn btn-default">변경</button>
								</div>
							</div>
						</form>
						<form action="changeGradeNo.do" method="post">
							<input type="hidden" name="id" value="${vo.id }">
							<input type="hidden" name="page" value="${param.page }">
							<input type="hidden" name="perPageNum" value="${param.perPageNum }">
							<!-- 등급변경 -->
							<div class="input-group col-md-5">
									<select name="gradeNo" class="form-control">
										<option ${(vo.gradeNo == 1)?"selected":"" } value="1">일반회원</option>
										<option ${(vo.gradeNo == 5)?"selected":"" } value="5">파트너</option>
										<option ${(vo.gradeNo == 9)?"selected":"" } value="9">관리자</option>
									</select>
								<div class="input-group-btn">
									<button class="btn btn-default">변경</button>
								</div>
							</div>
						</form>
						<div  class="input-group-btn">
							<a href="list.do?page=${param.page }&perPageNum=${param.perPageNum }" class="btn btn-default">list</a>
						</div>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>