function sfn_goodsQuantity(vOption) {
	var goodsQuantity = Number($("#goodsQuantity").val());
	if (vOption == "plus") {
		goodsQuantity = goodsQuantity + 1;
	}
	if (vOption == "minus") {
		goodsQuantity = goodsQuantity - 1;
	}
	if (goodsQuantity < 1) {
		goodsQuantity = 1;
	}
	$("#goodsQuantity").val(goodsQuantity);

	sfn_goods_amount();

}

function sfn_goods_addCart(optionLevel, optionRequiredYn) {
	// var optionLevel = Number("[[${rsMap.optionLevel}]]");
	// var optionRequiredYn = "[[${rsMap.optionRequiredYn}]]";

	// alert(optionRequiredYn);

	if (optionRequiredYn == "Y") {
		for (var i = 1; i <= optionLevel; i++) {
			if ($("#optionValue" + i).val() == "") {
				alert("옵션을 선택해 주세요");
				$("#optionValue" + i).focus();
				return false;
			}
		}
	}
	var vGoodsQuantity = $("#goodsQuantity").val();
	var vOptionId = $("#optionId").val();
	// alert(vOptionId);
	// fn_addCart("[[${rsMap.goodsId}]]", "[[${rsMap.goodsName}]]",
	// vGoodsQuantity, vOptionId);
}

function sfn_shopGoodsOptionGroup(vNextLevel) {
	var goodsId = $("#goodsId").val()
	var optionLastLevel = Number($("#optionLevel").val());
	$("#optionId").val("");

	if (optionLastLevel > 0) {

		// 최종단계 선택한 경우
		if (Number(vNextLevel) > optionLastLevel) {
			$("#optionId").val($("#optionValue" + optionLastLevel).val().split("^")[1]);
			$("#optionPrice").val($("#optionValue" + optionLastLevel).val().split("^")[2]);

			sfn_goods_amount();
		}

		for (i = vNextLevel; i <= optionLastLevel; i++) {
			if ($("#optionValue" + i) != null) {
				$("#optionValue" + i).empty();
			}
		}

		var form_data = "goodsId=" + goodsId;
		form_data += "&optionLevel=" + vNextLevel;
		form_data += "&optionLastLevel=" + optionLastLevel;
		for (i = 1; i < Number(vNextLevel); i++) {
			if ($("#optionValue" + i) != null) {
				form_data += "&optionValue" + i + "=" + $("#optionValue" + i).val();
			}
		}

		$.ajax({
			url : '/shopGoods/shopGoodsOptionGroupAjax',
			type : 'post',
			data : form_data,
			dataType : 'json',
			async : true,
			success : function(data) {

				var list = data.rsList;
				var src = '<option value="" selected>옵션을 선택하세요</option>';
				for (var i = 0; i < list.length; i++) {

					if (optionLastLevel == vNextLevel) {
						src += '<option value="' + list[i].optionValue + '^' + list[i].optionId + '^' + list[i].optionPrice + '">' + list[i].optionValue + ' ( +' + cfnFmtMoney(list[i].optionPrice) + '원)</option>';
					} else {
						src += '<option value="' + list[i].optionValue + '">' + list[i].optionValue + '</option>';
					}

				}
				$("#optionValue" + vNextLevel).append(src);

			}
		});

	}
}

function sfn_goods_amount() {
	var basePrice = Number($("#basePrice").val());
	var optionPrice = Number($("#optionPrice").val());
	var goodsQuantity = Number($("#goodsQuantity").val());
	var goodsAmount = (Number(basePrice) + optionPrice) * goodsQuantity;

	$("#goodsAmount").html(cfnFmtMoney(goodsAmount) + "원");
}

