/* -------------------------------------------------------------------------------------------------
 * Javascript library v 1.0 
 *
 * jQuery 기반의 자바스크립트 라이브러리 입니다.  
 * 다음의 외부 라이브러리에 의존합니다.
 * 
 * https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js 
 * : https://github.com/jquery/jquery
 * 
 * http://code.jquery.com/ui/1.9.2/jquery-ui.js
 * : https://github.com/jquery/jquery-ui
 * 
 * jquery.json-2.4.js
 * : https://github.com/douglascrockford/JSON-js
 * 
 * jquery.cookie.js
 * : https://github.com/carhartl/jquery-cookie
 * 
 * @author: 안홍민
 * -------------------------------------------------------------------------------------------------
 */
(function($) {
    
	var osstem = (function() {
		var osstem = function() { 
			return new osstem.fn.init();
		};
        
		osstem.fn = osstem.prototype = {
            init: function() {
                return this;    
            }
        };
        
		// ---------------------------------------------------------------------- String Section
        osstem.string = {
    		/**
    		 * 문자열의 길이가 주어진 길이보다 적으면 패딩문자로 좌측을 채웁니다.
    		 * @param src 원본문자열
    		 * @param length 문자열의 길이
    		 * @param pad 채울 문자열
    		 * @return 패딩된 문자열
    		 */
        	lpad : function(src, length, pad) {
        		if(!this.hasText(src)) return "";
        		var buffer = [];
        		for(var i=0; i < length - src.length; i++) {
        			buffer.push(pad);
        		}
        		src =  buffer.join("") + src;
        		return src.substring(0, length);
            },
            /**
            * 공백을 제외한 문자열을 가지고 있는지 확인합니다.
            * @param str 체크할 문자열
            * @return true : 문자열의 길이가 > 0 이면 , 그밖에 false
            */
            hasText : function(str) {
            	if(!str) return false;
            	str = this.trim(str);
            	if(str == "") return false;
            	return true;
            },
            /** 
			* 문자열 앞/뒤의 공백을 제거 합니다.
            * @param str 변경할 문자열
            * @return 변경된 문자열
            */
            trim : function(str) {
                return  str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
            },
            /** 
             * 문자열이 널이면 널스트링을 반환합니다.
             * @param str 체크할 문자열
             * @return 문자열이 null이면 "", 아니면 체크할 문자열
             */
            nvls : function(str) {
                if(!str) return "";
                else return  str;
            },
            /** 
             * Oracle의 decode 참조. "1,한글,2,영어,기타" 와 같은 문자열을 
             * decodeString에 설정하고 expr1에 '2' 값을 설정하면 "영어"를 반환합니다. 주어진 값이
             * 없으면 마지막 기타를 반환합니다.
             * @param expr1 찾을 문자열
             * @param decodeString 해석할 문자열 
             * @return 검색된 결과 문자열
             */                         
            decode : function (expr1, decodeString)   {
                decodeString  = this.trim(decodeString);
                var strs = decodeString.split(',');
                return this.decode_internal(expr1, strs);
            },
            /** decode에서 내부적으로 사용하는 함수 */
            decode_internal : function(expr1, exprs) {
                var i = 0;
                var hasElseValue = false;
                var isMatch = false;
                var expr = this.nvls(expr1);

                hasElseValue = (exprs.length % 2) == 1 ? true : false;
                for (i = 0; i < exprs.length; i++) {
                    if(( i % 2) == 0  && expr == exprs[i])
                        return exprs[i+1];
                    i++;
                }// for

                if (!isMatch && hasElseValue)
                    rv = exprs[exprs.length - 1];
                return rv;
            },
            /**
             * 문자열을 치환합니다.
             * @param str 원본문자열
             * @param findStr 찾을 문자열
             * @param replaceStr 치환할 문자열
             */
            replace : function(str, findStr, replaceStr) {
                if(!str) return str;
                return str.replace(new RegExp(findStr,"g"), replaceStr);
            },
            /**
             * 문자열의끝에서 주어진 길이만큼 분리합니다.
             * @param str 원본문자열
             * @param length 주어진 길이
             */
            right : function(str, length) {
            	if(!str) return "";
            	return ( str.length  >=  length)?    str.substring(str.length - length): str;
            },
            /**
             * 문자열의 시작부분을 잘라냅니다.
             * @param str 원본문자열
             * @param length 주어진 길이
             */
            left : function(str, length) {
            	if(!str) return "";
                return  str.length >= length  ? str.substring(0, length): str;
            },
            /**
             * 문자열을 주어진 구분자로 날자형식으로 포맷합니다.
             * @param str 원본문자열
             * @param ch 구분자
             */
            formatDate :  function(str, ch) {
            	if(str == null) return "";
            	if(str.length < 5) return str;
            	else if(str.length > 4 && str.length <  7) {
            		return this.left(str, 4) + ch  + str.substring(4);
            	}else {
            		return this.left(str, 4) + ch
            		+ str.substring(4,6) + ch
            		+ str.substring(6);
            	}
            } ,
            /**
             * 문자열을 주민번호 형식으로 변환합니다.
             * @param str 원본문자열
             */
            formatSsn : function(str) {
            	if(str == null) return "";
            	str = this.replace(str, "-", "");
            	if(str.length < 7) return str;
            	return  this.left(str, 6) + "-" + str.substring(6);
            },
            /**
             * 문자열을 우편번호 형식으로 변환합니다.
             * @param str 원본문자열
             */
            formatZipCode : function(str) {
            	if(str == null) return "";
            	str = this.replace(str, "-", "");
            	if(str.length < 4) return str;
            	return  this.left(str, 3) + "-" + str.substring(3);
            },
            /**
             * 입력된 숫자형식의 문자열에 콤마를 넣습니다.
             * @param str 원본문자열
             */
            formatComma : function(str) {
            	str += '';
            	x = str.split('.');
            	x1 = x[0];
            	x2 = x.length > 1 ? '.' + x[1] : '';
            	var rgx = /(\d+)(\d{3})/;
            	while (rgx.test(x1)) {
            		x1 = x1.replace(rgx, '$1' + ',' + '$2');
            	}
            	return x1 + x2;
            },
            /**
             * 전화번호 형식으로 변환합니다.
             * @param str 원본문자열 
             */
            formatTelNo : function(str) {
            	if(!str) return "";
            	if(str.length < 3) return str;
            	else if(str.length >= 3 & str.length < 5) {
            		return str.replace(/(^0(?:2|[0-9]{2}))([0-9]+$)/, "$1-$2");
            	}
            	else if(str.length >= 5 & str.length < 8) {
            		return str.replace(/(^0(?:2|[0-9]{2}))([0-9]{3,4})([0-9]+$)/, "$1-$2-$3");
            	}
            	else {
            		return str.replace(/(^0(?:2|[0-9]{2}))([0-9]+)([0-9]{4}$)/, "$1-$2-$3");
            	}
            },
            /**
             * 숫자 형식으로 변환합니다.
             * @param str 원본문자열 
             */
            formatNumber : function(str) {
            	if(!str) return "";
            	return str.replace(/[^0-9]+/, "");
            },
            /**
             * 문자열이 통화 형식인지 체크
             * @param str 원본문자열 
             */
            isCurrency :  function(str) {
            	return !str.match(/[^0-9,\.]{1,}/);
            },
            /**
             * 문자열이 정수형인지 체크
             * @param str 원본문자열 
             */
            isInteger : function(str) {
            	return !str.match(/[^0-9]{1,}/);
            },
            /**
             * 문자열이 정수형과 대쉬(-)만 있는지 체크
             * @param str 원본문자열 
             */
            isNumberDash : function(str) {
            	return !str.match(/[^0-9\-]{1,}/);
            },
            /**
             * 바이트로 환산한 문자열의 길이값 반환.
             * @param str 원본문자열 
             */
            getBytesLength : function(str){
            	str_len = str.length;
            	byte_cnt = 0;
            	if(str_len!=escape(str).length){
            		for(var i=0;i<str_len;i++){
            			byte_cnt++;
            			if(this.isUnicode(str.charAt(i))){  
            				byte_cnt++; 
            			}
            		}
            	}
            	else
            		byte_cnt = str_len;
            	return byte_cnt;
            },
            /**
             * 문자가 유니코드인지 확인
             * @param chr 비교 문자  
             */
            isUnicode : function(chr){
            	return (escape(chr).length==6);
            },
            /**
             * 문자가 메일인지 확인
             * @param str 원본문자열  
             */
            isMail : function (str) {
            	//var regEmail = new RegExp("([xA1-xFEa-z0-9_-]+@[xA1-xFEa-z0-9-]+\.[a-z0-9-]+)","gi");
            	//if(!regEmail.test(str)){
            	var mailexp =  /^[0-9a-zA-Z-_\.]*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; 
            	if(!mailexp.test(str)){
            		return false;
            	}
            	return true;
            },
            /**
             * 문자열에서 HTML 태그 제거
             * @param text 원본문자열  
             */
            removeHTML : function(text) {
            	var objReg = new RegExp();  //  정규 표현식 객체를 생성한다
            	objReg = /[<][^>]*[>]/gi;
            	// <...> 태그를 대소문자 구분 없이[/gi 옵션](g=global / i=insensitive) 모두 찾는다.
            	text= text.replace(objReg, "");
            	return text;
            },
            /**
             * 문자열에서 anchor 태그 제거
             * @param text 원본문자열  
             */
            removeAnchor : function(text){
            	text = text.replace(/<a(.*?)>/gi,"");  //<a href에 포함됨 모든 내용 제거
            	text = text.replace(/<(\/?)a>/gi,"");  //</a>태그 제거
            	return text;
            }
        };
        
        // ----------------------------------------------------------------------// Event Section
        osstem.event = {
    		/**
             * KEYUP event에서 엘리먼트의 값을 콤마 형식으로 변환
             * @param evt  
             */
        	 EVENT_COMMA : function(evt) {
        		 if(!osstem.string.isCurrency(this.value)) {
        			 alert("숫자만 입력가능합니다.");
        			 this.value = this.value.replace(/[^0-9,\.]/g, "");
        			 return false;
        		 }
        		 this.value= osstem.string.formatComma(this.value.replace(/,/g, ""));
        		 return true;
             },
             /**
              * KEYUP event에서 엘리먼트의 값을 정수형식으로 변환
              * @param evt  
              */
             EVENT_INT : function(evt)  {
            	 //if(!$.isNumber(evt.keyCode)) {
            	 if(!osstem.string.isInteger(this.value)) {
            		 alert("숫자만 입력가능합니다.");
            		 this.value = this.value.replace(/[^0-9]/g, "");
            		 return false;
            	 }
            	 return true;
             },
             /**
              * KEYUP event에서 엘리먼트의 값을 통화형식으로 변환
              * @param evt  
              */
             EVENT_CURRENCY : function(evt)  {
            	 //if(!$.isNumber(evt.keyCode)) {
            	 if(!osstem.string.isCurrency(this.value)) {
            		 alert("숫자만 입력가능합니다.");
            		 this.value = this.value.replace(/[^0-9,\.]/g, "");
            		 return false;
            	 }
            	 return true;
             },
             /**
              * KEYUP event에서 엘리먼트의 값을 전화번호 형식으로 변환
              * @param evt  
              */
             EVENT_TELNO : function(evt) {
            	 var str = this.value.replace(/[^0-9]/g, "");
            	 if(!osstem.string.isNumberDash(this.value)) {
            		 alert("전화번호 형식 123-5678-9999 만 입력가능합니다.");
            		 this.value = this.value.replace(/[^0-9\-]/g, "");
            		 return false;
            	 }
            	 this.value =  osstem.string.formatTelNo(str);
            	 return true;
             },
             /**
              * KEYUP event에서 엘리먼트의 값을 날자형식으로 변환
              * @param evt  
              */
             EVENT_DATE :  function(evt) {
            	 var str = this.value.replace(/[^0-9]/g, "");
            	 if(!osstem.string.isNumberDash(this.value)) {
            		 alert("날자형식 9999-12-31 만 입력가능합니다.");
            		 this.value = str;
            		 return false;
            	 }
            	 this.value = osstem.string.formatDate(str, "-");
            	 return true;
             },
             /**
              * KEYUP event에서 엘리먼트의 값을 주민번호 형식으로 변환
              * @param evt  
              */
             EVENT_SSN : function(evt) {
            	 var str = this.value.replace(/[^0-9]/g, "");
            	 if(!osstem.string.isNumberDash(this.value)) {
            		 alert("주민번호 형식 800101-1**** 만 입력가능합니다.");
            		 this.value = str;
            		 return false;
            	 }
            	 this.value = osstem.string.formatSsn(str);
            	 return true;
             }
        };
        
        // ----------------------------------------------------------------------// Util Section
        osstem.util = {
    		/**
             * 입력값에서 최소값을 반환
             * @param val1
             * @param val2
             */
        	min : function(val1, val2){
        		if(val1 < val2)
        			return val1;
        		else
        			return val2;
        	},
        	/**
             * 입력받은 실수를 내림하여 원하는 자리까지 표현한다.
             * @param num 변환할 실수
             * @param dsc 표현할 소숫자리
             */
        	floor : function(num, dec){
        		var temp = osstem.util.decToDigit(dec);
        		num = num*temp;
        		num = Math.round(num);
        		num = num/temp;
        		return num;
        	},
        	/**
             * 소수자리를 계산하기 위한 정수로 변환
             * @param desc 표현할 소숫자리
             * @return int
             */
        	decToDigit : function(dec){
        	    var temp = 1;
        	    if(dec>=1){
        	        for(var i=0; i<dec; i++){
        	            temp = temp*10;
        	        }
        	    }else if(dec<1){
        	        for(var i=dec; i<0; i++){
        	            temp = temp/10;
        	        }
        	    }
        	    return temp;
        	},
        	/**
             * 배열과 같은 컬랙션이 비어 있는지 확인
             * @param collection
             */
        	isEmpty : function(collection) {
                if(!collection) return true;
                if(collection.length == 0) return true;
                return false;
            },
            /**
             * 날자객체를 문자열로 되돌립니다.
             * @param date 날자 객체
             * @param concatChar 연결문자
             */
            getDateString : function(date, concatChar) {
            	return  date.getFullYear()
            		+ concatChar + osstem.string.lpad( "" + (date.getMonth() + 1), 2, '0')
            		+ concatChar + osstem.string.lpad( "" + date.getDate(), 2, '0');            
            },
            /**
             * get방식의 URL의 파라미터를 맵객체로 반환합니다. 
             * Example)
             *  	URL : http://papermashup.com/index.php?id=123&page=home
             *		var temp = osstem.util.getUrlVars();
			 *		for (var j in temp) {
			 *			console.log(j+":"+temp[j]);		
			 *		}
             */
            getUrlVars : function() {
            	var vars = {};
            	window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
         	        vars[key] = value;
         	    });
         	    return vars;         
            },
            /**
        	 * 맵객체를 파라미터 문자열로 변경
        	 * @param {Object} params
        	 */
        	makeParams : function(params){
        		var s = '';
        		for (var n in params) {
        			s += n + "=" + params[n] + "&";
        		}
        		s = s.substring(0, s.length-1);
        		var result = "?"+s;
        		return result;
        	},
            /** dummy function */
            dummy: function() {
               // not used 
            }
        };
        
        // -------------------------------------------------------------------------- JSON Section
        osstem.json = {
            /**
             * 데이타 객체를 쿼리스트링으로 변환합니다.
             * @param dataObject 데이타 객체
             */
            toQueryString : function(dataObject) {
                var result = "";
                for (var prop in dataObject){
                    result += "&" + prop + "=" + dataObject[prop];
                }//for
                return result;
            },
            /**
             * JSON 객체를 문자열로 변환한다.
             * @param object 데이타 객체
             */
            getJSONString : function(object) {
                return $.toJSON(object); 
            },       
            /** dummy function */
            dummy: function() {
               // not used 
            }
        };
        
        // -------------------------------------------------------------------------- HTTP Section
        osstem.http = {
        	/**
        	 *	AJAX 통신 함수, jQuery의 $.ajax() 함수의 래핑 함수입니다.  호출방법은 아래와 같습니다. 
        	 *
        	 *	var opts = {  success : function(); } ; 
        	 *	osstem.http.ajax(opts); 
        	 *        
        	 *  호출할때 파라미터로 넘기는 파라미터는 { } 표기법을 사용하여 넘기면 됩니다. 
        	 *  파라미터는 jQuery의 파라미터 이름을 사용하고 추가적으로 정의된 파라미터가 존재합니다. 
        	 *  아래는 파라미터에 대한 설명입니다.
        	 *
        	 *  acceepts
        	 *  	어떤 응답을 보낼지 서버에게 알리기 위해 Request Header에 보내는 content type이다. 
        	 *  	accetps setting의 수정이 필요하면, $.ajaxSetup()을 메소드를 사용한다.
        	 *   
        	 *	async
        	 *		디폴트로, 모든 요청은 비동기로 보내진다. 디폴트로 true 값이 설정된다.  
        	 *		동기식 요청을 보내고 싶으면 false 값을 설정한다.
        	 *		Cross-domain 요청과 dataType:"jsonp" 요청은 동기식을 지원하지 않는다.  
        	 *
        	 *	beforeSend(jqXHR, settings)
        	 *		요청전 callback function은 그것이 전송되기 전에 jqXHR을 수정할 수 있다.
        	 *
        	 *	cache
        	 *		기본값은 true 이다. dataType 'script'와 'jsonp'을 위해서는 false을 설정한다.  
        	 *		false로 설정하면 브라우져는 cache를 하지 않는다.
        	 *
        	 *	complete(jqXHR, textStatus)
        	 *		요청이 완료되었을 때 실행되는 함수이다. 
        	 *		success와 error callback들이 실행된 이후에 실행된다. 
        	 *		textStatus에 전달되는 값은 다음과 같다.
        	 *		"success", "notmodified", "error", "timeout", "abort", or "parsererror"
        	 *
        	 *	contents
        	 *		string/정규식 쌍의 map이다. 이것은 jQuery가 response을 그것에 주어진 content type으로 파싱하는 방법을 결정한다.
        	 * 
        	 *	contentType
        	 *		데이터가 서버로 전송될 때 content-type이 사용된다.  
        	 *		기본값은 "application/x-www-form-urlencoded"인데, 그것은 대부분의 경우에 좋다. 
        	 *		데이터는 항상 UTF-8 charset을 사용해서 서버로 전송된다. 
        	 *		적절히 서버에서 decode 해야 한다.
        	 *
        	 *	context
        	 *		생략
        	 *
        	 *	converters
        	 *		기본값 : {"* text": window.String, "text html": true, "text json": jQuery.parseJSON, "text xml": jQuery.parseXML}
        	 *		응답의 값을 변경하기 위한 함수를 반환한다.
        	 * 
        	 *	crossDomain
        	 *		생략
        	 *
        	 * 	data
        	 * 		서버로 전송될 데이터.
        	 *
        	 *	dataFilter(data, type)
        	 *		생략
        	 *
        	 *	dataType
        	 *		서버로 부터 되돌려 받을 데이터 타입. 값이 정해지지 않으면 MIME 타입을 이용한다.
        	 *		다음의 값을 사용할 수 있다. 
        	 *		"xml","html","script","json","jsonp""text"
        	 *
        	 *	error(jqXHR, textStatus, errorThrown)
        	 *		요청이 실패하면 호출되는 함수이다. 
        	 *		textStatus에 허용되는 값은 null을 포함하여 "timeout", "error", "abort", "parsererror" 이다.
        	 *		HTTP error가 발생하면 errorThrown은 HTTP의 문자열 상태값 "Not Found", "Internal Server Error" 와 같은 값을 받는다.
        	 *
        	 *	global
        	 *		생략
        	 *
        	 *	headers
        	 *		key/value 쌍의 맵. 요청과 함께 서버로 보내진다. beforeSend 함수가 호출되기 전에 설정된다.
        	 *
        	 *	ifModified
        	 *		생략
        	 *
        	 *	isLocal
        	 *		생략
        	 *
        	 *	jsonp
        	 *		jsonp 요청에서 jsonp callback function 이름을 오버라이드 한다. 추가적인 설명 필요.
        	 *
        	 *	jsonpCallback
        	 *		JSONP 요청에 대한 callback function 이름을 명시한다.
        	 *
        	 *	mimeType
        	 *		XHR mime thype을 오버라이드 할 mime type.
        	 *
        	 *	password
        	 *		HTTP access authentication 요청에서 사용되는 패스워드.
        	 *
        	 *	processData
        	 *		생략.
        	 *
        	 *	scriptCharset
        	 *		생략.
        	 *
        	 *	statusCode
        	 *		numeric HTTP code들의 맵이다.
        	 *		$.ajax({
        	 *			statusCode: {
        	 *				404: function() {
        	 *					alert('page not found');
        	 *				}
        	 *			}
        	 *		});
        	 *
        	 *	success(data, textStatus, jqXHR)
        	 *		요청이 성공했을 때 호출되는 함수이다.
        	 *
        	 *	timeout
        	 *		요청에 대한 타임아웃을 밀리세컨드로 설정한다. 
        	 *
        	 *	traditional
        	 *		생략.
        	 *
        	 *	type
        	 *		디폴트는 GET이다. POST 또는 GET으로 설정. 
        	 *		다른 메소드인  PUT, DELETE도 여기서 사용될 수 있다.
        	 *
        	 *	url
        	 *		요청을 보낼 URL.
        	 *
        	 *	username
        	 *		HTTTP access authentication 요청에서 사용할 사용자 이름.
        	 * 
        	 *	xhr
        	 *		XMLHttpRequest 객체를 생성할 콜백.
        	 *
        	 *	xhrFields
        	 *		생략.
        	 *
        	 *	target
        	 *		로딩이미지가 표시될 영역이 될 요소를 정의한다. 
        	 *		document.body 처럼 요소를 직접써도 되고, jQuery의 selector를 사용할 수 있다.
        	 *		예를들어 .contents 는  class=".contents" 가 적용된 요소를 선택한다.
        	 *
        	 *	errorProcType
        	 *		에러 처리 방법. 에러가 발생하면 alert로 띄울지 아니면 html을 되돌릴지를 결정한다. 
        	 *		사용자 정의 callback에는 별도로 처리하고 시스템에서 처리하는 방법을 명시. 
        	 */
    		ajax : function(opts){
    			var settings = {   
    				url        : "",
    				target     : document.body,
    				errorProcType:"alert",			// 에러처리 방식 alert, html
    				data     : {},					// 요청 데이터
    				success  : function(){},		// 응답성공시 실행할 함수
    				error    : function() {},		// 에러발생시 실행할 함수
    				dataType :"json",				// 응답데이터 유형, xml, html, script, json, jsonp,text
    				type     : "post",				// 전송방법 기본값  post, (get/post)
    				sendDataType : "json"			// 전송 데이터 타입 json, (json/string)	  
    			};
    			$.extend(settings, opts);
    			$.ajax(settings); 
    		},
    		/**
    		 * 쿠키 생성
    		 * @param {string} key
    		 * @param {string} value
    		 */
    		setCookie : function(key, value){
    			$.cookie(key,null, {path:'/'});
    			$.cookie(key, value, {path:'/'});	
    		},
    		/**
    		 * 쿠키 삭제
    		 * @param {string} key
    		 */
    		removeCookie : function(key){
    			$.cookie(key,null, {path:'/'});
    		},
    		/**
    		 * 쿠키 확인
    		 * @param {string} key
    		 */
    		getCookie : function(key){
    			return $.cookie(key);	
    		},
        	/** dummy function */
            dummy: function() {
               // not used 
            }        	
        };
        
        // ----------------------------------------------------------------------- Data Section
        osstem.data= {
			/**
			 * 입력된 JSON 객체의 값을 html element에 채웁니다.
			 * @param {JSON} jsonObj
			 */
        	setForm : function(jsonObj) {
        		this.setFormRecursive("", jsonObj); 
        	},
        	/** setForm의 내부적인 함수 입니다. */
        	setFormRecursive : function(parentElementName, jsonObj) {
        		var prefix  = (parentElementName == "")? "": parentElementName  + ".";
        		for(var key in jsonObj) {
        			var srchKey = prefix + key;
        			var eArr = $("*[data-name='" + srchKey + "']");  // html element 검색
        			if(!eArr)	continue; //  엘리먼트가 없으면 skip 
        			var propValue = jsonObj[key];
        			if(!propValue)  continue;
        			var e = eArr[0];
        			if(typeof propValue == "object") {
        				// 배열
        				if(propValue.constructor.toString().indexOf("Array") > -1) {
        					// 배열일 경우에는 checkbox, select multiple 처리
        					if(!e) continue;
        					if(e.type == "checkbox")  {
        						for(var j=0; j< eArr.length; j++) {
        							eArr[j].checked = false;  // initialize
        						}
        						for(var i=0; i < propValue.length; i++) {
        							for(var j=0; j< eArr.length; j++) {
        								if(propValue[i] == eArr[j].value) {
        									eArr[j].checked = true; 
        								}
        							}// for j
        						}//for i
        					}else if(e.type == "select-multiple") {
        						for(var i=0; i < e.options.length; i++) {
        							var opt = e.options[i];
        							opt.selected = false;
        						}
        						for(var i=0; i < e.options.length; i++) {
        							var opt = e.options[i];
        							for(var j=0; j < propValue.length; j++) {
        								if(propValue[j] == opt.value) {
        									opt.selected = true;  
        								} 
        							}// for j
        						}// for i 
        					}
        				}else {
        					// JSON Object
        					this.setFormRecursive(prefix + key, propValue);
        				}
        			} // object
        			else {
        				if(!e)  {  continue;  } // element가 없으면 skip
        				if(e.type) {
        					if( e.type == "text" ) {
        						/*
								if( $(e).hasClass("comma") ) {
        							e.value = osstem.string.formatComma(propValue);
								}else if( $(e).hasClass("ssn") ) {
        							e.value = osstem.string.formatSsn(propValue);
								}else if( $(e).hasClass("telephone") ) {
									e.value = osstem.string.formatTelNo(propValue);
								}else if( $(e).hasClass("zipcode") ) {
									e.value = osstem.string.formatZipCode(propValue);
								}else {
								}
								*/
        						e.value = propValue;
        					}else if( e.type == "hidden"  || e.type == "password" || e.type == "textarea") {
        						e.value = propValue;
        					}else if(e.type == "checkbox") {
        						for(var i=0; i < eArr.length; i++) {
        							eArr[i].checked = "false";
        							if(eArr[i].value == propValue) {
        								eArr[i].checked = "true";  
        							}
        						}// for 
        					}else if(e.type == "radio")  {
        						for(var i=0; i < eArr.length; i++) {
        							eArr[i].checked = false;
        							if(eArr[i].value == propValue) {
        								eArr[i].checked = true; 
        								break;  
        							}
        						}// for
        					}else if(e.type == "select-one") {
        						for(var i=0; i < e.options.length; i++) {
        							var opt = e.options[i]; 
        							opt.selected = false;
        							if(opt.value == propValue) {
        								opt.selected = true;
        								break;
        							} 
        						}// for 
        					}else if(e.type == "select-multiple") {
        						for(var i=0; i < e.options.length; i++) {
        							var opt = e.options[i];
        							opt.selected = false;
        							if(opt.value == propValue) {
        								opt.selected = true;
        								break;
        							}
                               }// for
        					}
                       }else {
                           //
                           e.innerHTML = propValue;
                       }
        			}
        		}// for
        	},
        	/** createData의 내부적인 함수 입니다. */
        	setJSONMember : function(rootObject  , e ) {
        		var dataName = $(e).attr('data-name');
        		if(/* 객체안의 객체 */ dataName.indexOf(".") > 0) {
        			var names  = dataName.split(".");  
        			var jsonMember = null;
        			for(var i=0; i < names.length-1; i++) {
        				if(i == 0) { 
        					if(!rootObject[names[i]]) rootObject[names[i]] = {};
        					jsonMember = rootObject[names[i]];
        				}else {
        					if(!jsonMember[names[i]]) jsonMember[names[i]] = {};
        					jsonMember = jsonMember[names[i]];
        				}
        			}// for
        			if(e.type) { 
        				osstem.data.setJSONMemberByValue(jsonMember, names[names.length-1] ,e); 
        			}else { 
        				jsonMember[names[names.length-1]] = $(e).html();
        			}
                }else{ 
                	// e : form element
                	if(e.type) {
                		osstem.data.setJSONMemberByValue(rootObject, dataName, e);  
                    }else { 
                    	rootObject[dataName] = $(e).html();
                    }
                }
        		return rootObject; 
        	},
        	/** 
        	 * createData의 내부적인 함수 입니다.
        	 * JSON 객체의 속성값을 채운다. 
        	 */
            setJSONMemberByValue : function(fld  /* { } --> 즉, JSON Object */, name, e) {
            	// fld[name]은  object.field와 같은 의미. 
            	switch(e.type) { 
            		case "radio":
            			fld[name] = e.value; 
            			break;
            		case "select-multiple":
            			fld[name] =[];
            			var arr = fld[name];
            			for(var j=0; j < e.options.length;j++) { 
            				if(e.options[j].selected) { 
            					arr.push(e.options[j].value);
            				}
            			}// for
            			break;
            		case "select-one":
            			fld[name] =  e.options[e.selectedIndex].value;
            			break;
            		case "checkbox":
            			var  dataName = $(e).attr('data-name');
            			var  ele = $('*[data-name='  + dataName +  ']');
            			if(ele.length == 1){// Checkbox 가 1개인경우 객체로 생성
            				if(e.checked) {
            					fld[name] = e.value; 
            	            }else{
            	                fld[name] = null;
            	            }
            				break;
            			}
            			// Array
            			if(!fld[name]) fld[name] = [];
            			var chkbox = fld[name];
            			if(e.checked) {
            				chkbox.push(e.value); 
            			}
            			break;
            		case "text":
            		case "hidden":
            		case "password":
            			fld[name] = e.value;
            			break;
            		case "textarea":
            			fld[name] = osstem.replace(e.value, "\r\n", "\\n");
            			//fld[name] = e.value;
            			break;
            	}// switch
            },
            /**
             * html element의 값을 읽어서 JSON 객체에 값을 채웁니다.
             */
            createData : function() {
            	var model = {};
            	var eArr = $("*[data-name]");
            	if(!eArr) return; 
            	for(var i=0; i < eArr.length; i++) {
            		osstem.data.setJSONMember(model, eArr[i]);
            	}// for
            	return model;
            },
            /**
             * form element의 option 값을 가지고 옵션이 선택이 되도록 한다
             * @param selector
             * @param optionValue
             */
            setSelect : function(selector, optionValue) {
            	$(selector).each(function() {
            		if(this.value == optionValue) {
            			this.selected = true;
            		}else { 
            			this.selected = false; 
            		}
            	});
            },	
            /**
             * multiple 속성을 가진 select 옵션 선택하게 만들기
             * @param selector jQuery selector
             * @parma optionValues :  [] 형태의 값 
             */
            setSelectMultiple: function(selector, optionValues) {
            	$(selector).each(function() {  
            		for(var i=0; i < optionValues.length; i++) { 
            			if(optionValues[i] === this.value)  {
            				this.selected = true; 
            			}
            		}
            	});               
            },
            /**
             * input=radio 의 값 설정합니다.
             * @param selector jQuery selector
             * @parma radioValue
             */
            setRadio: function(selector, radioValue) {
            	$(selector).each(function() {
            		if(this.value == radioValue) {
            			this.checked = true;
            		}else {
            			this.checked = false;
            		}
            	}); 
            },
            /** 
             * checkbox  값 설정합니다. 
             * @param selector jQuery selector
             * @parma checkValues :  [] 형태의 값 
             */ 
            setCheckbox : function(selector, checkValues) {
            	$(selector).each(function () {
            		this.checked = false; 
            	});
            	$(selector).each(function () {
            		for(var i=0; i < checkValues.length; i++) {
            			if(this.value == checkValues[i]) {
            				this.checked = true;    
            			}
            		}
            	});
            },
            /** 
             * select box 순서를 변경합니다. 
             * @param elementId
             * @parma direction
             */ 
            moveSelect : function(elementId, direction) {
            	/*
            	var selector = "#"+elementId;
            	var optionObjects = [];
        		var optionObjectsIndex = [];
        		$(selector+" option").each(function(){
					optionObjects.push($(this));
        			optionObjectsIndex.push($(this).index());
        		});
        		if(direction == "up"){
        			$(selector+" option:selected").each(function(){
        				var idx = $(selector+" option").index(this);
        				var newPos = idx - 1;
        				if(newPos > -1){
        					$(selector+" option").eq(newPos).before(optionObjects[idx]);
        				}
        			});
        		}else if(direction == "down"){
        			for(var i=optionObjects.length-1; i>=0; i--){
        				if(optionObjectsIndex[i]+1 == optionObjectsIndex.length){
        					continue;
        				}else{
        					if($(selector+" option").eq([optionObjectsIndex[i]]).prop("selected")){
        						$(selector+" option").eq([optionObjectsIndex[i]+1]).after(optionObjects[i]);
        					}
        				}
        			}
        		}
        		*/
            	var element = document.getElementById(elementId);  // Multiple Select Element
                var selIndex = element.selectedIndex;              // Selected Index
                var elementLength = element.options.length;        // Select Element Item Length
                var selText = element.options[selIndex].text;      // Selected Item Text
                var selValue = element.options[selIndex].value;    // Selected Item Value
                if(selIndex < 0) {
                    return;
                }
                if(direction == "top") {  // 최상위로 이동
                    var index = selIndex;
                    while(index > 0) {
                        element.options[index].text = element.options[index-1].text;
                        element.options[index].value = element.options[index-1].value;
                        index--;
                    }
                    element.options[0].text = selText;
                    element.options[0].value = selValue;
                    element.selectedIndex = 0;
                } else if(direction == "up") {  // 위로 이동
                    if(selIndex-1 < 0) return;
                    var oldText = element.options[selIndex-1].text;
                    var oldValue = element.options[selIndex-1].value;
                    element.options[selIndex-1].text = selText;
                    element.options[selIndex-1].value = selValue;
                    element.options[selIndex].text = oldText;
                    element.options[selIndex].value = oldValue;
                    element.selectedIndex = selIndex-1;
                } else if(direction == "down") {  // 아래로 이동
                    if(selIndex+2 > elementLength) return;
                    var oldText = element.options[selIndex+1].text;
                    var oldValue = element.options[selIndex+1].value;
                    element.options[selIndex+1].text = selText;
                    element.options[selIndex+1].value = selValue;
                    element.options[selIndex].text = oldText;
                    element.options[selIndex].value = oldValue;
                    element.selectedIndex = selIndex+1;
                } else if(direction == "bottom") {  // 최하위로 이동
                    var index = selIndex;
                    while(index < elementLength-1) {
                        element.options[index].text = element.options[index+1].text;
                        element.options[index].value = element.options[index+1].value;
                        index++;
                    }
                    element.options[element.options.length-1].text = selText;
                    element.options[element.options.length-1].value = selValue;
                    element.selectedIndex = element.options.length-1;
                }
                else return;
            },
            /**
        	 * selectbox의 option 모두 제거합니다. 
        	 * @param selector jQuery selector
        	 */
        	seletOptionRemoveAll : function(selector){
        		while($(selector+" option").length != 0 ){
        			$(selector+" option:eq(0)").remove();
        		}
        	},
        	/**
        	 * selectbox의 선택된 option 제거합니다.
        	 * @param selector jQuery selector
        	 */
        	seletOptionRemove : function(selector){
        		$(selector+" option:selected").remove();
        	},
        	/**
        	 * 첫번째 checkbox 체크여부를 하위 checkbox에 적용 
        	 * @param {string} selector_First 첫번째 checkbox
        	 * @param {string} selector_Array 하위 checkbox
        	 */
        	checkBoxCheckAll : function(selector_First,selector_Array){
    			$(selector_Array).each(function(){
    				$(this).prop('checked',$(selector_First).prop("checked"));
    			});
        	},
            /** dummy function */
            dummy: function() {
            	// not used 
            }           
        };
        
        // ----------------------------------------------------------------------- osstem.ui section
        osstem.ui =  {
            /** 
             *  modal dialog를 표시한다. 
             *  jquery.ui.dialog 를 참조한다. 
             *  다른 점이 있다면 selector를 옵션에 추가적으로 정의한다. 
             *  osstem.js 내부에서만 사용한다. 
             *  { selector :"#dialog-modal" }
             *   
             *  @param opt  json object, jquery.ui.dialog의 option 참조
             */
        	showModal:function(opts) {
        		var opts2 = $.extend( {}, opts, { modal:true} );
        		return $(opts.selector).dialog(opts2);
        	},
        	/** dialog를 표시한다. osstem.js 내부적으로만 사용함 */
        	showDialog: function(opts) {
        		return $(opts.selector).dialog(opts);
        	},
        	/** 
             * 이미지를 마우스 오버/아웃시 스왑합니다. 
             * @param src jQuery selector
             * @param overImage
             * @param outImage 
             */ 
        	swapImage : function(src, overImage, outImage) {
        		$(src).mouseover(function() {
        			this.src = overImage;
        		}).mouseout(function() {
        			this.src =  outImage;
        		});
        	},
        	/**
        	 * iframe을 구한다. cross browser 지원
        	 * @param iframeId  iframe's ID
        	 */
        	getIframe :  function(iframeId) {
        		return document.getElementById(iframeId).contentWindow || document.frames[iframeId];
        	},
        	/**
        	 *  팝업윈도우를 중앙에 위치시키기 위한 좌표 계산
        	 *  <pre>
        	 *  var dimension = osstem.ui.getCenterXY(448,366);
        	 *  window.open(url,'postalCode','width=448,height=366,top=' + dimension.Y + ',left=' + dimension.X);
        	 *  </pre>
        	 *
        	 *  @param w  팝업창의 width
        	 *  @param h  팝업창의 height
        	 *  @return   좌표객체 (.X : 좌측위치, .Y : 위쪽 위치 )
        	 */
        	getCenterXY :  function(w, h) {
        		var dimension = {};
        		demention.X = (screen.availWidth / 2) - (w / 2 );
        		demention.Y = (screen.availHeight /2) - (h/2) - 40;
        		return dimension;
        	},
        	/**
        	 * 태그의 좌표와 width, height를 반환 
        	 * 
        	 * Example)
        	 *  Html codes:
        	 *  	<div id="layer1" style="border:1px solid #cccccc;">1</div>
        	 *  
        	 *  Javascript Codes:
        	 * 		var temp1 = osstem.ui.getBounds("#layer1");
        	 * 		for (var i in temp1) {
        	 * 			console.log(i+":"+temp1[i]);		
        	 * 		}
        	 * @param selector 요소 selector
        	 */
        	getBounds : function(selector) {
        		/* var ret = { left:0, top:0, width:0,height:0 };
                 if(document.getBoxObjectFor) { 
                     var box = document.getBoxObjectFor(tag); 
                     ret.left = box.x; 
                     ret.top = box.y; 
                     ret.width = box.width; 
                     ret.height = box.height;
                 } else if(tag.getBoundingClientRect)  { // IE, FF3
                     var rect = tag.getBoundingClientRect(); 
                     ret.left = rect.left + (document.documentElement.scrollLeft || document.body.scrollLeft); 
                     ret.top  = rect.top + (document.documentElement.scrollTop || document.body.scrollTop); 
                     ret.width = rect.right - rect.left; 
                     ret.height = rect.bottom - rect.top; 
                 }
                 */
        		var ret = {
        			left: $(selector).offset().left,
        			top : $(selector).offset().top,
        			height: $(selector).outerHeight(),
        			width:  $(selector).outerWidth()
        		};
        		return ret; 
        	},
        	/**
        	 * div를 이벤트가 발생한 엘리먼트의 아래에 표시합니다.
        	 * 
        	 * Example)
        	 *  Html codes:
        	 *     <input type="text" id="test" value="" />
        	 *     
        	 *     <div id="layer1">Contents here </div>
        	 * 
        	 *  Javascript Codes:
        	 *     $("#test").click(function() {
        	 *          osstem.ui.showLayer("layer1", this);
        	 *     });
        	 *
        	 * @param  selector : selector. a div shows  below this target element.
        	 * @param  source      : event source element 
        	 */
        	showLayer: function(selector, source) {
        		//var position = $(source).position();
        		var offset   = $(source).offset();
        		var height   = $(source).outerHeight();
        		//var width    = $(source).outerWidth();
        		if (typeof console == "object") {
        			console.log(offset.left);
            		console.log(offset.top + height);
        		}
        		return $(selector)
        			.css("left",offset.left)
        			.css("top",offset.top + height)
        			.css("position","absolute")
        			.show();
        	}
        };

        // ----------------------------------------------------------------------- Sample Object
        
    	/**
    	 * JavaBean 유사한 Bean 객체
    	 * 
    	 * Example)
    	 *  	var module = osstem.bean("module");
    	 *		console.log('module : '+module);
    	 *		console.log('module.get() : '+module.get());
    	 *
    	 * @param  value
    	 */
        osstem.bean =  function(value){
        	var val = value;// private
    		function setValue(v){
    			if(v) val = v;
    		};
    		function getValue(){
    			return val;
    		};
    		return {
    			set : setValue,
    			get : getValue
    		};
        };
        
        osstem.hello =  {
        	aa:function() {
        		if (typeof console == "object") {
        			console.log('aa');
        		}
        		osstem.hello.bb();
        	},
        	bb:function() {
        		if (typeof console == "object") {
        			console.log('bb');
        		}
        	}
        };

        return osstem;
    })();

    window.osstem = osstem;
})(jQuery);