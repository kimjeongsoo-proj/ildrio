function gfn_inputText(columnName, columnValue, inputFormatType, styleCss, idx) {
	var src = '';
	if (inputFormatType == 'money') {
		src += '<input type="text" name="' + columnName + '" id="' + columnName + '" value="' + cfnFmtMoney(columnValue) + '" ';
	} else {
		src += '<input type="text" name="' + columnName + '" id="' + columnName + '" value="' + columnValue + '" ';
	}
	src += 'class="form-control" ';
	if (inputFormatType == 'money') {
		src += 'style="margin-left:5px;width:90%;height:25px;border:1px solid #f0f0f0;text-align:right;' + styleCss + '" ';
		src += ' onfocus="commaToNumber(this);this.select();" ';
		src += ' onblur="chk_num(this);numberToCommma(this);"  ';
	} else {
		src += 'style="margin-left:5px;width:90%;height:25px;border:1px solid #f0f0f0;' + styleCss + '" ';
	}
	src += 'onchange="gfn_change_chk(' + idx + ')" ';
	src += '>';

	return src;
}
function gfn_valueText(columnName, columnValue, inputFormatType, styleCss, idx) {
	var src = '';
	if (inputFormatType == 'money') {
		src += cfnFmtMoney(columnValue);
	} else {
		src += columnValue;
	}
	return src;
}
function gfn_hiddenValue(columnName, columnValue, inputFormatType, styleCss, idx) {
	var src = '<input type="hidden" name="'+columnName+'" id="'+columnName+'" value="'+columnValue+'">';
	if (inputFormatType == 'money') {
		src += cfnFmtMoney(columnValue);
	} else {
		src += columnValue;
	}
	return src;
}

function gfn_inputDate(columnName, columnValue, inputFormatType, styleCss, idx) {

	var src = '';
	src += '<input type="text" name="' + columnName + '" id="' + columnName + '" value="' + columnValue + '" ';
	src += 'class="form-control datePickerInput" ';
	src += 'style="margin-left:5px;width:90%;height:25px;border:1px solid #f0f0f0;' + styleCss + '" ';
	src += 'onchange="gfn_change_chk(' + idx + ')" ';
	src += '>';

	return src;
}

function gfn_valueDate(columnName, columnValue, inputFormatType, styleCss, idx) {

	return columnValue;
}

function gfn_inputText2(columnName, columnValue, inputFormatType, styleCss, idx, vOnchange, vReadonly) {
	var src = '';
	src += '<input type="text" name="' + columnName + '" id="' + columnName + '" value="' + columnValue + '" ';
	src += 'class="form-control" ';
	src += 'style="margin-left:5px;width:90%;height:25px;border:1px solid #f0f0f0;' + styleCss + '" ';
	if (inputFormatType == 'money') {
		src += ' onfocus="commaToNumber(this);this.select();" ';
		src += ' onblur="chk_num(this);numberToCommma(this);"  ';
	}
	src += 'onchange="gfn_change_chk(' + idx + ');' + vOnchange + '" ';
	src += ' ' + vReadonly + ' >';

	return src;
}

function gfn_inputCheckbox(columnName, columnValue, idx) {
	var src = '';
	src += '<input type="checkbox" name="chk_' + columnName + '" id="chk_' + columnName + '"  ';
	src += 'onclick="gfn_chkb_listObj(\'' + columnName + '\', \'' + idx + '\',  this.checked)" ';
	src += ' ' + gfn_isCheked('Y', columnValue) + ' ';
	src += 'onchange="gfn_change_chk(' + idx + ')"  ';
	src += '>';

	src += '<input type="hidden" name="' + columnName + '" id="' + columnName + '" ';
	src += 'value="' + columnValue + '"   ';
	src += 'style="width:50px;">';

	return src;
}
function gfn_valueCheckbox(columnName, columnValue, idx) {
	var src = '';
	src += '<input type="checkbox" name="chk_' + columnName + '" id="chk_' + columnName + '"  ';
	src += ' ' + gfn_isCheked('Y', columnValue) + ' ';
	src += ' readonly >';

	return src;
}

function gfn_inputSelectJs(columnName, columnValue, codeId ,styleCss, idx) {
	var src = '';

	src += '<select  name="' + columnName + '" id="' + columnName + '" ';
	src += 'class="form-control form-select" ';
	src += 'style="margin-left:5px;height:25px;border:1px solid #f0f0f0;padding:2px 5px;'+styleCss+'" ';
	src += 'onchange="gfn_change_chk(' + idx + ')" ';
	src += '>';
	src += cfn_CmmCd(codeId, "select", columnValue);
	src += '</select>';

	return src;
}  

