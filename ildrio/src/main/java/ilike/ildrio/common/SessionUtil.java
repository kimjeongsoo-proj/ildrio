package ilike.ildrio.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
@Log4j2
public class SessionUtil {

	private final ObjectMapper mapper;
	private final HttpSession session;

	public SessionUtil(ObjectMapper mapper, HttpSession session) {
		this.mapper = mapper;
		this.session = session;
	}

	
}
