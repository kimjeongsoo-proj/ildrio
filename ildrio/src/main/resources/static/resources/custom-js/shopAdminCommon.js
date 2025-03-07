function sfn_modalClose() {
	$('#modal_close').trigger("click");
}

$(document).ready(function() {
	$(".datePickerInput").datepicker({
		format : 'yyyy-mm-dd',
		autoHide : false
	});
	

	$(".emailFormat").inputmask({
		alias : "email"
	});
	$(".telNoFormat").inputmask("9{1,3}-9{1,4}-9{1,4}");
	$(".mobileNumberFormat").inputmask("999-9999-9999");
	$(".bizNoFormat").inputmask("999-99-9999");
	$(".integerFormat").inputmask({
		alias : "integer"
	});
	$(".percentageFormat").inputmask({
		alias : "percentage"
	});
	$('.moneyFormat').css('imeMode', 'disabled').keypress(function(event) {
		if (event.which && (event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	}).keyup(function() {
		if ($(this).val() != null && $(this).val() != '') {
			var tmps = $(this).val().replace(/[^0-9]/g, '');
			if (tmps.substring(0, 1) == "0") {
				tmps = tmps.substring(1, tmps.length);
			}
			var tmps2 = tmps.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
			$(this).val(tmps2);
		}
	});

});

function sfn_shopAdmin_menu(ssMenuGroup) {

	$("#shopAdmin_menu").empty();

	var form_data = "srhGroupCode=" + ssMenuGroup;
	form_data += "&srhMenuLevel=1";
	form_data += "&startRow=0";
	form_data += "&pageRow=9999";
	form_data += "&srhOrderBy=menu_code";

	$.ajax({
		url : '/sysMenu/sysMenuListAjax',
		type : 'post',
		data : form_data,
		dataType : 'json',
		async : true,
		success : function(data) {
			var list = data.rsList;
			var src1 = "";
			for (var i = 0; i < list.length; i++) {
				var linkUrl = cfnNull(list[i].linkUrl);

				if (linkUrl == "") {
					if (ssMenuGroup == "mainAdmin") {
						linkUrl = "/mallAdmin/home";
					} else {
						linkUrl = "/shopAdmin/home";
					}
				}
				var menuCode = cfnNull(list[i].defaultCode);
				if (menuCode == "") {
					menuCode = cfnNull(list[i].menuCode);
				}

				src1 += '<li class="dropdown dropdown-mega-menu"> ';
				src1 += '	<a class="dropdown-toggle nav-link" href="#" data-bs-toggle="dropdown" style="font-weight:bold;font-size:11pt;">' + cfnNull(list[i].menuName) + '</a> ';
				src1 += '	<div class="dropdown-menu" style="max-height:400px;overflow:auto;">';
				src1 += '		<ul class="mega-menu d-lg-flex" id="menu1_' + cfnNull(list[i].menuCode) + '"> ';
				src1 += '		</ul> ';
				src1 += '	</div> ';
				src1 += '</li> ';

				sfn_shopAdmin_subMenu(ssMenuGroup, cfnNull(list[i].menuCode));
			}
			$("#shopAdmin_menu").append(src1);

		}
	});

}

function sfn_shopAdmin_subMenu(ssMenuGroup, menuCode) {

	var form_data = "srhGroupCode=" + ssMenuGroup;
	form_data += "&srhMenuCode1=" + menuCode.substring(0, 4);
	form_data += "&startRow=0";
	form_data += "&pageRow=9999";
	form_data += "&srhOrderBy=menu_code";

	$("#menu1_" + menuCode).empty();

	$.ajax({
		url : '/sysMenu/sysMenuListAjax',
		type : 'post',
		data : form_data,
		dataType : 'json',
		async : true,
		success : function(data) {
			var list = data.rsList;
			var chk_menuCode2 = ""
			var shtm2 = "";
			for (var i = 0; i < list.length; i++) {

				var pageMenuCode = cfnNull(list[i].defaultCode);
				if (pageMenuCode == "") {
					pageMenuCode = cfnNull(list[i].menuCode);
				}

				var quaryStrAnd = "?";
				if (cfnNull(list[i].linkUrl).indexOf("?") > -1) {
					quaryStrAnd = "&";
				}
				var pageLinkUrl = cfnNull(list[i].linkUrl) + quaryStrAnd + 'menuCode=' + pageMenuCode;
				if (list[i].subCount != "0") {
					pageLinkUrl = "#";
				}

				if (list[i].menuLevel == "2") {

					if (chk_menuCode2 != cfnNull(list[i].menuCode)) {
						if (chk_menuCode2 != "") {
							shtm2 += '	</ul>';
							shtm2 += '</li>';
						}
						shtm2 += '<li class="mega-menu-col col-lg-2">';
						shtm2 += '	<ul id="sub2_' + cfnNull(list[i].menuCode) + '">';

						chk_menuCode2 = cfnNull(list[i].menuCode);
					}

				}

				if (list[i].menuLevel == "2") {
					shtm2 += '<li class="dropdown-header" style="padding:0px;">';
					shtm2 += '<a class="dropdown-item nav-link nav_item" href="' + pageLinkUrl + '" style="font-size:12pt; font-weight:bold;">' + cfnNull(list[i].menuName) + '</a>';
					shtm2 += '</li>';
				}
				if (list[i].menuLevel == "3") {
					shtm2 += '<li style="padding:0px;">';
					shtm2 += '<a class="dropdown-item nav-link nav_item" href="' + pageLinkUrl + '" style="padding:3px 20px; ">' + cfnNull(list[i].menuName) + '</a>';
					shtm2 += '</li>';
				}
				if (list[i].menuLevel == "4") {
					shtm2 += '<li style="padding-left:20px;">';
					shtm2 += '<a class="dropdown-item nav-link nav_item" href="' + pageLinkUrl + '" style="padding:3px 20px;color:darkblue;">' + cfnNull(list[i].menuName) + '</a>';
					shtm2 += '</li>';
				}
			}
			shtm2 += '	</ul>';
			shtm2 += '</li>';
			$("#menu1_" + menuCode).append(shtm2)
		}
	});

}

function sfn_detailSearchView() {
	var detail_icon = $("#detail_icon").attr('class');

	if (detail_icon == "linearicons-chevron-down") {
		$("#detail_icon").attr('class', 'linearicons-chevron-up');
		$("#div_listData").attr('class', 'col-lg-10');
		$("#div_detailSearch").show();
	} else {
		$("#div_listData").attr('class', 'col-lg-12');
		$("#detail_icon").attr('class', 'linearicons-chevron-down');
		$("#div_detailSearch").hide();
	}
}

function sfn_modalOpen(vPageUrl, vTitle,popHeight) {
	$("#popupPageModalTitle").html("<b>" + vTitle + "</b>");
	document.modal_iframe.location.href = vPageUrl;
	
	if( Number(popHeight) > (window.innerHeight - 50) ){
		popHeight = window.innerHeight - 50;
	}
	
	$("#popupPageContent").css("height",popHeight + "px");
	$("#modal_iframe").css("height",(Number(popHeight) - 100) + "px");
}
