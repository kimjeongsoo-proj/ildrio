/*
 페이지 로딩과 관련된 변수 및 함수 정의
 */
var isLoaded = true;
//페이지 로딩여부에 대한 변수

function chkLoading()
{
   if(top.main != undefined)
   {
   }
   return false;
}

function chkLoad()
{
   if(top.main != undefined)
   {
      if(!top.main.isLoaded)
      {
         return false;
      }
      else
      {
         return true;
      }
   }
   return true;
}

function checkLoading()
{
   if(top.main != undefined)
   {
      if(!top.main.isLoaded)
      {
         alert("페이지로딩중입니다.");
      }
   }
   return false;
}

function setLoaded()
{
   if(top.main != undefined)
   {
      top.main.isLoaded = true;
   }
}
/**
 * popup window
 * @param   dest 	popup 페이지
 * @param   widthx 	window width
 * @param   heighty 	window height
 * @return
 */

function popupWin(dest, widthx, heighty)
{
   window.open(dest, 'popup', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=' + widthx + ',height=' + heighty + '');
}
//==============================//
// 바이트 수 체크
//==============================//

function chk_len( obj, countstr)
{
   var totalLen, cnt, strCheck, arrCheck, lenOfCheck, lenOfChar;
   strCheck = obj.value;
   arrCheck = strCheck.split("");
   totalLen = strCheck.length;
   lenOfCheck = 0;
   for(cnt = 0; cnt < totalLen; cnt++)
   {
      lenOfChar = escape(arrCheck[cnt]);
      if(lenOfChar.length == 6)
      {
         lenOfCheck = lenOfCheck + 2;
      }
      else
      {
         lenOfCheck = lenOfCheck + 1;
      }
   }
   if(lenOfCheck > countstr)
   {
      alert(countstr + 'Byte 이상 초과 입력 할수 없습니다.');
      obj.select();
      obj.focus();
      return false;
   }
   else
   {
      return true;
   }
}

//==============================//
// 바이트 수 체크
//==============================//

function get_len(strCheck)
{
   var totalLen, cnt, arrCheck, lenOfCheck, lenOfChar;
   arrCheck = strCheck.split("");
   totalLen = strCheck.length;
   lenOfCheck = 0;
   for(cnt = 0; cnt < totalLen; cnt++)
   {
      lenOfChar = escape(arrCheck[cnt]);
      if(lenOfChar.length == 6)
      {
         lenOfCheck = lenOfCheck + 2;
      }
      else
      {
         lenOfCheck = lenOfCheck + 1;
      }
   }
   return lenOfCheck;
}



/**
 * 현재 객체의 다음 객체반환
 * @param   현재 입력 객체
 * @return  다음 text 객체
 */

function nextObj(obj)
{
   var index = 1;
   index = obj.sourceIndex;
  ++index;
   if(index < document.all.length)
   {
      if((document.all[index].type != 'text') &&(document.all[index].type != 'select'))
      {
        ++index;
         if(index >= document.all.length)
         {
            index = - 1;
         }
      }
   }
   else
   {
      index = - 1;
   }
   if(index != - 1)
   {
      return document.all[index];
   }
   else
   {
      return undefined;
   }
}
/**
 * 현재 객체의 이전 객체반환
 * @param   현재 입력 객체
 * @return  이전 text 객체
 */

function prevObj(obj)
{
   var index = 1;
   index = obj.sourceIndex;
  --index;
   if(index < document.all.length)
   {
      if((document.all[index].type != 'text') &&(document.all[index].type != 'select'))
      {
        --index;
         if(index < 0)
         {
            index = - 1;
         }
      }
   }
   else
   {
      index = - 1;
   }
   if(index != - 1)
   {
      return document.all[index];
   }
   else
   {
      return undefined;
   }
}
/**
 * 실수만 입력- ex) onkeypress="real_only(this,'컬럼이름');"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function real_only(obj, column)
{
   if(event.keyCode != 45){
	   if(((event.keyCode < 48) ||(event.keyCode > 57)) &&(event.keyCode != 46) &&(event.keyCode != 13))
	   {
	      event.returnValue = false;
	      alert(column + "는 +실수만 입력 가능합니다.");
	   }
   }
}
/**
 * 정수만 입력- ex) onkeypress="int_only(this,'컬럼이름');"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function int_only(obj, column)
{
   if(((event.keyCode < 48) ||(event.keyCode > 57)) &&(event.keyCode != 13))
   {
      event.returnValue = false;
      alert(column + "는 + 정수만 입력가능합니다.");
   }
}
/**
 * 정수만 입력- ex) onkeypress="int_only2(this,'컬럼이름');"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function int_only2(obj, column)
{
   if(((event.keyCode < 48) ||(event.keyCode > 57)) &&(event.keyCode != 13))
   {
      event.returnValue = false;
      alert(column + "는 숫자만 입력가능합니다.");
   }
}
/**
 * not null - ex) onblur="notnull(this,'컬럼이름');"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function notnull(obj, column)
{
   if(obj.value == '')
   {
      alert(column + "은 필수 입력 항목입니다.");
   }
   obj.focus();
}
/**
 * 길이 체크 - ex) onkeypress="chklen(this,3);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function chklen(obj, maxlen)
{
   if((obj.value).length > maxlen)
   {
      event.returnValue = false;
      alert("(" + maxlen + ")자리수를 초과할 수 없습니다.");
   }
   obj.focus();
}
/**
 * 숫자로 전환(콤마 제거) - ex) onfocus="tonum(this);"
 * @param   입력 상자
 * @return	전환된 숫자
 */

function tonum(obj)
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
/**
 * 금액 표시(3자리 마다 콤마 찍기) - ex) onblur="tostr(this);"
 * @param   입력 상자
 * @return	스트링
 */

function tostr(obj)
{
   obj.value=obj.value*1;
   sign = "";
   if(obj.value < 0)
   {
      sign = "-";
      obj.value = obj.value - 2 * obj.value;
   }
   num = obj.value;
   rat = "";
   if(num.indexOf(".") >= 0)
   {
      rat = num.substring(num.indexOf("."), num.length);
      num = num.substring(0, num.indexOf("."));
   }
   num_len = num.length;
   temp = "";
   co = 3;
   while(num_len > 0)
   {
      num_len = num_len - co;
      if(num_len < 0)
      {
         co = num_len + co;
         num_len = 0;
      }
      temp = "," + num.substr(num_len, co) + temp;
   }
   if(check_digit(obj))
   {
      if((num == "0") ||(temp.substr(1, temp.length) == "NaN"))
      {
         obj.value = '0' + rat;
      }
      else
      {
         obj.value = sign + temp.substr(1, temp.length) + rat;
      }
   }
}

/**
 * 숫자로 전환(데쉬 제거)
 * @param   입력 상자
 * @return	전환된 숫자
 */
function tonum3(num)
{
   if(num != '')
   {
      str = num;
      while(str.indexOf("-") >= 0)
      {
         str = str.replace("-", "");
      }
      return Number(str);
   }
   else
   {
      return 0;
   }
}

/**
 * 날짜 체크 yyyymmdd- ex) onblur="chkyyyymmdd(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function chkyyyymmdd(obj)
{
   if(obj.value != '')
   {
      var pattern = /^[0-9]+$/;
      str = obj.value;
      
      if((str.length != 8) ||(!pattern.test(str)))
      {
         event.returnValue = false;
         alert("날짜가 잘못 입력 되었습니다.\n\nYYYYMMDD 형식으로 입력해 주십시오!!");
         obj.value = '';
         obj.focus();
         return;
      }
      else
      {
         if((Number(str.substring(4, 6)) > 12) ||(Number(str.substring(4, 6)) < 1))
         {
            alert("날짜가 잘못 입력 되었습니다.\n\n달은 1월에서 12월까지입니다.");
            obj.value = '';
            obj.focus();
            return;
         }
         else if((Number(str.substring(6, 8)) > 31) ||(Number(str.substring(6, 8)) < 1))
         {
            alert("날짜가 잘못 입력 되었습니다.\n\n" + str.substring(4, 6) + "월에는 " + str.substring(6, 8) + "일이 없습니다.");
            obj.value = '';
            obj.focus();
            return;
         }
         else
         {
            var tempObj = null;
            if((tempObj = nextObj(obj)) != undefined)
            {
               if(tempObj.name != undefined)
               {
                  if((tempObj.name.indexOf("date") >= 0) &&(Number(todate2(obj)) > Number(todate2(tempObj))))
                  {
                     alert("기간 From은 To 보다 이후 날짜가 입력될수 없습니다.");
                     tempObj.value = obj.value;
                     toyyyymmdd(tempObj);
                     //obj.value='';
                     obj.focus();
                  }
               }
            }
            if((tempObj = prevObj(obj)) != undefined)
            {
               if(tempObj.name != undefined)
               {
                  if((tempObj.name.indexOf("date") >= 0) &&(Number(todate2(tempObj)) > Number(todate2(obj))))
                  {
                     alert("기간 To는 From 보다 이전 날짜가 입력될수 없습니다.");
                     tempObj.value = obj.value;
                     toyyyymmdd(tempObj);
                     //obj.value='';
                     obj.focus();
                  }
               }
            }
            toyyyymmdd(obj);
            return;
         }
      }
   }
}
/**
 * 날짜 체크 yyyymmdd-
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function chkdate(date_value)
{
   if(date_value != '')
   {
      var pattern = /^[0-9]+$/;
      if((date_value.length != 8) ||(!pattern.test(date_value)))
      {
         return false;
      }
      else
      {
         if((Number(date_value.substring(4, 6)) > 12) ||(Number(date_value.substring(4, 6)) < 1))
         {
            return false;
         }
         else if((Number(date_value.substring(6, 8)) > 31) ||(Number(date_value.substring(6, 8)) < 1))
         {
            return false;
         }
         else
         {
            return true;
         }
      }
   }
   return false;
}
/**
 * 날짜 체크 yyyymm- ex) onblur="chkyyyymm(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function chkyyyymm(obj)
{
   if(obj.value != '')
   {
      var pattern = /^[0-9]+$/;
      str = obj.value;
      if((str.length != 6) ||(!pattern.test(str)))
      {
         event.returnValue = false;
         alert("날짜가 잘못 입력 되었습니다.\n\nYYYYMM 형식으로 입력해 주십시오!!");
         obj.value = '';
         obj.focus();
         return;
      }
      else
      {
         if((Number(str.substring(4, 6)) > 12) ||(Number(str.substring(4, 6)) < 1))
         {
            alert("날짜가 잘못 입력 되었습니다.\n\n달은 1월에서 12월까지입니다.");
            obj.value = '';
            obj.focus();
            return;
         }
         else
         {
            var tempObj = null;
            if((tempObj = nextObj(obj)) != undefined)
            {
               if(tempObj.name != undefined)
               {
                  if((tempObj.name.indexOf("yyyymm") >= 0) &&(Number(todate2(obj)) > Number(todate2(tempObj))))
                  {
                     alert("기간 From은 To 보다 이후 날짜가 입력될수 없습니다.");
                     tempObj.value = obj.value;
                     toyyyymm(tempObj);
                     //obj.value='';
                     obj.focus();
                  }
               }
            }
            if((tempObj = prevObj(obj)) != undefined)
            {
               if(tempObj.name != undefined)
               {
                  if((tempObj.name.indexOf("yyyymm") >= 0) &&(Number(todate2(tempObj)) > Number(todate2(obj))))
                  {
                     alert("기간 To은 From 보다 이전 날짜가 입력될수 없습니다.");
                     tempObj.value = obj.value;
                     toyyyymm(tempObj);
                     //obj.value='';
                     obj.focus();
                  }
               }
            }
            toyyyymm(obj);
            return;
         }
      }
   }
}
/**
 * 날짜 체크 yyyymm- ex) onblur="chkyyyymm(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function chkyyyy(obj)
{
   if(obj.value != '')
   {
      var pattern = /^[0-9]+$/;
      str = obj.value;
      if((str.length != 4) ||(!pattern.test(str)))
      {
         event.returnValue = false;
         alert("날짜가 잘못 입력 되었습니다.\n\nYYYY 형식으로 입력해 주십시오!!");
         obj.value = '';
         obj.focus();
         return false;
      }
   }
   return true;
}
/**
 * 날짜 변환 ('-' 제거) - ex) onfocus="todate(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function todate(obj)
{
   if(obj.value != '')
   {
      str = obj.value;
      while(str.indexOf("-") >= 0)
      {
         str = str.replace("-", "");
      }
      while(str.indexOf("/") >= 0)
      {
         str = str.replace("/", "");
      }
      obj.value = str;
      obj.select();
   }
}
/**
 * 날짜 변환 ('-' 제거) - 리턴값으로 받음
 * @param			obj
 * @return			string
 */

function todate2(obj)
{
   if(obj.value != '')
   {
      str = obj.value;
      while(str.indexOf("-") >= 0)
      {
         str = str.replace("-", "");
      }
      while(str.indexOf("/") >= 0)
      {
         str = str.replace("/", "");
      }
      return str;
   }
}
/**
 * 날짜 변환 ('-' 추가) yyyymm- ex) onblur="toyyyymmdd(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function toyyyymmdd(obj)
{
   if(obj.value != '')
   {
      str = obj.value;
      str = str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8);
      obj.value = str;
      //		obj.select();
   }
}
/**
 * 날짜 변환 ('-' 추가) yyyymm- ex) onblur="toyyyymm(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function toyyyymm(obj)
{
   if(obj.value != '')
   {
      str = obj.value;
      str = str.substring(0, 4) + "/" + str.substring(4, 6);
      obj.value = str;
      //		obj.select();
   }
}
/**
 * 문자열 바꾸기
 * @param   변환할 문자열
 * @param   바꿀 문자열
 * @param   바뀐 문자열
 * @return	  변환된 문자열
 */
/**
function replaceAll(str, from, to)
{
   while(str.indexOf(from) > -1)
   {
      str = str.replace(from, to);
   }
   return str;
}
**/

function replaceAll(str, searchStr, replaceStr) {

    return str.split(searchStr).join(replaceStr);
}

/**
 * 문자 없애기
 * @param   변환할 문자열
 * @param   바꿀 문자 집합
 * @return	  변환된 문자열
 */

function removeAll(str, key)
{
   for(i = 0; i < key.length; i++)
   {
      str = replaceAll(str, key.substring(i, i + 1), "");
   }
   return str;
}
/**
 * Row click 시 색상 변환
 * @param   변환할 tr id
 * @return
 */

function toggleBg(obj)
{
   if(tempTr != undefined)
   {
      tempTr.style.backgroundColor = "#FFFFFF";
   }
   obj.style.backgroundColor = "#EEEEEE";
   tempTr = obj;
}
/**
 * Row click 시 색상 변환
 * @param   변환할 tr id
 * @return
 */

function toggleBg2(obj)
{
   if(tempTr != undefined)
   {
      tempTr.parentElement.style.backgroundColor = "#e7e7e7";
   }
   obj.parentElement.style.backgroundColor = "#FEFFFD";
   tempTr = obj;
}
/**
 * '-' 없이 입력 - ex) onkeypress="post_only(this,'컬럼이름');"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function post_only(obj, column)
{
   if(((event.keyCode < 48) ||(event.keyCode > 57)) &&(event.keyCode != 13))
   {
      event.returnValue = false;
      alert(column + "은 '-'없이 숫자만 입력해 주십시오.");
   }
}
///////////////////////////////////////////////////////////////////////////////
// 함수명 	: f_DisplayMaskNumber(aElement)
// 내  용 	: 숫자 마스크에 대한 작업 수행

function f_DisplayMaskNumber(aElement) // 숫자를 단위구분자를 사용하여 표현하는 함수
{
   var reverseMaskedNumber = "", maskedNumber = "";
   //
   var integerCount = 0, maskCount = 0, isPoint = 0;
   // 정수자릿수, 단위구분자 갯수, 소수점유무
   var integerIndex;
   // 정수의 시작위치
   var tmpValue, i;
   if(aElement.value == null) aString = aElement;
   else aString = aElement.value;
   aString = String(aString);
   // String으로 변환
   aString = f_RemoveMaskNumber(aString);
   // Number의 마스크를 삭제(',','\')
   integerIndex = aString.length;
   // 정수의 시작위치
   for(i = aString.length - 1; i >= 0; i--) // value의 길이만큼 loop
   {
      reverseMaskedNumber += aString.charAt(i);
      // 캐릭터 하나씩 변수에 첨가
      if(aString.charAt(i) == ".")
      {
         integerIndex = i - 1; // 소숫점이 있으면 정수시작위치 지정후 break
         isPoint = 1;
         // 소숫점 유!!
         break;
      }
   }
   if(isPoint == 0) // 소숫점 없을 경우
   {
      reverseMaskedNumber = "";
      // 변수클리어
      integerIndex -= 1;
      // 정수시작위치 - 1
   }
   for(i = integerIndex; i >= 0; i--) // 정수시작위치부터 loop
   {
      integerCount++;
      // 정수자릿수 카운트 ++
      reverseMaskedNumber += aString.charAt(i);
      // 캐릭터 하나씩 변수첨가.
      if(integerCount % 3 == 0 && i != 0 && aString.charAt(i - 1) != "-")
      {
         reverseMaskedNumber += ",";
         // 정수자릿수가 3의 배수일 때마다 ','첨가
         maskCount++;
         // 단위구분자 카운트 ++
      }
   }
   for(i = maskCount + aString.length; i >= 0; i--) // 단위구분자숫자 + 정수자릿수 + 정수시작위치만큼 loop
   {
      maskedNumber += reverseMaskedNumber.charAt(i);
      // 역순으로 변수에 대입
   }
   if(aElement.value == null)
   return maskedNumber;
   // 구분자 표시값 return
   else aElement.value = maskedNumber;
}
//
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
// 함수명 	: f_RemoveMaskNumber(aString)
// 내  용 	: 숫자 마스크에 대한 작업 수행

function f_RemoveMaskNumber(aElement) // 숫자를 단위구분자를 사용하여 표현하는 함수
{
   if(aElement.value == null)
   {
      return aElement.replace(/(\,)/g, "");
   }
   else
   {
      aElement.value = aElement.value.replace(/(\,)/g, "");
   }
}
/**
 * 입력 페이지에서 마지막 로우 마지막 입력 객체에서 onblur 시에 입력 로우 인서트
 * @param   현재 입력 객체
 * @return
 */

function chkadd(obj)
{
   var index = - 1;
   var chklength = - 1;
   for(i =(obj.form.elements.length - 1); i > 0; i--)
   {
      if(obj.form.elements[i] == obj)
      {
         index = i;
         break;
      }
   }
   for(i =(obj.form.elements.length - 1); i > 0; i--)
   {
      if((obj.form.elements[i].type == 'text') &&(obj.form.elements[i].style.backgroundColor != '#e7e7e7') &&(obj.form.elements[i].style.backgroundColor != '#E7E7E7'))
      {
         chklength = i;
         break;
      }
   }
   if(index >= chklength)
   {
      if(addTableRow() != undefined)
      {
         addTableRow();
      }
   }
}
/**
 * 현재 객체의 다음 객체로 포커스 이동
 * @param   현재 입력 객체
 * @return
 */

function next(obj)
{
   var index = 1;
   index = obj.sourceIndex;
  ++index;
   if(index < document.all.length)
   {
      while((document.all[index].type != 'text'))
      {
        if (document.all[index].type == 'select-one'){
	        break;
        }
        ++index;
         if(index >= document.all.length)
         {
            index = - 1;
            break;
         }
      }
   }
   else
   {
      index = - 1;
   }
   if(index != - 1)
   {
      document.all[index].focus();
   }
}
/**
 * from 과 to 의상품권 권종 검사
 * @param   현재 입력 객체
 * @return
 */

function checkCoupon(from, to)
{
   var coupon_from = new objCoupon(from.value);
   var coupon_to = new objCoupon(to.value);
   if(!coupon_from.valid)
   {
      alert(coupon_from.message);
      from.focus();
      return false;
   }
   if(!coupon_to.valid)
   {
      alert(coupon_to.message);
      to.focus();
      return false;
   }
   if(from.value.substring(0, 6) != to.value.substring(0, 6))
   {
      alert("From과 To의 상품권종류가 같지 않습니다.!");
      to.select();
      return false;
   }
   return true;
}

function setSelectBox(obj, rvalue)
{
   for(i = 0; i < obj.options.length; i++)
   {
      if(obj.options[i].value == rvalue)
      {
         obj.options[i].selected = true;
         return;
      }
   }
}
/* 문자에서 '-' 제거 */

function js_removeChar(str, chr)
{
   var src = new String(str);
   var tar = new String();
   var i, len = src.length;
   for(i = 0; i < len; i++)
   {
      if(src.charAt(i) == chr) tar += '';
      else tar += src.charAt(i);
   }
   return tar;
}
/* 문자에서 '-' 제거 */

function js_removeChar2(obj)
{
   var src = new String(obj.value);
   var tar = new String();
   var i, len = src.length;
   for(i = 0; i < len; i++)
   {
      if(src.charAt(i) == '-') tar += '';
      else tar += src.charAt(i);
   }
   obj.value = tar;
}
/****************************************************************************//* 카드번호  mask 씌우기                                                    *//****************************************************************************/

function cardMask1(obj)
{
   var chartest = obj.value;
   obj.value = "";
   chartest = js_removeChar(chartest, '-');
   if(chartest.length > 1)
   {
      if(chartest.length > 12)
      {
         obj.value += chartest.substring(0, 4) + "-" + chartest.substring(4, 8) + "-" + chartest.substring(8, 12) + "-" + chartest.substring(12);
      }
      else
      {
         alert("카드번호 입력이 잘못되었습니다");
         obj.focus();
      }
   }
   return true;
}
/**
 * 전화번호 - 넣기 - ex) onblur="chkTelNo(this);"
 * @param   obj 	체크할 오브젝트
 * @return			alert
 */

function chkTelNo(obj)
{
   if(obj.value != '')
   {
      var pattern = /^[0-9]+$/;
      str = todate2(obj);
      if((str.length < 7) ||(!pattern.test(str)))
      {
         event.returnValue = false;
         alert("전화번호가 잘못 입력 되었습니다.\n\nHyphen('-')없이 숫자로 입력해 주십시오!!");
         obj.value = '';
         obj.focus();
         return;
      }
      else
      {
         if(str.substring(0, 1) == "0")
         {
            if(str.substring(0, 2) == "02")
            {
               obj.value = str.substring(0, 2) + "-" + str.substring(2, str.length - 4) + "-" + str.substring(str.length - 4);
            }
            else
            {
               obj.value = str.substring(0, 3) + "-" + str.substring(3, str.length - 4) + "-" + str.substring(str.length - 4);
            }
         }
         else
         {
            obj.value = str.substring(0, str.length - 4) + "-" + str.substring(str.length - 4);
         }
         return;
      }
   }
}
function selectAll(){
	for (i=1;i<document.all.chkb.length;i++){
		if (document.all.chkb[i].checked){
			document.all.chkb[i].checked=false;
		}else{
			document.all.chkb[i].checked=true;
		}
	}
}
function chkRow(obj)
{
   var x = obj.parentElement.parentElement.rowIndex;
   // 해당 checkBox의 index를 얻음(tr의 rowIndex를 얻음)
   if(document.all.chkb[x].checked != undefined)
   {
      document.all.chkb[x].checked = true;
   }
}
/**
 * '-' 제거 - ex) onfocus="delHyphen(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function delHyphen(obj)
{
   if(obj.value != '')
   {
      str = obj.value;
      while(str.indexOf("-") >= 0)
      {
         str = str.replace("-", "");
      }
      obj.value = str;
      obj.select();
   }
}
/**
 * 주민번호 변환 ('-' 추가) - ex) onblur="toRegNo(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function toRegNo(obj)
{
   if(obj.value.length == 13)
   {
      str = obj.value;
      str = str.substring(0, 6) + "-" + str.substring(6, 13) ;
      obj.value = str;
      //		obj.select();
   }
}
/**
 * 우편번호 변환 ('-' 추가) - ex) onblur="toRegNo(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function toPostNo(obj)
{
   if(obj.value.length == 6)
   {
      str = obj.value;
      str = str.substring(0, 3) + "-" + str.substring(3) ;
      obj.value = str;
      //		obj.select();
   }
}
/**
 * 사업자번호변환 ('-' 추가) - ex) onblur="toRegNo(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */
function toBiz(obj)
{
   if (obj.value==''){
	   return;
   }else if(obj.value.length== 10)
   {
      str = obj.value;
      str = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 10);
      obj.value = str;
      //		obj.select();
   }else{
	   alert('사업자번호는 10자리입니다.');
	   obj.value='';
   }
}

