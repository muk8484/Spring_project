/**
 * 유용한 함수 작성
 */
 
 // 날짜 객체를 형태에 맞는 데이터 문자열 만들기
 
 function displayDateTime(timeStemp){
	// 넘어온 날짜 정보를 가지고 날짜 객체로 만든다.
	var dateTime = new Date(timeStemp);
	
	// 월을 2자리로 만들기
	var month = dateTime.getMonth() +1;
	if (month < 10) month = "0" + month;
	
	// 일 을 2자리로 만들기
	var day = dateTime.getDate();
	if (day < 10) day = "0" + day;
	
	// yyyy.MM.dd
	return dateTime.getFullYear() + "." + month + "." + day;
}