function gfn_inputSelect(columnName, columnValue, codeData, idx) {
	var src = '';

	src += '<select  name="' + columnName + '" id="' + columnName + '" ';
	src += 'class="form-control form-select" ';
	src += 'style="margin-left:5px;width:90%;height:25px;border:1px solid #f0f0f0;padding:2px 5px;" ';
	src += 'onchange="gfn_change_chk(' + idx + ')" ';
	src += '>';
	src += gfn_CmmCd(codeData, "select", columnValue);
	src += '</select>';

	return src;
}



function gfn_valueSelect(columnName, columnValue, codeData, idx) {
	return cfn_CmmCd(codeData, "value", columnValue);
}

function gfn_inputSelect_del(columnName, columnValue, columnCodeId, idx) {
	var src = '';

	src += '<select  name="' + columnName + '" id="' + columnName + '" ';
	src += 'class="form-control form-select" ';
	src += 'style="margin-left:5%x;width:90%;height:25px;border:1px solid #f0f0f0;padding:2px 5px;" ';
	src += 'onchange="gfn_change_chk(' + idx + ')" ';
	src += '>';
	src += cfn_CmmCd(columnCodeId, "select", columnValue);
	src += '</select>';

	return src;
}

function gfn_inputSelect2(columnName, columnValue, columnCodeId, idx, vOnchange) {
	var src = '';

	src += '<select  name="' + columnName + '" id="' + columnName + '" ';
	src += 'class="form-control form-select" ';
	src += 'style="margin-left:5px;width:90%;height:25px;border:1px solid #f0f0f0;" ';
	src += 'onchange="gfn_change_chk(' + idx + ');' + vOnchange + '" ';
	src += '>';
	src += cfn_CmmCd(columnCodeId, "select", columnValue);
	src += '</select>';

	return src;
}

function gfn_chkb_listObj(objNm, idx, isChecked) {
	if (isChecked == true) {
		$("#inputForm").children().find('input[name=' + objNm + ']').eq(idx).val("Y");
	} else {
		$("#inputForm").children().find('input[name=' + objNm + ']').eq(idx).val("N");
	}
}

function gfn_isCheked(chkVal, objVal) {
	var rtnVal = "";
	if (chkVal == objVal) {
		rtnVal = "checked";
	}
	return rtnVal;
}

function gfn_splitGrid_sameHeight(rowCount) {
	
	//alert(rowCount);

	var chk1 = "";
	for (var i = 0; i < rowCount; i++) {
		var ltrh = $('#LeftDataTable tr').eq(i).height();
		var rtrh = $('#RightDataTable tr').eq(i).height();

		if (ltrh > rtrh) {
			$('#RightDataTable tr').eq(i).css("height", ltrh);
		}
		if (ltrh < rtrh) {
			$('#LeftDataTable tr').eq(i).css("height", rtrh);
		}

	}
}

function gfn_splitGrid_scrollRight(x, y) {
	divRightTitle.scrollLeft = x;
	divLeftData.scrollTop = y;
}

function gfn_splitGrid_scrollLeft(x, y) {
	divLeftTitle.scrollLeft = x;
	divRightData.scrollTop = y;
}

function gfn_modal_help(menuCd) {
	$("#modal_title").html("도움말");
	cfn_modalPop("modal_contents", "popMargin", 50, 50);
	document.modal_iframe.location.href = "/cmmMenu/cmmMenuHelpView.do?menuCe=" + menuCd;

}
/*******************************************************************************
 * 체크박스
 ******************************************************************************/
function gfn_chkAll() {
	var irow = $("#inputForm").children().find('input[name=chkb]').length;
	var chked = $("#inputForm").children().find("input:checkbox[id=chkbAll]").is(":checked");
	if (chked == true) {
		for (i = 0; i < irow; i++) {
			$("#inputForm").children().find('input[name=chkb]').eq(i).prop("checked", true);
			$("#inputForm").children().find('input[name=chkbFlag]').eq(i).val('true');
		}
	} else {
		for (i = 0; i < irow; i++) {
			$("#inputForm").children().find('input[name=chkb]').eq(i).prop("checked", false);
			$("#inputForm").children().find('input[name=chkbFlag]').eq(i).val('false');
		}
	}
}