/**
 * 날짜 체크 yyyymmdd- ex) onblur="chkyyyymmdd(this);"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert
 */

function chkdate2(obj)
{
   if(obj.value != '')
   {
      var pattern = /^[0-9]+$/;
      var str = obj.value;
      if(!pattern.test(str))
      {
         event.returnValue = false;
         alert("날짜가 잘못 입력 되었습니다.\n");
         obj.value = '';
         obj.focus();
         return;
      }
      else
      {
	     switch(str.length){
		     case (5) :
		     	str=str.substring(0,4)+"0"+str.substring(4);
		     	obj.value=str;
		     	toyyyymm(obj);
		     	break;
		     case (6) :
		         if((Number(str.substring(4, 6)) > 12) ||(Number(str.substring(4, 6)) < 1)){
		            alert("날짜가 잘못 입력 되었습니다.\n\n달은 1월에서 12월까지입니다.");
		            obj.value = '';
		            obj.focus();
		            return;
		         }else {
			     	obj.value=str;
			     	toyyyymm(obj);
		         }
		     	break;
		     case (7) :
		     	 str=str.substring(0,6)+"0"+str.substring(6);
		         if((Number(str.substring(4, 6)) > 12) ||(Number(str.substring(4, 6)) < 1)){
		            alert("날짜가 잘못 입력 되었습니다.\n\n달은 1월에서 12월까지입니다.");
		            obj.value = '';
		            obj.focus();
		            return;
		         }else if((Number(str.substring(6, 8)) > 31) ||(Number(str.substring(6, 8)) < 1)){
		            alert("날짜가 잘못 입력 되었습니다.\n\n" + str.substring(4, 6) + "월에는 " + str.substring(6, 8) + "일이 없습니다.");
		            obj.value = '';
		            obj.focus();
		            return;
		         }else {
			     	obj.value=str;
			     	toyyyymmdd(obj);
		         }
		       	break;
		     case (8) :
		         if((Number(str.substring(4, 6)) > 12) ||(Number(str.substring(4, 6)) < 1)){
		            alert("날짜가 잘못 입력 되었습니다.\n\n달은 1월에서 12월까지입니다.");
		            obj.value = '';
		            obj.focus();
		            return;
		         }else if((Number(str.substring(6, 8)) > 31) ||(Number(str.substring(6, 8)) < 1)){
		            alert("날짜가 잘못 입력 되었습니다.\n\n" + str.substring(4, 6) + "월에는 " + str.substring(6, 8) + "일이 없습니다.");
		            obj.value = '';
		            obj.focus();
		            return;
		         }else {
			     	obj.value=str;
			     	toyyyymmdd(obj);
		         }
		     	break;
		     default :
		     	break;
	     }

      }
   }
}

