function sfn_shopOpmkUser_list() {
	var formData = $(document.pageSearchFrom).serialize();
	formData += "&" + $(document.totalSearchForm).serialize();
	formData += "&" + $(document.detailSearchForm).serialize();

	$.ajax({
		url : '/shopOpmk/shopOpmkUser/shopOpmkUserListAjax',
		type : 'post',
		data : formData,
		dataType : 'json',
		async : true,
		success : function(data) {
			sfn_shopOpmkUser_disp(data);
		}
	});

}

function sfn_shopOpmkUser_disp(data) {
	$("#footer_opmkUser").empty();
	var list = data.rsList;
	var src = "";
	var k = 0;
	for (var i = Number(data.startRow); i < Number(data.endRow); i++) {

		

		k++;
	}
	$("#footer_opmkUser").append(src);

}
