<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 리스트</title>

<link rel="stylesheet" href="/css/list.css">

<script type="text/javascript">
	$(function () {
		$(".dataRow").click(function(){
			let no = $(this).find(".no").text();
			location = "view.do?no=" + no + 
				"&inc=1&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"; 
		})
	});
</script>
</head>

<body>
	<div class="container">
		<h3>이미지 게시판 리스트</h3>
		<div class="row">
			<c:forEach items="${list }" var="vo" varStatus="vs">
				<!-- 줄바꿈을 하기위한 코드 - EL 객체의 삼항연산자 -->
				${(vs.index != 0 && (vs.index % 4 == 0))?"</div><div class='row'>":"" }
				<div class="card-deck col-md-3 dataRow" >
					<div class="card" *ngFor="let item of cards" >
						<div class="embed-responsive embed-responsive-4by3">
							<img src="${vo.fileName }" class="card-img-top embed-responsive-item" alt="tree">
						</div>
						<div class="card-body">
<%-- 							<h5 class="card-title">${vo.title }</h5> --%>
							<h5 class="card-subtitle"><span class="no">${vo.no }</span> ${vo.title }</h5>
							<p class="card-text mt-3">${vo.content }</p>
							<div>${vo.name }(${vo.id }) 
								<span class="pull-right">
									<fmt:formatDate value="${vo.writeDate }" pattern="yyyy.MM.dd"/> 
								</span> 
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${!empty login }">
		<!-- 로그인 한 경우만 버튼보이기 -->
			<div style="margin: 10px 0 10px 0">
				<a href="write.do" class="btn btn-primary" >올리기</a>
			</div>
		</c:if>
			<pageObject:pageNav listURI="list.do" pageObject="${pageObject }"></pageObject:pageNav>
	</div>
</body>
</html>