function GoSubmit()
{
   if (event.keyCode==13){
	   goView();
   }
}



function getCheckDigit(code){
  cnt = 1;
  sum = 0;
  for (i=0;i<12;i++){
     dgt = Number(code.substring(i, i+1));
     if ((i+1)%2 == 1) {
 		sum = sum + dgt;
 	 }else{
        sum = sum + dgt * 3;
 	 }
  }
  buf = Math.round(sum / 10);
  buf = (buf + 1) * 10;
  chk_dgt = (buf - sum)%10;
  return chk_dgt;	
}
function blank(){ 
	return;
}

function nexttab(obj,nextfocus){
	if ((event.keyCode=="13")||(event.keyCode=="9")|| (event.keyCode=="\t")){
	    if ( eval("document.all."+nextfocus) != undefined ) {
	        if ( eval("document.all."+nextfocus+".type") != "hidden" ) {
		        eval("document.all."+nextfocus+".focus();");
		    }
	    }
	}
}
function nextup(obj,nextfocus){
	if (event.keyCode=="38"){
	    
	    if ( eval("document.all."+nextfocus) != undefined ) {
	        if ( eval("document.all."+nextfocus+".type") != "hidden" ) {
		        eval("document.all."+nextfocus+".focus();");
		    }
	    }
	}
}
function nextdn(obj,nextfocus){
    if (event.keyCode=="40"){
    	    
	    if ( eval("document.all."+nextfocus) != undefined ) {
	        if ( eval("document.all."+nextfocus+".type") != "hidden" ) {
		        eval("document.all."+nextfocus+".focus();");
		    }
	    }
    }
      
}
function nextup2(obj,nextObjName,nextIdx){
	
	if (event.keyCode=="38"){
	    
	    if ( eval("document.getElementsByName('"+nextObjName+"')["+nextIdx+"]") != undefined ) {
	        if ( eval("document.getElementsByName('"+nextObjName+"')["+nextIdx+"].type") != "hidden" ) {
		        eval("document.getElementsByName('"+nextObjName+"')["+nextIdx+"].focus();");
		    }
	    }
	}
}
function nextdn2(obj,nextObjName,nextIdx){
    if (event.keyCode=="40"){
    	 if ( eval("document.getElementsByName('"+nextObjName+"')["+nextIdx+"]") != undefined ) {
 	        if ( eval("document.getElementsByName('"+nextObjName+"')["+nextIdx+"].type") != "hidden" ) {
 		        eval("document.getElementsByName('"+nextObjName+"')["+nextIdx+"].focus();");
 		    }
 	    }
    }
      
}
function toHHMM(obj)
{
   if(obj.value != '')
   {
      str = obj.value;
      while(str.indexOf(":") >= 0)
      {
         str = str.replace(":", "");
      }
      obj.value = str;
      obj.select();
   }
}
function toTime(obj)
{
   if(obj.value != '')
   {
	   if (obj.value.length!=4){
		   alert('HH:MM 형식으로 입력해주세요');
		   return;
	   }else{
		  str=obj.value; 
		  str=str.substring(0,2)+":"+str.substring(2);
	      obj.value = str;
	   }
   }
   
}

