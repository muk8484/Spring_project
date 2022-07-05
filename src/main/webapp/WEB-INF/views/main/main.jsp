<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

<link rel="stylesheet" href="/css/list.css">

<script type="text/javascript">
	
	$(function() {
		$(".dataRow").click(function() {
			// tag안에 data-module = '데이터' 로 설정한 데이터 가져오기
			let module = $(this).data("module");
			let no = $(this).find(".no").text();
			let url = "/" + module + "/view.do?no=" + no;
			// 글보기 URL
// 			alert(url);
			if(module == "board") url += "&inc=1";
			location = url;
		});
	});
	
</script>
</head>
<body>
	<div class="container">
		<!-- 공지사항과 일반 게시판 -->
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
				  <div class="panel-heading">공지사항</div>
				  <div class="panel-body">
				  	<table class="table">
						<c:choose>
							<c:when test="${noticeList ne null}">
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>시작일</th>
									<th>종료일</th>
								</tr>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${noticeList ne null}">
								<c:forEach items="${noticeList }" var="vo">
									<!-- no : 보여줄 글번호, inc - 조회수 증가 여부. 1:증가, 0:미증가 -->
									<tr class="dataRow" data-module="notice">
										<td class="no">${vo.no }</td>
										<!-- 자바 -> <a href="view.jsp?no=2">자바</a> -->
										<td>${vo.title }</td>
										<td> <fmt:formatDate value="${vo.startDate }" pattern="yyyy-MM-dd"/> </td>
										<td> <fmt:formatDate value="${vo.endDate }" pattern="yyyy-MM-dd"/> </td>
									</tr>
								</c:forEach>
							</c:when>
							<c:when test="${noticeList eq null}">
									<tr class="dataRow" data-module="notice">
										<td>공지사항이 존재하지 않습니다.</td>
									</tr>
							</c:when>
						</c:choose>
					</table>
				  </div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
				  <div class="panel-heading">게시판</div>
				  <div class="panel-body">
				  	<table class="table">
						<c:choose>
							<c:when test="${boardList ne null}">
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
								</tr>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${boardList ne null}">
								<c:forEach items="${boardList }" var="vo">
							<!-- no : 보여줄 글번호, inc - 조회수 증가 여부. 1:증가, 0:미증가 -->
									<tr class="dataRow" data-module="board">
										<td class="no">${vo.no }</td>
										<!-- 자바 -> <a href="view.jsp?no=2">자바</a> -->
										<td>${vo.title }</td>
										<td>${vo.writer }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:when test="${boardList eq null}">
									<tr class="dataRow" data-module="board">
										<td>등록된 글이 없습니다.</td>
									</tr>
							</c:when>
						</c:choose>
					</table>
				  </div>
				</div>
			</div>
		</div>
		<!-- 이미지 게시판 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
				  <div class="panel-heading">이미지</div>
				  <div class="panel-body">
					  <c:choose>
						<c:when test="${imageList ne null}">
						  	<c:forEach items="${imageList }" var="vo" varStatus="vs">
							<!-- 하나의 이미지 -->
							  <div class="col-md-3">
							    <div class="thumbnail dataRow" data-module="image">
							        <img src="${vo.fileName }" alt="${vo.title }" style="width:100%">
							        <div class="caption">
							          <p>[<span class="no">${vo.no}</span>] ${vo.title }</p>
							        </div>
							    </div>
							  </div>
							</c:forEach>
							</c:when>
							<c:when test="${imageList eq null}">
								<tr class="dataRow" data-module="board">
									<td>등록된 글이 없습니다.</td>
								</tr>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>