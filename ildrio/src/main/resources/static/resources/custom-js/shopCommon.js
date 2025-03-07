//헤더카테고리
function fn_headerCate() {
	var menuHeight = 0;
	var subHeight = "";
	if (cfn_browserMode() == "PC") {
		menuHeight = -47;
		subHeight = "height:500px";
	}

	$("#shopCateList").empty();
	$.ajax({
		url : '/mallCate/shopCateListAjax',
		type : 'post',
		data : {},
		dataType : 'json',
		async : true,
		success : function(data) {

			var chk_cate1 = "";
			var chk_cate2 = "";
			var list = data.rsList;
			var cateCnt = 0;
			for (var i = 0; i < list.length; i++) {
				if (chk_cate1 != list[i].cateCode1) {
					var shtml1 = "";
					shtml1 += '<li class="dropdown dropdown-mega-menu cateName1"> ';
					shtml1 += '	<a class="dropdown-item nav-link dropdown-toggler" data-bs-toggle="dropdown"> ';
					shtml1 += '		<i class="flaticon-tv"></i> ';
					shtml1 += '		<span style="cursor: pointer; font-size: 12pt; color: #666; font-weight: bold;">' + cfnNull(list[i].cateName1) + ' </span> ';
					shtml1 += '	</a> ';
					shtml1 += '	<div class="dropdown-menu" style="top: ' + (cateCnt * menuHeight) + 'px; height: 500px; over-flow-x:hidden;overflow-y: auto;"> ';
					shtml1 += '		<ul class="mega-menu d-lg-flex"> ';
					shtml1 += '			<li class="mega-menu-col col-lg-12"> ';
					shtml1 += '				<ul class="d-lg-flex" id="dropdown_' + cfnNull(list[i].cateCode1) + '> ';
					shtml1 += '				</ul>';
					shtml1 += '			</li>';
					shtml1 += '		</ul>';
					shtml1 += '	</div>';
					shtml1 += '</li> ';

					chk_cate1 = list[i].cateCode1;
					$("#shopCateList").append(shtml1);
					cateCnt++;
					goodsCount1 = 0;
					goodsCount2 = 0;
				}

				if (chk_cate2 != list[i].cateCode2) {
					var shtml2 = "";
					shtml2 += '	<li style="padding:5px 20px;">';
					shtml2 += '		<a  href="/shopGoods/cateGoodsList?cateLevel=2&cateCode=' + cfnNull(list[i].cateCode2) + '" style="font-size:12pt;">' + cfnNull(list[i].cateName2) + '</a> ';
					shtml2 += '	</li> ';

					$("#dropdown_" + cfnNull(list[i].cateCode1)).append(shtml2);
					chk_cate2 = list[i].cateCode2;
					goodsCount2 = 0;
				}

			}

		}
	});

}

// 헤더 장바구니 정보
function fn_header_count() {
	$("#header_cart_list").empty();
	$.ajax({
		url : '/shopCart/shopCartListAjax',
		type : 'post',
		data : {
			"cartMode" : "CART"
		},
		dataType : 'json',
		async : true,
		success : function(data) {
			var goodsTotal = 0;
			var goodsQuantity = 0;
			var html = "";
			var list = data.rsList;
			for (var i = 0; i < list.length; i++) {
				html += '<li> ';
				html += '	<a href="/shopGoods/shopGoodsView/' + list[i].goodsId + '"> ';
				html += '		<img src="' + list[i].goodsImage1 + '" alt="product1">';
				html += '		' + list[i].goodsName + ' ';
				html += '	</a> ';
				html += '	<span class="cart_quantity"> ';
				html += '		' + list[i].goodsQuantity + ' ';
				html += '		<span class="cart_amount"> ';
				html += '			<span class="price_symbole"></span> ';
				html += '		</span> ';
				html += '		' + cfnFmtMoney(list[i].sobiPrice) + ' ';
				html += '		(' + cfnFmtMoney(list[i].goodsQuantity) + '개) ';
				html += '	</span> ';
				html += '</li> ';

				goodsQuantity = goodsQuantity + Number(list[i].goodsQuantity);
				goodsTotal = goodsTotal + (Number(list[i].sobiPrice) * Number(list[i].goodsQuantity));

			}
			if (list.length == 0) {
				html += '<li style="center;"> </li>';

			}
			$("#header_cart_list").append(html);
			if (goodsQuantity > 0) {

				$("#header_cart_list").show();
				$("#header_cart_button").show();
				$("#cart_goodsQuantity").show();
				$("#cart_goodsQuantity").html(cfnFmtMoney(goodsQuantity));
				$("#cart_total").html(cfnFmtMoney(goodsTotal) + "원");
			}
		}
	});
}

// 장바구니 담기
function fn_addCart(vGgoodsId, vGoodsName, vGoodsQuantity) {

	var imgurl = $("#goods_img_" + vGgoodsId).attr("src")
	$("#cartModal_goods_img").attr("src", imgurl);
	$("#cartModal_goods_name").html(vGoodsName);

	$.ajax({
		url : '/shopCart/shopCartTrxn',
		type : 'post',
		data : {
			"trxnType" : "insert",
			"cartMode" : "CART",
			"goodsId" : vGgoodsId,
			"goodsQuantity" : vGoodsQuantity
		},
		dataType : 'json',
		async : false,
		success : function(data) {
			// 모달 열기
			$('#cartModal').modal('show');
			// 헤더 카트
			fn_header_count();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("실패하였습니다.");
		}
	});
}

// 관심상품 담기
function fn_addWish(vGgoodsId, vGoodsName) {

	var imgurl = $("#goods_img_" + vGgoodsId).attr("src")
	$("#wishModal_goods_img").attr("src", imgurl);
	$("#wishModal_goods_name").html(vGoodsName);

	$.ajax({
		url : '/shopWish/shopWishGoodsTrxn',
		type : 'post',
		data : {
			"trxnType" : "insert",
			"goodsId" : vGgoodsId
		},
		dataType : 'json',
		async : false,
		success : function(data) {

			// 모달 열기
			$('#wishModal').modal('show');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("실패하였습니다.");
		}
	});
}

// shop 정보 header footer
function fn_common_shopInfo() {
	$.ajax({
		url : '/shopInfo/shopInfoViewAjax',
		type : 'post',
		data : {},
		dataType : 'json',
		async : false,
		success : function(data) {
			//$("#footer_shopAddress").html(data.rsMap.shopName);
		}
	});
}