function chkValid(obj, msg)
{
    if(obj.value == ''){
        alert(msg+"입력하십시오.");
        obj.focus();
        return false;
    }
}   

function chkValidNum(obj,str, msg)
{
    if((obj.value == str ) ||(obj.value == '' ) ){
        alert(msg+"입력하십시오.");
        obj.focus();
        return false;
    }
}  


function chkLength(obj,ilen,msg){ 

   var len = 0; 
   var str = obj.value;
   if ( str == null ) return 0; 


     for(var i=0;i<str.length;i++){ 
       var c = escape(str.charAt(i)); 


        if ( c.length == 1 ) len ++; 
        else if ( c.indexOf("%u") != -1 ) len += 2; 
        else if ( c.indexOf("%") != -1 ) len += c.length/3; 
     } 
     
    if(len > ilen) {
      
      //alert(msg+" 입력길이는 "+ilen+" byte 이내입니다. (한글은 1자가 2byte)");
      //obj.focus(); 
      //return false;

    }
}

function selectTextbox(textbox)
{
	setTimeout(function() { textbox.select(); }, 10);
}
 
//특정 위치에 창열기
function f_window(url, w, h) { 
 
	win = window.open(url, 'win', 'resizable=yes, scrollbars=yes, x=100,y=200,width=' + w + ',height=' + h); 
	win.focus(); 
}
 