function gfn_chkb(idx) {
	var chked = $("#inputForm").children().find("input:checkbox[id=chkb]").eq(idx).is(":checked");

	if (chked == true) {
		$("#inputForm").children().find('input[name=chkbFlag]').eq(idx).val('true');
	} else {
		$("#inputForm").children().find('input[name=chkbFlag]').eq(idx).val('false');
	}
}

function gfn_change_chk(idx) {
	if (selectedRowIndex != null) {
		idx = selectedRowIndex;
	}

	$("#inputForm").children().find("input:checkbox[id=chkb]").eq(idx).prop("checked", true);
	$("#inputForm").children().find('input[name=chkbFlag]').eq(idx).val('true');
	$("#selectedRowIdx").val(idx);

}

function gfn_obj_chk(objNm, objVal, idx) {
	var chked = $("#inputForm").children().find("input:checkbox[id=" + objNm + "_chkb]").eq(idx).is(":checked");
	if (chked == true) {
		$("#inputForm").children().find('input[name=' + objNm + ']').eq(idx).val('Y');
	} else {
		$("#inputForm").children().find('input[name=' + objNm + ']').eq(idx).val('N');
	}
}

/*******************************************************************************
 * 선택행 이하 값 일괄적용
 ******************************************************************************/
function gfn_setAll_input(objNm, objType) {
	var idx = Number($("#selectedRowIdx").val());

	var vObjValue = $("#inputForm").children().find('input[name=' + objNm + ']').eq(idx).val();

	if (confirm((idx + 1) + "번째 행부터 [" + vObjValue + "] 일괄 적용하시겠습니까?")) {
		var irow = $("#inputForm").children().find('input[name=' + objNm + ']').length;

		for (i = idx; i < irow; i++) {
			// 선택
			$("#inputForm").children().find('input[name=chkb]').eq(i).prop("checked", true);
			$("#inputForm").children().find('input[name=chkbFlag]').eq(i).val('true');

			// 값변경
			$("#inputForm").children().find('input[name=' + objNm + ']').eq(i).val(vObjValue);
		}
	}
}

function gfn_setAll_select(objNm) {
	var idx = Number($("#selectedRowIdx").val());

	var vObjValue = $("#inputForm").children().find('select[name=' + objNm + ']').eq(idx).val();
	var vObjText = $("#inputForm").children().find('select[name=' + objNm + ']  option:selected').eq(idx).text();

	if (confirm((idx + 1) + "번째 행부터 [" + vObjText + "] 일괄 적용하시겠습니까?")) {
		var irow = $("#inputForm").children().find('select[name=' + objNm + ']').length;

		for (i = idx; i < irow; i++) {
			// 선택
			$("#inputForm").children().find('input[name=chkb]').eq(i).prop("checked", true);
			$("#inputForm").children().find('input[name=chkbFlag]').eq(i).val('true');

			// 값변경
			$("#inputForm").children().find('select[name=' + objNm + ']').eq(i).val(vObjValue);
		}
	}
}

function gfn_setAll_checkbox(objNm) {
	var idx = Number($("#selectedRowIdx").val());

	var chked = $("#inputForm").children().find("input:checkbox[name=chk_" + objNm + "]").eq(idx).is(":checked");
	var irow = $("#inputForm").children().find('input[name=' + objNm + ']').length;
	var vObjValue = $("#inputForm").children().find('input[name=' + objNm + ']').eq(idx).val();

	if (confirm((idx + 1) + "번째 행부터 [" + chked + "] 일괄 적용하시겠습니까?")) {
		for (i = idx; i < irow; i++) {
			// 선택
			$("#inputForm").children().find('input[name=chkb]').eq(i).prop("checked", true);
			$("#inputForm").children().find('input[name=chkbFlag]').eq(i).val('true');

			// 값변경
			$("#inputForm").children().find('input[name=chk_' + objNm + ']').eq(i).prop("checked", chked);
			$("#inputForm").children().find('input[name=' + objNm + ']').eq(i).val(vObjValue);
		}
	}

}
/*******************************************************************************
 * 체크박스 form
 ******************************************************************************/
