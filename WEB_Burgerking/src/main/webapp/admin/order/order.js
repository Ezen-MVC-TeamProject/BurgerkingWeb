function go_search_order(){
	if(document.frm.key.value=="")
		return;
		
	var url = "burger.do?command=adminOrderList&page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}

function go_total_order(){
	document.frm.key.value="";
	document.frm.action = "burger.do?command=adminOrderList&page=1";
	document.frm.submit();
}

function go_order_save(){
	var count = 0;
	if(document.frm.result.length == undefined){
		if(document.frm.result.checked == true)
			count++;
	}else{
		for(var i = 0; i < document.frm.result.length; i++){
			if(document.frm.result[i].checked == true)
				count++;
		}
	}
	
	if(count == 0){
		alert("주문처리할 항목을 선택해 주세요.")
	}else{
		document.frm.action = "burger.do?command=adminOrderSave";
		document.frm.submit();
	}
}
function del_order(){
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
		document.frm.action = "burger.do?command=adminOrderDelete";
	    document.frm.submit();
	}
}