function sfn_goodsView_tab(objectId) {

	src = "<thead><tr>";
	if (objectId == "goodsDetail") {
		src += '<th style="width:120px;text-align:center;font-size:13pt;border-bottom:3px solid #aaa;"> ';
		src += '	<a href="#goodsDetail" style="font-weight:bold;color:#000;">상품상세</a> ';
		src += '</th> ';
	} else {
		src += '<th style="width:120px;text-align:center;font-size:13pt;"> ';
		src += '	<a href="#goodsDetail">상품상세</a> ';
		src += '</th> ';
	}
	if (objectId == "deliveryInfo") {
		src += '<th style="width:120px;text-align:center;font-size:13pt;border-bottom:3px solid #aaa;"> ';
		src += '	<a href="#deliveryInfo" style="font-weight:bold;color:#000;">배송정보</a> ';
		src += '</th> ';
	} else {
		src += '<th style="width:120px;text-align:center;font-size:13pt;"> ';
		src += '	<a href="#deliveryInfo">배송정보</a> ';
		src += '</th> ';
	}
	if (objectId == "returnInfo") {
		src += '<th style="width:150px;text-align:center;font-size:13pt;border-bottom:3px solid #aaa;"> ';
		src += '	<a href="#returnInfo" style="font-weight:bold;color:#000;">반품교환 정보</a> ';
		src += '</th> ';
	} else {
		src += '<th style="width:150px;text-align:center;font-size:13pt;"> ';
		src += '	<a href="#returnInfo">반품교환 정보</a> ';
		src += '</th> ';
	}
	if (objectId == "goodsReview") {
		src += '<th style="width:120px;text-align:center;font-size:13pt;border-bottom:3px solid #aaa;"> ';
		src += '	<a href="#goodsReview" style="font-weight:bold;color:#000;">상품후기</a> ';
		src += '</th> ';
	} else {
		src += '<th style="width:120px;text-align:center;font-size:13pt;"> ';
		src += '	<a href="#goodsReview">상품후기</a> ';
		src += '</th> ';
	}
	if (objectId == "goodsQna") {
		src += '<th style="width:120px;text-align:center;font-size:13pt;border-bottom:3px solid #aaa;"> ';
		src += '	<a href="#goodsQna" style="font-weight:bold;color:#000;">상품문의</a> ';
		src += '</th> ';
	} else {
		src += '<th style="width:120px;text-align:center;font-size:13pt;"> ';
		src += '	<a href="#goodsQna">상품문의</a> ';
		src += '</th> ';
	}
	src += '<th></th> ';
	src += '</tr></thead> ';

	$("#tab_" + objectId).html(src);

}

function sfn_stickyTab() {

	var goodsDetailTop = $('#goodsDetail').offset().top;
	var deliveryInfoTop = $('#deliveryInfo').offset().top;
	var returnInfoTop = $('#returnInfo').offset().top;
	var goodsReviewTop = $('#goodsReview').offset().top;
	var goodsQnaTop = $('#goodsQna').offset().top;

	$(window).scroll(function() {

		$("#span_goodsDetailTop").html(goodsDetailTop + "px");

		if ($(window).scrollTop() >= (goodsDetailTop + 70)) {
			$('#sticky-tab').css({
				top : '70px',
				position : 'fixed'
			});
		} else {
			$('#sticky-tab').css({
				position : 'relative',
				top : '0px'	
			});
		}

		if (($(window).scrollTop() >= goodsDetailTop - 150) && ($(window).scrollTop() < deliveryInfoTop - 150)) {
			$('#tab_goodsDetail').css('font-weight', '700');
		} else {
			$('#tab_goodsDetail').css('font-weight', '400');
		}
		if (($(window).scrollTop() >= deliveryInfoTop - 150) && ($(window).scrollTop() < returnInfoTop - 150)) {
			$('#tab_deliveryInfo').css('font-weight', '700');
		} else {
			$('#tab_deliveryInfo').css('font-weight', '400');
		}
		if (($(window).scrollTop() >= returnInfoTop - 150) && ($(window).scrollTop() < goodsReviewTop - 150)) {
			$('#tab_returnInfo').css('font-weight', '700');
		} else {
			$('#tab_returnInfo').css('font-weight', '400');
		}
		if (($(window).scrollTop() >= goodsReviewTop - 150) && ($(window).scrollTop() < goodsQnaTop - 150)) {
			$('#tab_goodsReview').css('font-weight', '700');
		} else {
			$('#tab_goodsReview').css('font-weight', '400');
		}
		if (($(window).scrollTop() >= goodsQnaTop - 150)) {
			$('#tab_goodsQna').css('font-weight', '700');
		} else {
			$('#tab_goodsQna').css('font-weight', '400');
		}
	});
}

