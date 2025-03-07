function cfn_CmmCdSrc(codeId) {
	var src = "";
	if (codeId == "admin_confirm_state") {
		src = "♥REQ^승인요청";
		src += "♥PEND^승인보류";
		src += "♥OK^승인완료";
		src += "♥CANCEL^승인취소";
	}

	if (codeId == "adm_shop_stat") {
		src = "♥NEW^신규신청";
		src += "♥USE^사용";
		src += "♥STOP^사용중지";
		src += "♥DENY^승인거부";
		src += "♥PEND^승인보류";
	}

	if (codeId == "adm_sply_stat") {
		src = "♥USE^사용중";
		src += "♥STOP^사용중지";
	}

	if (codeId == "best_goods_rate") {
		src = "♥5^5";
		src += "♥4^4";
		src += "♥3^3";
		src += "♥2^2";
		src += "♥1^1";
	}

	if (codeId == "collection_type") {
		src = "♥MAIN_RECOMMEND^메인 추천상품";
		src += "♥EVENT_COLLECTION^이벤트 모음전";
	}

	if (codeId == "delivery_cost_type") {
		src = "♥FREE^배송비 무료";
		src += "♥EACH^개별배송비";
		src += "♥MERGE^묶음배송비";
	}

	if (codeId == "dlvry_place_cd") {
		src = "♥ILIKE^아이라이크 물류";
		src += "♥SELF^자사 출고지";
	}

	if (codeId == "goods_adult_type") {
		src = "♥NA^해당없음";
		src += "♥YES^미성년 판매금지";
	}

	if (codeId == "hour") {
		src = "♥01^01";
		src += "♥02^02";
		src += "♥03^03";
		src += "♥04^04";
		src += "♥05^05";
		src += "♥06^06";
		src += "♥07^07";
		src += "♥08^08";
		src += "♥09^09";
		src += "♥10^10";
		src += "♥11^11";
		src += "♥12^12";
		src += "♥13^13";
		src += "♥14^14";
		src += "♥15^15";
		src += "♥16^16";
		src += "♥17^17";
		src += "♥18^18";
		src += "♥19^19";
		src += "♥20^20";
		src += "♥21^21";
		src += "♥22^22";
		src += "♥23^23";
	}

	if (codeId == "option_level") {
		src = "♥1^1";
		src += "♥2^2";
		src += "♥3^3";
	}

	if (codeId == "recommend_rate") {
		src = "♥5^5";
		src += "♥4^4";
		src += "♥3^3";
		src += "♥2^2";
		src += "♥1^1";
	}

	if (codeId == "reveiver_memo") {
		src = "♥001^직접 받겠습니다.";
		src += "♥002^경비실에 맡겨주세요.";
		src += "♥003^배송전 연락 바랍니다.";
		src += "♥009^직접 입력";
	}

	if (codeId == "ROOT") {
		src = "♥hour^시간";
		src += "♥option_level^옵션레벨";
		src += "♥supply_state^공급 상태";
		src += "♥recommend_rate^추천등급";
		src += "♥best_goods_rate^베스트 상품 등급";
		src += "♥collection_type^콜렉센 유형";
		src += "♥goods_adult_type^상품 성인 구분";
		src += "♥sys_myshop_state^시스템  마이샵 상태";
		src += "♥supply_price_type^공급가격유형";
		src += "♥delivery_cost_type^배송비 유형 코드";
		src += "♥admin_confirm_state^관리자 승인 상태";
	}

	if (codeId == "root") {
		src = "♥YN^여부";
		src += "♥biz_type^개인/사업자 구분";
		src += "♥adm_sply_stat^관리자 공급 상태";
		src += "♥reveiver_memo^수신자 메세지";
		src += "♥dlvry_place_cd^배송출고지코드";
		src += "♥adm_shop_stat^관리자 샵 상태";
	}

	if (codeId == "supply_price_type") {
		src = "♥NORMAL^판매자 임의";
		src += "♥JIDO_LIMIT^지도가 이하 금지";
		src += "♥JIDO_FIX^지도가 지정가격";
	}

	if (codeId == "supply_state") {
		src = "♥SALE^판매중";
		src += "♥SOUT^품절";
		src += "♥STOP^단종";
		src += "♥DEL^삭제";
	}

	if (codeId == "sys_myshop_state") {
		src = "♥REQ^신청중";
		src += "♥PEND^보류";
		src += "♥OK^승인";
		src += "♥STOP^중지";
	}

	if (codeId == "YN") {
		src = "♥Y^Yes";
		src += "♥N^No";
	}

	if (src == "") {
		src += "♥1^1";
		src += "♥2^2";
		src += "♥3^3";
		src += "♥4^4";
		src += "♥5^5";
		src += "♥6^6";
		src += "♥7^7";
		src += "♥8^8";
		src += "♥9^9";
		src += "♥10^10";
	}

	return src;

}

function cfn_CmmCd(codeData, opt, currCode) {

	var src = codeData;

	var arrSrc = src.split("♥");
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