function f_window2(url, w, h) { 
 
	win = window.open(url, 'win', 'resizable=yes, scrollbars=no, x=100,y=200,width=' + w + ',height=' + h); 
	win.focus(); 
}

var g4_is_gecko  = navigator.userAgent.toLowerCase().indexOf("gecko") != -1;
var g4_is_ie     = navigator.userAgent.toLowerCase().indexOf("msie") != -1;


//숫자관련===============================================
/**
 * 숫자로 전환(콤마 제거)
 * @param   입력 상자
 * @return	전환된 숫자
 */

function valToNum(val)
{
   if(val != '')
   {
      str = val;
      while(str.indexOf(",") >= 0)
      {
         str = str.replace(",", "");
      }
      return Number(str);
   }
   else
   {
      return 0;
   }
}

//-------------------------------------------------------------------
//숫자인가를 체크하는 함수  // Arg로 받은 한 값이 조건에 맞는지 하나씩 체크해야 함.
//-------------------------------------------------------------------

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



//-------------------------------------------------------------------
//숫자인가를 체크하는 함수  // Arg로 받은 한 값이 조건에 맞는지 하나씩 체크해야 함.
//-------------------------------------------------------------------
function chkMoney(obj)
{
	var i;
	var str = obj.value;
	for(i = 0; i < str.length; i++)
	{
	   if(!isMoney(str.charAt(i)))
	   {
	      alert("숫자만 입력가능합니다.");
	      //obj.value = '';
	      obj.focus();
	      return false;
	   }
	}
	return true;
}
function isMoney(value)
{
	var j;
	var intValue = "01234567890,";
	for(j = 0; j < intValue.length; j++)
	{
	   if(value == intValue.charAt(j))
	   {
	       return true;
	   }
	}
	return false;
}
//-------------------------------------------------------------------
//숫자로 구성된 문자열인가를 체크하는 함수 (마이너스, Dot 모두 허용)
//-------------------------------------------------------------------

