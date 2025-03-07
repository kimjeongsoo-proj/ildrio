function cfn_isValid_English(objNm, str, min, max) {

	// 정규표현식을 사용하여 영문 대소문자로만 이루어져 있는지 체크
	var englishRegex = /^[a-zA-Z]+$/;

	if (str.length < min || str.length > max) {
		alert("["+objNm+"] 길이 " + min + "자 ~ " + max + "자로 입력해주세요.");
		return false;
	}

	if (!englishRegex.test(str)) {
		alert('['+objNm+'] 영문으로 입력해 주세요. ');
		return false;
	}

	return true;
}

function cfn_isValid_AlphaNumeric(objNm,str, min, max) {

	// 정규표현식을 사용하여 영문자와 숫자만 허용
	var alphaNumericRegex = /^[a-zA-Z0-9]+$/;

	if (str.length < min || str.length > max) {
		alert("["+objNm+"] 길이 " + min + "자 ~ " + max + "자로 입력해주세요.");
		return false;
	}

	if (!alphaNumericRegex.test(str)) {
		alert('['+objNm+'] 영문,숫자만 입력 가능합니다. ');
		return false;
	}

	return true;
}

// 바이트 길이 체크 체크
function cfn_isValid_Empty(objNm,str, min, max) {

	
	if (str.length < min || str.length > max) {
		alert("["+objNm+"] 길이 " + min + " ~ " + max + "자로 입력해주세요.");
		return false;
	}
	return true;
}

// 사업자 번호 체크
function cfn_isValid_bizNumber(objNm,bizNumber) {

	var bizNumberRegex = /^\d{3}-\d{2}-\d{5}$/;

	if (!bizNumberRegex.test(bizNumber)) {
		alert('['+objNm+'] 유효한 사업자 번호를 입력하세요. (예: 123-12-12345)');
		return false;
	}
	return true;
}

// 법인번호 번호 체크
function cfn_isValid_lawNumber(objNm,lawNumber) {

	var lawNumberRegex = "/^\d{6}-\d{7}$/";

	if (!lawNumberRegex.test(lawNumber)) {
		alert('['+objNm+'] 유효한 법인 번호를 입력하세요. (예: 123456-1234567)');
		return false;
	}
	return true;
}

// 핸드폰 번호
function cfn_isValid_mobileNumber(objNm,phoneNumber) {

	var phoneNumberRegex = /^\d{3}-\d{3,4}-\d{4}$/;

	if (!phoneNumberRegex.test(phoneNumber)) {
		alert('['+objNm+'] 유효한 전화번호를 입력하세요. (예: 010-1234-5678)');
		return false;
	}
	return true;
}

// 전홥번호
function cfn_isValid_phoneNumber(objNm,phoneNumber) {
	var phoneNumberRegex = /^\d{2,3}-\d{3,4}-\d{4}$/;

	if (!phoneNumberRegex.test(phoneNumber)) {
		alert('['+objNm+'] 유효한 전화번호를 입력하세요. (예: 02-1234-5678)');
		return false;
	}
	return true;
}

// 이메일 포멧 체크
function cfn_isValid_Email(objNm,email) {
	var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

	if (emailRegex.test(email)) {
		alert('['+objNm+'] 이메일 형식이 아닙니다.');
		return false;
	}
	return true;
}

