
function cfn_chkEmpty(obj,msg) {
	var str = obj.val();
	if(str == null || str.trim() == ""){
		obj.focus();
		alert('['+msg+'] 필수 입력항목입니다.');
		return false;
	}
    return true;
}
 

/*******************************************************************************
 * 영문자로 시작하는6~20자 영문자 또는 숫자이어야 합니다.
 ******************************************************************************/
function cfn_chkMbrId(str) {

    str = str.toLowerCase().trim();

    if (str.length < 6) {
        alert("아이디는 6자 이상 20자 이내 입니다.");
        return false;
    }
    if (str.length > 20) {
        alert("아이디는 6자 이상 20자 이내 입니다."); 
        return false;
    }

    var blank_pattern = /[\s]/g;
    if (blank_pattern.test(str.value) === true) {
        alert(' 공백은 사용할 수 없습니다. ');
        return false;
    }
    var idReg = /^[a-z0-9]{5,19}$/g;
    if (!idReg.test(str)) {
        alert("아이디는 영문자/숫자 6~20자로 입력해주세요.");
        return false;
    }

    return true;
}

/*******************************************************************************
 * 영문,숫자,특수문자 비밀번호 8자리 ~ 20자리 이내로 입력해주세요.(비밀번호 표준)
 ******************************************************************************/
