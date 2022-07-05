<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글보기</title>

<!-- 유티리티 js 등록 -->
<script type="text/javascript" src = "/js/util.js"></script>

<!-- 댓글 처리를 위한 객체 포함 등록 -->
<script type="text/javascript" src = "/js/reply.js"></script>

<style type="text/css">
	.dataRow:hover{
		background: #eee;
		cursor: pointer;
	}
</style>

<!-- 댓글 객체를 위한 실제적인 댓글의 처리 -->
<script type="text/javascript">
$(function(){
	// JSON 데이터를 받아내는 처리
	showList();
	//replyService.list({no : ${vo.no }}, function(list){alert(JSON.stringify(list));})

	// 글번호 - 파라메터로 전달하지 않아도 아래에서 선언한 메서드에서 다 사용 가능하다. - 전역변수
	let no = '${vo.no}'
	var bnoValue = '<c:out value = "${list.rno}"/>';
	// 데이터를 가져와서 태그를 넣어야 하는 곳
	let replyUL = $(".chat");
	
	// 댓글 리스트를 보여주는 함수 - 호출하는 경우 - 게시글보기 적용, 댓글 등록, 댓글 수정, 댓글 삭제
	function showList(){
		// 데이터를 가져온다.
		// 태그를 만든다.
		// 정해진곳 (chat)에 태그를 넣는다.
		replyService.list({no : ${vo.no }}, 
				function(list){
					// alert(JSON.stringify(list));
					// 태그를 만든다
					let str = "";
					// 댓글 데이터가 없는경우
					let len = list.length;
					if(list == null || len == 0 )
						str += "<li class='list-group-item'>댓글이 존재하지 않습니다.</li>"
					// 데이터가 있는경우
					else{
						// index를 이용한 반복 처리
						for(var i = 0; i < len; i++){
							
							str += "<li class='left clearfix list-group-item dataRow' data-rno='" + list[i].rno + "'>";
							str += "<div>";
							str += "<div class='header'>";
							str += "<strong class='primary-font writer'>"+ list[i].writer +"</strong>";
							str += "<small class='pull-right text-muted'>" + displayDateTime(list[i].writeDate) + "</small><br>";
// 							str += "<small class='pull-right text-muted'>" + list[i].writeDate + "</small>";
							str += "</div>";
							str += "<p class='content'>" + list[i].content + "</p>";
							str += "</div>";
							str += "</li>"
							console.log(displayDateTime(list[i].writeDate));
						}
					}
					// 정해진곳 (chat)에 태그를 넣는다.
					replyUL.html(str);
				});
		} 
	// showList() 끝---------------
		
	// 댓글등록 이벤트 처리 :: modal 창 열기------------
	$("#writeReplyBtn").click(function(){
// 		alert("댓글 등록 이벤트");
		// 모달창 열기 -> 모달창에 있는 등록 버튼을 클릭하면 작성한 내용을 데이터 객체 만들고 등록처리한다.
		// 원래는 모달창만 열지만 지금은 샘플데이터로 등록이 되는지 테스팅한다. - replyService.write(data, success, error)
		// 제목 바꾸기
		$("#myModal .modal-title").text("댓글등록")
		
		// 댓글번호 입력란 없애기
		$("#rnoDiv, #modalUpdateReplyBtn, #modalDeleteReplyBtn").hide();
		// 보여줄 객체 정의
		$("#modalWriteReplyBtn").show();
		
		// 모달창에 있는 입력한 데이터 지우기
		$("#content").val("")
		$("#writer").val("")
		// 원래의 모달창 열기
		$("#myModal").modal("show");
	});
	// 댓글등록 이벤트 처리끝 :: modal 창 열기------------
	
	// 데이터를 넘겨서 댓글 등록 처리 이벤트----------------------------
	$("#modalWriteReplyBtn").click(function(){
		// 데이터 수집 - no는 위에 전역변수로 존재한다.
		let content = $("#content").val();
		let writer = $("#writer").val();
		
		// 데이터가 비어 있으면 안된다. 체크
		if(!content || !writer){
			alert("데이터가 비어 있으면 안됨");
			return;
		}
		
		// 서버에 데이터를 전달해서 등록한다.
		replyService.write(
			{content : content, writer : writer, no : no},
			function(result){
				alert("RESULT : " + result);
				// 적용이 되었는지 보기위해서 댓글 리스트를 다시 가져와서 뿌린다.
				showList();
				$("#myModal").modal("hide");
			}
		);
	});
	// 데이터를 넘겨서 댓글 등록 처리 이벤트 끝--------------------------

	
	// 댓글을 클릭하여 수정이나 삭제하기 위한 모달 창 열기--------------------------
	// 이벤트가 일어나지 않는다. 객체가 function을 넣을 당시 없는 코드이기 때문에
// 	$(".dataRow").click(function(){
// 		alert("수정 / 삭제"); // 동작안함
// 	});
	// 나중에 생긴 코드들은 이벤트의 전달을 해야하는데 on() 을 사용한다.
	$(".chat").on("click", ".dataRow", function(){
//		alert("on 이벤트 전달- 수정 / 삭제");
		//모달 제목 고치기
		$("#myModal .modal-title").text("댓글 수정 / 삭제");
		
		// 수정할 데이터 수집
		let rno = $(this).data("rno"); // data-rno = '64' ==> 64를 가져온다.
		let content = $(this).find(".content").text();
		let writer = $(this).find(".writer").text();
		
		$("#rno").val(rno);
		$("#content").val(content);
		$("#writer").val(writer);
		
		// 등록버튼 보이지않게
		$("#modalWriteReplyBtn").hide();
		// 꼭 보여줘야 하는 객체
		$("#rnoDiv, #modalUpdateReplyBtn, #modalDeleteReplyBtn").show();
		
		$("#myModal").modal("show");
	});
	// 댓글을 클릭하여 수정이나 삭제하기 위한 모달 창 열기 끝--------------------------
	
	// 모달창 안에 수정 버튼을 클릭해서 수정처리--------------------------
	$("#modalUpdateReplyBtn").click(function(){
		// alert("댓글 수정처리")
		// 데이터 수집
		let rno = $("#rno").val();
		let content = $("#content").val();
		let writer = $("#writer").val();
		
		let reply = {
				rno : rno,
				content : content,
				writer : writer
		}
// 		alert(JSON.stringify(reply));
		replyService.update(reply, function(result){
			alert("RESULT : " + result)
			showList();
			$("#myModal").modal("hide");
		});
	});
	// 모달창 안에 수정 버튼을 클릭해서 수정처리 끝--------------------------
	
		// 댓글을 클릭하여 수정이나 삭제하기 위한 모달 창 열기 끝--------------------------
	
	// 모달창 안에 삭제 버튼을 클릭해서 삭제 처리--------------------------
	$("#modalDeleteReplyBtn").click(function(){
		// alert("댓글 삭제 처리")
		// 데이터 수집
		let rno = $("#rno").val();
		
		replyService.delete(rno, function(result){
			alert("RESULT : " + result)
			showList();
			$("#myModal").modal("hide");
		});
	});
	// 모달창 안에 삭제 버튼을 클릭해서 삭제 처리 끝--------------------------
});
	