// 상품상세 - 상품문의
function sfn_goodsqna_list(vGoodsId) {
	var formData = "goodsId=" + vGoodsId;

	$.ajax({
		url : '/goodsQna/goodsQnaListAjax',
		type : 'post',
		data : formData,
		dataType : 'json',
		async : true,
		success : function(data) {
			$("#tbody_qna_list").empty();
			var list = data.rsList;
			var src = "";
			var k = 0;
			for (var i = 0; i < list.length; i++) {

				src += '<tr style="height:60px;"> ';
				src += '  <td style="width:150px;white-space:normal;text-align:left;"> ';
				src += '    ' + cfnNull(list[i].memberName) + '';
				src += '  </td>';
				src += '  <td style="width:;white-space:normal;text-align:left;"> ';
				src += '    ' + cfnNull(list[i].questionContents) + '';
				src += '  </td>';
				src += '  <td style="width:150px;white-space:normal;"> ';
				src += '    ' + cfnFmtDate(list[i].questionDatetime, ".") + '';
				src += '  </td>';
				src += '</tr> ';

				k++;
			}

			if (src == "") {
				src += '<tr style="height:60px;"> ';
				src += '	<td style="width:;white-space:normal;text-align:center;"> ';
				src += '		등록된 상품문의가 없습니다.';
				src += ' 	</td>';
				src += '</tr> ';
			}

			$("#tbody_qna_list").append(src);

		}
	});

}

function sfn_goodsreview_list(vGoodsId) {
	var formData = "goodsId=" + vGoodsId;
	$.ajax({
		url : '/goodsReview/goodsReviewListAjax',
		type : 'post',
		data : formData,
		dataType : 'json',
		async : true,
		success : function(data) {
			$("#tbody_review_list").empty();
			var list = data.rsList;
			var src = "";
			var k = 0;

			for (var i = Number(data.startRow); i < Number(data.endRow); i++) {

				src += '<tr style="height:60px;text-align:center;"> ';
				src += '  <td style="width:150px;white-space:normal;text-align:left;"> ';
				src += '    ' + cfnNull(list[i].memberName) + '';
				src += '  </td>';
				src += '  <td style="width:100px;"> ';
				src += '	<div class="rating_wrap"> ';
				src += '		<div class="rating"> ';
				src += '			<div class="product_rate" style="width: ' + Number(list[i].reviewRate) * 20 + '%"></div>';
				src += '		</div> ';
				src += '		<span class="rating_num"></span> ';
				src += '	</div> ';
				src += '  </td>';
				src += '  <td style="width:;white-space:normal;text-align:left;"> ';
				src += '    ' + cfnNull(list[i].reviewContents) + '';
				src += '  </td>';
				src += '  <td style="width:150px;white-space:normal;"> ';
				src += '    ' + cfnFmtDate(list[i].insertDatetime, ".") + '';
				src += '  </td>';
				src += '</tr> ';

				k++;
			}
			if (src == "") {
				src += ' <tr style="height:60px;"> ';
				src += '		<td style="width:;white-space:normal;text-align:center;"> ';
				src += '			등록된 상품리뷰가 없습니다.';
				src += ' 		</td>';
				src += '</tr> ';
			}

			$("#tbody_review_list").append(src);

		}
	});

}