// 비밀번호 1
function cfn_isValid_password1(str) {

	var pw = str;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	if (pw.length < 6 || pw.length > 20) {
		alert("비밀번호 6자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}

	if (pw.search(/₩s/) != -1) {
		alert("비밀번호는 공백업이 입력해주세요.");
		return false;
	}
	return true;

}

// 비밀번호2
function cfn_isValid_password2(str) {
	var pw = str;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	if (pw.length < 6 || pw.length > 20) {
		alert("비밀번호 6자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}
	if (pw.search(/₩s/) != -1) {
		alert("비밀번호는 공백업이 입력해주세요.");
		return false;
	}

	if ((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) {
		alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여  6자리 ~ 20자리 입력해주세요.");
		return false;
	}

	return true;

}

// 비밀버호3
function cfn_isValid_password3(str) {

	var pw = str;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	if (pw.length < 8 || pw.length > 20) {
		alert("8자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}

	if (pw.search(/₩s/) != -1) {
		alert("비밀번호는 공백업이 입력해주세요.");
		return false;
	}

	if ((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) {
		alert("영문,숫자, 특수문자  혼합하여 입력해주세요.");
		return false;
	}

	return true;

}

// 바이크 길이
function cfn_getByteCount(str) {
	var byteCount = 0;
	for (var i = 0; i < str.length; i++) {
		var charCode = str.charCodeAt(i);
		if (charCode <= 0x7F) {
			byteCount += 1;
		} else if (charCode <= 0x7FF) {
			byteCount += 2;
		} else if (charCode <= 0xFFFF) {
			byteCount += 3;
		} else {
			byteCount += 4;
		}
	}
	return byteCount;
}

// 날짜 유효성
function cfn_isValid_Date(objNm,dateString) {
	// JavaScript의 Date 객체를 사용하여 날짜 유효성 체크
	// 정상적인 날짜 형식이면 해당 날짜로 객체를 생성할 수 있습니다.
	var parsedDate = new Date(dateString);

	// isNaN을 사용하여 날짜 객체가 유효한지 확인합니다.
	// getTime() 메서드는 날짜를 밀리초로 변환합니다.
	if (isNaN(parsedDate.getTime())) {
		alert("["+objNm+"] 날짜가 잘못 되었습니다.");
		return false;
	}
	return true;
}

// 정수 유효성 체크
function cfn_isValid_Integer(objNm,value) {
	// 정수로 변환 가능한지 체크
	// JavaScript에서는 parseInt 함수를 사용하여 문자열을 정수로 변환할 수 있습니다.
	var integerValue = parseInt(value);

	// isNaN을 사용하여 정수로 변환 가능한지 확인
	if (!isNaN(integerValue) && Number.isInteger(integerValue)) {
		return true;
	} else {
		alert("["+objNm+"] 정수형으로 입력해 주세요.");
		return false;
	}
}

// 커런시 1000단위 컴마 숫자
function cfn_isValid_Currency(objNm,value) {
	// 정규표현식을 사용하여 숫자와 1000단위 컴마가 올바르게 사용되었는지 체크
	var currencyRegex = /^\d{1,3}(,\d{3})*(\.\d{1,2})?$/;

	if (!currencyRegex.test(value)) {
		alert('['+objNm+'] 유효한 숫자가 아닙니다.');
		return false;
	}

	return true;
}

// 퍼센트
function cfn_isValid_Percent(objNm,value) {
	// 정규표현식을 사용하여 숫자와 퍼센트 기호가 올바르게 사용되었는지 체크
	var percentRegex = /^(\d+(\.\d+)?%?)$/;

	if (!percentRegex.test(value)) {
		alert('['+objNm+'] 유효한 숫자가 아닙니다.');
		return false;
	}

	return true;

}

function cfn_chk_Integer(objId) {
	var value = $("#"+objId).val();
	
	var integerRegex = /^[0-9]+$/;

	if (!integerRegex.test(value)) {
		alert("숫자만 입력해 주세요.");
		$("#"+objId).val("0");
	}
}

function cfn_chk_Percent(objId) {
	var value = $("#"+objId).val();
	
	var checkRegex = /^(\d+(\.\d+)?%?)$/;

	if (!checkRegex.test(value)) {
		alert("유효한 숫자를 입력해 주세요.");
		$("#"+objId).val("0");
	}
}

function cfn_chk_Money(objId) {
	var input = $("#"+objId).val();
	
	// Remove non-numeric characters
    input = input.replace(/[^0-9]/g, '');
    
    // Check if input is a valid number
    if (input === '') {
    	//input = "0";
        //alert("숫자를 입력해 주세요");
    } 
    
    // Add commas every three digits
    var formattedInput = input.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    $("#"+objId).val(formattedInput);
	
	
}

//날짜 유효성
function cfn_chk_Date(objId) {
	var value = $("#"+objId).val();
	// JavaScript의 Date 객체를 사용하여 날짜 유효성 체크
	// 정상적인 날짜 형식이면 해당 날짜로 객체를 생성할 수 있습니다.
	var parsedDate = new Date(value);

	// isNaN을 사용하여 날짜 객체가 유효한지 확인합니다.
	// getTime() 메서드는 날짜를 밀리초로 변환합니다.
	if(value != ""){
		if (isNaN(parsedDate.getTime())) {
			alert("날짜형식이  잘못 되었습니다.");
			$("#"+objId).val("");
			return false;
		}
	}
	return true;
}