</script>
</head>
<body>
	<div class="container">
		<h3>공지사항 글보기</h3>
		<table class="table">
			<tbody>
				<tr>
					<th>번호</th>
					<td>${vo.no }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${vo.title }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${vo.content }</td>
				</tr>
				<tr>
					<th>공지시작일</th>
					<td>
						<fmt:formatDate value="${vo.startDate }" pattern="yyyy.MM.dd"/>
					</td>
				</tr>
				<tr>
					<th>공지종료일</th>
					<td>
						<fmt:formatDate value="${vo.endDate }" pattern="yyyy.MM.dd"/>
					</td>
				</tr>
				<tr>
					<th>공지수정일</th>
					<td>
						<fmt:formatDate value="${vo.updateDate }" pattern="yyyy.MM.dd"/>
					</td>
				</tr>
				<tr>
					<th>공지등록일</th>
					<td>
						<fmt:formatDate value="${vo.writeDate }" pattern="yyyy.MM.dd"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="update.do?no=${vo.no }&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}" class="btn btn-default">수정</a>
						<a href="delete.do?no=${vo.no }" class="btn btn-default">삭제</a>
						<a href="list.do?page=${pageObject.page}&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}" class="btn btn-default">리스트</a>
					</td>
				</tr>
			</tbody>
		</table>
		
		<!-- 댓글 표시부분 : 414 페이지  -->
		<div class="row" style="margin-top: 15px;">
		
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> Reply 
						<button id="writeReplyBtn" class="btn btn-primary btn-xs pull-right">댓글쓰기</button>
					</div>
					<div class="panel-body">
						<div> <strong>* 댓글을 클릭하면 수정 / 삭제를 할 수 있습니다.</strong></div>
						<ul	class="chat list-group">
<!-- 							<li class="left clearfix list-group-item" data-rno='12'> -->
<!-- 								<div class="header"> -->
<!-- 									<strong class="primary-font">작성자</strong> -->
<!-- 									<small class="pull-right text-muted">날짜 데이터</small> -->
<!-- 								</div> -->
<!-- 								<p>댓글 내용이 나오는 부분</p> -->
<!-- 							</li> -->
						</ul>
					</div>
				</div>
			</div>
			
		</div>
	</div>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">댓글 등록</h4>
				</div>
				<div class="modal-body">
					<div class="form-group" id="rnoDiv">
						<label for="rno">댓글 번호</label> 
						<input type="rno" class="form-control" id="rno" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="content">내용</label>
						<textarea class="form-control" rows="5" id="content"></textarea>
					</div>
					<div class="form-group">
						<label for="writer">작성자</label> 
						<input type="text" class="form-control" id="writer">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="modalWriteReplyBtn">등록</button>
					<button type="button" class="btn btn-default" id="modalUpdateReplyBtn">수정</button>
					<button type="button" class="btn btn-default" id="modalDeleteReplyBtn">삭제</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				</div>
			</div>

		</div>
	</div>

</body>
</html>