function check_digit(obj)
{
var i;
var str = commaToNumberVal(obj.value);
for(i = 0; i < str.length; i++)
{
   if(!is_int(str.charAt(i)))
   {
      alert("숫자만 입력가능합니다.");
      obj.value = '';
      obj.focus();
      return false;
   }
}
return true;
}

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
      obj.value = '';
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

/**
 * 금액 표시(3자리 마다 콤마 찍기)
 * @param   입력 상자
 * @return	스트링
 */

function cfnFmtMoney(num)
{
   num=num*1;
   sign = "";
   if(num < 0)
   {
      sign = "-";
      num = num - 2 * num;
   }
   num = String(num);
   rat = "";
   if(num.indexOf(".") >= 0)
   {
      rat = num.substring(num.indexOf("."), num.length);
      num = num.substring(0, num.indexOf("."));
   }
   num_len = num.length;
   temp = "";
   co = 3;
   while(num_len > 0)
   {
      num_len = num_len - co;
      if(num_len < 0)
      {
         co = num_len + co;
         num_len = 0;
      }
      temp = "," + num.substr(num_len, co) + temp;
   }
   if((num == "0") ||(temp.substr(1, temp.length) == "NaN"))
   {
      return '0' + rat;
   }
   else
   {
      return sign + temp.substr(1, temp.length) + rat;
   }
   
}
function cfnMoneyToNum(str)
{
	 while(str.indexOf(",") >= 0)
     {
        str = str.replace(",", "");
     }
	 return str;
}
function cfnFmtDate(str,dlStr)
{
	if(str == null){
		str = "";
	}else {
		str =  replaceAll(str,'-','');
		str =  replaceAll(str,'.','');
		
		if(str.length >= 8){
			
				str = str.substring(0,4)+dlStr+str.substring(4,6)+dlStr+str.substring(6,8);
			
		}
	
		if( str.length  == 6 ){
			str =	str.substring(0,4)+"-"+str.substring(4,6);
		}
		
	}
	return str;
}
function cfnMrgn(num)
{
	var str = "";
	if(Number(num) > 0){
		str="<span style=\"color:blue;font-weight:bold;\">+"+cfnFmtMoney(num)+"</span>";
	}else{
		str="<span style=\"color:red;font-weight:bold;\">"+cfnFmtMoney(num)+"</span>";
	}
	return str;
}


