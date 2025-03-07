package ilike.ildrio.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	public String getErrorPath() {
		return ERROR_PATH;
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String errPage = "error/error";

		HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(status.toString()));
		log.info("httpStatus : " + httpStatus);
		model.addAttribute("code", status.toString());
		model.addAttribute("msg", httpStatus.getReasonPhrase());
		model.addAttribute("timestamp", new Date());

		if (status.toString().equals("404")) {
			errPage = "error/not_found";
		}
		if (status.toString().equals("500")) {
			errPage = "error/server_error";
		}

		return errPage;

	}

	

}