function cfn_chkPwd1(str) {

    var pw = str;

    var num = pw.search(/[0-9]/g);

    var eng = pw.search(/[a-z]/ig);

    var spe = pw.search(/[`~!@#$%^&*|₩'";:/?]/gi);

    if (pw.length < 8|| pw.length > 20) {

        return false;

    }

    if (pw.search(/₩s/) !== -1) {

        return false;

    }
    return true;

}

/*******************************************************************************
 * 영문,숫자,특수문자 중 2가지 혼합하여 8자리~20자리 이내.(비밀번호 표준)
 ******************************************************************************/
function cfn_chkPwd2(str) {

    var pw = str;

    var num = pw.search(/[0-9]/g);

    var eng = pw.search(/[a-z]/ig);

    var spe = pw.search(/[`~!@#$%^&*|'";:/?]/gi);

    if (pw.length < 8 || pw.length > 20) {

        alert("영문,숫자,특수문자 중 2가지 이상 포함하여 8자리~20자리 이내로 입력해주세요.");

        return false;

    }

    if (pw.search(/₩s/) !== -1) {

        alert("비밀번호는 공백없이 입력해주세요.");

        return false;

    }

    if ((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) {

        alert("영문,숫자,특수문자 비밀번호 8자리-20자리 이내로 입력해주세요.");

        return false;

    }

    return true;

}

/*******************************************************************************
 * 영문,숫자,특수문자 혼합하여 8자리~20자리 이내.(비밀번호 표준)
 ******************************************************************************/
function cfn_chkPswd3(str) {

    var pw = str;

    var num = pw.search(/[0-9]/g);

    var eng = pw.search(/[a-z]/ig);

    var spe = pw.search(/[`~!#$%^&*|'";:/?]/gi);

    if (pw.length < 8 || pw.length > 20) {

        alert("영문,숫자,특수문자 비밀번호 8자리-20자리 이내로 입력해주세요.");

        return false;

    }

    if (pw.search(/₩s/) !== -1) {

        alert("비밀번호는 공백없이 입력해주세요.");

        return false;

    }

    if ((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) {

        alert("영문,숫자,특수문자 비밀번호 8자리-20자리 이내로 입력해주세요.");

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

    if (vPopMode === "win_fix") {

        popWidth = vWidth;
        popHeight = vHeight;
    }
    if (vPopMode === "win_mrgn") {

        popWidth = $(window).width() - vWidth;
        popHeight = $(window).height() - vHeight;
    }
    if (vPopMode === "win_hfix") {

        popWidth = ($(window).width() - vWidth);
        popHeight = vHeight;
    }
    if (vPopMode === "win_wfix") {

        popWidth = vWidth;
        popHeight = $(window).height() - vHeight;
    }
    if (vPopMode === "layer_fix") {
        popWidth = vWidth;
        popHeight = vHeight;
    }
    if (vPopMode === "layer_mrgn") {

        popWidth = $(window).width() - vWidth;
        popHeight = $(window).height() - vHeight;

        if (popWidth > 1200) {
            popWidth = 1200;
        }
        if (popHeight > 1000) {
            popWidth = 1000;
        }
    }
    if (vPopMode === "layer_hfix") {
        popWidth = ($(window).width() - vWidth);
        popHeight = vHeight;
    }
    if (vPopMode === "layer_wfix") {
        popWidth = vWidth;
        popHeight = $(window).height() - vHeight;
    }

    vTop = ($(window).height() - popHeight) / 2;
    vLeft = ($(window).width() - popWidth) / 2;

    if (vPopMode.indexOf("win_") > -1) {

        window.open(vUrl, vPopNm,
            "top="
            + vTop
            + ",left="
            + vLeft
            + ", width="
            + popWidth
            + ", height="
            + popHeight
            + ",location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes");
    }
    if (vPopMode.indexOf("layer_") > -1) {

        cfn_ModalOpenWindow(vUrl, vPopNm, popWidth, popHeight);
    }
}

function cfn_orderBy(columnName, ascDesc) {
    document.searchForm.srhOrderBy.value = columnName;
    document.searchForm.srhOrderAsc.value = ascDesc;
    fn_searchPage('1');

}

function cfn_pageNavigationButton(iTotalCnt, iPageRow, iCurrPage, iPageBlock) {

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

    if (iPageCnt < 1) {
        iPageCnt = 1;
    }

    var nextPageYn = "N";
    if (iPageCnt > iCurrPage) {
        nextPageYn = "Y";
    }

    var src = "";

    if (iCurrPage > 1) {

        src += "<div class=\"pageFirst\" onclick=\"fn_searchPage('1')\" ><i class=\"fas fa-angle-double-left\"/></div>";
        src += "<div class=\"pagePrev\" onclick=\"fn_searchPage('" + (iCurrPage - 1) + "')\"><i class=\"fas fa-angle-left\"/></div>";
    } else {

        src += "<div class=\"pageFirst\"><i class=\"fas fa-angle-double-left\"/></div>";
        src += "<div class=\"pagePrev\"><i class=\"fas fa-angle-left\"/></div>";

    }

    // src += "&nbsp;";
    for (i = iFrPage; i <= iToPage; i++) {

        if (i === iCurrPage) {
            src += "<div class=\"pageNow\" >" + i + "</div>";
        } else {
            src += "<div class=\"pageNum\" onclick=\"fn_searchPage('" + i + "')\" >" + i + "</div>";
        }

    }
    if (nextPageYn === "Y") {

        src += "<div class=\"pageNext\"  onclick=\"fn_searchPage('" + (iCurrPage + 1) + "')\" ><i class=\"fas fa-angle-right\"/></div>";
        src += "<div class=\"pageLast\" onclick=\"fn_searchPage('" + iPageCnt + "')\" ><i class=\" fas fa-angle-double-right\"/></div>";

    } else {

        src += "<div class=\"pageNext\"><i class=\"fas fa-angle-right\"/></div>";
        src += "<div class=\"pageLast\"><i class=\" fas fa-angle-double-right\"/></div>";
    }

    return src;

}

function cfn_pageNavigation_li(iTotalCnt, iPageRow, iCurrPage, iPageBlock) {

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

    if (iPageCnt < 1) {
        iPageCnt = 1;
    }

    var nextPageYn = "N";
    if (iPageCnt > iCurrPage) {
        nextPageYn = "Y";
    }

    var src = "";

    if (iCurrPage > 1) {

        src += '<li><a onclick="fn_searchPage(\'' + (iCurrPage - 1) + '\')"><img src="/resources/image/page_prev_2.png" alt=""></a></li>';
    }

    for (i = iFrPage; i <= iToPage; i++) {

        if (i === iCurrPage) {
            src += '<li class="active"><a>' + i + '</a></li>';
        } else {
            src += '<li><a onclick="fn_searchPage(\'' + i + '\')" style="cursor:pointer;" >' + i + '</a></li>';
        }

    }
    if (nextPageYn === 'Y') {

        src += '<li><a onclick="fn_searchPage(\'' + (iCurrPage + 1)  + '\')" style="cursor:pointer;" ><img src="/resources/image/page_next_2.png" alt=""></a></li>';
    } 

    return src;

}

function cfn_srh() {
    if (event.keyCode === 13) {
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

function cfn_chk_email(vObjNm, vObjTitle, isNotNull) {

    var v_Val1 = document.getElementById(vObjNm + "_1").value;
    var v_Val2 = document.getElementById(vObjNm + "_2").value;

    if ((isNotNull === "notNull") || (v_Val1 !== "" || v_Val2 !== "")) {

        if (v_Val1.length < 3) {
            alert("[" + vObjTitle + "1] 정확한 이메일 주소를 입력합세요");
            document.getElementById(vObjNm + "_1").focus();
            return false;
        }

        if ((v_Val2.length < 3) || (v_Val2.indexOf(".") < 0)) {
            alert("[" + vObjTitle + "2] 정확한 이메일 주소를 입력합세요");
            document.getElementById(vObjNm + "_2").focus();
            return false;
        }
    }
    document.getElementById(vObjNm).value = v_Val1 + "@" + v_Val2;
    return true;
}

function cfn_chk_name(name) {
    var regXP = /^[가-힣]{2,4}$/;
    if (!regXP.test(name)) {
        return false;
    }
    return true;

}

function cfn_chk_telno(vObjNm, vObjTitle, isNotNull) {

    var v_Val1 = document.getElementById(vObjNm + "_1").value;
    var v_Val2 = document.getElementById(vObjNm + "_2").value;
    var v_Val3 = document.getElementById(vObjNm + "_3").value;

    if ((isNotNull === "notNull") || (v_Val2 !== "" || v_Val3 !== "")) {

        if ((v_Val1.length < 2) || (v_Val1.length > 5)) {
            alert("[" + vObjTitle + "1] 2자리 ~ 4자리 입니다.");
            document.getElementById(vObjNm + "_1").focus();
            return false;
        }

        if ((v_Val2.length < 3) || (v_Val2.length > 5)) {
            alert("[" + vObjTitle + "2] 3자리 또는 4자리 입니다.");
            document.getElementById(vObjNm + "_2").focus();
            return false;
        }
        if (v_Val3.length !== 4) {
            alert("[" + vObjTitle + "3] 4자리 입니다.");
            document.getElementById(vObjNm + "_3").focus();
            return false;
        }

    }

    if (v_Val2 === "" && v_Val3 === "") {
        document.getElementById(vObjNm).value = "";
    } else {
        document.getElementById(vObjNm).value = v_Val1 + "-" + v_Val2 + "-"
            + v_Val3;
    }

    return true;

}

function cfn_chk_bizNo(vObjNm, vObjTitle, isNotNull) {

    var v_Val1 = document.getElementById(vObjNm + "_1").value;
    var v_Val2 = document.getElementById(vObjNm + "_2").value;
    var v_Val3 = document.getElementById(vObjNm + "_3").value;

    if (isNotNull === "notNull") {

        if (v_Val1.length !== 3) {
            alert("[" + vObjTitle + "1] 3자리 입니다.");
            document.getElementById(vObjNm + "_1").focus();
            return false;
        }

        if (v_Val2.length !== 2) {
            alert("[" + vObjTitle + "2] 2자리 입니다.");
            document.getElementById(vObjNm + "_2").focus();
            return false;
        }
        if (v_Val3.length !== 5) {
            alert("[" + vObjTitle + "3] 5자리 입니다.");
            document.getElementById(vObjNm + "_3").focus();
            return false;
        }

    }
    document.getElementById(vObjNm).value = v_Val1 + "-" + v_Val2 + "-"
        + v_Val3;

    return true;
}

/*******************************************************************************
 * cfn_byte
 ******************************************************************************/
function cfn_byteObj(obj) {
    var codeByte = 0;
    for (var idx = 0; idx < obj.val().length; idx++) {
        var oneChar = escape(obj.val().charAt(idx));
        if (oneChar.length === 1) {
            codeByte++;
        } else if (oneChar.indexOf("%u") !== -1) {
            codeByte += 2;
        } else if (oneChar.indexOf("%") !== -1) {
            codeByte++;
        }
    }
    return codeByte;
}

function cfn_byteStr(str) {
    var codeByte = 0;
    for (var idx = 0; idx < str.length; idx++) {
        var oneChar = escape(str.charAt(idx));
        if (oneChar.length === 1) {
            codeByte++;
        } else if (oneChar.indexOf("%u") !== -1) {
            codeByte += 2;
        } else if (oneChar.indexOf("%") !== -1) {
            codeByte++;
        }
    }
    return codeByte;
}

function cfnZeroSpace(vVal) {
    if (vVal === 0) {
        vVal = "";
    }
    return vVal;
}

function cfnChkSpace(chkVal, vVal) {
    if ((chkVal === 0) && (vVal === 0)) {
        vVal = "";
    }
    return vVal;
}

/**
 * 
 * @param str
 * @param searchStr
 * @param replaceStr
 * @returns
 */
function replaceAll(str, searchStr, replaceStr) {

    return str.split(searchStr).join(replaceStr);
}

// -------------------------------------------------------------------
// 숫자인가를 체크하는 함수 // Arg로 받은 한 값이 조건에 맞는지 하나씩 체크해야 함.
// -------------------------------------------------------------------

function cfn_isNumType(value) {
    var j;
    var intValue = "01234567890.-,";
    for (j = 0; j < intValue.length; j++) {
        if (value === intValue.charAt(j)) {
            return true;
        }
    }
    return false;
}

function cfn_isMoney(value) {
    var j;
    var intValue = "01234567890,-";
    for (j = 0; j < intValue.length; j++) {
        if (value === intValue.charAt(j)) {
            return true;
        }
    }
    return false;
}

function cfn_isInt(value) {
    var j;
    var intValue = "0123456789-";
    for (j = 0; j < intValue.length; j++) {
        if (value === intValue.charAt(j)) {
            return true;
        }
    }
    return false;
}

/**
 * 금액 표시(3자리 마다 콤마 찍기)
 * 
 * @param 입력
 *            상자
 * @return 스트링
 */

function cfnFmtMoney(num) {
    num = num * 1;
    sign = "";
    if (num < 0) {
        sign = "-";
        num = num - 2 * num;
    }
    num = String(num);
    rat = "";
    if (num.indexOf(".") >= 0) {
        rat = num.substring(num.indexOf("."), num.length);
        num = num.substring(0, num.indexOf("."));
    }
    num_len = num.length;
    temp = "";
    co = 3;
    while (num_len > 0) {
        num_len = num_len - co;
        if (num_len < 0) {
            co = num_len + co;
            num_len = 0;
        }
        temp = "," + num.substr(num_len, co) + temp;
    }
    if ((num === "0") || (temp.substr(1, temp.length) === "NaN")) {
        return '0' + rat;
    } else {
        return sign + temp.substr(1, temp.length) + rat;
    }

}

function cfnFmtInt(str) {
    while (str.indexOf(",") >= 0) {
        str = str.replace(",", "");
    }
    return str;
}

function cfnFmtDate(str, dlStr) {
    if (str == null) {
        str = "";
    } else {
        str = replaceAll(str, '-', '');
        str = replaceAll(str, '.', '');

        if (str.length >= 8) {

            str = str.substring(0, 4) + dlStr + str.substring(4, 6) + dlStr + str.substring(6, 8);

        }

        if (str.length === 6) {
            str = str.substring(0, 4) + "-" + str.substring(4, 6);
        }

    }
    return str;
}

function cfnFmtDateTime(str, dlStr) {

    if (str == null) {
        str = "";
    } else {
        str = replaceAll(str, '-', '');
        str = replaceAll(str, '.', '');
        str = replaceAll(str, ' ', '');
        str = replaceAll(str, ':', '');

        if (str.length >= 12) {

            str = str.substring(0, 4) + dlStr + str.substring(4, 6) + dlStr + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10, 12);

        }

        if (str.length === 6) {
            str = str.substring(0, 4) + "-" + str.substring(4, 6);
        }

    }
    return str;
}

function cfnDateDiff(strDate1, strDate2) {

    strDate1 = cfnFmtDate(strDate1, "-");
    strDate2 = cfnFmtDate(strDate2, "-");

    var sdt = new Date(strDate1);
    var edt = new Date(strDate2);
    var dateDiff = Math.ceil((edt.getTime() - sdt.getTime()) / (1000 * 3600 * 24));

    return dateDiff;
}

function cfnEemailType(str) {

    if ((str.indexOf("@") === -1) || (str.indexOf(".") === -1)) {
        return false;
    }

    if ((str.split("@")[0].length === 0) || (str.split("@")[1].split(".")[0].length === 0) || (str.split(".")[1].length === 0)) {
        return false;
    }
    return true;
}

/*******************************************************************************
 * cfnNul
 ******************************************************************************/

function cfnNull(val) {
    var rtnVal = val;
    if (val === undefined) {
        rtnVal = "";
    }
    return rtnVal;
}

function cfnIfnull(val1, val2) {
    var rtnVal = val1;
    if ((val1 === undefined) || (val1 === "")) {
        rtnVal = val2;
    }
    return rtnVal;
}

function cfnZero(val1) {
    var rtnVal = val1;
    if ((val1 === undefined) || (val1 === "") || (val1 === "0")) {
        rtnVal = 0;
    }
    return rtnVal;
}

function cfnSplit(val, char, idx) {
    if (val === undefined) {
        rtnVal = "";
    } else {
        if (val.indexOf(char) > -1) {
            rtnVal = val.split(char)[idx];
        } else {
            rtnVal = "";
        }
    }
    return rtnVal;
}

/*******************************************************************************
 * 
 * @param frm
 * @returns
 */
function cfnFormParam(frm) {

    var shrFormParam = "";
    var elemCnt = frm.elements.length;
    var andStr = "";
    for (i = 0; i < elemCnt; i++) {
        shrFormParam += andStr + frm.elements[i].name + "="
            + frm.elements[i].value;
        andStr = "amp;";
    }
    return shrFormParam;
}

function cfnChkEmpty(vObj, vObjText) {

    if (vObj.val() === "") {
        alert("[" + vObjText + "] 입력하세요");
        vObj.focus();
        return false;
    }
    return true;
}

function cfnChkZero(vObj, vObjText) {

    if (vObj.val() === "0") {
        alert("[" + vObjText + "] 입력하세요");
        vObj.focus();
        return false;
    }
    return true;
}

function cfnBrowserMode() {

    browserMode = "PC";
    if (window.navigator.userAgent.indexOf("Mobile") >= 0
        || window.navigator.userAgent.indexOf("Phone") >= 0
        || window.navigator.userAgent.indexOf("Opera") >= 0
        || window.navigator.userAgent.indexOf("Safari") >= 0) {
        browserMode = "PC";
    }

    var UserAgent = navigator.userAgent;
    if (UserAgent
            .match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null
        || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
        browserMode = "MOBILE";
    }

    if (UserAgent
            .match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null
        || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
        if (navigator.userAgent.indexOf("Safari") > -1) {
            // alert("결제시 Safari 브라우저는 지원이 안됩니다. Chrome으로 결제해 주시기 바랍니다. ");
        }
    }

    return browserMode;
}

/*******************************************************************************
 * checkbox
 ******************************************************************************/
function cfn_chkAll() {

    var irow = document.getElementsByName("chkb").length;
    if (document.getElementById("chkbAll").checked === true) {
        for (i = 0; i < irow; i++) {
            document.getElementsByName("chkb")[i].checked = true;
            document.getElementsByName("saveFlag")[i].value = "save";
        }
    }
    if (document.getElementById("chkbAll").checked === false) {
        for (i = 0; i < irow; i++) {
            document.getElementsByName("chkb")[i].checked = false;
            document.getElementsByName("saveFlag")[i].value = "skip";
        }

    }
}

function cfn_selchkb(idx) {
    if (document.getElementsByName("chkb")[idx].checked === true) {
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

function cfn_chkAllFalse() {

    var irow = document.getElementsByName("chkb").length;
    for (i = 1; i < irow; i++) {
        document.getElementsByName("chkb")[i].checked = false;
        document.getElementsByName("saveFlag")[i].value = "skip";
    }
}


function cfn_chkObjAll(objNm) {

    var irow = document.getElementsByName(objNm).length;
    if (document.getElementById("chkAll_" + objNm).checked === true) {
        for (i = 1; i < irow; i++) {
            document.getElementsByName("chk_" + objNm)[i].checked = true;
            document.getElementsByName(objNm)[i].value = "Y";
        }
    }
    if (document.getElementById("chkAll_" + objNm).checked === false) {
        for (i = 1; i < irow; i++) {
            document.getElementsByName("chk_" + objNm)[i].checked = false;
            document.getElementsByName(objNm)[i].value = "N";
        }

    }
}

function cfn_changChkb(idx) {
    document.getElementsByName("saveFlag")[idx].value = "save";
    document.getElementsByName("chkb")[idx].checked = true;
}

/** 숫자체크* */
function chk_num(obj)
{
	var i;
	var str = obj.value;
	for(i = 0; i < str.length; i++)
	{
	   if(!is_int(str.charAt(i)))
	   {
	      alert("숫자만 입력가능합니다.");
	      obj.value = '0';
	      obj.focus();
	      return false;
	   }
	}
	return true;
}


/*
 * 0123456789 숫자만가능
 * 
 */
function chk_onlyNum(obj)
{
	var i;
	var str = obj.value;
	for(i = 0; i < str.length; i++)
	{
	   if(!is_onlyNum(str.charAt(i)))
	   {
	      alert("숫자만 입력가능합니다.");
	      obj.value = '0';
	      obj.focus();
	      return false;
	   }
	}
	return true;
}
function is_onlyNum(value)
{
	var j;
	var intValue = "01234567890-,";
	for(j = 0; j < intValue.length; j++)
	{
	   if(value == intValue.charAt(j))
	   {
	      return true;
	   }
	}
	return false;
}
function is_int(value)
{
	var j;
	var intValue = "01234567890.-,";
	for(j = 0; j < intValue.length; j++)
	{
	   if(value == intValue.charAt(j))
	   {
	      return true;
	   }
	}
	return false;
}

function replaceAll(str, searchStr, replaceStr) {

    return str.split(searchStr).join(replaceStr);
}

/**
 * 금액 표시(3자리 마다 콤마 찍기) - ex) onblur="tostr(this);"
 * 
 * @param 입력
 *            상자
 * @return 스트링
 */

function fmtMoneyObj(obj){
	
    var num = replaceAll(obj.value,",","");
    
    obj.value = num.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
   
}

/**
 * 숫자로 전환(콤마 제거) - ex) onfocus="tonum(this);"
 * 
 * @param 입력
 *            상자
 * @return 전환된 숫자
 */

function fmtNumberObj(obj)
{
  if(obj.value != '')
  {
     str = obj.value;
     while(str.indexOf(",") >= 0)
     {
        str = str.replace(",", "");
     }
     obj.value = Number(str);
  }
}

//================================================
function gfnInputNum(objNm,objVal){
	var shtml = '';
	shtml += '   <input type="text"  name="'+objNm+'" id="'+objNm+'"  ';
	shtml += '   value="' + cfnFmtMoney(objVal) + '" ';
	shtml += '   onfocus="commaToNumber(this);this.select();" ';
	shtml += '   onblur="chk_num(this);numberToCommma(this);" ';
	shtml += '   class="form-control" style="width:95%;text-align:right;background-color:transparent;" >  ';
	return shtml;
}

function gfnInputStr(objNm,objVal){
	var shtml = '';
	shtml += '   <input type="text"  name="'+objNm+'" id="'+objNm+'"  ';
	shtml += '   value="' + cfnNull(objVal) + '" ';
	shtml += '   onfocus="this.select();" ';
	shtml += '   class="form-control" style="width:95%;text-align:left;background-color:transparent;" >  ';
	return shtml;
}
function gfnInputOption(objNm,objVal,optionList){
	var shtml = '';
	shtml += '   <select name="'+objNm+'" id="'+objNm+'" style="width:95%;background-color:transparent;">  ';
	shtml += optionList;
	shtml += '   </select>  ';
	return shtml;
}

function gfnInputDate(objNm,objVal){
	var shtml = '';
	shtml += '   <input type="text"  name="'+objNm+'" id="'+objNm+'"  ';
	shtml += '   value="' + cfnNull(objVal,'-') + '" ';
	shtml += '   onfocus="this.select();" ';
	shtml += '   class="form-control"  style="width:95%;text-align:center;background-color:transparent;" >  ';
	return shtml;
}

function gfnValueStr(objNm,objVal){
	var shtml =  cfnNull(objVal);
	return shtml;
}
function gfnValueNum(objNm,objVal){
	var shtml =  cfnFmtMoney(objVal);
	return shtml;
}
function gfnValueHidden(objNm,objVal,idx,align){
	var shtml = '<span>' + cfnNull(objVal) + '</span>" ';
	shtml += '   <input type="text"  name="'+objNm+'" id="'+objNm+'" value="' + cfnNull(objVal) + '"> ';
	return shtml;
}