function cfnFmtDateTime(str,dlStr)
{
	
	if(str == null){
		str = "";
	}else {
		str =  replaceAll(str,'-','');
		str =  replaceAll(str,'.','');
		str =  replaceAll(str,' ','');
		str =  replaceAll(str,':','');
		
		if(str.length >= 12){
			
				str = str.substring(0,4)+dlStr+str.substring(4,6)+dlStr+str.substring(6,8)+" "+str.substring(8,10)+":"+str.substring(10,12) ;
			
		}
	
		if( str.length  == 6 ){
			str =	str.substring(0,4)+"-"+str.substring(4,6);
		}
		
	}
	return str;
}


/**
 * 금액 표시(3자리 마다 콤마 찍기) - ex) onblur="tostr(this);"
 * @param   입력 상자
 * @return	스트링
 */

function numToCommaObj(obj){
	tostr(obj);
}

function tostr(obj)
{
   obj.value = Number(replaceAll(obj.value,",",""))*1;
   sign = "";
   if(obj.value < 0)
   {
      sign = "-";
      obj.value = obj.value - 2 * obj.value;
   }
   num = obj.value;
   rat = "";
   if(num.indexOf(".") >= 0)
   {
      rat = num.substring(num.indexOf("."), num.length);
      num = num.substring(0, num.indexOf("."));
   }
   num_len = num.length;
   temp = "";
   co = 3;
   while(num_len > 0)
   {
      num_len = num_len - co;
      if(num_len < 0)
      {
         co = num_len + co;
         num_len = 0;
      }
      temp = "," + num.substr(num_len, co) + temp;
   }
   if(check_digit(obj))
   {
      if((num == "0") ||(temp.substr(1, temp.length) == "NaN"))
      {
         obj.value = '0' + rat;
      }
      else
      {
         obj.value = sign + temp.substr(1, temp.length) + rat;
      }
   }
}

