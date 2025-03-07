var winW = top.window.innerWidth || top.document.documentElement.clientWidth || top.document.body.clientWidth;
var winH = top.window.innerHeight || top.document.documentElement.clientHeight || top.document.body.clientHeight;

/*******************************************************************************
 * 체크박스
 ******************************************************************************/

function cfn_fromChkAll(formName,chkName){
	var irow = $("#"+formName).children().find('input[name='+chkName+']').length;
	var chked = $("#"+formName).children().find("input:checkbox[id="+chkName+"All]").is(":checked");
	if(chked == true){
		for(i = 0; i < irow; i++){
			$("#"+formName).children().find('input[name='+chkName+']').eq(i).prop("checked", true);
			$("#"+formName).children().find('input[name='+chkName+'Flag]').eq(i).val('true');
		}
	}else{
		for(i = 0; i < irow; i++){
			$("#"+formName).children().find('input[name='+chkName+']').eq(i).prop("checked", false);
			$("#"+formName).children().find('input[name='+chkName+'Flag]').eq(i).val('false');
		}
	}
}

function cfn_fromChkb(formName,chkName,idx){
	var chked = $("#"+formName).children().find("input:checkbox[id="+chkName+"]").eq(idx).is(":checked");
	if(chked == true){
		$("#"+formName).children().find('input[name='+chkName+'Flag]').eq(idx).val('true');
	}else{
		$("#"+formName).children().find('input[name='+chkName+'Flag]').eq(idx).val('false');
	}
}


function cfn_chkAll() {

	var irow = document.getElementsByName("chkb").length;
	if (document.getElementById("chkbAll").checked == true) {
		for (i = 1; i < irow; i++) {
			document.getElementsByName("chkb")[i].checked = true;
			document.getElementsByName("saveFlag")[i].value = "save";
		}
	}
	if (document.getElementById("chkbAll").checked == false) {
		for (i = 1; i < irow; i++) {
			document.getElementsByName("chkb")[i].checked = false;
			document.getElementsByName("saveFlag")[i].value = "skip";
		}

	}
}

function cfn_selchkb(idx) {
	if (document.getElementsByName("chkb")[idx].checked == true) {
		document.getElementsByName("saveFlag")[idx].value = "save";
		document.getElementsByName("chkb")[idx].checked = true;
	} else {
		document.getElementsByName("saveFlag")[idx].value = "skip";
		document.getElementsByName("chkb")[idx].checked = false;
	}
}

function cfn_chkAllTrue() {

	var irow = document.getElementsByName("chkb").length;
	for (i = 1; i < irow; i++) {
		document.getElementsByName("chkb")[i].checked = true;
		document.getElementsByName("saveFlag")[i].value = "save";
	}
}


function cfn_chkObjAll(objNm) {

	var irow = document.getElementsByName(objNm).length;
	if (document.getElementById("chkAll_"+objNm).checked == true) {
		for (i = 1; i < irow; i++) {
			document.getElementsByName("chk_"+objNm)[i].checked = true;
			document.getElementsByName(objNm)[i].value = "Y";
		}
	}
	if (document.getElementById("chkAll_"+objNm).checked == false) {
		for (i = 1; i < irow; i++) {
			document.getElementsByName("chk_"+objNm)[i].checked = false;
			document.getElementsByName(objNm)[i].value = "N";
		}

	}
}


/*******************************************************************************
 * div스크롤 splitGrid
 ******************************************************************************/
function cfn_splitGrid_scrollRight(x, y) {
	divRightTitle.scrollLeft = x;
	divLeftData.scrollTop = y;
}

function cfn_splitGrid_scrollLeft(x, y) {
	divLeftTitle.scrollLeft = x;
	divRightData.scrollTop = y;
}

function cfn_splitGrid_contentsPageResize(v_marginSplitLeft, v_marginHeight) {

	var marginHeight = Number(v_marginHeight);
	var marginSplitLeft = Number(v_marginSplitLeft);

	var leftMenuWidth = 250;
	var widthMargin = 5;
	if (navigator.userAgent.toLowerCase().indexOf("msie") > 0) {
		widthMargin = 0;
	}

	var vFullWidth = $(window).width() - leftMenuWidth - 44;

	$('#divRightTitle').css('width', vFullWidth - marginSplitLeft);
	$('#divRightData').css('width', vFullWidth - marginSplitLeft);
	$('#divRightData').css('height', $(window).height() - marginHeight);
	$('#divLeftData').css('height', $(window).height() - marginHeight);

}


/*******************************************************************************
 * div스크롤 Grid
 ******************************************************************************/
function cfn_grid_scroll(x, y) {
	DataHeader.scrollLeft = x;
	DataList.scrollTop = y;
}

function cfn_grid_contentsPageResize(v_marginSplitLeft, v_marginHeight) {
	
	

	var marginHeight = Number(v_marginHeight);
	var marginSplitLeft = Number(v_marginSplitLeft);

	var leftMenuWidth = 250;
	var widthMargin = 0;
	if (navigator.userAgent.toLowerCase().indexOf("msie") > 0) {
		widthMargin = 0;
	}

	if(cfn_browserMode()  == "mobile"){
		leftMenuWidth = 0;
	}

	$('#DataHeader').css('width', $(window).width() - (leftMenuWidth + marginSplitLeft + widthMargin));
	$('#DataList').css('width', $(window).width() - (leftMenuWidth + marginSplitLeft + widthMargin));
	$('#DataList').css('height', $(window).height() - marginHeight);

}

function cfn2_splitGrid_sameHeight(rowCount) {
	
	
	var chk1 = "";
	for(var i = 0 ; i < rowCount; i++){
		var ltrh  =  $('#LeftDataTable tr').eq(i).height();
		var rtrh  =  $('#RightDataTable tr').eq(i).height();
	
		if(ltrh > rtrh){
			$('#RightDataTable tr').eq(i).css("height",ltrh);
		}
		if(ltrh < rtrh){
			$('#LeftDataTable tr').eq(i).css("height",rtrh+1.65);
		}
		
		
	}
	
	
	
}

/*******************************************************************************
 * 첫째줄 값 일괄적용
 ******************************************************************************/

/*******************************************************************************
 * 커럼변경시 자동 체크
 ******************************************************************************/
function cfn_changChkb(idx) {
	document.getElementsByName("saveFlag")[idx].value = "save";
	document.getElementsByName("chkb")[idx].checked = true;
}

function cfn_chkbFlag(idx) {
	document.getElementsByName("chkbFlag")[idx].value = "true";
	document.getElementsByName("chkb")[idx].checked = true;
}

/*******************************************************************************
 * 컬럼숨기기
 ******************************************************************************/
function cfn_columnHide() {

	var chkHideColumns = $("#hideColumns").val();
	var arrHideColumns = chkHideColumns.split("^");

	$('input:checkbox[name="chkHideColumns"]').each(function() {
		chkbName = $(this).val();

		if (chkHideColumns.indexOf(chkbName) > 0) {

			$("th[id^='" + chkbName + "']").hide();
			$("td[id^='" + chkbName + "']").hide();

			this.checked = false; // checked 처리
		} else {

			$("th[id^='" + chkbName + "']").show();
			$("td[id^='" + chkbName + "']").show();

			this.checked = true; // checked 처리
		}

	});

}

function cfn_setColumn(vProgNm) {

	var chkedVal = "";

	$('input:checkbox[name="chkHideColumns"]').each(function() {

		chkbName = $(this).val();

		if (this.checked == true) {

			$("th[id^='" + chkbName + "']").show();
			$("td[id^='" + chkbName + "']").show();

		} else {

			chkedVal += "^" + chkbName;

			$("th[id^='" + chkbName + "']").hide();
			$("td[id^='" + chkbName + "']").hide();
		}

	});

	$("#hideColumns").val(chkedVal);

	cfn_columnHide();
	$("#divHideColumns").hide();

	// DB에저장
	cfn_hiddenColsSave(vProgNm);
}

function cfn_columnHideAll(vProgNm) {

	var chkedVal = "";

	$('input:checkbox[name="chkHideColumns"]').each(function() {
		chkbName = $(this).val();

		$("th[id^='" + chkbName + "']").hide();
		$("td[id^='" + chkbName + "']").hide();

		this.checked = false; // checked 처리

		chkedVal += "^" + chkbName;

	});

	$("#hideColumns").val(chkedVal);

	cfn_columnHide();
	$("#divHideColumns").hide();

	// DB에저장
	cfn_hiddenColsSave(vProgNm);
}

function cfn_columnShowAll(vProgNm) {

	var chkedVal = "";

	$('input:checkbox[name="chkHideColumns"]').each(function() {
		chkbName = $(this).val();

		$("th[id^='" + chkbName + "']").show();
		$("td[id^='" + chkbName + "']").show();

		this.checked = true; // checked 처리

	});

	$("#hideColumns").val(chkedVal);

	cfn_columnHide();
	$("#divHideColumns").hide();

	// DB에저장
	cfn_hiddenColsSave(vProgNm);
}

function cfn_divHideColumnsView() {
	$("#divHideColumns").show();
}
function cfn_divHideColumnsClose() {
	$("#divHideColumns").hide();
}

