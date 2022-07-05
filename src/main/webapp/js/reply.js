/**
 * 댓글을 위한 객체
 */
 // 댓글 객체가 적용이 됐는지 알기 위해서 console에 찍는다.
// console.log("reply Module.......");

// replyService 함수  - 처리해야할 함수를 가지고 있는 함수 작성 : 처리 해야할 함수를 리턴해주는 함수
let replyService = (function(){
	
	// -------------실행할 함수 정의-------------
	// 댓글 리스트 가져오기 - list(서버에 넘겨주는 데이터 객체, 데이터를 가져오면 실행되는 함수, 오류가 나면 실행되는 함수)
	function list(param, callback, error){
		// alert("댓글 리스트 가져오기");
		let no = param.no;
		// $ = jquary  $.getJSON() -> 서버에 JSON 데이터를 요청하는 ajax 함수이이다.
		$.getJSON(
			// ajax 서버 url
			"/reply/list.do?no=" + no,
			// 서버에서 데이터를 성공적으로 받아온후에 파라메터로 받는 데이터로 실행되는 함수 
			function(data){
				// 콜백 함수가 있다면 
				if(callback){
					// 콜백 함수 실행한다.
					callback(data);
				}
			// 데이터 가져오기에서 실패 했을때 실행되는 함수
		}).fail(function(xhr, status, err){
			// error() 가 있으면 실행한다.
			if(error) error();
			else alert("데이터 가져오기를 실패하셨습니다.")
		});
		// ------ $.getJSON()끝
	}
	// ------- list()끝
	
	
	// 댓글 등록하기 - write(서버에 넘겨주는 데이터 객체{}, 데이터를 가져오면 실행되는 함수, 오류가 나면 실행되는 함수)
	function write(reply, callback, error){
		//alert("reply.js - 댓글 등록 실행 - reply" + JSON.stringify(reply));
		// jquery의 ajax 함수 - load(), getJSON, ajax() - ajax 의 동작은 틀에 맞게 데이터 입력하면 동작한다.
		$.ajax({
			url : "/reply/write.do", // ajax를 통해서 요청되는 서버 주소
			type : "post", // 데이터 전달방식
			data : JSON.stringify(reply), // 전달되는 데이터
			contentType : "application/json; charset=utf-8", // 전달되는 데이터의 형식
			success : function(result, states, xhr){ //요청한 처리가 정상적으로 동작했을 때 함수
				if(callback) callback(result);
				else 
					alert("댓글 등록이 되었습니다.");
			},
			error : function(xhr, status, er){ //요청한 처리가 비정상적으로 동작해서 오류가 났을때 함수
				if(error) error();
				else alert("댓글 등록 실패");
			}
		});
	}
	// ------- write()끝
	
	// ------- update() 시작
	function update(reply, callback, error){
		alert("reply.js - 댓글 수정 실행 - reply" + JSON.stringify(reply));
		$.ajax({
			url : "/reply/update.do", // ajax를 통해서 요청되는 서버 주소
			type : "patch", // 데이터 전달방식
			data : JSON.stringify(reply), // 전달되는 데이터
			contentType : "application/json; charset=utf-8", // 전달되는 데이터의 형식
			success : function(result, states, xhr){ //요청한 처리가 정상적으로 동작했을 때 함수
				if(callback) callback(result);
				else 
					alert("댓글이 수정이 되었습니다.");
			},
			error : function(xhr, status, er){ //요청한 처리가 비정상적으로 동작해서 오류가 났을때 함수
				if(error) error();
				else alert("댓글 수정 실패");
			}
		});
	}
	// ------- update() 끝
	
	
	// ------- delete() 시작
	function deleteReplay(rno, callback, error){
		alert("reply.js - 댓글 삭제 실행 - delete" + JSON.stringify(rno));
		$.ajax({
			url : "/reply/delete.do?rno=" + rno, // ajax를 통해서 요청되는 서버 주소
			type : "delete", // 데이터 전달방식
			success : function(result, states, xhr){ //요청한 처리가 정상적으로 동작했을 때 함수
				if(callback) callback(result);
				else 
					alert("댓글이 삭제 되었습니다.");
			},
			error : function(xhr, status, er){ //요청한 처리가 비정상적으로 동작해서 오류가 났을때 함수
				if(error) error();
				else alert("댓글 삭제 실패");
			}
		});
	}
	// ------- delete() 끝

	// 위에 만들어놓은 함수를 객체로 만들어서 리턴해준다.
	return {
		// : 앞에 list 는 변수(속성)이다. : 뒤에 list 는 위에서 선언한 함수 자체이다. replyService.list();
		list : list,
		write : write,
		update : update,
		delete : deleteReplay // delete 는 예약어라 이미 사용중
	};
	
})(); // listService 안에 함수를 실행하는 것을 저장 -> 결과는 list 가 있는 객체가 나온다.	