function gfn_formChkAll(formId, chkName) {

	var irow = $("#" + formId).children().find('input[name=' + chkName + ']').length;
	var chked = $("#" + formId).children().find("input:checkbox[id=" + chkName + "All]").is(":checked");
	if (chked == true) {
		for (i = 0; i < irow; i++) {
			$("#" + formId).children().find('input[name=' + chkName + ']').eq(i).prop("checked", true);
			$("#" + formId).children().find('input[name=' + chkName + 'Flag]').eq(i).val('true');
		}
	} else {
		for (i = 0; i < irow; i++) {
			$("#" + formId).children().find('input[name=' + chkName + ']').eq(i).prop("checked", false);
			$("#" + formId).children().find('input[name=' + chkName + 'Flag]').eq(i).val('false');
		}
	}
}

function gfn_formChkb(formId, chkbId, idx) {
	var chked = $("#" + formId).children().find("input:checkbox[id=" + chkbId + "]").eq(idx).is(":checked");
	if (chked == true) {
		$("#" + formId).children().find('input[name=' + chkbId + 'Flag]').eq(idx).val('true');
	} else {
		$("#" + formId).children().find('input[name=' + chkbId + 'Flag]').eq(idx).val('false');
	}
}

/*******************************************************************************
 * 폼 선택행 이하 값 일괄적용
 ******************************************************************************/
function gfn_formSetAll_input(formId, objNm, objType) {
	var idx = Number($("#selectedRowIdx").val());

	var vObjValue = $("#" + formId).children().find('input[name=' + objNm + ']').eq(idx).val();

	if (confirm((idx + 1) + "번째 행부터 [" + vObjValue + "] 일괄 적용하시겠습니까?")) {
		var irow = $("#" + formId).children().find('input[name=' + objNm + ']').length;

		for (i = idx; i < irow; i++) {
			// 선택
			$("#" + formId).children().find('input[name=chkb]').eq(i).prop("checked", true);
			$("#" + formId).children().find('input[name=chkbFlag]').eq(i).val('true');

			// 값변경
			$("#inputForm").children().find('input[name=' + objNm + ']').eq(i).val(vObjValue);
		}
	}
}

function gfn_formSetAll_select(formId, objNm) {
	var idx = Number($("#selectedRowIdx").val());

	var vObjValue = $("#" + formId).children().find('select[name=' + objNm + ']').eq(idx).val();

	if (confirm((idx + 1) + "번째 행부터 [" + vObjValue + "] 일괄 적용하시겠습니까?")) {
		var irow = $("#" + formId).children().find('select[name=' + objNm + ']').length;

		for (i = idx; i < irow; i++) {
			// 선택
			$("#" + formId).children().find('input[name=chkb]').eq(i).prop("checked", true);
			$("#" + formId).children().find('input[name=chkbFlag]').eq(i).val('true');

			// 값변경
			$("#" + formId).children().find('select[name=' + objNm + ']').eq(i).val(vObjValue);
		}
	}
}

function gfn_formSetAll_checkbox(formId, objNm) {
	var idx = Number($("#selectedRowIdx").val());

	var chked = $("#" + formId).children().find("input:checkbox[name=chk_" + objNm + "]").eq(idx).is(":checked");
	var irow = $("#" + formId).children().find('input[name=' + objNm + ']').length;
	var vObjValue = $("#" + formId).children().find('input[name=' + objNm + ']').eq(idx).val();

	if (confirm((idx + 1) + "번째 행부터 [" + chked + "] 일괄 적용하시겠습니까?")) {
		for (i = idx; i < irow; i++) {
			// 선택
			$("#" + formId).children().find('input[name=chkb]').eq(i).prop("checked", true);
			$("#" + formId).children().find('input[name=chkbFlag]').eq(i).val('true');

			// 값변경
			$("#" + formId).children().find('input[name=chk_' + objNm + ']').eq(i).prop("checked", chked);
			$("#" + formId).children().find('input[name=' + objNm + ']').eq(i).val(vObjValue);
		}
	}

}


function gfn_CmmCd(codeData, opt, currCode) {


	var arrSrc = codeData.split("♥");
	var rtn = "";

	if (opt == "select") {
		rtn = "<option value=''></option>";
		for (i = 1; i < arrSrc.length; i++) {

			var arrOption = arrSrc[i].split("^");
			if (arrOption[0] == currCode) {
				rtn += "<option value='" + arrOption[0] + "' selected>" + arrOption[1] + "</option>";
			} else {
				rtn += "<option value='" + arrOption[0] + "'>" + arrOption[1] + "</option>";
			}
		}
	}

	if (opt == "value") {
		rtn = currCode;
		for (i = 1; i < arrSrc.length; i++) {
			var arrOption = arrSrc[i].split("^");
			if (arrOption[0] == currCode) {
				rtn = arrOption[1];
			}
		}
	}

	return rtn;
}
/*******************************************************************************
 * gfn_sort
 ******************************************************************************/
