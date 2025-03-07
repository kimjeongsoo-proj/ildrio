function pfn_modalClose() {
	$("#popupPageModalResize").modal("hide");
}

function pfn_popupPageModalResize_close() {
	$("#popupPageModalResize").modal("hide");
}
function pfn_popupPageSubModal_close() {
	$("#popupPageSubModal").modal("hide");
}
$(document).ready(function() {
	$(".datePickerInput").datepicker({
		format : 'yyyy-mm-dd',
		autoHide : true,
		zIndex : 9999
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

function pfn_header_menu(ssMenuGroup) {

	$("#header_menu").empty();

	var form_data = "groupCode=" + ssMenuGroup;
	form_data += "&srhMenuLevel=1";
	form_data += "&startRow=0";
	form_data += "&pageRow=9999";
	form_data += "&srhOrderBy=sort_path";

	$.ajax({
		url : '/sysMenu/sysMenuListAjax',
		type : 'post',
		data : form_data,
		dataType : 'json',
		async : true,
		success : function(data) {
			var list = data.rsList;
			var src = "";
			for (var i = 0; i < list.length; i++) {
				var linkUrl = cfnNull(list[i].linkUrl);

				if (linkUrl == "") {
					if (ssMenuGroup == "sysAdmin") {
						linkUrl = "/mallAdmin/home";
					} else {
						linkUrl = "/shopAdmin/home";
					}
				}
				var menuCode = cfnNull(list[i].defaultCode);
				if (menuCode == "") {
					menuCode = cfnNull(list[i].menuCode);
				}

				src += '<li class="nav-item" style="padding-right:30px;"> ';
				src += '<a href="' + linkUrl + '?menuCode=' + menuCode + '" style="color:#fff;font-size:12pt;">' + cfnNull(list[i].menuName) + '</a> ';
				src += '</li> ';
			}
			$("#header_menu").append(src);

		}
	});

}

function pfn_side_menu(ssMenuGroup, vMenuCode) {

	var form_data = "groupCode=" + ssMenuGroup;
	//form_data += "&menuCode=" + vMenuCode.substring(0, 2);
	form_data += "&startRow=0";
	form_data += "&pageRow=9999";
	form_data += "&srhOrderBy=code_path";

	//alert(form_data);

	$("#side_menu_nav").empty();

	$.ajax({
		url : '/sysMenu/sysMenuListAjax',
		type : 'post',
		data : form_data,
		dataType : 'json',
		async : false,
		success : function(data) {
			var list = data.rsList;
			for (var i = 0; i < list.length; i++) {
				if (list[i].menuLevel == "1") {
					
					var quaryStrAnd = "?";
					if (cfnNull(list[i].linkUrl).indexOf("?") > -1) {
						quaryStrAnd = "&";
					}
					var menuIcon = 'metismenu-icon pe-7s-rocket';
					if (cfnNull(list[i].menuIcon) != '') {
						menuIcon = cfnNull(list[i].menuIcon)
					}
					var src = "";
					
					if (list[i].subCount == "0") {
						src += '  <a href="' + cfnNull(list[i].linkUrl) + quaryStrAnd + 'menuCode=' + cfnNull(list[i].menuCode) + '" id="menu_' + cfnNull(list[i].menuCode) + '" > ';
						src += '<li class="app-sidebar__heading" style="font-size:11pt;">'
						src += '      ' + cfnNull(list[i].menuName) + ' ';
						src += '</li>';
						src += '  </a> ';
					}else{
						src += '<li class="app-sidebar__heading" style="font-size:11pt;">' + cfnNull(list[i].menuName) + '</li>';
						src += '<div id="sub1_' + cfnNull(list[i].menuCode) + '">';
						src += '</div>';
						src += '</li>';
					}
					
					$("#side_menu_nav").append(src);

				}

			}

			var chk_menuUpCode = "";
			for (var i = 0; i < list.length; i++) {

				if (list[i].menuLevel == "2") {
					var quaryStrAnd = "?";
					if (cfnNull(list[i].linkUrl).indexOf("?") > -1) {
						quaryStrAnd = "&";
					}
					var menuIcon = 'metismenu-icon pe-7s-rocket';
					if (cfnNull(list[i].menuIcon) != '') {
						menuIcon = cfnNull(list[i].menuIcon)
					}
					var src = "";
					if (list[i].subCount == "0") {
						
						if (list[i].targetType == "POPUP") {
							src += '<li>';
							src += '    <a data-bs-toggle="modal" data-bs-target="#popupPageModalResize"  onclick="pfn_modalPop(\'popMargin\', 10, 10, \'' + cfnNull(list[i].menuName) + '\' ,\'' + cfnNull(list[i].linkUrl) + quaryStrAnd + 'menuCode=' + cfnNull(list[i].menuCode) + '\');"  style="cursor:pointer;"> ';
							src += '      <i class="metismenu-icon pe-7s-rocket"></i> ';
							src += '    	' + cfnNull(list[i].menuName) + '';
							src += '    </a>';
							src += '</li>';
						}else{
							src += '<li>';
							src += '  <a href="' + cfnNull(list[i].linkUrl) + quaryStrAnd + 'menuCode=' + cfnNull(list[i].menuCode) + '" id="menu_' + cfnNull(list[i].menuCode) + '" > ';
							src += '      <i class="metismenu-icon pe-7s-rocket"></i> ';
							src += '      ' + cfnNull(list[i].menuName) + ' ';
							src += '  </a> ';
							src += '</li>';
						}
					} else {
						src += '<li id="menu_' + cfnNull(list[i].menuCode) + '">';
						src += '  <a  aria-expanded="true"> ';
						src += '      <i class="metismenu-icon pe-7s-rocket"></i> ';
						src += '      ' + cfnNull(list[i].menuName) + ' ';
						src += '      <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i> ';
						src += '  </a> ';
						src += '  <ul class="mm-collapse" id="sub2_' + cfnNull(list[i].menuCode) + '">';
						src += '  </ul> ';
						src += '</li>';
					}

					$("#sub1_" + list[i].menuUpCode).append(src);
				}
			}

			for (var i = 0; i < list.length; i++) {

				if (list[i].menuLevel == "3") {
					var quaryStrAnd = "?";
					if (cfnNull(list[i].linkUrl).indexOf("?") > -1) {
						quaryStrAnd = "&";
					}

					var src = "";
					//if (list[i].subCount == "0") {
					src += '<li>';
					src += '	<a href="' + cfnNull(list[i].linkUrl) + quaryStrAnd + 'menuCode=' + cfnNull(list[i].menuCode) + '" id="menu_' + cfnNull(list[i].menuCode) + '">';
					src += '		' + cfnNull(list[i].menuName) + '</i>';
					src += '	</a>';
					src += '</li>';
					//} else {
					//src += '<li id="menu_' + cfnNull(list[i].menuCode) + '">';
					//src += '	<a  style="font-size:9pt;">';
					//src += '		' + cfnNull(list[i].menuName) + ' <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>';
					//src += '	</a>';
					//src += '	<ul class="mm-collapse" id="sub4_' + cfnNull(list[i].menuCode) + '">';
					//src += '	</ul> ';
					//src += '</li>';
					//}
					$("#sub2_" + list[i].menuUpCode).append(src);
				}
				//alert(src);
			}

			for (var i = 0; i < list.length; i++) {
				var menuCode = cfnNull(list[i].defaultCode);
				if (menuCode == "") {
					menuCode = cfnNull(list[i].menuCode);
				}
				var quaryStrAnd = "?";
				if (cfnNull(list[i].linkUrl).indexOf("?") > -1) {
					quaryStrAnd = "&";
				}
				if (list[i].menuLevel == "4") {
					var src = "";
					if (list[i].subCount == "0") {
						src += '<li>';
						src += '	<a href="' + cfnNull(list[i].linkUrl) + quaryStrAnd + 'menuCode=' + cfnNull(list[i].menuCode) + '" id="menu_' + cfnNull(list[i].menuCode) + '">';
						src += '		' + cfnNull(list[i].menuName) + '</i>';
						src += '	</a>';
						src += '</li>';
					} else {
						src += '<li id="menu_' + cfnNull(list[i].menuCode) + '">';
						src += '	<a href="' + cfnNull(list[i].linkUrl) + quaryStrAnd + 'menuCode=' + menuCode + '" style="font-size:9pt;">';
						src += '		' + cfnNull(list[i].menuName) + ' <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>';
						src += '	</a>';
						src += '	<ul class="mm-collapse" id="sub5_' + cfnNull(list[i].menuCode) + '">';
						src += '	</ul> ';
						src += '</li>';
					}
					$("#sub3_" + list[i].menuUpCode).append(src);
				}
			}

			for (var i = 0; i < list.length; i++) {
				var menuCode = cfnNull(list[i].defaultCode);
				if (menuCode == "") {
					menuCode = cfnNull(list[i].menuCode);
				}
				var quaryStrAnd = "?";
				if (cfnNull(list[i].linkUrl).indexOf("?") > -1) {
					quaryStrAnd = "&";
				}
				if (list[i].menuLevel == "6") {
					var src = "";
					src += '		<li>';
					src += '			<a href="' + cfnNull(list[i].linkUrl) + quaryStrAnd + 'menuCode=' + cfnNull(list[i].menuCode) + '" id="menu_' + cfnNull(list[i].menuCode) + '">';
					src += '				' + cfnNull(list[i].menuName) + '';
					src += '			</a>';
					src += '		</li> ';
					$("#sub4_" + list[i].menuUpCode).append(src);
				}
			}

			// 메뉴 포커스
			pfn_select_menu(vMenuCode);

		}
	});

}

function pfn_select_menu(menuCode) {

	// menuCode = menuCode.replace("#","");
	var codeLevel = menuCode.length / 2;
	var sel_menuCode_path = "";
	for (var i = 2; i <= codeLevel; i++) {
		var sel_menuCode = menuCode.substring(0, i * 2);
		$("#menu_" + sel_menuCode).addClass('mm-active');
		sel_menuCode_path += ">" + sel_menuCode;
	}
	//alert(sel_menuCode_path);

}

/*******************************************************************************
 * admin LTE 모달 사이즈 2022.12.9 김정수
 ******************************************************************************/
function pfn_modalPop(vPopMode, vWidth, vHeight, vTitle, vUrl) {

	var popTop = 0;
	var popLeft = 0;

	if (vPopMode == "popSize") {

		if (vHeight > window.innerHeight) {
			vHeight = window.innerHeight - 30;
		}
		if (vWidth > window.innerWidth) {
			vWidth = window.innerWidth - 30;
		}
		popTop = (window.innerHeight - vHeight) / 2;
		popLeft = (window.innerWidth - vWidth) / 2;

	}
	if (vPopMode == "popMargin") {

		popTop = vHeight / 2;
		popLeft = vWidth / 2;

		vHeight = window.innerHeight - vHeight - 25;
		vWidth = window.innerWidth - vWidth - 15;

		if (vWidth > 1500) {
			// vWidth = 1500;
			// popLeft = (window.innerWidth - vWidth) /2;
		}
	}

	if (window.innerWidth < 860) {

		popTop = 0;
		popLeft = 0;

		vHeight = window.innerHeight - 20;
		vWidth = window.innerWidth - 20;
	}

	$("#popupPageModalResize").css("top", popTop + "px");
	$("#popupPageModalResize").css("left", popLeft + "px");
	$("#modal_resize_contents").css("height", vHeight + "px");
	$("#modal_resize_contents").css("width", vWidth + "px");

	$("#modal_resize_title").html(vTitle);
	$("#modal_resize_iframe").css("height", (vHeight - 110) + "px");

	document.modal_resize_iframe.location.href = vUrl;
}

function pfn_sub_modalPop(vPopMode, vWidth, vHeight, vTitle, vUrl) {

	var popTop = 0;
	var popLeft = 0;

	if (vPopMode == "popSize") {

		if (vHeight > window.innerHeight) {
			vHeight = window.innerHeight - 30;
		}
		if (vWidth > window.innerWidth) {
			vWidth = window.innerWidth - 30;
		}
		popTop = (window.innerHeight - vHeight) / 2;
		popLeft = (window.innerWidth - vWidth) / 2;

	}
	if (vPopMode == "popMargin") {

		popTop = vHeight / 2;
		popLeft = vWidth / 2;

		vHeight = window.innerHeight - vHeight - 15;
		vWidth = window.innerWidth - vWidth - 15;

		if (vWidth > 1500) {
			// vWidth = 1500;
			// popLeft = (window.innerWidth - vWidth) /2;
		}
	}

	if (window.innerWidth < 860) {

		popTop = 0;
		popLeft = 0;

		vHeight = window.innerHeight - 20;
		vWidth = window.innerWidth - 20;
	}

	$("#popupPageSubModal").css("top", popTop + "px");
	$("#popupPageSubModal").css("left", popLeft + "px");

	$("#sub_modal_contents").css("height", vHeight + "px");
	$("#sub_modal_contents").css("width", vWidth + "px");

	$("#sub_modal_title").html(vTitle);

	// $("#sub_modal_contents").css("top", popTop + "px");
	// $("#sub_modal_contents").css("left", popLeft + "px");

	$("#sub_modal_dialog").css("height", window.innerHeight + "px");
	$("#sub_modal_dialog").css("width", window.innerWidth + "px");

	$("#sub_modal_iframe").css("height", (vHeight - 110) + "px");

	document.sub_modal_iframe.location.href = vUrl;
}

function pfn_detailSearchPop(vWidth, vHeight) {

	var popTop = (window.innerHeight - vHeight) / 2;
	var popLeft = (window.innerWidth - vWidth) / 2;

	$("#detailSearchModal").css("top", popTop + "px");
	$("#detailSearchModal").css("left", popLeft + "px");

	$("#detailSearchContents").css("height", vHeight + "px");
	$("#detailSearchContents").css("width", vWidth + "px");

}

function pfn_detailSearchReset() {
	$('#deatilSearchForm')[0].reset();
}
