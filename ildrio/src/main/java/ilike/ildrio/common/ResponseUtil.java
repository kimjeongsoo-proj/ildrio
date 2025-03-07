package ilike.ildrio.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;


/**
 * @author js.kim
 * @packageName :ilike.ildrio.common
 * @fileName : ResponseUtil.java
 * @date : 2021. 10. 25.
 * @description :
 * ==========================================
 * DATE       		AUTHOR 			CONTENT
 * -------------------------------------------
 * 2021. 10. 25   js.kim       REST API Util
 */
@Component
public class ResponseUtil {


    /**
     * 비동기 통신 시 응답 할 공통코드
     *
     * @param req
     * @param map
     * @param headers
     * @return
     * @methodName : getResponseEntity
     * @author : js.kim
     * @date : 2021. 10. 25.
     */
    public HttpEntity<Object> getResponseEntity(HttpServletRequest req, Map<String, Object> map, HttpHeaders headers) {
        map.put("method", req.getMethod());
        map.put("status", HttpStatus.OK.value());
        map.put("country", req.getLocale().getCountry());
        map.put("response_time", LocalDateTime.now());

        return new ResponseEntity<>(map, headers, HttpStatus.OK);
    }


}