/**
 * 숫자로 전환(콤마 제거) - ex) onfocus="tonum(this);"
 * @param   입력 상자
 * @return	전환된 숫자
 */

function commaToNumber(obj)
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

/**
 * 숫자로 전환(콤마 제거)
 * @param   입력 상자
 * @return	전환된 숫자
 */

function commaToNumberVal(num)
{
   if(num != '')
   {
      str = num;
      while(str.indexOf(",") >= 0)
      {
         str = str.replace(",", "");
      }
      return Number(str);
   }
   else
   {
      return 0;
   }
}

/**
 * 금액 표시(3자리 마다 콤마 찍기) - ex) onblur="tostr(this);"
 * @param   입력 상자
 * @return	스트링
 */

function numberToCommma(obj)
{
	
   num = obj.value;
	   
   while(num.indexOf(",") > -1)
   {
	   num = num.replace(",", "");
   }
   
   rat = "";
   if(num.indexOf(".") >= 0)
   {
      rat = num.substring(num.indexOf("."), num.length);
      num = num.substring(0, num.indexOf("."));
   }
   num_len = num.length;
   temp = "";
   co = 3;
   while(num_len > 0)
   {
      num_len = num_len - co;
      if(num_len < 0)
      {
         co = num_len + co;
         num_len = 0;
      }
      temp = "," + num.substr(num_len, co) + temp;
   }
   
   obj.value =  temp.substr(1, temp.length) + rat;
   
   /**
   if(check_digit(obj))
   {
      if((num == "0") ||(temp.substr(1, temp.length) == "NaN"))
      {
         obj.value = '0' + rat;
      }
      else
      {
         obj.value = sign + temp.substr(1, temp.length) + rat;
      }
   }
   **/
}

/**
 * 자리수
 * 1. 반올림
 * 2. 버림
 * 3. 올림
 */
//지정자리 반올림 (값, 자릿수)
function round(n, pos) {
	var digits = Math.pow(10, pos);
	var sign = 1;
	if (n < 0) {
		sign = -1;
	}
	// 음수이면 양수처리후 반올림 한 후 다시 음수처리 
	n = n * sign; 
	var num = Math.round(n * digits) / digits; 
	num = num * sign; 
	
	return num.toFixed(pos);	
}

//지정자리 버림 (값, 자릿수) 
function floor(n, pos) { 
	var digits = Math.pow(10, pos); 
	var num = Math.floor(n * digits) / digits; 
	return num.toFixed(pos); 
}

//지정자리 올림 (값, 자릿수) 
function Ceiling(n, pos) { 
	var digits = Math.pow(10, pos); 
	var num = Math.ceil(n * digits) / digits; 
	return num.toFixed(pos); 
}






/**
 * 정수만 입력- ex) onkeypress="int_only(this,'컬럼이름');"
 * @param   obj 	체크할 오브젝트
 * @param   column 	컬럼이름
 * @return			alert 
 */

function keypressInt(obj)
{
   if(((event.keyCode < 48) ||(event.keyCode > 57)) &&(event.keyCode != 13))
   {
      event.returnValue = false;
      alert("숫자만 입력가능합니다.");
   }
}


 function lpad(len,chr,str){
	
	var vLen = str.toString().length;
	var chrPad = "";
	
	
	if(len > vLen){
		for(i = 0; i < (len-vLen); i++){
			chrPad = chrPad +""+ chr;
		}
	}
	
	return chrPad + str.toString();
	
}
 
function numberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
 
 
var winW = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
var winH = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

function cfn_timeStamp() {
	  var d = new Date();
	  var s =
	    leadingZeros(d.getFullYear(), 4) + '-' +
	    leadingZeros(d.getMonth() + 1, 2) + '-' +
	    leadingZeros(d.getDate(), 2) + ' ' +

	    leadingZeros(d.getHours(), 2) + ':' +
	    leadingZeros(d.getMinutes(), 2) + ':' +
	    leadingZeros(d.getSeconds(), 2);

	  return s;
}
function leadingZeros(n, digits) {
	  var zero = '';
	  n = n.toString();

	  if (n.length < digits) {
	    for (i = 0; i < digits - n.length; i++)
	      zero += '0';
	  }
	  return zero + n;
	}

function cfnDateDiff(strDate1,strDate2) {
	
	strDate1 = cfnFmtDate(strDate1,"-");
	strDate2 = cfnFmtDate(strDate2,"-");

	var sdt = new Date(strDate1);
	var edt = new Date(strDate2);
	var dateDiff = Math.ceil((edt.getTime()-sdt.getTime())/(1000*3600*24));

	return dateDiff;
}
 
function chk_emailType(str)
{
	
	if( (str.indexOf("@") == -1)|| (str.indexOf(".") == -1) ){
		alert("이메일 형식이 아닙니다.");
		return false;
	}
	
	if ( (str.split("@")[0].length == 0 )  || (str.split("@")[1].split(".")[0].length == 0  ) || (str.split(".")[1].length == 0  ) ){
		alert("이메일 형식이 아닙니다.");
		return false;
	}
	
	return true;
}
