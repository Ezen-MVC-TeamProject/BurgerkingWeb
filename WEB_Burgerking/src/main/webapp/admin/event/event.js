function go_event_wrt(){
	document.frm.action = "burger.do?command=adminEventWriteForm";
	document.frm.submit();
}

function go_event_search(){
	if(document.frm.key.value=="")
		return;
		
	var url = "burger.do?command=adminEventList&page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}

function go_event_total(){
	document.frm.key.value="";
	document.frm.action = "burger.do?command=adminEventList&page=1";
	document.frm.submit();
}

function del_event(){
	var count = 0;  //  체크된 체크박스의 갯수를 카운트 하기위한 변수
	if(document.frm.delete.length==undefined){   // 장바구니에 물건이 하나일때, 체크박스가 하나일때
		if( document.frm.delete.checked == true)   // 그 체크박스만 체크되어 있는지 확인
			count++;	 
	}else{
		for( var i=0; i<document.frm.delete.length; i++){
			if( document.frm.delete[i].checked==true)
				count++;
		}
	}
	// 지금의 스크립트 명령은 체크박스가 하나도 체크되지 않았다면 원래로 되돌아 가기위한 코드들입니다
	if( count == 0 ){
		alert("삭제할 항목을 선택해주세요");
	} else{
		document.frm.action = "burger.do?command=adminEventDelete";
	    document.frm.submit();
	}
}

function go_event_detail(eseq){
	var url = "burger.do?command=adminEventDetail&eseq=" + eseq;
	document.frm.action = url;
	document.frm.submit();
}

function go_event_mov(){
	location.href="burger.do?command=adminEventList";
}

function go_event_mod(eseq){
	var url = "burger.do?command=adminEventUpdateForm&eseq=" + eseq;
	location.href=url;
}

function go_event_mod_save(){
	if(document.frm.subject.value==""){
		alert("이벤트명를 입력하세요.");
		document.frm.subject.focus();
	}else if(document.frm.enddate.value==""){
		alert("종료일을 입력하세요");
		document.frm.enddate.focus();
	}else if(document.frm.content.value==""){
		alert("상세 설명을 입력하세요.");
		document.frm.content.focus();
	}else{
		if(confirm('수정하시겠습니까?')){
			document.frm.action = "burger.do?command=adminEventUpdate";
			document.frm.submit();
		}
	}
}

function go_event_save(){
	var theForm = document.frm;
	if(theForm.subject.value==""){
		alert("이벤트명을 입력하세요.");
		theForm.subject.focus();
	}else if(theForm.enddate.value==""){
		alert("종료일을 입력하세요");
		theForm.enddate.focus();
	}else if(theForm.content.value==""){
		alert("상세 설명를 입력하세요");
		theForm.content.focus();
	}else if(theForm.image.value==""){
		alert("이벤트 이미지를 입력하세요");
		theForm.image.focus();
	}else{
		theForm.action = "burger.do?command=adminEventWrite";
		theForm.submit();
	}
}