function gfn_sort(sortNm, sortCols, sortAsc) {

	var old_sortNm = $("#srhOrderName").val();

	$("#sort_" + old_sortNm).html("▽")
	$("#sort_" + old_sortNm).css("color", "#b0b0b0");
	$("#sort_" + sortNm).css("color", "#202020");

	if (old_sortNm != sortNm) {

		$("#srhOrderName").val(sortNm);
		$("#srhOrderBy").val(sortCols);
		$("#srhOrderAsc").val(sortAsc);

		if (sortAsc == "asc") {
			$("#sort_" + sortNm).html("▲")
		}
		if (sortAsc == "desc") {
			$("#sort_" + sortNm).html("▼");
		}
	} else {
		if ($("#srhOrderAsc").val() == "desc") {
			$("#srhOrderAsc").val("asc");
			$("#sort_" + sortNm).html("▲");
		} else {
			$("#srhOrderAsc").val("desc");
			$("#sort_" + sortNm).html("▼");
		}
	}
	fn_searchPage('1');
}

/*******************************************************************************
 * div스크롤 splitGrid
 ******************************************************************************/
function gfn_splitGrid_scrollRight(x, y) {
	divRightTitle.scrollLeft = x;
	divLeftData.scrollTop = y;
}

function gfn_splitGrid_scrollLeft(x, y) {
	divLeftTitle.scrollLeft = x;
	divRightData.scrollTop = y;
}

function gfn_detail_view() {
	if ($("#div_detail_search").css("display") == "none") {
		$("#div_detail_search").show();
		$("#detail_button").html("<i class=\"pe-7s-more btn-icon-wrapper\" style=\"font-size:12pt;\"></i> 기본검색");
	} else {
		$("#div_detail_search").hide();
		$("#detail_button").html("<i class=\"pe-7s-keypad btn-icon-wrapper\" style=\"font-size:12pt;\"></i> 상세검색");
	}

}

function gfn_scrollbar_sidebar(divLeftWidth, vTopMrgn) {
	var sideWidth = $(".scrollbar-sidebar").width();
	var vMenuWidth = 308;
	if (Number(sideWidth) > 100) {
		vMenuWidth = 168;
	}
	gfn_splitGridResize(vMenuWidth, Number(vLeftWidth), vTopMrgn);
}