function cfn_trSelectBackgroundColor(rowid) {

	$('#LeftDataTable tr').css("background-color", "");
	$('#RightDataTable tr').css("background-color", "");

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#LeftDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
			$('#RightDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

function cfn_trSelectBackgroundColorObj(objNm, rowid) {

	$('#"+objNm+" tr').css("background-color", "");

	for (var i = 0; i < $('#"+objNm+" tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#"+objNm+" tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

// DB에저장
function cfn_hiddenColsSave(vProgNm) {
	var frm = document.searchForm;

	frm.target = "trxnFrame";
	frm.action = "/commonDB/sysProgColsTrxn.do?progNm=" + vProgNm;
	frm.submit();

}

function cfn_trSelectBackgroundColorObj(objName, rowid) {

	$(objName + ' tr').css("background-color", "");

	for (var i = 0; i < $(objName + ' tr').length; i++) {
		if (i == (rowid - 1)) {
			$(objName + ' tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

// iframe resize
function cfn_autoResize(i) {
	var iframeHeight = (i).contentWindow.document.body.scrollHeight;
	(i).height = iframeHeight + 20;
}

/*******************************************************************************
 * 영문자로 시작하는5~20자 영문자 또는 숫자이어야 합니다.
 ******************************************************************************/
function cfn_check_memberId(Obj) {
	
	str = Obj.val().toLowerCase().trim();
	
	if(str.length < 5){
		alert("아이디는 5자 이상 20자 이내 입니다.");
		return false;
	}
	if(str.length > 20){
		alert("아이디는 5자 이상 20자 이내 입니다.");
		return false;
	}

	var blank_pattern = /[\s]/g;
	if (blank_pattern.test(str.value) == true) {
		alert(' 공백은 사용할 수 없습니다. ');
		return false;
	}
	var idReg = /^[a-z]+[a-z0-9]{4,19}$/g;
	if (!idReg.test(str)) {
		alert(" 영문자로 시작하는5~20자 영문자 또는 영문 +숫자이어야 합니다.");
		return false;
	}

	return true;
}










function cfn_winPopup(vPopMode, vWidth, vHeight, vUrl, vPopNm) {
	
	var winHeight = $(window).height();
	var winWidth = $(window).width();

	var popWidth = 500;
	var popHeight = 500;

	var vTop = 100;
	var vLeft = 100;

	var popType = "winPop";

	if (vPopMode == "win_fix") {

		popWidth = vWidth;
		popHeight = vHeight;
	}
	if (vPopMode == "win_mrgn") {

		popWidth = $(window).width() - vWidth;
		popHeight = $(window).height() - vHeight;
	}
	if (vPopMode == "win_hfix") {

		popWidth = ($(window).width() - vWidth);
		popHeight = vHeight;
	}
	if (vPopMode == "win_wfix") {

		popWidth = vWidth;
		popHeight = $(window).height() - vHeight;
	}
	if (vPopMode == "layer_fix") {
		popWidth = vWidth;
		popHeight = vHeight;
	}
	if (vPopMode == "layer_mrgn") {

		popWidth = $(window).width() - vWidth;
		popHeight = $(window).height() - vHeight;
	}
	if (vPopMode == "layer_hfix") {
		popWidth = ($(window).width() - vWidth);
		popHeight = vHeight;
	}
	if (vPopMode == "layer_wfix") {
		popWidth = vWidth;
		popHeight = $(window).height() - vHeight;
	}

	vTop = ($(window).height() - popHeight) / 2;
	vLeft = ($(window).width() - popWidth) / 2;

	if (vPopMode.indexOf("win_") > -1) {

		window.open(vUrl, vPopNm, "top=" + vTop + ",left=" + vLeft + ", width=" + popWidth + ", height=" + popHeight + ",location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes");
	}
	if (vPopMode.indexOf("layer_") > -1) {
		

		cfn_ModalOpenWindow(vUrl, vPopNm, popWidth, popHeight);
	}
}

function cfn_winPopMargin(vUrl, vWm, vHm) {

	var vH = $(window).height() - vHm;
	var vW = $(window).width() - vHm;

	var vTop = ($(window).height() - vH) / 2;
	var vLeft = ($(window).width() - vW) / 2;
	window.open(vUrl, "winPop", "top=" + vTop + ",left=" + vLeft + ", width=" + vW + ", height=" + vH + ",location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes");
}

function cfn_winPopNmMargin(vUrl, vPopNm, vWm, vHm) {

	var vH = $(window).height() - vHm;
	var vW = $(window).width() - vHm;

	var vTop = ($(window).height() - vH) / 2;
	var vLeft = ($(window).width() - vW) / 2;
	window.open(vUrl, vPopNm, "top=" + vTop + ",left=" + vLeft + ", width=" + vW + ", height=" + vH + ",location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes");
}

function cfn_winPopNmFix(vUrl, vPopNm, vW, vH) {

	var vTop = ($(window).height() - vH) / 2;
	var vLeft = ($(window).width() - vW) / 2;
	window.open(vUrl, vPopNm, "top=" + vTop + ",left=" + vLeft + ", width=" + vW + ", height=" + vH + ",location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes");
}

function cfn_srhTerm(vOpt) {

	document.getElementById('srhTermOpt').value = vOpt;

	var dt = new Date();
	var month = dt.getMonth() + 1;
	var day = dt.getDate();
	var year = dt.getFullYear();

	if (vOpt == "today") {

		document.getElementById('srhFrDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
		document.getElementById('srhToDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
	}

	if (vOpt == "thisWeek") {

		var myDate = new Date();
		var dayOfMonth = myDate.getDate();
		myDate.setDate(dayOfMonth - 7);

		var frYear = myDate.getFullYear();
		var frMonth = myDate.getMonth() + 1;
		var frDay = myDate.getDate();

		document.getElementById('srhFrDate').value = frYear + "-" + lpad(2, '0', frMonth) + "-" + lpad(2, '0', frDay);
		document.getElementById('srhToDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
	}

	if (vOpt == "thisMonth") {

		var myDateMonth = new Date();
		myDateMonth.setMonth(myDateMonth.getMonth() - 1);

		var frYear = myDateMonth.getFullYear();
		var frMonth = myDateMonth.getMonth() + 1;
		var frDay = myDateMonth.getDate();

		document.getElementById('srhFrDate').value = frYear + "-" + lpad(2, '0', frMonth) + "-" + lpad(2, '0', frDay);
		document.getElementById('srhToDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
	}
	if (vOpt == "threeMonth") {

		var myDateMonth = new Date();
		myDateMonth.setMonth(myDateMonth.getMonth() - 3);

		var frYear = myDateMonth.getFullYear();
		var frMonth = myDateMonth.getMonth() + 1;
		var frDay = myDateMonth.getDate();

		document.getElementById('srhFrDate').value = frYear + "-" + lpad(2, '0', frMonth) + "-" + lpad(2, '0', frDay);
		document.getElementById('srhToDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
	}
	if (vOpt == "") {
		document.getElementById('srhFrDate').value = "";
		document.getElementById('srhToDate').value = "";
	}
}

function cfn_setRadioValue(vObjNm, vVal) {
	$("#" + vObjNm).val(vVal);

	if (vObjNm == "lastLvlYn") {
		fn_propItemInput();
	}
}

function cfn_orderBy(columnName, ascDesc) {
	document.searchForm.srhOrderBy.value = columnName;
	document.searchForm.srhOrderAsc.value = ascDesc;
	fn_searchPage('1');

}

function cfn_selectPageNav(iTotalCnt, iPageRow, iCurrPage) {

	var iTotalCnt = Number(iTotalCnt);
	var iPageRow = Number(iPageRow);

	var iPageCnt = Math.ceil(iTotalCnt / iPageRow);
	var iRemainder = iTotalCnt % iPageRow;
	if (iTotalCnt > iPageCnt * iPageRow) {
		iPageCnt = iPageCnt + 1;
	}

	var iCurrPage = Number(iCurrPage);

	var src = "";
	src += "페이지 자료수: ";
	src += "<select id=\"_rows\" name=\"_rows\" onchange=\"fn_searchPage('1')\" style=\"height:22px;\">  ";
	src += "     <option value=\"10\">10</option> ";
	src += "     <option value=\"20\">20</option> ";
	src += "     <option value=\"30\">30</option> ";
	src += "     <option value=\"50\">50</option> ";
	src += "     <option value=\"100\">100</option> ";
	src += " </select>  ";

	src += " &nbsp;&nbsp; 페이지: ";
	if (iCurrPage > 1) {
		src += "    <a onclick=\"fn_searchPage('" + (iCurrPage - 1) + "')\" style=\"cursor:pointer\"><img src=\"/img/ezwx/icon_prev.gif\" align=\"absmiddle\"></a> ";
	}

	src += "    <select name=\"pageNo\" id=\"pageNo\" onchange=\"fn_searchPage(this.value)\" style=\"border:2px solid #D31D2F;height:22px;\"> ";
	for (i = 1; i <= iPageCnt; i++) {

		if (iCurrPage == i) {
			src += "    <option value=\"" + i + "\" selected> " + i + " of " + iPageCnt + " </option>";
		} else {
			src += "    <option value=\"" + i + "\" > " + i + " of " + iPageCnt + " </option>";
		}
	}
	src += "    </select>  ";
	src += " ";
	if (iCurrPage < iPageCnt) {
		src += "    <a onclick=\"fn_searchPage('" + (iCurrPage + 1) + "')\" style=\"cursor:pointer\"><img src=\"/img/ezwx/icon_next.gif\" align=\"absmiddle\"></a>  ";
	}

	$("#td_pageNav").html(src);

}

function cfn_buttonPageNavNew(iTotalCnt, iPageRow, iCurrPage) {

	var iCurrPage = Number(iCurrPage);
	var iTotalCnt = Number(iTotalCnt);
	var iPageRow = Number(iPageRow);
	var iPageBlock = 10;

	var iPageCnt = Math.ceil(iTotalCnt / iPageRow);
	var iRemainder = iTotalCnt % iPageRow;
	if (iTotalCnt > iPageCnt * iPageRow) {
		iPageCnt = iPageCnt + 1;
	}

	var iCurrBlock = Math.floor(iCurrPage / iPageBlock);

	var iBlockRemainder = iCurrPage % iPageBlock;
	if (iBlockRemainder > 0) {
		iCurrBlock = iCurrBlock + 1;
	}

	var iFrPage = (iCurrBlock - 1) * iPageBlock + 1;
	var iToPage = iCurrBlock * iPageBlock;
	if (iToPage > iPageCnt) {
		iToPage = iPageCnt;
	}

	if(iPageCnt < 1){
		iPageCnt = 1;
	}
	
	var nextPageYn = "N";
	if (iPageCnt > iCurrPage) {
		nextPageYn = "Y";
	}
	
	
	var src = "";

	

	if (iCurrPage > 1) {
		src += "<a onclick=\"fn_searchPage('1')\" style=\"cursor:pointer\" class=\"first\"><img src=\"/img/ezwx/pag_first.gif\" class=\"first\" alt=\"맨처음\"></a>&nbsp;";
		src += "<a onclick=\"fn_searchPage('" + (iCurrPage - 1) + "')\" style=\"cursor:pointer\" class=\"prev\"><img src=\"/img/ezwx/pag_prev.gif\" class=\"prev\" alt=\"이전\"></a>&nbsp;";
	} else {
		src += "<a onclick=\"#\"  class=\"first\"><img src=\"/img/ezwx/pag_first.gif\" class=\"first\" alt=\"맨처음\"></a>&nbsp;";
		src += "<a href\"#\"  class=\"prev\"><img src=\"/img/ezwx/pag_prev.gif\" class=\"prev\" alt=\"이전\"></a>&nbsp;";
	}

	// src += "&nbsp;";
	for (i = iFrPage; i <= iToPage; i++) {

		if (i == iCurrPage) {
			src += "<span class='num' style=\"cursor:pointer;padding:0px 10px;text-align:center;border:0px solid #c0c0c0; font-size:12pt; font-weight: bold;display:inline-block;\" ><b>" + i + "</b></span>&nbsp;";
		} else {
			src += "<span onclick=\"fn_searchPage('" + i + "')\" style=\"cursor:pointer;padding:0px 10px;text-align:center;border:0px solid #c0c0c0;font-size:12pt; \">" + i + "</span>&nbsp;";
		}

	}
	if (nextPageYn == "Y") {
		src += "<a onclick=\"fn_searchPage('" + (iCurrPage + 1) + "')\" style=\"cursor:pointer\" class=\"next\"><img src=\"/img/ezwx/pag_next.gif\" alt=\"다음\" class=\"next\" /></a>&nbsp;";
		src += "<a onclick=\"fn_searchPage('" + iPageCnt + "')\" style=\"cursor:pointer\"  class=\"last\"><img src=\"/img/ezwx/pag_last.gif\" class=\"last\" alt=\"맨마지막\"></a>";

	} else {
		src += "<a href\"#\" class=\"next\"><img src=\"/img/ezwx/pag_next.gif\" alt=\"다음\" class=\"next\" /></a>&nbsp;";
		src += "<a onclick=\"#\"  class=\"last\"><img src=\"/img/ezwx/pag_last.gif\" class=\"last\" alt=\"맨마지막\"></a>";
	}

	
	
	return src;

}

function cfn_buttonPageNavBlock(iTotalCnt, iPageRow, iCurrPage,iPageBlock) {

	var iCurrPage = Number(iCurrPage);
	var iTotalCnt = Number(iTotalCnt);
	var iPageRow = Number(iPageRow);
	var iPageBlock = Number(iPageBlock);

	var iPageCnt = Math.ceil(iTotalCnt / iPageRow);
	var iRemainder = iTotalCnt % iPageRow;
	if (iTotalCnt > iPageCnt * iPageRow) {
		iPageCnt = iPageCnt + 1;
	}

	var iCurrBlock = Math.floor(iCurrPage / iPageBlock);

	var iBlockRemainder = iCurrPage % iPageBlock;
	if (iBlockRemainder > 0) {
		iCurrBlock = iCurrBlock + 1;
	}

	var iFrPage = (iCurrBlock - 1) * iPageBlock + 1;
	var iToPage = iCurrBlock * iPageBlock;
	if (iToPage > iPageCnt) {
		iToPage = iPageCnt;
	}

	if(iPageCnt < 1){
		iPageCnt = 1;
	}
	
	var nextPageYn = "N";
	if (iPageCnt > iCurrPage) {
		nextPageYn = "Y";
	}
	
	
	var src = "";

	

	if (iCurrPage > 1) {
		src += "<a onclick=\"fn_searchPage('1')\" style=\"cursor:pointer\" class=\"first\"><img src=\"/img/ezwx/pag_first.gif\" class=\"first\" alt=\"맨처음\"></a>&nbsp;";
		src += "<a onclick=\"fn_searchPage('" + (iCurrPage - 1) + "')\" style=\"cursor:pointer\" class=\"prev\"><img src=\"/img/ezwx/pag_prev.gif\" class=\"prev\" alt=\"이전\"></a>&nbsp;";
	} else {
		src += "<a onclick=\"#\"  class=\"first\"><img src=\"/img/ezwx/pag_first.gif\" class=\"first\" alt=\"맨처음\"></a>&nbsp;";
		src += "<a href\"#\"  class=\"prev\"><img src=\"/img/ezwx/pag_prev.gif\" class=\"prev\" alt=\"이전\"></a>&nbsp;";
	}

	// src += "&nbsp;";
	for (i = iFrPage; i <= iToPage; i++) {

		if (i == iCurrPage) {
			src += "<span class='num' style=\"cursor:pointer;padding:0px 10px;text-align:center;border:1px solid #c0c0c0; font-size:12pt; font-weight: bold;display:inline-block;\" ><b>" + i + "</b></span>&nbsp;";
		} else {
			src += "<span onclick=\"fn_searchPage('" + i + "')\" style=\"cursor:pointer;padding:1px 10px;text-align:center;border:0px solid #c0c0c0;font-size:12pt; \">" + i + "</span>&nbsp;";
		}

	}
	if (nextPageYn == "Y") {
		src += "<a onclick=\"fn_searchPage('" + (iCurrPage + 1) + "')\" style=\"cursor:pointer\" class=\"next\"><img src=\"/img/ezwx/pag_next.gif\" alt=\"다음\" class=\"next\" /></a>&nbsp;";
		src += "<a onclick=\"fn_searchPage('" + iPageCnt + "')\" style=\"cursor:pointer\"  class=\"last\"><img src=\"/img/ezwx/pag_last.gif\" class=\"last\" alt=\"맨마지막\"></a>";

	} else {
		src += "<a href\"#\" class=\"next\"><img src=\"/img/ezwx/pag_next.gif\" alt=\"다음\" class=\"next\" /></a>&nbsp;";
		src += "<a onclick=\"#\"  class=\"last\"><img src=\"/img/ezwx/pag_last.gif\" class=\"last\" alt=\"맨마지막\"></a>";
	}

	
	
	return src;

}


function cfn_srh() {
	if (event.keyCode == 13) {
		if (document.searchForm.pageNo != null) {
			fn_searchPage('1');
		} else {
			fn_search();
		}
	}
}

function cfn_processMsg() {

	var processTop = ($(window).height() - 100) / 2;
	var processLeft = ($(window).width() - 500) / 2;
	document.getElementById("processMsg").style.top = processTop + "px";
	document.getElementById("processMsg").style.left = processLeft + "px";
	document.getElementById("processMsg").style.display = "";

}

function cfn_setRadioValue(vObjNm, vVal) {
	$("#" + vObjNm).val(vVal);

	if (vObjNm == "lastLvlYn") {
		fn_propItemInput();
	}
}

function cfn_chkbox_input(vObjNm, vVal) {

	if ($("input:checkbox[id='chk_" + vObjNm + "']").is(":checked") == true) {
		$("#vObjNm").val(vVal);
	} else {

		if (vVal == "Yes") {
			document.getElementById(vObjNm).value = "No";
		}
		if (vVal == "Y") {
			document.getElementById(vObjNm).value = "N";
		}
		if (vVal == "1") {
			document.getElementById(vObjNm).value = "0";
		}
	}
}

function cfn_chkbox_input_YN(vObjNm) {

	if ($("input:checkbox[id='chk_" + vObjNm + "']").is(":checked") == true) {
		document.getElementById(vObjNm).value = "Y";
	} else {
		document.getElementById(vObjNm).value = "N";
	}
}

function cfn_trSelectBackgroundColor(rowid) {

	$('#LeftDataTable tr').css("background-color", "");
	$('#RightDataTable tr').css("background-color", "");

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#LeftDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
			$('#RightDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

function cfn_trSelectBackground(rowid) {

	if ($('#LeftDataTable tr') != null) {
		// $('#LeftDataTable tr').css("border", "");
		$('#LeftDataTable tr').css("background-color", "");
	}
	if ($('#RightDataTable tr') != null) {
		// $('#RightDataTable tr').css("border", "");
		$('#RightDataTable tr').css("background-color", "");
	}

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			// $('#LeftDataTable tr:eq(' + i + ')').css("border", "3px solid
			// #8080C0");
			$('#LeftDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");

			if ($('#RightDataTable tr') != null) {
				// $('#LeftDataTable tr:eq(' + i + ')').css("border-right",
				// "0px");
				$('#RightDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
				// $('#RightDataTable tr:eq(' + i + ')').css("border", "3px
				// solid #8080C0");
				// $('#RightDataTable tr:eq(' + i + ')').css("border-left",
				// "0px");
			}

		}
	}
}



function cfn_trSelectBorder(rowid) {

	if ($('#LeftDataTable tr') != null) {
		$('#LeftDataTable tr').css("background-color", "");
	}
	if ($('#RightDataTable tr') != null) {
		$('#RightDataTable tr').css("background-color", "");
	}

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#LeftDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
			$('#RightDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");

		}
	}
}

function cfn_tableTrSelectBorder(tableId,rowid) {

	if ($('#'+tableId+' tr') != null) {
		$('#'+tableId+' tr').css("background-color", "");
	}

	
	for (var i = 0; i < $('#'+tableId+' tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#'+tableId+' tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

function cfn_trSelectBorder_del(rowid) {

	if ($('#LeftDataTable tr') != null) {
		$('#LeftDataTable tr').css("border", "");
	}
	if ($('#RightDataTable tr') != null) {
		$('#RightDataTable tr').css("border", "");
	}

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#LeftDataTable tr:eq(' + i + ')').css("border", "2px solid #8080C0");

			if ($('#RightDataTable tr') != null) {
				$('#LeftDataTable tr:eq(' + i + ')').css("border-right", "0px");
				$('#RightDataTable tr:eq(' + i + ')').css("border", "2px solid #8080C0");
				$('#RightDataTable tr:eq(' + i + ')').css("border-left", "0px");
			}

		}
	}
}

function cfn_trSelectBorderChkb_del(rowid) {

	$('#LeftDataTable tr').css("border", "");
	$('#RightDataTable tr').css("border", "");

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#LeftDataTable tr:eq(' + i + ')').css("border", "2px solid #8080C0");
			$('#LeftDataTable tr:eq(' + i + ')').css("border-right", "0px");
			$('#RightDataTable tr:eq(' + i + ')').css("border", "2px solid #8080C0");
			$('#RightDataTable tr:eq(' + i + ')').css("border-left", "0px");
		}
	}
}

function cfn_trSelectBackgroundColorObj(objNm, rowid) {

	$('#"+objNm+" tr').css("background-color", "");

	for (var i = 0; i < $('#"+objNm+" tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#"+objNm+" tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

function cfn_processMsg() {

}

function cfn_tabLocation(vUrl, v_tab_idx) {

	if (vUrl.indexOf("?") > -1) {
		vUrl = vUrl + "&tab_idx=" + v_tab_idx;
	} else {
		vUrl = vUrl + "?tab_idx=" + v_tab_idx;
	}

	window.location.href = vUrl;
}

/*******************************************************************************
 * div스크롤 Grid
 ******************************************************************************/
function cfn_scroll(x, y) {
	DataHeader.scrollLeft = x;
	// DataList.scrollTop = y;
}

function cfn_grid_scroll(x, y) {
	DataHeader.scrollLeft = x;
	DataList.scrollTop = y;
}

function cfn_grid_contentsPageResize(v_marginHeight) {

	var marginHeight = Number(v_marginHeight);

	var leftMenuWidth = 250;
	var widthMargin = 0;
	if (navigator.userAgent.toLowerCase().indexOf("msie") > 0) {
		widthMargin = 0;
	}

	$('#DataHeader').css('width', $(window).width() - (leftMenuWidth + widthMargin));
	$('#DataList').css('width', $(window).width() - (leftMenuWidth + widthMargin));
	$('#DataList').css('height', $(window).height() - marginHeight);

}

function cfn_grid_Resize_hidden(divTitle, divData, leftMenuWidth, marginHeight) {

	var marginHeight = Number(marginHeight);
	var leftMenuWidth = Number(leftMenuWidth);
	var widthMargin = 0;
	if (navigator.userAgent.toLowerCase().indexOf("msie") > 0) {
		widthMargin = 0;
	}

	var vFullWidth = $(window).width() - leftMenuWidth - 40;
	
	if($(window).width() < 740){
		vFullWidth = $(window).width()  - 40;
	}
	
	$('#' + divTitle).css('width',vFullWidth);
	$('#' + divData).css('width', vFullWidth);
	$('#' + divData).css('height', $(window).height() - marginHeight);

}

/*******************************************************************************
 * 첫째줄 값 일괄적용
 ******************************************************************************/
function cfn_setAllValue(vObjName, chkbYn) {
    var v_rowSeletedIdx = $("#rowSeletedIdx").val();
    
    var vObjValue = document.getElementsByName(vObjName)[v_rowSeletedIdx].value;
    var vObjElem = document.getElementsByName(vObjName)[v_rowSeletedIdx];
    var vObjText = vObjValue;
    if(vObjElem.type == "select-one"){
    	vObjText = vObjElem.options[vObjElem.selectedIndex].text;
   }
    
    if(confirm(v_rowSeletedIdx+"번째 행부터 ["+vObjText+"] 일괄 적용하시겠습니까?")) {
    	
    	var ilen = document.getElementsByName(vObjName).length;
    	if (ilen > 1) {
    		
    		for (i = v_rowSeletedIdx; i < ilen; i++) {
    			document.getElementsByName("chkb")[i].checked = true;
    			document.getElementsByName("saveFlag")[i].value = "save";

    			if(i > 1) {
    				document.getElementsByName(vObjName)[i].value = vObjValue;
    			}
    		}
    	}
    }
	

}

/*******************************************************************************
 * 첫째줄 값 일괄적용
 ******************************************************************************/
function cfn_setChkAllValue(vObjName, chkbYn) {
	
	
   var v_rowSeletedIdx = $("#rowSeletedIdx").val();
	    
    var vObjValue = document.getElementsByName(vObjName)[v_rowSeletedIdx].value;
    var vObjElem = document.getElementsByName(vObjName)[v_rowSeletedIdx];
    var vObjText = vObjValue;
    if(vObjValue != "Y"){
    	vObjText = "N"; 
    }
    
    if(confirm(v_rowSeletedIdx+"번째 행부터  "+vObjText+" 일괄 적용하시겠습니까?")) {
    	var ilen = document.getElementsByName(vObjName).length;
    	if (ilen > 1) {
    		var vObjValue = document.getElementsByName(vObjName)[v_rowSeletedIdx].value;
    		for (i = v_rowSeletedIdx; i < ilen; i++) {
    			document.getElementsByName("chkb")[i].checked = true;
    			document.getElementsByName("saveFlag")[i].value = "save";
    			if(i > 1) {
    				document.getElementsByName(vObjName)[i].value = vObjValue;
    				if (vObjValue != "") {
    					document.getElementsByName("chk_" + vObjName)[i].checked = true;
    				} else {
    					document.getElementsByName("chk_" + vObjName)[i].checked = false;
    				}

    			}

    		}
    	}
    }
	
	
	
}


/*******************************************************************************
 * 컬럼숨기기
 ******************************************************************************/
function cfn_columnHide() {

	var chkHideColumns = $("#hideColumns").val();
	var arrHideColumns = chkHideColumns.split("^");

	$('input:checkbox[name="chkHideColumns"]').each(function() {
		chkbName = $(this).val();

		if (chkHideColumns.indexOf(chkbName) > 0) {

			$("th[id^='" + chkbName + "']").hide();
			$("td[id^='" + chkbName + "']").hide();

			this.checked = false; // checked 처리
		} else {

			$("th[id^='" + chkbName + "']").show();
			$("td[id^='" + chkbName + "']").show();

			this.checked = true; // checked 처리
		}

	});

}

function cfn_setColumn(vProgNm) {

	var chkedVal = "";

	$('input:checkbox[name="chkHideColumns"]').each(function() {

		chkbName = $(this).val();

		if (this.checked == true) {

			$("th[id^='" + chkbName + "']").show();
			$("td[id^='" + chkbName + "']").show();

		} else {

			chkedVal += "^" + chkbName;

			$("th[id^='" + chkbName + "']").hide();
			$("td[id^='" + chkbName + "']").hide();
		}

	});

	$("#hideColumns").val(chkedVal);

	cfn_columnHide();
	$("#divHideColumns").hide();

	// DB에저장
	cfn_hiddenColsSave(vProgNm);
}

function cfn_columnHideAll(vProgNm) {

	var chkedVal = "";

	$('input:checkbox[name="chkHideColumns"]').each(function() {
		chkbName = $(this).val();

		$("th[id^='" + chkbName + "']").hide();
		$("td[id^='" + chkbName + "']").hide();

		this.checked = false; // checked 처리

		chkedVal += "^" + chkbName;

	});

	$("#hideColumns").val(chkedVal);

	cfn_columnHide();
	$("#divHideColumns").hide();

	// DB에저장
	cfn_hiddenColsSave(vProgNm);
}

function cfn_columnShowAll(vProgNm) {

	var chkedVal = "";

	$('input:checkbox[name="chkHideColumns"]').each(function() {
		chkbName = $(this).val();

		$("th[id^='" + chkbName + "']").show();
		$("td[id^='" + chkbName + "']").show();

		this.checked = true; // checked 처리

	});

	$("#hideColumns").val(chkedVal);

	cfn_columnHide();
	$("#divHideColumns").hide();

	// DB에저장
	cfn_hiddenColsSave(vProgNm);
}

function cfn_divHideColumnsView() {
	$("#divHideColumns").show();
}
function cfn_divHideColumnsClose() {
	$("#divHideColumns").hide();
}

function cfn_trSelectBackgroundColor(rowid) {

	$('#LeftDataTable tr').css("background-color", "");
	$('#RightDataTable tr').css("background-color", "");

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#LeftDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
			$('#RightDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

function cfn_trSelectBackgroundColorObj(objNm, rowid) {

	$('#"+objNm+" tr').css("background-color", "");

	for (var i = 0; i < $('#"+objNm+" tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#"+objNm+" tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

// DB에저장
function cfn_hiddenColsSave(vProgNm) {
	var frm = document.searchForm;

	frm.target = "trxnFrame";
	frm.action = "/commonDB/sysProgColsTrxn.do?progNm=" + vProgNm;
	frm.submit();

}

function cfn_trSelectBackgroundColorObj(objName, rowid) {

	$(objName + ' tr').css("background-color", "");

	for (var i = 0; i < $(objName + ' tr').length; i++) {
		if (i == (rowid - 1)) {
			$(objName + ' tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

// iframe resize
function cfn_autoResize(i) {
	var iframeHeight = (i).contentWindow.document.body.scrollHeight;
	(i).height = iframeHeight + 20;
}




function cfn_winPopup_del(vPopMode, vWidth, vHeight, vUrl, vPopNm) {

	var winHeight = top.$(window).height();
	var winWidth = top.$(window).width();

	// alert(winHeight+"/"+winWidth);

	var popWidth = 500;
	var popHeight = 500;

	var vTop = 100;
	var vLeft = 100;

	var popType = "winPop";

	if (vPopMode == "win_fix") {

		popWidth = vWidth;
		popHeight = vHeight;
	}
	if (vPopMode == "win_mrgn") {

		popWidth = $(window).width() - vWidth;
		popHeight = $(window).height() - vHeight;
	}
	if (vPopMode == "win_hfix") {

		popWidth = ($(window).width() - vWidth);
		popHeight = vHeight;
	}
	if (vPopMode == "win_wfix") {

		popWidth = vWidth;
		popHeight = $(window).height() - vHeight;
	}
	if (vPopMode == "layer_fix") {
		popWidth = vWidth;
		popHeight = vHeight;
	}
	if (vPopMode == "layer_mrgn") {

		popWidth = $(window).width() - vWidth;
		popHeight = $(window).height() - vHeight;
	}
	if (vPopMode == "layer_hfix") {
		popWidth = ($(window).width() - vWidth);
		popHeight = vHeight;
	}
	if (vPopMode == "layer_wfix") {
		popWidth = vWidth;
		popHeight = $(window).height() - vHeight;
	}

	vTop = ($(window).height() - popHeight) / 2;
	vLeft = ($(window).width() - popWidth) / 2;

	if (vPopMode.indexOf("win_") > -1) {

		window.open(vUrl, vPopNm, "top=" + vTop + ",left=" + vLeft + ", width=" + popWidth + ", height=" + popHeight + ",location=no, directories=no,resizable=yes,status=no,toolbar=no,menubar=no, scrollbars=yes");
	}
	if (vPopMode.indexOf("layer_") > -1) {
		
		alert(popWidth +"/"+popHeight);

		cfn_ModalOpenWindow(vUrl, vPopNm, popWidth, popHeight);
	}
}

function cfn_winPopMargin(vUrl, vWm, vHm) {

	var vH = $(window).height() - vHm;
	var vW = $(window).width() - vHm;

	var vTop = ($(window).height() - vH) / 2;
	var vLeft = ($(window).width() - vW) / 2;
	window.open(vUrl, "winPop", "top=" + vTop + ",left=" + vLeft + ", width=" + vW + ", height=" + vH + ",location=no, directories=no,resizable=yes,status=no,toolbar=no,menubar=no, scrollbars=yes");
}

function cfn_winPopNmMargin(vUrl, vPopNm, vWm, vHm) {

	var vH = $(window).height() - vHm;
	var vW = $(window).width() - vHm;

	var vTop = ($(window).height() - vH) / 2;
	var vLeft = ($(window).width() - vW) / 2;
	window.open(vUrl, vPopNm, "top=" + vTop + ",left=" + vLeft + ", width=" + vW + ", height=" + vH + ",location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes");
}

function cfn_winPopNmFix(vUrl, vPopNm, vW, vH) {

	var vTop = ($(window).height() - vH) / 2;
	var vLeft = ($(window).width() - vW) / 2;
	window.open(vUrl, vPopNm, "top=" + vTop + ",left=" + vLeft + ", width=" + vW + ", height=" + vH + ",location=no, directories=no,resizable=yes,status=no,toolbar=no,menubar=no, scrollbars=yes");
}

function cfn_srhTerm(vOpt) {

	document.getElementById('srhTermOpt').value = vOpt;

	var dt = new Date();
	var month = dt.getMonth() + 1;
	var day = dt.getDate();
	var year = dt.getFullYear();

	if (vOpt == "today") {

		document.getElementById('srhFrDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
		document.getElementById('srhToDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
	}

	if (vOpt == "thisWeek") {

		var myDate = new Date();
		var dayOfMonth = myDate.getDate();
		myDate.setDate(dayOfMonth - 7);

		var frYear = myDate.getFullYear();
		var frMonth = myDate.getMonth() + 1;
		var frDay = myDate.getDate();

		document.getElementById('srhFrDate').value = frYear + "-" + lpad(2, '0', frMonth) + "-" + lpad(2, '0', frDay);
		document.getElementById('srhToDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
	}

	if (vOpt == "thisMonth") {

		var myDateMonth = new Date();
		myDateMonth.setMonth(myDateMonth.getMonth() - 1);

		var frYear = myDateMonth.getFullYear();
		var frMonth = myDateMonth.getMonth() + 1;
		var frDay = myDateMonth.getDate();

		document.getElementById('srhFrDate').value = frYear + "-" + lpad(2, '0', frMonth) + "-" + lpad(2, '0', frDay);
		document.getElementById('srhToDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
	}
	if (vOpt == "threeMonth") {

		var myDateMonth = new Date();
		myDateMonth.setMonth(myDateMonth.getMonth() - 3);

		var frYear = myDateMonth.getFullYear();
		var frMonth = myDateMonth.getMonth() + 1;
		var frDay = myDateMonth.getDate();

		document.getElementById('srhFrDate').value = frYear + "-" + lpad(2, '0', frMonth) + "-" + lpad(2, '0', frDay);
		document.getElementById('srhToDate').value = year + "-" + lpad(2, '0', month) + "-" + lpad(2, '0', day);
	}
	if (vOpt == "") {
		document.getElementById('srhFrDate').value = "";
		document.getElementById('srhToDate').value = "";
	}
}

function cfn_setRadioValue(vObjNm, vVal) {
	$("#" + vObjNm).val(vVal);

	if (vObjNm == "lastLvlYn") {
		fn_propItemInput();
	}
}

function cfn_orderBy(columnName, ascDesc) {
	document.searchForm.srhOrderBy.value = columnName;
	document.searchForm.srhOrderAsc.value = ascDesc;
	fn_searchPage('1');

}

function cfn_selectPageNavNew(iTotalCnt, iPageRow, iCurrPage) {

	var iTotalCnt = Number(iTotalCnt);
	var iPageRow = Number(iPageRow);

	var iPageCnt = Math.ceil(iTotalCnt / iPageRow);
	var iRemainder = iTotalCnt % iPageRow;
	if (iTotalCnt > iPageCnt * iPageRow) {
		iPageCnt = iPageCnt + 1;
	}

	var iCurrPage = Number(iCurrPage);

	var src = "";
	src += "페이지 자료수: ";
	src += "<select id=\"sPageRow\" name=\"sPageRow\" onchange=\"fn_searchPage('1')\" style=\"height:22px;\">  ";
	src += "     <option value=\"10\">10</option> ";
	src += "     <option value=\"20\">20</option> ";
	src += "     <option value=\"30\">30</option> ";
	src += "     <option value=\"50\">50</option> ";
	src += "     <option value=\"100\">100</option> ";
	src += "     <option value=\"200\">200</option> ";
	src += "     <option value=\"500\">500</option> ";
	src += "     <option value=\"1000\">1000</option> ";
	src += "     <option value=\"5000\">5,000(느림)</option> ";
	src += "     <option value=\"10000\">10,000(느림)</option> ";
	src += " </select>  ";

	src += " &nbsp;&nbsp; 페이지: ";
	if (iCurrPage > 1) {
		src += "    <span><a onclick=\"fn_searchPage('" + (iCurrPage - 1) + "')\" style=\"cursor:pointer\"><img src=\"/img/ezwx/icon_prev.gif\" align=\"absmiddle\"></a></span> ";
	}

	src += "    <span><select name=\"sCurrPage\" id=\"sCurrPage\" onchange=\"fn_searchPage(this.value)\" style=\"border:2px solid #D31D2F;height:22px;\"> ";
	for (i = 1; i <= iPageCnt; i++) {

		if (iCurrPage == i) {
			src += "    <option value=\"" + i + "\" selected> " + i + " of " + iPageCnt + " </option>";
		} else {
			src += "    <option value=\"" + i + "\" > " + i + " of " + iPageCnt + " </option>";
		}
	}
	src += "    </select></span>  ";
	src += " ";
	if (iCurrPage < iPageCnt) {
		src += "   <a onclick=\"fn_searchPage('" + (iCurrPage + 1) + "')\" style=\"cursor:pointer\"><img src=\"/img/ezwx/icon_next.gif\" align=\"absmiddle\"></a>  ";
	}

	$("#td_pageNav").html(src);
	$("#sPageRow").val(iPageRow);

}

function cfn_selectPageNavCmm(iTotalCnt, iPageRow, iCurrPage) {

	var iTotalCnt = Number(iTotalCnt);
	var iPageRow = Number(iPageRow);

	var iPageCnt = Math.ceil(iTotalCnt / iPageRow);
	var iRemainder = iTotalCnt % iPageRow;
	if (iTotalCnt > iPageCnt * iPageRow) {
		iPageCnt = iPageCnt + 1;
	}

	var iCurrPage = Number(iCurrPage);

	var src = "";
	src += "페이지 자료수: ";
	src += "<select id=\"pageRows\" name=\"pageRows\" onchange=\"fn_searchPage('1')\" style=\"height:22px;\">  ";
	src += "     <option value=\"10\">10</option> ";
	src += "     <option value=\"20\">20</option> ";
	src += "     <option value=\"30\">30</option> ";
	src += "     <option value=\"50\">50</option> ";
	src += "     <option value=\"100\">100</option> ";
	src += "     <option value=\"200\">200</option> ";
	src += "     <option value=\"500\">500</option> ";
	src += "     <option value=\"1000\">1000</option> ";
	src += "     <option value=\"5000\">5,000(느림)</option> ";
	src += "     <option value=\"10000\">10,000(느림)</option> ";
	src += " </select>  ";

	src += " &nbsp;&nbsp; 페이지: ";
	if (iCurrPage > 1) {
		src += "    <span><a onclick=\"fn_searchPage('" + (iCurrPage - 1) + "')\" style=\"cursor:pointer\"><img src=\"/img/ezwx/icon_prev.gif\" align=\"absmiddle\"></a></span> ";
	}

	src += "    <span><select name=\"currPage\" id=\"currPage\" onchange=\"fn_searchPage(this.value)\" style=\"border:2px solid #D31D2F;height:22px;\"> ";
	for (i = 1; i <= iPageCnt; i++) {

		if (iCurrPage == i) {
			src += "    <option value=\"" + i + "\" selected> " + i + " of " + iPageCnt + " </option>";
		} else {
			src += "    <option value=\"" + i + "\" > " + i + " of " + iPageCnt + " </option>";
		}
	}
	src += "    </select></span>  ";
	src += " ";
	if (iCurrPage < iPageCnt) {
		src += "   <a onclick=\"fn_searchPage('" + (iCurrPage + 1) + "')\" style=\"cursor:pointer\"><img src=\"/img/ezwx/icon_next.gif\" align=\"absmiddle\"></a>  ";
	}
	var startRows = (iCurrPage - 1 ) * iPageRow + 1;
	src += "<input type=\"hidden\" name=\"startRows\" value=\""+startRows+"\" >";

	$("#td_pageNav").html(src);
	$("#pageRows").val(iPageRow);

}

function cfn_selectPageNavNevi2(iTotalCnt, iPageRow, iCurrPage) {

	var iTotalCnt = Number(iTotalCnt);
	var iPageRow = Number(iPageRow);

	var iPageCnt = Math.ceil(iTotalCnt / iPageRow);
	var iRemainder = iTotalCnt % iPageRow;
	if (iTotalCnt > iPageCnt * iPageRow) {
		iPageCnt = iPageCnt + 1;
	}

	var iCurrPage = Number(iCurrPage);

	var src = "";
	src += "<span style=\"color:red;\">전체 : "+cfnFmtMoney(iTotalCnt)+" 개</span> ";

	src += " &nbsp;&nbsp; 페이지: ";
	if (iCurrPage > 1) {
		src += "    <span><a onclick=\"fn_searchPage('" + (iCurrPage - 1) + "')\" style=\"cursor:pointer\"><img src=\"/img/ezwx/icon_prev.gif\" align=\"absmiddle\"></a></span> ";
	}

	src += "    <span><select name=\"sCurrPage\" id=\"sCurrPage\" onchange=\"fn_searchPage(this.value)\"  class=\"input9e0\" style=\"height:22px;\"> ";
	for (i = 1; i <= iPageCnt; i++) {

		if (iCurrPage == i) {
			src += "    <option value=\"" + i + "\" selected> " + i + " of " + iPageCnt + " </option>";
		} else {
			src += "    <option value=\"" + i + "\" > " + i + " of " + iPageCnt + " </option>";
		}
	}
	src += "    </select></span>  ";
	src += " ";
	if (iCurrPage < iPageCnt) {
		src += "   <a onclick=\"fn_searchPage('" + (iCurrPage + 1) + "')\" style=\"cursor:pointer\"><img src=\"/img/ezwx/icon_next.gif\" align=\"absmiddle\"></a>  ";
	}
	src += " <select id=\"sPageRow\" name=\"sPageRow\" onchange=\"fn_searchPage('1')\" class=\"input9e0\" style=\"height:22px;\">  ";
	src += "     <option value=\"10\">10 개씩 보이기</option> ";
	src += "     <option value=\"20\">20 개씩 보이기</option> ";
	src += "     <option value=\"30\">30 개씩 보이기</option> ";
	src += "     <option value=\"50\">50 개씩 보이기</option> ";
	src += "     <option value=\"100\">100 개씩 보이기</option> ";
	src += "     <option value=\"200\">200 개씩 보이기</option> ";
	src += "     <option value=\"500\">500 개씩 보이기</option> ";
	src += "     <option value=\"1000\">1,000 개씩 보이기</option> ";
	src += "     <option value=\"5000\">5,000(느림)</option> ";
	src += "     <option value=\"10000\">10,000(느림)</option> ";
	src += " </select>  ";

	$("#td_pageNav").html(src);
	$("#sPageRow").val(iPageRow);

}

function cfn_processMsg() {

	var processTop = ($(window).height() - 100) / 2;
	var processLeft = ($(window).width() - 500) / 2;
	document.getElementById("processMsg").style.top = processTop + "px";
	document.getElementById("processMsg").style.left = processLeft + "px";
	document.getElementById("processMsg").style.display = "";

}

function cfn_setRadioValue(vObjNm, vVal) {
	$("#" + vObjNm).val(vVal);

	if (vObjNm == "lastLvlYn") {
		fn_propItemInput();
	}
}


function cfn_trSelectBackgroundColor(rowid) {

	$('#LeftDataTable tr').css("background-color", "");
	$('#RightDataTable tr').css("background-color", "");

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#LeftDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
			$('#RightDataTable tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

function cfn_trSelectBackgroundColor(leftObjNm, rightObjNm, rowid) {

	$('#' + leftObjNm + ' tr').css("background-color", "");
	$('#' + rightObjNm + ' tr').css("background-color", "");

	for (var i = 0; i < $('#' + leftObjNm + ' tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#' + leftObjNm + ' tr:eq(' + i + ')').css("background-color", "#D1EBFE");
			$('#' + rightObjNm + ' tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

function cfn_trSelectBorderChkb_del(rowid) {

	$('#LeftDataTable tr').css("border", "");
	$('#RightDataTable tr').css("border", "");

	for (var i = 0; i < $('#LeftDataTable tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#LeftDataTable tr:eq(' + i + ')').css("border", "2px solid #8080C0");
			$('#LeftDataTable tr:eq(' + i + ')').css("border-right", "0px");
			$('#RightDataTable tr:eq(' + i + ')').css("border", "2px solid #8080C0");
			$('#RightDataTable tr:eq(' + i + ')').css("border-left", "0px");
		}
	}
}

function cfn_trSelectBorderChkb_del(leftObj, rightObj, rowid) {

	$('#' + leftObj + ' tr').css("border", "");
	$('#' + rightObj + ' tr').css("border", "");

	for (var i = 0; i < $('#' + leftObj + ' tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#' + leftObj + ' tr:eq(' + i + ')').css("border", "2px solid #8080C0");
			$('#' + leftObj + ' tr:eq(' + i + ')').css("border-right", "0px");
			$('#' + rightObj + ' tr:eq(' + i + ')').css("border", "2px solid #8080C0");
			$('#' + rightObj + ' tr:eq(' + i + ')').css("border-left", "0px");
		}
	}
}
function cfn_trSelectBorderChkb_del(leftObj, rowid) {

	$('#' + leftObj + ' tr').css("border", "");

	for (var i = 0; i < $('#' + leftObj + ' tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#' + leftObj + ' tr:eq(' + i + ')').css("border", "2px solid #8080C0");
			$('#' + leftObj + ' tr:eq(' + i + ')').css("border-right", "0px");
		}
	}
}

function cfn_trSelectBackgroundColorObj(objNm, rowid) {

	$('#"+objNm+" tr').css("background-color", "");

	for (var i = 0; i < $('#"+objNm+" tr').length; i++) {
		if (i == (rowid - 1)) {
			$('#"+objNm+" tr:eq(' + i + ')').css("background-color", "#D1EBFE");
		}
	}
}

function cfn_processMsg() {

}

function fn_processMsg() {

}

function cfn_tabLocation(vUrl, v_tab_idx) {

	if (vUrl.indexOf("?") > -1) {
		vUrl = vUrl + "&tab_idx=" + v_tab_idx;
	} else {
		vUrl = vUrl + "?tab_idx=" + v_tab_idx;
	}

	window.location.href = vUrl;
}

/*******************************************************************************
 * div스크롤 Grid
 ******************************************************************************/
function cfn_grid_scroll(x, y) {
	DataHeader.scrollLeft = x;
	DataList.scrollTop = y;
}

function cfn_grid_resize(leftMenuWidth, marginHeight) {

	var marginHeight = Number(marginHeight);
	var leftMenuWidth = Number(leftMenuWidth);

	var widthMargin = 0;
	if (navigator.userAgent.toLowerCase().indexOf("msie") > 0) {
		widthMargin = 5;
	}
	
	var vFullWidth = $(window).width() -270;
	
	if($(window).width() < 860){
		vFullWidth = $(window).width()  - 30;
	}

	$('#DataHeader').css('width', vFullWidth);
	$('#DataList').css('width',vFullWidth);
	$('#DataList').css('height', $(window).height() - marginHeight);

}

/*******************************************************************************
 * div스크롤 SplitGrid
 ******************************************************************************/
function cfn_splitGrid_scrollRight(x, y) {
	divRightTitle.scrollLeft = x;
	divLeftData.scrollTop = y;
}

function cfn_splitGrid_scrollLeft(x, y) {
	divLeftTitle.scrollLeft = x;
	divRightData.scrollTop = y;
}

function cfn_splitGrid_resize(v_leftMenuWidth, v_divLeftWidth, v_gridTopMargin) {
	
	
	var gridHeight = $(window).height() - Number(v_gridTopMargin);
	var gridRightWidth = $(window).width() - Number(v_divLeftWidth) - 270;
	
	$("#divLeftData").css("height",gridHeight +"px");
	$("#divRightData").css("height",gridHeight +"px");
	$("#divRightTitle").css("width",gridRightWidth +"px");
	$("#divRightData").css("width",gridRightWidth +"px");

	// var widthMargin = 5;
	// if (navigator.userAgent.toLowerCase().indexOf("msie") > 0) {
	// widthMargin = 0;
	// }

	// var vFullWidth = $(window).width() - leftMenuWidth - 20;

	// $('#divRightTitle').css('width', vFullWidth - marginSplitLeft);
	// $('#divRightData').css('width', vFullWidth - marginSplitLeft);
	// $('#divRightData').css('height', $(window).height() - marginHeight);
	// $('#divLeftData').css('height', $(window).height() - marginHeight);

}

function cfn_splitGrid_resize_v3(v_leftMenuWidth, v_marginSplitLeft, v_marginHeight) {
	var leftMenuWidth = 305;
	var marginHeight = Number(v_marginHeight);
	var marginSplitLeft = Number(v_marginSplitLeft);

	var widthMargin = 5;
	if (navigator.userAgent.toLowerCase().indexOf("msie") > 0) {
		widthMargin = 0;
	}

	var vFullWidth = $(window).width() - leftMenuWidth - 44;

	$('#divRightTitle').css('width', vFullWidth - marginSplitLeft);
	$('#divRightData').css('width', vFullWidth - marginSplitLeft);
	$('#divRightData').css('height', $(window).height() - marginHeight);
	$('#divLeftData').css('height', $(window).height() - marginHeight);

}

/*******************************************************************************
 * cfn_sort
 ******************************************************************************/
function cfn_sort(sortNm, sortCols, sortAsc) {

	var old_sortNm = $("#srhOrderNm").val();

	$("#sort_" + old_sortNm).html("▽")
	$("#sort_" + old_sortNm).css("color", "#b0b0b0");
	$("#sort_" + sortNm).css("color", "#202020");

	if (old_sortNm != sortNm) {
		document.searchForm.srhOrderNm.value = sortNm;
		document.searchForm.srhOrderBy.value = sortCols;
		document.searchForm.srhOrderAsc.value = sortAsc;

		if (sortAsc == "asc") {
			$("#sort_" + sortNm).html("▲")
		}
		if (sortAsc == "desc") {
			$("#sort_" + sortNm).html("▼");
		}
	} else {
		if (document.searchForm.srhOrderAsc.value == "desc") {
			document.searchForm.srhOrderAsc.value = "asc";
			$("#sort_" + sortNm).html("▲");
		} else {
			document.searchForm.srhOrderAsc.value = "desc";
			$("#sort_" + sortNm).html("▼");
		}
	}
	fn_search();
}

/*******************************************************************************
 * cfn_sort2
 ******************************************************************************/
function cfn_sort2(sortNm, sortColsAsc, sortColsDesc, sortAsc) {

	var old_sortNm = $("#srhOrderNm").val();

	$("#sort_" + old_sortNm).html("▽")
	$("#sort_" + old_sortNm).css("color", "#b0b0b0");
	$("#sort_" + sortNm).css("color", "#202020");

	if (old_sortNm != sortNm) {
		document.searchForm.srhOrderNm.value = sortNm;
		document.searchForm.srhOrderAsc.value = sortAsc;

		if (sortAsc == "asc") {
			$("#sort_" + sortNm).html("▲")
			document.searchForm.srhOrderBy.value = sortColsAsc;
		}
		if (sortAsc == "desc") {
			$("#sort_" + sortNm).html("▼");
			document.searchForm.srhOrderBy.value = sortColsDesc;
		}
	} else {
		if (document.searchForm.srhOrderAsc.value == "desc") {
			document.searchForm.srhOrderAsc.value = "asc";
			document.searchForm.srhOrderBy.value = sortColsAsc;
			$("#sort_" + sortNm).html("▲");
		} else {
			document.searchForm.srhOrderAsc.value = "desc";
			document.searchForm.srhOrderBy.value = sortColsDesc;
			$("#sort_" + sortNm).html("▼");
		}
	}
	fn_search();
}

/*******************************************************************************
 * cfn_chkb_obj
 ******************************************************************************/
function cfn_chkb_obj(objNm, objVal, isChecked) {
	if (isChecked == true) {
		document.getElementById(objNm).value = objVal;
	} else {
		document.getElementById(objNm).value = "";
	}
}

function cfn_chkb_listObj(objNm, idx, objVal, isChecked) {
	$("#rowSeletedIdx").val(idx);
	if (isChecked == true) {
		document.getElementsByName(objNm)[idx].value = objVal;
	} else {
		if(objVal == "Y"){
			document.getElementsByName(objNm)[idx].value = "N";
		}else{
			document.getElementsByName(objNm)[idx].value = "";
		}
		
	}
}

function cfn_isCheked(chkVal, objVal) {
	var rtnVal = "";
	if (chkVal == objVal) {
		rtnVal = "checked";
	}
	return rtnVal;
}

/*******************************************************************************
 * cfn_split_trDown & trUp
 ******************************************************************************/
function cfn_split_trDown(objLeftTblNm, objRightTblNm, objLeftCols, objRightCols) {

	var selRow = "";
	var nextRow = "";

	var rowCnt = document.getElementsByName("chkb").length;

	var chkLast = rowCnt;

	$('#' + objLeftTblNm + ' tr').css("border", "");
	$('#' + objRightTblNm + ' tr').css("border", "");

	for (i = (rowCnt - 1); i > 0; i--) {

		if (document.getElementsByName("chkb")[i].checked == true) {
			var selIdx = i;
			var nextIdx = i + 1;

			if (chkLast == rowCnt) {
				chkLast = nextIdx;
			}

			if (chkLast <= rowCnt) {

				var sel_chkb = document.getElementsByName("chkb")[selIdx].checked;
				var sel_saveFlag = document.getElementsByName("saveFlag")[selIdx].value;

				var next_chkb = document.getElementsByName("chkb")[nextIdx].checked;
				var next_saveFlag = document.getElementsByName("saveFlag")[nextIdx].value;

				document.getElementsByName("chkb")[nextIdx].checked = sel_chkb;
				document.getElementsByName("saveFlag")[nextIdx].value = sel_saveFlag;

				document.getElementsByName("chkb")[selIdx].checked = next_chkb;
				document.getElementsByName("saveFlag")[selIdx].value = next_saveFlag;

				var arrObjLeftCols = objLeftCols.split("^");

				if (arrObjLeftCols.length > 0) {

					for (j = 0; j < arrObjLeftCols.length; j++) {

						if (document.getElementsByName(arrObjLeftCols[j])[nextIdx] != null) {

							if (arrObjLeftCols[j].indexOf("chk_") > -1) {
								var sel_chkNm = document.getElementsByName(arrObjLeftCols[j])[selIdx].checked;
								var next_chkNm = document.getElementsByName(arrObjLeftCols[j])[nextIdx].checked;

								document.getElementsByName(arrObjLeftCols[j])[nextIdx].checked = sel_chkNm;
								document.getElementsByName(arrObjLeftCols[j])[selIdx].checked = next_chkNm;

							} else {
								var sel_objNm = document.getElementsByName(arrObjLeftCols[j])[selIdx].value;
								var next_objNm = document.getElementsByName(arrObjLeftCols[j])[nextIdx].value;

								document.getElementsByName(arrObjLeftCols[j])[nextIdx].value = sel_objNm;
								document.getElementsByName(arrObjLeftCols[j])[selIdx].value = next_objNm;
							}

						}

					}
				}

				var arrObjRightCols = objRightCols.split("^");

				if (arrObjRightCols.length > 0) {

					for (j = 0; j < arrObjRightCols.length; j++) {

						if (document.getElementsByName(arrObjRightCols[j])[nextIdx] != null) {

							if (arrObjRightCols[j].indexOf("chk_") > -1) {
								var sel_chkNm = document.getElementsByName(arrObjRightCols[j])[selIdx].checked;
								var next_chkNm = document.getElementsByName(arrObjRightCols[j])[nextIdx].checked;

								document.getElementsByName(arrObjRightCols[j])[nextIdx].checked = sel_chkNm;
								document.getElementsByName(arrObjRightCols[j])[selIdx].checked = next_chkNm;

							} else {
								var sel_objNm = document.getElementsByName(arrObjRightCols[j])[selIdx].value;
								var next_objNm = document.getElementsByName(arrObjRightCols[j])[nextIdx].value;

								document.getElementsByName(arrObjRightCols[j])[nextIdx].value = sel_objNm;
								document.getElementsByName(arrObjRightCols[j])[selIdx].value = next_objNm;
							}
						}

					}
				}
			}
		}

	}

	for (i = 0; i < rowCnt; i++) {
		if (document.getElementsByName("chkb")[i].checked == true) {

			$('#' + objLeftTblNm + ' tr:eq(' + (i - 1) + ')').css("border", "2px solid #8080C0");
			$('#' + objLeftTblNm + ' tr:eq(' + (i - 1) + ')').css("border-right", "0px");
			$('#' + objRightTblNm + ' tr:eq(' + (i - 1) + ')').css("border", "2px solid #8080C0");
			$('#' + objRightTblNm + ' tr:eq(' + (i - 1) + ')').css("border-left", "0px");
		}
	}

}

function cfn_split_trUp(objLeftTblNm, objRightTblNm, objLeftCols, objRightCols) {

	var selRow = "";
	var nextRow = "";

	var rowCnt = document.getElementsByName("chkb").length;

	var chkFirst = -1;

	$('#' + objLeftTblNm + ' tr').css("border", "");
	$('#' + objRightTblNm + ' tr').css("border", "");

	for (i = 1; i < rowCnt; i++) {

		if (document.getElementsByName("chkb")[i].checked == true) {

			var selIdx = i;
			var nextIdx = i - 1;

			if (chkFirst == -1) {
				chkFirst = nextIdx;
			}

			if (chkFirst > 0) {

				var sel_chkb = document.getElementsByName("chkb")[selIdx].checked;
				var sel_saveFlag = document.getElementsByName("saveFlag")[selIdx].value;

				var next_chkb = document.getElementsByName("chkb")[nextIdx].checked;
				var next_saveFlag = document.getElementsByName("saveFlag")[nextIdx].value;

				document.getElementsByName("chkb")[nextIdx].checked = sel_chkb;
				document.getElementsByName("saveFlag")[nextIdx].value = sel_saveFlag;

				document.getElementsByName("chkb")[selIdx].checked = next_chkb;
				document.getElementsByName("saveFlag")[selIdx].value = next_saveFlag;

				var arrObjLeftCols = objLeftCols.split("^");

				if (arrObjLeftCols.length > 0) {

					for (j = 0; j < arrObjLeftCols.length; j++) {

						if (document.getElementsByName(arrObjLeftCols[j])[nextIdx] != null) {

							if (arrObjLeftCols[j].indexOf("chk_") > -1) {
								var sel_chkNm = document.getElementsByName(arrObjLeftCols[j])[selIdx].checked;
								var next_chkNm = document.getElementsByName(arrObjLeftCols[j])[nextIdx].checked;

								document.getElementsByName(arrObjLeftCols[j])[nextIdx].checked = sel_chkNm;
								document.getElementsByName(arrObjLeftCols[j])[selIdx].checked = next_chkNm;

							} else {
								var sel_objNm = document.getElementsByName(arrObjLeftCols[j])[selIdx].value;
								var next_objNm = document.getElementsByName(arrObjLeftCols[j])[nextIdx].value;

								document.getElementsByName(arrObjLeftCols[j])[nextIdx].value = sel_objNm;
								document.getElementsByName(arrObjLeftCols[j])[selIdx].value = next_objNm;
							}

						}

					}
				}

				var arrObjRightCols = objRightCols.split("^");

				if (arrObjRightCols.length > 0) {

					for (j = 0; j < arrObjRightCols.length; j++) {

						if (document.getElementsByName(arrObjRightCols[j])[nextIdx] != null) {

							if (arrObjRightCols[j].indexOf("chk_") > -1) {
								var sel_chkNm = document.getElementsByName(arrObjRightCols[j])[selIdx].checked;
								var next_chkNm = document.getElementsByName(arrObjRightCols[j])[nextIdx].checked;

								document.getElementsByName(arrObjRightCols[j])[nextIdx].checked = sel_chkNm;
								document.getElementsByName(arrObjRightCols[j])[selIdx].checked = next_chkNm;

							} else {
								var sel_objNm = document.getElementsByName(arrObjRightCols[j])[selIdx].value;
								var next_objNm = document.getElementsByName(arrObjRightCols[j])[nextIdx].value;

								document.getElementsByName(arrObjRightCols[j])[nextIdx].value = sel_objNm;
								document.getElementsByName(arrObjRightCols[j])[selIdx].value = next_objNm;
							}
						}

					}
				}

			}

		}

	}

	for (i = 0; i < rowCnt; i++) {
		if (document.getElementsByName("chkb")[i].checked == true) {

			$('#' + objLeftTblNm + ' tr:eq(' + (i - 1) + ')').css("border", "2px solid #8080C0");
			$('#' + objLeftTblNm + ' tr:eq(' + (i - 1) + ')').css("border-right", "0px");
			$('#' + objRightTblNm + ' tr:eq(' + (i - 1) + ')').css("border", "2px solid #8080C0");
			$('#' + objRightTblNm + ' tr:eq(' + (i - 1) + ')').css("border-left", "0px");
		}
	}

}

/*******************************************************************************
 * cfn_list_trDown & trUp
 ******************************************************************************/
function cfn_list_trDown(objLeftTblNm, objLeftCols) {

	var selRow = "";
	var nextRow = "";

	var rowCnt = document.getElementsByName("chkb").length;

	var chkLast = rowCnt;

	$('#' + objLeftTblNm + ' tr').css("border", "");

	for (i = (rowCnt - 1); i > 0; i--) {

		if (document.getElementsByName("chkb")[i].checked == true) {
			var selIdx = i;
			var nextIdx = i + 1;

			if (chkLast == rowCnt) {
				chkLast = nextIdx;
			}

			if (chkLast <= rowCnt) {

				var sel_chkb = document.getElementsByName("chkb")[selIdx].checked;
				var sel_saveFlag = document.getElementsByName("saveFlag")[selIdx].value;

				var next_chkb = document.getElementsByName("chkb")[nextIdx].checked;
				var next_saveFlag = document.getElementsByName("saveFlag")[nextIdx].value;

				document.getElementsByName("chkb")[nextIdx].checked = sel_chkb;
				document.getElementsByName("saveFlag")[nextIdx].value = sel_saveFlag;

				document.getElementsByName("chkb")[selIdx].checked = next_chkb;
				document.getElementsByName("saveFlag")[selIdx].value = next_saveFlag;

				var arrObjLeftCols = objLeftCols.split("^");

				if (arrObjLeftCols.length > 0) {

					for (j = 0; j < arrObjLeftCols.length; j++) {

						if (document.getElementsByName(arrObjLeftCols[j])[nextIdx] != null) {

							if (arrObjLeftCols[j].indexOf("chk_") > -1) {
								var sel_chkNm = document.getElementsByName(arrObjLeftCols[j])[selIdx].checked;
								var next_chkNm = document.getElementsByName(arrObjLeftCols[j])[nextIdx].checked;

								document.getElementsByName(arrObjLeftCols[j])[nextIdx].checked = sel_chkNm;
								document.getElementsByName(arrObjLeftCols[j])[selIdx].checked = next_chkNm;

							} else {
								var sel_objNm = document.getElementsByName(arrObjLeftCols[j])[selIdx].value;
								var next_objNm = document.getElementsByName(arrObjLeftCols[j])[nextIdx].value;

								document.getElementsByName(arrObjLeftCols[j])[nextIdx].value = sel_objNm;
								document.getElementsByName(arrObjLeftCols[j])[selIdx].value = next_objNm;
							}

						}

					}
				}
			}
		}

	}

	for (i = 0; i < rowCnt; i++) {
		if (document.getElementsByName("chkb")[i].checked == true) {

			$('#' + objLeftTblNm + ' tr:eq(' + (i - 1) + ')').css("border", "2px solid #8080C0");
		}
	}

}

function cfn_list_trUp(objLeftTblNm, objLeftCols) {

	var selRow = "";
	var nextRow = "";

	var rowCnt = document.getElementsByName("chkb").length;

	var chkFirst = -1;

	$('#' + objLeftTblNm + ' tr').css("border", "");

	for (i = 1; i < rowCnt; i++) {

		if (document.getElementsByName("chkb")[i].checked == true) {

			var selIdx = i;
			var nextIdx = i - 1;

			if (chkFirst == -1) {
				chkFirst = nextIdx;
			}

			if (chkFirst > 0) {

				var sel_chkb = document.getElementsByName("chkb")[selIdx].checked;
				var sel_saveFlag = document.getElementsByName("saveFlag")[selIdx].value;

				var next_chkb = document.getElementsByName("chkb")[nextIdx].checked;
				var next_saveFlag = document.getElementsByName("saveFlag")[nextIdx].value;

				document.getElementsByName("chkb")[nextIdx].checked = sel_chkb;
				document.getElementsByName("saveFlag")[nextIdx].value = sel_saveFlag;

				document.getElementsByName("chkb")[selIdx].checked = next_chkb;
				document.getElementsByName("saveFlag")[selIdx].value = next_saveFlag;

				var arrObjLeftCols = objLeftCols.split("^");

				if (arrObjLeftCols.length > 0) {

					for (j = 0; j < arrObjLeftCols.length; j++) {

						if (document.getElementsByName(arrObjLeftCols[j])[nextIdx] != null) {

							if (arrObjLeftCols[j].indexOf("chk_") > -1) {
								var sel_chkNm = document.getElementsByName(arrObjLeftCols[j])[selIdx].checked;
								var next_chkNm = document.getElementsByName(arrObjLeftCols[j])[nextIdx].checked;

								document.getElementsByName(arrObjLeftCols[j])[nextIdx].checked = sel_chkNm;
								document.getElementsByName(arrObjLeftCols[j])[selIdx].checked = next_chkNm;

							} else {
								var sel_objNm = document.getElementsByName(arrObjLeftCols[j])[selIdx].value;
								var next_objNm = document.getElementsByName(arrObjLeftCols[j])[nextIdx].value;

								document.getElementsByName(arrObjLeftCols[j])[nextIdx].value = sel_objNm;
								document.getElementsByName(arrObjLeftCols[j])[selIdx].value = next_objNm;
							}

						}

					}
				}
			}
		}
	}

	for (i = 0; i < rowCnt; i++) {
		if (document.getElementsByName("chkb")[i].checked == true) {

			$('#' + objLeftTblNm + ' tr:eq(' + (i - 1) + ')').css("border", "2px solid #8080C0");
		}
	}

}
/*******************************************************************************
 * cfn_list_trDown & trUp
 ******************************************************************************/
function cfn_list_trDown_idx(objTblNm, objCols, objIdx) {

	var selRow = "";
	var nextRow = "";

	var rowCnt = document.getElementsByName("chkb_" + objIdx).length;

	var chkLast = rowCnt;

	$('#' + objTblNm + ' tr').css("border", "");

	for (i = (rowCnt - 1); i > 0; i--) {

		if (document.getElementsByName("chkb_" + objIdx)[i].checked == true) {
			var selIdx = i;
			var nextIdx = i + 1;

			if (chkLast == rowCnt) {
				chkLast = nextIdx;
			}

			if (chkLast <= rowCnt) {

				var sel_chkb = document.getElementsByName("chkb_" + objIdx)[selIdx].checked;
				var sel_saveFlag = document.getElementsByName("saveFlag_" + objIdx)[selIdx].value;

				var next_chkb = document.getElementsByName("chkb_" + objIdx)[nextIdx].checked;
				var next_saveFlag = document.getElementsByName("saveFlag_" + objIdx)[nextIdx].value;

				document.getElementsByName("chkb_" + objIdx)[nextIdx].checked = sel_chkb;
				document.getElementsByName("saveFlag_" + objIdx)[nextIdx].value = sel_saveFlag;

				document.getElementsByName("chkb_" + objIdx)[selIdx].checked = next_chkb;
				document.getElementsByName("saveFlag_" + objIdx)[selIdx].value = next_saveFlag;

				var arrobjCols = objCols.split("^");

				if (arrobjCols.length > 0) {

					for (j = 0; j < arrobjCols.length; j++) {

						if (document.getElementsByName(arrobjCols[j])[nextIdx] != null) {

							if (arrobjCols[j].indexOf("chk_") > -1) {
								var sel_chkNm = document.getElementsByName(arrobjCols[j])[selIdx].checked;
								var next_chkNm = document.getElementsByName(arrobjCols[j])[nextIdx].checked;

								document.getElementsByName(arrobjCols[j])[nextIdx].checked = sel_chkNm;
								document.getElementsByName(arrobjCols[j])[selIdx].checked = next_chkNm;

							} else {
								var sel_objNm = document.getElementsByName(arrobjCols[j])[selIdx].value;
								var next_objNm = document.getElementsByName(arrobjCols[j])[nextIdx].value;

								document.getElementsByName(arrobjCols[j])[nextIdx].value = sel_objNm;
								document.getElementsByName(arrobjCols[j])[selIdx].value = next_objNm;
							}

						}

					}
				}
			}
		}

	}

	for (i = 0; i < rowCnt; i++) {
		if (document.getElementsByName("chkb_" + objIdx)[i].checked == true) {

			$('#' + objTblNm + ' tr:eq(' + (i - 1) + ')').css("border", "2px solid #8080C0");
		}
	}

}

function cfn_list_trUp_idx(objTblNm, objCols, objIdx) {

	var selRow = "";
	var nextRow = "";

	var rowCnt = document.getElementsByName("chkb_" + objIdx).length;

	var chkFirst = -1;

	$('#' + objTblNm + ' tr').css("border", "");

	for (i = 1; i < rowCnt; i++) {

		if (document.getElementsByName("chkb_" + objIdx)[i].checked == true) {

			var selIdx = i;
			var nextIdx = i - 1;

			if (chkFirst == -1) {
				chkFirst = nextIdx;
			}

			if (chkFirst > 0) {

				var sel_chkb = document.getElementsByName("chkb_" + objIdx)[selIdx].checked;
				var sel_saveFlag = document.getElementsByName("saveFlag_" + objIdx)[selIdx].value;

				var next_chkb = document.getElementsByName("chkb_" + objIdx)[nextIdx].checked;
				var next_saveFlag = document.getElementsByName("saveFlag_" + objIdx)[nextIdx].value;

				document.getElementsByName("chkb_" + objIdx)[nextIdx].checked = sel_chkb;
				document.getElementsByName("saveFlag_" + objIdx)[nextIdx].value = sel_saveFlag;

				document.getElementsByName("chkb_" + objIdx)[selIdx].checked = next_chkb;
				document.getElementsByName("saveFlag_" + objIdx)[selIdx].value = next_saveFlag;

				var arrobjCols = objCols.split("^");

				if (arrobjCols.length > 0) {

					for (j = 0; j < arrobjCols.length; j++) {

						if (document.getElementsByName(arrobjCols[j])[nextIdx] != null) {

							if (arrobjCols[j].indexOf("chk_") > -1) {
								var sel_chkNm = document.getElementsByName(arrobjCols[j])[selIdx].checked;
								var next_chkNm = document.getElementsByName(arrobjCols[j])[nextIdx].checked;

								document.getElementsByName(arrobjCols[j])[nextIdx].checked = sel_chkNm;
								document.getElementsByName(arrobjCols[j])[selIdx].checked = next_chkNm;

							} else {
								var sel_objNm = document.getElementsByName(arrobjCols[j])[selIdx].value;
								var next_objNm = document.getElementsByName(arrobjCols[j])[nextIdx].value;

								document.getElementsByName(arrobjCols[j])[nextIdx].value = sel_objNm;
								document.getElementsByName(arrobjCols[j])[selIdx].value = next_objNm;
							}

						}

					}
				}
			}
		}
	}

	for (i = 0; i < rowCnt; i++) {
		if (document.getElementsByName("chkb_" + objIdx)[i].checked == true) {

			$('#' + objTblNm + ' tr:eq(' + (i - 1) + ')').css("border", "2px solid #8080C0");
		}
	}

}

/*******************************************************************************
 * cfn_nextFocus
 ******************************************************************************/
function cfn_nextFocus(objName, idx) {

	var objLen = document.getElementsByName(objName).length;

	if ((event.keyCode == "13") || (event.keyCode == "40")) {

		if (idx < objLen) {
			idx = Number(idx) + 1;
			document.getElementsByName(objName)[idx].select();
			document.getElementsByName(objName)[idx].focus();
			cfn_trSelectBorder(idx);
		}
	}
	if ((event.keyCode == "38")) {
		if (idx > 1) {
			idx = Number(idx) - 1;
			document.getElementsByName(objName)[idx].select();
			document.getElementsByName(objName)[idx].focus();
			
			cfn_trSelectBorder(idx);
		}
	}
}

function cfn_nextFocus2(objName, idx) {

	var objLen = $('input[name='+objName+']').size();

	
	if ((event.keyCode == "13") || (event.keyCode == "40")) {
		
		if (idx < objLen) {
			var nidx = Number(idx) + 1;
			$('input[name='+objName+']').eq(nidx).select();
			$('input[name='+objName+']').eq(nidx).focus();
			cfn_trSelectBorder(nidx);
		}
	}
	if ((event.keyCode == "38")) {
		if (idx > 1) {
			var nidx = Number(idx) - 1;
			$('input[name='+objName+']').eq(nidx).select();
			$('input[name='+objName+']').eq(nidx).focus();
			
			cfn_trSelectBorder(nidx);
		}
	}
}

function cfn_nextFocusFrom(formName,objName, idx) {

	var objLen = $("#"+formName).children('input[name='+objName+']').size();

	if ((event.keyCode == "13") || (event.keyCode == "40")) {

		if (idx < objLen) {
			idx = Number(idx) + 1;
			$("#"+formName).children('input[name='+objName+']').eq(idx).select();
			$("#"+formName).children('input[name='+objName+']').eq(idx).focus();
			cfn_trSelectBorder(idx);
		}
	}
	if ((event.keyCode == "38")) {
		if (idx > 1) {
			idx = Number(idx) - 1;
			$("#"+formName).children('input[name='+objName+']').eq(idx).select();
			$("#"+formName).children('input[name='+objName+']').eq(idx).focus();
			
			cfn_trSelectBorder(idx);
		}
	}
}

/*******************************************************************************
 * cfnNul
 ******************************************************************************/

function cfnUndefined(val1, val2) {
	var rtnVal = val1;
	if (val1 == undefined) {
		rtnVal = val2;
	}
	return rtnVal;

}

function cfnNull(val) {
	var rtnVal = val;
	if (val == undefined) {
		rtnVal = "";
	}
	return rtnVal;
}
function cfnNull2(val1, val2) {
	var rtnVal = val1;
	if( (val1 == undefined) || (val1 == "") ) {
		rtnVal = val2;
	}
	return rtnVal;
}

function cfnZero2(val1, val2) {
	var rtnVal = val1;
	if( (val1 == undefined) || (val1 == "") || (val1 == "0") ) {
		rtnVal = val2;
	}
	return rtnVal;
}

function cfnFmtMoney2(val1, val2) {
	var rtnVal = cfnFmtMoney(val1);
	if( (val1 == undefined) || (val1 == "") || (val1 == "0") ) {
		rtnVal = val2;
	}
	return rtnVal;
}

function cfnSplit(val,char,idx) {
	if (val == undefined) {
		rtnVal = "";
	}else{
		if(val.indexOf(char) > -1){
			rtnVal = val.split(char)[idx];
		}else{
			rtnVal = "";
		}
	}
	return rtnVal;
}
/*******************************************************************************
 * cfnNul
 ******************************************************************************/

function cfn_imgDel(objNm) {
	document.getElementById(objNm).value = "";

	if (document.getElementById("img_" + objNm) != null) {
		document.getElementById("img_" + objNm).src = "";
	}
}
function cfn_fileDel(objNm) {
	document.getElementById(objNm).value = "";
}


/*******************************************************************************
 * cfn_byte
 ******************************************************************************/
function cfn_byte(obj) {
	var codeByte = 0;
	for (var idx = 0; idx < obj.val().length; idx++) {
		var oneChar = escape(obj.val().charAt(idx));
		if (oneChar.length == 1) {
			codeByte++;
		} else if (oneChar.indexOf("%u") != -1) {
			codeByte += 2;
		} else if (oneChar.indexOf("%") != -1) {
			codeByte++;
		}
	}
	return codeByte;
}

function cfn_byteStr(str) {
	var codeByte = 0;
	for (var idx = 0; idx < str.length; idx++) {
		var oneChar = escape(str.charAt(idx));
		if (oneChar.length == 1) {
			codeByte++;
		} else if (oneChar.indexOf("%u") != -1) {
			codeByte += 2;
		} else if (oneChar.indexOf("%") != -1) {
			codeByte++;
		}
	}
	return codeByte;
}

function cfnZeroSpace(vVal) {
	if (vVal == 0) {
		vVal = "";
	}
	return vVal;
}

function cfnChkSpace(chkVal, vVal) {
	if ((chkVal == 0) && (vVal == 0)) {
		vVal = "";
	}
	return vVal;
}

function cfn_processBar() {

	timeLoop++;

	var loopCnt = 7 * 60 / 10;
	var wProcessBar = Math.ceil(400 / loopCnt) * timeLoop;
	var min = Math.floor(timeLoop * 10 / 60);
	var sec = (timeLoop * 10) - (min * 60);

	if (min > 0) {
		min_sec = min + "분";
	}
	if (sec > 0) {
		min_sec = min_sec + sec + "초";
	}

	$("#processBar").css("width", wProcessBar);
	$("#processMins").html(min_sec);

}

function cfn_search_tab(v_tabIdx, srhTabOption) {
	$("#srhTabOption").val(srhTabOption);
	for (i = 1; i <= 20; i++) {
		if (document.getElementById("tab_" + i) != null) {
			document.getElementById("tab_" + i).className = "headerTab";
		}
	}
	document.getElementById("tab_" + v_tabIdx).className = "headerTabSelect";
	fn_searchPage('1');
}
function cfn_selectedIdx(vIdx) {
	 $("#rowSeletedIdx").val(vIdx);
}
/*******************************************************************************
 * 
 * @param frm
 * @returns
 */
function cfn_shrFormParam(frm) {
	   
	var shrFormParam = "";
    var elemCnt = frm.elements.length; 
    var andStr = "";
    for(i=0; i < elemCnt; i++){
    	shrFormParam += andStr+frm.elements[i].name+"="+frm.elements[i].value;
    	andStr = "amp;";
    }
    return shrFormParam;
}

/**
 * 
 * @param str
 * @param selVal
 * @returns
 */
function cfn_StrOption(str, selVal) {
	if((selVal == null) || (selVal == undefined) ){
		selVal = "";
	}
	var rtn = "<option value=\"\"></option>";   
	var strList = str.split(",");
	if(strList.length > 0){
	    for(i=0; i < strList.length; i++){
	    	if(strList[i].split("^")[0] == selVal){
	    		rtn += "<option value=\""+strList[i].split("^")[0]+"\" selected>"+strList[i].split("^")[1]+"</option>";
	    	}else{
	    		rtn += "<option value=\""+strList[i].split("^")[0]+"\">"+strList[i].split("^")[1]+"</option>";
	    	}
	    }
	}
    return rtn;
}

function cfn_chkEmpty(vObj,vObjText){
	
	if (vObj.val() == "") {
		alert("["+vObjText+"] 입력하세요");
		vObj.focus();
		return false;
	}
	return true;
}
function cfn_chkZero(vObj,vObjText){
	
	if (vObj.val() == "0") {
		alert("["+vObjText+"] 입력하세요");
		vObj.focus();
		return false;
	}
	return true;
}

function cfn_mrgnColor(vNum){
	var rtn = "blue";
	if(Number(vNum) > 0){
		rtn = "blue";
	}else{
		rtn = "red";
	}
	return rtn;
}

function cfn_select_cmsCate(v_cmsCateLvl, v_cmsCateCd) {

	if (v_cmsCateCd == "") {
		var v_cmsCateLvl_pre = Number(v_cmsCateLvl) - 1;
		if(v_cmsCateLvl_pre == 0){
			$("#srhCmsCateLvl").val(v_cmsCateLvl_pre);
			$("#srhCmsCateCd").val("0");
		}else{
			$("#srhCmsCateLvl").val(v_cmsCateLvl_pre);
			$("#srhCmsCateCd").val($("#srhCmsCateCd"+v_cmsCateLvl_pre).val());
		}
	} else {
		$("#srhCmsCateCd").val(v_cmsCateCd);
		$("#srhCmsCateLvl").val(v_cmsCateLvl);
	}

	var v_cmsCateLvl_next = Number(v_cmsCateLvl) + 1;

	// 초기화
	for (i = v_cmsCateLvl_next; i <= 5; i++) {
		$("#srhCmsCateCd" + i).empty();
	}

	$.ajax({
		url : '/cmmDb/cmmGetListAjax.do',
		type : 'post',
		data : {
			sql_id : "cmmList_cms_cate",
			cmsCateUpCd : v_cmsCateCd
		},
		dataType : 'json',
		success : function(data) {
			var src = "<option value=''>▽분류선택</option>";
			for (var i = 0; i < data.rsList.length; i++) {
				src += "<option value='"+data.rsList[i].cmsCateCd+"'>" + data.rsList[i].cmsCateNm + "</option>"
			}
			$("#srhCmsCateCd" + v_cmsCateLvl_next).append(src);
		}
	});

}

function cfn_find_cmsMakerNm(vGoodsMode,vObjNm, vRelObjNm) {
	var v_cmsBrandNm = $("#"+vRelObjNm).val();
	cfn_winPopup('layer_mrgn', 200, 200, '/cmsGoods/cmsGoodsMakerPop.do?goodsMode='+vGoodsMode+'&objNm='+vObjNm+'&srhBrandNm=' + v_cmsBrandNm, '제조사찾기');
}
function cfn_find_cmsBrandNm(vGoodsMode,vObjNm, vRelObjNm) {
	var v_cmsMakerNm = $("#"+vRelObjNm).val();
	cfn_winPopup('layer_mrgn', 200, 200, '/cmsGoods/cmsGoodsBrandPop.do?goodsMode='+vGoodsMode+'&objNm='+vObjNm+'&srhMakerNm=' + v_cmsMakerNm, '브랜드찾기');
}

	
function cfn_navarMinPrc(vOpmkPrc,vMinPrc,vMinUrl){
	var src= '';
	if(  Number(vOpmkPrc)*0.7 <= Number(vMinPrc)  &&   Number(vMinPrc)  <= Number(vOpmkPrc)*1.7 ){
		 src ='<br><a onclick="cfn_winPopup(\'win_mrgn\', 100, 100, \'' + cfnNull(vMinUrl) + '\', \'naverMinPrc\')" style="cursor:pointer;" >' + cfnFmtMoney(vMinPrc) + '</a>&nbsp;';
	} else {
		 src = '<br>-&nbsp;';
	}
	
	return src;
}

function cfn_opmkMrgn(opmkPrc,cateCostPct,mrgn_opmkPrc){
	var src= '<br><span style="color:' + cfn_mrgnColor(mrgn_opmkPrc) + ';cursor:pointer;"  title="수수료율(%) = ' + cateCostPct + '% , 수수료  = ' + cfnFmtMoney(Math.round(Number(opmkPrc) * Number(cateCostPct) / 100)) + '">' + cfnFmtMoney(Math.round(mrgn_opmkPrc)) + '&nbsp;</span>';
	
	return src;
}

function cfn_navarMinPrcChk(vOpmkPrc,vMinPrc,vMinUrl){
	var src= '';
	if(  Number(vOpmkPrc)*0.7 <= Number(vMinPrc)  &&   Number(vMinPrc)  <= Number(vOpmkPrc)*1.7 ){
		 src ='<a onclick="cfn_winPopup(\'win_mrgn\', 100, 100, \'' + cfnNull(vMinUrl) + '\', \'naverMinPrc\')" style="cursor:pointer;" >' + cfnFmtMoney(vMinPrc) + '</a>&nbsp;';
		 
		 var chaOpmkPrc = Number(vMinPrc) -  Number(vOpmkPrc) ;
		 if(chaOpmkPrc > 0){
			 src +='<br><span style="color:blue;" >+' + cfnFmtMoney(chaOpmkPrc) + '</a>&nbsp;';
		 }else{
			 src +='<br><span style="color:red;" >' + cfnFmtMoney(chaOpmkPrc) + '</a>&nbsp;';
		 }
	} else {
		 src = '-&nbsp;';
	}
	
	return src;
}

function cfn_navarMinPrcChk2(vJidoPrc,vMinPrc,vMinUrl){
	var src= '';
	if(  Number(vJidoPrc)*0.7 <= Number(vMinPrc)  &&   Number(vMinPrc)  <= Number(vJidoPrc)*1.7 ){
		
		 var chaOpmkPrc = Number(vMinPrc) -  Number(vJidoPrc) ;
		 if(chaOpmkPrc > 0){
			 src ='<br><a onclick="cfn_winPopup(\'win_mrgn\', 100, 100, \'' + cfnNull(vMinUrl) + '\', \'naverMinPrc\')" style="cursor:pointer;color:blue;background-color:#FFFFB9;padding-left:10px;" >' + cfnFmtMoney(vMinPrc) + '</a>&nbsp;';
		 }else{
			 src ='<br><a onclick="cfn_winPopup(\'win_mrgn\', 100, 100, \'' + cfnNull(vMinUrl) + '\', \'naverMinPrc\')" style="cursor:pointer;color:red;background-color:#FFFFB9;padding-left:10px;" >' + cfnFmtMoney(vMinPrc) + '</a>&nbsp;';
		 }
		
		
		 
	} else {
		 src = '<br>-&nbsp;';
	}
	
	return src;
}

function cfn_navarMinPrcChk3(vJidoPrc,vMinPrc,vMinUrl){
	var src= '';
	if(  Number(vJidoPrc)*0.7 <= Number(vMinPrc)  &&   Number(vMinPrc)  <= Number(vJidoPrc)*1.7 ){
		
		 var chaOpmkPrc = Number(vMinPrc) -  Number(vJidoPrc) ;
		 if(chaOpmkPrc > 0){
			 src ='<a onclick="cfn_winPopup(\'win_mrgn\', 100, 100, \'' + cfnNull(vMinUrl) + '\', \'naverMinPrc\')" style="cursor:pointer;" >' + cfnFmtMoney(vMinPrc) + '</a>&nbsp;';
			 src +='<br><span style="color:blue;">+' + cfnFmtMoney(chaOpmkPrc) + '</span>&nbsp;';
		 }else{
			 src ='<a onclick="cfn_winPopup(\'win_mrgn\', 100, 100, \'' + cfnNull(vMinUrl) + '\', \'naverMinPrc\')" style="cursor:pointer;" >' + cfnFmtMoney(vMinPrc) + '</a>&nbsp;';
			 src +='<br><span style="color:red;">' + cfnFmtMoney(chaOpmkPrc) + '</span>&nbsp;';
		 }
		
		
		 
	} else {
		 src = '-&nbsp;';
	}
	
	return src;
}


function cfn_navarMinPrcChk4(vSalePrc0,vJidoPrc,vOpmkPrc,vCateCostPct,vMinPrc,vMinUrl,vCmsGoodsCd, vOpmkMallNm){
	
	
	
	var mrgn_opmkPrc = (Number(vOpmkPrc) - Number(vSalePrc0)) - Number(vOpmkPrc) * Number(vCateCostPct) / 100;
	
	var src= '';
	
	if(mrgn_opmkPrc > 0){
		 src += '  <br><span style="color:blue;cursor:pointer;" title="마켓수수료율 : '+vCateCostPct+'%\n수수료 : 판매가  '+cfnFmtMoney(vOpmkPrc)+' - 판매몰원가 '+cfnFmtMoney(vSalePrc0)+' - 판매수수료 ' + cfnFmtMoney(Math.round(Number(vSalePrc0) * Number(vCateCostPct) / 100)) + '">+' + cfnFmtMoney(Math.round(mrgn_opmkPrc)) + '&nbsp;</span>  ';
	}else{
		 src += '  <br><span style="color:red;cursor:pointer;" title="마켓수수료율 : '+vCateCostPct+'%\n수수료  : 판매가 '+cfnFmtMoney(vOpmkPrc)+' - 판매몰원가 '+cfnFmtMoney(vSalePrc0)+' - 판매수수료  ' + cfnFmtMoney(Math.round(Number(vSalePrc0) * Number(vCateCostPct) / 100)) + '">' + cfnFmtMoney(Math.round(mrgn_opmkPrc)) + '&nbsp;</span>  ';
	}
	
	if(  Number(vJidoPrc)*0.7 <= Number(vMinPrc)  &&   Number(vMinPrc)  <= Number(vJidoPrc)*1.7 ){
		var chaOpmkPrc =   Number(vOpmkPrc) - Number(vMinPrc) ;
		 src +='<br><a onclick="cfn_winPopup(\'win_mrgn\', 50, 50, \'/cmsGoods/cmsGoodsNaverPrcEdit.do?cmsGoodsCd=' + vCmsGoodsCd + '&opmkMallNm='+vOpmkMallNm+'\', \'naverMinPrc\')" style="cursor:pointer;color:red;" title="클릭하면 해당상품으로 링크됩니다. " ><b><u>' + cfnFmtMoney(vMinPrc) + '</u></b></a>&nbsp;';
		
		 
		 if(chaOpmkPrc > 0){
			 src +='<br><span style="color:red;">+' + cfnFmtMoney(chaOpmkPrc) + '</span>&nbsp;';
		 }else{
			 src +='<br><span style="color:blue;">' + cfnFmtMoney(chaOpmkPrc) + '</span>&nbsp;';
		 }
		
	} else {
		 src += '<br>-&nbsp;';
	}
	
	return src;
}


function cfn_menuStat(vStatCd, vChkStatCd, vMcd,vCnt){
	
	if(vStatCd == vChkStatCd){
		 if (($("#span_"+vMcd) != undefined) && ($("#span_"+vMcd)  != null ) ) {
		      if(Number(vCnt) > 0) {
		    	$("#span_"+vMcd).html(" ("+cfnFmtMoney(vCnt)+")");
		    	$("#span_"+vMcd).css("display","inline");
		      }
		 }
	}
}


function cfn_dlvryStatCd_main(vDlvryStatCd){
   var rtnStr = "";
   switch(vDlvryStatCd) {
	   case "DLRQ": 
		   rtnStr += '<option value="DLRQ">배송의뢰접수</option>';
		   rtnStr += '<option value="INRQ">입고요청</option>';
		   break;
	   case "INRQ": 
		   rtnStr += '<option value="INRQ">입고발주</option>';
		   rtnStr += '<option value="INOK">검수입고완료</option>';
		   rtnStr += '<option value="INPT">부분입고</option>';
		   rtnStr += '<option value="DLRQ">배송의뢰 복원</option>';
		   break;
	   case "OTPT": 
		   rtnStr += '<option value="OTOK">부분출고</option>';
		   rtnStr += '<option value="INPT">부분검수입고</option>';
		   rtnStr += '<option value="INOK">검수입고완료</option>';
		   rtnStr += '<option value="INRQ">입고발주로 복원</option>';
		   break;
	   case "OTOK": 
		   rtnStr += '<option value="OTOK">줄고완료</option>';
		   rtnStr += '<option value="INOK">검수입고완료</option>';
		   rtnStr += '<option value="INPT">부분검수입고</option>';
		   rtnStr += '<option value="INRQ">입고발주로 복원</option>';
		   break;
	   case "INPT": 
		   rtnStr += '<option value="INPT">부분검수입고</option>';
		   rtnStr += '<option value="INOK">검수입고완료</option>';
		   rtnStr += '<option value="INRQ">입고발주로 복원</option>';
		   rtnStr += '<option value="DLPK">배송포장</option>';
		   break;
	   case "INOK": 
		   rtnStr += '<option value="INOK">검수입고완료</option>';
		   rtnStr += '<option value="DLPK">배송포장</option>';
		   break;
	   case "DLPK": 
		   rtnStr += '<option value="DLPK">배송포장</option>';
		   rtnStr += '<option value="DLOK">배송완료</option>';
		   break;
	   case "DLOK": 
		   rtnStr += '<option value=""></option>';
		   break;
	   case "INRQ": 
		   rtnStr += '<option value=""></option>';
		   break;
	   case "INRQ": 
		   rtnStr += '<option value=""></option>';
		   break;
	   case "INRQ": 
		   rtnStr += '<option value=""></option>';
		   break;
		   
	   default:	   
   }
  return rtnStr;
    
}


function cfn_browserMode() {

	browserMode = "PC";
	if (window.navigator.userAgent.indexOf("Mobile") >= 0 || window.navigator.userAgent.indexOf("Phone") >= 0 || window.navigator.userAgent.indexOf("Opera") >= 0 || window.navigator.userAgent.indexOf("Safari") >= 0) {
		browserMode = "PC";
	}

	var UserAgent = navigator.userAgent;
	if (UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
		browserMode = "MOBILE";
	}

	if (UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
		if (navigator.userAgent.indexOf("Safari") > -1) {
			// alert("결제시 Safari 브라우저는 지원이 안됩니다. Chrome으로 결제해 주시기 바랍니다. ");
		}
	}

	return browserMode;
}

function cfn_div_search_detail() {

	if ($("#span_div_search_detail").html() == "▼") {
		$("#div_search_detail").show();
		$("#span_div_search_detail").html("△");
	} else {
		$("#div_search_detail").hide();
		$("#span_div_search_detail").html("▼");
	}
	fn_detail_resize();
}

function cfn_div_search_detail_open() {
	
	$("#detail_button").hide();
	$("#div_search_detail").show();
	fn_detail_resize();
}

function cfn_div_search_detail_close() {
	
	$("#detail_button").show();
	$("#div_search_detail").hide();
	fn_detail_resize();
	
}


function cfnSleep(milliseconds){
	 var start= new Date().getTime();
	  for (var i= 0; i < 1e7; i++) {
	    if ((new Date().getTime() -start) > milliseconds){
	      break;
	    }
	  }
}

function cfn_bytelength(str) {
	var character;
	var b = 0;
	var contentsLength = str.length;

	for (var i = 0; i < contentsLength; i++) {
		character = str.charAt(i);
		if (escape(character).length > 4) {
			b += 2;
		} else {
			b++;
		}
	}
    return b;
}

function cfn_addMonth(vToday, iMonths,vDelimeter){
	var dt = new Date(vToday);
	dt.setMonth( dt.getMonth() + iMonths );
    var year = dt.getFullYear();
    var month = (1+dt.getMonth());
    month = month >= 10 ? month : '0' +month;
    var day = dt.getDate();
    day = day >= 10 ? day : '0'+day;
    var rtnDate = year+vDelimeter+month+vDelimeter+day;
    return  rtnDate
}

/*******************************************************************************
 * 이미지 로드 오류 2022.11.28 김정수
 ******************************************************************************/
function cfn_chkImg(url){
	var rtn = "";
	$.get(url, function() {
		 rtn = "SUCCESS";
    })
    .fail(function() {
    	rtn = "FAIL";
    })
	return rtn;
}

/*******************************************************************************
 * admin LTE 모달 사이즈 2022.12.9 김정수
 ******************************************************************************/
function cfn_modalsize(vW, vHm, vWm) {

	var popHeight = $(window).height() - vWm;
	var popWidth = $(window).width() - vHm;

	if (popWidth > vW) {
		popWidth = vW;
	}
	var popLeft = ($(window).width() - popWidth) / 2 - 10;

	$("#modal_contents").css("top", 30 + "px");
	$("#modal_contents").css("left", popLeft + "px");
	$("#modal_contents").css("height", popHeight + "px");
	$("#modal_contents").css("width", popWidth + "px");

	$(".modal-content").css("height", popHeight + "px");
	$(".modal-content").css("width", popWidth + "px");

	$("#modal_iframe").css("height", (popHeight - 150) + "px");
}

/*******************************************************************************
 * admin LTE 모달 사이즈 2022.12.9 김정수
 ******************************************************************************/
function cfn_modalFix(objId,vWidth, vHeight) {
	

	if(vHeight > window.innerHeight) {
		vHeight = window.innerHeight - 50;
	}
	if(vWidth > window.innerWidth) {
		vWidth = window.innerWidth - 50;
	}
	var popTop = (window.innerHeight - vHeight )/2;
	var popLeft = (window.innerWidth - vWidth )/2;


	$("#"+objId).css("top", popTop + "px");
	$("#"+objId).css("left", popLeft + "px");
	$("#"+objId).css("height", vHeight + "px");
	$("#"+objId).css("width", vWidth + "px");

	$(".modal-content").css("height", vHeight + "px");
	$(".modal-content").css("width", vWidth + "px");

	$("#modal_iframe").css("height", (vHeight - 120) + "px");
}
/*******************************************************************************
 * admin LTE 모달 사이즈 2022.12.9 김정수
 ******************************************************************************/
function cfn_modalPop(objId,vPopMode, vWidth, vHeight) {
	
	var popTop = 0;
	var popLeft = 0;
	
	

	if(vPopMode == "popSize"){
		
		if(vHeight > window.innerHeight) {
			vHeight = window.innerHeight - 30;
		}
		if(vWidth > window.innerWidth) {
			vWidth = window.innerWidth - 30;
		}
		
		
		popTop = (window.innerHeight - vHeight )/2;
		popLeft = (window.innerWidth - vWidth )/2;
		
		
	}
	if(vPopMode == "popMargin"){
		
		popTop = vHeight /2;
		popLeft = vWidth /2;
		
        vHeight = window.innerHeight - vHeight - 15;
		vWidth = window.innerWidth - vWidth - 15;
		
		alert(popTop +"/"+popLeft+"/"+vHeight+"/"+vWidth);
		
		//if(vWidth > 1300){
		//	vWidth = 1300;
		//	popLeft = (window.innerWidth - vWidth) /2;
		//}
		alert(popTop +"/"+popLeft+"/"+vHeight+"/"+vWidth);
	}
	

	if( cfn_browserMode() != "PC" ){
		
		popTop = 0;
		popLeft = 0;
		
        vHeight = window.innerHeight - 20;
		vWidth = window.innerWidth -  20;
	}

	alert(popTop +"/"+popLeft+"/"+vHeight+"/"+vWidth);
	
	$("#"+objId).css("top", popTop + "px");
	$("#"+objId).css("left", popLeft + "px");
	$("#"+objId).css("height", vHeight + "px");
	$("#"+objId).css("width", vWidth + "px");

	$(".modal-content").css("height", vHeight + "px");
	$(".modal-content").css("width", vWidth + "px");

	$("#modal_iframe").css("height", (vHeight - 110) + "px");
}

/*******************************************************************************
 * admin LTE 모달 사이즈 2022.12.9 김정수
 ******************************************************************************/
function cfn_detailSearch_modalsize(vW, vHm, vWm) {

	var popHeight = $(window).height() - vWm;
	var popWidth = $(window).width() - vHm;

	if (popWidth > vW) {
		popWidth = vW;
	}
	var popLeft = ($(window).width() - popWidth) / 2 - 10;

	$("#modal_detail").css("top", 30 + "px");
	$("#modal_detail").css("left", popLeft + "px");
	$("#modal_detail").css("height", popHeight + "px");
	$("#modal_detail").css("width", popWidth + "px");

	$(".modal-content").css("height", popHeight + "px");
	$(".modal-content").css("width", popWidth + "px");
}

/*******************************************************************************
 * admin LTE cfn_pageNavi 2022.12.22 김정수
 ******************************************************************************/
function cfn_pageNavi(vDataCnt) {
	var vCurrPage = Number($("#currPage").val());
	var vPageRows = Number($("#pageRow").val());
	var vPageCount = Math.ceil(vDataCnt / vPageRows);
	if (vDataCnt > vPageRows * vPageCount) {
		vPageCount = vPageCount + 1;
	}
	$("#lastPage").val(vPageCount);
	$("#pageNo").empty();
	for (var i = 1; i <= vPageCount; i++) {
		if(i == vCurrPage){
			$("#pageNo").append("<option value=\""+i+"\" selected>" + i + " 페이지</option>");
		}else{
			$("#pageNo").append("<option value=\""+i+"\">" + i + " 페이지</option>");
		}
	}
}
/*******************************************************************************
 * admin LTE cfn_pageMove 2022.12.22 김정수
 ******************************************************************************/
function cfn_pageMove(vOpt){
	var vCurrPage = Number($("#currPage").val());
	var vLastPage = Number($("#lastPage").val());
	var vMovePage = vCurrPage;
	if(vOpt == "prev"){
		if(vMovePage > 1){
			vMovePage = vCurrPage - 1;
		}else{
			vMovePage = 1;
		}
	}
	if(vOpt == "next"){
		if(vMovePage < vLastPage){
			vMovePage = Number(vCurrPage) + 1;
		}else{
			vMovePage = vLastPage;
		}
	}
	fn_searchPage(vMovePage);
}


/*******************************************************************************
 * admin LTE cfn_serTermDate 2022.12.22 김정수
 ******************************************************************************/
function cfn_serTermDate(vOpt){
	var today = new Date();
	var yyyy = today.getFullYear();
	var mm = today.getMonth()+1;
	var dd = today.getDate();
	if(mm < 10){
		mm = "0" + mm;
	}
	if(dd < 10){
		dd = "0" + dd;
	}
	var toDate = yyyy+"-"+mm+"-"+dd;
	
	var frday = new Date(toDate);
	if(vOpt == "oneWeek"){
		frday.setDate(frday.getDate() - 7); 
	}
	if(vOpt == "oneMonth"){
		frday.setMonth(frday.getMonth() - 1); 
	}
	if(vOpt == "threeMonth"){
		frday.setMonth(frday.getMonth() - 3); 
	}
	if(vOpt == "sixMonth"){
		frday.setMonth(frday.getMonth() - 6); 
	}
	if(vOpt == "oneYear"){
		frday.setMonth(frday.getMonth() - 12); 
	}
	var fr_yyyy = frday.getFullYear();
	var fr_mm = frday.getMonth()+1;
	var fr_dd = frday.getDate();
	if(fr_mm < 10){
		fr_mm = "0" + fr_mm;
	}
	if(fr_dd < 10){
		fr_dd = "0" + fr_dd;
	}
	var frDate = fr_yyyy+"-"+fr_mm+"-"+fr_dd;
	
	if(vOpt == "today"){
		frDate = toDate;
	}
	if(vOpt == "all"){
		frDate = '2000-01-01';
	}
	if(vOpt != ""){
		$("#srhFrDate").val(frDate);
		$("#srhToDate").val(toDate);
	}else{
		$("#srhFrDate").val("");
		$("#srhToDate").val("");
	}
	$("#shrTermType").val(vOpt);
	
}

/*******************************************************************************
 * admin LTE cfn_srhOrderBy 2022.12.22 김정수
 ******************************************************************************/
function cfn_srhOrderBy(vVal){
	$("#srhOrderBy").val(vVal);
	fn_searchPage('1');
}
/*******************************************************************************
 * admin LTE cfn_deatilSearchInfo 2022.12.27 김정수
 ******************************************************************************/
function cfn_detailView(vStartObjNm, vEndObjNm) {
	var chkStr = cfn_deatilSearchInfo('chk', vStartObjNm, vEndObjNm);
	if ($("#detailView").css("display") == "none") {
		$("#detailView").show();

		if (chkStr > 0) {
			$("#btn_detailView").removeClass("btn-light");
			$("#btn_detailView").addClass("btn-warning");
			$("#btn_detailView").html("<i class=\"lnr-chevron-up  btn-icon-wrapper\"></i>상세 " + chkStr + "항목");
		} else {
			$("#btn_detailView").removeClass("btn-warning");
			$("#btn_detailView").addClass("btn-light");
			$("#btn_detailView").html("<i class=\"lnr-chevron-up  btn-icon-wrapper\"></i>상세");
		}
		$("#btm_search").hide();
	} else {
		$("#detailView").hide();

		if (chkStr > 0) {
			$("#btn_detailView").removeClass("btn-light");
			$("#btn_detailView").addClass("btn-warning");
			$("#btn_detailView").html("<i class=\"lnr-chevron-down  btn-icon-wrapper\"></i>상세 " + chkStr + "항목");
		} else {
			$("#btn_detailView").removeClass("btn-warning");
			$("#btn_detailView").addClass("btn-light");
			$("#btn_detailView").html("<i class=\"lnr-chevron-down  btn-icon-wrapper\"></i>상세");
		}
		$("#btm_search").show();
	}
}
/*******************************************************************************
 * admin LTE cfn_deatilSearchInfo 2022.12.27 김정수
 ******************************************************************************/
function cfn_deatilSearchInfo(vOpt, vStartObjNm, vEndObjNm) {
	var objs = $("#searchForm").serializeArray();
	var chkYn = "";
	var chkCnt = 0;
	var vObjs = "";
	for (var i = 0; i < objs.length; i++) {

		var param_name = objs[i].name;
		var param_value = objs[i].value;

		if (param_name == vStartObjNm) {
			chkYn = "Y";
		}
		if (param_name == vEndObjNm) {
			chkYn = "N";
		}
		if (chkYn == "Y") {

			if (vOpt == "chk") {
				if (param_value.length > 0) {
					chkCnt++;
				}
			}
			if (vOpt == "reset") {
				$("#" + param_name).val("");
				vObjs += ", " + param_name;
			}
		}
	}
	alert(vObjs);

	return chkCnt;

}

/*******************************************************************************
 * admin LTE cfn_searchForm_reset 2022.12.27 김정수
 ******************************************************************************/
function cfn_searchForm_reset(vStartObjNm, vEndObjNm) {
	var objs = $("#searchForm").serializeArray();
	var chkYn = "";
	var chkCnt = 0;
	var vObjs = "";
	for (var i = 0; i < objs.length; i++) {

		var param_name = objs[i].name;
		var param_value = objs[i].value;

		if (param_name == vStartObjNm) {
			chkYn = "Y";
		}
		if (param_name == vEndObjNm) {
			chkYn = "N";
		}
		if (chkYn == "Y") {

				$("#" + param_name).val("");
				vObjs += ", " + param_name;
		}
	}
	alert(vObjs);

}

/*******************************************************************************
 * admin LTE cfn_srhCmsCateCd 2022.12.27 김정수
 ******************************************************************************/
function cfn_srhCmsCateCd(v_cmsCateLvl, v_cmsCateCd) {

	$("#srhCmsCateCd").val(v_cmsCateCd);
	$("#srhCmsCateLvl").val(v_cmsCateLvl);

	var v_cmsCateLvl_next = Number(v_cmsCateLvl) + 1;

	// 초기화
	for (i = v_cmsCateLvl_next; i <= 5; i++) {
		$("#srhCmsCateCd" + i).empty();
	}

	$.ajax({
		url : '/cmmDb/cmmGetListAjax.do',
		type : 'post',
		data : {
			sql_id : "cmmList_cms_cate",
			cmsCateUpCd : v_cmsCateCd,
			cateMode : "goodsMain"
		},
		dataType : 'json',
		success : function(data) {
			var src = "<option value=''>▽분류선택</option>";
			for (var i = 0; i < data.rsList.length; i++) {
				src += "<option value='"+data.rsList[i].cmsCateCd+"'>" + data.rsList[i].cmsCateNm + "</option>"
			}
			$("#srhCmsCateCd" + v_cmsCateLvl_next).append(src);
		}
	});
}
/** ******************************** */
function fn_opmkSalePrc(partnerPrc, jidoPrc, jidoLimitPct, opmkCostPct,saleMrgnPct, prcTypeCd){
	var opmkSalePrc = 0;
	var optiMrgnPrc = partnerPrc / (100 - ( opmkCostPct + saleMrgnPct  ))*100;
	optiMrgnPrc = Math.ceil(optiMrgnPrc/10)*10;
	
	var optiJidoPrc = jidoPrc * ( 100 - jidoLimitPct ) / 100;
	optiJidoPrc = Math.ceil(optiJidoPrc/10)*10;
	
	if( prcTypeCd == "SOBI"){
		if( jidoPrc > optiMrgnPrc ) {
			opmkSalePrc = jidoPrc;
		} else {
			opmkSalePrc = optiMrgnPrc;
		}
	}
	if( prcTypeCd == "OJIDO"){
		if( optiJidoPrc > optiMrgnPrc ) {
			opmkSalePrc = optiJidoPrc;
		} else {
			opmkSalePrc = optiMrgnPrc;
		}
	}
	if( prcTypeCd == "MRGN"){
		opmkSalePrc = optiMrgnPrc;
	}
	
	return opmkSalePrc;
}

/** *************** */
function cfn_chk_ext(filePathName) {
	var up_falg = "false";
	var allow_exts = [ "jpg", "png", "jpeg", "gif" ];

	var lastIndex = -1;
	lastIndex = filePathName.lastIndexOf('.');
	var extension = "";

	if (lastIndex != -1) {
		extension = filePathName.substring(lastIndex + 1, filePathName.len).toLowerCase();

	} else {
		extension = "";
	}
	for (i = 0; i < allow_exts.length; i++) {
		if (allow_exts[i] == extension) {
			up_falg = "true";
		}
	}
	if (up_falg == "false") {
		alert("[" + extension + "] 파일형식은 업로드할수 없습니다.");
		return false;
	}
}
/** ***************************** */
function cfn_upattachFile(objName, saveDir) {

	var filePathName = document.getElementById("file_"+objName).value;
	if (cfn_chk_ext(filePathName) == false) {
		document.getElementById("file_"+objName).value = "";
		return;
	}
	
	var formData = new FormData();
	var uploadFile = $("#file_"+objName)[0].files[0];
	
	
	formData.append('uploadFile', $("#file_"+objName)[0].files[0]);
	formData.append('saveDir', saveDir);

	$.ajax({
		type : "POST",
		enctype : 'multipart/form-data',
		url : "/uploadImage",
		data : formData,
		processData : false,
		contentType : false,
		cache : false,
		timeout : 600000,
		success : function(data) {
			alert(data.rsltMsg);
			alert(data.saveAsFileNm);
			$("#file_"+objName).val("");
			$("#"+objName).val("/uploads"+data.saveAsFileNm);
			
		},
		error : function(e) {
			console.log("ERROR : ", e);
			alert("fail");
		}
	});

}



// 탭 이동
function cfn_tabMove(v_tabIdx) {
	// alert(v_tabIdx);
	var ilen = $("[id^='tab_title_']").length;
	// alert(ilen);
	
	for(var i = 1; i <= ilen; i++){
		if(i == v_tabIdx){
			$("#tab_title_"+i).css("color","black");
			$("#tab_cnt_"+i).css("background-color","red");
		}else{
			$("#tab_title_"+i).css("color","lightgray");
			$("#tab_cnt_"+i).css("background-color","lightgray");
		}
	}
	
}

// 북마크 이동
function cfn_bookmark(bookmarkId){
	var bookmarkTop = $("#"+bookmarkId).offset().top;
	window.scrollTo(0,bookmarkTop);
}


// 이전체이지 이동
function cfn_goBack(vPageUrl){
	var referrer = document.referrer;
	if(referrer.indexOf(vPageUrl)> -1){
		history.back();
	}else{
		window.location.href=vPageUrl;
	}
}

// 이미지 파일 선택

function cfn_imageFileSelect(afileName,objNm) {
	if (afileName != "") {
		
	}
}

// 이미지 업로드
function cfn_imgUpload(imgObjNm, saveDir){
	$('#file_'+imgObjNm).on('change', function(e) {
		var maxSize = 5 * 1024 * 1024;
		var imageFile = e.originalEvent.srcElement.files[0];
		if (imageFile.size > maxSize) {
			alert("이미지파일 사이즈는 5MB 이내로 등록 가능합니다.");
			return false;
		}
		if (imageFile) {
			$('#img_'+imgObjNm).show();
			$('#img_'+imgObjNm).attr("src", URL.createObjectURL(imageFile));
			cfn_upattachFile(imgObjNm, saveDir);
		}
	});
}


// 바이트 체크
function cfn_byte(objNm,len) {
	$('[name="'+objNm+'"]').on('blur', function() {
		// 메시지 내용 byte 체크
		var byteLen = cfn_bytelength($('[name="'+objNm+'"]').val());

		$('#span_byte_'+objNm).empty();
		$('#span_byte_'+objNm).html(byteLen + "/"+len+" byte")
		$('#text_byte_'+objNm).val(byteLen);
		if (byteLen > len) {
			$('#div_byte_'+objNm).css("background-color", "orange");
		} else {
			$('#div_byte_'+objNm).css("background-color", "white");
		  }

	});
}