/** ********************************************** */
function gfn_splitGridResize2(gridLeftWidth) {
	
	var leftMenuWidth = 0;
	var gridWidth = window.innerWidth;

	if ($("#left_side_menu").css("width") != undefined) {
		leftMenuWidth = $("#left_side_menu").css("width").replace("px", "");
		gridWidth = gridWidth - 75;
	} else {
		gridWidth = gridWidth - 60;
		if (window.innerWidth > 2400) {
			gridWidth = 2365;
		}
	}

	var gridRightWidth = gridWidth - Number(gridLeftWidth) - Number(leftMenuWidth);

	if ($('#divLeftTitle').css("width") != undefined) {
		

		$("#tdLeftTitle").css("width", gridLeftWidth + "px");
		$("#divLeftTitle").css("width", gridLeftWidth + "px");
		$("#divLeftData").css("width", gridLeftWidth + "px");

		
		$("#divRightTitle").css("width", gridRightWidth + "px");
		$("#divRightData").css("width", gridRightWidth + "px");

		var gridTop = $("#gridTop").offset().top;
		var gridHeight = window.innerHeight - gridTop - 120;

		$("#divLeftData").css("height", gridHeight + "px");
		$("#divRightData").css("height", gridHeight + "px");
	}

}
function gfn_splitGridResize3(gridLeftWidth, gridMargin) {
	var leftMenuWidth = 0;
	var gridWidth = window.innerWidth;

	if ($("#left_side_menu").css("width") != undefined) {
		leftMenuWidth = $("#left_side_menu").css("width").replace("px", "");
		gridWidth = gridWidth - 75;
	} else {
		gridWidth = gridWidth - 60;
		if (window.innerWidth > 2400) {
			gridWidth = 2365;
		}
	}

	var gridRightWidth = gridWidth - Number(gridLeftWidth) - Number(leftMenuWidth);

	if ($('#divLeftTitle').css("width") != undefined) {
		

		$("#tdLeftTitle").css("width", gridLeftWidth + "px");
		$("#divLeftTitle").css("width", gridLeftWidth + "px");
		$("#divLeftData").css("width", gridLeftWidth + "px");

		
		$("#divRightTitle").css("width", gridRightWidth + "px");
		$("#divRightData").css("width", gridRightWidth + "px");

		var gridTop = $("#gridTop").offset().top;
		var gridHeight = window.innerHeight - gridTop - Number(gridMargin);

		$("#divLeftData").css("height", gridHeight + "px");
		$("#divRightData").css("height", gridHeight + "px");
	}

}
function gfn_splitGridResize(vMenuWidth, vLeftWidth, vTopMrgn) {

	vMenuWidth = $("#left_side_menu").css("width").replace("px", "");

	if (window.innerWidth < 800) {
		$("#divLeftData").css({
			'overflow' : 'auto'
		});
	}

	if (cfn_browserMode() == "PC") {

		$("#divLeftTitle").css("width", vLeftWidth + "px");
		$("#divLeftData").css("width", vLeftWidth + "px");
		$("#divRightTitle").css("width", (window.innerWidth - vLeftWidth - vMenuWidth) + "px");
		$("#divRightData").css("width", (window.innerWidth - vLeftWidth - vMenuWidth) + "px");

		$("#divLeftData").css("height", (window.innerHeight - vTopMrgn) + "px");
		$("#divRightData").css("height", (window.innerHeight - vTopMrgn) + "px");

	} else {

		$("#tdRightTitle").hide();
		$("#tdRightData").hide();

		$("#divLeftTitle").css("width", (window.innerWidth - 30) + "px");
		$("#divLeftData").css("width", (window.innerWidth - 30) + "px");
	}
}

function gfn_splitGridResize3(gridLeftWidth, gridHeightMargin) {
	var leftMenuWidth = 0;
	var gridWidth = window.innerWidth;

	if ($("#left_side_menu").css("width") != undefined) {
		leftMenuWidth = $("#left_side_menu").css("width").replace("px", "");
		gridWidth = gridWidth - 75;
	} else {
		gridWidth = gridWidth - 60;
		if (window.innerWidth > 2400) {
			gridWidth = 2365;
		}
	}

	var gridRightWidth = gridWidth - Number(gridLeftWidth) - Number(leftMenuWidth);

	if ($('#divLeftTitle').css("width") != undefined) {

		$("#divLeftTitle").css("width", gridLeftWidth + "px");
		$("#divLeftData").css("width", gridLeftWidth + "px");

		$("#divRightTitle").css("width", gridRightWidth + "px");
		$("#divRightData").css("width", gridRightWidth + "px");

		var gridTop = $("#gridTop").offset().top;
		var gridHeight = window.innerHeight - Number(gridHeightMargin);

		$("#divLeftData").css("height", gridHeight + "px");
		$("#divRightData").css("height", gridHeight + "px");
	}

}

function gfn_gridHeightFit() {

	var leftMenuWidth = 0;
	var gridWidth = window.innerWidth;
	var gridTop = $("#gridTop").offset().top;
	var gridLeftWidth = 500;
	if ($("#divLeftTitle").css("width") != undefined) {
		gridLeftWidth = $("#divLeftTitle").css("width").replace("px", "");
	}

	if ($("#left_side_menu").css("width") != undefined) {
		leftMenuWidth = $("#left_side_menu").css("width").replace("px", "");
		gridWidth = gridWidth - 65;
	} else {
		gridWidth = gridWidth - 50;
		if (window.innerWidth > 2400) {
			gridWidth = 2365;
		}
	}

	var gridRightWidth = gridWidth - Number(gridLeftWidth) - Number(leftMenuWidth);

	if ($('#divLeftTitle').css("width") != undefined) {

		$("#divLeftTitle").css("width", gridLeftWidth + "px");
		$("#divLeftData").css("width", gridLeftWidth + "px");

		$("#divRightTitle").css("width", gridRightWidth + "px");
		$("#divRightData").css("width", gridRightWidth + "px");

		if ($("#grid_height").attr('class') == "linearicons-line-spacing") {

			$("#divLeftData").css("height", (window.innerHeight - 210) + "px");
			$("#divRightData").css("height", (window.innerHeight - 210) + "px");
			$("#grid_height").removeClass('linearicons-line-spacing').addClass('linearicons-contract');

			window.scrollTo(0, gridTop - 100);
		} else {
			window.location.href = "#top";
			$("#divLeftData").css("height", (window.innerHeight - gridTop - 110) + "px");
			$("#divRightData").css("height", (window.innerHeight - gridTop - 110) + "px");
			$("#grid_height").removeClass('linearicons-contract').addClass('linearicons-line-spacing');
		}

	}

}

function gfn_gridHeightFit_pop() {

	if ($('#divLeftTitle') != null) {
		var gridTop = $("#gridTop").offset().top;

		if ($("#grid_height").attr('class') == "lnr-line-spacing") {
			// window.location.href="#gridTop";

			$("#divLeftData").css("height", (window.innerHeight - 130) + "px");
			$("#divRightData").css("height", (window.innerHeight - 130) + "px");
			$("#grid_height").removeClass('lnr-line-spacing').addClass('lnr-text-align-justify');

			window.scrollTo(0, gridTop - 180);
		} else {
			window.location.href = "#top";
			$("#divLeftData").css("height", (window.innerHeight - 260) + "px");
			$("#divRightData").css("height", (window.innerHeight - 260) + "px");
			$("#grid_height").removeClass('lnr-text-align-justify').addClass('lnr-line-spacing');
		}

	}
}

function gfn_process_view(vMessage) {

	var v_scrollTop = $(document).scrollTop();

	$("#processMsg_txt").html(vMessage);
	$("#processMsg").css("top", ($(window).height() - 100) / 2 + 100 + v_scrollTop);
	$("#processMsg").css("left", ($(window).width() - 500) / 2);
	$("#processMsg").show();
}
function gfn_process_hide() {
	$("#processMsg").hide();
}

function gfn_splistGridTdResize(){
	
	// 각 th에 드래그 핸들 추가
	  $("#RightTableHeader th").each(function (index) {
	    $(this).append('<div class="resize-handle"></div>');
	  });

	  let startX, startWidth, columnIndex;

	  // mousedown 이벤트
	  $(".resize-handle").on("mousedown", function (e) {
	    const $currentTh = $(this).parent(); // 현재 th 요소
	    columnIndex = $currentTh.index(); // 현재 열 인덱스
	    startX = e.pageX;
	    startWidth = $currentTh.outerWidth(); // 외부 너비 계산

	    // 마우스 이동 이벤트
	    $(document).on("mousemove.resizeTable", function (e) {
	      const newWidth = Math.max(startWidth + (e.pageX - startX), 20); // 최소 너비 20px

	      // 두 테이블의 열 너비를 동기화
	      $("#RightTableHeader th").eq(columnIndex).width(newWidth);
	      //$("#RightTableHeader td:nth-child(" + (columnIndex + 1) + ")").width(newWidth);
	      //$("#RightDataTable th").eq(columnIndex).width(newWidth);
	      $("#RightDataTable td:nth-child(" + (columnIndex + 1) + ")").width(newWidth);
	    });

	    // 마우스 버튼을 떼면 이벤트 해제
	    $(document).on("mouseup.resizeTable", function () {
	      $(document).off(".resizeTable");
	    });

	    // 이벤트 중단
	    e.preventDefault();
	  });
}

function gfn_syncHeaderColumnWith(){
	var columnWidths = [];
	var $table2 = $("#RightDataTable");
	
	
	$("#RightTableHeader th").each(function () {
	    columnWidths.push($(this).outerWidth()); // 또는 .width()
	  });
	
	$table2.find("td").each(function (index) {
	    if (columnWidths[index] !== undefined) {
	      $(this).css("width", columnWidths[index] + "px");
	    }
	});
	
	gfn_syncHeaderColumnWithLeft();
}
function gfn_syncHeaderColumnWithLeft(){
	var columnWidths = [];
	
	var $table3 = $("#LeftDataTable");
	$("#LeftTableHeader th").each(function () {
	    columnWidths.push($(this).outerWidth()); // 또는 .width()
	  });
	
	$table3.find("td").each(function (index) {
	    if (columnWidths[index] !== undefined) {
	      $(this).css("width", columnWidths[index] + "px");
	    }
	});
}
